package uo.ri.model;

import javax.persistence.*;
import java.util.Objects;

public class Sustitucion {

	private Repuesto repuesto;
	private Intervencion intervencion;
	private int cantidad;

    //Atributos accidentales
    private Long id;

    public Sustitucion() {
    }

    public Sustitucion(Repuesto repuesto, Intervencion intervencion, int cantidad) {
	    if(cantidad<=0){
	        throw new IllegalArgumentException("Las sustituciones deben tener al menos un repuesto.");
        }
		this.cantidad = cantidad;
		Association.Sustituir.link(repuesto, this, intervencion);
	}

    public double getImporte() {
        return repuesto.getPrecio() * cantidad;
    }

    protected void _setRepuesto(Repuesto repuesto) {
        this.repuesto = repuesto;
    }

    protected void _setIntervencion(Intervencion intervencion) {
        this.intervencion = intervencion;
    }

    public Repuesto getRepuesto() {
        return repuesto;
    }

    public Intervencion getIntervencion() {
        return intervencion;
    }

    public int getCantidad() {
        return cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sustitucion that = (Sustitucion) o;
        return Objects.equals(repuesto, that.repuesto) &&
                Objects.equals(intervencion, that.intervencion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(repuesto, intervencion);
    }

    @Override
    public String toString() {
        return "Sustitucion{" +
                "repuesto=" + repuesto +
                ", intervencion=" + intervencion +
                ", cantidad=" + cantidad +
                '}';
    }
}
