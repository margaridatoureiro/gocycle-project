package isel.sisinf.jpa.BicicletaDAL;

import isel.sisinf.jpa.InterfaceDAL.IDataMapper;
import isel.sisinf.jpa.InterfaceDAL.IRepository;
import isel.sisinf.model.Bicicleta;

import java.util.Collection;

public interface IBicicletaRepository extends IRepository<Bicicleta, Collection<Bicicleta>,Integer>, IDataMapper<Bicicleta> {
}
