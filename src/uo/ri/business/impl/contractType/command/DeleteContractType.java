package uo.ri.business.impl.contractType.command;

import uo.ri.business.exception.BusinessCheck;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.Command;
import uo.ri.business.repository.ContractTypeRepository;
import uo.ri.conf.Factory;
import uo.ri.model.ContractType;

public class DeleteContractType implements Command<Void> {
    private Long id;
    private ContractTypeRepository repo = Factory.repository
	    .forContractType();

    public DeleteContractType(Long id) {
	this.id = id;
    }

    @Override
    public Void execute() throws BusinessException {
	ContractType c = repo.findById(this.id);
	check(c);
	repo.remove(c);
	return null;
    }

    private void check(ContractType c) throws BusinessException {
	BusinessCheck.isNotNull(c, "El tipo de contrato no existe.");
	BusinessCheck.isTrue(c.getContracts().isEmpty(),
		"No puede haber contratos del tipo.");
    }
}
