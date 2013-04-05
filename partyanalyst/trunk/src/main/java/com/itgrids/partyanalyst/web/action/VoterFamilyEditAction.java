package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class VoterFamilyEditAction extends ActionSupport implements ServletRequestAware{
	private HttpServletRequest request;
	private HttpSession session;
	
	private String editDetails;
	
	private Long id;
	
	private String hno;
	
	private Long publicationDateId;
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getHno() {
		return hno;
	}


	public void setHno(String hno) {
		this.hno = hno;
	}


	public Long getPublicationDateId() {
		return publicationDateId;
	}


	public void setPublicationDateId(Long publicationDateId) {
		this.publicationDateId = publicationDateId;
	}


	public String getEditDetails() {
		return editDetails;
	}


	public void setEditDetails(String editDetails) {
		this.editDetails = editDetails;
	}


	public HttpServletRequest getRequest() {
		return request;
	}


	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}


	public HttpSession getSession() {
		return session;
	}


	public void setSession(HttpSession session) {
		this.session = session;
	}


	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	
	public String execute() throws Exception
	{
		
		
		return SUCCESS;
		
	}
	

}
