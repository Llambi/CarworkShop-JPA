package uo.ri.business.impl.contract.command;

import uo.ri.business.exception.BusinessCheck;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.Command;
import uo.ri.business.repository.ContractRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Contract;

public class DeleteContract implements Command<Void> {
    private Long id;
    private ContractRepository repo = Factory.repository.forContract();

    public DeleteContract(Long id) {
        this.id = id;
    }

    @Override
    public Void execute() throws BusinessException {
        Contract c = repo.findById(this.id);
        BusinessCheck.isNotNull(c,"El contrato no existe.");
        //TODO: Comprobaciones extra para el borrado de un contrato.
        repo.remove(c);
        return null;
    }
}
