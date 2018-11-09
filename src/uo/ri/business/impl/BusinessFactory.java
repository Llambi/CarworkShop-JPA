package uo.ri.business.impl;

import uo.ri.business.*;
import uo.ri.business.impl.invoice.InvoiceServiceImpl;
import uo.ri.business.impl.mechanic.MechanicCrudServiceImpl;

public class BusinessFactory implements ServiceFactory {

	@Override
	public MechanicCrudService forMechanicCrudService() {
		return new MechanicCrudServiceImpl();
	}

	@Override
	public InvoiceService forInvoice() {
		return new InvoiceServiceImpl();
	}

	@Override
	public VehicleReceptionService forVehicleReception() {
		throw new RuntimeException("Not yet implemented");
	}

	@Override
	public CloseBreakdownService forClosingBreakdown() {
		throw new RuntimeException("Not yet implemented");
	}

}
