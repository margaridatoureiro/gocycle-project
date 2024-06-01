package isel.sisinf.jpa.BicicletaDAL;

import isel.sisinf.jpa.InterfaceDAL.IRepository;
import isel.sisinf.model.Bicicleta;

import java.util.Collection;
import java.util.List;

public class BicicletaRepository implements IRepository<Bicicleta, Collection<Bicicleta>, Integer> {
    @Override
    public Collection<Bicicleta> getAll() {
        return List.of();
    }

    @Override
    public Bicicleta findByKey(Integer key) {
        return null;
    }

    @Override
    public Collection<Bicicleta> find(String jpql, Object... params) {
        return List.of();
    }

    @Override
    public Bicicleta save(Bicicleta entity) {
        return null;
    }

    @Override
    public Bicicleta delete(Bicicleta entity) {
        return null;
    }
}
