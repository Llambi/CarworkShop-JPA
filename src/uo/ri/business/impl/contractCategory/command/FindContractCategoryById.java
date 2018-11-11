package uo.ri.business.impl.contractCategory.command;

import uo.ri.business.dto.ContractCategoryDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.Command;

public class FindContractCategoryById implements Command<ContractCategoryDto> {
    @Override
    public ContractCategoryDto execute() throws BusinessException {
        //TODO: Encontrar una categoria de contrato por id.
        return null;
    }
}
