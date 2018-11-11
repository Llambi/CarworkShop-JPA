package uo.ri.business.impl.payroll.command;

import uo.ri.business.dto.PayrollDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.DtoAssembler;
import uo.ri.business.repository.PayrollRepository;
import uo.ri.conf.Factory;

import java.util.List;

public class FindAllPayrolls implements Command<List<PayrollDto>> {
    private PayrollRepository repo = Factory.repository.forPayroll();

    @Override
    public List<PayrollDto> execute() throws BusinessException {
        return DtoAssembler.toPayrollDtoList(repo.findAll());
    }
}
