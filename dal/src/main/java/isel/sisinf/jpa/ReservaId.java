package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Set;

/* */
 

 @Embeddable
 class ReservaId implements Serializable {
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long noreserva;
 
     @ManyToOne
     @JoinColumn(name = "loja")
     private Loja loja;
 
     // getters and setters, equals and hashCode methods
 }