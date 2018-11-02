package uo.ri.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "AVERIA_ID, MECANICO_ID")})
public class Intervencion {

    @ManyToOne
    private Averia averia;
    @ManyToOne
    private Mecanico mecanico;
    private int minutos;

    //Atriburos accidentales
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Transient
    private Set<Sustitucion> sustituciones = new HashSet<>();


    public Intervencion() {
    }

    public Intervencion(Averia averia, Mecanico mecanico) {
        Association.Intervenir.link(averia, this, mecanico);
    }

    public Intervencion(Mecanico mecanico, Averia averia) {
        Association.Intervenir.link(averia, this, mecanico);
    }

    public double getImporte() {
        double importe = (getMinutos() / 60.0) * getAveria().getVehiculo().getTipo().getPrecioHora();
        for (Sustitucion sust : sustituciones) {
            importe += sust.getImporte();
        }
        return importe;
    }

    protected Set<Sustitucion> _getSustituciones() {
        return sustituciones;
    }

    public Set<Sustitucion> getSustituciones() {
        return new HashSet<>(sustituciones);
    }

    public Averia getAveria() {
        return averia;
    }

    protected void _setAveria(Averia averia) {
        this.averia = averia;
    }

    public Mecanico getMecanico() {
        return mecanico;
    }

    protected void _setMecanico(Mecanico mecanico) {
        this.mecanico = mecanico;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Intervencion that = (Intervencion) o;
        return Objects.equals(averia, that.averia) &&
                Objects.equals(mecanico, that.mecanico);
    }

    @Override
    public int hashCode() {
        return Objects.hash(averia, mecanico);
    }

    @Override
    public String toString() {
        return "Intervencion{" +
                "averia=" + averia +
                ", mecanico=" + mecanico +
                ", minutos=" + minutos +
                '}';
    }


}
