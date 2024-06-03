package isel.sisinf.jpa.PessoaDAL;

import isel.sisinf.jpa.InterfaceDAL.IDataMapper;
import isel.sisinf.model.Pessoa;
import jakarta.persistence.Query;

import java.util.Collection;
import java.util.List;

public class PessoaRepository implements IPessoaRepository {

    @Override
    public Pessoa create(Pessoa entity) {
        return null;
    }

    @Override
    public Pessoa update(Pessoa entity) {
        return null;
    }

    @Override
    public Collection<Pessoa> getAll() {
        return null;
    }

    @Override
    public Pessoa findByKey(Integer key) {
        return null;
    }

    @Override
    public Collection<Pessoa> find(String jpql, Object... params) {
        return null;
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