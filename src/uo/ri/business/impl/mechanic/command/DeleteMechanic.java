package uo.ri.business.impl.mechanic.command;

import uo.ri.business.exception.BusinessCheck;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.Command;
import uo.ri.business.repository.MecanicoRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Mecanico;

public class DeleteMechanic implements Command<Void> {

    private Long idMecanico;
    private MecanicoRepository repo = Factory.repository.forMechanic();

    public DeleteMechanic(Long idMecanico) {
        this.idMecanico = idMecanico;
    }

    public Void execute() throws BusinessException {
        Mecanico m = repo.findById(this.idMecanico);
        check(m);
        repo.remove(m);
        return null;
    }

    private void check(Mecanico m) throws BusinessException {
        BusinessCheck.isNotNull(m, "El mecanico no existe.");
        BusinessCheck.isTrue(m.getIntervenciones().isEmpty(),
                "El mecanico tiene intervenciones asignadas.");
        BusinessCheck.isTrue(m.getContracts().isEmpty(),
                "El mecanico tiene Contratos.");
        BusinessCheck.isTrue(m.getAsignadas().isEmpty(),
                "El mecanico tiene averias asignadas.");
    }

}
