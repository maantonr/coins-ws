package com.mig.coins.db.base;

// PDTE Documentar
/*
 * Clase para el control de conexiones de la SelfService,
 * Permite controlar posibles commits y rollback intermedios.
 */
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

import org.apache.commons.logging.Log;

// PDTE Documentar
/**
 * 
 * @author jjimenezg
 * @version 2016.00
 */
public class CoinsMockConnection implements Connection, IConnectionProxy
{
	private transient final Connection src;

	private static final Log LOG=org.apache.commons.logging.LogFactory.getLog(com.mig.coins.db.base.CoinsMockConnection.class);

	public CoinsMockConnection(Connection connection)
	{
		src=connection;
	}

	@Override
	public Connection sourceConnection()
	{
		return src;
	}

	/**
	 * @param iface
	 * @return
	 * @throws SQLException
	 * @see java.sql.Wrapper#unwrap(java.lang.Class)
	 */
	@Override
	public <T> T unwrap(Class<T> iface)
		throws SQLException
	{
		return src.unwrap(iface);
	}

	/**
	 * @param iface
	 * @return
	 * @throws SQLException
	 * @see java.sql.Wrapper#isWrapperFor(java.lang.Class)
	 */
	@Override
	public boolean isWrapperFor(Class<?> iface)
		throws SQLException
	{
		return src.isWrapperFor(iface);
	}

	/**
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#createStatement()
	 */
	@Override
	public Statement createStatement()
		throws SQLException
	{
		return src.createStatement();
	}

	/**
	 * @param sql
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#prepareStatement(java.lang.String)
	 */
	@Override
	public PreparedStatement prepareStatement(String sql)
		throws SQLException
	{
		return src.prepareStatement(sql);
	}

	/**
	 * @param sql
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#prepareCall(java.lang.String)
	 */
	@Override
	public CallableStatement prepareCall(String sql)
		throws SQLException
	{
		return src.prepareCall(sql);
	}

	/**
	 * @param sql
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#nativeSQL(java.lang.String)
	 */
	@Override
	public String nativeSQL(String sql)
		throws SQLException
	{
		return src.nativeSQL(sql);
	}

	/**
	 * @param autoCommit
	 * @throws SQLException
	 * @see java.sql.Connection#setAutoCommit(boolean)
	 */
	@Override
	public void setAutoCommit(boolean autoCommit)
		throws SQLException
	{
		src.setAutoCommit(autoCommit);
	}

	/**
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#getAutoCommit()
	 */
	@Override
	public boolean getAutoCommit()
		throws SQLException
	{
		return src.getAutoCommit();
	}

	/**
	 * @throws SQLException
	 * @see java.sql.Connection#commit()
	 * @deprecated
	 */
	@Deprecated
	@Override
	public void commit()
		throws SQLException
	{
		LOG.warn("!!!! ATENCIÓN ESTE COMMIT SE DESCARTARÁ PARA GESTIÓN ÚNICA DE COMMITS EN CAPA SUPERIOR, ver sourceConnection.commit() si procede!!!!! ");
	}

	/**
	 * @throws SQLException
	 * @see java.sql.Connection#rollback()
	 * @deprecated
	 */
	@Deprecated
	@Override
	public void rollback()
		throws SQLException
	{
		LOG.warn("!!!! ATENCI�N ESTE ROLLBACK SE DESCARTAR�� PARA GESTI�N ښNICA DE ROLLBACKS EN CAPA SUPERIOR, ver sourceConnection.rollback() si procede !!!!! ");
	}

	/**
	 * @throws SQLException
	 * @see java.sql.Connection#close()
	 */
	@Override
	public void close()
		throws SQLException
	{
		if(!isClosed())
		{
			src.close(); //S� cierro la conexi�n para garantizar que no se queda abierta ante un cierre desde SesionSetup
		}
	}

	/**
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#isClosed()
	 */
	@Override
	public boolean isClosed()
		throws SQLException
	{
		
		return (null == src) ? true : src.isClosed();
	}

	/**
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#getMetaData()
	 */
	@Override
	public DatabaseMetaData getMetaData()
		throws SQLException
	{
		return src.getMetaData();
	}

	/**
	 * @param readOnly
	 * @throws SQLException
	 * @see java.sql.Connection#setReadOnly(boolean)
	 */
	@Override
	public void setReadOnly(boolean readOnly)
		throws SQLException
	{
		src.setReadOnly(readOnly);
	}

