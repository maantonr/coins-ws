package com.mig.coins.db.entity;

// PDTE Documentar
public class Divisa {
	private Integer id = null;
	private String nombre = null;
	private String siglas = null;
	private String fraccion = null;
	private Integer multiplo = 1;

	public Divisa() {
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getSiglas() {
		return siglas;
	}
	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}
	public String getFraccion() {
		return fraccion;
	}
	public void setFraccion(String fraccion) {
		this.fraccion = fraccion;
	}
	public Integer getMultiplo() {
		return multiplo;
	}
	public void setMultiplo(Integer multiplo) {
		this.multiplo = multiplo;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DivisaVO [id=");
		builder.append(id);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", siglas=");
		builder.append(siglas);
		builder.append(", fraccion=");
		builder.append(fraccion);
		builder.append(", multiplo=");
		builder.append(multiplo);
		builder.append("]");
		return builder.toString();
	}
		
}
