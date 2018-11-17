package uo.ri.business.impl.mechanic;

import uo.ri.business.MechanicCrudService;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.CommandExecutor;
import uo.ri.business.impl.mechanic.command.*;
import uo.ri.conf.Factory;

import java.util.List;

public class MechanicCrudServiceImpl implements MechanicCrudService {

    private CommandExecutor executor = Factory.executor.forExecutor();

    @Override
    public void addMechanic(MechanicDto mecanico) throws BusinessException {
        executor.execute(new AddMechanic(mecanico));
    }

    @Override
    public void updateMechanic(MechanicDto mecanico) throws BusinessException {
        executor.execute(new UpdateMechanic(mecanico));
    }

    @Override
    public void deleteMechanic(Long idMecanico) throws BusinessException {
        executor.execute(new DeleteMechanic(idMecanico));
    }

    @Override
    public List<MechanicDto> findAllMechanics() throws BusinessException {
        return executor.execute(new FindAllMechanics());
    }

    @Override
    public List<MechanicDto> findActiveMechanics() throws BusinessException {
        return executor.execute(new FindActiveMechanics());
    }

    @Override
    public MechanicDto findMechanicById(Long id) throws BusinessException {
        return executor.execute(new FindMechanicById(id));
    }


}