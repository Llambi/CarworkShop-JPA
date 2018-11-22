package uo.ri.business.repository;

import java.util.Date;
import java.util.List;

import uo.ri.model.Payroll;

public interface PayrollRepository extends Repository<Payroll> {
    /**
     * @param dni
     * @return the last date for payrolls
     */
    Date getLastDatePayroll();

    List<Payroll> findByMechanicId(Long id);
}
