package uo.ri.business.impl.contractCategory.command;

import uo.ri.business.dto.ContractCategoryDto;
import uo.ri.business.exception.BusinessCheck;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.EntityAssembler;
import uo.ri.business.repository.ContractCategoryRepository;
import uo.ri.conf.Factory;
import uo.ri.model.ContractCategory;

public class AddContractCategory implements Command<Void> {
    private ContractCategoryDto dto;
    private ContractCategoryRepository repo = Factory.repository.forContractCategory();

    public AddContractCategory(ContractCategoryDto dto) {
        this.dto = dto;
    }

    @Override
    public Void execute() throws BusinessException {
        ContractCategory c = EntityAssembler.toEntity(this.dto);
        BusinessCheck.isNotNull(c);
        repo.add(c);
        return null;
    }
}
