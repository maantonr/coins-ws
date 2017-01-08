package com.mig.coins.main.adm;

import com.mig.coins.db.base.DbStatus;

public class SystemStatus {

	private String appName;
	
	private String homeDir;
	private String dataDir;
	private String logDir;

	private DbStatus dbInfo;

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getHomeDir() {
		return homeDir;
	}

	public void setHomeDir(String homeDir) {
		this.homeDir = homeDir;
	}

	public String getDataDir() {
		return dataDir;
	}

	public void setDataDir(String dataDir) {
		this.dataDir = dataDir;
	}

	public String getLogDir() {
		return logDir;
	}

	public void setLogDir(String logDir) {
		this.logDir = logDir;
	}

	public DbStatus getDbInfo() {
		return dbInfo;
	}

	public void setDbInfo(DbStatus dbInfo) {
		this.dbInfo = dbInfo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SystemStatus [appName=");
		builder.append(appName);
		builder.append(", homeDir=");
		builder.append(homeDir);
		builder.append(", dataDir=");
		builder.append(dataDir);
		builder.append(", logDir=");
		builder.append(logDir);
		builder.append(", dbInfo=");
		builder.append(dbInfo);
		builder.append("]");
		return builder.toString();
	}
	
	
}
