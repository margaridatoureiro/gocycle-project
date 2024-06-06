-- noinspection SqlDialectInspectionForFile
-- noinspection SqlNoDataSourceInspectionForFile
start transaction;

INSERT INTO Pessoa (nome, morada, email, telefone, noident, nacionalidade, atrdisc) VALUES
    ('Peter Pan', 'Terra do Nunca', 'peter@gmail.com', '919821731', '15223370', 'Inglesa','C'),
    ('Mulan', 'Rua do Sol-posto', 'mulan@gmail.com', '919821732', '15223371', 'Chinesa', 'G'),
    ('Pocahontas', 'Floresta Encantada', 'pocahontas@gmail.com', '919821733', '15223372', 'Nativa Americana', 'C'),
    ('Hércules', 'Praça Central de Atenas','hercules@gmail.com', '919821734', '15223373', 'Grega', 'C'),
    ('João Filipe', 'Avenida da República','joaofilipe@gmail.com', '919821735', '15223374', 'Portuguesa', 'C'),
    ('Alice', 'País das Maravilhas','alice@gmail.com', '919821736', '15223375', 'Alemã', 'C'),
    ('José Manuel', 'Avenida de Roma','zemanel@gmail.com', '919821737', '15223376', 'Portuguesa', 'C'),
    ('Christopher Robin', 'Bosque dos Cem Acres','christopher@gmail.com', '919821738', '15223377', 'Inglesa', 'G');


INSERT INTO Loja (codigo, email, endereco, localidade, gestor) VALUES
	(
		123, 'magic-store@gmail.com', 'Diagon Alley', 'Londres', 
		(SELECT id FROM Pessoa WHERE noident = '15223371')
	),
	(
		456, 'theportugueseshire@gmail.com', 'The Mediterranean Shire Avenue', 'Lisboa', 
		(SELECT id FROM Pessoa WHERE noident = '15223374')
	),
	(
		789, 'ghoststore@gmail.com', 'Graveyard USA', 'Lisboa', 
		(SELECT id FROM Pessoa WHERE noident = '15223376')
	)
	;


INSERT INTO TelefoneLoja (loja, numero) VALUES
	(
		(SELECT codigo FROM Loja WHERE endereco = 'Diagon Alley'),
		'212166476'
	),
	(
		(SELECT codigo FROM Loja WHERE endereco = 'The Mediterranean Shire Avenue'),
		'212166477'
	);


INSERT INTO Dispositivo (noserie, latitude, longitude, bateria) VALUES
    (771, 39.74, -8.81, 10),
    (772, 39.74, -8.81, 70),
    (773, 39.74, -8.81, 100),
    (774, 39.74, -8.81, 35),
    (775, 39.74, -8.81, 99),
	(776, 39.74, -8.81, 80),
	(777, 39.74, -8.81, 40),
	(778, 39.74, -8.81, 100),
    (779, 39.74, -8.81, 100);


INSERT INTO Bicicleta (peso, raio, modelo, marca, mudanca, estado, atrdisc, dispositivo) VALUES
	(61.01,  15, 'Mountain 3000', 'Trek', 18, 'livre', 'C',
		(SELECT noserie FROM Dispositivo WHERE noserie = 771)
	),
	(82.01,  20, 'Supreme FX', 'Trek', 24, 'em manutenção', 'E',
		(SELECT noserie FROM Dispositivo WHERE noserie = 772)
	),
	(73.01,  13, 'Speedy Kids', 'Trek', 6, 'livre', 'C',
		(SELECT noserie FROM Dispositivo WHERE noserie = 773)
	),
	(61.01,  20, 'Mountain Roll 4000', 'Trek', 24, 'livre', 'E',
		(SELECT noserie FROM Dispositivo WHERE noserie = 774)
	),
	(82.01,  20, 'Radical Trip', 'Trek', 24, 'livre', 'E',
		(SELECT noserie FROM Dispositivo WHERE noserie = 775)
	),
	(73.01,  14, 'Dutch Treasure', 'Trek', 1, 'livre', 'C',
		(SELECT noserie FROM Dispositivo WHERE noserie = 776)
	),
	(78.01,  14, 'Black Pearl', 'Trek', 6, 'em manutenção', 'C',
		(SELECT noserie FROM Dispositivo WHERE noserie = 777)
	),
	(78.20,  15, 'Pegasus', 'Trek', 18, 'em manutenção', 'C',
		(SELECT noserie FROM Dispositivo WHERE noserie = 778)
	),
	(65.56,  17, 'Modelo-B', 'Marca-A', 24, 'livre', 'C',
		(SELECT noserie FROM Dispositivo WHERE noserie = 779)
	 )
	;

INSERT INTO Classica (bicicleta, nomudanca) VALUES
	((SELECT id FROM Bicicleta WHERE modelo = 'Mountain 3000'), 5),
	((SELECT id FROM Bicicleta WHERE modelo = 'Speedy Kids'), 3),
	((SELECT id FROM Bicicleta WHERE modelo = 'Dutch Treasure'), 2),
  	((SELECT id FROM Bicicleta WHERE modelo = 'Black Pearl'), 4),
  	((SELECT id FROM Bicicleta WHERE modelo = 'Pegasus'), 5),
	((SELECT id FROM Bicicleta WHERE modelo = 'Modelo-B'), 4)
