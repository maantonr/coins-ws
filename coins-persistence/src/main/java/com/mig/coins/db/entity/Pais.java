package com.mig.coins.db.entity;

import java.util.List;

//PDTE Documentar
public class Pais {
		private Integer id = null;
		private String nombre = null;
		private String siglas = null;
		private List<Divisa> lDivisas = null;

		public Pais() {
			
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


		// PDTE ¿Implementar un mecanismo para recuperar las divisas si no están cargadas? Problema: Necesito tener la session (está en un project superior) o la conexion y el locale 
		public List<Divisa> getlDivisas() {
			return lDivisas;
		}


		public void setlDivisas(List<Divisa> lDivisas) {
			this.lDivisas = lDivisas;
		}


		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("PaisVO [id=");
			builder.append(id);
			builder.append(", nombre=");
			builder.append(nombre);
			builder.append(", siglas=");
			builder.append(siglas);
			builder.append(", lDivisas=");
			builder.append(lDivisas);
			builder.append("]");
			return builder.toString();
		}
		
		
}
