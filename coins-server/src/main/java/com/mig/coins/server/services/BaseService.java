package com.mig.coins.server.services;

import com.mig.coins.main.IBaseBusiness;
import com.mig.coins.server.base.ServerConfig;


/**
 * Clase padre de todos los servicios que se publicitan con WebServices
 * 
 * 
 * @author maanton
 *
 */
public class BaseService {

	/**
	 * Devuelve una instancia de la clase Business asociada al servicio y que implementa
	 * el API de negocio que resuelve las peticiones WebService recibidas
	 * 
	 * @param classz
	 * @return 
	 */
	protected IBaseBusiness getServiceImpl(final Class classz) {
		
		final String className = ServerConfig.getInstance().getServiceClassName(classz);
		if (null == className) {
			// PDTE Generar Exception
		}

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
