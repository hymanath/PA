package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.itgrids.partyanalyst.dto.CadreInfo;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.UserCadresInfoVO;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.itgrids.partyanalyst.service.impl.CrossVotingEstimationService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.ISessionConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public class CadreRegisterPageAction extends ActionSupport implements ServletRequestAware,ServletContextAware, ModelDriven, Preparable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CadreManagementService cadreManagementService;
	private CrossVotingEstimationService crossVotingEstimationService;
	private static final Logger log = Logger.getLogger(CadreRegisterPageAction.class);
	
	private ServletContext context;
	private UserCadresInfoVO userCadresInfoVO = new UserCadresInfoVO();
	private HttpServletRequest request;
	private HttpSession session;
	private List<SelectOptionVO> stateList;
	private List<SelectOptionVO> districtList;
	private List<SelectOptionVO> constituencyList;
	private List<SelectOptionVO> mandalList;
	private List<SelectOptionVO> villagesList;
	private IRegionServiceData regionServiceData;
	private List<SelectOptionVO> socialStatus = new ArrayList<SelectOptionVO>();
	private List<SelectOptionVO> eduStatus = new ArrayList<SelectOptionVO>();
	private List<SelectOptionVO> partyCommitteesList;
	private List<SelectOptionVO> cadreSkillsList = new ArrayList<SelectOptionVO>();
	private List<SelectOptionVO> partyTrainingCampsList = new ArrayList<SelectOptionVO>();
	private IStaticDataService staticDataService; 
	private List<SelectOptionVO> occupationsList = new ArrayList<SelectOptionVO>();
	private List<SelectOptionVO> languagesList = new ArrayList<SelectOptionVO>();
	private Boolean partyCommittees_flag = false;
	private Boolean cadreSkills_flag = false;
	private Boolean partyTrainingCamps_flag = false;
	private List<String> gender = new ArrayList<String>();
	private List<String> cadreType = new ArrayList<String>();
	private List<SelectOptionVO> districtsList_1;
	private List<SelectOptionVO> constituenciesList_1;
	private List<SelectOptionVO> mandalsList_1;
	private List<SelectOptionVO> villagesList_1;
	private String cadreId;
	private List<SelectOptionVO> cadreLevels;
	private String windowTask;  
	private CadreInfo cadreInfo;
	private List<String> language_options;
	
	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
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

	public List<SelectOptionVO> getMandalList() {
		return mandalList;
	}

	public void setMandalList(List<SelectOptionVO> mandalList) {
		this.mandalList = mandalList;
	}	
	
	public void setCadreManagementService(
			CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
	}
	
	public void setRegionServiceData(IRegionServiceData regionServiceData){
		this.regionServiceData = regionServiceData;
	}

	public void setCrossVotingEstimationService(
			CrossVotingEstimationService crossVotingEstimationService) {
		this.crossVotingEstimationService = crossVotingEstimationService;
	}
	
	public void setServletRequest(HttpServletRequest request) {		
		this.request = request;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;		
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

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}	

	public List<SelectOptionVO> getOccupationsList() {
		return occupationsList;
	}

	public void setOccupationsList(List<SelectOptionVO> occupationsList) {
		this.occupationsList = occupationsList;
	}	

	public List<SelectOptionVO> getLanguagesList() {
		return languagesList;
	}

	public void setLanguagesList(List<SelectOptionVO> languagesList) {
		this.languagesList = languagesList;
	}
	
	public List<String> getGender() {
		return gender;
	}
	public void setGender(List<String> gender) {
		this.gender = gender;
	}

	public List<SelectOptionVO> getDistrictsList_1() {
		return districtsList_1;
	}

	public void setDistrictsList_1(List<SelectOptionVO> districtsList_1) {
		this.districtsList_1 = districtsList_1;
	}

	public List<SelectOptionVO> getConstituenciesList_1() {
		return constituenciesList_1;
	}

	public void setConstituenciesList_1(List<SelectOptionVO> constituenciesList_1) {
		this.constituenciesList_1 = constituenciesList_1;
	}

	public List<SelectOptionVO> getMandalsList_1() {
		return mandalsList_1;
	}

	public void setMandalsList_1(List<SelectOptionVO> mandalsList_1) {
		this.mandalsList_1 = mandalsList_1;
	}

	public List<SelectOptionVO> getVillagesList_1() {
		return villagesList_1;
	}

	public void setVillagesList_1(List<SelectOptionVO> villagesList_1) {
		this.villagesList_1 = villagesList_1;
	}	

	public List<SelectOptionVO> getVillagesList() {
		return villagesList;
	}

	public void setVillagesList(List<SelectOptionVO> villagesList) {
		this.villagesList = villagesList;
	}	

	public String getCadreId() {
		return cadreId;
	}

	public void setCadreId(String cadreId) {
		this.cadreId = cadreId;
	}

	public CadreInfo getCadreInfo() {
		return cadreInfo;
	}

	public void setCadreInfo(CadreInfo cadreInfo) {
		this.cadreInfo = cadreInfo;
	}
	
	public List<String> getCadreType() {
		return cadreType;
	}

	public void setCadreType(List<String> cadreType) {
		this.cadreType = cadreType;
	}	

	public List<SelectOptionVO> getCadreLevels() {
		return cadreLevels;
	}
	

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setCadreLevels(List<SelectOptionVO> cadreLevels) {
		this.cadreLevels = cadreLevels;
	}

	public String getWindowTask() {
		return windowTask;
	}

	public void setWindowTask(String windowTask) {
		this.windowTask = windowTask;
	}

	public String execute(){
		if(log.isDebugEnabled())
			log.debug("CadreRegisterPageAction.execute() start");
		System.out.println("cadreId:"+cadreId);
		session = request.getSession();
		
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		if(regVO==null)
			return ERROR;
		String accessType =regVO.getAccessType();
		Long accessValue= new Long(regVO.getAccessValue());

		stateList = new ArrayList<SelectOptionVO>();
		districtList = new ArrayList<SelectOptionVO>();
		constituencyList = new ArrayList<SelectOptionVO>();
		mandalList = new ArrayList<SelectOptionVO>();
		villagesList = new ArrayList<SelectOptionVO>();
		districtsList_1 = new ArrayList<SelectOptionVO>();
		constituenciesList_1 = new ArrayList<SelectOptionVO>();
		mandalsList_1 = new ArrayList<SelectOptionVO>();
		villagesList_1 = new ArrayList<SelectOptionVO>();
		
		language_options = new ArrayList<String>();
		language_options.add("speak");
		language_options.add("read");
		language_options.add("write");
		
		
		stateList = cadreManagementService.findStatesByCountryID(accessValue.toString());
		
		session.setAttribute(ISessionConstants.LANGUAGE_OPTIONS,language_options);
		session.setAttribute(ISessionConstants.STATES, stateList);
		session.setAttribute(ISessionConstants.DISTRICTS, districtList);
		session.setAttribute(ISessionConstants.CONSTITUENCIES, constituencyList);
		session.setAttribute(ISessionConstants.MANDALS, mandalList);
		session.setAttribute(ISessionConstants.VILLAGES, villagesList);
		
		
		session.setAttribute(ISessionConstants.DISTRICTS_1, districtsList_1);
		session.setAttribute(ISessionConstants.CONSTITUENCIES_1, constituenciesList_1);
		session.setAttribute(ISessionConstants.MANDALS_1, mandalsList_1);
		session.setAttribute(ISessionConstants.VILLAGES_1, villagesList_1);
		
/*		if("MLA".equals(accessType))
		{
			log.debug("Access Type = MLA ****");
			List<SelectOptionVO> list = regionServiceData.getStateDistrictByConstituencyID(accessValue);
			
			stateList.add(list.get(0));			
			districtList.add(list.get(1));
			constituencyList.add(list.get(2));
			mandalList = regionServiceData.getMandalsByConstituencyID(accessValue);
			session.setAttribute("statesList", stateList);
			session.setAttribute("districtsList",districtList);
			session.setAttribute("constituenciesList",constituencyList);
			session.setAttribute("mandalsList",mandalList);
			
						
		}else if("COUNTRY".equals(accessType))
		{
			log.debug("Access Type = Country ****");
			stateList = cadreManagementService.findStatesByCountryID(accessValue.toString());
			session.setAttribute("statesList", stateList);
			
		}else if("STATE".equals(accessType)){
			log.debug("Access Type = State ****");
			
			String name = cadreManagementService.getStateName(accessValue);
			SelectOptionVO obj1 = new SelectOptionVO(0L,"Select State");
			SelectOptionVO obj2 = new SelectOptionVO();
			obj2.setId(accessValue);
			obj2.setName(name);
			stateList.add(obj2);
			districtList = staticDataService.getDistricts(accessValue);
			districtList.add(0,new SelectOptionVO(0l,"Select District"));
			session.setAttribute("statesList", stateList);
			session.setAttribute("districtsList",districtList);
			
		}else if("DISTRICT".equals(accessType)){
			log.debug("Access Type = District ****");			

			List<SelectOptionVO> list = regionServiceData.getStateDistrictByDistrictID(accessValue);
			stateList.add(list.get(0));
			districtList.add(list.get(1));
			constituencyList = regionServiceData.getConstituenciesByDistrictID(accessValue);
			session.setAttribute("statesList", stateList);
			session.setAttribute("districtsList",districtList);
			session.setAttribute("constituenciesList",constituencyList);
			
			
		} else if("MP".equals(accessType)){
			log.debug("Access Type = MP ****");
			stateList = regionServiceData.getStateByParliamentConstituencyID(accessValue);
			SelectOptionVO state = stateList.get(0);
			Long stateID = state.getId();
			Long year = regionServiceData.getLatestParliamentElectionYear(stateID);
			log.debug("year:::::"+year);
			log.debug("stateID:::::"+stateID);
			if(year!=null){
				constituencyList = crossVotingEstimationService.getAssembliesForParliament(accessValue,year);
			}	
			log.debug("constituencyList.size():"+constituencyList.size());	
			session.setAttribute("constituenciesList",constituencyList);
			
		}*/
		
		socialStatus = staticDataService.getAllSocialCategories(); 
		eduStatus = staticDataService.getAllEducationalQualifications();
		occupationsList = staticDataService.getAllOccupations();
		languagesList = staticDataService.getAllLanguages();
		if(IConstants.USER_TYPE_PARTY.equals(regVO.getUserType()) && IConstants.BJP.equals(regVO.getPartyShortName()))
		{
			partyCommitteesList = cadreManagementService.getCommitteesForAParty(regVO.getParty());
			partyTrainingCampsList = cadreManagementService.getPartyTrainingCamps(regVO.getParty()); 
			cadreSkillsList = cadreManagementService.getPartyCadreSkills(regVO.getParty()); 
			partyCommittees_flag = true;
			cadreSkills_flag = true;
			partyTrainingCamps_flag = true;
			session.setAttribute(ISessionConstants.PARTY_COMMITTEES,partyCommitteesList);
			session.setAttribute(ISessionConstants.PARTY_TRAINING_CAMPS,partyTrainingCampsList);
			session.setAttribute(ISessionConstants.CADRE_SKILLS,cadreSkillsList);
		}
		
		if(gender.size() == 0){
			gender.add("Male");
			gender.add("Female");
		}
		if(cadreType.size() == 0)
		{
			cadreType.add(IConstants.CADRE_MEMBER_TYPE_ACTIVE);
			cadreType.add(IConstants.CADRE_MEMBER_TYPE_NORMAL);
		}
		cadreLevels = cadreManagementService.getAllCadreLevels();
		session.setAttribute(ISessionConstants.CADRE_LEVELS, cadreLevels );
		session.setAttribute(ISessionConstants.CADRETYPES, cadreType);
		session.setAttribute(ISessionConstants.GENDERS, gender);
		session.setAttribute(ISessionConstants.SOCIALCATEGORIES,socialStatus);
		session.setAttribute(ISessionConstants.EDU_QUALIFICATIONS, eduStatus);
		session.setAttribute(ISessionConstants.OCCUPATIONS, occupationsList);
		session.setAttribute(ISessionConstants.LANGUAGES, languagesList);
		session.setAttribute(ISessionConstants.PARTY_COMMITTEES_FLAG, partyCommittees_flag);
		session.setAttribute(ISessionConstants.CADRE_SKILLS_FLAG, cadreSkills_flag);
		session.setAttribute(ISessionConstants.PARTY_TRAINING_CAMPS_FLAG, partyTrainingCamps_flag);
		return Action.SUCCESS;
	}

	public void prepare() throws Exception {
		cadreId = request.getParameter("cadreId");
		System.out.println("cadreId in prepare:"+cadreId);
        if( "0".equals(cadreId)) {
        	cadreInfo = new CadreInfo();
        } else {
        	if(cadreId != null)
        		cadreInfo = cadreManagementService.getCadreCompleteInfo(new Long(cadreId));
        }
	}
    

     public Object getModel() {
        return cadreInfo;
    }

}
