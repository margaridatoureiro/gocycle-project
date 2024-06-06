package isel.sisinf.jpa.ReservaDAL;

import isel.sisinf.jpa.JPAContext;
import isel.sisinf.model.ClienteReserva;
import isel.sisinf.model.Reserva;
import isel.sisinf.model.ReservaId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.OptimisticLockException;
import jakarta.persistence.Query;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public class ReservaRepository implements IReservaRepository {

    @Override
    public Collection<Reserva> getAll() {
        try (JPAContext ctx = new JPAContext()) {
            ctx.beginTransaction();
            EntityManager entityManager = ctx.getEntityManager();
            Collection<Reserva> bookings = entityManager.createNamedQuery("Reserva.getAll", Reserva.class).getResultList();
            ctx.commit();
            return bookings;
        } catch (Exception e) {
            System.err.println("Error listing bookings: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Reserva findByKey(ReservaId key)
    {
        try (JPAContext ctx = new JPAContext()) {
            ctx.beginTransaction();
            EntityManager entityManager = ctx.getEntityManager();
            Reserva reserva = entityManager.find(Reserva.class, key);
            ctx.commit();
            return reserva;
        } catch (Exception e) {
            System.err.println("Error finding booking: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<Reserva> find(String jpql, Object... params) {
        return List.of();
    }

    @Override
    public Reserva save(Reserva entity) {
        return null;
    }

    @Override
    public Reserva create(Reserva entity) {
        try (JPAContext ctx = new JPAContext()) {
            ctx.beginTransaction();
            ctx.helperCreateImpl(entity);
            ctx.commit();
            System.out.println("Booking created successfully!");
        } catch (Exception e) {
            System.err.println("Error creating booking: " + e.getMessage());
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public Reserva update(Reserva entity) {
        return null;
    }

    @Override
    public Reserva delete(Reserva entity) {
        if (entity == null) {
            System.err.println("Error deleting booking: entity is null");
            return null;
        }
        try (JPAContext ctx = new JPAContext()) {
            ctx.beginTransaction();
            EntityManager entityManager = ctx.getEntityManager();

            // Fetching ClienteReserva entries associated with the Reserva
            List<ClienteReserva> clienteReservas = entityManager.createQuery(
                            "SELECT cr FROM ClienteReserva cr WHERE cr.id.reserva = :reserva AND cr.id.loja = :loja", ClienteReserva.class)
                    .setParameter("reserva", entity.getNoreserva())
                    .setParameter("loja", entity.getLoja().getCodigo())
                    .getResultList();

            // Deleting ClienteReserva entries
            for (ClienteReserva cr : clienteReservas) {
                entityManager.remove(cr);
            }

            // Deleting the Reserva
            Reserva managedEntity = entityManager.merge(entity);
            entityManager.remove(managedEntity);

            ctx.commit();
            System.out.println("Booking and related client reservations deleted successfully!");
            return entity;
        } catch (OptimisticLockException e) {
            System.err.println("Error deleting booking: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error deleting booking: " + e.getMessage());
        }
        return null;
    }


    public boolean isBikeAvailableOnDate(Integer id, LocalDateTime date) {
        try (JPAContext ctx = new JPAContext()) {
            ctx.beginTransaction();
            EntityManager entityManager = ctx.getEntityManager();
            Query query = entityManager.createNativeQuery("SELECT is_bike_available_on_date(?::integer, ?::timestamp)");
            query.setParameter(1, id);
            query.setParameter(2, date);
            boolean isAvailable = (boolean) query.getSingleResult();
            ctx.commit();
            return isAvailable;
        } catch (Exception e) {
            System.err.println("Error checking bike availability: " + e.getMessage());
        }
        return false;
    }

    public void forceOptimisticLockingError() {
        ReservaId reservaId = new ReservaId(1, 123);

        try (JPAContext ctx1 = new JPAContext()) {
            ctx1.beginTransaction();
            EntityManager entityManager1 = ctx1.getEntityManager();
            Reserva reserva1 = entityManager1.find(Reserva.class, reservaId);
            reserva1.setValor(20.0); // Modify the entity

            // Start second transaction
            try (JPAContext ctx2 = new JPAContext()) {
                ctx2.beginTransaction();
                EntityManager entityManager2 = ctx2.getEntityManager();
                Reserva reserva2 = entityManager2.find(Reserva.class, reservaId);
                reserva2.setValor(10.0); // Modify the entity
                entityManager2.merge(reserva2);
                ctx2.commit(); // Commit second transaction
            } catch (Exception e) {
                System.err.println("Error in second transaction: " + e.getMessage());
            }

            entityManager1.merge(reserva1);
            ctx1.commit(); // Attempt to commit first transaction
        } catch (OptimisticLockException e) {
            System.err.println("OptimisticLockException: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error in first transaction: " + e.getMessage());
        }
    }

    public void provokeOptimisticLockingErrorWhileDeletingReservation() {
        ReservaId reservaId = new ReservaId(1, 123); // Use your actual ReservaId

        EntityManager entityManager1 = null;
        EntityManager entityManager2 = null;

        try {
            // First transaction: Retrieve the reservation
            try (JPAContext ctx1 = new JPAContext()) {
                ctx1.beginTransaction();
                entityManager1 = ctx1.getEntityManager();
                Reserva reserva1 = entityManager1.find(Reserva.class, reservaId);
                ctx1.commit();
            }

            // Second transaction: Modify the reservation
            try (JPAContext ctx2 = new JPAContext()) {
                ctx2.beginTransaction();
                entityManager2 = ctx2.getEntityManager();
                Reserva reserva2 = entityManager2.find(Reserva.class, reservaId);
                reserva2.setValor(13.37); // Modify the entity
                entityManager2.merge(reserva2);
                ctx2.commit();
            }

            // Third transaction: Delete the reservation along with associated ClienteReserva
            try (JPAContext ctx3 = new JPAContext()) {
                ctx3.beginTransaction();
                entityManager1 = ctx3.getEntityManager();
                Reserva reserva3 = entityManager1.find(Reserva.class, reservaId);

                // Modify the reservation again in the second transaction before committing the deletion
                try (JPAContext ctx2 = new JPAContext()) {
                    ctx2.beginTransaction();
                    entityManager2 = ctx2.getEntityManager();
                    Reserva reserva2 = entityManager2.find(Reserva.class, reservaId);
                    reserva2.setValor(6.66); // Modify the entity again to provoke optimistic locking error
                    entityManager2.merge(reserva2);
                    ctx2.commit();
                } catch (Exception e) {
                    System.err.println("Error in modifying reservation in the second transaction: " + e.getMessage());
                }

                ReservaRepository reservaRepository = new ReservaRepository();
                Reserva deletedReserva = reservaRepository.delete(reserva3);
                ctx3.commit();
            }
        } catch (OptimisticLockException e) {
            System.err.println("OptimisticLockException: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (entityManager1 != null && entityManager1.isOpen()) {
                entityManager1.close();
            }
            if (entityManager2 != null && entityManager2.isOpen()) {
                entityManager2.close();
            }
        }
    }




}
