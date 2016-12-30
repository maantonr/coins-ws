package com.mig.coins.server.base.error;

import java.lang.reflect.InvocationTargetException;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// PDTE Documentar
public class ErrorMessage {

	private static final Log LOG=LogFactory.getLog(ErrorMessage.class);

	/** contains the same HTTP Status code returned by the server */
	@XmlElement(name = "httpStatus")
	int httpStatus;
	
	/** application specific error code */
	@XmlElement(name = "code")
	String code;
	
	/** message describing the error (copy message of parent-exception)*/
	@XmlElement(name = "msgUser")
	String message;
		
	/** link point to page where the error message is documented */
	@XmlElement(name = "helpLink")
	String helpLink;
	
	/** extra information that might useful for developers */
	@XmlElement(name = "msgDeveloper")
	String msgDeveloper;	

	/** errorSequence for developers and systems persons**/
	@XmlElement(name = "errorSequence")
	String errorSequence;	
	
	public String getErrorSequence() {
		return errorSequence;
	}

	public void setErrorSequence(String errorSequence) {
		this.errorSequence = errorSequence;
	}

	public int getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMsgDeveloper() {
		return msgDeveloper;
	}

	public void setMsgDeveloper(String developerMessage) {
		this.msgDeveloper = developerMessage;
	}

	public String getHelpLink() {
		return helpLink;
	}

	public void setHelpLink(String helpLink) {
		this.helpLink = helpLink;
	}
	
	public ErrorMessage(CoinsWsException ex){
		try
		{
			BeanUtils.copyProperties(this, ex);
		} 
		catch (IllegalAccessException| InvocationTargetException e)
		{
			LOG.warn("ErrorMessage:",e);
		}
	}
	
	public ErrorMessage(NotFoundException ex){
		this.httpStatus = Response.Status.NOT_FOUND.getStatusCode();
		this.message = ex.getMessage();
		this.helpLink = "https://jersey.java.net/apidocs/2.8/jersey/javax/ws/rs/NotFoundException.html";		
	}

	/**
	 * 
	 */
	public ErrorMessage() {
		//para la construcción con Excepciones que no son de tipo AppException
	}

}
