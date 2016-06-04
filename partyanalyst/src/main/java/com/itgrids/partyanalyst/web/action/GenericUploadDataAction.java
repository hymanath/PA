/**
 * 
 */
package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Sai Krishna
 *
 */
public class GenericUploadDataAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware, ServletContextAware {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ServletContext context;
	private HttpSession session; 
	
	private final static Logger LOG = Logger.getLogger(GenericUploadDataAction.class);
	
	private EntitlementsHelper entitlementsHelper;
	
	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public ServletContext getContext() {
		return context;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(javax.servlet.http.HttpServletRequest)
	 */
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletResponseAware#setServletResponse(javax.servlet.http.HttpServletResponse)
	 */
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.util.ServletContextAware#setServletContext(javax.servlet.ServletContext)
	 */
	public void setServletContext(ServletContext context) {
		this.context = context;
	}
	
	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public String execute(){
		
		if(LOG.isDebugEnabled())
			LOG.debug("Started Executing GenericUpload Process ..");
		
		session = request.getSession();
		 RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		 List<String> entitlements = null;
			if(user.getEntitlements() != null && user.getEntitlements().size()>0){
				entitlements = user.getEntitlements();
				if(user == null && !entitlements.contains(IConstants.CADRE_UPLOAD_ENTITLEMENT)){
					return INPUT;
				}
				if(!entitlements.contains(IConstants.CADRE_UPLOAD_ENTITLEMENT))
					return ERROR;
		/*if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.CADRE_UPLOAD_ENTITLEMENT))
			return INPUT;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.CADRE_UPLOAD_ENTITLEMENT))
			return ERROR;*/
		
			}
		return Action.SUCCESS;
	}

}
