package com.mig.coins.main;

import java.sql.Connection;
import java.util.Locale;

import com.mig.coins.util.session.Session;
import com.mig.coins.util.session.SessionException;
import com.mig.coins.util.session.SessionManager;

// PDTE Documentar
public class BaseBusiness implements IBaseBusiness {

	private final Session mySession;

	public BaseBusiness() throws SessionException {
		mySession = SessionManager.getInstance().getSessionInCurrentThread();
	}

	protected Session getSession() {
		return mySession;
	}

	protected Connection getConnection() throws SessionException {
		return mySession.getConnection();
	}

	protected Locale getLocale() {
		return mySession.getLocale();
	}

	// PDTE Métodos para generar las exceptions 
	// PDTE Otros métodos utiles
}
