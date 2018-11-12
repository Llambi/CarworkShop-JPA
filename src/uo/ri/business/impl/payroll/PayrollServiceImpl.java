package uo.ri.business.impl.payroll;

import uo.ri.business.PayrollService;
import uo.ri.business.dto.PayrollDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.CommandExecutor;
import uo.ri.business.impl.payroll.command.*;
import uo.ri.conf.Factory;

import java.util.List;

public class PayrollServiceImpl implements PayrollService {
    private CommandExecutor executor = Factory.executor.forExecutor();

    @Override
    public List<PayrollDto> findAllPayrolls() throws BusinessException {
        return executor.execute(new FindAllPayrolls());
    }

    @Override
    public List<PayrollDto> findPayrollsByMechanicId(Long id) throws BusinessException {
        return executor.execute(new FindPayrollsByMechanicId(id));
    }

    @Override
    public PayrollDto findPayrollById(Long id) throws BusinessException {
        return executor.execute(new FindPayrollById(id));
    }

    @Override
    public void deleteLastPayrollForMechanicId(Long id) throws BusinessException {
        executor.execute(new DeleteLastPayrollForMechanicId(id));
    }

    @Override
    public int deleteLastGenetaredPayrolls() throws BusinessException {
        return executor.execute(new DeleteLastGenetaredPayrolls());
    }

    @Override
    public int generatePayrolls() throws BusinessException {
        return executor.execute(new GeneratePayrolls());
    }
}
