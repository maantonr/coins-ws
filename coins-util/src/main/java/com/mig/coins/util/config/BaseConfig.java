package com.mig.coins.util.config;

import org.apache.commons.configuration2.XMLConfiguration;

public class BaseConfig {

	protected static String parseStringVar(XMLConfiguration config, String varName) {
		String varValue = null;
		
		final String tmpValue = config.getString(varName);
		if ((null != tmpValue) && (tmpValue.startsWith("@"))) {
			varValue = getEnvVar(tmpValue);
		} else {
			varValue = tmpValue;
		}
		
		return varValue;
	}
	
	protected static int parseIntVar(XMLConfiguration config, String varName, String defaultValue) throws NumberFormatException {
		int varValue = 0;
		
		String tmpValue = config.getString(varName, defaultValue);
		if (tmpValue.startsWith("@")) {
			tmpValue = getEnvVar(tmpValue);
		}
		
		varValue = Integer.parseInt(tmpValue);
		return varValue;
	}
	
	private static String getEnvVar(String varName)  {
		String varValue = System.getenv(varName.substring(1));
		
		return varValue;
	}
}
