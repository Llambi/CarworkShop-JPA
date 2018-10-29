package uo.ri.model;

import java.util.HashSet;
import java.util.Set;

public class ContractType {
    //TODO: hash, equals, toString
    private String name;
    private int compensationDays;

    //Atributos accidentales
    private Set<Contract> contracts = new HashSet<>();

    public ContractType(String name, int compensationDays) {
        this.name = name;
        this.compensationDays = compensationDays;
    }

    public String getName() {
        return name;
    }

    public int getCompensationDays() {
        return compensationDays;
    }

    protected Set<Contract> _getContracts() {
        return contracts;
    }

    public Set<Contract> getContracts() {
        return new HashSet<>(contracts);
    }
}
