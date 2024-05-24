package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Set;

/*
 * 
 * 
 */

 @Embeddable
class TelefoneLojaId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "loja")
    private Loja loja;

    @Column(length = 10)
    private String numero;

    // getters and setters, equals and hashCode methods
}