package isel.sisinf.jpa.ClienteReservaDAL;

import isel.sisinf.jpa.InterfaceDAL.IRepository;
import isel.sisinf.model.ClienteReserva;
import isel.sisinf.model.ClienteReservaId;

import java.util.Collection;

public interface IClienteReservaRepository extends IRepository<ClienteReserva, Collection<ClienteReserva>, ClienteReservaId>, IClienteReservaDataMapper {
}
