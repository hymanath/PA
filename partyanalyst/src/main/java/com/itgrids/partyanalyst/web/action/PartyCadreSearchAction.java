package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CadreVo;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class PartyCadreSearchAction extends ActionSupport implements ServletRequestAware {
	
	private static final Logger LOG = Logger.getLogger(PartyCadreSearchAction.class);
	private HttpServletRequest request;
	private CadreManagementService cadreManagementService;
	
	private List<SelectOptionVO> constituencysList;
	private Long electionscopeId;
	private Long stateId;
	private List<SelectOptionVO> panchayatList,committeLevels;
	JSONObject jObj = null;
	private String task = null;
	private List<CadreVo> vo,cadreDetails,locationDetails;
	private List<GenericVO> committeesLevelValue,committee;
	private EntitlementsHelper entitlementsHelper;
	
	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}
	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}
	public List<CadreVo> getLocationDetails() {
		return locationDetails;
	}
	public void setLocationDetails(List<CadreVo> locationDetails) {
		this.locationDetails = locationDetails;
	}
	public List<GenericVO> getCommittee() {
		return committee;
	}
	public void setCommittee(List<GenericVO> committee) {
		this.committee = committee;
	}
	public List<GenericVO> getCommitteesLevelValue() {
		return committeesLevelValue;
	}
	public void setCommitteesLevelValue(List<GenericVO> committeesLevelValue) {
		this.committeesLevelValue = committeesLevelValue;
	}
	public List<CadreVo> getCadreDetails() {
		return cadreDetails;
	}
	public void setCadreDetails(List<CadreVo> cadreDetails) {
		this.cadreDetails = cadreDetails;
	}
	public List<CadreVo> getVo() {
		return vo;
	}
	public void setVo(List<CadreVo> vo) {
		this.vo = vo;
	}
	public List<SelectOptionVO> getCommitteLevels() {
		return committeLevels;
	}
	public void setCommitteLevels(List<SelectOptionVO> committeLevels) {
		this.committeLevels = committeLevels;
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
	public List<SelectOptionVO> getPanchayatList() {
		return panchayatList;
	}
	public void setPanchayatList(List<SelectOptionVO> panchayatList) {
		this.panchayatList = panchayatList;
	}
	public Long getElectionscopeId() {
		return electionscopeId;
	}
	public void setElectionscopeId(Long electionscopeId) {
		this.electionscopeId = electionscopeId;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public List<SelectOptionVO> getConstituencysList() {
		return constituencysList;
	}
	public void setConstituencysList(List<SelectOptionVO> constituencysList) {
		this.constituencysList = constituencysList;
	}
	public CadreManagementService getCadreManagementService() {
		return cadreManagementService;
	}
	public void setCadreManagementService(
			CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
	}
	
	
	
	public String execute(){
		
		HttpSession session = request.getSession();
		RegistrationVO regVO =(RegistrationVO) session.getAttribute("USER");
		if(regVO == null)
		{
			return Action.INPUT;
		}
		List<String> entitlements = null;
		if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
			entitlements = regVO.getEntitlements();
			if(regVO == null && !entitlements.contains(IConstants.PARTY_CADRE_SEARCH)){
				return Action.INPUT;
			}
			if(!entitlements.contains(IConstants.PARTY_CADRE_SEARCH)){
				return Action.ERROR;
			}
		/*if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.PARTY_CADRE_SEARCH))
			return INPUT;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.PARTY_CADRE_SEARCH))
			return ERROR;*/
		}
		return SUCCESS;
	}

	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
		}
	public String getAllConstituencys() throws Exception
	{
		constituencysList =cadreManagementService.getAllConstituencys(electionscopeId,stateId);
				try{
						if(constituencysList!=null && constituencysList.size()>0){
					
								SelectOptionVO selectOptionVO = new SelectOptionVO();
								selectOptionVO.setId(0l);
								selectOptionVO.setName("Select Constituency");
								constituencysList.add(0,selectOptionVO);
							}
						return Action.SUCCESS;
					}
		catch(Exception e)
		{
			LOG.error("Exception Rised In getAllConstituencys() in PartyCadreSearchAction class",e);
		}
		return Action.SUCCESS;
	}
	public String getAllPanchayatAndMuncipalitie() throws Exception
	{
		
		try{
			
			jObj =new JSONObject(getTask());
			panchayatList=cadreManagementService.getAllPanchayatAndMuncipalities(jObj.getLong("constituencyId"));

				return Action.SUCCESS;
		}
		catch(Exception e)
		{
			LOG.error("Exception Rised In getAllPanchayatAndMuncipalitie() in PartyCadreSearchAction class",e);
		}
		return Action.SUCCESS;
	}
	
	
	public String getCommitteeLevels() 
	{
		
		try{
			
			jObj =new JSONObject(getTask());
			committeLevels=cadreManagementService.getCommitteeLevels();
			return Action.SUCCESS;
		}
		catch(Exception e)
		{
			LOG.error("Exception Rised In getCommitteeLevels() in PartyCadreSearchAction class",e);
		}
		return Action.SUCCESS;
	}
		
	public String getCadreDetailsByPanchayatAndMuncipalitie() 
	{
		try{
			jObj =new JSONObject(getTask());
			vo=cadreManagementService.getCadreDetailsbyPanchayatIdAndMuncipality(jObj.getLong("panchayatId"),jObj.getString("locationName"));
			
			return Action.SUCCESS;
			}catch(Exception e)
			{
				LOG.error("Exception Rised In getCadreDetailsByPanchayatAndMuncipalitie() in PartyCadreSearchAction class",e);
			}
		return Action.SUCCESS;
	}
	public String getCommitteCadreDetails() 
	{
		
		try{
			
			jObj =new JSONObject(getTask());
			
			cadreDetails = cadreManagementService.getCommitteCadreDetails(jObj.getLong("committeeId"),jObj.getLong("committeeLevelId"));
			return Action.SUCCESS;
		}
		catch(Exception e)
		{
			LOG.error("Exception Rised In getCommitteCadreDetails() in PartyCadreSearchAction class",e);
		}
		return Action.SUCCESS;
	}
	public String getCommitteeLevelValues() 
	{
		
		try{
			
			jObj =new JSONObject(getTask());
			committeesLevelValue=cadreManagementService.getAllCommitteeLevelValues(jObj.getLong("committeeLevelId"));
			
		}
		catch(Exception e)
		{
			LOG.error("Exception Rised In getCommitteeLevelValues()",e);
		}
		return Action.SUCCESS;
	}	
	public String getCommittees() 
	{
		
		try{
			
			jObj =new JSONObject(getTask());
			committee=cadreManagementService.getAllCommittees(jObj.getLong("committeeLevelValueId"),jObj.getLong("scopeId"));
			
			return Action.SUCCESS;
		}
		catch(Exception e)
		{
			LOG.error("Exception Rised In getCommittees() ",e);
		}
		return Action.SUCCESS;
	}

	/*public String getAllMuncipalities()
	{
		try{
			jObj =new JSONObject(getTask());
			locationDetails=cadreManagementService.getLocalElectionBodyDetailsByConId(jObj.getLong("constituencyId"));
			
		}
		catch(Exception e)
		{
			LOG.error("Exception Rised In getAllMuncipalities() in PartyCadreSearchAction class",e);
		}
		return Action.SUCCESS;
	}*/

}
