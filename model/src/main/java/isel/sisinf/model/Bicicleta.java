package isel.sisinf.model;
import jakarta.persistence.*;

import java.math.BigDecimal;


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