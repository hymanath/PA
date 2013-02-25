package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CadreSearchAction extends ActionSupport implements ServletRequestAware 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
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
	JSONObject jObj = null;
	
	public List<SelectOptionVO> getBloodGroupList() {
		return bloodGroupList;
	}

	public void setBloodGroupList(List<SelectOptionVO> bloodGroupList) {
		this.bloodGroupList = bloodGroupList;
	}
	
	public String getWindowTask() {
		return windowTask;
	}

	public void setWindowTask(String windowTask) {
		this.windowTask = windowTask;
	}

	public List<SelectOptionVO> getOccupationsList() {
		return occupationsList;
	}

	public void setOccupationsList(List<SelectOptionVO> occupationsList) {
		this.occupationsList = occupationsList;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public CadreManagementService getCadreManagementService() {
		return cadreManagementService;
	}

	public void setCadreManagementService(
			CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public List<SelectOptionVO> getSocialStatus() {
		return socialStatus;
	}

	public void setSocialStatus(List<SelectOptionVO> socialStatus) {
		this.socialStatus = socialStatus;
	}

	public List<SelectOptionVO> getEduStatus() {
		return eduStatus;
	}

	public void setEduStatus(List<SelectOptionVO> eduStatus) {
		this.eduStatus = eduStatus;
	}

	public List<SelectOptionVO> getPartyCommitteesList() {
		return partyCommitteesList;
	}

	public void setPartyCommitteesList(List<SelectOptionVO> partyCommitteesList) {
		this.partyCommitteesList = partyCommitteesList;
	}

	public List<SelectOptionVO> getCadreSkillsList() {
		return cadreSkillsList;
	}

	public void setCadreSkillsList(List<SelectOptionVO> cadreSkillsList) {
		this.cadreSkillsList = cadreSkillsList;
	}

	public List<SelectOptionVO> getPartyTrainingCampsList() {
		return partyTrainingCampsList;
	}

	public void setPartyTrainingCampsList(
			List<SelectOptionVO> partyTrainingCampsList) {
		this.partyTrainingCampsList = partyTrainingCampsList;
	}

	public Boolean getIsPartyCommitee() {
		return isPartyCommitee;
	}

	public void setIsPartyCommitee(Boolean isPartyCommitee) {
		this.isPartyCommitee = isPartyCommitee;
	}

	public Boolean getIsCadreSkills() {
		return isCadreSkills;
	}

	public void setIsCadreSkills(Boolean isCadreSkills) {
		this.isCadreSkills = isCadreSkills;
	}

	public Boolean getIsTrainingCamps() {
		return isTrainingCamps;
	}

	public void setIsTrainingCamps(Boolean isTrainingCamps) {
		this.isTrainingCamps = isTrainingCamps;
	}

	public void setServletRequest(HttpServletRequest request) {
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

	public String execute() throws Exception
	{
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		if(regVO==null)
			return ERROR;
		
		windowTask = request.getParameter("windowTask");
		String accessType = regVO.getAccessType();
		Long accessValue= new Long(regVO.getAccessValue());
		socialStatus = staticDataService.getAllSocialCategories(); 
		socialStatus.add(0, new SelectOptionVO(0L,"All"));
		bloodGroupList = cadreManagementService.getAllBloodGroupTypes();
		bloodGroupList.add(0, new SelectOptionVO(0L,"All"));
		
		eduStatus = staticDataService.getAllEducationalQualifications();
		eduStatus.add(0, new SelectOptionVO(0L,"All"));
		
		occupationsList = staticDataService.getAllOccupations();
		occupationsList.add(0, new SelectOptionVO(0L,"All"));
		//prepopulate locations based on accesstype and access values
		stateList = new ArrayList<SelectOptionVO>();
		districtList = new ArrayList<SelectOptionVO>();
		constituencyList = new ArrayList<SelectOptionVO>();
		parliamentConstituencies = regionServiceDataImp.getAllParliamentConstituenciesForAState(1l,accessValue);
		if("MLA".equals(accessType))
		{
			List<SelectOptionVO> list = regionServiceDataImp.getStateDistrictByConstituencyID(accessValue);
			stateList.add(list.get(0));			
			districtList.add(list.get(1));			
			constituencyList.add(list.get(2));
			
		}else if("COUNTRY".equals(accessType))
		{
			stateList = cadreManagementService.findStatesByCountryID(accessValue.toString());
			stateList.add(0,new SelectOptionVO(0L, "Select State"));			
			
		}else if("STATE".equals(accessType)){
			
			String name = cadreManagementService.getStateName(accessValue);
			SelectOptionVO obj2 = new SelectOptionVO();
			obj2.setId(accessValue);
			obj2.setName(name);			
			stateList.add(obj2);
			districtList = staticDataService.getDistricts(accessValue);
			districtList.add(0,new SelectOptionVO(0l,"Select District"));	
			
		}else if("DISTRICT".equals(accessType)){
						
			List<SelectOptionVO> list = regionServiceDataImp.getStateDistrictByDistrictID(accessValue);
			stateList.add(list.get(0));			
			districtList.add(list.get(1));
			constituencyList = regionServiceDataImp.getConstituenciesByDistrictID(accessValue);
			constituencyList.add(0, new SelectOptionVO(0l,"Select Constituency"));
			
		} else if("MP".equals(accessType)){
			
			ConstituencyInfoVO constituencyInfoVO = new ConstituencyInfoVO();
			stateList = regionServiceDataImp.getStateByParliamentConstituencyID(accessValue);
			constituencyInfoVO = staticDataService.getLatestAssemblyConstituenciesForParliament(accessValue);
			constituencyList = constituencyInfoVO.getAssembyConstituencies();
			constituencyList.add(0,new SelectOptionVO(0l,"Select Constituency"));
			parliamentConstituencyList.add(new SelectOptionVO(constituencyInfoVO.getConstituencyId(),constituencyInfoVO.getConstituencyName())); 
			
		}
		// party specific input selection criteria
		if("Party".equals(regVO.getUserType()))
		{
			//party commitee details
			partyCommitteesList = cadreManagementService.getCommitteesForAParty(regVO.getParty());
			if(partyCommitteesList != null && partyCommitteesList.size() > 0){
			    partyCommitteesList.add(0, new SelectOptionVO(0L,"All"));
			    isPartyCommitee = true;
			}
			else if(partyCommitteesList == null || partyCommitteesList.size() == 0)
				isPartyCommitee = false;
			
			//party training camp details
			partyTrainingCampsList = cadreManagementService.getPartyTrainingCamps(regVO.getParty());
			if(partyTrainingCampsList != null && partyTrainingCampsList.size() > 0){
			    partyTrainingCampsList.add(0, new SelectOptionVO(0L,"All"));
			    isTrainingCamps = true;
			}else if(partyTrainingCampsList == null || partyTrainingCampsList.size() == 0)
				isTrainingCamps = false;
			
			//cadre skill details
			cadreSkillsList = cadreManagementService.getPartyCadreSkills(regVO.getParty());
			if(cadreSkillsList != null && cadreSkillsList.size() > 0){
			    cadreSkillsList.add(0, new SelectOptionVO(0L,"All"));
			    isCadreSkills = true;
			}else if(cadreSkillsList == null || cadreSkillsList.size() == 0)
				isCadreSkills = false;
		}
		
		return Action.SUCCESS;
	}
	public String getParliamentConstis(){
		
		try{
			jObj = new JSONObject(request.getParameter("task"));
			if(jObj.getString("task").equalsIgnoreCase("getPariamentConstituencies")){
				parliamentConstituencies = regionServiceDataImp.getAllParliamentConstituenciesForAState(1l,jObj.getLong("stateId"));	
			}
		}catch(Exception e){
			
		}
		return Action.SUCCESS;
	}

}
