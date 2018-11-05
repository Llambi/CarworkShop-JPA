package uo.ri.model;

public class Metalico extends MedioPago {

    //Atributos accidentales
    private Long id;

    public Metalico() {
    }

    public Metalico(Cliente cliente) {
        Association.Pagar.link(this, cliente);
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
