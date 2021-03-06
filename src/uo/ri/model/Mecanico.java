package uo.ri.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import alb.util.date.Dates;
import uo.ri.model.types.ContractStatus;

public class Mecanico {
    private String dni;
    private String apellidos;
    private String nombre;

    // Atributos accidentales
    private Long id;
    private Set<Averia> averias = new HashSet<>();
    private Set<Intervencion> intervenciones = new HashSet<>();
    private Set<Contract> contracts = new HashSet<>();

    public Mecanico() {
    }

    public Mecanico(String dni) {
	this.dni = dni;
    }

    public Mecanico(String dni, String nombre, String apellidos) {

	this(dni);
	this.setNombre(nombre);
	this.setApellidos(apellidos);
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

    public void setApellidos(String apellidos) {
	this.apellidos = apellidos;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
	if (this == o)
	    return true;
	if (o == null || getClass() != o.getClass())
	    return false;
	Mecanico mecanico = (Mecanico) o;
	return Objects.equals(dni, mecanico.dni);
    }

    @Override
    public int hashCode() {
	return Objects.hash(dni);
    }

    @Override
    public String toString() {
	return "Mecanico{" + "dni='" + dni + '\'' + ", apellidos='"
		+ apellidos + '\'' + ", nombre='" + nombre + '\''
		+ '}';
    }

    public Long getId() {
	return this.id;
    }

    /**
     * Metodo que devuelve el contrato activo del mecanico con fecha de inicio
     * posterior.
     *
     * @return Contract seleccionado o null si no hay ninguno activo
     */
    public Contract getActiveContract() {
	Contract selectedContract = null;

	for (Contract contract : contracts) {
	    if (selectedContract == null && contract.getStatus()
		    .equals(ContractStatus.ACTIVE)) {
		selectedContract = contract;
	    } else if (contract.getStatus()
		    .equals(ContractStatus.ACTIVE)
		    && Dates.isAfter(contract.getStartDate(),
			    selectedContract.getStartDate())) {
		selectedContract = contract;
	    }
	}
	return selectedContract;
    }

    /**
     * Metodo que dice si un mecanico ha realizado intervenciones entre dos fechas
     * dadas, pudiendo ser la fecha de fin igual a null de modo que se comprobara si
     * ha realizado una intervencion desde la fecha de inicio dada.
     * 
     * @param startDate inicio del periodo en el que se quiere buscar.
     * @param endDate   fin del periodo en el que se quiere buscar
     * @return true si ha realizado alguna intervencion, false si no.
     */
    public boolean isContributionsOnDate(Date startDate,
	    Date endDate) {

	boolean flag;
	if (endDate != null) {
	    flag = getIntervenciones().stream()
		    .anyMatch(intervencion -> Dates
			    .isAfter(intervencion.getAveria()
				    .getFecha(), startDate)
			    && Dates.isBefore(intervencion.getAveria()
				    .getFecha(), endDate));
	} else {
	    flag = getIntervenciones().stream()
		    .anyMatch(intervencion -> Dates.isAfter(
			    intervencion.getAveria().getFecha(),
			    startDate));
	}
	return flag;
    }
}
