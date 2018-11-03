package uo.ri.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "TTiposContrato")
public class ContractType {
    @Column(unique = true)
    private String name;
    private int compensationDays;

    //Atributos accidentales
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "contractType")
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

    protected Set<Contract> _getContracts() {
        return contracts;
    }

    public Set<Contract> getContracts() {
        return new HashSet<>(contracts);
    }

    @Override
    public String toString() {
        return "ContractType{" +
                "name='" + name + '\'' +
                ", compensationDays=" + compensationDays +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContractType that = (ContractType) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