;

INSERT INTO Eletrica (bicicleta, autonomia, velocidade) VALUES
	((SELECT id FROM Bicicleta WHERE modelo = 'Supreme FX'), 200, 150),
	((SELECT id FROM Bicicleta WHERE modelo = 'Mountain Roll 4000'), 220, 150),
	((SELECT id FROM Bicicleta WHERE modelo = 'Radical Trip'), 170, 120)
;

INSERT INTO Reserva (loja, dtinicio, dtfim, valor, bicicleta) VALUES
	(
		(SELECT codigo FROM Loja WHERE endereco = 'Diagon Alley'),
		'2024-11-21 10:05:34',
		'2024-11-28 10:05:34',
		22.75,
		(SELECT id FROM Bicicleta WHERE modelo = 'Mountain 3000')
	),
	(
		(SELECT codigo FROM Loja WHERE endereco = 'Diagon Alley'),
		'2029-11-22 10:05:34',
		'2029-11-28 10:05:34',
		30.75,
		(SELECT id FROM Bicicleta WHERE modelo = 'Supreme FX')
	),
	(
		(SELECT codigo FROM Loja WHERE endereco = 'The Mediterranean Shire Avenue'),
		'2024-08-01 10:05:34',
		'2024-08-10 10:05:34',
		26.55,
		(SELECT id FROM Bicicleta WHERE modelo = 'Dutch Treasure')
	),
	(
		(SELECT codigo FROM Loja WHERE endereco = 'The Mediterranean Shire Avenue'),
		'2026-11-22 10:05:34',
		'2026-11-30 10:05:34',
		20.65,
		(SELECT id FROM Bicicleta WHERE modelo = 'Mountain Roll 4000')
	),
	(
		(SELECT codigo FROM Loja WHERE endereco = 'The Mediterranean Shire Avenue'),
		'2025-11-22 10:05:34',
		'2025-11-29 16:05:34',
		20.65,
		(SELECT id FROM Bicicleta WHERE modelo = 'Radical Trip')
	),
	(
		(SELECT codigo FROM Loja WHERE endereco = 'The Mediterranean Shire Avenue'),
		'2027-11-16 12:05:34',
		'2027-11-18 10:05:34',
		20.65,
		(SELECT id FROM Bicicleta WHERE modelo = 'Speedy Kids')
	),
	(
		(SELECT codigo FROM Loja WHERE endereco = 'The Mediterranean Shire Avenue'),
		'2024-12-20 19:05:34',
		'2024-12-30 10:05:34',
		20.65,
		(SELECT id FROM Bicicleta WHERE modelo = 'Radical Trip')
	),
	(
		(SELECT codigo FROM Loja WHERE endereco = 'The Mediterranean Shire Avenue'),
		'2028-11-22 11:05:34',
		'2028-11-30 10:05:34',
		20.65,
		(SELECT id FROM Bicicleta WHERE modelo = 'Mountain 3000')
	),
	(
		(SELECT codigo FROM Loja WHERE endereco = 'The Mediterranean Shire Avenue'),
		'2024-11-22 11:05:34',
		'2024-11-30 10:05:34',
		30.75,
		(SELECT id FROM Bicicleta WHERE modelo = 'Supreme FX')
	),
	(
		(SELECT codigo FROM Loja WHERE endereco = 'The Mediterranean Shire Avenue'),
		'2024-10-22 11:05:34',
		'2024-10-30 10:05:34',
		30.75,
		(SELECT id FROM Bicicleta WHERE modelo = 'Supreme FX')
	),
	(
		(SELECT codigo FROM Loja WHERE endereco = 'The Mediterranean Shire Avenue'),
		'2026-11-10 11:05:34',
		'2026-11-30 10:05:34',
		30.75,
		(SELECT id FROM Bicicleta WHERE modelo = 'Dutch Treasure')
	),
	(
		(SELECT codigo FROM Loja WHERE endereco = 'The Mediterranean Shire Avenue'),
		'2024-11-20 11:05:34',
		'2024-11-30 10:05:34',
		30.75,
		(SELECT id FROM Bicicleta WHERE modelo = 'Mountain 3000')
	)
	;
	
commit;

DO $$
DECLARE
    reserva_id INTEGER;
    pessoa_id INTEGER;
    loja_id INTEGER;
BEGIN
    FOR reserva_id IN (SELECT noreserva FROM Reserva)
    LOOP
        -- Get the id of the Pessoa with the given noident
        SELECT id INTO pessoa_id FROM Pessoa LIMIT 1;

        -- Get the loja code of the Reserva with the given noreserva
        SELECT loja INTO loja_id FROM Reserva WHERE noreserva = reserva_id;

        -- Insert a new ClienteReserva entry
        INSERT INTO ClienteReserva (cliente, reserva, loja) VALUES (pessoa_id, reserva_id, loja_id);
    END LOOP;
END; $$;
