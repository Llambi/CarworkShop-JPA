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
import uo.ri.model.Association;
import uo.ri.model.Contract;
import uo.ri.model.ContractCategory;
import uo.ri.model.ContractType;
import uo.ri.model.Mecanico;

public class AddContract implements Command<Void> {
    private ContractDto dto;
    private ContractRepository contractRepo = Factory.repository
	    .forContract();
    private MecanicoRepository mecanicoRepo = Factory.repository
	    .forMechanic();
    private ContractCategoryRepository categoryRepo = Factory.repository
	    .forContractCategory();
    private ContractTypeRepository typeRepo = Factory.repository
	    .forContractType();

    public AddContract(ContractDto dto) {
	this.dto = dto;
    }

    @Override
    public Void execute() throws BusinessException {
	Mecanico m = mecanicoRepo.findById(this.dto.mechanicId);
	ContractCategory cc = categoryRepo
		.findById(this.dto.categoryId);
	ContractType ct = typeRepo.findById(this.dto.typeId);

	check(m, cc, ct);

	Contract lastContract = m.getActiveContract();
	if (lastContract != null && !lastContract.isFinished()) {
	    lastContract.markAsFinished(
		    Dates.subMonths(Dates.today(), 1));
	}

	Contract c = EntityAssembler.toEntity(this.dto, m);
	Association.Categorize.link(c, cc);
	Association.Typefy.link(c, ct);

	contractRepo.add(c);
	return null;
    }

    private void check(Mecanico m, ContractCategory cc,
	    ContractType ct) throws BusinessException {
	BusinessCheck.isNotNull(m,
		"El mecanico del contrato no existe.");
	BusinessCheck.isNotNull(cc,
		"La categoria del contrato no existe.");
	BusinessCheck.isNotNull(ct,
		"El tipo del contrato no existe.");
	BusinessCheck.isTrue(this.dto.yearBaseSalary > 0,
		"El salario base debe ser mayor que 0");
	if (this.dto.endDate != null)
	    BusinessCheck.isTrue(
		    Dates.isAfter(this.dto.endDate,
			    this.dto.startDate),
		    "La fecha de fin de contrato debe ser posterior a la de inicio.");
    }
}
