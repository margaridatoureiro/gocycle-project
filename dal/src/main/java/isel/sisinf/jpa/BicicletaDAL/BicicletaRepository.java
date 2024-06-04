package isel.sisinf.jpa.BicicletaDAL;

import isel.sisinf.jpa.JPAContext;
import isel.sisinf.model.Bicicleta;
import jakarta.persistence.EntityManager;

import java.util.Collection;

public class BicicletaRepository implements IBicicletaRepository {
    @Override
    public Bicicleta create(Bicicleta entity) {
        try (JPAContext ctx = new JPAContext()) {
            ctx.beginTransaction();
            ctx.helperCreateImpl(entity);
            ctx.commit();
            System.out.println("Bicycle created successfully!");
        } catch (Exception e) {
            System.err.println("Error creating bicycle: " + e.getMessage());
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public Collection<Bicicleta> getAll() {
        try (JPAContext ctx = new JPAContext()) {
            ctx.beginTransaction();
            EntityManager entityManager = ctx.getEntityManager();
            Collection<Bicicleta> bikes = entityManager.createNamedQuery("Bicicleta.getAll", Bicicleta.class).getResultList();
            ctx.commit();
            return bikes;
        } catch (Exception e) {
            System.err.println("Error listing bicycles: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Bicicleta findByKey(Integer key) {
        try (JPAContext ctx = new JPAContext()) {
            ctx.beginTransaction();
            EntityManager entityManager = ctx.getEntityManager();
            Bicicleta bike = entityManager
                    .createNamedQuery("Bicicleta.findByKey", Bicicleta.class)
                    .setParameter("key", key)
                    .getSingleResult();
            ctx.commit();
            System.out.println("Bicycle found!");
            return bike;
        } catch (Exception e) {
            System.err.println("No bicycle with that key was found: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<Bicicleta> find(String jpql, Object... params) {
        //return helperQueryImpl( jpql, params);
        return null;
    }

    @Override
    public Bicicleta update(Bicicleta entity) {
        //return (Bicicleta) helperUpdateImpl(entity);
        return null;
    }

    @Override
    public Bicicleta delete(Bicicleta entity) {
        //return (Bicicleta) helperDeleteteImpl(entity);
        return null;
    }

    @Override
    public Bicicleta save(Bicicleta entity) {
        return null;
    }
}
