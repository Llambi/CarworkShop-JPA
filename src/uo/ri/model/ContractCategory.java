package uo.ri.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ContractCategory {
    private String name;
    private double trieniumSalary;
    private double productivityPlus;

    // Atributos accidentales
    private Long id;
    private Set<Contract> contracts = new HashSet<>();

    public ContractCategory() {
    }

    public ContractCategory(String name) {
	this.name = name;
    }

    public ContractCategory(String name, double trieniumSalary,
	    double productivityPlus) {
	this(name);
	this.setTrieniumSalary(trieniumSalary);
	this.setProductivityPlus(productivityPlus);
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

    public double getTrieniumSalary() {
	return trieniumSalary;
    }

    public void setTrieniumSalary(double trieniumSalary) {
	this.trieniumSalary = trieniumSalary;
    }

    public double getProductivityPlus() {
	return productivityPlus;
    }

    public void setProductivityPlus(double productivityPlus) {
	this.productivityPlus = productivityPlus;
    }

    public Long getId() {
	return id;
    }

    @Override
    public String toString() {
	return "ContractCategory{" + "name='" + name + '\''
		+ ", trieniumSalary=" + trieniumSalary
		+ ", productivityPlus=" + productivityPlus + '}';
    }

    @Override
    public boolean equals(Object o) {
	if (this == o)
	    return true;
	if (o == null || getClass() != o.getClass())
	    return false;
	ContractCategory that = (ContractCategory) o;
	return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
	return Objects.hash(name);
    }
}
