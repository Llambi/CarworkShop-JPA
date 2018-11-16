package uo.ri.model;

import uo.ri.model.types.FacturaStatus;

import java.util.Objects;

public class Cargo {

    private Factura factura;
    private MedioPago medioPago;
    private double importe = 0.0;

    //Atributos accidentales
    private Long id;

    public Cargo() {
    }

    public Cargo(Factura factura, MedioPago medioPago) {
        Association.Cargar.link(factura, this, medioPago);
    }

    public Cargo(Factura factura, MedioPago medioPago, double importe) {
        if (medioPago instanceof TarjetaCredito) {
            TarjetaCredito tc = (TarjetaCredito) medioPago;
            if (!tc.isValidNow())
                throw new IllegalStateException("La tarjeta está caducada");
            tc.pagar(importe);
        } else if (medioPago instanceof Bono) {
            Bono b = (Bono) medioPago;
            if (importe > b.getDisponible())
                throw new IllegalStateException
                        ("No hay saldo suficiente en el bono");
            b.pagar(importe);
        } else
            medioPago.acumular(importe);

        this.setImporte(importe);
        Association.Cargar.link(factura, this, medioPago);
    }

    protected void _setFactura(Factura factura) {
        this.factura = factura;
    }

    protected void _setMedioPago(MedioPago medioPago) {
        this.medioPago = medioPago;
    }

    public Factura getFactura() {
        return factura;
    }

    public MedioPago getMedioPago() {
        return medioPago;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cargo cargo = (Cargo) o;
        return Objects.equals(factura, cargo.factura) &&
                Objects.equals(medioPago, cargo.medioPago);
    }

    @Override
    public int hashCode() {
        return Objects.hash(factura, medioPago);
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "factura=" + factura +
                ", medioPago=" + medioPago +
                ", importe=" + importe +
                '}';
    }

    /**
     * Anula (retrocede) este cargo de la factura y el medio de pago
     * Solo se puede hacer si la factura no está abonada
     * Decrementar el acumulado del medio de pago
     * Desenlazar el cargo de la factura y el medio de pago
     *
     * @throws java.lang.IllegalStateException if the invoice is already settled
     */
    public void rewind() {
        if (factura.getStatus() != FacturaStatus.ABONADA) {
            medioPago.acumulado -= importe;
            Association.Cargar.unlink(this);
        }
    }

}
