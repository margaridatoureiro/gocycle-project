package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Set;

/*
CREATE TABLE ClienteReserva(
	cliente integer REFERENCES Pessoa(id),
	reserva integer,
	loja integer,
	PRIMARY KEY (cliente, reserva, loja),
	FOREIGN KEY (reserva, loja) REFERENCES Reserva(noreserva, loja)
);
 */

//ver tp 12   join table

 @Embeddable
 class ClienteReservaId implements Serializable {
     @ManyToOne
     @JoinColumn(name = "cliente")
     private Pessoa cliente;
 
     @ManyToOne
     @JoinColumn(name = "reserva")
     private Reserva reserva;
 
     @ManyToOne
     @JoinColumn(name = "loja")
     private Loja loja;
 
 }