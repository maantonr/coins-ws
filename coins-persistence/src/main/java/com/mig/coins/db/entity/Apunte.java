package com.mig.coins.db.entity;

//PDTE Documentar
public class Apunte {
	private long id = 0;
	private Propietario propietario = null;
	private String descripcion = null;
	private java.sql.Date fecha = null;
	private double importe = 0.0;
	private Pedido pedido = null;
	private boolean prevision = false;
	private String observaciones = null;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Propietario getPropietario() {
		return propietario;
	}
	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public java.sql.Date getFecha() {
		return fecha;
	}
	public void setFecha(java.sql.Date fecha) {
		this.fecha = fecha;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public boolean isPrevision() {
		return prevision;
	}
	public void setPrevision(boolean prevision) {
		this.prevision = prevision;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Apunte [id=");
		builder.append(id);
		builder.append(", propietario=");
		builder.append(propietario);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append(", fecha=");
		builder.append(fecha);
		builder.append(", importe=");
		builder.append(importe);
		builder.append(", pedido=");
		builder.append(pedido);
		builder.append(", prevision=");
		builder.append(prevision);
		builder.append(", observaciones=");
		builder.append(observaciones);
		builder.append("]");
		return builder.toString();
	}

	
}
