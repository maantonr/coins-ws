package com.mig.coins.main;

import java.sql.Connection;
import java.util.Locale;

import com.mig.coins.util.session.Session;
import com.mig.coins.util.session.SessionException;
import com.mig.coins.util.session.SessionManager;

// PDTE Documentar
public class BaseBusiness implements IBaseBusiness {

	private Session mySession;

	public BaseBusiness(){
	}

	protected Session getSession() throws SessionException  {
		if (null == mySession) {
			mySession = SessionManager.getInstance().getSessionInCurrentThread();
		}
		return mySession;
	}

	protected Connection getConnection() throws SessionException {
		if (null == mySession) {
			mySession = SessionManager.getInstance().getSessionInCurrentThread();
		}
		return mySession.getConnection();
	}

	protected Locale getLocale() throws SessionException {
		if (null == mySession) {
			mySession = SessionManager.getInstance().getSessionInCurrentThread();
		}
		return mySession.getLocale();
	}

	// PDTE Métodos para generar las exceptions 
	// PDTE Otros métodos utiles
}
