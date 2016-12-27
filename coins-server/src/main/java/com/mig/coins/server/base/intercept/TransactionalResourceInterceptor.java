package com.mig.coins.server.base.intercept;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mig.coins.db.base.CoinsMockConnection;
import com.mig.coins.db.base.Pool;
import com.mig.coins.main.base.Session;
import com.mig.coins.main.base.SessionManager;

//PDTE Documentar
@Priority(Priorities.USER)
public class TransactionalResourceInterceptor implements MethodInterceptor {

	private static final Log LOG=LogFactory.getLog(TransactionalResourceInterceptor.class);

	@Override
	public Object invoke(final MethodInvocation methodInvocation)
			throws Throwable {

		final SessionManager manager = SessionManager.getInstance();
		final Session session = manager.getSessionInCurrentThread();

		// Inicia la transacción de base de datos
		final CoinsMockConnection connection = getMockConnection();
		session.setConnection(connection);
		
		try {
			// Invoca al método interceptado
			final Object result = methodInvocation.proceed();

			// Commit
			connection.sourceConnection().commit();

			return result;
		} 
		catch (final Exception re)  
		{
			throw re; // propagamos la excepción que será gestionada por los Mapper
		}
		finally
		{
			manager.removeSessionInCurrentThread();
		}
	}

	private CoinsMockConnection getMockConnection() throws SQLException 
	{
		final Pool pool = Pool.getInstance();
		final Connection source = pool.getConnection();
		final CoinsMockConnection mock = new CoinsMockConnection(source);
		return mock;
	}

}