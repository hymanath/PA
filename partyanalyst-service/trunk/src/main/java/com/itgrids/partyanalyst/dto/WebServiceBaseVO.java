package com.itgrids.partyanalyst.dto;

public class WebServiceBaseVO {
private Long id;
private String url;
private String appName;
private String syncType;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public String getAppName() {
	return appName;
}
public void setAppName(String appName) {
	this.appName = appName;
}
public String getSyncType() {
	return syncType;
}
public void setSyncType(String syncType) {
	this.syncType = syncType;
}



}
