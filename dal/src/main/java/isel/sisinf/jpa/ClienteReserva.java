package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Set;

/*CREATE TABLE ClienteReserva(
	cliente integer REFERENCES Pessoa(id),
	reserva integer,
	loja integer,
	PRIMARY KEY (cliente, reserva, loja),
	FOREIGN KEY (reserva, loja) REFERENCES Reserva(noreserva, loja)
);
 */

 @Entity
 @Table(name = "ClienteReserva")
 public class ClienteReserva {
     @EmbeddedId
     private ClienteReservaId id;
 
     @ManyToOne
     @JoinColumns({
         @JoinColumn(name = "reserva", referencedColumnName = "noreserva", insertable = false, updatable = false),
         @JoinColumn(name = "loja", referencedColumnName = "loja", insertable = false, updatable = false)
     })
     private Reserva reserva;
 
     @ManyToOne
     @JoinColumn(name = "cliente", insertable = false, updatable = false)
     private Pessoa cliente;
 
     // getters and setters
 }