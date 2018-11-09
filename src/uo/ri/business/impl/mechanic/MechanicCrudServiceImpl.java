package uo.ri.business.impl.mechanic;

import java.util.List;

import uo.ri.business.MechanicCrudService;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.CommandExecutor;
import uo.ri.business.impl.mechanic.command.AddMechanic;
import uo.ri.business.impl.mechanic.command.DeleteMechanic;
import uo.ri.business.impl.mechanic.command.FindAllMechanics;
import uo.ri.business.impl.mechanic.command.FindMechanicById;
import uo.ri.business.impl.mechanic.command.UpdateMechanic;
import uo.ri.conf.Factory;

public class MechanicCrudServiceImpl implements MechanicCrudService {
	
	private CommandExecutor executor = Factory.executor.forExecutor();

	@Override
	public void addMechanic(MechanicDto mecanico) throws BusinessException {
		new AddMechanic( mecanico ).execute();
	}

	@Override
	public void updateMechanic(MechanicDto mecanico) throws BusinessException {
		new UpdateMechanic( mecanico ).execute();
	}

	@Override
	public void deleteMechanic(Long idMecanico) throws BusinessException {
		new DeleteMechanic(idMecanico).execute();
	}

	@Override
	public List<MechanicDto> findAllMechanics() throws BusinessException {
		return new FindAllMechanics().execute();
	}

	@Override
	public MechanicDto findMechanicById(Long id) throws BusinessException {
		return new FindMechanicById(id).execute();
	}


}