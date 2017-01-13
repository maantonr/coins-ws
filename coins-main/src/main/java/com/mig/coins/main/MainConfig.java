package com.mig.coins.main;

import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import com.mig.coins.util.config.BaseConfig;

public class MainConfig extends BaseConfig {

	private static MainConfig instance;

	private String rootDataPath;
	private String paisesFilePath;
	private String logPath;

	private MainConfig() {
		final Configurations configs = new Configurations();
		try {
			final XMLConfiguration config = configs.xml("main-config.xml");

			// Paths 
			rootDataPath = parseStringVar(config, "filesystem.root");
			paisesFilePath = rootDataPath + parseStringVar(config, "filesystem.pais");
			logPath = parseStringVar(config, "filesystem.log");
		} catch (ConfigurationException e) {
			// PDTE Tratamiento de exceptions
			e.printStackTrace();
		}
	}

	public static MainConfig getInstance() {
		if (null == instance) {
			instance = new MainConfig();
		}

		return instance;
	}

	public String getRootDataPath() {
		return rootDataPath;
	}

	public String getPaisesFilePath() {
		return paisesFilePath;
	}

	public String getLogPath() {
		return logPath;
	}

}
