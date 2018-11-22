package uo.ri.business.impl.contractType.command;

import uo.ri.business.dto.ContractTypeDto;
import uo.ri.business.exception.BusinessCheck;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.Command;
import uo.ri.business.repository.ContractTypeRepository;
import uo.ri.conf.Factory;
import uo.ri.model.ContractType;

public class UpdateContractType implements Command<Void> {
    private ContractTypeDto dto;
    private ContractTypeRepository repo = Factory.repository
	    .forContractType();

    public UpdateContractType(ContractTypeDto dto) {
	this.dto = dto;
    }

    @Override
    public Void execute() throws BusinessException {
	ContractType c = repo.findById(this.dto.id);
	check(c);
	c.setCompensationDays(this.dto.compensationDays);
	return null;
    }

    private void check(ContractType c) throws BusinessException {
	BusinessCheck.isNotNull(c, "El tipo de contrato no existe.");
	BusinessCheck.isTrue(this.dto.compensationDays > 0,
		"El tipo de contrato tiene una compensacion por dias"
			+ " menor que 0.");
    }
}
