package uo.ri.business.repository;

import uo.ri.model.Contract;

import java.util.List;

public interface ContractRepository extends Repository<Contract> {
    List<Contract> getContractsforPayroll();
}
