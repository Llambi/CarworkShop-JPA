package uo.ri.business.impl;

import uo.ri.business.*;
import uo.ri.business.impl.contract.ContractCrudServiceImpl;
import uo.ri.business.impl.contractCategory.ContractCategoryCrudServiceImpl;
import uo.ri.business.impl.contractType.ContractTypeCrudServiceImpl;
import uo.ri.business.impl.invoice.InvoiceServiceImpl;
import uo.ri.business.impl.mechanic.MechanicCrudServiceImpl;
import uo.ri.business.impl.payroll.PayrollServiceImpl;

public class BusinessFactory implements ServiceFactory {

    @Override
    public MechanicCrudService forMechanicCrudService() {
        return new MechanicCrudServiceImpl();
    }

    @Override
    public ContractCrudService forContractCrud() {
        return new ContractCrudServiceImpl();
    }

    @Override
    public ContractTypeCrudService forContractTypeCrud() {
        return new ContractTypeCrudServiceImpl();
    }

    @Override
    public ContractCategoryCrudService forContractCategoryCrud() {
        return new ContractCategoryCrudServiceImpl();
    }

    @Override
    public PayrollService forPayroll() {
        return new PayrollServiceImpl();
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
