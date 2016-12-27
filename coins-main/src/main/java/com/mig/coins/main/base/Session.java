package com.mig.coins.main.base;

import java.sql.Connection;
import java.util.Locale;

public class Session {

	private final String sessionId;		// Identificador de la sesión. - PDTE solo sirve para mostrar la que se ha eliminado
	private final String ipAddress;		// Dirección IP de la máquina cliente.
	private final Thread currentThread;	// Hilo en que se ejecuta la sesión.
	private java.sql.Time currentTime;	// Última hora en la que se actualiza la sesión.
	private final Locale locale;
	private long owner=0;
	private Connection connection=null;	// Conexión a base de datos
	
	public Session(Locale locale, String ipAddress, Thread currentThread, String sessionId) {
		super();
		this.locale = locale;
		this.ipAddress = ipAddress;
		this.currentThread = currentThread;
		this.sessionId = sessionId;
		currentTime = new java.sql.Time(System.currentTimeMillis());
	}

	public Locale getLocale() {
		return locale;
	}

	public String getCountry() {
		return this.locale.getCountry();
	}

	public String getLanguage() {
		return this.locale.getLanguage();
	}

	public Thread getCurrentThread() {
		return currentThread;
	}

	public java.sql.Time getCurrentTime() {
		return currentTime;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public Connection getConnection() {
		if (this.connection==null){
			// PDTE Exception
//			throw new ConnectionInSessionNotSetException(getCurrentThread());
		}
		return this.connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public String getSessionId() {
		return sessionId;
	}

	public long getOwner() {
		return owner;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Session [sessionId=");
		builder.append(sessionId);
		builder.append(", ipAddress=");
		builder.append(ipAddress);
		builder.append(", currentThread=");
		builder.append(currentThread);
		builder.append(", currentTime=");
		builder.append(currentTime);
		builder.append(", locale=");
		builder.append(locale);
		builder.append(", owner=");
		builder.append(owner);
		builder.append(", connection=");
		builder.append(connection);
		builder.append("]");
		return builder.toString();
	}
}
