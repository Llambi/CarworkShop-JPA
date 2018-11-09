package uo.ri.business.impl.invoice.command;

import java.util.List;

import uo.ri.business.dto.InvoiceDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.Command;

public class CreateInvoiceFor implements Command<InvoiceDto>{

	private List<Long> idsAveria;

	public CreateInvoiceFor(List<Long> idsAveria) {
		this.idsAveria = idsAveria;
	}

	@Override
	public InvoiceDto execute() throws BusinessException {

		return null;
	}

}
