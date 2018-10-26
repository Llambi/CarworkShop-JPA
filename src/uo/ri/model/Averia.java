package uo.ri.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import uo.ri.model.types.AveriaStatus;

public class Averia {

    private String descripcion;
    private Date fecha;
    private double importe = 0.0;
    private AveriaStatus status = AveriaStatus.ABIERTA;

    //Atributos accidentales
    private Vehiculo vehiculo;
    private Mecanico mecanico;

    public Averia(Vehiculo vehiculo) {
        Association.Averiar.link(vehiculo, this);
        this.fecha = new Date();
    }

    public Averia(Vehiculo vehiculo, String descripcion) {
        this(vehiculo);
        this.descripcion = descripcion;
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
        return fecha;
    }

    public double getImporte() {
        return importe;
    }

    public AveriaStatus getStatus() {
        return status;
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
        return Objects.hash(vehiculo);
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
        // Solo se puede asignar una averia que está ABIERTA
        // linkado de averia y mecanico
        // la averia pasa a ASIGNADA
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
        // Se verifica que está en estado TERMINADA
        // Se pasa la averia a ABIERTA
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
    }

    /**
     * Desvincula la avería en estado ASIGNADA del mecánico y la pasa a ABIERTA
     *
     * @throws IllegalStateException si
     *                               - La averia no está en estado ASIGNADA, o
     * @see Diagramas de estados en el enunciado de referencia
     */
    public void desassign() {
    }

}
