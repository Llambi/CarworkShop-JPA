package uo.ri.business.impl.payroll.command;

import uo.ri.business.dto.PayrollDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.DtoAssembler;
import uo.ri.business.repository.PayrollRepository;
import uo.ri.conf.Factory;

public class FindPayrollById implements Command<PayrollDto> {
    private Long id;
    private PayrollRepository repo = Factory.repository.forPayroll();

    public FindPayrollById(Long id) {
	this.id = id;
    }

    @Override
    public PayrollDto execute() throws BusinessException {
	return DtoAssembler.toDto(repo.findById(this.id));
    }
}
