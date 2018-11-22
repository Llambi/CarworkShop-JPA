package uo.ri.business.impl.invoice.command;

import java.util.List;

import uo.ri.business.dto.InvoiceDto;
import uo.ri.business.exception.BusinessCheck;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.DtoAssembler;
import uo.ri.business.repository.AveriaRepository;
import uo.ri.business.repository.FacturaRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Averia;
import uo.ri.model.Factura;

public class CreateInvoiceFor implements Command<InvoiceDto> {

    private List<Long> idsAveria;
    private FacturaRepository facturaRepo = Factory.repository
	    .forFactura();
    private AveriaRepository averiaRepo = Factory.repository
	    .forAveria();

    public CreateInvoiceFor(List<Long> idsAveria) {
	this.idsAveria = idsAveria;
    }

    @Override
    public InvoiceDto execute() throws BusinessException {
	Long number = facturaRepo.getNextInvoiceNumber();
	List<Averia> a = averiaRepo.findByIds(idsAveria);
	checkInvoiceParameters(number, a);
	Factura f = new Factura(number, a);
	facturaRepo.add(f);
	return DtoAssembler.toDto(f);
    }

    private void checkInvoiceParameters(Long number, List<Averia> a)
	    throws BusinessException {
	BusinessCheck.isNotNull(number,
		"El numero de la factura no existe.");
	BusinessCheck.isFalse(a.isEmpty(),
		"Una factura debe terner averias.");
    }

}
