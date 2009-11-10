package com.itgrids.partyanalyst.web.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.ILoginService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

public class LoginAction extends ActionSupport implements ServletContextAware, ServletRequestAware{

	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext context;
	private String userName = null;
	private String password = null;
	private ILoginService loginService;
	private String name = null;
	
	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	
	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}

	@RequiredStringValidator(key="requiredstring" ,shortCircuit=true)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String value) {
		userName = value;
	}

	@RequiredStringValidator(key="requiredstring" ,shortCircuit=true)
	public String getPassword() {
		return password;
	}
	public void setPassword(String value) {
		password = value;
	}

	public String execute() throws Exception {
		
		session = request.getSession();
		RegistrationVO regVO = loginService.checkForValidUser(userName, password);
		name = regVO.getFirstName() + " " + regVO.getLastName();
				
		if (regVO.getRegistrationID()==null) {
			session.setAttribute("loginStatus", "in");
			addActionError("Invalid user name or password! Please try again!");
			return ERROR;
			
			
		} else {

			session.setAttribute("USER",regVO);
			session.setAttribute("UserName", name);
			session.setAttribute("loginStatus", "out");
			return SUCCESS;
			
		}

	}

}