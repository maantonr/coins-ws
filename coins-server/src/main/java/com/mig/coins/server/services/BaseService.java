package com.mig.coins.server.services;

import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import com.mig.coins.main.IBaseBusiness;


//PDTE DOC
public class BaseService {

	private XMLConfiguration config;

	// PDTE DOC
	protected BaseService () {
		final Configurations configs = new Configurations();
		try {
			config = configs.xml("server-config.xml");
		} catch (ConfigurationException e) {
			// PDTE Tratamiento de exceptions
			e.printStackTrace();
		}
	}

	// PDTE DOC
	protected IBaseBusiness getServiceImpl(final Class classz) {
		// Recupera la implementación en función del nombre de la clase Service
		final String propertiesName = "apiImpl." + classz.getSimpleName();
		final String className = config.getString(propertiesName);

		// Instancia la clase dinámicamente
		IBaseBusiness businessImpl = null;
		try {
			businessImpl=(IBaseBusiness)Class.forName(className).newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// PDTE Tratamiento de exceptions
			e.printStackTrace();
		}

		return businessImpl;
	}
}
