package uo.ri.business.impl.contract.command;

import java.util.List;

import uo.ri.business.dto.ContractDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.DtoAssembler;
import uo.ri.business.repository.ContractRepository;
import uo.ri.conf.Factory;

public class FindContractsByMechanicId
	implements Command<List<ContractDto>> {
    private ContractRepository repo = Factory.repository
	    .forContract();
    private Long id;

    public FindContractsByMechanicId(Long id) {
	this.id = id;
    }

    @Override
    public List<ContractDto> execute() throws BusinessException {
	return DtoAssembler
		.toContractDtoList(repo.findByMechanicId(this.id));
    }
}
