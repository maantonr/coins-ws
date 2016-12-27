package com.mig.coins.db.dao;

import java.sql.Connection;
import java.util.Locale;

// PDTE Documentar
public class AbstractDAOImpl implements IDAO{
	Connection connection;
	Locale locale;
	
	public AbstractDAOImpl() {
	}

	@Override
	public void setConnection(Connection conn) {
		connection = conn;
	}

	@Override
	public void setLocale(Locale loc) {
		locale = loc;
	}

	protected Connection getConnection() {
		return connection;
	}

	protected Locale getLocale() {
		return locale;
	}

	
}
