package isel.sisinf.jpa.ReservaDAL;

import isel.sisinf.jpa.JPAContext;
import isel.sisinf.model.Reserva;
import isel.sisinf.model.ReservaId;
import jakarta.persistence.EntityManager;


import java.time.LocalDate;
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
        try (JPAContext ctx = new JPAContext()) {
            ctx.beginTransaction();
            EntityManager entityManager = ctx.getEntityManager();
            Reserva managedEntity = entityManager.merge(entity);
            entityManager.remove(managedEntity);
            ctx.commit();
            System.out.println("Booking deleted successfully!");
            return entity;
        } catch (Exception e) {
            System.err.println("Error deleting booking: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public boolean isBikeAvailableOnDate(Integer id, LocalDate date) {
        try (JPAContext ctx = new JPAContext()) {
            ctx.beginTransaction();
            EntityManager entityManager = ctx.getEntityManager();
            boolean isAvailable = (boolean) entityManager.createNamedQuery("Reserva.isBikeAvailableOnDate")
                    .setParameter("id", id)
                    .setParameter("date", date)
                    .getSingleResult();
            ctx.commit();
            return isAvailable;
        } catch (Exception e) {
            System.err.println("Error checking bike availability: " + e.getMessage());
        }
        return false;
    }
}
