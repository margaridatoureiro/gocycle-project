package isel.sisinf.model;

import jakarta.persistence.*;
import java.util.Set;

/*
 * CREATE TABLE TelefoneLoja(
	loja integer REFERENCES Loja(codigo),
	numero varchar(10),
	PRIMARY KEY(loja, numero),
	constraint CHK_TelefoneLoja_numero CHECK (numero ~ '^\d{4,15}$')
);
 */


@Entity
@Table(name = "TelefoneLoja")
public class TelefoneLoja {
    @EmbeddedId
    private TelefoneLojaId id;

    @Column(nullable = false, length = 10)
    private String numero;

    // getters and setters
}