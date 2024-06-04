package isel.sisinf.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

/* */


@Embeddable
public class ReservaId implements Serializable {

    private Integer noreserva;
    private Integer loja;

    public ReservaId() {}

    public ReservaId(Integer noreserva, Integer loja) {
        this.noreserva = noreserva;
        this.loja = loja;
    }

    public Integer getNoreserva() {
        return noreserva;
    }

    public void setNoreserva(Integer noreserva) {
        this.noreserva = noreserva;
    }

    public Integer getLoja() {
        return loja;
    }

    public void setLoja(Integer loja) {
        this.loja = loja;
    }
}