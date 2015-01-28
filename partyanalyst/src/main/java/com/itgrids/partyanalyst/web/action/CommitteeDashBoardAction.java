package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CommitteeDashBoardAction extends ActionSupport {
	
	private HttpServletRequest         			request;
	private HttpSession 						session;
	private EntitlementsHelper 					entitlementsHelper;
	private JSONObject							jObj;
	private String 								task;
	private LocationWiseBoothDetailsVO          locationWiseBoothDetailsVO;
	private ICadreCommitteeService				cadreCommitteeService;
	private static final Logger         		LOG = Logger.getLogger(CommitteeDashBoardAction.class);
	
	
	public LocationWiseBoothDetailsVO getLocationWiseBoothDetailsVO() {
		return locationWiseBoothDetailsVO;
	}
	public void setLocationWiseBoothDetailsVO(
			LocationWiseBoothDetailsVO locationWiseBoothDetailsVO) {
		this.locationWiseBoothDetailsVO = locationWiseBoothDetailsVO;
	}
	public ICadreCommitteeService getCadreCommitteeService() {
		return cadreCommitteeService;
	}
	public void setCadreCommitteeService(
			ICadreCommitteeService cadreCommitteeService) {
		this.cadreCommitteeService = cadreCommitteeService;
	}
	public JSONObject getjObj() {
		return jObj;
	}
	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
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
	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}
	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public String execute(){
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		boolean noaccess = false;
		if(regVO==null){
			return "input";
		}if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"CADRE_COMMITTEE_MANAGEMENT")){
			noaccess = true ;
		}
		if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
			noaccess = false;
		}
		
		if(noaccess){
			return "error";
		}
		
		return Action.SUCCESS;
	}
	
	
	public String gettingDashBoardLocationWiseDetailsAction(){
		try{
			jObj = new JSONObject(getTask());
			org.json.JSONArray levelIdss = jObj.getJSONArray("designationArr");
			
			List<Long> levelIds = new ArrayList<Long>();
			String state =jObj.getString("state");
			
			if(levelIdss !=null && levelIdss.length() >0){
				for(int i=0; i<levelIdss.length(); i++ ){
					levelIds.add((long) levelIdss.length());
				}
			}
			
			locationWiseBoothDetailsVO = cadreCommitteeService.getTotalCommitteesPanchayatLevelByState(state,levelIds);
			locationWiseBoothDetailsVO = cadreCommitteeService.getMembersCountByLocation(state,levelIds);
			locationWiseBoothDetailsVO = cadreCommitteeService.getStartedCommitteesCountByLocation(state,levelIds);
			
		}catch(Exception e){
			LOG.error("Exception Occured In gettingDashBoardLocationWiseDetails method "+e);
		}
		return Action.SUCCESS;
	}
}
