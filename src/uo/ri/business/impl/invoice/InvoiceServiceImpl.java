package uo.ri.business.impl.invoice;

import uo.ri.business.InvoiceService;
import uo.ri.business.dto.BreakdownDto;
import uo.ri.business.dto.InvoiceDto;
import uo.ri.business.dto.PaymentMeanDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.CommandExecutor;
import uo.ri.business.impl.invoice.command.*;
import uo.ri.conf.Factory;

import java.util.List;
import java.util.Map;

public class InvoiceServiceImpl implements InvoiceService {

    private CommandExecutor executor = Factory.executor.forExecutor();

    @Override
    public InvoiceDto createInvoiceFor(List<Long> idsAveria)
            throws BusinessException {
        return executor.execute(new CreateInvoiceFor(idsAveria));
    }

    @Override
    public InvoiceDto findInvoice(Long numeroInvoiceDto) throws BusinessException {
        return executor.execute(new FindInvoice(numeroInvoiceDto));
    }

    @Override
    public List<PaymentMeanDto> findPayMethodsForInvoice(Long idInvoiceDto)
            throws BusinessException {
        return executor.execute(new FindPayMethodsForInvoice(idInvoiceDto));
    }

    @Override
    public void settleInvoice(Long idInvoiceDto, Map<Long, Double> cargos)
            throws BusinessException {
        executor.execute(new SettleInvoice(idInvoiceDto));
    }

    @Override
    public List<BreakdownDto> findRepairsByClient(String dni) throws BusinessException {
        return executor.execute(new FindRepairsByClient(dni));
    }

}
