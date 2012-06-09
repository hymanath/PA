package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.utils.IConstants;

public class ChangePasswordAction implements ServletRequestAware ,ServletResponseAware{

	private HttpServletRequest request;
	private HttpServletResponse response;
	HttpSession session;
	
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String execute()
	{
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute(IConstants.USER);
		
		return "SUCCESS";
	}
}
