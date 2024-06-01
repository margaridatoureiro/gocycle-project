package isel.sisinf.model;

import jakarta.persistence.*;

import java.io.Serializable;

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