package uo.ri.persistence.jpa;

import uo.ri.business.repository.PayrollRepository;
import uo.ri.model.Payroll;
import uo.ri.persistence.jpa.util.BaseRepository;
import uo.ri.persistence.jpa.util.Jpa;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PayrollJpaRepository extends BaseRepository<Payroll> implements PayrollRepository {
    @Override
    public Date getLastDatePayroll() {
        return Jpa.getManager().createNamedQuery("Nomina.getLastDatePayroll",
                Date.class)
                .getResultStream()
                .reduce((first, second) -> second)
                .orElse(null);
    }

    @Override
    public List<Payroll> findByMechanicId(Long id) {
        return Jpa.getManager().createNamedQuery("Nomina" +
                ".getNominaByMecanicoId", Payroll.class)
                .setParameter(1, id)
                .getResultStream()
                .collect(Collectors.toList());
    }
}
