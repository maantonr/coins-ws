package com.mig.coins.db.entity;

// PDTE Documentar
public class Coleccion {

	private long id = 0;
	private Propietario propietario = null;
	private Catalogo moneda = null;
	private EstadoMoneda estado = null;
	private Pedido pedido = null;
	private Apunte apunte = null;
	private java.sql.Date fecha = null;
	private int unidPedidas = 0;
	private int unidades = 0;
	private int unidRepetidas = 0;
	private double precioPrev = 0.0;
	private double precioReal = 0.0;
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
	public Catalogo getMoneda() {
		return moneda;
	}
	public void setMoneda(Catalogo moneda) {
		this.moneda = moneda;
	}
	public EstadoMoneda getEstado() {
		return estado;
	}
	public void setEstado(EstadoMoneda estado) {
		this.estado = estado;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Apunte getApunte() {
		return apunte;
	}
	public void setApunte(Apunte apunte) {
		this.apunte = apunte;
	}
	public java.sql.Date getFecha() {
		return fecha;
	}
	public void setFecha(java.sql.Date fecha) {
		this.fecha = fecha;
	}
	public int getUnidPedidas() {
		return unidPedidas;
	}
	public void setUnidPedidas(int unidPedidas) {
		this.unidPedidas = unidPedidas;
	}
	public int getUnidades() {
		return unidades;
	}
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	public int getUnidRepetidas() {
		return unidRepetidas;
	}
	public void setUnidRepetidas(int unidRepetidas) {
		this.unidRepetidas = unidRepetidas;
	}
	public double getPrecioPrev() {
		return precioPrev;
	}
	public void setPrecioPrev(double precioPrev) {
		this.precioPrev = precioPrev;
	}
	public double getPrecioReal() {
		return precioReal;
	}
	public void setPrecioReal(double precioReal) {
		this.precioReal = precioReal;
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
		builder.append("Coleccion [id=");
		builder.append(id);
		builder.append(", propietario=");
		builder.append(propietario);
		builder.append(", moneda=");
		builder.append(moneda);
		builder.append(", estado=");
		builder.append(estado);
		builder.append(", pedido=");
		builder.append(pedido);
		builder.append(", apunte=");
		builder.append(apunte);
		builder.append(", fecha=");
		builder.append(fecha);
		builder.append(", unidPedidas=");
		builder.append(unidPedidas);
		builder.append(", unidades=");
		builder.append(unidades);
		builder.append(", unidRepetidas=");
		builder.append(unidRepetidas);
		builder.append(", precioPrev=");
		builder.append(precioPrev);
		builder.append(", precioReal=");
		builder.append(precioReal);
		builder.append(", observaciones=");
		builder.append(observaciones);
		builder.append("]");
		return builder.toString();
	}

	
}
