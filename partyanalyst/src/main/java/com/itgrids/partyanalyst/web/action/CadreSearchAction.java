package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.ISessionConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CadreSearchAction extends ActionSupport implements ServletRequestAware 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8240423820642334020L;
	private static final Logger LOG = Logger.getLogger(CadreSearchAction.class);
	private transient HttpServletRequest request;
	private List<SelectOptionVO> socialStatus = new ArrayList<SelectOptionVO>();
	private List<SelectOptionVO> eduStatus = new ArrayList<SelectOptionVO>();
	private Boolean isPartyCommitee;
	private List<SelectOptionVO> partyCommitteesList = new ArrayList<SelectOptionVO>();
	private Boolean isCadreSkills;
	private List<SelectOptionVO> cadreSkillsList = new ArrayList<SelectOptionVO>();
	private Boolean isTrainingCamps;
	private List<SelectOptionVO> partyTrainingCampsList = new ArrayList<SelectOptionVO>();
	private List<SelectOptionVO> occupationsList = new ArrayList<SelectOptionVO>();
	private IStaticDataService staticDataService; 
	private CadreManagementService cadreManagementService;
	private HttpSession session;
	private String windowTask;
	private List<SelectOptionVO> stateList;
	private List<SelectOptionVO> districtList;
	private List<SelectOptionVO> constituencyList;
	private List<SelectOptionVO> parliamentConstituencyList;
	private IRegionServiceData regionServiceDataImp;
	private List<SelectOptionVO> parliamentConstituencies;
	private List<SelectOptionVO> bloodGroupList;
	private Long voterId;
	private EntitlementsHelper entitlementsHelper;
	private String fromParent;
	
	private List<SelectOptionVO>  designationsList ;

	public List<SelectOptionVO> getDesignationsList() {
		return designationsList;
	}

	public void setDesignationsList(final List<SelectOptionVO> designationsList) {
		this.designationsList = designationsList;
	}

	public List<SelectOptionVO> getBloodGroupList() {
		return bloodGroupList;
	}

	public void setBloodGroupList(final List<SelectOptionVO> bloodGroupList) {
		this.bloodGroupList = bloodGroupList;
	}
	
	public String getWindowTask() {
		return windowTask;
	}

	public void setWindowTask(final String windowTask) {
		this.windowTask = windowTask;
	}

	public List<SelectOptionVO> getOccupationsList() {
		return occupationsList;
	}

	public void setOccupationsList(final List<SelectOptionVO> occupationsList) {
		this.occupationsList = occupationsList;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(final HttpSession session) {
		this.session = session;
	}

	public CadreManagementService getCadreManagementService() {
		return cadreManagementService;
	}

	public void setCadreManagementService(
			final CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(final IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public List<SelectOptionVO> getSocialStatus() {
		return socialStatus;
	}

	public void setSocialStatus(final List<SelectOptionVO> socialStatus) {
		this.socialStatus = socialStatus;
	}

	public List<SelectOptionVO> getEduStatus() {
		return eduStatus;
	}

	public void setEduStatus(final List<SelectOptionVO> eduStatus) {
		this.eduStatus = eduStatus;
	}

	public List<SelectOptionVO> getPartyCommitteesList() {
		return partyCommitteesList;
	}

	public void setPartyCommitteesList(final List<SelectOptionVO> partyCommitteesList) {
		this.partyCommitteesList = partyCommitteesList;
	}

	public List<SelectOptionVO> getCadreSkillsList() {
		return cadreSkillsList;
	}

	public void setCadreSkillsList(final List<SelectOptionVO> cadreSkillsList) {
		this.cadreSkillsList = cadreSkillsList;
	}

	public List<SelectOptionVO> getPartyTrainingCampsList() {
		return partyTrainingCampsList;
	}

	public void setPartyTrainingCampsList(
			final List<SelectOptionVO> partyTrainingCampsList) {
		this.partyTrainingCampsList = partyTrainingCampsList;
	}

	public Boolean getIsPartyCommitee() {
		return isPartyCommitee;
	}

	public void setIsPartyCommitee(final Boolean isPartyCommitee) {
		this.isPartyCommitee = isPartyCommitee;
	}

	public Boolean getIsCadreSkills() {
		return isCadreSkills;
	}

	public void setIsCadreSkills(final Boolean isCadreSkills) {
		this.isCadreSkills = isCadreSkills;
	}

	public Boolean getIsTrainingCamps() {
		return isTrainingCamps;
	}

	public void setIsTrainingCamps(final Boolean isTrainingCamps) {
		this.isTrainingCamps = isTrainingCamps;
	}

	public void setServletRequest(final HttpServletRequest request) {
		this.request = request;		
	}	
	
	public List<SelectOptionVO> getStateList() {
		return stateList;
	}

	public void setStateList(List<SelectOptionVO> stateList) {
		this.stateList = stateList;
	}

	public List<SelectOptionVO> getDistrictList() {
		return districtList;
	}

	public void setDistrictList(List<SelectOptionVO> districtList) {
		this.districtList = districtList;
	}

	public List<SelectOptionVO> getConstituencyList() {
		return constituencyList;
	}

	public void setConstituencyList(List<SelectOptionVO> constituencyList) {
		this.constituencyList = constituencyList;
	}	

	public List<SelectOptionVO> getParliamentConstituencyList() {
		return parliamentConstituencyList;
	}

	public void setParliamentConstituencyList(
			List<SelectOptionVO> parliamentConstituencyList) {
		this.parliamentConstituencyList = parliamentConstituencyList;
	}	

	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}

	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}

	public List<SelectOptionVO> getParliamentConstituencies() {
		return parliamentConstituencies;
	}

	public void setParliamentConstituencies(
			List<SelectOptionVO> parliamentConstituencies) {
		this.parliamentConstituencies = parliamentConstituencies;
	}

	public Long getVoterId() {
		return voterId;
	}

	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}

	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}
	
	

	public String getFromParent() {
		return fromParent;
	}

	public void setFromParent(String fromParent) {
		this.fromParent = fromParent;
	}

	public String execute() 
	{
		session = request.getSession();
		final RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		if(regVO==null){
			return ERROR;
		}
		List<String> entitlements = null;
		if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
			entitlements = regVO.getEntitlements();
			if(regVO == null && !entitlements.contains(IConstants.CADRE_MANAGEMENT_ENTITLEMENT)){
				return INPUT;
			}
			if(!entitlements.contains(IConstants.CADRE_MANAGEMENT_ENTITLEMENT)){
				return ERROR;
			}
	/*	if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.CADRE_MANAGEMENT_ENTITLEMENT)){
			return INPUT;
		}
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.CADRE_MANAGEMENT_ENTITLEMENT)){
			return ERROR;
		}*/
		windowTask = request.getParameter("windowTask");
		final String all = "All";
		final String accessType = regVO.getAccessType();
		final Long accessValue= Long.valueOf(regVO.getAccessValue());
		socialStatus = staticDataService.getAllSocialCategories(); 
		socialStatus.add(0, new SelectOptionVO(0L,all));
		bloodGroupList = cadreManagementService.getAllBloodGroupTypes();
		bloodGroupList.add(0, new SelectOptionVO(0L,all));
		
		eduStatus = staticDataService.getAllEducationalQualifications();
		eduStatus.add(0, new SelectOptionVO(0L,all));
		
		occupationsList = staticDataService.getAllOccupations();
		occupationsList.add(0, new SelectOptionVO(0L,all));
		//prepopulate locations based on accesstype and access values
		stateList = new ArrayList<SelectOptionVO>();
		districtList = new ArrayList<SelectOptionVO>();
		constituencyList = new ArrayList<SelectOptionVO>();
		parliamentConstituencies = regionServiceDataImp.getAllParliamentConstituenciesForAState(1l,accessValue);
		if("MLA".equals(accessType))
		{
			final List<SelectOptionVO> list = regionServiceDataImp.getStateDistrictByConstituencyID(accessValue);
			stateList.add(list.get(0));			
			districtList.add(list.get(1));			
			constituencyList.add(list.get(2));
			
		}else if("COUNTRY".equals(accessType))
		{
			stateList = cadreManagementService.findStatesByCountryID(accessValue.toString());
			stateList.add(0,new SelectOptionVO(0L, "Select State"));			
			
		}else if("STATE".equals(accessType)){
			
			final String name = cadreManagementService.getStateName(accessValue);
			final SelectOptionVO obj2 = new SelectOptionVO();
			obj2.setId(accessValue);
			obj2.setName(name);			
			stateList.add(obj2);
			districtList = staticDataService.getDistricts(accessValue);
			districtList.add(0,new SelectOptionVO(0l,"Select Location"));	
			
		}else if("DISTRICT".equals(accessType)){
						
			final List<SelectOptionVO> list = regionServiceDataImp.getStateDistrictByDistrictID(accessValue);
			stateList.add(list.get(0));			
			districtList.add(list.get(1));
			constituencyList = regionServiceDataImp.getConstituenciesByDistrictID(accessValue);
			constituencyList.add(0, new SelectOptionVO(0l,"Select Constituency"));
			
		} else if("MP".equals(accessType)){
			
			stateList = regionServiceDataImp.getStateByParliamentConstituencyID(accessValue);
			final ConstituencyInfoVO constituencyInfoVO = staticDataService.getLatestAssemblyConstituenciesForParliament(accessValue);
			constituencyList = constituencyInfoVO.getAssembyConstituencies();
			constituencyList.add(0,new SelectOptionVO(0l,"Select Constituency"));
			if(parliamentConstituencyList == null){
			   parliamentConstituencyList = new ArrayList<SelectOptionVO>();
			}
			parliamentConstituencyList.add(new SelectOptionVO(constituencyInfoVO.getConstituencyId(),constituencyInfoVO.getConstituencyName())); 
			
		}
		
	 if(IConstants.USER_TYPE_PARTY.equals(regVO.getUserType())) 
		{
			partyCommitteesList = new ArrayList<SelectOptionVO>(0);
			partyTrainingCampsList = new ArrayList<SelectOptionVO>(0);
			cadreSkillsList = new ArrayList<SelectOptionVO>(0);
						
			partyCommitteesList = cadreManagementService.getCommitteesForAParty(regVO.getParty());
			
			partyTrainingCampsList = cadreManagementService.getPartyTrainingCamps(regVO.getParty()); 
			
			cadreSkillsList = cadreManagementService.getPartyCadreSkills(regVO.getParty()); 
			
			session.setAttribute(ISessionConstants.PARTY_COMMITTEES,partyCommitteesList);
			if(windowTask.equals(IConstants.CREATE_NEW)){
				designationsList = new ArrayList<SelectOptionVO>(0);
			}
			session.setAttribute(ISessionConstants.COMMITTEE_DESIGNATIONS,designationsList);
			session.setAttribute(ISessionConstants.PARTY_TRAINING_CAMPS,partyTrainingCampsList);
			session.setAttribute(ISessionConstants.CADRE_SKILLS,cadreSkillsList);
			
		}
	}
	return Action.SUCCESS;
	}
	public String getParliamentConstis(){
		
		try{
			final JSONObject jObj = new JSONObject(request.getParameter("task"));
			if(jObj.getString("task").equalsIgnoreCase("getPariamentConstituencies")){
				parliamentConstituencies = regionServiceDataImp.getAllParliamentConstituenciesForAState(1l,jObj.getLong("stateId"));	
			}
		}catch(Exception e){
			LOG.error("Exception rised in getParliamentConstis",e);
		}
		return Action.SUCCESS;
	}
	public String getBloodGroups(){
		
		try{
			 JSONObject jObj = new JSONObject(request.getParameter("task"));
			
				bloodGroupList = cadreManagementService.getAllBloodGroupTypes();
			
		}catch(Exception e){
			LOG.error("Exception rised in getBloodGroups",e);
		}
		return Action.SUCCESS;
	}
	
}
