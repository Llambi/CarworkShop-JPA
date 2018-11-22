package uo.ri.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ContractType {
    private String name;
    private int compensationDays;

    // Atributos accidentales
    private Long id;
    private Set<Contract> contracts = new HashSet<>();

    public ContractType() {
    }

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

    public void setCompensationDays(int compensationDays) {
	this.compensationDays = compensationDays;
    }

    protected Set<Contract> _getContracts() {
	return contracts;
    }

    public Set<Contract> getContracts() {
	return new HashSet<>(contracts);
    }

    public Long getId() {
	return id;
    }

    @Override
    public String toString() {
	return "ContractType{" + "name='" + name + '\''
		+ ", compensationDays=" + compensationDays + '}';
    }

    @Override
    public boolean equals(Object o) {
	if (this == o)
	    return true;
	if (o == null || getClass() != o.getClass())
	    return false;
	ContractType that = (ContractType) o;
	return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
	return Objects.hash(name);
    }
}
