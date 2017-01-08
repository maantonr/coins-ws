package com.mig.coins.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

// PDTE Documentar
public abstract class AbstractDAOImpl implements IDAO {
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

	public abstract long count() throws SQLException;
	
	protected PreparedStatement getPreparedStatement(final String sqlSelect) throws SQLException {
		return connection.prepareStatement(sqlSelect);
	}
	
	protected PreparedStatement getPreparedStatement(final String sqlSelect, List<Object> lArgs) throws SQLException {
		final PreparedStatement stmt = connection.prepareStatement(sqlSelect);
		
		//
		if ((null != lArgs) && (!lArgs.isEmpty())) {
			int pos = 0;
			for (Object arg : lArgs) {
				pos++;
				if (arg instanceof String) {
					stmt.setString(pos, (String)arg);
				} else if (arg instanceof Integer) {
					stmt.setInt(pos, (Integer)arg);
				} else if (arg instanceof Long) {
					stmt.setLong(pos, (Long)arg);
				} else if (arg instanceof Double) {
					stmt.setDouble(pos, (Double)arg);
				} else if (arg instanceof java.sql.Date) {
					stmt.setDate(pos, (java.sql.Date)arg);
				} else {
					// PDTE Exception
				}
			}
		}
		
		return stmt;
	}
	
	
}
