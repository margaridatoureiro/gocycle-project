package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Set;

/*CREATE TABLE Reserva(
	noreserva serial,
	loja integer REFERENCES Loja(codigo),
	dtinicio timestamp NOT NULL, 
	dtfim timestamp,
	valor numeric(4,2) NOT NULL,
	bicicleta integer NOT NULL REFERENCES Bicicleta(id),
	PRIMARY KEY (noreserva, loja),
	constraint CHK_Reserva_dtfim CHECK (dtfim > dtinicio)
);
 */

 @Entity
 @Table(name = "Reserva")
 public class Reserva {
     @EmbeddedId
     private ReservaId id;
 
     @ManyToOne
     @JoinColumn(name = "loja", insertable = false, updatable = false)
     private Loja loja;
 
     @Column(nullable = false)
     private Timestamp dtinicio;
 
     @Column
     private Timestamp dtfim;
 
     @Column(nullable = false, precision = 4, scale = 2)
     private BigDecimal valor;
 
     @ManyToOne
     @JoinColumn(name = "bicicleta", nullable = false)
     private Bicicleta bicicleta;
 
     // getters and setters
 }