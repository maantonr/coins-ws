package com.mig.coins.db.entity;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

// PDTE Documentar
public class EstadoMoneda {

	public enum Estados {
		DESCONOCIDO(1), 
		RESERVADA(2),
		NO_EXISTE(3), // Para que lo necesito
		COMPRANDOSE(4),
		FALTA(5),
		SOLO_CARTERA(6),
		PAGADA(7),
		OK(8);

		private final Integer value;

		private static final Map<Integer, Estados> lookup=new HashMap<Integer, Estados>();
		static
		{
			for (Estados each : EnumSet.allOf(Estados.class))
			{
				lookup.put(each.value, each);
			}
		}

		public static Estados getEnum(Integer value)
		{
			return lookup.get(value);
		}

		private Estados(Integer value)
		{
			this.value=value;
		}

		public Integer getValue()
		{
			return value;
		}
	}

	private int id;
	private String estado;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EstadoMoneda [id=");
		builder.append(id);
		builder.append(", estado=");
		builder.append(estado);
		builder.append("]");
		return builder.toString();
	}
}
