package uo.ri.model;

import java.util.HashSet;
import java.util.Set;

public class ContractCategory {
    //TODO: hash, equals, toString
    private String name;
    private double trienniumSalary;
    private double productivityPlus;

    //Atributos accidentales
    private Set<Contract> contracts = new HashSet<>();

    public ContractCategory(String name) {
        this.name = name;
    }

    public ContractCategory(String name, double trienniumSalary, double productivityPlus) {
        this(name);
        this.trienniumSalary = trienniumSalary;
        this.productivityPlus = productivityPlus;
    }

    protected Set<Contract> _getContracts() {
        return contracts;
    }

    public Set<Contract> getContracts() {
        return new HashSet<>(contracts);
    }

    public String getName() {
        return name;
    }

    public double getTrienniumSalary() {
        return trienniumSalary;
    }

    public double getProductivityPlus() {
        return productivityPlus;
    }
}
