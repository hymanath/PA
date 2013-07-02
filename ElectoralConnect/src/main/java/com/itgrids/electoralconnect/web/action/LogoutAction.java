package com.itgrids.electoralconnect.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class LogoutAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	private HttpServletResponse responce;
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public String execute()
	{
		HttpSession session = request.getSession();
		session.invalidate();
		
		try{
			responce.sendRedirect("homePage.action");
		}catch (Exception e) {
		}
		return Action.SUCCESS;
	}

}
