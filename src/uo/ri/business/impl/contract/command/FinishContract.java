package uo.ri.business.impl.contract.command;

import uo.ri.business.exception.BusinessCheck;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.Command;
import uo.ri.business.repository.ContractRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Contract;

import java.util.Date;

public class FinishContract implements Command<Void> {
    private final Long id;
    private final Date endDate;
    private ContractRepository repo = Factory.repository.forContract();


    public FinishContract(Long id, Date endDate) {
        this.id = id;
        this.endDate = endDate;
    }

    @Override
    public Void execute() throws BusinessException {
        Contract c = repo.findById(this.id);
        BusinessCheck.isNotNull(c,"El contrato no existe.");
        BusinessCheck.isNotNull(this.endDate,"Debe haber un fecha de finalizacion.");
        c.markAsFinished(this.endDate);
        return null;
    }
}
