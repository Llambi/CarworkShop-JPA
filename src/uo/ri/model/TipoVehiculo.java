package uo.ri.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "TTipoVehiculo")
public class TipoVehiculo {

    @Column(unique = true)
    private String nombre;
    private double precioHora;

    //Atributos accidentales
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "tipoVehiculo")
    private Set<Vehiculo> vehiculos = new HashSet<>();

    public TipoVehiculo() {
    }

    public TipoVehiculo(String nombre) {
        this.nombre = nombre;
    }

    public TipoVehiculo(String nombre, double precioHora) {

        this(nombre);
        this.precioHora = precioHora;
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
