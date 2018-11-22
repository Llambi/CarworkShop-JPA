package uo.ri.business.impl.invoice.command;

import java.util.List;

import uo.ri.business.dto.PaymentMeanDto;
import uo.ri.business.exception.BusinessCheck;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.DtoAssembler;
import uo.ri.business.repository.FacturaRepository;
import uo.ri.business.repository.MedioPagoRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Factura;
import uo.ri.model.MedioPago;

public class FindPayMethodsForInvoice
	implements Command<List<PaymentMeanDto>> {
    private Long idInvoiceDto;
    private MedioPagoRepository medioPagoRepo = Factory.repository
	    .forMedioPago();
    private FacturaRepository facturaRepository = Factory.repository
	    .forFactura();

    public FindPayMethodsForInvoice(Long idInvoiceDto) {
	this.idInvoiceDto = idInvoiceDto;
    }

    @Override
    public List<PaymentMeanDto> execute() throws BusinessException {
	Factura f = facturaRepository.findById(idInvoiceDto);
	BusinessCheck.isNotNull(f, "No existe la factura.");
	List<MedioPago> mp = medioPagoRepo
		.findPaymentMeansByInvoiceId(f.getId());
	BusinessCheck.isFalse(mp.isEmpty(),
		"No hay medios de pago para la factura.");
	return DtoAssembler.toPaymentMeanDtoList(mp);
    }
}
