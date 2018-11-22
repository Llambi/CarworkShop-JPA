package uo.ri.business.impl.contractCategory.command;

import uo.ri.business.exception.BusinessCheck;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.Command;
import uo.ri.business.repository.ContractCategoryRepository;
import uo.ri.conf.Factory;
import uo.ri.model.ContractCategory;

public class DeleteContractCategory implements Command<Void> {
    private Long id;
    private ContractCategoryRepository repo = Factory.repository
	    .forContractCategory();

    public DeleteContractCategory(Long id) {
	this.id = id;
    }

    @Override
    public Void execute() throws BusinessException {
	ContractCategory c = repo.findById(this.id);
	check(c);
	repo.remove(c);
	return null;
    }

    private void check(ContractCategory c) throws BusinessException {
	BusinessCheck.isNotNull(c,
		"La categoria de contrato no existe.");
	BusinessCheck.isTrue(c.getContracts().isEmpty(),
		"No puede haber contratos de la categoria.");
    }
}
