package com.itgrids.partyanalyst.web.action;

import javax.servlet.ServletContext;
import org.apache.struts2.util.ServletContextAware;
import com.opensymphony.xwork2.ActionSupport;

public class FooterLinksAction extends ActionSupport implements ServletContextAware {

	private static final long serialVersionUID = -239741514327287325L;
	private String linkFrom;
	
	
	
	public String getLinkFrom() {
		return linkFrom;
	}

	public void setLinkFrom(String linkFrom) {
		this.linkFrom = linkFrom;
	}
	
	public void setServletContext(ServletContext arg0) {
		
	}
	
	public String execute()
	{
		return linkFrom;
	}

	

	
	
}
