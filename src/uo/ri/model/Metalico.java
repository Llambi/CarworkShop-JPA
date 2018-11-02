package uo.ri.model;

import javax.persistence.*;

@Entity
public class Metalico extends MedioPago {

    //Atributos accidentales
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Metalico() {
    }

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
