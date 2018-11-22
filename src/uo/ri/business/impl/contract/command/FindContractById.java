package uo.ri.business.impl.contract.command;

import uo.ri.business.dto.ContractDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.DtoAssembler;
import uo.ri.business.repository.ContractRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Contract;

public class FindContractById implements Command<ContractDto> {
    private Long id;
    private ContractRepository repo = Factory.repository
	    .forContract();

    public FindContractById(Long id) {
	this.id = id;
    }

    @Override
    public ContractDto execute() throws BusinessException {
	Contract c = repo.findById(this.id);
	return c == null ? null : DtoAssembler.toDto(c);
    }
}
