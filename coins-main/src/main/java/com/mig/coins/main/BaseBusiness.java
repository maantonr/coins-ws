package com.mig.coins.main;

import java.sql.Connection;
import java.util.Locale;

import com.mig.coins.main.base.Session;
import com.mig.coins.main.base.SessionManager;

// PDTE Documentar
public class BaseBusiness implements IBaseBusiness {

	private final Session mySession;

	public BaseBusiness() {
		mySession = SessionManager.getInstance().getSessionInCurrentThread();
	}

	protected Session getSession() {
		return mySession;
	}

	protected Connection getConnection() {
		return mySession.getConnection();
	}

	protected Locale getLocale() {
		return mySession.getLocale();
	}

	// PDTE Métodos para generar las exceptions 
	// PDTE Otros métodos utiles
}
