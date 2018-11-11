package uo.ri.business.impl.contract;

import uo.ri.business.ContractCrudService;
import uo.ri.business.dto.ContractDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.CommandExecutor;
import uo.ri.business.impl.contract.command.AddContract;
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

    }

    @Override
    public void deleteContract(Long id) throws BusinessException {

    }

    @Override
    public void finishContract(Long id, Date endDate) throws BusinessException {

    }

    @Override
    public ContractDto findContractById(Long id) throws BusinessException {
        return null;
    }

    @Override
    public List<ContractDto> findContractsByMechanicId(Long id) throws BusinessException {
        return null;
    }
}
