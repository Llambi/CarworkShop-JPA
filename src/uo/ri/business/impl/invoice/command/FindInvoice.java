package uo.ri.business.impl.invoice.command;

import uo.ri.business.dto.InvoiceDto;
import uo.ri.business.exception.BusinessCheck;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.DtoAssembler;
import uo.ri.business.repository.FacturaRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Factura;

public class FindInvoice implements Command<InvoiceDto> {

    private Long numeroInvoiceDto;
    private FacturaRepository repo = Factory.repository.forFactura();

    public FindInvoice(Long numeroInvoiceDto) {
	this.numeroInvoiceDto = numeroInvoiceDto;
    }

    @Override
    public InvoiceDto execute() throws BusinessException {
	Factura f = repo.findByNumber(this.numeroInvoiceDto);
	BusinessCheck.isNotNull(f, "La factura no existe.");
	return DtoAssembler.toDto(f);
    }
}
