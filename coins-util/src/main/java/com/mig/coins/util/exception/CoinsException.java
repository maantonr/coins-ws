package com.mig.coins.util.exception;

import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mig.coins.util.session.Session;
import com.mig.coins.util.session.SessionException;
import com.mig.coins.util.session.SessionManager;

// PDTE Documentar
public abstract class CoinsException extends Exception {

	private static final long serialVersionUID = 3945849173114436822L;
	private static final Log LOG=LogFactory.getLog(CoinsException.class);

	private static final String BUNDLE_NAME="CoinsExceptionBundle";// Suffix for bundle files. 
	private static final ResourceBundle bundle=initBundle();
	
	private static final String MSG_USER_DEFAULT="Error grave, pongase en contacto con el administrador del sistema";

	/** application specific error code */
	private final String code; 
		
	/** detailed error description for developers*/
	private final String msgDeveloper;
	
	private final String errorSequence=new java.rmi.server.UID().toString();

	public CoinsException(String errCode, String msgDeveloper, Object... varargs) {
		//this(null, enumCode, msgDeveloper, varargs);
		super(getMsgBundleFormatted(null, errCode, varargs));
		this.code = errCode;
		this.msgDeveloper = getMsgDeveloperFormatted(null, msgDeveloper, varargs);
	}

	public CoinsException(Exception cause, String errCode, String msgDeveloper, Object... varargs) {
		super(getMsgBundleFormatted(cause, errCode, varargs), cause);
		this.code = errCode;
		this.msgDeveloper = getMsgDeveloperFormatted(cause, msgDeveloper, varargs);
	}
	
	/**
	 * Formatea el mensaje con las posibles variables recibidas como argumentos. Formato esperado del mensaje: "Este es mi mensaje con las variables %1s %2s"
	 * 
	 * @param cause
	 * @param errCode
	 * @param varargs
	 * @return
	 */
	private static String getMsgBundleFormatted(Exception cause, String errCode, Object... varargs) {
		String message = "";
		try {
			message = getMsgFormatted(bundle.getString(errCode), varargs);
		} catch (java.util.MissingResourceException e) {
			LOG.error(String.format("\n\n **** ATENCIÓN DESARROLLADOR **** FALTA EL MENSAJE %s EN EL BUNDLE: ", errCode), e);
			if(cause!=null)
			{
				LOG.error("\n\nERROR ORIGINAL: ", cause); //muestra la traza completa del error original
			}
			message = MSG_USER_DEFAULT;
		}
		return message;
	}
	
	
	/**
	 * Formatea el mensaje con las posibles variables recibidas como argumentos. Formato esperado del mensaje: "Este es mi mensaje con las variables String %1s Decimal %2d"
	 * 
	 * @param message
	 * @param varargs
	 * @return
	 */
	private static String getMsgFormatted(String message,Object...  varargs){
		return (varargs!=null&varargs.length>0?String.format(message,(Object[]) varargs):message);
	}
	
	/**
	 * Formatea el mensaje del Desarrollador con las posibles variables recibidas como argumentos y le suma el mensaje de la excepción.
	 *   Formato esperado del mensaje: "Este es mi mensaje con las variables String %1s Decimal %2d"
	 * @param enumCode
	 * @param varargs
	 * @return
	 */
	private static String getMsgDeveloperFormatted(Exception cause,String message,Object... varargs){
		return getMsgFormatted(message,varargs)+(cause!=null?". |*** CAUSA = "+cause.getMessage()+" ***|":"");
	}
	

	/**
	 * init the Bundle object for the class
	 * 
	 * @return ResourceBundle
	 */
	private static ResourceBundle initBundle()	{
		Session session=null;
		
		// Obtenemos Locale a partir de la session
		Locale locale=Locale.getDefault();
		try {
			session = SessionManager.getInstance().getSessionInCurrentThread();
			if (null == session) {
				LOG.warn("No session defined. Using default locale "  + locale.toString());
			} else {
				locale=session.getLocale();
			}
		} catch (SessionException e) { //no dejamos que esta excepción pare el proceso
			LOG.warn("Error initializing CoinsExceotion. ", e);
		}
		
		// PDTE - Como inicializamos el bundle en los hijos
		return ResourceBundle.getBundle(BUNDLE_NAME, locale);
	}
	
	public String getCode() {
		return code;
	}

	public String getMsgDeveloper() {
		return msgDeveloper;
	}

	public String getErrorSequence() {
		return errorSequence;
	}

}
