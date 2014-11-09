package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CadreRegistrationAmountReportAction extends ActionSupport{

	private static final Logger         		LOG = Logger.getLogger(CadreRegistrationAmountReportAction.class);
	private HttpSession 						session;
	private HttpServletRequest                  request;

	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public static Logger getLog() {
		return LOG;
	}

	public String execute(){
		try {
			LOG.info("Entered into execute method in CadreRegistrationAmountReportAction Action");
			session = request.getSession();
			RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			
			if(user == null)
				return Action.INPUT;
			
		} catch (Exception e) {
			LOG.info("Entered into execute method in  CadreRegistrationAmountReportAction Action");
		}
		return Action.SUCCESS;
	}
	public String getCadreRegAmountReport(){
		try{
			request = ServletActionContext.getRequest();
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			String reportValue = request.getParameter("reportValue");
			
		}
		catch(Exception e){
			LOG.info("Entered into execute method in  CadreRegistrationAmountReportAction Action");
		}
		return Action.SUCCESS;
	}
	
}
