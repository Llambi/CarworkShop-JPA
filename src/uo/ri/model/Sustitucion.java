package uo.ri.model;

public class Sustitucion {
	
	private Repuesto repuesto;
	private Intervencion intervencion;
	private int cantidad;

	public Sustitucion(Repuesto repuesto, Intervencion intervencion, int cantidad) {
	    if(cantidad<=0){
	        throw new IllegalArgumentException("Las sustituciones deben tener al menos un repuesto.");
        }
		this.cantidad = cantidad;
		Association.Sustituir.link(repuesto, this, intervencion);
	}

    public double getImporte() {
        return repuesto.getPrecio() * cantidad;
    }

    protected void _setRepuesto(Repuesto repuesto) {
        this.repuesto = repuesto;
    }

    protected void _setIntervencion(Intervencion intervencion) {
        this.intervencion = intervencion;
    }

    public Repuesto getRepuesto() {
        return repuesto;
    }

    public Intervencion getIntervencion() {
        return intervencion;
    }


    public int getCantidad() {
        return cantidad;
    }


}
