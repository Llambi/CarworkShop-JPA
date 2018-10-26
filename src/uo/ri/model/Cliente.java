package uo.ri.model;

import uo.ri.model.types.Address;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Cliente {

    private String dni;
    private String nombre;
    private String apellidos;
    private String email;
    private String phone;
    private Address address;

    //Atributos accidentales
    private Set<Vehiculo> vehiculos = new HashSet<>();
    private Set<MedioPago> mediosPago = new HashSet<>();

    public Cliente(String dni) {
        this.dni = dni;
    }

    public Cliente(String dni, String nombre, String apellidos) {

        this(dni);
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    protected Set<MedioPago> _getMediosPago() {
        return mediosPago;
    }

    public Set<MedioPago> getMediosPago() {
        return new HashSet<>(mediosPago);
    }

    public Set<Vehiculo> getVehiculos() {
        return new HashSet<>(vehiculos);
    }

    protected Set<Vehiculo> _getVehiculos() {
        return vehiculos;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(dni, cliente.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address=" + address +
                '}';
    }
}

