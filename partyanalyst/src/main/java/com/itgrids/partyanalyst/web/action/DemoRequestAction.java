package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.DemoRequestVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IMarketingManagementService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class DemoRequestAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = -7208486615493841167L;
	private static final Logger LOG = Logger.getLogger(DemoRequestAction.class);
	private HttpServletRequest request;
	private HttpSession session;
	private String task;
	private JSONObject jObj;
	private IMarketingManagementService marketingManagementService;
	private Long demoRequestId;
	private DemoRequestVO demoRequestVO;
	private List<DemoRequestVO> demoRequestVOList;
	private ResultStatus resultStatus;
	private List<SelectOptionVO> selectOptionVOList;
	private EntitlementsHelper entitlementsHelper;
	 
	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
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

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public JSONObject getjObj() {
		return jObj;
	}

	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}
	

	public IMarketingManagementService getMarketingManagementService() {
		return marketingManagementService;
	}

	public void setMarketingManagementService(
			IMarketingManagementService marketingManagementService) {
		this.marketingManagementService = marketingManagementService;
	}

	public Long getDemoRequestId() {
		return demoRequestId;
	}

	public void setDemoRequestId(Long demoRequestId) {
		this.demoRequestId = demoRequestId;
	}

	public DemoRequestVO getDemoRequestVO() {
		return demoRequestVO;
	}

	public void setDemoRequestVO(DemoRequestVO demoRequestVO) {
		this.demoRequestVO = demoRequestVO;
	}

	public List<DemoRequestVO> getDemoRequestVOList() {
		return demoRequestVOList;
	}

	public void setDemoRequestVOList(List<DemoRequestVO> demoRequestVOList) {
		this.demoRequestVOList = demoRequestVOList;
	}
	

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
    
	public List<SelectOptionVO> getSelectOptionVOList() {
		return selectOptionVOList;
	}

	public void setSelectOptionVOList(List<SelectOptionVO> selectOptionVOList) {
		this.selectOptionVOList = selectOptionVOList;
	}

	public String execute()throws Exception
	{
		HttpSession session = request.getSession();
		session = request.getSession();
		
		if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.ASPIRANT_DEMO_REQUESTS_VIEW))
			return INPUT;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.ASPIRANT_DEMO_REQUESTS_VIEW))
			return ERROR;
		
		selectOptionVOList = marketingManagementService.getDemoRequestActionTypes();
		return SUCCESS;
	}
	
	public String ajaxHandler()
	{
		session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		
		try{
		Long userId = 0L;
		if(user != null)
		 userId = user.getRegistrationID();
		
		jObj = new JSONObject(getTask());
		if(jObj.getString("task").equalsIgnoreCase("getDemoRequestData"))
			demoRequestVOList = marketingManagementService.getDemoRequestData();
		
		else if(jObj.getString("task").equalsIgnoreCase("getSelectedDemoRequestData"))
		 demoRequestVO = marketingManagementService.getSelectedDemoRequestData(jObj.getLong("demoRequestId"));
		
		else if(jObj.getString("task").equalsIgnoreCase("saveDemoRequestActionsData"))
		 resultStatus = marketingManagementService.saveDemoRequestActionsData(jObj.getLong("demoRequestId"),jObj.getString("type"),jObj.getString("content"),jObj.getString("response"),userId);
		
		else if(jObj.getString("task").equalsIgnoreCase("deleteDemoRequest"))
		 resultStatus = marketingManagementService.deleteDemoRequestData(jObj.getLong("demoRequestId"));
		 
		else if(jObj.getString("task").equalsIgnoreCase("upDateDemoRequestDetails"))
		{
		  DemoRequestVO demoRequestVO = new DemoRequestVO();
		  demoRequestVO.setName(jObj.getString("name"));
		  demoRequestVO.setConstituency(jObj.getString("Constituency"));
		  demoRequestVO.setMobileNo(jObj.getString("mobileNo"));
		  demoRequestVO.setEmail(jObj.getString("email"));
		  demoRequestVO.setId(jObj.getLong("demoRequestId"));
		  demoRequestVO.setAssignedTo(jObj.getString("assignedTo"));
		  demoRequestVO.setUserId(userId);
	     resultStatus = marketingManagementService.upDateDemoRequestDetails(demoRequestVO);
		}
			
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error(" Exception Occured in ajaxHandler() method, Exception - "+e);
		}
		return Action.SUCCESS;
	}
	
	
}
