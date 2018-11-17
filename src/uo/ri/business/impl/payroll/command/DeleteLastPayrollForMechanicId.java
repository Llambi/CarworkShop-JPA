package uo.ri.business.impl.payroll.command;

import uo.ri.business.exception.BusinessCheck;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.Command;
import uo.ri.business.repository.MecanicoRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Contract;
import uo.ri.model.Mecanico;
import uo.ri.model.Payroll;

public class DeleteLastPayrollForMechanicId implements Command<Void> {
    private MecanicoRepository mechanicRepo = Factory.repository.forMechanic();
    private Long id;

    public DeleteLastPayrollForMechanicId(Long id) {
        this.id = id;
    }

    @Override
    public Void execute() throws BusinessException {
        Mecanico m = mechanicRepo.findById(this.id);
        BusinessCheck.isNotNull(m, "El mecanico no existe.");
        Contract c = m.getActiveContract();
        BusinessCheck.isNotNull(c,
                "No hay un contrato activo para el mecanico.");
        Payroll p = c.getLastPayroll();
        BusinessCheck.isNotNull(p,
                "No hay nomina que eliminar para el mecanico.");
        c.getPayrolls().remove(p);
        return null;
    }
}
