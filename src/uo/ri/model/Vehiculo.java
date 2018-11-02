package uo.ri.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.PrimitiveIterator;
import java.util.Set;

@Entity
@Table(name = "TVehiculos")
public class Vehiculo {

    private String marca;
    @Column(unique = true)
    private String matricula;
    private String modelo;
    @Column(name = "num_averia")
    private int numAverias = 0;

    //Atributos accidentales
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Cliente cliente;
    @Transient
    private TipoVehiculo tipoVehiculo;
    @Transient
    private Set<Averia> averias = new HashSet<>();

    public Vehiculo() {
    }

    public Vehiculo(String matricula) {
        this.matricula = matricula;
    }

    public Vehiculo(String matricula, String marca, String modelo) {

        this(matricula);
        this.marca = marca;
        this.modelo = modelo;
    }

    protected Set<Averia> _getAverias() {
        return averias;
    }

    public Set<Averia> getAverias() {
        return new HashSet<>(averias);
    }

    public TipoVehiculo getTipo() {
        return tipoVehiculo;
    }

    protected void _setTipo(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    protected void _setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getMarca() {
        return marca;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public int getNumAverias() {
        this.numAverias = averias.size();
        return numAverias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehiculo vehiculo = (Vehiculo) o;
        return Objects.equals(matricula, vehiculo.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "marca='" + marca + '\'' +
                ", matricula='" + matricula + '\'' +
                ", modelo='" + modelo + '\'' +
                ", numAverias=" + numAverias +
                '}';
    }
}
