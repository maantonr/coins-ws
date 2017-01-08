package com.mig.coins.db.base;

// Pdte Documentar
public class DbStatus {

	private String status;
	
	private String driver;
	private String host;
	private String port;
	private String url;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DbStatus [status=");
		builder.append(status);
		builder.append(", driver=");
		builder.append(driver);
		builder.append(", host=");
		builder.append(host);
		builder.append(", port=");
		builder.append(port);
		builder.append(", url=");
		builder.append(url);
		builder.append("]");
		return builder.toString();
	}

	
}
