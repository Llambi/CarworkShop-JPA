package uo.ri.business.impl.contractCategory.command;

import uo.ri.business.dto.ContractCategoryDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.Command;

import java.util.List;

public class FindAllContractCategories implements Command<List<ContractCategoryDto>> {
    @Override
    public List<ContractCategoryDto> execute() throws BusinessException {
        // TODO: Encontrar todas las categorias de contrato
        return null;
    }
}
