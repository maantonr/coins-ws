package com.mig.coins.db.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;

// PDTE Documentar
public interface IDAO {

	// PDTE Otros métodos generales
	public void setConnection(Connection conn);
	public void setLocale(Locale loc);
	
	public long count() throws SQLException;
}
