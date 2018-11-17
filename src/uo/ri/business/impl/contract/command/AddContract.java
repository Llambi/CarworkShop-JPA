package uo.ri.business.impl.contract.command;

import alb.util.date.Dates;
import uo.ri.business.dto.ContractDto;
import uo.ri.business.exception.BusinessCheck;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.EntityAssembler;
import uo.ri.business.repository.ContractCategoryRepository;
import uo.ri.business.repository.ContractRepository;
import uo.ri.business.repository.ContractTypeRepository;
import uo.ri.business.repository.MecanicoRepository;
import uo.ri.conf.Factory;
import uo.ri.model.*;

public class AddContract implements Command<Void> {
    private ContractDto dto;
    private ContractRepository contractRepo = Factory.repository.forContract();
    private MecanicoRepository mecanicoRepo = Factory.repository.forMechanic();
    private ContractCategoryRepository contractCategoryRepo =
            Factory.repository.forContractCategory();
    private ContractTypeRepository contractTypeRepo =
            Factory.repository.forContractType();

    public AddContract(ContractDto dto) {
        this.dto = dto;
    }

    @Override
    public Void execute() throws BusinessException {
        Mecanico m = mecanicoRepo.findByDni(this.dto.dni);
        BusinessCheck.isNotNull(m, "El mecanico del contrato no existe.");

        Contract lastContract = m.getActiveContract();
        if (!lastContract.isFinished()) {
            lastContract.markAsFinished(Dates.subMonths(Dates.today(), 1));
        }

        Contract c = EntityAssembler.toEntity(this.dto, m);
        BusinessCheck.isNotNull(c, "El contrato a añadir no existe.");

        ContractCategory cc = contractCategoryRepo.findById(this.dto.categoryId);
        BusinessCheck.isNotNull(cc, "La categoria del contrato no existe.");
        Association.Categorize.link(c, cc);

        ContractType ct = contractTypeRepo.findById(this.dto.typeId);
        BusinessCheck.isNotNull(ct, "El tipo del contrato no existe.");
        Association.Typefy.link(c, ct);

        contractRepo.add(c);
        return null;
    }
}
