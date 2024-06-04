package isel.sisinf.jpa.LojaDAL;

import isel.sisinf.jpa.InterfaceDAL.IRepository;
import isel.sisinf.model.Loja;

import java.util.Collection;

public interface ILojaRepository extends IRepository<Loja, Collection<Loja>,Integer>, ILojaDataMapper {
}
