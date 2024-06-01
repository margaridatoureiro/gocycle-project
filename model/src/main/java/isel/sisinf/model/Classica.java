package isel.sisinf.model;

import jakarta.persistence.*;
import java.util.Set;

/*CREATE TABLE Classica(
	bicicleta integer PRIMARY KEY REFERENCES Bicicleta(id),
	nomudanca integer NOT NULL,
	constraint CHK_Classica_nomudanca CHECK (nomudanca BETWEEN 0 AND 5)
);
 */

 @Entity
 @Table(name = "Classica")
 public class Classica {
     @Id
     @OneToOne
     @JoinColumn(name = "bicicleta")
     private Bicicleta bicicleta;
 
     @Column(nullable = false)
     private Integer nomudanca;
 
     // getters and setters
 }