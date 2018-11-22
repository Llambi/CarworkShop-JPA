package uo.ri.business.repository;

import java.util.List;

import uo.ri.model.Contract;

public interface ContractRepository extends Repository<Contract> {
    List<Contract> getContractsforPayroll();

    List<Contract> findByMechanicId(Long id);
}
