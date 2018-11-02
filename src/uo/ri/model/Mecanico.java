package uo.ri.model;

import alb.util.date.Dates;
import uo.ri.model.types.ContractStatus;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "TMecanicos")
public class Mecanico {
    @Column(unique = true)
    private String dni;
    private String apellidos;
    private String nombre;

    //Atributos accidentales
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "mecanico")
    private Set<Averia> averias = new HashSet<>();
    @OneToMany(mappedBy = "mecanico")
    private Set<Intervencion> intervenciones = new HashSet<>();
    private Set<Contract> contracts = new HashSet<>();

    public Mecanico() {
    }

    public Mecanico(String dni) {
        this.dni = dni;
    }

    public Mecanico(String dni, String nombre, String apellidos) {

        this(dni);
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    protected Set<Contract> _getContracts() {
        return contracts;
    }

    public Set<Contract> getContracts() {
        return new HashSet<>(contracts);
    }

    protected Set<Intervencion> _getIntervenciones() {
        return intervenciones;
    }

    public Set<Intervencion> getIntervenciones() {
        return new HashSet<>(intervenciones);
    }

    protected Set<Averia> _getAsignadas() {
        return averias;
    }

    public Set<Averia> getAsignadas() {
        return new HashSet<>(averias);
    }

    public String getDni() {
        return dni;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mecanico mecanico = (Mecanico) o;
        return Objects.equals(dni, mecanico.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    @Override
    public String toString() {
        return "Mecanico{" +
                "dni='" + dni + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    /**
     * Metodo que devuelve el contrato activo del mecanico con fecha de inicio posterior.
     *
     * @return Contract seleccionado o null si no hay ninguno activo
     */
    public Contract getActiveContract() {
        Contract selectedContract = null;

        for (Contract contract : contracts) {
            if (selectedContract == null && contract.getStatus().equals(ContractStatus.ACTIVE)) {
                selectedContract = contract;
            } else if (contract.getStatus().equals(ContractStatus.ACTIVE) && Dates.isAfter(contract.getStartDate(), selectedContract.getStartDate())) {
                selectedContract = contract;
            }
        }
        return selectedContract;
    }
}
