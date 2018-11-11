package uo.ri.business.impl.contractCategory.command;

import uo.ri.business.dto.ContractCategoryDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.DtoAssembler;
import uo.ri.business.repository.ContractCategoryRepository;
import uo.ri.conf.Factory;

import java.util.List;

public class FindAllContractCategories implements Command<List<ContractCategoryDto>> {
    private ContractCategoryRepository repo = Factory.repository.forContractCategory();
    @Override
    public List<ContractCategoryDto> execute() throws BusinessException {
        return DtoAssembler.toContractCategoryDtoList(repo.findAll());
    }
}
