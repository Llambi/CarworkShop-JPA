package uo.ri.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class MedioPago {

    protected double acumulado = 0.0;

    private Cliente cliente;
    private Set<Cargo> cargos = new HashSet<>();

    //Atributos accidentales
    private Long id;

    public Cliente getCliente() {
        return cliente;
    }

    protected Set<Cargo> _getCargos() {
        return cargos;
    }

    public Set<Cargo> getCargos() {
        return new HashSet<>(cargos);
    }

    protected void _setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getAcumulado() {
        return acumulado;
    }

    /**
     * Método para los tests
     */
    public abstract void pagar(double cantidad);

    public void unlink() {
        Association.Pagar.unlink(cliente, this);

    }

    public void acumular(double importe) {
        this.acumulado += importe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedioPago medioPago = (MedioPago) o;
        return Objects.equals(cliente, medioPago.cliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente);
    }

    @Override
    public String toString() {
        return "MedioPago{" +
                "acumulado=" + acumulado +
                '}';
    }
}
