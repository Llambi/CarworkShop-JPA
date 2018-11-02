package uo.ri.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "TBonos")
public class Bono extends MedioPago {
    @Column(unique = true)
    private String codigo;
    private double disponible = 0.0;
    private String descripcion;

    //Atributos accidentales
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Bono() {
    }

    public Bono(String codigo) {
        this.codigo = codigo;
    }

    public Bono(String codigo, double disponible, String descripcion) {

        this(codigo);
        this.disponible = disponible;
        this.descripcion = descripcion;
    }

    public Bono(String codigo, double disponible) {
        this(codigo);
        this.disponible = disponible;
    }

    public String getCodigo() {
        return codigo;
    }

    public double getDisponible() {
        return disponible;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public void pagar(double cantidad) {
        if (cantidad > disponible)
            throw new IllegalStateException("No hay suficiente dinero disponible");
        disponible -= cantidad;
        acumulado += cantidad;

    }

    @Override

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bono bono = (Bono) o;
        return Objects.equals(codigo, bono.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return "Bono{" +
                "codigo='" + codigo + '\'' +
                ", disponible=" + disponible +
                ", descripcion='" + descripcion + '\'' +
                ", acumulado=" + acumulado +
                '}';
    }
}
