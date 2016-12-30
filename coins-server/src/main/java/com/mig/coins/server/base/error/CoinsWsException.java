package com.mig.coins.server.base.error;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mig.coins.util.exception.CoinsException;

// PDTE Documentar
// PDTE 
public class CoinsWsException extends CoinsException {

	private static final long serialVersionUID = 9048152855418526379L;
	private static final Log LOG=LogFactory.getLog(CoinsWsException.class);
	private static final String BUNDLE_NAME="CoinsWsExceptionBundle";
	
	private final Integer httpStatus;
	private final String helpLink;
	
	public CoinsWsException(String errCode, String msgDeveloper, Object[] varargs) {
		super(null, errCode, msgDeveloper, varargs);
		// PDTE Implementar - Tengo que complementar el error http y el link
		httpStatus=500;
		helpLink="http://blablabla.com";
	}

	public CoinsWsException(CoinsException cause, String errCode, String msgDeveloper, Object[] varargs) {
		super(cause, errCode, msgDeveloper, varargs);
		// PDTE Implementar - Tengo que complementar el error http y el link con una conversión?
		httpStatus=500;
		helpLink="http://blablabla.com";
	}

	public CoinsWsException(Exception cause, String errCode, String msgDeveloper, Object[] varargs) {
		super(cause, errCode, msgDeveloper, varargs);
		// PDTE Implementar - Tengo que complementar el error http y el link
		httpStatus=500;
		helpLink="http://blablabla.com";
	}

	public Integer getHttpStatus() {
		return httpStatus;
	}

	public String getHelpLink() {
		return helpLink;
	}

	public static String getDefaultLink() {
		return "http://blablabla.com";
	}
}
