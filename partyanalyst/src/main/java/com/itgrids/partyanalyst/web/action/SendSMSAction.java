package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.LocationwiseProblemStatusInfoVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ISmsService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.ActionSupport;

public class SendSMSAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private String task = null;
	JSONObject jObj = null;
	private static final Logger log = Logger.getLogger(SendSMSAction.class);
	private ISmsService smsCountrySmsService;
	private Long remainingSms;
	
	
	public ISmsService getSmsCountrySmsService() {
		return smsCountrySmsService;
	}

	public void setSmsCountrySmsService(ISmsService smsCountrySmsService) {
		this.smsCountrySmsService = smsCountrySmsService;
	}

	public Long getRemainingSms() {
		return remainingSms;
	}

	public void setRemainingSms(Long remainingSms) {
		this.remainingSms = remainingSms;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.setRequest(request);
		
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getTask() {
		return task;
	}

	public JSONObject getJObj() {
		return jObj;
	}

	public void setJObj(JSONObject obj) {
		jObj = obj;
	}	


	public String execute() throws Exception{
			
			log.debug("In execute of SendSMS Action");
			String message = jObj.getString("message");
			JSONArray cellNumbers = jObj.getJSONArray("numbers");
			String smsMsgs[] = new String[cellNumbers.length()];
			for(int i=0; i<cellNumbers.length(); i++){
				smsMsgs[i] = (String)cellNumbers.get(i);
			}
			HttpSession session = request.getSession();
			RegistrationVO userVo = (RegistrationVO)session.getAttribute("USER");
			Long userID = userVo.getRegistrationID();
			
			remainingSms = smsCountrySmsService.getRemainingSmsLeftForUser(userID);
			smsCountrySmsService.sendSms(message, true,userID,IConstants.Influencing_People,smsMsgs);
			return SUCCESS;
		}
	
	

}
