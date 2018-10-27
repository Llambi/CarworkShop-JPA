package uo.ri.model;

import java.util.HashSet;
import java.util.Set;

public class ContractType {
    //TODO: hash, equals, toString
    private String name;
    private Double compensationDays;

    //Atributos accidentales
    private Set<Contract> contracts = new HashSet<>();

    public String getName() {
        return name;
    }

    public Double getCompensationDays() {
        return compensationDays;
    }

    protected Set<Contract> _getContracts() {
        return contracts;
    }

    public Set<Contract> getContracts() {
        return new HashSet<>(contracts);
    }
}
