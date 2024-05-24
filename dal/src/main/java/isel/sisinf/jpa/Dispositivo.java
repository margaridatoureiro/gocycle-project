package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Set;

/*
CREATE TABLE Dispositivo(
	noserie integer PRIMARY KEY,
	latitude numeric(6,4) NOT NULL,
	longitude numeric(6,4) NOT NULL,
	bateria integer NOT NULL,
	constraint CHK_Dispositivo_bateria CHECK (bateria BETWEEN 0 AND 100)
);
 */


 @Entity
 @Table(name = "Dispositivo")
 public class Dispositivo {
     @Id
     private Integer noserie;
 
     @Column(nullable = false, precision = 6, scale = 4)
     private BigDecimal latitude;
 
     @Column(nullable = false, precision = 6, scale = 4)
     private BigDecimal longitude;
 
     @Column(nullable = false)
     private Integer bateria;
 
     // getters and setters
 }