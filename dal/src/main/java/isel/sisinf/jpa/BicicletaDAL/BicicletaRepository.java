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
            System.out.println("Bicicleta created successfully!");
        } catch (Exception e) {
            System.err.println("Error creating bicicleta: " + e.getMessage());
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
            System.err.println("Error listing bikes: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Bicicleta findByKey(Integer key) {
       /* return _em.createNamedQuery("Bicicleta.findByKey", Bicicleta.class)
                .setParameter("key", key)
                .getSingleResult();*/
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
