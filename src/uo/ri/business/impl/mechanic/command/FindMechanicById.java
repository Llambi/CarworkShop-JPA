package uo.ri.business.impl.mechanic.command;

import uo.ri.business.dto.MechanicDto;
import uo.ri.business.exception.BusinessException;

public class FindMechanicById {

	private Long id;

	public FindMechanicById(Long id) {
		this.id = id;
	}

	public MechanicDto execute() throws BusinessException {

		return null;
	}

}
