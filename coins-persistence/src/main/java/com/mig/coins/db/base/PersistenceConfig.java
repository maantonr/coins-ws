package com.mig.coins.db.base;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class PersistenceConfig {

	private static PersistenceConfig instance;

	private boolean dummyMode = false;

	private Map<String, String> mDAOs = new HashMap<String, String>();

	private String driver;
	private String url;
	private String usr;
	private String pwd;
	private int minIdle=5;
	private int maxIdle=20;
	private int maxOpenStatements=180;

	private static String DAO_PAIS = "DAO_PAIS";
	private static String DAO_DIVISA = "DAO_DIVISA";

	private PersistenceConfig() {
		final Configurations configs = new Configurations();
		try {
			final XMLConfiguration config = configs.xml("persistence-config.xml");

			dummyMode = config.getBoolean("dummy");

			// Datasource 
			// PDTE que hacemos en modo Dummy
			driver = config.getString("datasource.driver");
			url = config.getString("datasource.url");
			usr = config.getString("datasource.user");
			pwd = config.getString("datasource.password");
			if (config.containsKey("datasource.minIdle")) {
				minIdle = config.getInt("datasource.minIdle");
			}
			if (config.containsKey("datasource.maxIdle")) {
				maxIdle = config.getInt("datasource.maxIdle");
			}
			if (config.containsKey("datasource.maxOpenStatements")) {
				maxOpenStatements = config.getInt("datasource.maxOpenStatements");
			}

			// DAOs
			if (dummyMode) {
				mDAOs.put(DAO_PAIS, "com.mig.coins.db.dao.dummy.PaisDAODummyImpl");
				mDAOs.put(DAO_DIVISA, "com.mig.coins.db.dao.dummy.DivisaDAODummyImpl");
			} else {
				mDAOs.put(DAO_PAIS, config.getString("daos.pais"));
				mDAOs.put(DAO_DIVISA, config.getString("daos.divisa"));
			}
		} catch (ConfigurationException e) {
			// PDTE Tratamiento de exceptions
			e.printStackTrace();
		}
	}

	public static PersistenceConfig getInstance() {
		if (null == instance) {
			instance = new PersistenceConfig();
		}

		return instance;
	}

	public boolean isDummyMode() {
		return dummyMode;
	}

	public String getDriver() {
		return driver;
	}

	public String getUrl() {
		return url;
	}

	public String getUsr() {
		return usr;
	}

	public String getPwd() {
		return pwd;
	}

	public int getMinIdle() {
		return minIdle;
	}

	public int getMaxIdle() {
		return maxIdle;
	}

	public int getMaxOpenStatements() {
		return maxOpenStatements;
	}
	
	public String getPaisDAOClassName() {
		return mDAOs.get(DAO_PAIS);
	}
	
	public String getDivisaDAOClassName() {
		return mDAOs.get(DAO_DIVISA);
	}

}
