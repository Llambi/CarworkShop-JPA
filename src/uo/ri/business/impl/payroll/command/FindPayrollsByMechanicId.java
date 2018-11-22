package uo.ri.business.impl.payroll.command;

import java.util.List;

import uo.ri.business.dto.PayrollDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.DtoAssembler;
import uo.ri.business.repository.PayrollRepository;
import uo.ri.conf.Factory;

public class FindPayrollsByMechanicId
	implements Command<List<PayrollDto>> {
    private Long id;
    private PayrollRepository repo = Factory.repository.forPayroll();

    public FindPayrollsByMechanicId(Long id) {
	this.id = id;
    }

    @Override
    public List<PayrollDto> execute() throws BusinessException {
	return DtoAssembler
		.toPayrollDtoList(repo.findByMechanicId(this.id));
    }
}
