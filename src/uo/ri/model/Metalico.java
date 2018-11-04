package uo.ri.model;

import javax.persistence.*;

public class Metalico extends MedioPago {

    //Atributos accidentales
    private Long id;

    public Metalico() {
    }

    public Metalico(Cliente cliente) {
        Association.Pagar.link(this, cliente);
    }

    public void pagar(double cantidad) {
        acumulado += cantidad;
    }

    public String toString() {
        return "Metalico{" +
                "acumulado=" + acumulado +
                '}';
    }
}
