package uo.ri.business.impl.contractType.command;

import uo.ri.business.dto.ContractTypeDto;
import uo.ri.business.exception.BusinessCheck;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.EntityAssembler;
import uo.ri.business.repository.ContractTypeRepository;
import uo.ri.conf.Factory;
import uo.ri.model.ContractType;

public class AddContractType implements Command<Void> {
    private ContractTypeDto dto;
    private ContractTypeRepository repo = Factory.repository.forContractType();

    public AddContractType(ContractTypeDto dto) {
        this.dto = dto;
    }

    @Override
    public Void execute() throws BusinessException {
        ContractType c = EntityAssembler.toEntity(this.dto);
        BusinessCheck.isNotNull(c, "El tipo de contrato no existe.");
        repo.add(c);
        return null;
    }
}
