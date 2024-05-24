package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Set;

/*CREATE TABLE Eletrica(
	bicicleta integer PRIMARY KEY REFERENCES Bicicleta(id),
	autonomia integer NOT NULL,
	velocidade integer NOT NULL
);

 */

 @Entity
 @Table(name = "Eletrica")
 public class Eletrica {
     @Id
     @OneToOne
     @JoinColumn(name = "bicicleta")
     private Bicicleta bicicleta;
 
     @Column(nullable = false)
     private Integer autonomia;
 
     @Column(nullable = false)
     private Integer velocidade;
 
     // getters and setters
 }