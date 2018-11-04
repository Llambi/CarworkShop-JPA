package uo.ri.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Repuesto {

    private String codigo;
    private String descripcion;
    private double precio;

    //Atributos accidentales
    private Long id;
    private Set<Sustitucion> sustituciones = new HashSet<>();

    public Repuesto() {
    }

    public Repuesto(String codigo) {
        this.codigo = codigo;
    }

    public Repuesto(String codigo, String descripcion, double precio) {
        this(codigo);
        this.descripcion = descripcion;
        this.precio = precio;
    }

    protected Set<Sustitucion> _getSustituciones() {
        return sustituciones;
    }

    public Set<Sustitucion> getSustituciones() {
        return new HashSet<>(sustituciones);
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repuesto repuesto = (Repuesto) o;
        return Objects.equals(codigo, repuesto.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return "Repuesto{" +
                "codigo='" + codigo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                '}';
    }
}