	/**
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#isReadOnly()
	 */
	@Override
	public boolean isReadOnly()
		throws SQLException
	{
		return src.isReadOnly();
	}

	/**
	 * @param catalog
	 * @throws SQLException
	 * @see java.sql.Connection#setCatalog(java.lang.String)
	 */
	@Override
	public void setCatalog(String catalog)
		throws SQLException
	{
		src.setCatalog(catalog);
	}

	/**
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#getCatalog()
	 */
	@Override
	public String getCatalog()
		throws SQLException
	{
		return src.getCatalog();
	}

	/**
	 * @param level
	 * @throws SQLException
	 * @see java.sql.Connection#setTransactionIsolation(int)
	 */
	@Override
	public void setTransactionIsolation(int level)
		throws SQLException
	{
		src.setTransactionIsolation(level);
	}

	/**
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#getTransactionIsolation()
	 */
	@Override
	public int getTransactionIsolation()
		throws SQLException
	{
		return src.getTransactionIsolation();
	}

	/**
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#getWarnings()
	 */
	@Override
	public SQLWarning getWarnings()
		throws SQLException
	{
		return src.getWarnings();
	}

	/**
	 * @throws SQLException
	 * @see java.sql.Connection#clearWarnings()
	 */
	@Override
	public void clearWarnings()
		throws SQLException
	{
		src.clearWarnings();
	}

