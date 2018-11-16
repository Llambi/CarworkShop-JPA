package uo.ri.business.impl.contract.command;

import uo.ri.business.dto.ContractDto;
import uo.ri.business.exception.BusinessCheck;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.Command;
import uo.ri.business.repository.ContractRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Contract;

public class UpdateContract implements Command<Void> {
    private ContractDto dto;
    private ContractRepository contractRepo = Factory.repository.forContract();

    public UpdateContract(ContractDto dto) {
        this.dto = dto;
    }

    @Override
    public Void execute() throws BusinessException {
        Contract c = contractRepo.findById(this.dto.id);
        BusinessCheck.isNotNull(c, "El contrato no existe.");
        //TODO: Ver que se puede actualizar del contrato.

        return null;
    }
}
