package uo.ri.business.impl.mechanic.command;

import uo.ri.business.dto.MechanicDto;
import uo.ri.business.exception.BusinessCheck;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.Command;
import uo.ri.business.repository.MecanicoRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Mecanico;

public class UpdateMechanic implements Command<Void> {

    private MechanicDto dto;
    private MecanicoRepository repo = Factory.repository.forMechanic();

    public UpdateMechanic(MechanicDto dto) {
        this.dto = dto;
    }

    public Void execute() throws BusinessException {
        Mecanico m = repo.findById(this.dto.id);
        check(m);
        m.setApellidos(this.dto.surname);
        m.setNombre(this.dto.name);

        return null;
    }

    private void check(Mecanico m) throws BusinessException {
        BusinessCheck.isNotNull(m, "El mecanico no existe.");
        BusinessCheck.isNotNull(this.dto.name,
                "El nombre no existe.");
        BusinessCheck.isNotNull(this.dto.surname,
                "El apellido no existe.");
    }

}
