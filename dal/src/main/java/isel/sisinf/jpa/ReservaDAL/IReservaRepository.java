package isel.sisinf.jpa.ReservaDAL;

import isel.sisinf.jpa.InterfaceDAL.IRepository;
import isel.sisinf.model.Reserva;

import java.util.Collection;

public interface IReservaRepository extends IRepository<Reserva, Collection<Reserva>,Integer>, ReservaDataMapper {
}
