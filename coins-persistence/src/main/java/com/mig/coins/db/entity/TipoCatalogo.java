package com.mig.coins.db.entity;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class TipoCatalogo {

	public enum Tipos {
		MONEDA(1),
		CARTERA(2), 
		BOLSA(3),
		ROLLO(4)
		;

		private final Integer value;

		private static final Map<Integer, Tipos> lookup=new HashMap<Integer, Tipos>();
		static
		{
			for (Tipos each : EnumSet.allOf(Tipos.class))
			{
				lookup.put(each.value, each);
			}
		}

		public static Tipos getEnum(Integer value)
		{
			return lookup.get(value);
		}

		private Tipos(Integer value)
		{
			this.value=value;
		}

		public Integer getValue()
		{
			return value;
		}
	}

	private int id = 0;
	private String nombre = null;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TipoCatalogo [id=");
		builder.append(id);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append("]");
		return builder.toString();
	}

	
}
