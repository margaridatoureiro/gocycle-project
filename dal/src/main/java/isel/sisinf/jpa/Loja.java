package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Set;

/*CREATE TABLE Loja(
	codigo integer PRIMARY KEY,
	email varchar(40) UNIQUE NOT NULL,
	endereco varchar(100) NOT NULL,
	localidade varchar(30) NOT NULL,
	gestor integer NOT NULL REFERENCES Pessoa(id),
	constraint CHK_Loja_email CHECK (email ~ '.+@.+\.\w{1,}')
);
 */

@Entity
@Table(name = "Loja")
public class Loja {
    @Id
    private Integer codigo;

    @Column(nullable = false, unique = true, length = 40)
    private String email;

    @Column(nullable = false, length = 100)
    private String endereco;

    @Column(nullable = false, length = 30)
    private String localidade;

    @ManyToOne
    @JoinColumn(name = "gestor", nullable = false)
    private Pessoa gestor;

    // getters and setters
}