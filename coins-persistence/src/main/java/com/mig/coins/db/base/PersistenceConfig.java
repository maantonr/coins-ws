package com.mig.coins.db.base;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import com.mig.coins.util.config.BaseConfig;

public class PersistenceConfig extends BaseConfig {

	private static PersistenceConfig instance;

	private boolean dummyMode = false;

	private Map<String, String> mDAOs = new HashMap<String, String>();

	private String driver;
	private String host;
	private String port;
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
			driver = parseStringVar(config, "datasource.driver");
			host = parseStringVar(config, "datasource.host");
			port = parseStringVar(config, "datasource.port");
			url = parseStringVar(config, "datasource.url");
			usr = parseStringVar(config, "datasource.user");
			pwd = parseStringVar(config, "datasource.password");
			minIdle = parseIntVar(config, "datasource.minIdle", "5");
			maxIdle = parseIntVar(config, "datasource.maxIdle", "20");
			maxOpenStatements = parseIntVar(config, "datasource.maxOpenStatements", "180");

			// DAOs
			if (dummyMode) {
				mDAOs.put(DAO_PAIS, "com.mig.coins.db.dao.dummy.PaisDAODummyImpl");
				mDAOs.put(DAO_DIVISA, "com.mig.coins.db.dao.dummy.DivisaDAODummyImpl");
			} else {
				mDAOs.put(DAO_PAIS, config.getString("daos.pais"));
				mDAOs.put(DAO_DIVISA, config.getString("daos.divisa"));
			}
		} catch (ConfigurationException | NumberFormatException e) {
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

	public String getHost() {
		return host;
	}

	public String getPort() {
		return port;
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
