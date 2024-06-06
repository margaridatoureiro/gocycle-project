package isel.sisinf.jpa.ClienteReservaDAL;

import isel.sisinf.jpa.JPAContext;
import isel.sisinf.model.ClienteReserva;
import isel.sisinf.model.ClienteReservaId;
import isel.sisinf.model.Reserva;

import java.util.Collection;
import java.util.List;

public class ClienteReservaRepository implements IClienteReservaRepository {
    @Override
    public Collection<ClienteReserva> getAll() {
        return List.of();
    }

    @Override
    public ClienteReserva findByKey(ClienteReservaId key) {
        return null;
    }


    @Override
    public Collection<ClienteReserva> find(String jpql, Object... params) {
        return List.of();
    }

    @Override
    public ClienteReserva save(ClienteReserva entity) {
        return null;
    }

    @Override
    public ClienteReserva create(ClienteReserva entity) {
        try (JPAContext ctx = new JPAContext()) {
            ctx.beginTransaction();
            ctx.helperCreateImpl(entity);
            ctx.commit();
            System.out.println("Client Booking created successfully!");
        } catch (Exception e) {
            System.err.println("Error creating client " +
                    "booking: " + e.getMessage());
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public ClienteReserva update(ClienteReserva entity) {
        return null;
    }

    @Override
    public ClienteReserva delete(ClienteReserva entity) {
        return null;
    }
}
