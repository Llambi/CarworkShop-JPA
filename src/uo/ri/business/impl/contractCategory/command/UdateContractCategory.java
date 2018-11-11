package uo.ri.business.impl.contractCategory.command;

import uo.ri.business.dto.ContractCategoryDto;
import uo.ri.business.exception.BusinessCheck;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.Command;
import uo.ri.business.repository.ContractCategoryRepository;
import uo.ri.conf.Factory;
import uo.ri.model.ContractCategory;

public class UdateContractCategory implements Command<Void> {
    private ContractCategoryDto dto;
    private ContractCategoryRepository repo = Factory.repository.forContractCategory();

    public UdateContractCategory(ContractCategoryDto dto) {
        this.dto = dto;
    }

    @Override
    public Void execute() throws BusinessException {
        ContractCategory c = repo.findById(this.dto.id);
        BusinessCheck.isNotNull(c,"La categoria de contrato no existe.");
        c.setTrienniumSalary(this.dto.trieniumSalary);
        c.setProductivityPlus(this.dto.productivityPlus);
        return null;
    }
}
