package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.opensymphony.xwork2.Action;

public class CadreMapAction  implements ServletRequestAware {

private static Logger LOG = Logger.getLogger(CadreMapAction.class);
	
	private HttpServletRequest request;
	
	private Long id;
	private String type;
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public String execute()
	{
			
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO==null){
				return "input";
			}
			return Action.SUCCESS;
	}
}
