package com.mig.coins.db.entity;

// PDTE Documenta
public class Catalogo {

	private long id = 0;
	private TipoCatalogo tipo = null;
	private long pedido = 0;
	private String observaciones = null;
	private Pais pais = null;
	private String ceca = null;
	private int year = 0;
	private String estrella = null;
	private Divisa divisa = null;
	private double facial = 0.0;
	private int ordenAux = 0;
	private String modelo = null;
	private String KM = null;
	private boolean cartera = false;
	private String anverso = null;
	private String reverso = null;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public TipoCatalogo getTipo() {
		return tipo;
	}
	public void setTipo(TipoCatalogo tipo) {
		this.tipo = tipo;
	}
	public long getPedido() {
		return pedido;
	}
	public void setPedido(long pedido) {
		this.pedido = pedido;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	public String getCeca() {
		return ceca;
	}
	public void setCeca(String ceca) {
		this.ceca = ceca;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getEstrella() {
		return estrella;
	}
	public void setEstrella(String estrella) {
		this.estrella = estrella;
	}
	public Divisa getDivisa() {
		return divisa;
	}
	public void setDivisa(Divisa divisa) {
		this.divisa = divisa;
	}
	public double getFacial() {
		return facial;
	}
	public void setFacial(double facial) {
		this.facial = facial;
	}
	public int getOrdenAux() {
		return ordenAux;
	}
	public void setOrdenAux(int ordenAux) {
		this.ordenAux = ordenAux;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getKM() {
		return KM;
	}
	public void setKM(String kM) {
		KM = kM;
	}
	public boolean isCartera() {
		return cartera;
	}
	public void setCartera(boolean cartera) {
		this.cartera = cartera;
	}
	public String getAnverso() {
		return anverso;
	}
	public void setAnverso(String anverso) {
		this.anverso = anverso;
	}
	public String getReverso() {
		return reverso;
	}
	public void setReverso(String reverso) {
		this.reverso = reverso;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Catalogo [id=");
		builder.append(id);
		builder.append(", tipo=");
		builder.append(tipo);
		builder.append(", pedido=");
		builder.append(pedido);
		builder.append(", observaciones=");
		builder.append(observaciones);
		builder.append(", pais=");
		builder.append(pais);
		builder.append(", ceca=");
		builder.append(ceca);
		builder.append(", year=");
		builder.append(year);
		builder.append(", estrella=");
		builder.append(estrella);
		builder.append(", divisa=");
		builder.append(divisa);
		builder.append(", facial=");
		builder.append(facial);
		builder.append(", ordenAux=");
		builder.append(ordenAux);
		builder.append(", modelo=");
		builder.append(modelo);
		builder.append(", KM=");
		builder.append(KM);
		builder.append(", cartera=");
		builder.append(cartera);
		builder.append(", anverso=");
		builder.append(anverso);
		builder.append(", reverso=");
		builder.append(reverso);
		builder.append("]");
		return builder.toString();
	}

	
}
