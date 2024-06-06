package isel.sisinf.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ClienteReservaId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "cliente")
    private Integer cliente;

    @Column(name = "reserva")
    private Integer reserva;

    @Column(name = "loja")
    private Integer loja;

    // Default constructor
    public ClienteReservaId() {}

    // Parameterized constructor
    public ClienteReservaId(Integer cliente, Integer reserva, Integer loja) {
        this.cliente = cliente;
        this.reserva = reserva;
        this.loja = loja;
    }

    // Getters and setters
    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public Integer getReserva() {
        return reserva;
    }

    public void setReserva(Integer reserva) {
        this.reserva = reserva;
    }

    public Integer getLoja() {
        return loja;
    }

    public void setLoja(Integer loja) {
        this.loja = loja;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteReservaId that = (ClienteReservaId) o;
        return Objects.equals(cliente, that.cliente) &&
                Objects.equals(reserva, that.reserva) &&
                Objects.equals(loja, that.loja);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente, reserva, loja);
    }
}
