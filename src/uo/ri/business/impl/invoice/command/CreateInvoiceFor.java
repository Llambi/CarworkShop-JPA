package uo.ri.business.impl.invoice.command;

import uo.ri.business.dto.InvoiceDto;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.Command;

import java.util.List;

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
