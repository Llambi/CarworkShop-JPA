package uo.ri.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Bono extends MedioPago {

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

    public String getCodigo() {
        return codigo;
    }

    public double getDisponible() {
        return disponible;
    }

    public String getDescripcion() {
        return descripcion;
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
