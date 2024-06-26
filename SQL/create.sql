-- noinspection SqlDialectInspectionForFile
-- noinspection SqlNoDataSourceInspectionForFile

start transaction;
do $$
begin

DROP TRIGGER IF EXISTS check_bike_availability_trigger ON Reserva;
DROP FUNCTION IF EXISTS check_bike_availability();
DROP FUNCTION IF EXISTS is_bike_available_on_date();
DROP TABLE IF EXISTS ClienteReserva;
DROP TABLE IF EXISTS Reserva CASCADE;
DROP TABLE IF EXISTS Eletrica;
DROP TABLE IF EXISTS Classica;
DROP TABLE IF EXISTS Bicicleta CASCADE;
DROP TABLE IF EXISTS Dispositivo CASCADE;
DROP TABLE IF EXISTS TelefoneLoja; 
DROP TABLE IF EXISTS Loja CASCADE;
DROP TABLE IF EXISTS Pessoa CASCADE;


CREATE TABLE Pessoa(
	id serial PRIMARY KEY,
	nome varchar(40) NOT NULL,
	morada varchar(150) NOT NULL,
	email varchar(40) UNIQUE NOT NULL,
	telefone varchar(30) UNIQUE NOT NULL,
	noident char(12) UNIQUE NOT NULL,
	nacionalidade varchar(20) NOT NULL,
	atrdisc char(2) NOT NULL,
	constraint CHK_Pessoa_email CHECK (email ~ '.+@.+\.\w{1,}'),
	constraint CHK_Pessoa_telefone CHECK (telefone ~ '^\d{4,15}$'),
	constraint CHK_Pessoa_atrdisc CHECK (atrdisc IN('G', 'C'))
);

CREATE TABLE Loja(
	codigo integer PRIMARY KEY,
	email varchar(40) UNIQUE NOT NULL,
	endereco varchar(100) NOT NULL,
	localidade varchar(30) NOT NULL,
	gestor integer NOT NULL REFERENCES Pessoa(id),
	constraint CHK_Loja_email CHECK (email ~ '.+@.+\.\w{1,}')
);

CREATE TABLE TelefoneLoja(
	loja integer REFERENCES Loja(codigo),
	numero varchar(10),
	PRIMARY KEY(loja, numero),
	constraint CHK_TelefoneLoja_numero CHECK (numero ~ '^\d{4,15}$')
);


CREATE TABLE Dispositivo(
	noserie integer PRIMARY KEY,
	latitude numeric(6,4) NOT NULL,
	longitude numeric(6,4) NOT NULL,
	bateria integer NOT NULL,
	constraint CHK_Dispositivo_bateria CHECK (bateria BETWEEN 0 AND 100)
);

CREATE TABLE Bicicleta(
	id serial PRIMARY KEY,
	peso numeric(4,2) NOT NULL,
	raio integer NOT NULL,
	modelo varchar(20) NOT NULL,
	marca varchar(30) NOT NULL,
	mudanca integer,
	estado varchar(30) NOT NULL,
	atrdisc char(2) NOT NULL,
    dispositivo integer NOT NULL UNIQUE,
    CONSTRAINT FK_Bicicleta_Dispositivo FOREIGN KEY (dispositivo) REFERENCES Dispositivo(noserie),
	constraint CHK_Bicicleta_raio CHECK (raio BETWEEN 13 AND 23),
	constraint CHK_Bicicleta_mudanca CHECK (mudanca IN(1, 6, 18, 24)),
	constraint CHK_Bicicleta_estado CHECK (estado IN('livre', 'ocupado', 'em manutenção')),
	constraint CHK_Bicicleta_atrdisc CHECK (atrdisc IN('C', 'E'))
);

CREATE TABLE Classica(
	bicicleta integer PRIMARY KEY REFERENCES Bicicleta(id),
	nomudanca integer NOT NULL,
	constraint CHK_Classica_nomudanca CHECK (nomudanca BETWEEN 0 AND 5)
);

CREATE TABLE Eletrica(
	bicicleta integer PRIMARY KEY REFERENCES Bicicleta(id),
	autonomia integer NOT NULL,
	velocidade integer NOT NULL
);


CREATE TABLE Reserva(
	noreserva serial,
	loja integer REFERENCES Loja(codigo),
	dtinicio timestamp NOT NULL, 
	dtfim timestamp,
	valor numeric(4,2) NOT NULL,
	bicicleta integer NOT NULL REFERENCES Bicicleta(id),
	version integer not null default 0,
	PRIMARY KEY (noreserva, loja),
	constraint CHK_Reserva_dtfim CHECK (dtfim > dtinicio)
);


CREATE TABLE ClienteReserva(
	cliente integer REFERENCES Pessoa(id),
	reserva integer,
	loja integer,
	PRIMARY KEY (cliente, reserva, loja),
	FOREIGN KEY (reserva, loja) REFERENCES Reserva(noreserva, loja)
);

end; $$ ;
commit;

CREATE OR REPLACE FUNCTION is_bike_available_on_date(bike_id INTEGER, check_date TIMESTAMP)
RETURNS BOOLEAN AS $$
DECLARE
    count_reservations INTEGER;
BEGIN
    SELECT COUNT(*)
    INTO count_reservations
    FROM reserva
    WHERE bicicleta = bike_id AND (check_date >= dtinicio AND (dtfim IS NULL OR check_date <= dtfim));

    IF count_reservations > 0 THEN
        RETURN FALSE;
    ELSE
        RETURN TRUE;
    END IF;
END; $$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION check_bike_availability()
    RETURNS TRIGGER AS $$
BEGIN
    -- Call the is_bike_available_on_date function with the necessary parameters
    -- For example, if the Reserva table has columns 'bike_id' and 'date'
    IF NOT is_bike_available_on_date(NEW.bicicleta, NEW.dtinicio) THEN
        RAISE EXCEPTION 'The bike is not available on the specified date';
    END IF;

    -- If the bike is available, return NEW to allow the insert operation to proceed
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER check_bike_availability_trigger
BEFORE INSERT ON Reserva
FOR EACH ROW
EXECUTE PROCEDURE check_bike_availability();