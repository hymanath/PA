package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.EmailDetailsVO;
import com.itgrids.partyanalyst.dto.QuickRequestVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.impl.MailService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class SendMailToAdminAction extends ActionSupport implements ServletRequestAware 
 {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
//Instance Variables
	 	
	    private HttpServletRequest request;
	    private String userName;
	    private String emailId;
	    private String mobileNumber;
	    private String userRequirement;
	    private String text;
	    JSONObject jObj;
	    private String task = null;
	    QuickRequestVO quickRequestVO=new QuickRequestVO();
	    private  MailService mailService;
	    ResultStatus result = new ResultStatus();

		public String getUserName() {
			return quickRequestVO.getUserName();
		}

		public void setUserName(String userName) {
			quickRequestVO.setUserName(userName);
			
		}

		public String getEmailId() {
			return quickRequestVO.getEmailId();
		}

		public void setEmailId(String emailId) {
			quickRequestVO.setEmailId(emailId);
		}

		public String getMobileNumber() {
			return quickRequestVO.getMobileNumber();
		}

		public void setMobileNumber(String mobileNumber) {
			quickRequestVO.setMobileNumber(mobileNumber);
		}

		public String getUserRequirement() {
			return quickRequestVO.getUserRequirement();
		}
		public void setUserRequirement(String userRequirement) {
			quickRequestVO.setUserRequirement(userRequirement);
		}
		public void setRequest(HttpServletRequest request) {
			this.request = request;
		}

		public HttpServletRequest getRequest() {
			return request;
		}

		public JSONObject getJobj() {
			return jObj;
		}

		public void setJobj(JSONObject jObj) {
			this.jObj = jObj;
		}

		public void setTask(String task) {
			this.task = task;
		}

		public String getTask() {
			return task;
		}

		public ResultStatus getResult() {
			return result;
		}

		public void setResult(ResultStatus result) {
			this.result = result;
		}

		public MailService getMailService() {
			return mailService;
		}

		public void setMailService(MailService mailService) {
			this.mailService = mailService;
		}
		public void setServletRequest(HttpServletRequest request) {
			// TODO Auto-generated method stub
			this.request = request;
			
		}
		public String execute() throws Exception{
			
			try {
				jObj = new JSONObject(getTask());
				
				quickRequestVO.setUserName(jObj.getString("name"));
				quickRequestVO.setEmailId(jObj.getString("email"));
				quickRequestVO.setMobileNumber(jObj.getString("mobile"));
				quickRequestVO.setUserRequirement(jObj.getString("requirement"));			
				String requestURL= request.getRequestURL().toString();
				String requestFrom = "";
				if(requestURL.contains("www.partyanalyst.com"))
					requestFrom = IConstants.SERVER;
				else
					requestFrom = IConstants.LOCALHOST;
				result = mailService.sendQuickRequestEmailToAdmin(quickRequestVO,requestFrom);
				
			}
			catch (ParseException e) {
				
				e.printStackTrace();
			}
			
		    return SUCCESS;
		}
		
		public String sendMailToAdminGroup()
		{
			
			try{
				jObj = new JSONObject(getTask());
				EmailDetailsVO emailDetailsVo= new EmailDetailsVO();
				emailDetailsVo.setCandidateName(jObj.getString("name"));
				emailDetailsVo.setEmail(jObj.getString("email"));
				emailDetailsVo.setMobile(jObj.getString("mobileNO"));
				emailDetailsVo.setConstituencyName(jObj.getString("constituencyName"));
				String requestURL= request.getRequestURL().toString();
				String requestFrom = "";
				if(requestURL.contains("partyanalyst.com"))
					requestFrom = IConstants.SERVER;
				else
					requestFrom = IConstants.LOCALHOST;
				result = mailService.sendEmailToAdminGroup(emailDetailsVo,requestFrom);
				
			}
			catch(Exception e)
			{
				e.printStackTrace();	
			}
			return Action.SUCCESS;
		}

		
		
		
 

	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    

}