package com.itgrids.cardprint.action;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONObject;

import com.itgrids.cardprint.dto.ResultStatus;
import com.itgrids.cardprint.dto.UserVO;
import com.itgrids.cardprint.service.IUserService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{

	private static final Logger LOG = Logger.getLogger(LoginAction.class);
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ServletContext context;
	private String task;
	private JSONObject jobj;
	private HttpSession session;
	private IUserService userService;
	private ResultStatus resultStatus;
	private UserVO userDetails;
	
	public IUserService getUserService() {
		return userService;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		
	}
	public void setServletContext(ServletContext context) {
		this.context = context;
	}

 	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public JSONObject getJobj() {
		return jobj;
	}
	public void setJobj(JSONObject jobj) {
		this.jobj = jobj;
	}
	
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	
	public UserVO getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(UserVO userDetails) {
		this.userDetails = userDetails;
	}
	
	public String execute()
	{
		
		try{
			session = request.getSession();
			return "success";
		}
		catch (Exception e) {
			 LOG.error(e);
		}
		return Action.SUCCESS;
	}
	
	public String validateUserLogin()
	{
		try{
			resultStatus = new ResultStatus();
			jobj = new JSONObject(getTask());
			session = request.getSession();
		     String uniqueyKey = (String) session.getAttribute("uniqueKey");
		     userDetails = userService.validateUserLogin(jobj.getString("username"), jobj.getString("password"),jobj.getString("key"));
			if (userDetails == null || userDetails.getUserId() == null)
			{
				
				session.setAttribute("loginStatus", "out");
				resultStatus.setResultCode(1);
				return Action.SUCCESS;
				
			}	
			if(userDetails != null && userDetails.getUserId() != null)
			{
				
				session.setAttribute("loginStatus", "in");
				session.setAttribute("USER", userDetails);
				//resultStatus.setRedirectUrl(redirectToPage());
				resultStatus.setResultCode(0);
			}
			
			
		}catch(Exception e)
		{
			LOG.error(e);
		}
		return Action.SUCCESS;
	}
	
	
	public String redirectToPage()
	{
		String url = "";
		session = request.getSession();
	    UserVO userDetails = (UserVO) session.getAttribute("USER");
	    
		/*if(userDetails.getEntitlements().contains(IConstants.CLAIM_DOCUMENT_UPLOAD_DASHBOARD_REDIRECT))
			url = "claimDocumentUploadDashBoardAction.action";
		else if(userDetails.getEntitlements().contains(IConstants.CLAIM_DOCUMENT_VERIFIER_DASHBOARD_REDIRECT))
			url = "claimDocumentVerifierDashBoardAction.action";
		else if(userDetails.getEntitlements().contains(IConstants.SUPPORT_CENTER_CALLER_REDIRECT))
			url = "claimStatusSearchAction.action";
		else if(userDetails.getEntitlements().contains(IConstants.REGISTRATION_USER))
			url = "insuranceSearchAction.action";
		else if(userDetails.getEntitlements().contains(IConstants.WEB_MONITERING_VERIFIER_REDIRECT))
			url = "primaryVerificationStatusAction.action";
		else if(userDetails.getEntitlements().contains(IConstants.INSURANCE_COMPANY_OFFICER_DASHBOARD_REDIRECT))
			url = "insuranceOfficerDashBoardAction.action";
		else if(userDetails.getEntitlements().contains(IConstants.LABOR_DEPT_OFFICER_DASHBOARD_REDIRECT))
			url = "labourdeptOfficerDashBoardAction.action";*/
		
		return url;
		
	}
	
	public String logout(){
		try{
	       session = request.getSession();
	       session.setAttribute("loginStatus", "out");
	       session.removeAttribute("USER");
	       session.invalidate();
		   return Action.SUCCESS;
		}catch(Exception e){
			LOG.error("Exception occured in logout() in LoginAction class"+e);
		}
		return Action.SUCCESS;
	}
	
}
