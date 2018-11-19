package uo.ri.business.impl.mechanic.command;

import uo.ri.business.dto.MechanicDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.DtoAssembler;
import uo.ri.business.repository.MecanicoRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Mecanico;

import java.util.List;

public class FindActiveMechanics implements Command<List<MechanicDto>> {

    private MecanicoRepository repo = Factory.repository.forMechanic();
    @Override
    public List<MechanicDto> execute() throws BusinessException {
        return DtoAssembler.toMechanicDtoList(repo.findActive());
    }
}
