package uo.ri.business.impl.payroll.command;

import uo.ri.business.dto.PayrollDto;
import uo.ri.business.exception.BusinessCheck;
import uo.ri.business.exception.BusinessException;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.DtoAssembler;
import uo.ri.business.repository.MecanicoRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Contract;
import uo.ri.model.Mecanico;

import java.util.List;
import java.util.stream.Collectors;

public class FindPayrollsByMechanicId implements Command<List<PayrollDto>> {
    private Long id;
    private MecanicoRepository mechanicRepo = Factory.repository.forMechanic();

    public FindPayrollsByMechanicId(Long id) {
        this.id = id;
    }

    @Override
    public List<PayrollDto> execute() throws BusinessException {
        Mecanico m = mechanicRepo.findById(this.id);
        BusinessCheck.isNotNull(m,"El mecanico no existe.");
        return m.getContracts().stream()
                .flatMap((Contract contract) -> contract.getPayrolls().stream())
                .map(DtoAssembler::toDto)
                .collect(Collectors.toList());
    }
}
