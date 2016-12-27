package com.mig.coins.db.dao;

import java.sql.Connection;
import java.util.Locale;

import com.mig.coins.db.base.PersistenceConfig;

// PDTE Documentar
public class DAOFactory {

	private static DAOFactory factory;
	private PersistenceConfig conf; 
	
	private DAOFactory() {
		conf = PersistenceConfig.getInstance();
	}

	public static DAOFactory getInstance() {
		if (factory == null) {
			factory = new DAOFactory();
		}
		
		return factory;
	}

	public IPaisDAO getPaisDAO(Connection conn, Locale loc) {
		return (IPaisDAO)getGenericDAO(conf.getPaisDAOClassName(), conn, loc);
	}

	public IDivisaDAO getDivisaDAO(Connection conn, Locale loc) {
		return (IDivisaDAO)getGenericDAO(conf.getDivisaDAOClassName(), conn, loc);
	}

	private IDAO getGenericDAO(String className, Connection conn, Locale loc) {
		IDAO daoImpl = null;
		try {
			daoImpl=(IDAO)Class.forName(className).newInstance();
			daoImpl.setConnection(conn);
			daoImpl.setLocale(loc);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// PDTE Tratamiento de exceptions
			e.printStackTrace();
		}
		
		return daoImpl;
	}

}
