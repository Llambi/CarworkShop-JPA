package uo.ri.business.impl.invoice.command;

import uo.ri.business.exception.BusinessCheck;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.Command;
import uo.ri.business.repository.FacturaRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Factura;

public class SettleInvoice implements Command<Void> {
    private Long idInvoiceDto;
    private FacturaRepository repo = Factory.repository.forFactura();

    public SettleInvoice(Long idInvoiceDto) {
	this.idInvoiceDto = idInvoiceDto;
    }

    @Override
    public Void execute() throws BusinessException {
	Factura f = repo.findById(this.idInvoiceDto);
	BusinessCheck.isNotNull(f, "La factura no existe.");
	BusinessCheck.isFalse(f.isSettled(),
		"La factura ya esta abonada.");
	f.settle();
	return null;
    }
}
