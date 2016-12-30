package com.mig.coins.server.base.error;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.apache.commons.logging.Log;

import com.mig.coins.server.base.error.CoinsWsException;

// PDTE Documentar
public class AppExceptionMapper implements ExceptionMapper<CoinsWsException> {
	private static final Log LOG=org.apache.commons.logging.LogFactory.getLog(AppExceptionMapper.class);
	private final String errorSequence=new java.rmi.server.UID().toString();
	
	public Response toResponse(CoinsWsException ex) {
		LOG.error(ex.toString() + " (Error sequence=" + errorSequence + ")" , ex);
		return Response.status(ex.getHttpStatus())
				.entity(new ErrorMessage(ex))
				.type(MediaType.APPLICATION_JSON).
				build();
	}
}
