package uo.ri.business.impl.payroll.command;

import alb.util.date.Dates;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.Command;
import uo.ri.business.repository.ContractRepository;
import uo.ri.business.repository.PayrollRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Contract;
import uo.ri.model.Intervencion;
import uo.ri.model.Payroll;
import uo.ri.model.types.AveriaStatus;

import static java.lang.Math.toIntExact;

public class GeneratePayrolls implements Command<Integer> {
    private ContractRepository contractRepo = Factory.repository.forContract();
    private PayrollRepository payrollRepo = Factory.repository.forPayroll();

    @Override
    public Integer execute() throws BusinessException {
        return toIntExact(contractRepo.getContractsforPayroll().stream()
                .map(contract -> {
                    Payroll payroll = new Payroll(contract, Dates.today(), calculateProductivityTime(contract));
                    payrollRepo.add(payroll);
                    return payroll;
                }).count());

    }

    private double calculateProductivityTime(Contract contract) {
        return contract.getMechanic().getIntervenciones().stream()
                .filter(inter -> Dates.isSameMonth(inter.getAveria().getFecha(), Dates.today())
                        && inter.getAveria().getStatus().equals(AveriaStatus.ABIERTA))
                .mapToDouble(Intervencion::getImporte)
                .sum();
    }
}
