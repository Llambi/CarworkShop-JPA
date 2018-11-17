package uo.ri.persistence.jpa;

import alb.util.date.Dates;
import uo.ri.business.repository.ContractRepository;
import uo.ri.model.Contract;
import uo.ri.model.Mecanico;
import uo.ri.persistence.jpa.util.BaseRepository;
import uo.ri.persistence.jpa.util.Jpa;

import java.util.List;
import java.util.stream.Collectors;

public class ContractJpaRepository extends BaseRepository<Contract>
        implements ContractRepository {
    @Override
    public List<Contract> getContractsforPayroll() {
        return Jpa.getManager().createNamedQuery("Contract" +
                ".getContractsforPayroll", Contract.class)
                .setParameter(1, Dates.lastDayOfMonth(Dates.today()))
                .getResultStream()
                .collect(Collectors.toList());
    }

    @Override
    public List<Contract> findByMechanicId(Long id) {
        return Jpa.getManager().createNamedQuery("Contract" +
                ".getContractsByMechanicId", Contract.class)
                .setParameter(1, id)
                .getResultStream()
                .collect(Collectors.toList());
    }
}
