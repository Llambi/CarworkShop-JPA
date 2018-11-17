package uo.ri.business.impl.invoice.command;

import uo.ri.business.dto.PaymentMeanDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.DtoAssembler;
import uo.ri.business.repository.FacturaRepository;
import uo.ri.business.repository.MedioPagoRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Factura;
import uo.ri.model.MedioPago;
import uo.ri.model.util.Check;

import java.util.List;

public class FindPayMethodsForInvoice implements Command<List<PaymentMeanDto>> {
    private Long idInvoiceDto;
    private MedioPagoRepository medioPagoRepo =
            Factory.repository.forMedioPago();
    private FacturaRepository facturaRepository =
            Factory.repository.forFactura();

    public FindPayMethodsForInvoice(Long idInvoiceDto) {
        this.idInvoiceDto = idInvoiceDto;
    }

    @Override
    public List<PaymentMeanDto> execute() throws BusinessException {
        Factura f = facturaRepository.findById(idInvoiceDto);
        Check.isNotNull(f, "No existe la factura.");
        List<MedioPago> mp = medioPagoRepo.findPaymentMeansByInvoiceId(f.getId());
        Check.isFalse(mp.isEmpty(),
                "No hay medios de pago para la factura.");
        return DtoAssembler.toPaymentMeanDtoList(mp);
    }
}
