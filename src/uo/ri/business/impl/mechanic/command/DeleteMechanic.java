package uo.ri.business.impl.mechanic.command;

import uo.ri.business.exception.BusinessException;

public class DeleteMechanic {

	private Long idMecanico;

	public DeleteMechanic(Long idMecanico) {
		this.idMecanico = idMecanico;
	}

	public Void execute() throws BusinessException {

		return null;
	}

}
