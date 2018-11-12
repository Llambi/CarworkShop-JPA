package uo.ri.ui.admin.mechanic.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.MechanicCrudService;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.conf.Factory;

public class UpdateMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {
		
		// Pedir datos
		MechanicDto dto = new MechanicDto();
		dto.id = Console.readLong("Id del mecánico");
		dto.name = Console.readString("Nombre");
		dto.surname = Console.readString("Apellidos");
		
		// Procesar
		MechanicCrudService as = Factory.service.forMechanicCrudService();

		as.updateMechanic(dto);
		
		// Mostrar resultado
		Console.println("Mecánico actualizado");
	}

}