	/**
	 * @param resultSetType
	 * @param resultSetConcurrency
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#createStatement(int, int)
	 */
	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency)
		throws SQLException
	{
		return src.createStatement(resultSetType, resultSetConcurrency);
	}

	/**
	 * @param sql
	 * @param resultSetType
	 * @param resultSetConcurrency
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#prepareStatement(java.lang.String, int, int)
	 */
	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
		throws SQLException
	{
		return src.prepareStatement(sql, resultSetType, resultSetConcurrency);
	}

	/**
	 * @param sql
	 * @param resultSetType
	 * @param resultSetConcurrency
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#prepareCall(java.lang.String, int, int)
	 */
	@Override
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency)
		throws SQLException
	{
		return src.prepareCall(sql, resultSetType, resultSetConcurrency);
	}

	/**
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#getTypeMap()
	 */
	@Override
	public Map<String, Class<?>> getTypeMap()
		throws SQLException
	{
		return src.getTypeMap();
	}

	/**
	 * @param map
	 * @throws SQLException
	 * @see java.sql.Connection#setTypeMap(java.util.Map)
	 */
	@Override
	public void setTypeMap(Map<String, Class<?>> map)
		throws SQLException
	{
		src.setTypeMap(map);
	}

	/**
	 * @param holdability
	 * @throws SQLException
	 * @see java.sql.Connection#setHoldability(int)
	 */
	@Override
	public void setHoldability(int holdability)
		throws SQLException
	{
		src.setHoldability(holdability);
	}

	/**
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#getHoldability()
	 */
	@Override
	public int getHoldability()
		throws SQLException
	{
		return src.getHoldability();
	}

	/**
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#setSavepoint()
	 */
	@Override
	public Savepoint setSavepoint()
		throws SQLException
	{
		return src.setSavepoint();
	}

	/**
	 * @param name
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#setSavepoint(java.lang.String)
	 */
	@Override
	public Savepoint setSavepoint(String name)
		throws SQLException
	{
		return src.setSavepoint(name);
	}

	/**
	 * @param savepoint
	 * @throws SQLException
	 * @see java.sql.Connection#rollback(java.sql.Savepoint)
	 */
	@Override
	public void rollback(Savepoint savepoint)
		throws SQLException
	{
		LOG.warn("rollback intervenido");
	}

	/**
	 * @param savepoint
	 * @throws SQLException
	 * @see java.sql.Connection#releaseSavepoint(java.sql.Savepoint)
	 */
	@Override
	public void releaseSavepoint(Savepoint savepoint)
		throws SQLException
	{
		src.releaseSavepoint(savepoint);
	}

	/**
	 * @param resultSetType
	 * @param resultSetConcurrency
	 * @param resultSetHoldability
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#createStatement(int, int, int)
	 */
	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
		throws SQLException
	{
		return src.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	/**
	 * @param sql
	 * @param resultSetType
	 * @param resultSetConcurrency
	 * @param resultSetHoldability
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#prepareStatement(java.lang.String, int, int, int)
	 */
	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability)
		throws SQLException
	{
		return src.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	/**
	 * @param sql
	 * @param resultSetType
	 * @param resultSetConcurrency
	 * @param resultSetHoldability
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#prepareCall(java.lang.String, int, int, int)
	 */
	@Override
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability)
		throws SQLException
	{
		return src.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	/**
	 * @param sql
	 * @param autoGeneratedKeys
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#prepareStatement(java.lang.String, int)
	 */
	@Override
	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys)
		throws SQLException
	{
		return src.prepareStatement(sql, autoGeneratedKeys);
	}

	/**
	 * @param sql
	 * @param columnIndexes
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#prepareStatement(java.lang.String, int[])
	 */
	@Override
	public PreparedStatement prepareStatement(String sql, int[] columnIndexes)
		throws SQLException
	{
		return src.prepareStatement(sql, columnIndexes);
	}

	/**
	 * @param sql
	 * @param columnNames
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#prepareStatement(java.lang.String, java.lang.String[])
	 */
	@Override
	public PreparedStatement prepareStatement(String sql, String[] columnNames)
		throws SQLException
	{
		return src.prepareStatement(sql, columnNames);
	}

	/**
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#createClob()
	 */
	@Override
	public Clob createClob()
		throws SQLException
	{
		return src.createClob();
	}

	/**
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#createBlob()
	 */
	@Override
	public Blob createBlob()
		throws SQLException
	{
		return src.createBlob();
	}

	/**
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#createNClob()
	 */
	@Override
	public NClob createNClob()
		throws SQLException
	{
		return src.createNClob();
	}

	/**
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#createSQLXML()
	 */
	@Override
	public SQLXML createSQLXML()
		throws SQLException
	{
		return src.createSQLXML();
	}

	/**
	 * @param timeout
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#isValid(int)
	 */
	@Override
	public boolean isValid(int timeout)
		throws SQLException
	{
		return src.isValid(timeout);
	}

	/**
	 * @param name
	 * @param value
	 * @throws SQLClientInfoException
	 * @see java.sql.Connection#setClientInfo(java.lang.String, java.lang.String)
	 */
	@Override
	public void setClientInfo(String name, String value)
		throws SQLClientInfoException
	{
		src.setClientInfo(name, value);
	}

	/**
	 * @param properties
	 * @throws SQLClientInfoException
	 * @see java.sql.Connection#setClientInfo(java.util.Properties)
	 */
	@Override
	public void setClientInfo(Properties properties)
		throws SQLClientInfoException
	{
		src.setClientInfo(properties);
	}

	/**
	 * @param name
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#getClientInfo(java.lang.String)
	 */
	@Override
	public String getClientInfo(String name)
		throws SQLException
	{
		return src.getClientInfo(name);
	}

	/**
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#getClientInfo()
	 */
	@Override
	public Properties getClientInfo()
		throws SQLException
	{
		return src.getClientInfo();
	}

	/**
	 * @param typeName
	 * @param elements
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#createArrayOf(java.lang.String, java.lang.Object[])
	 */
	@Override
	public Array createArrayOf(String typeName, Object[] elements)
		throws SQLException
	{
		return src.createArrayOf(typeName, elements);
	}

	/**
	 * @param typeName
	 * @param attributes
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#createStruct(java.lang.String, java.lang.Object[])
	 */
	@Override
	public Struct createStruct(String typeName, Object[] attributes)
		throws SQLException
	{
		return src.createStruct(typeName, attributes);
	}

	/**
	 * @param schema
	 * @throws SQLException
	 * @see java.sql.Connection#setSchema(java.lang.String)
	 */
	@Override
	public void setSchema(String schema)
		throws SQLException
	{
		src.setSchema(schema);
	}

	/**
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#getSchema()
	 */
	@Override
	public String getSchema()
		throws SQLException
	{
		return src.getSchema();
	}

	/**
	 * @param executor
	 * @throws SQLException
	 * @see java.sql.Connection#abort(java.util.concurrent.Executor)
	 */
	@Override
	public void abort(Executor executor)
		throws SQLException
	{
		src.abort(executor);
	}

	/**
	 * @param executor
	 * @param milliseconds
	 * @throws SQLException
	 * @see java.sql.Connection#setNetworkTimeout(java.util.concurrent.Executor, int)
	 */
	@Override
	public void setNetworkTimeout(Executor executor, int milliseconds)
		throws SQLException
	{
		src.setNetworkTimeout(executor, milliseconds);
	}

	/**
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#getNetworkTimeout()
	 */
	@Override
	public int getNetworkTimeout()
		throws SQLException
	{
		return src.getNetworkTimeout();
	}

}
