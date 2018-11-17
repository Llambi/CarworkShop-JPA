package uo.ri.business.impl.payroll.command;

import alb.util.date.Dates;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.Command;
import uo.ri.business.repository.ContractRepository;
import uo.ri.business.repository.PayrollRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Payroll;

import static java.lang.Math.toIntExact;

public class GeneratePayrolls implements Command<Integer> {
    private ContractRepository contractRepo = Factory.repository.forContract();
    private PayrollRepository payrollRepo = Factory.repository.forPayroll();

    @Override
    public Integer execute() throws BusinessException {
        return toIntExact(contractRepo.getContractsforPayroll().stream()
                .map(contract -> {
                    Payroll payroll = new Payroll(contract, Dates.today(),
                            contract.calculateProductivityTime());
                    payrollRepo.add(payroll);
                    return payroll;
                }).count());

    }
}
