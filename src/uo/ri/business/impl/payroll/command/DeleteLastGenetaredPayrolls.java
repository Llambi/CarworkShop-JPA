package uo.ri.business.impl.payroll.command;

import alb.util.date.Dates;
import uo.ri.business.exception.BusinessCheck;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.Command;
import uo.ri.business.repository.PayrollRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Payroll;

import java.util.Date;
import java.util.List;

public class DeleteLastGenetaredPayrolls implements Command<Integer> {
    private PayrollRepository payrollRepo = Factory.repository.forPayroll();

    @Override
    public Integer execute() throws BusinessException {
        Date date = payrollRepo.getLastDatePayroll();
        BusinessCheck.isNotNull(date,"No hay una ultima fecha de nominas");
        int counter=0;
        List<Payroll> payrolls = payrollRepo.findAll();
        for (Payroll payroll : payrolls){
            if(Dates.diffDays(payroll.getDate(),date)==0) {
                counter++;
                payrolls.remove(payroll);
            }
        }
        return counter;
    }
}
