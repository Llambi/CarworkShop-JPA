package uo.ri.business.impl.contract;

import uo.ri.business.ContractCrudService;
import uo.ri.business.dto.ContractDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.CommandExecutor;
import uo.ri.business.impl.contract.command.*;
import uo.ri.conf.Factory;

import java.util.Date;
import java.util.List;

public class ContractCrudServiceImpl implements ContractCrudService {
    private CommandExecutor executor = Factory.executor.forExecutor();

    @Override
    public void addContract(ContractDto c) throws BusinessException {
        executor.execute(new AddContract(c));
    }

    @Override
    public void updateContract(ContractDto dto) throws BusinessException {
        executor.execute(new UpdateContract(dto));
    }

    @Override
    public void deleteContract(Long id) throws BusinessException {
        executor.execute(new DeleteContract(id));
    }

    @Override
    public void finishContract(Long id, Date endDate) throws BusinessException {
        executor.execute(new FinishContract(id, endDate));
    }

    @Override
    public ContractDto findContractById(Long id) throws BusinessException {
        return executor.execute(new FindContractById(id));
    }

    @Override
    public List<ContractDto> findContractsByMechanicId(Long id) throws BusinessException {
        return executor.execute(new FindContractsByMechanicId(id));
    }
}
