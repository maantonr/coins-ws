package com.mig.coins.server.base.error;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.apache.commons.logging.Log;

// PDTE Documentar
public class UncheckedExceptionMapper implements ExceptionMapper<Throwable>
{
	private static final Log LOG=org.apache.commons.logging.LogFactory.getLog(UncheckedExceptionMapper.class);
	private final String errorSequence=new java.rmi.server.UID().toString();
	public Response toResponse(Throwable ex) 
	{
		LOG.error(ex.toString() + " (Error sequence=" + errorSequence + ")" , ex);
		ErrorMessage errorMessage = new ErrorMessage();
		setHttpStatus(ex, errorMessage);
		errorMessage.setCode("WS0000"); // 
		errorMessage.setMessage(ex.getMessage());
		StringWriter errorStackTrace = new StringWriter();
		ex.printStackTrace(new PrintWriter(errorStackTrace));//NOSONAR jjimenezg 20/06/2016 necesario para mostrar el stacktrace por pantalla
		errorMessage.setMsgDeveloper(errorStackTrace.toString());
		errorMessage.setHelpLink(CoinsWsException.getDefaultLink());
		errorMessage.setErrorSequence(errorSequence);

		return Response.status(errorMessage.getHttpStatus()).entity(errorMessage).type(MediaType.APPLICATION_JSON).build();
	}

	private void setHttpStatus(Throwable ex, ErrorMessage errorMessage) 
	{
		if (ex instanceof WebApplicationException) 
		{ 
			errorMessage.setHttpStatus(((WebApplicationException) ex).getResponse().getStatus());
		} 
		else 
		{
			errorMessage.setHttpStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()); // defaults to internal server error 500
		}
	}
}
