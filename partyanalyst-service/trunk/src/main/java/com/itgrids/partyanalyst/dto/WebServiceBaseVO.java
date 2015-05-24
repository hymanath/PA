package com.itgrids.partyanalyst.dto;

public class WebServiceBaseVO {
private Long id;
private String url;
private String appName;
private String statusSyncType;
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
public String getStatusSyncType() {
	return statusSyncType;
}
public void setStatusSyncType(String statusSyncType) {
	this.statusSyncType = statusSyncType;
}



}
