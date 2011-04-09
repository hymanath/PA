package com.itgrids.partyanalyst.web.action;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class FooterLinksAction extends ActionSupport implements ServletContextAware {

	@Override
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public String execute()
	{
		
		return Action.SUCCESS;
	}
	
}
