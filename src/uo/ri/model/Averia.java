package uo.ri.model;

import uo.ri.model.types.AveriaStatus;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Averia {

    private String descripcion;
    private Date fecha;
    private double importe = 0.0;
    private AveriaStatus status = AveriaStatus.ABIERTA;

    //Atributos accidentales
    private Long id;
    private Vehiculo vehiculo;
    private Mecanico mecanico;
    private Factura factura;
    private Set<Intervencion> intervenciones = new HashSet<>();

    public Averia() {
    }

    public Averia(Vehiculo vehiculo) {
        this.fecha = new Date();
        Association.Averiar.link(vehiculo, this);
    }

    public Averia(Vehiculo vehiculo, String descripcion) {
        this(vehiculo);
        this.setDescripcion(descripcion);
    }

    public Factura getFactura() {
        return factura;
    }

    protected void _setFactura(Factura factura) {
        this.factura = factura;
    }

    protected Set<Intervencion> _getIntervenciones() {
        return intervenciones;
    }

    public Set<Intervencion> getIntervenciones() {
        return new HashSet<>(intervenciones);
    }

    public Mecanico getMecanico() {
        return mecanico;
    }

    protected void _setMecanico(Mecanico mecanico) {
        this.mecanico = mecanico;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    protected void _setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Date getFecha() {
        return new Date(fecha.getTime());
    }

    public double getImporte() {
        return importe;
    }

    public AveriaStatus getStatus() {
        return status;
    }

    public Long getId() {
        return this.id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Averia averia = (Averia) o;
        return Objects.equals(vehiculo, averia.vehiculo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fecha, vehiculo);
    }

    @Override
    public String toString() {
        return "Averia{" +
                "descripcion='" + descripcion + '\'' +
                ", fecha=" + fecha +
                ", importe=" + importe +
                ", status=" + status +
                ", vehiculo=" + vehiculo +
                '}';
    }

    /**
     * Asigna la averia al mecanico y esta queda marcada como ASIGNADA
     *
     * @param mecanico
     * @throws IllegalStateException si
     *                               - La avería no está en estado ABIERTA, o
     *                               - La avería ya está enlazada con otro mecánico
     * @see Diagramas de estados en el enunciado de referencia
     */
    public void assignTo(Mecanico mecanico) {
        if (getStatus() != AveriaStatus.ABIERTA) {
            throw new IllegalStateException("La averia no esta abierta.");
        }
        if (getMecanico() != null) {
            throw new IllegalStateException("La averia esta enlazada con otro mecanico.");
        }
        Association.Asignar.link(mecanico, this);
        status = AveriaStatus.ASIGNADA;

    }

    /**
     * El mecánico da por finalizada esta avería, entonces se calcula el
     * importe y pasa a TERMINADA
     *
     * @throws IllegalStateException si
     *                               - La avería no está en estado ASIGNADA, o
     *                               - La avería no está enlazada con un mecánico
     * @see Diagramas de estados en el enunciado de referencia
     */
    public void markAsFinished() {
        if (getStatus() != AveriaStatus.ASIGNADA) {
            throw new IllegalStateException("La averia no esta en estado asignada.");
        }
        if (getMecanico() == null) {
            throw new IllegalStateException("La averia no tiene enlazado un mecanico.");
        }
        importe = 0.0;
        for (Intervencion inter : intervenciones) {
            importe += inter.getImporte();
        }
        Association.Asignar.unlink(getMecanico(), this);
        this.status = AveriaStatus.TERMINADA;

    }

    /**
     * Una averia en estado TERMINADA se puede asignar a otro mecánico
     * (p.e., el primero no ha hecho bien la reparación), pero debe ser pasada
     * a ABIERTA primero
     *
     * @throws IllegalStateException si
     *                               - La avería no está en estado TERMINADA
     * @see Diagramas de estados en el enunciado de referencia
     */
    public void reopen() {
        if (getStatus() != AveriaStatus.TERMINADA) {
            throw new IllegalStateException("La averia no esta en estado terminada.");
        }
        this.status = AveriaStatus.ABIERTA;

    }

    /**
     * Edte método se llama desde la factura al ejecutar factura.removeAveria()
     * Retrocede la averia a TERMINADA
     *
     * @throws IllegalStateException si
     *                               - La averia no está en estado FACTURADA, o
     *                               - La avería aún está enlazada con la factura
     * @see Diagramas de estados en el enunciado de referencia
     */
    public void markBackToFinished() {
        if (getStatus() != AveriaStatus.FACTURADA) {
            throw new IllegalStateException("La averia no esta en estado facturada.");
        }
        if (getFactura() == null) {
            throw new IllegalStateException("La averia no esta enlazada con una factura.");
        }
        this.status = AveriaStatus.TERMINADA;

    }

    /**
     * Edte método se llama desde la factura al ejecutar factura.addAveria()
     * Marca la averia como FACTURADA
     *
     * @throws IllegalStateException si
     *                               - La averia no está en estado TERMINADA, o
     *                               - La avería no está enlazada con una factura
     * @see Diagramas de estados en el enunciado de referencia
     */
    public void markAsInvoiced() {
        if (getStatus() != AveriaStatus.TERMINADA) {
            throw new IllegalStateException("La averia no esta en estado terminada.");
        }
        if (getFactura() == null) {
            throw new IllegalStateException("La averia no esta en estado facturada.");
        }
        this.status = AveriaStatus.FACTURADA;

    }

    /**
     * Desvincula la avería en estado ASIGNADA del mecánico y la pasa a ABIERTA
     *
     * @throws IllegalStateException si
     *                               - La averia no está en estado ASIGNADA, o
     * @see Diagramas de estados en el enunciado de referencia
     */
    public void desassign() {
        if (getStatus() != AveriaStatus.ASIGNADA) {
            throw new IllegalStateException
                    ("La averia no esta en estado asignada.");
        }
        Association.Asignar.unlink(getMecanico(), this);
        this.status = AveriaStatus.ABIERTA;
    }

}
