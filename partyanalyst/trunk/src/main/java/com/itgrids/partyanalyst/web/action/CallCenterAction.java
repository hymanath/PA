package com.itgrids.partyanalyst.web.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.itgrids.partyanalyst.dto.CallCenterVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.ICallCenterService;
import com.itgrids.partyanalyst.utils.IConstants;


public class CallCenterAction extends ActionSupport implements ServletRequestAware{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2619726916593528832L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private ICallCenterService callCenterService;
	JSONObject jObj = null;
	private String task;
	private List<ProblemBeanVO> problemBeanVO;
	private CallCenterVO callCenterVO ;
	private EntitlementsHelper entitlementsHelper;
	
	
	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}


	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}


	public void setCallCenterService(ICallCenterService callCenterService) {
		this.callCenterService = callCenterService;
	}


	public ICallCenterService getCallCenterService() {
		return callCenterService;
	}


	public void setTask(String task) {
		this.task = task;
	}


	public String getTask() {
		return task;
	}


	public void setProblemBeanVO(List<ProblemBeanVO> problemBeanVO) {
		this.problemBeanVO = problemBeanVO;
	}


	public List<ProblemBeanVO> getProblemBeanVO() {
		return problemBeanVO;
	}


	public void setCallCenterVO(CallCenterVO callCenterVO) {
		this.callCenterVO = callCenterVO;
	}


	public CallCenterVO getCallCenterVO() {
		return callCenterVO;
	}


	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}


	public HttpServletResponse getResponse() {
		return response;
	}


	public String execute(){
		
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		
		if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.CALL_CENTER_ENTITLEMENT))
			return IConstants.NOT_LOGGED_IN;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.CALL_CENTER_ENTITLEMENT))
			return ERROR;
		
		return SUCCESS;
	}
	
	public String ajaxCallHandler(){
		
		HttpSession session = request.getSession();
		
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");	
		
		 Long userId ;
		try {
			jObj = new JSONObject(getTask());
			if(jObj.getString("task").equalsIgnoreCase("problemSearch")){
				String name = jObj.getString("name");
				String refNum = jObj.getString("refNum");
				String mobileNum = jObj.getString("mobileNum");
				String emailId = jObj.getString("emailId");
				String frmDate = jObj.getString("fromDate");
				String endDate = jObj.getString("endDate");
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date fromDate = null ;
				Date toDate = null;
				if(!frmDate.equalsIgnoreCase("")){
					 fromDate =dateFormat.parse(frmDate);
				}
				if(!endDate.equalsIgnoreCase("")){
				 toDate =dateFormat.parse(endDate);
				}
				userId =  user.getRegistrationID();
				CallCenterVO callCenterVO = new CallCenterVO();
				callCenterVO.setName(name);
				callCenterVO.setEmailId(emailId);
				callCenterVO.setMobileNum(mobileNum);
				callCenterVO.setRefNum(refNum);
				callCenterVO.setFromDate(fromDate);
				callCenterVO.setToDate(toDate);
				callCenterVO.setUserId(userId);
				
				problemBeanVO = callCenterService.getProblemDetails(callCenterVO);
			}
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		return SUCCESS;
		
	}
}
