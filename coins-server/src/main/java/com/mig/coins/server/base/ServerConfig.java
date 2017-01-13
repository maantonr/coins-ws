package com.mig.coins.server.base;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import com.mig.coins.util.config.BaseConfig;

/**
 * Clase Singleton para acceder al fichero de configuración de la capa servidora
 * 
 * @author maanton
 *
 */
public class ServerConfig extends BaseConfig {

	private static ServerConfig instance;

	private Map<String, String> mServices = new HashMap<String, String>();


	/**
	 * Constructor privado para el singleton
	 * 
	 * Lee y almacena el contenido del fichero de configuración
	 */
	private ServerConfig() {
		final Configurations configs = new Configurations();
		try {
			final XMLConfiguration config = configs.xml("server-config.xml");

			mServices.put("CatalogService", config.getString("apiImpl.CatalogService"));
			mServices.put("AdminisService", config.getString("apiImpl.AdminisService"));
		} catch (ConfigurationException e) {
			// PDTE Tratamiento de exceptions
			e.printStackTrace();
		}
	}
	
	/**
	 * Recupera la instancia única del singleton
	 * 
	 * @return
	 */
	public static ServerConfig getInstance() {
		if (null == instance) {
			instance = new ServerConfig();
		}

		return instance;
	}

	/**
	 * Devuelve el nombre de la clase de negocio definida para resolver los APIs de una determinada
	 * clase de servicio. 
	 * 
	 * Una clase service debe tener asociada en el fichero de configuración una clase Business que 
	 * implenta el API definido en el servicio
	 * 
	 * @param classz Clase Service
	 * @return
	 */
	public String getServiceClassName(final Class classz) {
		
		return mServices.get(classz.getSimpleName());
	}

}
