package uo.ri.business.impl.contract.command;

import alb.util.date.Dates;
import uo.ri.business.dto.ContractDto;
import uo.ri.business.exception.BusinessCheck;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.Command;
import uo.ri.business.repository.ContractRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Contract;

public class UpdateContract implements Command<Void> {
    private ContractDto dto;
    private ContractRepository contractRepo = Factory.repository
	    .forContract();

    public UpdateContract(ContractDto dto) {
	this.dto = dto;
    }

    @Override
    public Void execute() throws BusinessException {
	Contract c = contractRepo.findById(this.dto.id);
	check(c);
	c.setEndDate(this.dto.endDate);
	c.setBaseSalaryPerYear(this.dto.yearBaseSalary);

	return null;
    }

    private void check(Contract c) throws BusinessException {
	BusinessCheck.isNotNull(c, "El contrato no existe.");
	BusinessCheck.isTrue(!c.isFinished(),
		"El contrato no esta activo.");
	BusinessCheck.isTrue(this.dto.yearBaseSalary > 0,
		"El salario base es menor que 0.");
	BusinessCheck.isTrue(
		this.dto.endDate == null || Dates
			.isBefore(this.dto.endDate, c.getStartDate()),
		"La fecha de fin de contrato no es null o "
			+ "no es despues de la fecha de comienzo.");
	if (this.dto.endDate != null)
	    BusinessCheck.isTrue(
		    Dates.isAfter(this.dto.endDate, c.getStartDate()),
		    "La fecha de fin de contrato debe ser posterior a la de inicio.");
    }
}
