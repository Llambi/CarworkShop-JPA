package uo.ri.business.impl.mechanic.command;

import uo.ri.business.dto.MechanicDto;
import uo.ri.business.exception.BusinessCheck;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.EntityAssembler;
import uo.ri.business.repository.MecanicoRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Mecanico;

public class AddMechanic implements Command<Void> {

    private MechanicDto dto;
    private MecanicoRepository repo = Factory.repository
	    .forMechanic();

    public AddMechanic(MechanicDto mecanico) {
	this.dto = mecanico;
    }

    public Void execute() throws BusinessException {
	BusinessCheck.isNull(repo.findByDni(this.dto.dni),
		"El mecanico ya existe.");
	Mecanico m = EntityAssembler.toEntity(this.dto);
	repo.add(m);
	return null;
    }

}
