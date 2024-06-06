package isel.sisinf.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@NamedQuery(name="Reserva.findByKey", query="SELECT r FROM Reserva r WHERE r.id = :key")
@NamedQuery(name = "Reserva.getAll", query = "SELECT r FROM Reserva r")
@NamedQuery(
        name = "Reserva.isBikeAvailableOnDate",
        query = "SELECT CASE WHEN COUNT(r) > 0 " +
                "THEN false ELSE true END " +
                "FROM Reserva r WHERE r.bicicleta.id = :id " +
                "AND (:date >= r.dtinicio AND (r.dtfim IS NULL " +
                "OR :date <= r.dtfim))"
)

 public class Reserva implements Serializable {

     @Version
     private int version;

     @EmbeddedId
     private ReservaId id;

     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "noreserva", insertable = false, updatable = false)
     private Integer noreserva;

     @MapsId("loja")
     @ManyToOne
     @JoinColumn(name = "loja", referencedColumnName = "codigo")
     private Loja loja;

     @Column(name = "dtinicio", nullable = false)
     private LocalDateTime dtinicio = LocalDateTime.now();

     @Column(name = "dtfim")
     private LocalDateTime dtfim;

     @Column(name = "valor", nullable = false)
     private Double valor;

     @ManyToOne
     @JoinColumn(name = "bicicleta", nullable = false)
     private Bicicleta bicicleta;

     // Default constructor
     public Reserva() {
     }

     // Parameterized constructor
     public Reserva(Loja loja, LocalDateTime dtinicio, LocalDateTime dtfim, Double valor, Bicicleta bicicleta) {
         this.loja = loja;
         this.dtfim = dtfim;
         this.dtinicio = dtinicio;
         this.valor = valor;
         this.bicicleta = bicicleta;
     }

     public ReservaId getId() {
         return id;
     }

     public void setId(ReservaId id) {
         this.id = id;
     }

     public Integer getNoreserva() {
         return noreserva;
     }

     public void setNoreserva(Integer noreserva) {
         this.noreserva = noreserva;
     }

     public Loja getLoja() {
         return loja;
     }

     public void setLoja(Loja loja) {
         this.loja = loja;
     }

     public LocalDateTime getDtinicio() {
         return dtinicio;
     }

     public void setDtinicio(LocalDateTime dtinicio) {
         this.dtinicio = dtinicio;
     }

     public LocalDateTime getDtfim() {
         return dtfim;
     }

     public void setDtfim(LocalDateTime dtfim) {
         this.dtfim = dtfim;
     }

     public Double getValor() {
         return valor;
     }

     public void setValor(Double valor) {
         this.valor = valor;
     }

     public Bicicleta getBicicleta() {
         return bicicleta;
     }

     public void setBicicleta(Bicicleta bicicleta) {
         this.bicicleta = bicicleta;
     }
 }