package uo.ri.business.impl.invoice.command;

import uo.ri.business.dto.InvoiceDto;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.DtoAssembler;
import uo.ri.business.repository.AveriaRepository;
import uo.ri.business.repository.FacturaRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Averia;
import uo.ri.model.Factura;
import uo.ri.model.util.Check;

import java.util.List;

public class CreateInvoiceFor implements Command<InvoiceDto>{

	private List<Long> idsAveria;
	private FacturaRepository facturaRepo = Factory.repository.forFactura();
	private AveriaRepository averiaRepo = Factory.repository.forAveria();

	public CreateInvoiceFor(List<Long> idsAveria) {
		this.idsAveria = idsAveria;
	}

	@Override
	public InvoiceDto execute() {
		Long number = facturaRepo.getNextInvoiceNumber();
		List<Averia> a = averiaRepo.findByIds(idsAveria);
        checkInvoiceParameters(number, a);
        Factura f = new Factura(number, a);
		facturaRepo.add(f);
		return DtoAssembler.toDto(f);
	}

    private void checkInvoiceParameters(Long number, List<Averia> a) {
        Check.isNotNull(number,"El numero de la factura no existe.");
        Check.isFalse(a.isEmpty(),"Una factura debe terner averias.");
    }

}
