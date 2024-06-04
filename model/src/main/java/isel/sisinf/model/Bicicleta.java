package isel.sisinf.model;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;


 @Entity
 @NamedQuery(name="Bicicleta.findByKey", query="SELECT b FROM Bicicleta b WHERE b.id = :key")
 @NamedQuery(name = "Bicicleta.getAll", query = "SELECT b FROM Bicicleta b")
 public class Bicicleta implements Serializable {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long id;
 
     @Column(nullable = false, precision = 4, scale = 2)
     private BigDecimal peso;
 
     @Column(nullable = false)
     private Integer raio;
 
     @Column(nullable = false)
     private String modelo;
 
     @Column(nullable = false)
     private String marca;
 
     private Integer mudanca;
 
     @Column(nullable = false)
     private String estado;
 
     @Column(nullable = false)
     private String atrdisc;
 
     @ManyToOne
     @JoinColumn(name = "dispositivo", referencedColumnName = "noserie", nullable = false)
     private Dispositivo dispositivo;

     // Default constructor
     public Bicicleta() {
     }

     // Parameterized constructor
     public Bicicleta(BigDecimal peso, Integer raio, String modelo, String marca, Integer mudanca, String estado, String atrdisc, Dispositivo dispositivo) {
         this.peso = peso;
         this.raio = raio;
         this.modelo = modelo;
         this.marca = marca;
         this.mudanca = mudanca;
         this.estado = estado;
         this.atrdisc = atrdisc;
         this.dispositivo = dispositivo;
     }

     public long getId() {
         return id;
     }

     public void setId(long id) {
         this.id = id;
     }

     public BigDecimal getPeso() {
         return peso;
     }

     public void setPeso(BigDecimal peso) {
         this.peso = peso;
     }

     public Integer getRaio() {
         return raio;
     }

     public void setRaio(Integer raio) {
         this.raio = raio;
     }

     public String getModelo() {
         return modelo;
     }

     public void setModelo(String modelo) {
         this.modelo = modelo;
     }

     public String getMarca() {
         return marca;
     }

     public void setMarca(String marca) {
         this.marca = marca;
     }

     public Integer getMudanca() {
         return mudanca;
     }

     public void setMudanca(Integer mudanca) {
         this.mudanca = mudanca;
     }

     public String getEstado() {
         return estado;
     }

     public void setEstado(String estado) {
         this.estado = estado;
     }

     public String getAtrdisc() {
         return atrdisc;
     }

     public void setAtrdisc(String atrdisc) {
         this.atrdisc = atrdisc;
     }

     public Dispositivo getDispositivo() {
         return dispositivo;
     }

     public void setDispositivo(Dispositivo dispositivo) {
         this.dispositivo = dispositivo;
     }

     public long getNoserie() {
         return dispositivo != null ? dispositivo.getNoserie() : 0;
     }
 }