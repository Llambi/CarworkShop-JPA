package uo.ri.persistence.jpa;

import uo.ri.business.repository.PayrollRepository;
import uo.ri.model.Payroll;
import uo.ri.persistence.jpa.util.BaseRepository;
import uo.ri.persistence.jpa.util.Jpa;

import java.util.Date;

public class PayrollJpaRepository extends BaseRepository<Payroll> implements PayrollRepository {
    @Override
    public Date getLastDatePayroll() {
        return Jpa.getManager().createNamedQuery("Factura.getLastDatePayroll",Date.class)
                .getResultStream()
                .reduce((first,second)->second)
                .orElse(null);
    }
}
