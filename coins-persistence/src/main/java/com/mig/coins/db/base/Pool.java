package com.mig.coins.db.base;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

// PDTE documentar
public class Pool {
	private static Pool     pool;
	
	private PersistenceConfig conf; 
	private BasicDataSource ds;

	private Pool()
	{
		conf = PersistenceConfig.getInstance();
		
		ds = new BasicDataSource();
		ds.setDriverClassName(conf.getDriver());
		ds.setUsername(conf.getUsr());
		ds.setPassword(conf.getPwd());
		ds.setUrl(conf.getUrl());
		ds.setMinIdle(conf.getMinIdle());
		ds.setMaxIdle(conf.getMaxIdle());
		ds.setMaxOpenPreparedStatements(conf.getMaxOpenStatements());
	}

	public static Pool getInstance() {
		if (pool == null) {
			pool = new Pool();
			return pool;
		} else {
			return pool;
		}
	}

	public Connection getConnection() throws SQLException {
		if (conf.isDummyMode()) {
			return new CoinsMockConnection(null);
		} else {
			final Connection conexion = this.ds.getConnection();
			conexion.setAutoCommit(false);
			return conexion;
		}
	}
}