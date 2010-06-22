package com.itgrids.partyanalyst.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;

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
		param=request.getParameter("task");		
		
		try {
			jObj=new JSONObject(param);			
		} catch (ParseException e) {
			
			e.printStackTrace();
		}		
		String userName = jObj.getString("userName");
		String password = jObj.getString("password");
		
		loginStatus = "true";
		
		return Action.SUCCESS;
	}


	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}


	
	public void setServletContext(ServletContext context) {
		this.context = context;		
	}

}
