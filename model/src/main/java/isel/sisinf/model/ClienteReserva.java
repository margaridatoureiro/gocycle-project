package isel.sisinf.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ClienteReserva")
public class ClienteReserva implements Serializable {

    @EmbeddedId
    private ClienteReservaId id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "reserva", referencedColumnName = "noreserva", insertable = false, updatable = false),
            @JoinColumn(name = "loja", referencedColumnName = "loja", insertable = false, updatable = false)
    })
    private Reserva reserva;

    @ManyToOne
    @JoinColumn(name = "cliente", insertable = false, updatable = false)
    private Pessoa cliente;


    @Version
    @Column(name = "version", nullable = false)
    private int version;

    // Getters and setters
    public ClienteReservaId getId() {
        return id;
    }

    public void setId(ClienteReservaId id) {
        this.id = id;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Pessoa getCliente() {
        return cliente;
    }

    public void setCliente(Pessoa cliente) {
        this.cliente = cliente;
    }
}
