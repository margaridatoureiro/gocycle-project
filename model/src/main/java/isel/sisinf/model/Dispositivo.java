package isel.sisinf.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Set;

 @Entity
 public class Dispositivo {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long noserie;
 
     @Column(nullable = false, precision = 6, scale = 4)
     private BigDecimal latitude;
 
     @Column(nullable = false, precision = 6, scale = 4)
     private BigDecimal longitude;
 
     @Column(nullable = false)
     private Integer bateria;

     public long getNoserie() {
         return noserie;
     }

     public void setNoserie(long noserie) {
         this.noserie = noserie;
     }

     public BigDecimal getLatitude() {
         return latitude;
     }

     public void setLatitude(BigDecimal latitude) {
         this.latitude = latitude;
     }

     public BigDecimal getLongitude() {
         return longitude;
     }

     public void setLongitude(BigDecimal longitude) {
         this.longitude = longitude;
     }

     public Integer getBateria() {
         return bateria;
     }

     public void setBateria(Integer bateria) {
         this.bateria = bateria;
     }
 }