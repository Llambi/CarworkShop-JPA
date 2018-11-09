package uo.ri.model;

import uo.ri.model.util.Check;

import java.util.Date;
import java.util.Objects;

public class TarjetaCredito extends MedioPago {

    private String numero;
    private String tipo;
    private Date validez;

    //Atributos accidentales
    private Long id;

    public TarjetaCredito() {
    }

    public TarjetaCredito(String numero) {
        this.numero = numero;
    }

    public TarjetaCredito(String numero, String tipo, Date validez) {

        this(numero);
        this.tipo = tipo;
        this.validez = validez;
    }

    @Override
    public void pagar(double cantidad) {
        if (!isValidNow()) {
            throw new java.lang.IllegalStateException("La tarjeta está caducada");
        } else {
            acumulado += cantidad;
        }
    }

    public boolean isValidNow() {
        if (new Date().getTime() < getValidez().getTime()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método que comprueba si la fecha de caducidad de la tarjeta es válida
     *
     * @throws uo.ri.model.util.exception.IllegalStateException
     */
    public void comprobarFecha() throws uo.ri.model.util.exception.IllegalStateException {
        boolean comprobar = new Date().after(getValidez());
        Check.isFalse(comprobar, "La fecha de validez de la tarjeta ha caducado");
    }

    public String getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo;
    }

    public Date getValidez() {
        return validez;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TarjetaCredito that = (TarjetaCredito) o;
        return Objects.equals(numero, that.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }

    @Override
    public String toString() {
        return "TarjetaCredito{" +
                "numero='" + numero + '\'' +
                ", tipo='" + tipo + '\'' +
                ", validez=" + validez +
                ", acumulado=" + acumulado +
                '}';
    }
}
