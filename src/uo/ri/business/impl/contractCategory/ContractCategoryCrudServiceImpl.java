package uo.ri.business.impl.contractCategory;

import java.util.List;

import uo.ri.business.ContractCategoryCrudService;
import uo.ri.business.dto.ContractCategoryDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.CommandExecutor;
import uo.ri.business.impl.contractCategory.command.AddContractCategory;
import uo.ri.business.impl.contractCategory.command.DeleteContractCategory;
import uo.ri.business.impl.contractCategory.command.FindAllContractCategories;
import uo.ri.business.impl.contractCategory.command.FindContractCategoryById;
import uo.ri.business.impl.contractCategory.command.UdateContractCategory;
import uo.ri.conf.Factory;

public class ContractCategoryCrudServiceImpl
	implements ContractCategoryCrudService {

    private CommandExecutor executor = Factory.executor.forExecutor();

    @Override
    public void addContractCategory(ContractCategoryDto dto)
	    throws BusinessException {
	executor.execute(new AddContractCategory(dto));
    }

    @Override
    public void deleteContractCategory(Long id)
	    throws BusinessException {
	executor.execute(new DeleteContractCategory(id));
    }

    @Override
    public void updateContractCategory(ContractCategoryDto dto)
	    throws BusinessException {
	executor.execute(new UdateContractCategory(dto));
    }

    @Override
    public ContractCategoryDto findContractCategoryById(Long id)
	    throws BusinessException {
	return executor.execute(new FindContractCategoryById(id));
    }

    @Override
    public List<ContractCategoryDto> findAllContractCategories()
	    throws BusinessException {
	return executor.execute(new FindAllContractCategories());
    }
}
