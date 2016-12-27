package com.mig.coins.server.services.adm;

public class Paises {
		private Integer id = null;
		private String nombre = null;
		private String siglas = null;

		public Paises() {
			
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


		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("PaisVO [id=");
			builder.append(id);
			builder.append(", nombre=");
			builder.append(nombre);
			builder.append(", siglas=");
			builder.append(siglas);
			builder.append("]");
			return builder.toString();
		}
		
		
}
