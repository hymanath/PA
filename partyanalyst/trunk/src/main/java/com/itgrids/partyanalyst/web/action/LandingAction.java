package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.ILoginService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class LandingAction extends ActionSupport implements ServletRequestAware,ServletContextAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(LandingAction.class);
	private HttpServletRequest request;	
	private ServletContext context;
	JSONObject jObj;
	private String loginStatus;
	private ILoginService loginService;
	private RegistrationVO registrationVO;
	private HttpSession session;	
	private String name = null;
	private String src = null;
	
	public RegistrationVO getRegistrationVO() {
		return registrationVO;
	}

	public void setRegistrationVO(RegistrationVO registrationVO) {
		this.registrationVO = registrationVO;
	}

	public ILoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	public JSONObject getJObj() {
		return jObj;
	}

	public void setJObj(JSONObject obj) {
		jObj = obj;
	}

	public String execute() throws Exception
	{
        return SUCCESS;
    }
	
	public String checkUserLogin() throws Exception
	{
		String param=null;		
		param = request.getParameter("task");		
		session = request.getSession();
		
		try {
			jObj=new JSONObject(param);			
		} catch (ParseException e) {
			
			e.printStackTrace();
		}

		String userName = jObj.getString("userName");
		String password = jObj.getString("password");
		
		registrationVO = loginService.checkForValidUser(userName, password);
		
		name = registrationVO.getFirstName();
		
		if (registrationVO.getRegistrationID()==null)
		{
			session.setAttribute("loginStatus", "in");
			loginStatus = "false";		
		}else
		{
			int hiden = 0;
			registrationVO.setUserStatus(IConstants.PARTY_ANALYST_USER);
			session.setAttribute("loginStatus", "out");
			session.setAttribute("USER",registrationVO);
			session.setAttribute("UserName", name);
			session.setAttribute("UserType", "PartyAnalyst");
			session.setAttribute("HiddenCount", hiden);
			loginStatus = "true";
			
		}
		
		return Action.SUCCESS;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}

	public void setServletContext(ServletContext context) {
		this.context = context;		
	}

}
