package com.itgrids.electoralconnect.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class HomePageAction extends ActionSupport implements ServletRequestAware{

private HttpServletRequest request;
private String passwordChanged;

public String getPasswordChanged() {
	return passwordChanged;
}

public void setPasswordChanged(String passwordChanged) {
	this.passwordChanged = passwordChanged;
}

public String execute() throws Exception {
	return SUCCESS;
}

@Override
public void setServletRequest(HttpServletRequest request) {
	this.request = request;
}
}
