package uo.ri.business.repository;

import uo.ri.model.Payroll;

import java.util.Date;

public interface PayrollRepository extends Repository<Payroll> {
    /**
     * @param dni
     * @return the last date for payrolls
     */
    Date getLastDatePayroll();
}
