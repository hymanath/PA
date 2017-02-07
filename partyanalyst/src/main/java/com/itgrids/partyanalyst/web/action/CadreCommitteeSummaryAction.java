package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CadreCommitteeMemberVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CadreCommitteeSummaryAction extends ActionSupport implements ServletRequestAware{
	
	private HttpServletRequest         			request;
	private HttpSession 						session;
	JSONObject jObj;
	private String 								task;
	private ICadreCommitteeService   		 	cadreCommitteeService;
	private List<CadreCommitteeMemberVO> cadreCommitteeMemberVOList;
	private EntitlementsHelper 					entitlementsHelper;
	private Long locationId;
	private String locationType;
	
	
	
	
	
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	public List<CadreCommitteeMemberVO> getCadreCommitteeMemberVOList() {
		return cadreCommitteeMemberVOList;
	}
	public void setCadreCommitteeMemberVOList(
			List<CadreCommitteeMemberVO> cadreCommitteeMemberVOList) {
		this.cadreCommitteeMemberVOList = cadreCommitteeMemberVOList;
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
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	
	public ICadreCommitteeService getCadreCommitteeService() {
		return cadreCommitteeService;
	}
	public void setCadreCommitteeService(
			ICadreCommitteeService cadreCommitteeService) {
		this.cadreCommitteeService = cadreCommitteeService;
	}
	
	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}
	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}
	public String execute()
	{

		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		boolean noaccess = false;
		if(regVO==null){
			return "input";
			
		}
		List<String> entitlements = null;
		if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
			entitlements = regVO.getEntitlements();
			if(!(entitlements.contains(IConstants.TDP_CADRE_SEARCH.trim()) || entitlements.contains("CADRE_COMMITTEE_MANAGEMENT".trim()) || entitlements.contains("TDP_COMMITTEE_STATE_DISTRICT_ACCESS".trim()) 
					&& entitlements.contains("COMMITTEE_MGT".trim()))){
				noaccess = true ;
			}
		/*if(!(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"CADRE_COMMITTEE_MANAGEMENT") || entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"TDP_COMMITTEE_STATE_DISTRICT_ACCESS"))
				&& !(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"COMMITTEE_MGT"))){
			noaccess = true ;
		}*/
		if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
			noaccess = false;
		}
		
		if(noaccess){
			return "error";
		}
	}	
		return Action.SUCCESS;
	}

	public String getCommitteeDetailsByStatus(){

		
		try{
			session = request.getSession();	
			RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			if(user != null){
			jObj = new JSONObject(getTask());
			List<Long> committeeEnrollmentIdsLst = new ArrayList<Long>();
			JSONArray committeeEnrollmentIds=jObj.getJSONArray("committeeEnrollmentId");
			if(committeeEnrollmentIds!=null &&  committeeEnrollmentIds.length()>0){
				for( int i=0;i<committeeEnrollmentIds.length();i++){
					committeeEnrollmentIdsLst.add(Long.valueOf(committeeEnrollmentIds.getString(i))); 
				}
			}
			if(jObj.getString("task").equalsIgnoreCase("memberCnt"))
			cadreCommitteeMemberVOList = cadreCommitteeService.getCommitteeDetailsByStatus(jObj.getLong("basicCommitteetypeId"),jObj.getString("status"),jObj.getLong("levelId"),user.getAccessValue(),committeeEnrollmentIdsLst);
			else if(jObj.getString("task").equalsIgnoreCase("memberInfo"))
				cadreCommitteeMemberVOList = cadreCommitteeService.getCommitteeMemberDetails(jObj.getLong("basicCommitteetypeId"),jObj.getLong("locationId"),jObj.getLong("levelId"),jObj.getString("status"),committeeEnrollmentIdsLst);
			else if(jObj.getString("task").equalsIgnoreCase("committeComplete"))
				cadreCommitteeMemberVOList = cadreCommitteeService.setCommitteConfirmation(jObj.getLong("basicCommitteetypeId"),jObj.getLong("locationId"),jObj.getLong("levelId"),committeeEnrollmentIdsLst);
			else if(jObj.getString("task").equalsIgnoreCase("deleterole"))
				cadreCommitteeMemberVOList = cadreCommitteeService.deleteCadreRole(jObj.getLong("tdpcommitteeMemberId"),committeeEnrollmentIdsLst);
			
			}
		}catch(Exception e){
			LOG.error("Exception occured in getCommitteeDetailsByStatus() At CadreCommitteeAction ",e);
		}
		
		return Action.SUCCESS;
	}
	

}
