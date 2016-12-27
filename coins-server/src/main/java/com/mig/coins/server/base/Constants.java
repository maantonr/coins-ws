package com.mig.coins.server.base;


import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/*
 * Clase genérica de constantes.
 * 
 * @author maanton
 * 
 * @date 10/12/2016
 * 
 */
public abstract class Constants
{

	// PDTE Revisar
//	/**
//	 * Cabeceras de sessión permitidas
//	 * 
//	 * @version 2016.00
//	 */
//	public enum HeadersSession {
//		LOCALIZATION("localization"),
//		TRANSACTION_COMMENT("transactionComment"), CLIENT_VERSION("clientVersion");
//		private final String value;
//
//		private static final Map<String, HeadersSession> lookup=new HashMap<String, HeadersSession>();
//		static
//		{
//			for (HeadersSession each : EnumSet.allOf(HeadersSession.class))
//			{
//				lookup.put(each.value, each);
//			}
//		}
//
//		public static HeadersSession getEnum(String value)
//		{
//			return lookup.get(value);
//		}
//
//		private HeadersSession(String value)
//		{
//			this.value=value;
//		}
//
//		public String getValue()
//		{
//			return value;
//		}
//	}
	
	/**
	 * Packages intervenidos por filtros o Interceptores
	 * 
	 * @version 2016.00
	 */
	public enum PackagesIntercept {
		BASE("com.mig.coins"),
		SERVICES("com.mig.coins.server.services");

		private final String value;

		private static final Map<String, PackagesIntercept> lookup=new HashMap<String, PackagesIntercept>();
		static
		{
			for (PackagesIntercept each : EnumSet.allOf(PackagesIntercept.class))
			{
				lookup.put(each.value, each);
			}
		}

		public static PackagesIntercept getEnum(String value)
		{
			return lookup.get(value);
		}

		private PackagesIntercept(String value)
		{
			this.value=value;
		}

		public String getValue()
		{
			return value;
		}
	}
	
	// PDTE Revisar
//	/**
//	 * Properties UserWeb enumeration
//	 * 
//	 * @version 2016.00
//	 */
//	public enum UserWebProp {
//		USER_NAME("userName"),
//		LANGUAGE("language"), COUNTRY("country"),
//		USER_CLAVE("userClave"), PROFILE_CODE("profileCode");
//
//		private final String value;
//
//		private static final Map<String, UserWebProp> lookup=new HashMap<String, UserWebProp>();
//		static
//		{
//			for (UserWebProp each : EnumSet.allOf(UserWebProp.class))
//			{
//				lookup.put(each.value, each);
//			}
//		}
//
//		public static UserWebProp getEnum(String value)
//		{
//			return lookup.get(value);
//		}
//
//		private UserWebProp(String value)
//		{
//			this.value=value;
//		}
//
//		public String getValue()
//		{
//			return value;
//		}
//	}
	
//	public enum HeaderControl{
//		XORIGIN("X-Incms-Origin"), XORIGIN_USER("X-Incms-Origin-D");
//		
//		private final String value;
//		
//		private static final Map<String, HeaderControl> Lookup=new HashMap<String, HeaderControl>();
//		
//		public static HeaderControl getEnum(String value){
//			return Lookup.get(value);
//		}
//		
//		private HeaderControl(String value){
//			this.value = value;
//		}
//		
//		public String getValue(){
//			return value;
//		}
//	}
}

