package uo.ri.business.impl.contractType;

import uo.ri.business.ContractTypeCrudService;
import uo.ri.business.dto.ContractTypeDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.CommandExecutor;
import uo.ri.business.impl.contractType.command.*;
import uo.ri.conf.Factory;

import java.util.List;

public class ContractTypeCrudServiceImpl implements ContractTypeCrudService {
    private CommandExecutor executor = Factory.executor.forExecutor();

    @Override
    public void addContractType(ContractTypeDto dto)
            throws BusinessException {
        executor.execute(new AddContractType(dto));
    }

    @Override
    public void deleteContractType(Long id) throws BusinessException {
        executor.execute(new DeleteContractType(id));
    }

    @Override
    public void updateContractType(ContractTypeDto dto)
            throws BusinessException {
        executor.execute(new UpdateContractType(dto));
    }

    @Override
    public ContractTypeDto findContractTypeById(Long id)
            throws BusinessException {
        return executor.execute(new FindContractTypeById(id));
    }

    @Override
    public List<ContractTypeDto> findAllContractTypes()
            throws BusinessException {
        return executor.execute(new FindAllContractTypes());
    }
}
