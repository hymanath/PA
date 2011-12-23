package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.brickred.socialauth.AuthProvider;
import org.brickred.socialauth.Contact;
import org.brickred.socialauth.Profile;
import org.brickred.socialauth.SocialAuthManager;

import com.opensymphony.xwork2.ActionSupport;

public class SocialAuthSuccessAction extends ActionSupport implements ServletRequestAware,ServletContextAware{

	private static final long serialVersionUID = 4043816144609356125L;
	private static final Logger log = Logger.getLogger(SocialAuthSuccessAction.class);
	private HttpServletRequest request;
	private ServletContext context;
	private SocialAuthManager socialAuthManager;
	private List<Contact> contactsList;
	private Profile profile;
	private HttpSession session;
	
	public List<Contact> getContactsList() {
		return contactsList;
	}

	public void setContactsList(List<Contact> contactsList) {
		this.contactsList = contactsList;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
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
		socialAuthManager = (SocialAuthManager)session.getAttribute("SocialAuthManager");
		if (socialAuthManager != null)
		{
			log.debug("socialAuthManager object is created");
            contactsList = new ArrayList<Contact>(0);
            try {
                Map<String, String> paramsMap = new HashMap<String, String>();
                Map<String,String[]> params = request.getParameterMap();
                
                for (Map.Entry<String, String[]> entry : params.entrySet()) {
                        String key = entry.getKey();
                        String values[] = entry.getValue();
                        paramsMap.put(key, values[0].toString()); // Only 1 value is
                }
                
                AuthProvider provider = socialAuthManager.connect(paramsMap);
                log.debug(provider+"Provider object is created");
                profile = provider.getUserProfile();
                contactsList = provider.getContactList();
                log.debug("Profile & Contact objs created");
                if (contactsList != null && contactsList.size() > 0) {
                    for (Contact p : contactsList) {
                        if (StringUtils.isEmpty(p.getFirstName())
                                        && StringUtils.isEmpty(p.getLastName())) {
                                p.setFirstName(p.getDisplayName());
                                log.debug(p.getDisplayName());
                        }
                    }
                }
                return SUCCESS;   
            } catch (Exception e)
            {
                   return ERROR;
            }
         }
		return ERROR;
	 }

}
