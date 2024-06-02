package isel.sisinf.jpa.PessoaDAL;

import isel.sisinf.jpa.InterfaceDAL.IDataMapper;
import isel.sisinf.jpa.InterfaceDAL.IRepository;
import isel.sisinf.model.Pessoa;

import java.util.Collection;

public interface IPessoaRepository extends IRepository<Pessoa, Collection<Pessoa>,Integer>, IDataMapper<Pessoa> {

}
