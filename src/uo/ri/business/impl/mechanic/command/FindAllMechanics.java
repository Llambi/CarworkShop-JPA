package uo.ri.business.impl.mechanic.command;

import uo.ri.business.dto.MechanicDto;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.DtoAssembler;
import uo.ri.business.repository.MecanicoRepository;
import uo.ri.conf.Factory;

import java.util.List;

public class FindAllMechanics implements Command<List<MechanicDto>> {

    private MecanicoRepository repo = Factory.repository.forMechanic();

    public List<MechanicDto> execute() {
        return DtoAssembler.toMechanicDtoList(repo.findAll());
    }

}
