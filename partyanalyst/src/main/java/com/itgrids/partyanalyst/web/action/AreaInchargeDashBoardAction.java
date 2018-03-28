package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.AreaInchargeVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.IAreaInchargeDashBoardService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class AreaInchargeDashBoardAction extends ActionSupport  implements ServletRequestAware{
     
	private static final Logger LOG = Logger.getLogger(AreaInchargeDashBoardAction.class);
	
	//private HttpServletRequest  request;
	private HttpServletRequest  request;
	private HttpSession session;
	
	private JSONObject jObj;
	private String task;
	private ResultStatus resultStatus;
	private AreaInchargeVO inchargeVO;
	private IAreaInchargeDashBoardService areaInchargeDashBoardService;
	
	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}


	public String execute(){
		return Action.SUCCESS;
	}


	public JSONObject getjObj() {
		return jObj;
	}

	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}
	
	public AreaInchargeVO getInchargeVO() {
		return inchargeVO;
	}

	public void setInchargeVO(AreaInchargeVO inchargeVO) {
		this.inchargeVO = inchargeVO;
	}
	
	public IAreaInchargeDashBoardService getAreaInchargeDashBoardService() {
		return areaInchargeDashBoardService;
	}

	public void setAreaInchargeDashBoardService(
			IAreaInchargeDashBoardService areaInchargeDashBoardService) {
		this.areaInchargeDashBoardService = areaInchargeDashBoardService;
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

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String getAreaInchargeDetails(){
		try{
			
			/*RegistrationVO regVO =(RegistrationVO) request.getSession().getAttribute("USER");
			Long userId=0l;
			if(regVO!=null){
				userId = regVO.getRegistrationID();
			}*/
			jObj = new JSONObject(getTask());
			inchargeVO = areaInchargeDashBoardService.getAreaInchargeDetails(jObj.getString("voterId"),jObj.getString("mobileNo"),jObj.getString("memberShipId"));
			
		}catch (Exception e) {
			LOG.error("Entered into getAreaInchargeDetails Action",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String savingAssigningBooths(){
		try{
			
			jObj = new JSONObject(getTask());
			JSONArray boothIdsArr = jObj.getJSONArray("boothIds");  
			List<Long> boothIdsList = new ArrayList<Long>();
			if(boothIdsArr != null && boothIdsArr.length() > 0){
				for (int i = 0; i < boothIdsArr.length(); i++){
					boothIdsList.add(Long.parseLong(boothIdsArr.getString(i)));          
				}  
			}
			resultStatus = areaInchargeDashBoardService.savingAssigningBooths(jObj.getLong("cadreId"),boothIdsList);
			
		}catch (Exception e) {
			LOG.error("Entered into savingAssigningBooths Action",e);
		}
		
		return Action.SUCCESS;
	}
	public String getAssignedAndUnAssignedBooths(){
		try{
			
			jObj = new JSONObject(getTask());
			inchargeVO = areaInchargeDashBoardService.getAssignedAndUnAssignedBooths();
			
		}catch (Exception e) {
			LOG.error("Entered into getAssignedAndUnAssignedBooths Action",e);
		}
		
		return Action.SUCCESS;
	}
	public String editAssignedInchargeDetails(){
		try{
			
			jObj = new JSONObject(getTask());
			JSONArray boothIdsArr = jObj.getJSONArray("boothIds");  
			List<Long> boothIdsList = new ArrayList<Long>();
			if(boothIdsArr != null && boothIdsArr.length() > 0){
				for (int i = 0; i < boothIdsArr.length(); i++){
					boothIdsList.add(Long.parseLong(boothIdsArr.getString(i)));          
				}  
			}
			inchargeVO = areaInchargeDashBoardService.editAssignedInchargeDetails(jObj.getLong("cadreId"),boothIdsList);
			
		}catch (Exception e) {
			LOG.error("Entered into editAssignedInchargeDetails Action",e);
		}
		
		return Action.SUCCESS;
	}
}
