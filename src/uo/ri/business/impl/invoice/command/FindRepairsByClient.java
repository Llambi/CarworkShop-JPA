package uo.ri.business.impl.invoice.command;

import java.util.List;

import uo.ri.business.dto.BreakdownDto;
import uo.ri.business.exception.BusinessCheck;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.DtoAssembler;
import uo.ri.business.repository.AveriaRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Averia;

public class FindRepairsByClient
	implements Command<List<BreakdownDto>> {
    private String dni;
    private AveriaRepository repo = Factory.repository.forAveria();

    public FindRepairsByClient(String dni) {
	this.dni = dni;
    }

    @Override
    public List<BreakdownDto> execute() throws BusinessException {
	List<Averia> a = repo.findNoFacturadasByDni(this.dni);
	BusinessCheck.isFalse(a.isEmpty(),
		"No hay averias sin facturar para el cliente.");
	return DtoAssembler.toBreakdownDtoList(a);
    }
}
