package uo.ri.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class TipoVehiculo {

    private String nombre;
    private double precioHora;

    //Atributos accidentales
    private Long id;
    private Set<Vehiculo> vehiculos = new HashSet<>();

    public TipoVehiculo() {
    }

    public TipoVehiculo(String nombre) {
        this.nombre = nombre;
    }

    public TipoVehiculo(String nombre, double precioHora) {

        this(nombre);
        this.setPrecioHora(precioHora);
    }

    protected Set<Vehiculo> _getVehiculos() {
        return vehiculos;
    }

    public Set<Vehiculo> getVehiculos() {
        return new HashSet<>(vehiculos);
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecioHora() {
        return precioHora;
    }

    public void setPrecioHora(double precioHora) {
        this.precioHora = precioHora;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipoVehiculo that = (TipoVehiculo) o;
        return Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    @Override
    public String toString() {
        return "TipoVehiculo{" +
                "nombre='" + nombre + '\'' +
                ", precioHora=" + precioHora +
                '}';
    }
}
