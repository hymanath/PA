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
import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.IPartyStrengthService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.itgrids.partyanalyst.service.impl.RegionServiceDataImp;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.ISessionConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class CadreOnlineRegistrationAction extends ActionSupport implements ServletRequestAware,ServletContextAware, ModelDriven, Preparable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CadreManagementService cadreManagementService;
	private static final Logger LOG = Logger.getLogger(CadreOnlineRegistrationAction.class);
	
	private ServletContext context;
	private HttpServletRequest request;
	private HttpSession session;
	/**
	 * lists used to populate address select dropdowns
	 */
	private List<SelectOptionVO> stateList,stateList_o, districtList, constituencyList,parliamentConstituencyList,mandalList,boothsList ;
	/**
	* Lists used to populate select dropdown
	*
	**/
	private List<SelectOptionVO> relationshipList, languagesList, eduStatus, occupationsList, socialStatus, cadreLevelsList,partyCommitteesList, designationsList, cadreSkillsList,partyTrainingCampsList,cadreRolesList ;
	/**
	 * services used in this action
	 */
	private IStaticDataService staticDataService; 
	private RegionServiceDataImp regionServiceDataImp;
	private ICrossVotingEstimationService crossVotingEstimationService;
	/**
	 * List of strings used render to struts 2 radio btn elements
	 */
	private List<String> gender, dobOptionsList, language_options, cadreType;
	/**
	 * Boleans for displaying only the required options in the form.
	 *
	 */
	private Boolean partyCommitteesFlag = false;
	private Boolean cadreSkillsFlag = false;
	private Boolean partyTrainingCampsFlag = false;
	
	//to display or hide official address form inputs.if set to true, the form inputs are hidden, if set to false form inputs are shown
	private Boolean sameAsCAFlag;	
	private String cadreId;
	private String windowTask;  
	private CadreInfo cadreInfo;
	/**
	 * Select options for cadre level regional data
	 * 
	 */
	private List<SelectOptionVO> districtListC, constituencyListC;
	/**
	 * varibales used to pre select the required select boxes in cadre level
	 * 
	 */
	private Long defaultStateId = 0l;
	private Long defaultDistId = 0l;
	private Long defaultConstId = 0l;	
	private Long defaultCadreLevelId;
	private List<SelectOptionVO> bloodGroupTypes;
	private Long voterId;
	private IPartyStrengthService partyStrengthService;
	private IVotersAnalysisService votersAnalysisService;
	private String name;
	private Long onlineRegId;
	//private RegistrationVO registrationVO;
	public List<SelectOptionVO> getBloodGroupTypes() {
		return bloodGroupTypes;
	}

	public void setBloodGroupTypes(List<SelectOptionVO> bloodGroupTypes) {
		this.bloodGroupTypes = bloodGroupTypes;
	}
	
	
	public IPartyStrengthService getPartyStrengthService() {
		return partyStrengthService;
	}

	public void setPartyStrengthService(IPartyStrengthService partyStrengthService) {
		this.partyStrengthService = partyStrengthService;
	}

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
	
	public void setCadreManagementService(
			CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
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

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getWindowTask() {
		return windowTask;
	}

	public void setWindowTask(String windowTask) {
		this.windowTask = windowTask;
	}	

	public RegionServiceDataImp getRegionServiceDataImp() {
		return regionServiceDataImp;
	}

	public void setRegionServiceDataImp(RegionServiceDataImp regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
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

	public ICrossVotingEstimationService getCrossVotingEstimationService() {
		return crossVotingEstimationService;
	}

	public void setCrossVotingEstimationService(
			ICrossVotingEstimationService crossVotingEstimationService) {
		this.crossVotingEstimationService = crossVotingEstimationService;
	}
	
	public List<String> getDobOptionsList() {
		return dobOptionsList;
	}

	public void setDobOptionsList(List<String> dobOptionsList) {
		this.dobOptionsList = dobOptionsList;
	}	
	
	public List<SelectOptionVO> getDistrictListC() {
		return districtListC;
	}

	public void setDistrictListC(List<SelectOptionVO> districtListC) {
		this.districtListC = districtListC;
	}

	public List<SelectOptionVO> getConstituencyListC() {
		return constituencyListC;
	}

	public void setConstituencyListC(List<SelectOptionVO> constituencyListC) {
		this.constituencyListC = constituencyListC;
	}

	public Long getDefaultStateId() {
		return defaultStateId;
	}

	public void setDefaultStateId(Long defaultStateId) {
		this.defaultStateId = defaultStateId;
	}

	public Long getDefaultDistId() {
		return defaultDistId;
	}

	public void setDefaultDistId(Long defaultDistId) {
		this.defaultDistId = defaultDistId;
	}

	public Long getDefaultConstId() {
		return defaultConstId;
	}

	public void setDefaultConstId(Long defaultConstId) {
		this.defaultConstId = defaultConstId;
	}

	public Long getDefaultState() {
		return this.defaultStateId;
	}
	
	public Long getDefaultDistrict() {
		return this.defaultDistId;
	}	

	public Long getDefaultConstituency() {
		return this.defaultConstId;
	}
	// to make the active or normal cadre type radio button to pre select active radio button
	public String getDefaultCadreType() {
		return "Active";
	}

	public List<SelectOptionVO> getCadreLevelsList() {
		return cadreLevelsList;
	}

	public void setCadreLevelsList(List<SelectOptionVO> cadreLevelsList) {
		this.cadreLevelsList = cadreLevelsList;
	}
	// to pre select cadre level  based on user access type
	public Long getDefaultCadreLevel()
	{
		return this.defaultCadreLevelId;
	}	

	public Long getDefaultCadreLevelId() {
		return defaultCadreLevelId;
	}

	public void setDefaultCadreLevelId(Long defaultCadreLevelId) {
		this.defaultCadreLevelId = defaultCadreLevelId;
	}	

	public Boolean getSameAsCAFlag() {
		return sameAsCAFlag;
	}

	public void setSameAsCAFlag(Boolean sameAsCAFlag) {
		this.sameAsCAFlag = sameAsCAFlag;
	}

	public List<SelectOptionVO> getRelationshipList() {
		return relationshipList;
	}

	public void setRelationshipList(List<SelectOptionVO> relationshipList) {
		this.relationshipList = relationshipList;
	}	

	public List<SelectOptionVO> getParliamentConstituencyList() {
		return parliamentConstituencyList;
	}

	public void setParliamentConstituencyList(
			List<SelectOptionVO> parliamentConstituencyList) {
		this.parliamentConstituencyList = parliamentConstituencyList;
	}	

	public List<SelectOptionVO> getDesignationsList() {
		return designationsList;
	}

	public void setDesignationsList(List<SelectOptionVO> designationsList) {
		this.designationsList = designationsList;
	}

		
	public Long getVoterId() {
		return voterId;
	}

	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}

	public IVotersAnalysisService getVotersAnalysisService() {
		return votersAnalysisService;
	}

	public void setVotersAnalysisService(
			IVotersAnalysisService votersAnalysisService) {
		this.votersAnalysisService = votersAnalysisService;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public Long getOnlineRegId() {
		return onlineRegId;
	}

	public void setOnlineRegId(Long onlineRegId) {
		this.onlineRegId = onlineRegId;
	}

	
	
	/*public RegistrationVO getRegistrationVO() {
		return registrationVO;
	}

	public void setRegistrationVO(RegistrationVO registrationVO) {
		this.registrationVO = registrationVO;
	}*/

	public String execute(){
		if(LOG.isDebugEnabled())
			LOG.debug("CadreRegisterPageAction.execute() start");
		
		session = request.getSession();
    	RegistrationVO regVO = cadreManagementService.getUserDetailsByOnlineRegId(onlineRegId);
    	if(regVO == null)
    	{
    		return ERROR;
    	}
    	session.removeAttribute("loginStatus");
		session.removeAttribute("checkedTypeValue");
		session.removeAttribute("HiddenCount");
		session.removeAttribute("UserName");
		session.removeAttribute(IConstants.USER);
		session.removeAttribute(IWebConstants.FREE_USER_ROLE);
    	session.setAttribute("USER", regVO);
    	 if( "0".equals(cadreId)) {
         	cadreInfo = new CadreInfo();
         	cadreLevelsList = cadreManagementService.getAllCadreLevels();
         }
		String accessType =regVO.getAccessType();
		boolean isCadreParliamentWise = regVO.isCadreParliamentWise();
		Long accessValue= Long.valueOf(regVO.getAccessValue());
		relationshipList = cadreManagementService.getAllRelationships();
		cadreRolesList = new ArrayList<SelectOptionVO>(0);
		
		cadreRolesList = cadreManagementService.getCadreRoles();
		bloodGroupTypes = cadreManagementService.getAllBloodGroupTypes();
		
		//all states in country
		//stateList_o = regionServiceDataImp.getStatesByCountry(1l);
		stateList_o = partyStrengthService.getAllStatesHavinElectionData("Assembly");
		stateList_o.add(0,new SelectOptionVO(0l,"Select State"));
		//initialize all the required lists with empty array
		//current address
		stateList = new ArrayList<SelectOptionVO>(0);
		districtList = new ArrayList<SelectOptionVO>(0);
		constituencyList = new ArrayList<SelectOptionVO>(0);
		parliamentConstituencyList  = new ArrayList<SelectOptionVO>(0);
		mandalList = new ArrayList<SelectOptionVO>(0);
		//cadre level regions select variables		
		districtListC = new ArrayList<SelectOptionVO>(0);
		constituencyListC = new ArrayList<SelectOptionVO>(0);
		// add elements in to language options
		language_options = new ArrayList<String>();
		language_options.add(IConstants.SPEAK_LANGUAGE);
		language_options.add(IConstants.READ_LANGUAGE);
		language_options.add(IConstants.WRITE_LANGUAGE);
		
		gender = new ArrayList<String>();
		gender.add("Male");
		gender.add("Female");
		
		dobOptionsList= new ArrayList<String>();
		dobOptionsList.add("Date Of Birth");
		dobOptionsList.add("Age");
		
		cadreType = new ArrayList<String>();
		cadreType.add(IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		cadreType.add(IConstants.CADRE_MEMBER_TYPE_NORMAL);
				
		//add elements in to cadre level elements
		cadreLevelsList = new ArrayList<SelectOptionVO>();
		cadreLevelsList.add(new SelectOptionVO(2l,"STATE"));
		cadreLevelsList.add(new SelectOptionVO(3l,"DISTRICT"));
		cadreLevelsList.add(new SelectOptionVO(10l,"PARLIAMENT CONSTITUENCY"));
		cadreLevelsList.add(new SelectOptionVO(4l,"CONSTITUENCY"));
		cadreLevelsList.add(new SelectOptionVO(5l,"MANDAL"));
		cadreLevelsList.add(new SelectOptionVO(6l,"VILLAGE"));
		cadreLevelsList.add(new SelectOptionVO(7l,"MUNICIPAL-CORP-GMC"));
		cadreLevelsList.add(new SelectOptionVO(8l,"WARD"));
		cadreLevelsList.add(new SelectOptionVO(9l,"BOOTH"));
		
		if(windowTask.equals(IConstants.CREATE_NEW))
		{	
			if("MLA".equals(accessType))
			{
				LOG.debug("Access Type = MLA ****");
				List<SelectOptionVO> list = regionServiceDataImp.getStateDistrictByConstituencyID(accessValue);
				
				stateList.add(list.get(0));			
				districtList.add(list.get(1));
				constituencyList.add(list.get(2));
				mandalList = regionServiceDataImp.getSubRegionsInConstituency(accessValue, IConstants.PRESENT_YEAR, null);
				mandalList.add(0,new SelectOptionVO(0l,"Select Location"));
				parliamentConstituencyList = cadreManagementService.findLatestParliamentForAssembly(accessValue);
				districtListC = regionServiceDataImp.getDistrictsByStateID(list.get(0).getId());
				constituencyListC = regionServiceDataImp.getConstituenciesByDistrictID(list.get(1).getId());
				//session.setAttribute(ISessionConstants.STATES, stateList);
				//default state element to be selected in cadre level selection for active cadre
				setDefaultStateId(list.get(0).getId());
				//default district element to be selected in cadre level selection for active cadre
				setDefaultDistId(list.get(1).getId());
				//default constituency element to be selected in cadre level selection for active cadre
				setDefaultConstId(list.get(2).getId());
				//default cadre level element to be selected in cadre level selection for active cadre
				setDefaultCadreLevelId(4l);				
							
			}else if("COUNTRY".equals(accessType))
			{
				LOG.debug("Access Type = Country ****");
				stateList = cadreManagementService.findStatesByCountryID(accessValue.toString());
				stateList.add(0,new SelectOptionVO(0l,"Select State"));
				//session.setAttribute(ISessionConstants.STATES, stateList);
				//default cadre level element to be selected in cadre level selection for active cadre
				setDefaultCadreLevelId(0l);				
				
			}else if("STATE".equals(accessType)){
				LOG.debug("Access Type = State ****");
				
				String name = cadreManagementService.getStateName(accessValue);
				SelectOptionVO obj2 = new SelectOptionVO();
				obj2.setId(accessValue);
				obj2.setName(name);
				stateList.add(obj2);
				districtList = staticDataService.getDistricts(accessValue);
				districtList.add(0,new SelectOptionVO(0l,"Select District"));
				constituencyList = new ArrayList<SelectOptionVO>(0);
				parliamentConstituencyList  = new ArrayList<SelectOptionVO>(0);
				if(isCadreParliamentWise){
					parliamentConstituencyList = staticDataService.getLatestConstituenciesByStateIdAndType(accessValue,IConstants.PARLIAMENT_CONSTITUENCY_TYPE);
					constituencyList = staticDataService.getLatestConstituenciesByStateIdAndType(accessValue,IConstants.ASSEMBLY_ELECTION_TYPE);
					parliamentConstituencyList.add(0,new SelectOptionVO(0l,"Select Location"));
					constituencyList.add(0,new SelectOptionVO(0l,"Select Location"));
				}
				//session.setAttribute(ISessionConstants.STATES, stateList);
				//default state element to be selected in cadre level selection for active cadre
				setDefaultStateId(accessValue);
				//default cadre level element to be selected in cadre level selection for active cadre
				setDefaultCadreLevelId(2l);
				
			}else if("DISTRICT".equals(accessType)){
				LOG.debug("Access Type = District ****");			

				List<SelectOptionVO> list = regionServiceDataImp.getStateDistrictByDistrictID(accessValue);
				stateList.add(list.get(0));
				districtList.add(list.get(1));
				parliamentConstituencyList = cadreManagementService.getParliamentConstituenciesInADistrict(accessValue.toString());
				//parliamentConstituencyList.add(0,new SelectOptionVO(0l,"Select Location"));
				constituencyList = regionServiceDataImp.getConstituenciesByDistrictID(accessValue);
				constituencyList.add(0,new SelectOptionVO(0l,"Select Constituency"));
				districtListC = regionServiceDataImp.getDistrictsByStateID(list.get(0).getId());
				//session.setAttribute(ISessionConstants.STATES, stateList);
				//default state element to be selected in cadre level selection for active cadre
				setDefaultStateId(list.get(0).getId());
				//default district element to be selected in cadre level selection for active cadre
				setDefaultDistId(list.get(1).getId());
				//default cadre level element to be selected in cadre level selection for active cadre
				setDefaultCadreLevelId(3l);	
				
			} else if("MP".equals(accessType)){
				LOG.debug("Access Type = MP ****");
				
				ConstituencyInfoVO constituencyInfoVO = new ConstituencyInfoVO();
				stateList = regionServiceDataImp.getStateByParliamentConstituencyID(accessValue);
				constituencyInfoVO = staticDataService.getLatestAssemblyConstituenciesForParliament(accessValue);
				constituencyList = constituencyInfoVO.getAssembyConstituencies();
				constituencyList.add(0,new SelectOptionVO(0l,"Select Constituency"));
				parliamentConstituencyList.add(new SelectOptionVO(constituencyInfoVO.getConstituencyId(),constituencyInfoVO.getConstituencyName())); 
				//session.setAttribute(ISessionConstants.STATES, stateList);
				
				setDefaultCadreLevelId(3l);
			}			
			setSameAsCAFlag(true);
			//contact details current address
			session.setAttribute(ISessionConstants.STATES, stateList);
			session.setAttribute(ISessionConstants.DISTRICTS,districtList);
			session.setAttribute(ISessionConstants.CONSTITUENCIES,constituencyList);
			session.setAttribute(ISessionConstants.P_CONSTITUENCIES, parliamentConstituencyList);
			session.setAttribute(ISessionConstants.MANDALS,mandalList);	
			session.setAttribute(ISessionConstants.VILLAGES, new ArrayList<SelectOptionVO>());
			session.setAttribute(ISessionConstants.BOOTHS,new ArrayList<SelectOptionVO>());		
			// contact details official address
			session.setAttribute(ISessionConstants.STATES_O, stateList_o);
			session.setAttribute(ISessionConstants.DISTRICTS_O, new ArrayList<SelectOptionVO>());
			session.setAttribute(ISessionConstants.CONSTITUENCIES_O, new ArrayList<SelectOptionVO>());
			session.setAttribute(ISessionConstants.P_CONSTITUENCIES_O,new ArrayList<SelectOptionVO>());
			session.setAttribute(ISessionConstants.MANDALS_O, new ArrayList<SelectOptionVO>());
			session.setAttribute(ISessionConstants.VILLAGES_O, new ArrayList<SelectOptionVO>());
			session.setAttribute(ISessionConstants.BOOTHS_O,new ArrayList<SelectOptionVO>());
			
		} else if(windowTask.equals(IConstants.UPDATE_EXISTING))
		{
			 session.setAttribute(ISessionConstants.STATES_O, stateList_o);
			if("COUNTRY".equals(accessType)){
			  session.setAttribute(ISessionConstants.STATES, stateList_o);	
			 
			}else{
				if(cadreInfo.getState() != null){
					String name = cadreManagementService.getStateName(Long.valueOf(cadreInfo.getState()));
					SelectOptionVO obj2 = new SelectOptionVO();
					obj2.setId(Long.valueOf(cadreInfo.getState()));
					obj2.setName(name);
					stateList = new ArrayList<SelectOptionVO>();
					stateList.add(obj2);
				    session.setAttribute(ISessionConstants.STATES, stateList);	
				}else{
					session.setAttribute(ISessionConstants.STATES, stateList_o);	
				}
			}
			setDefaultCadreLevelId(cadreInfo.getCadreLevel());
			setSameAsCAFlag(cadreInfo.getSameAsCA());			
		}
		socialStatus = staticDataService.getAllSocialCategories(); 
		eduStatus = staticDataService.getAllEducationalQualifications();
		occupationsList = staticDataService.getAllOccupations();
		languagesList = staticDataService.getAllLanguages();
		if(IConstants.USER_TYPE_PARTY.equals(regVO.getUserType()))
		{
			partyCommitteesList = new ArrayList<SelectOptionVO>(0);
			partyTrainingCampsList = new ArrayList<SelectOptionVO>(0);
			cadreSkillsList = new ArrayList<SelectOptionVO>(0);
						
			partyCommitteesList = cadreManagementService.getCommitteesForAParty(regVO.getParty());
			if(partyCommitteesList.size()>0)
				partyCommitteesFlag = true;
			partyTrainingCampsList = cadreManagementService.getPartyTrainingCamps(regVO.getParty()); 
			if(partyTrainingCampsList.size()>0)
				partyTrainingCampsFlag = true;
			cadreSkillsList = cadreManagementService.getPartyCadreSkills(regVO.getParty()); 
			if(cadreSkillsList.size()>0)
				cadreSkillsFlag = true;
						
			session.setAttribute(ISessionConstants.PARTY_COMMITTEES,partyCommitteesList);
			if(windowTask.equals(IConstants.CREATE_NEW))
				designationsList = new ArrayList<SelectOptionVO>(0);
			session.setAttribute(ISessionConstants.COMMITTEE_DESIGNATIONS,designationsList);
			session.setAttribute(ISessionConstants.PARTY_TRAINING_CAMPS,partyTrainingCampsList);
			session.setAttribute(ISessionConstants.CADRE_SKILLS,cadreSkillsList);
			
		}
				
		//place the variables in to session where these are referenced when validation fails in and control comes back to form
			
		// personal info		
		session.setAttribute(ISessionConstants.GENDERS, gender);
		session.setAttribute(ISessionConstants.DOB_OPTIONS, dobOptionsList);
		session.setAttribute(ISessionConstants.FAMILY_RELATIONS,relationshipList);
		session.setAttribute(ISessionConstants.BLOOD_GROUPS,bloodGroupTypes);
		
		//social status		
		session.setAttribute(ISessionConstants.LANGUAGES, languagesList);
		session.setAttribute(ISessionConstants.LANGUAGE_OPTIONS,language_options);
		session.setAttribute(ISessionConstants.EDU_QUALIFICATIONS, eduStatus);
		session.setAttribute(ISessionConstants.OCCUPATIONS, occupationsList);
		session.setAttribute(ISessionConstants.SOCIALCATEGORIES,socialStatus);
		//cadre levels variables
		session.setAttribute(ISessionConstants.CADRETYPES, cadreType);
		session.setAttribute(ISessionConstants.CADRE_LEVELS_LIST,cadreLevelsList);
		
		//cadre level regions select variables
		session.setAttribute(ISessionConstants.STATES_C, stateList_o);
		session.setAttribute(ISessionConstants.DISTRICTS_C, districtListC);
		session.setAttribute(ISessionConstants.CONSTITUENCIES_C, constituencyListC);
		session.setAttribute(ISessionConstants.MANDALS_C, new ArrayList<SelectOptionVO>());
		session.setAttribute(ISessionConstants.VILLAGES_C, new ArrayList<SelectOptionVO>());
		session.setAttribute(ISessionConstants.BOOTHS_C,new ArrayList<SelectOptionVO>());
		//party specific variables		
		session.setAttribute(ISessionConstants.PARTY_COMMITTEES_FLAG, partyCommitteesFlag);
		//session.setAttribute(ISessionConstants.COMMITTEE_DESIGNATIONS, new ArrayList<SelectOptionVO>());
		session.setAttribute(ISessionConstants.CADRE_SKILLS_FLAG, cadreSkillsFlag);
		session.setAttribute(ISessionConstants.PARTY_TRAINING_CAMPS_FLAG, partyTrainingCampsFlag);
		session.setAttribute(ISessionConstants.CADRE_ROLES_LIST,cadreRolesList);
				
		return Action.SUCCESS;
	}

	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	/*public void prepare() throws Exception {
		cadreId = request.getParameter("cadreId");
		if(request.getParameter("voterId") != null)
		voterId = Long.parseLong(request.getParameter("voterId"));
		if(request.getParameter("onlineRegId") != null)
		onlineRegId = Long.parseLong(request.getParameter("onlineRegId"));
        if( "0".equals(cadreId)) {
        	cadreInfo = new CadreInfo();
        	cadreLevelsList = cadreManagementService.getAllCadreLevels();
        } else if(cadreId != null)
        {	
        	cadreInfo = cadreManagementService.getCadreCompleteInfo(Long.valueOf(cadreId));
        	prepopulateLocations(cadreInfo);
        }  
        else if(voterId != 0)
        {
        	cadreInfo = votersAnalysisService.getCadreDetailsByVoterId(voterId);
        	prepopulateLocations(cadreInfo);
        }
        if(onlineRegId != null)
        {
        	session = request.getSession();
        	RegistrationVO registrationVO = cadreManagementService.getUserDetailsByOnlineRegId(1l);
        	session.setAttribute("USER", registrationVO);
        }
	}    

     public Object getModel() {
        return cadreInfo;
    }
     
    public void prepopulateLocations(CadreInfo  cadreInfo)
    {
    		session = request.getSession();
    		LOG.info("inside method populate const");
    		List<SelectOptionVO> pConstituencyList = new ArrayList<SelectOptionVO>();
    		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
    		String accessType =regVO.getAccessType();
    		boolean isCadreParliamentWise = regVO.isCadreParliamentWise();
    		List<SelectOptionVO> pConstituencyList_o = new ArrayList<SelectOptionVO>();
    		List<SelectOptionVO> constituencynames_o = new ArrayList<SelectOptionVO>();
    		List<SelectOptionVO> districtNames_o = new ArrayList<SelectOptionVO>();
    		//get districts
    		List<SelectOptionVO> districtNames_c = new ArrayList<SelectOptionVO>();
    		if("MP".equals(accessType) || "COUNTRY".equals(accessType) || "STATE".equals(accessType)){
	    		 districtNames_c=cadreManagementService.findDistrictsByState(cadreInfo.getState());			
	    		SelectOptionVO obj = new SelectOptionVO(0L,"Select District");
	    		districtNames_c.add(0, obj);
    		}else{
    			List<SelectOptionVO> list = regionServiceDataImp.getStateDistrictByDistrictID(Long.valueOf(cadreInfo.getDistrict()));
				
				districtNames_c.add(list.get(1));
    		}
    		
    		session.setAttribute(ISessionConstants.DISTRICTS, districtNames_c);
			
    		//get constituencies
    		List<SelectOptionVO> constituencynames_c = new ArrayList<SelectOptionVO>();
    		if(cadreInfo.getDistrict() != null)
    		{
    			constituencynames_c=regionServiceDataImp.getConstituenciesByDistrictID(Long.valueOf(cadreInfo.getDistrict()));	
    			SelectOptionVO obj1 = new SelectOptionVO(0L,"Select Constituency");
    			constituencynames_c.add(0, obj1);        		
    		}
    		if("STATE".equals(accessType) && isCadreParliamentWise){
    			pConstituencyList = staticDataService.getLatestConstituenciesByStateIdAndType(Long.valueOf(cadreInfo.getState().trim()),IConstants.PARLIAMENT_CONSTITUENCY_TYPE);
    			pConstituencyList.add(0, new SelectOptionVO(0l,"Select Location"));
    			constituencynames_c = staticDataService.getLatestConstituenciesByStateIdAndType(Long.valueOf(cadreInfo.getState().trim()),IConstants.ASSEMBLY_ELECTION_TYPE);
    			constituencynames_c.add(0, new SelectOptionVO(0l,"Select Location"));
    			session.setAttribute(ISessionConstants.P_CONSTITUENCIES, pConstituencyList);
    			session.setAttribute(ISessionConstants.CONSTITUENCIES, constituencynames_c);
    		}else{
	    		if(cadreInfo.getParliament() != null)
	    		{
	    			if(cadreInfo.getDistrict() == null){
		    			ConstituencyInfoVO constituencyInfoVO = new ConstituencyInfoVO();
		    			constituencyInfoVO = staticDataService.getLatestAssemblyConstituenciesForParliament(Long.valueOf(cadreInfo.getParliament()));
		    			pConstituencyList.add(new SelectOptionVO(constituencyInfoVO.getConstituencyId(),constituencyInfoVO.getConstituencyName()));
		    			constituencynames_c=constituencyInfoVO.getAssembyConstituencies();	
		    			SelectOptionVO obj1 = new SelectOptionVO(0L,"Select Constituency");
		    			constituencynames_c.add(0, obj1);
		    			
	    			}else{
	    				if("DISTRICT".equals(accessType) || "COUNTRY".equals(accessType) || "STATE".equals(accessType)){
	    					pConstituencyList = cadreManagementService.getParliamentConstituenciesInADistrict(cadreInfo.getDistrict());
	    					//constituencynames_c = cadreManagementService.getAssemblyConstiForParlInADistrict(cadreInfo.getDistrict(),Long.valueOf(cadreInfo.getParliament()));
	    					//SelectOptionVO obj1 = new SelectOptionVO(0L,"Select Constituency");
	    	    			//constituencynames_c.add(0, obj1);
	    				}else{
	    					pConstituencyList =  cadreManagementService.findLatestParliamentForAssembly(cadreInfo.getConstituencyID());
	    					List<SelectOptionVO> list = regionServiceDataImp.getStateDistrictByConstituencyID(Long.valueOf(cadreInfo.getConstituencyID()));
	    					constituencynames_c = new ArrayList<SelectOptionVO>();
	    					constituencynames_c.add(list.get(2));
	    				}
	    			}
	    			session.setAttribute(ISessionConstants.P_CONSTITUENCIES, pConstituencyList);
	    		}
	    					
				session.setAttribute(ISessionConstants.CONSTITUENCIES, constituencynames_c);
    		}
			//get Mandals
			List<SelectOptionVO> mandals_c=regionServiceDataImp.getSubRegionsInConstituency(Long.valueOf(cadreInfo.getConstituencyID()),IConstants.PRESENT_YEAR,null);
			SelectOptionVO obj2 = new SelectOptionVO(0L,"Select Mandal");
			mandals_c.add(0, obj2);
			session.setAttribute(ISessionConstants.MANDALS, mandals_c);
			
			//get villages
			List<SelectOptionVO> villageNames_c = regionServiceDataImp.getHamletsOrWards(Long.valueOf(cadreInfo.getMandal()),IConstants.PRESENT_YEAR);
			SelectOptionVO obj3 = new SelectOptionVO(0L,"Select Village");
			villageNames_c.add(0, obj3);
			
			session.setAttribute(ISessionConstants.VILLAGES, villageNames_c);
			List<SelectOptionVO> boothsList_c;
			if(cadreInfo.getMandalName().contains(IConstants.GREATER_ELECTION_TYPE))
				boothsList_c = getRegionServiceDataImp().getboothsInWard(Long.valueOf(cadreInfo.getVillage()),Long.valueOf(IConstants.PRESENT_ELECTION_YEAR),Long.valueOf(cadreInfo.getConstituencyID()));
			else 
				boothsList_c = getRegionServiceDataImp().getBoothsInTehsilOrMunicipality(Long.valueOf(cadreInfo.getMandal()),Long.valueOf(IConstants.PRESENT_ELECTION_YEAR),Long.valueOf(cadreInfo.getConstituencyID()));
			if(boothsList_c != null){
				SelectOptionVO obj = new SelectOptionVO(0L,"Select Location");
				boothsList_c.add(0, obj);
			}
			session.setAttribute(ISessionConstants.BOOTHS, boothsList_c);
			//get districts
			//LOG.info("same as ca false");
    		 districtNames_o=cadreManagementService.findDistrictsByState(cadreInfo.getPstate());			
    		SelectOptionVO obj4 = new SelectOptionVO(0L,"Select District");
    		districtNames_o.add(0, obj4);
    		
    		session.setAttribute(ISessionConstants.DISTRICTS_O, districtNames_o);
    		pConstituencyList_o = staticDataService.getLatestConstituenciesByStateIdAndType(Long.valueOf(cadreInfo.getPstate().trim()),IConstants.PARLIAMENT_CONSTITUENCY_TYPE);
    		pConstituencyList_o.add(0, new SelectOptionVO(0l,"Select Location"));
    		
    		if(!"MP".equals(accessType) && !isCadreParliamentWise){
    			constituencynames_o = regionServiceDataImp.getConstituenciesByDistrictID(Long.valueOf(cadreInfo.getPdistrict()));	
    			constituencynames_o.add(0, new SelectOptionVO(0l,"Select Location"));
    		}
    		else if(isCadreParliamentWise){
    			//pConstituencyList_o = cadreManagementService.getParliamentConstituenciesInADistrict(cadreInfo.getPdistrict());
    			constituencynames_o = staticDataService.getLatestConstituenciesByStateIdAndType(Long.valueOf(cadreInfo.getPstate().trim()),IConstants.ASSEMBLY_ELECTION_TYPE);
    			constituencynames_o.add(0, new SelectOptionVO(0l,"Select Location"));
    			//constituencynames_o = cadreManagementService.getAssemblyConstiForParlInADistrict(cadreInfo.getPdistrict(),Long.valueOf(cadreInfo.getPParliament()));
    		}else if("MP".equals(accessType)){
    			//pConstituencyList_o = staticDataService.getLatestConstituenciesByStateIdAndType(Long.valueOf(cadreInfo.getPstate().trim()),IConstants.PARLIAMENT_CONSTITUENCY_TYPE);
    			//pConstituencyList_o.add(0, new SelectOptionVO(0l,"Select Location"));
    			ConstituencyInfoVO constituencyInfoVO = staticDataService.getLatestAssemblyConstituenciesForParliament(Long.valueOf(cadreInfo.getPParliament()));
    			constituencynames_o=constituencyInfoVO.getAssembyConstituencies();	
    		}
    		session.setAttribute(ISessionConstants.P_CONSTITUENCIES_O, pConstituencyList_o);
    		session.setAttribute(ISessionConstants.CONSTITUENCIES_O, constituencynames_o);

		if(cadreInfo.getSameAsCA() == false)
		{
			//get districts
    		//List<SelectOptionVO> districtNames_o = new ArrayList<SelectOptionVO>();
    		
    		if("MP".equals(accessType) || "COUNTRY".equals(accessType) || "STATE".equals(accessType)){
    			districtNames_o=cadreManagementService.findDistrictsByState(cadreInfo.getPstate());			
	    		SelectOptionVO obj = new SelectOptionVO(0L,"Select District");
	    		districtNames_o.add(0, obj);
    		}else{
    			List<SelectOptionVO> list = regionServiceDataImp.getStateDistrictByDistrictID(Long.valueOf(cadreInfo.getPdistrict()));
				
    			districtNames_o.add(list.get(1));
    		}
    		
    		session.setAttribute(ISessionConstants.DISTRICTS_O, districtNames_o);
			
    		//get constituencies
    		List<SelectOptionVO> constituencynames_o = new ArrayList<SelectOptionVO>();
    		if(cadreInfo.getPdistrict() != null)
    		{
    			constituencynames_o=regionServiceDataImp.getConstituenciesByDistrictID(Long.valueOf(cadreInfo.getPdistrict()));	
    			SelectOptionVO obj1 = new SelectOptionVO(0L,"Select Constituency");
    			constituencynames_o.add(0, obj1);        		
    		}
    		if(cadreInfo.getPParliament() != null)
    		{
    			if(cadreInfo.getPdistrict() == null){
	    			ConstituencyInfoVO constituencyInfoVO = new ConstituencyInfoVO();
	    			constituencyInfoVO = staticDataService.getLatestAssemblyConstituenciesForParliament(Long.valueOf(cadreInfo.getPParliament()));
	    			pConstituencyList_o.add(new SelectOptionVO(constituencyInfoVO.getConstituencyId(),constituencyInfoVO.getConstituencyName()));
	    			constituencynames_o=constituencyInfoVO.getAssembyConstituencies();	
	    			SelectOptionVO obj1 = new SelectOptionVO(0L,"Select Constituency");
	    			constituencynames_o.add(0, obj1);
	    			
    			}else{
    				if("DISTRICT".equals(accessType) || "COUNTRY".equals(accessType) || "STATE".equals(accessType)){
    					pConstituencyList_o = cadreManagementService.getParliamentConstituenciesInADistrict(cadreInfo.getPdistrict());
    					constituencynames_o = cadreManagementService.getAssemblyConstiForParlInADistrict(cadreInfo.getPdistrict(),Long.valueOf(cadreInfo.getPParliament()));
    					//SelectOptionVO obj1 = new SelectOptionVO(0L,"Select Constituency");
    					//constituencynames_o.add(0, obj1);
    				}else{
    					pConstituencyList_o =  cadreManagementService.findLatestParliamentForAssembly(cadreInfo.getPconstituencyID());
    					List<SelectOptionVO> list = regionServiceDataImp.getStateDistrictByConstituencyID(Long.valueOf(cadreInfo.getPconstituencyID()));
    					constituencynames_o.add(list.get(2));
    				}
    			}
    			session.setAttribute(ISessionConstants.P_CONSTITUENCIES_O, pConstituencyList_o);
    		}
    					
    		session.setAttribute(ISessionConstants.CONSTITUENCIES_O, constituencynames_o);
			
			
    		
					
    		//get constituencies
    		List<SelectOptionVO> constituencynames_o=regionServiceDataImp.getConstituenciesByDistrictID(Long.valueOf(cadreInfo.getPdistrict()));	
			SelectOptionVO obj5 = new SelectOptionVO(0L,"Select Constituency");
			constituencynames_o.add(0, obj5);
    					
			session.setAttribute(ISessionConstants.CONSTITUENCIES_O, constituencynames_o);
			
			
			//get Mandals/local bodies
			List<SelectOptionVO> mandals_o=regionServiceDataImp.getSubRegionsInConstituency(Long.valueOf(cadreInfo.getPconstituencyID()),IConstants.PRESENT_YEAR,null);	
			SelectOptionVO obj6 = new SelectOptionVO(0L,"Select Mandal");
			mandals_o.add(0, obj6);
			session.setAttribute(ISessionConstants.MANDALS_O, mandals_o);
			
			
			//get villages/wards
			List<SelectOptionVO> villageNames_o = regionServiceDataImp.getHamletsOrWards(Long.valueOf(cadreInfo.getPmandal()),IConstants.PRESENT_YEAR);
			SelectOptionVO obj7 = new SelectOptionVO(0L,"Select Village");
			villageNames_o.add(0, obj7);
			session.setAttribute(ISessionConstants.VILLAGES_O, villageNames_o);	
			
			// get booths 
			List<SelectOptionVO> boothsList_o = new ArrayList<SelectOptionVO>();
			if(cadreInfo.getPmandalName().contains(IConstants.GREATER_ELECTION_TYPE))
				
				boothsList_o = getRegionServiceDataImp().getboothsInWard(Long.valueOf(cadreInfo.getPvillage()),Long.valueOf(IConstants.PRESENT_ELECTION_YEAR),Long.valueOf(cadreInfo.getPconstituencyID()));
			else 
				boothsList_o = getRegionServiceDataImp().getBoothsInTehsilOrMunicipality(Long.valueOf(cadreInfo.getPmandal()),Long.valueOf(IConstants.PRESENT_ELECTION_YEAR),Long.valueOf(cadreInfo.getPconstituencyID()));
			if(boothsList_o != null){
				SelectOptionVO obj = new SelectOptionVO(0L,"Select Location");
				boothsList_o.add(0, obj);
			}
			session.setAttribute(ISessionConstants.BOOTHS_O, boothsList_o);
			
		} else if(cadreInfo.getSameAsCA() == true)
		{
			LOG.info("same as ca true");
			session.setAttribute(ISessionConstants.DISTRICTS_O, districtNames_o);
			session.setAttribute(ISessionConstants.CONSTITUENCIES_O, constituencynames_o);
			session.setAttribute(ISessionConstants.P_CONSTITUENCIES_O, pConstituencyList_o);
			session.setAttribute(ISessionConstants.MANDALS_O, mandals_c);
			session.setAttribute(ISessionConstants.VILLAGES_O, villageNames_c);
			session.setAttribute(ISessionConstants.BOOTHS_O, boothsList_c);
		}
		if(cadreInfo.getDesignation() != null)
			designationsList = cadreManagementService.getDesignationsInCommittee(cadreInfo.getPartyCommittee());
		
			
	}*/

    

}
