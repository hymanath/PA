package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class ProfileManagePageAction extends ActionSupport implements ServletRequestAware{
	
	private static final long serialVersionUID = 2619726916593528832L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Long candidateld;
	
	
	
	
	
	public Long getCandidateld() {
		return candidateld;
	}

	public void setCandidateld(Long candidateld) {
		this.candidateld = candidateld;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public String execute()  {
		
		
		return SUCCESS;
	}

}
