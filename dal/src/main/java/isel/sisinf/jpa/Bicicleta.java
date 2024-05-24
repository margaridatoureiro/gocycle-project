package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Set;

/*
CREATE TABLE Bicicleta(
	id serial PRIMARY KEY,
	peso numeric(4,2) NOT NULL,
	raio integer NOT NULL,
	modelo varchar(20) NOT NULL,
	marca varchar(30) NOT NULL,
	mudanca integer,
	estado varchar(30) NOT NULL,
	atrdisc char(2) NOT NULL,
	dispositivo integer NOT NULL REFERENCES Dispositivo(noserie),
	constraint CHK_Bicicleta_raio CHECK (raio BETWEEN 13 AND 23),
	constraint CHK_Bicicleta_mudanca CHECK (mudanca IN(1, 6, 18, 24)),
	constraint CHK_Bicicleta_estado CHECK (estado IN('livre', 'ocupado', 'em manutenção')),
	constraint CHK_Bicicleta_atrdisc CHECK (atrdisc IN('C', 'E'))
);
 */


 @Entity
 @Table(name = "Bicicleta")
 public class Bicicleta {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
 
     @Column(nullable = false, precision = 4, scale = 2)
     private BigDecimal peso;
 
     @Column(nullable = false)
     private Integer raio;
 
     @Column(nullable = false, length = 20)
     private String modelo;
 
     @Column(nullable = false, length = 30)
     private String marca;
 
     private Integer mudanca;
 
     @Column(nullable = false, length = 30)
     private String estado;
 
     @Column(nullable = false, length = 2)
     private String atrdisc;
 
     @ManyToOne
     @JoinColumn(name = "dispositivo", nullable = false)
     private Dispositivo dispositivo;
 
     // getters and setters
 }