package isel.sisinf.jpa.PessoaDAL;

import isel.sisinf.jpa.JPAContext;
import isel.sisinf.model.Pessoa;

import java.util.Collection;
import java.util.List;

public class PessoaRepository implements IPessoaRepository {

    public Pessoa create(Pessoa entity) {
        try (JPAContext ctx = new JPAContext()) {
            ctx.beginTransaction();
            ctx.helperCreateImpl(entity);
            ctx.commit();
            System.out.println("Customer created successfully!");
        } catch (Exception e) {
            System.err.println("Error creating customer: " + e.getMessage());
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public Pessoa update(Pessoa entity) {
        return null;
    }

    @Override
    public Collection<Pessoa> getAll() {
        return List.of();
    }

    @Override
    public Pessoa findByKey(Integer key) {
        return null;
    }

    @Override
    public Collection<Pessoa> find(String jpql, Object... params) {
        return List.of();
    }

    @Override
    public Pessoa save(Pessoa entity) {
        return null;
    }

    @Override
    public Pessoa delete(Pessoa entity) {
        return null;
    }
}
