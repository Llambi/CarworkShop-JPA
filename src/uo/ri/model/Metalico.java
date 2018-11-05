package uo.ri.model;

public class Metalico extends MedioPago {

    public Metalico(Cliente cliente) {
        Association.Pagar.link(cliente, this);
    }

    @Override
    public void pagar(double cantidad) {
        acumulado += cantidad;
    }

    @Override
    public String toString() {
        return "Metalico{" +
                "acumulado=" + acumulado +
                '}';
    }
}
