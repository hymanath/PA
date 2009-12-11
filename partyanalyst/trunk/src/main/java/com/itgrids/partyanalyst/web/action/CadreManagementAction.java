package com.itgrids.partyanalyst.web.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CadreManagementAction extends ActionSupport implements ServletRequestAware,ServletContextAware{

	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public String execute() throws Exception{
		
		System.out.println("In execute of Cadre Management Action ********");
		
		return Action.SUCCESS;
	}
}
