package uo.ri.business.impl.invoice.command;

import uo.ri.business.dto.BreakdownDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.DtoAssembler;
import uo.ri.business.repository.AveriaRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Averia;
import uo.ri.model.util.Check;

import java.util.List;

public class FindRepairsByClient implements Command<List<BreakdownDto>> {
    private String dni;
    private AveriaRepository repo = Factory.repository.forAveria();

    public FindRepairsByClient(String dni) {
        this.dni = dni;
    }

    @Override
    public List<BreakdownDto> execute() throws BusinessException {
        List<Averia> a = repo.findNoFacturadasByDni(this.dni);
        Check.isFalse(a.isEmpty(),
                "No hay averias sin facturar para el cliente.");
        return DtoAssembler.toBreakdownDtoList(a);
    }
}
