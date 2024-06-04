package isel.sisinf.jpa.LojaDAL;

import isel.sisinf.jpa.JPAContext;
import isel.sisinf.model.Loja;
import jakarta.persistence.EntityManager;

import java.util.Collection;
import java.util.List;

public class LojaRepository implements ILojaRepository {
    @Override
    public Loja create(Loja entity) {
        return null;
    }

    @Override
    public Loja update(Loja entity) {
        return null;
    }

    @Override
    public Collection<Loja> getAll() {
        try (JPAContext ctx = new JPAContext()) {
            ctx.beginTransaction();
            EntityManager entityManager = ctx.getEntityManager();
            Collection<Loja> stores = entityManager.createNamedQuery("Loja.getAll", Loja.class).getResultList();
            ctx.commit();
            return stores;
        } catch (Exception e) {
            System.err.println("Error listing stores: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Loja findByKey(Integer key) {
        try (JPAContext ctx = new JPAContext()) {
            ctx.beginTransaction();
            EntityManager entityManager = ctx.getEntityManager();
            Loja store = entityManager
                    .createNamedQuery("Loja.findByKey", Loja.class)
                    .setParameter("key", key)
                    .getSingleResult();
            ctx.commit();
            System.out.println("Store found!");
            return store;
        } catch (Exception e) {
            System.err.println("No store with that key was found: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<Loja> find(String jpql, Object... params) {
        return List.of();
    }

    @Override
    public Loja save(Loja entity) {
        return null;
    }

    @Override
    public Loja delete(Loja entity) {
        return null;
    }
}
