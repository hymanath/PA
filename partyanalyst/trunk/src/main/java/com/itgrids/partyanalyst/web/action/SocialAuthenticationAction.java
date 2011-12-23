package com.itgrids.partyanalyst.web.action;

import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.RequestUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.brickred.socialauth.SocialAuthConfig;
import org.brickred.socialauth.SocialAuthManager;

import com.opensymphony.xwork2.ActionSupport;

public class SocialAuthenticationAction extends ActionSupport implements ServletRequestAware,ServletContextAware{

	private static final long serialVersionUID = 2366948100321158997L;
	private static final Logger log = Logger.getLogger(SocialAuthenticationAction.class);
	
	private HttpServletRequest request;
	private ServletContext context;
	private String id;      
	private SocialAuthManager socialAuthManager;
	private String redirectURL;
	private HttpSession session;

	public String getRedirectURL() {
		return redirectURL;
	}

	public void setRedirectURL(String redirectURL) {
		this.redirectURL = redirectURL;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SocialAuthManager getSocialAuthManager() {
		return socialAuthManager;
	}

	public void setSocialAuthManager(SocialAuthManager socialAuthManager) {
		this.socialAuthManager = socialAuthManager;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}
	
	public String execute()
	{
		session = request.getSession();
		try{
		
        InputStream in = SocialAuthenticationAction.class.getClassLoader()
                        .getResourceAsStream("oauth_consumer.properties");
        SocialAuthConfig conf = SocialAuthConfig.getDefault();
        conf.load(in);
        socialAuthManager = new SocialAuthManager();
        socialAuthManager.setSocialAuthConfig(conf);

        String returnToUrl = "http://www.partyanalyst.com/socialAuthSuccessAction.action";
                        
        redirectURL = socialAuthManager.getAuthenticationUrl(id, returnToUrl);
    	
        session.setAttribute("SocialAuthManager", socialAuthManager);
        log.debug("Redirecting to: " + redirectURL);
        
		return SUCCESS;
		}
		catch (Exception e) {
			return ERROR;
		}
	}

}
