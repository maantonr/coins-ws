package com.mig.coins.db.entity;

// PDTE Documentar
public class Pedido {

	private long id = 0;
	private String descripcion = null;
	private String proveedor = null;
	private java.sql.Date fecha = null;
	private EstadoPedido estado;
	private String observaciones = null;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getProveedor() {
		return proveedor;
	}
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
	public java.sql.Date getFecha() {
		return fecha;
	}
	public void setFecha(java.sql.Date fecha) {
		this.fecha = fecha;
	}
	public EstadoPedido getEstado() {
		return estado;
	}
	public void setEstado(EstadoPedido estado) {
		this.estado = estado;
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
		builder.append("Pedido [id=");
		builder.append(id);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append(", proveedor=");
		builder.append(proveedor);
		builder.append(", fecha=");
		builder.append(fecha);
		builder.append(", estado=");
		builder.append(estado);
		builder.append(", observaciones=");
		builder.append(observaciones);
		builder.append("]");
		return builder.toString();
	}

}
