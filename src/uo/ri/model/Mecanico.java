package uo.ri.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
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

    protected Set<Intervencion> _getIntervenciones() {
        return intervenciones;
    }

    public Set<Intervencion> getIntervenciones() {
        return new HashSet<>(intervenciones);
    }

    protected Set<Averia> _getAverias() {
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
}
