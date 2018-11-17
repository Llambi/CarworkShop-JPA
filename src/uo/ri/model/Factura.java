package uo.ri.model;

import alb.util.math.Round;
import uo.ri.model.types.AveriaStatus;
import uo.ri.model.types.FacturaStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Factura {

    private Long numero;
    private Date fecha;
    private double importe;
    private double iva;
    private FacturaStatus status = FacturaStatus.SIN_ABONAR;

    //Atributos accidentales
    private Long id;
    private Set<Cargo> cargos = new HashSet<>();
    private Set<Averia> averias = new HashSet<>();

    public Factura() {
    }

    public Factura(Long numero) {
        this.numero = numero;
        this.fecha = new Date();
    }

    public Factura(long numero, Date fecha) {
        this(numero);
        this.fecha = new Date(fecha.getTime());
    }

    public Factura(long numero, List<Averia> averias) {
        this(numero);
        fillAverias(averias);
    }

    public Factura(long numero, Date fecha, List<Averia> averias) {
        this(numero, fecha);
        fillAverias(averias);
        calcularImporte();
    }

    /**
     * Metodo que dada una lista de averias las pasa a nuestro Set comprobando
     * que esten terminadas.
     *
     * @param averias Averias que se quieren añadir.
     * @throws IllegalStateException si alguna de las averias no esta terminada.
     */
    private void fillAverias(List<Averia> averias) {
        for (Averia averia : averias) {
            if (averia.getStatus() != AveriaStatus.TERMINADA) {
                throw new IllegalStateException("Averia no finalizada");
            }
            addAveria(averia);
        }
    }

    /**
     * Añade la averia a la factura y actualiza el importe e iva de la factura
     *
     * @param averia
     * @throws IllegalStateException si la factura no está en estado SIN_ABONAR
     * @see Diagramas de estados en el enunciado de referencia
     */
    public void addAveria(Averia averia) {
        if (getStatus() == FacturaStatus.SIN_ABONAR &&
                averia.getStatus() == AveriaStatus.TERMINADA) {
            Association.Facturar.link(this, averia);
            averia.markAsInvoiced();
            calcularImporte();
        } else {
            throw new IllegalStateException("Averia no terminada");
        }
    }

    /**
     * Calcula el importe de la avería y su IVA, teniendo en cuenta la fecha de
     * factura
     */
    void calcularImporte() {
        importe = 0.0;
        for (Averia averia : averias) {
            importe += averia.getImporte();
        }
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat
                ("yyyy-MM-dd");
        Date fechaAux = null;
        try {
            fechaAux = formatoDelTexto.parse("2012-07-01");
        } catch (ParseException ex) {
        }
        if (fecha.after(fechaAux)) {
            setIva(0.21);
        } else {
            setIva(0.18);
        }

        importe = importe + (importe * iva);
        importe = Round.twoCents(importe);
    }

    /**
     * Elimina una averia de la factura, solo si está SIN_ABONAR y recalcula
     * el importe
     *
     * @param averia
     * @throws IllegalStateException si la factura no está en estado SIN_ABONAR
     * @see Diagramas de estados en el enunciado de referencia
     */
    public void removeAveria(Averia averia) {
        if (status == FacturaStatus.SIN_ABONAR) {
            averia.markBackToFinished();
            Association.Facturar.unlink(this, averia);
            calcularImporte();
        } else {
            throw new IllegalStateException
                    ("La factura no esta en estado SIN ABONAR.");
        }
    }

    /**
     * Marks the invoice as ABONADA, but
     *
     * @throws IllegalStateException if
     *                               - Is already settled, or
     *                               - the amounts paid with charges to payment
     *                               means does not cover
     *                               the total of the invoice
     * @see Diagramas de estados en el enunciado de referencia
     */
    public void settle() {
        if (isSettled())
            throw new IllegalStateException
                    ("No se puede liquidar una factura sin averias");
        else if (importeCargos() > getImporte() + 0.01)
            throw new IllegalStateException("Los cargos no igualan el importe");
        else if (importeCargos() < getImporte() - 0.01)
            throw new IllegalStateException("Los cargos no igualan el importe");

        this.status = FacturaStatus.ABONADA;
    }

    private double importeCargos() {
        double importeCargos = 0.0;
        for (Cargo cargo : cargos) {
            importeCargos += cargo.getImporte();
        }
        return importeCargos;
    }

    public boolean isSettled() {
        if (getStatus() == FacturaStatus.ABONADA) {
            return true;
        } else {
            return false;
        }
    }

    protected Set<Averia> _getAverias() {
        return averias;
    }

    public Set<Averia> getAverias() {
        return new HashSet<>(averias);
    }

    protected Set<Cargo> _getCargos() {
        return cargos;
    }

    public Set<Cargo> getCargos() {
        return new HashSet<>(cargos);
    }

    public Long getNumero() {
        return numero;
    }

    public Date getFecha() {
        return new Date(fecha.getTime());
    }

    public void setFecha(Date fecha) {
        this.fecha = new Date(fecha.getTime());
    }

    public double getImporte() {
        calcularImporte();
        return importe;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public FacturaStatus getStatus() {
        return status;
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Factura factura = (Factura) o;
        return Objects.equals(numero, factura.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }

    @Override
    public String toString() {
        return "Factura{" +
                "numero=" + numero +
                ", fecha=" + fecha +
                ", importe=" + importe +
                ", iva=" + iva +
                ", status=" + status +
                '}';
    }
}
