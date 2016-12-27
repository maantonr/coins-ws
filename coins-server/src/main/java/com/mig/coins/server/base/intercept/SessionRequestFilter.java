package com.mig.coins.server.base.intercept;

//import isf.servlets.Country;
//import isf.servlets.Language;
//import isf.servlets.Method;
//import isf.servlets.Profile;
//import isf.servlets.Session;
//import isf.servlets.SessionSetupManager;
//import isf.servlets.User;

import java.io.IOException;
import java.util.Locale;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;

import com.mig.coins.main.base.Session;
import com.mig.coins.main.base.SessionManager;

//import com.indracompany.incms.ss.main.SsConstants.HeaderControl;
//import com.indracompany.incms.ss.main.SsConstants.HeadersSession;
//import com.indracompany.incms.ss.main.UserWeb;

/**
 * Filtro encargado de crear la Sesion en cada peticion
 * 
 * @author jjimenezg
 * 
 */
@Priority(Priorities.HEADER_DECORATOR)
public class SessionRequestFilter implements ContainerRequestFilter {

	@Context
	private HttpServletRequest request;

	public void filter(ContainerRequestContext requestContext)
			throws IOException {
		// PDTE Construir la Session a partir de los datos de cabecera
		Locale loc = new Locale("ES", "es");
		
		Thread currentThread=Thread.currentThread();
		SessionManager manager = SessionManager.getInstance();
		Session sess = manager.setSessionInCurrentThread(loc, "127.0.0.1", currentThread.getName());
		
		System.out.println("Construida la sesion: " + sess.toString());
	}
}


//	@Override
//	public void filter(ContainerRequestContext requestContext)
//			throws IOException {

//		//la session se libera en TransactionalResourceInterceptor
//		Session session = getSession(requestContext.getLanguage(),
//				requestContext.getUriInfo().getPath());
//		
//		//Se captura la cabecera x-Origin y se guarda como businessProperties en la sessión
//		String origin = requestContext.getHeaderString(HeaderControl.XORIGIN.getValue());
//		//Se captura la cabecera x-Origin&d donde se encuentra el docNumber
//		String docNumber = requestContext.getHeaderString(HeaderControl.XORIGIN_USER.getValue());
//		if (origin != null && !origin.isEmpty()){
//			Map<String, String> businessProperties = new HashMap<String, String>();
//			businessProperties.put(HeaderControl.XORIGIN.getValue(), origin);
//
//			//DocNumber
//			if (docNumber != null && !docNumber.isEmpty()){
//				businessProperties.put(HeaderControl.XORIGIN_USER.getValue(), docNumber);
//			}
//			
//			session.setBusinessProperties(businessProperties);
//		}
//		
//		SessionSetupManager.getInstance().setSessionSetupInCurrentThread(
//				session.getSessionId(), session);
//
//		/*
//		 * final SecurityContext securityContext = requestContext
//		 * .getSecurityContext();  i f (securityContext = = null ||
//		 * !securityContext.isUserInRole("privileged")) {
//		 * 
//		 * requestContext.abortWith(Response
//		 * .status(Response.Status.UNAUTHORIZED)
//		 * .entity("User cannot access the resource.").build()); }
//		 */

//	}


//	/**
//	 * Method to create session
//	 * @param locale
//	 * @param methodName
//	 * @return
//	 */
//	private Session getSession(Locale locale, String methodName) {
//		UserWeb userWeb = UserWeb.getInstance();// implica login s�lo la primera vez que se instancia
//		Locale myLocale = locale != null ? locale : request.getLocale();
//		String sLang = myLocale != null && myLocale.getLanguage() != null ? myLocale
//				.getLanguage() : userWeb.getLanguage();
//		Language language = new Language();
//		language.setLanguageCode(sLang);
//
//		String sCountry = myLocale != null && myLocale.getCountry() != null ? myLocale
//				.getCountry() : userWeb.getCountry();
//		Country country = new Country();
//		country.setCountryCode(sCountry);
//
//		Method method = new Method();
//		method.setMethodName(methodName);
//
//		User user = new User();
//		user.setId(Long.parseLong(userWeb.getUser()));
//		user.setName(userWeb.getUserName());
//
//		Profile profile = new Profile();
//		profile.setId(Long.parseLong(userWeb.getProfileId()));
//
//		//creamos una nueva sesion cada vez
//		return new Session(user, country, language, profile, method,
//				userWeb.getSessionId(), userWeb.getAuthorityId(),
//				request.getRemoteAddr(), request.getHeader(HeadersSession.LOCALIZATION.getValue()),
//				request.getHeader(HeadersSession.TRANSACTION_COMMENT.getValue()),
//				request.getHeader(HeadersSession.CLIENT_VERSION.getValue()));
//	}
//}
