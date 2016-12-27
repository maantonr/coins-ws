package com.mig.coins.main.base;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// PDTE Documentar
public class SessionManager {

	private static final Log LOG=LogFactory.getLog(SessionManager.class);
	private static final int MAX_SESSION = 128;

	private static final SessionManager INSTANCE = new SessionManager();

	private final Map<Thread, Session> mapSessionByThread = new HashMap<Thread, Session>(MAX_SESSION);

	/** Timeout en milisengudos. */
	private final int timeout = 1000 * 60 * 2;

	/** Periodo en milisegundos (2 minutos) que revisamos si hay sesiones caducadas*/
	private final int period = 1000 * 60 * 1;

	public static SessionManager getInstance() {
		return INSTANCE;
	}

	private SessionManager() {
		final Timer timer = new Timer(true);

		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				removeSessionsOlderThanTimeout();
			}
		}, 0, this.period);
	}

	private void removeSessionsOlderThanTimeout() {
		synchronized (SessionManager.this) {
			final Collection<Session> colSession = SessionManager.this.mapSessionByThread.values();
			final long now = System.currentTimeMillis();
			for (Iterator<Session> iterator = colSession.iterator(); iterator.hasNext();) {
				final Session session = iterator.next();
				final long timeDiference = now - session.getCurrentTime().getTime();
				if (this.timeout <= timeDiference) {
					LOG.trace("Deleting SessionSetup sessionId=" + session.getSessionId());

					iterator.remove();
					SessionManager.this.mapSessionByThread.remove(session.getCurrentThread());
					SessionManager.this.notifyAll();
				}
			}
		}
	}

	// PDTE Exceptions
	public Session getSessionInCurrentThread()
	//			throws SessionSetupNotSetException
	{
		synchronized (this)
		{
			final Thread currentThread=Thread.currentThread();
			final Session sessionSetup=this.mapSessionByThread.get(currentThread);
			if (sessionSetup == null)
			{
				// PDTE Lanzar la exception
				//					throw new SessionSetupNotSetException(currentThread);
			}
			return sessionSetup;
		}
	}

	/**
	 * Associates a session just created to the current thread.
	 * 
	 * @param session Session.
	 */
	// PDTE Exceptions
	public Session setSessionInCurrentThread(Locale locale, String ipAddress, String sessionId)
	{
		synchronized (this)
		{
			final Thread currentThread=Thread.currentThread();
			Session sessionOld=this.mapSessionByThread.get(currentThread);
			if (sessionOld != null)
			{
				LOG.error("Ya existe una sessión en el hilo actual: " + sessionOld.toString());
				// PDTE No debería ocurrir si la estamos eliminando al finalizar la sessión
			}

			if (size() == MAX_SESSION)
			{ // Chequeo anti-saturación:
				removeSessionsOlderThanTimeout();
			}

			// Construyo la session y la guardo asociada al hilo actual
			final Session sess = new Session(locale, ipAddress, currentThread, sessionId);
			this.mapSessionByThread.put(currentThread, sess);
			this.notifyAll();
			return sess;
		}
	}

	public void removeSessionInCurrentThread()
	{
		synchronized (this)
		{
			final Thread currentThread=Thread.currentThread();
			final Session sessionSetup=this.mapSessionByThread.remove(currentThread);
			if (sessionSetup != null)
			{
				this.notifyAll();
				
				final Connection conn = sessionSetup.getConnection();
				if (null != conn)
				{
					try
					{
						// PDTE Verificar como se cierra la conexion
						conn.close();// El Pool se encarga de liberarla, tb hace un rollback implicitamente
					}
					catch (SQLException e)
					{
						LOG.warn("Error al cerrar la conexión de base de datos ", e);
					}
				}
			}
		}
	}

	public int size()
	{
		return this.mapSessionByThread.size();
	}

	public Iterator<Session> iterator()
	{
		return this.mapSessionByThread.values().iterator();
	}

}
