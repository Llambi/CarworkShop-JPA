package uo.ri.business.impl.payroll.command;

import java.util.Date;
import java.util.List;

import alb.util.date.Dates;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.Command;
import uo.ri.business.repository.PayrollRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Payroll;

public class DeleteLastGenetaredPayrolls implements Command<Integer> {
    private PayrollRepository payrollRepo = Factory.repository
	    .forPayroll();

    @Override
    public Integer execute() throws BusinessException {
	Date date = payrollRepo.getLastDatePayroll();
	int counter = 0;
	if (date != null) {
	    List<Payroll> payrolls = payrollRepo.findAll();
	    for (Payroll payroll : payrolls) {
		if (Dates.diffDays(payroll.getDate(), date) == 0) {
		    counter++;
		    payrollRepo.remove(payroll);
		}
	    }
	}
	return counter;
    }
}
