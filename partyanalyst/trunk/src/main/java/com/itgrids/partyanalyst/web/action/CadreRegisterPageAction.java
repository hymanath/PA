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
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.itgrids.partyanalyst.service.impl.RegionServiceDataImp;
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
	private static final Logger log = Logger.getLogger(CadreRegisterPageAction.class);
	
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
	private Boolean partyCommittees_flag = false;
	private Boolean cadreSkills_flag = false;
	private Boolean partyTrainingCamps_flag = false;
	
	//to display or hide official address form inputs.if set to true, the form inputs are hidden, if set to false form inputs are shown
	private Boolean sameAsCAFlag;	
	private String cadreId;
	private String windowTask;  
	private CadreInfo cadreInfo;
	/**
	 * Select options for cadre level regional data
	 * 
	 */
	private List<SelectOptionVO> districtList_c, constituencyList_c;
	/**
	 * varibales used to pre select the required select boxes in cadre level
	 * 
	 */
	private Long defaultStateId = 0l;
	private Long defaultDistId = 0l;
	private Long defaultConstId = 0l;	
	private Long defaultCadreLevelId;
	
	
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

	public List<SelectOptionVO> getDistrictList_c() {
		return districtList_c;
	}

	public void setDistrictList_c(List<SelectOptionVO> districtList_c) {
		this.districtList_c = districtList_c;
	}

	public List<SelectOptionVO> getConstituencyList_c() {
		return constituencyList_c;
	}

	public void setConstituencyList_c(List<SelectOptionVO> constituencyList_c) {
		this.constituencyList_c = constituencyList_c;
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

	public String execute(){
		if(log.isDebugEnabled())
			log.debug("CadreRegisterPageAction.execute() start");
		
		session = request.getSession();
		
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		if(regVO==null)
			return ERROR;
		String accessType =regVO.getAccessType();
		Long accessValue= new Long(regVO.getAccessValue());
		relationshipList = cadreManagementService.getAllRelationships();
		cadreRolesList = new ArrayList<SelectOptionVO>(0);
		
		cadreRolesList = cadreManagementService.getCadreRoles(); 
		//all states in country
		stateList_o = regionServiceDataImp.getStatesByCountry(1l);
		stateList_o.add(0,new SelectOptionVO(0l,"Select State"));
		//initialize all the required lists with empty array
		//current address
		stateList = new ArrayList<SelectOptionVO>(0);
		districtList = new ArrayList<SelectOptionVO>(0);
		constituencyList = new ArrayList<SelectOptionVO>(0);
		parliamentConstituencyList  = new ArrayList<SelectOptionVO>(0);
		mandalList = new ArrayList<SelectOptionVO>(0);
		//cadre level regions select variables		
		districtList_c = new ArrayList<SelectOptionVO>(0);
		constituencyList_c = new ArrayList<SelectOptionVO>(0);
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
				log.debug("Access Type = MLA ****");
				List<SelectOptionVO> list = regionServiceDataImp.getStateDistrictByConstituencyID(accessValue);
				
				stateList.add(list.get(0));			
				districtList.add(list.get(1));
				constituencyList.add(list.get(2));
				mandalList = regionServiceDataImp.getSubRegionsInConstituency(accessValue, IConstants.PRESENT_YEAR, null);
				mandalList.add(0,new SelectOptionVO(0l,"Select Location"));
				
				districtList_c = regionServiceDataImp.getDistrictsByStateID(list.get(0).getId());
				constituencyList_c = regionServiceDataImp.getConstituenciesByDistrictID(list.get(1).getId());
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
				log.debug("Access Type = Country ****");
				stateList = cadreManagementService.findStatesByCountryID(accessValue.toString());
				stateList.add(0,new SelectOptionVO(0l,"Select State"));
				//session.setAttribute(ISessionConstants.STATES, stateList);
				//default cadre level element to be selected in cadre level selection for active cadre
				setDefaultCadreLevelId(0l);				
				
			}else if("STATE".equals(accessType)){
				log.debug("Access Type = State ****");
				
				String name = cadreManagementService.getStateName(accessValue);
				SelectOptionVO obj2 = new SelectOptionVO();
				obj2.setId(accessValue);
				obj2.setName(name);
				stateList.add(obj2);
				districtList = staticDataService.getDistricts(accessValue);
				districtList.add(0,new SelectOptionVO(0l,"Select District"));
				//session.setAttribute(ISessionConstants.STATES, stateList);
				//default state element to be selected in cadre level selection for active cadre
				setDefaultStateId(accessValue);
				//default cadre level element to be selected in cadre level selection for active cadre
				setDefaultCadreLevelId(2l);
				
			}else if("DISTRICT".equals(accessType)){
				log.debug("Access Type = District ****");			

				List<SelectOptionVO> list = regionServiceDataImp.getStateDistrictByDistrictID(accessValue);
				stateList.add(list.get(0));
				districtList.add(list.get(1));
				constituencyList = regionServiceDataImp.getConstituenciesByDistrictID(accessValue);
				constituencyList.add(0,new SelectOptionVO(0l,"Select Constituency"));
				districtList_c = regionServiceDataImp.getDistrictsByStateID(list.get(0).getId());
				//session.setAttribute(ISessionConstants.STATES, stateList);
				//default state element to be selected in cadre level selection for active cadre
				setDefaultStateId(list.get(0).getId());
				//default district element to be selected in cadre level selection for active cadre
				setDefaultDistId(list.get(1).getId());
				//default cadre level element to be selected in cadre level selection for active cadre
				setDefaultCadreLevelId(3l);	
				
			} else if("MP".equals(accessType)){
				log.debug("Access Type = MP ****");
				
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
			
			session.setAttribute(ISessionConstants.STATES, stateList_o);	
			session.setAttribute(ISessionConstants.STATES_O, stateList_o);
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
				partyCommittees_flag = true;
			partyTrainingCampsList = cadreManagementService.getPartyTrainingCamps(regVO.getParty()); 
			if(partyTrainingCampsList.size()>0)
				partyTrainingCamps_flag = true;
			cadreSkillsList = cadreManagementService.getPartyCadreSkills(regVO.getParty()); 
			if(cadreSkillsList.size()>0)
				cadreSkills_flag = true;
						
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
		session.setAttribute(ISessionConstants.DISTRICTS_C, districtList_c);
		session.setAttribute(ISessionConstants.CONSTITUENCIES_C, constituencyList_c);
		session.setAttribute(ISessionConstants.MANDALS_C, new ArrayList<SelectOptionVO>());
		session.setAttribute(ISessionConstants.VILLAGES_C, new ArrayList<SelectOptionVO>());
		session.setAttribute(ISessionConstants.BOOTHS_C,new ArrayList<SelectOptionVO>());
		//party specific variables		
		session.setAttribute(ISessionConstants.PARTY_COMMITTEES_FLAG, partyCommittees_flag);
		//session.setAttribute(ISessionConstants.COMMITTEE_DESIGNATIONS, new ArrayList<SelectOptionVO>());
		session.setAttribute(ISessionConstants.CADRE_SKILLS_FLAG, cadreSkills_flag);
		session.setAttribute(ISessionConstants.PARTY_TRAINING_CAMPS_FLAG, partyTrainingCamps_flag);
		session.setAttribute(ISessionConstants.CADRE_ROLES_LIST,cadreRolesList);
				
		return Action.SUCCESS;
	}

	public void prepare() throws Exception {
		cadreId = request.getParameter("cadreId");
		
        if( "0".equals(cadreId)) {
        	cadreInfo = new CadreInfo();
        	cadreLevelsList = cadreManagementService.getAllCadreLevels();
        } else if(cadreId != null)
        {	
        	cadreInfo = cadreManagementService.getCadreCompleteInfo(new Long(cadreId));
        	prepopulateLocations(cadreInfo);
        }        
	}    

     public Object getModel() {
        return cadreInfo;
    }
     
    public void prepopulateLocations(CadreInfo  cadreInfo)
    {
    		session = request.getSession();
    		System.out.println("inside method populate const");
    		List<SelectOptionVO> pConstituencyList = new ArrayList<SelectOptionVO>();
    		//get districts
    		List<SelectOptionVO> districtNames_c=cadreManagementService.findDistrictsByState(cadreInfo.getState());			
    		SelectOptionVO obj = new SelectOptionVO(0L,"Select District");
    		districtNames_c.add(0, obj);
    		
    		session.setAttribute(ISessionConstants.DISTRICTS, districtNames_c);
			
    		//get constituencies
    		List<SelectOptionVO> constituencynames_c = new ArrayList<SelectOptionVO>();
    		if(cadreInfo.getDistrict() != null)
    		{
    			constituencynames_c=regionServiceDataImp.getConstituenciesByDistrictID(new Long(cadreInfo.getDistrict()));	
    			SelectOptionVO obj1 = new SelectOptionVO(0L,"Select Constituency");
    			constituencynames_c.add(0, obj1);        		
    		}
    		if(cadreInfo.getParliament() != null)
    		{
    			ConstituencyInfoVO constituencyInfoVO = new ConstituencyInfoVO();
    			constituencyInfoVO = staticDataService.getLatestAssemblyConstituenciesForParliament(new Long(cadreInfo.getParliament()));
    			pConstituencyList.add(new SelectOptionVO(constituencyInfoVO.getConstituencyId(),constituencyInfoVO.getConstituencyName()));
    			constituencynames_c=constituencyInfoVO.getAssembyConstituencies();	
    			SelectOptionVO obj1 = new SelectOptionVO(0L,"Select Constituency");
    			constituencynames_c.add(0, obj1);
    			session.setAttribute(ISessionConstants.P_CONSTITUENCIES, pConstituencyList);
    		}
    					
			session.setAttribute(ISessionConstants.CONSTITUENCIES, constituencynames_c);
			
			//get Mandals
			List<SelectOptionVO> mandals_c=regionServiceDataImp.getSubRegionsInConstituency(new Long(cadreInfo.getConstituencyID()),IConstants.PRESENT_YEAR,null);
			SelectOptionVO obj2 = new SelectOptionVO(0L,"Select Mandal");
			mandals_c.add(0, obj2);
			session.setAttribute(ISessionConstants.MANDALS, mandals_c);
			
			//get villages
			List<SelectOptionVO> villageNames_c = regionServiceDataImp.getHamletsOrWards(new Long(cadreInfo.getMandal()),IConstants.PRESENT_YEAR);
			SelectOptionVO obj3 = new SelectOptionVO(0L,"Select Village");
			villageNames_c.add(0, obj3);
			
			session.setAttribute(ISessionConstants.VILLAGES, villageNames_c);
			List<SelectOptionVO> boothsList_c;
			if(cadreInfo.getMandalName().contains(IConstants.GREATER_ELECTION_TYPE))
				boothsList_c = getRegionServiceDataImp().getboothsInWard(new Long(cadreInfo.getVillage()),new Long(IConstants.PRESENT_ELECTION_YEAR),new Long(cadreInfo.getConstituencyID()));
			else 
				boothsList_c = getRegionServiceDataImp().getBoothsInTehsilOrMunicipality(new Long(cadreInfo.getMandal()),new Long(IConstants.PRESENT_ELECTION_YEAR),new Long(cadreInfo.getConstituencyID()));
			session.setAttribute(ISessionConstants.BOOTHS, boothsList_c);

		if(cadreInfo.getSameAsCA() == false)
		{
    		//get districts
			System.out.println("same as ca false");
    		List<SelectOptionVO> districtNames_o=cadreManagementService.findDistrictsByState(cadreInfo.getPstate());			
    		SelectOptionVO obj4 = new SelectOptionVO(0L,"Select District");
    		districtNames_o.add(0, obj4);
    		
    		session.setAttribute(ISessionConstants.DISTRICTS_O, districtNames_o);
					
    		//get constituencies
    		List<SelectOptionVO> constituencynames_o=regionServiceDataImp.getConstituenciesByDistrictID(new Long(cadreInfo.getPdistrict()));	
			SelectOptionVO obj5 = new SelectOptionVO(0L,"Select Constituency");
			constituencynames_o.add(0, obj5);
    					
			session.setAttribute(ISessionConstants.CONSTITUENCIES_O, constituencynames_o);
			
			
			//get Mandals/local bodies
			List<SelectOptionVO> mandals_o=regionServiceDataImp.getSubRegionsInConstituency(new Long(cadreInfo.getPconstituencyID()),IConstants.PRESENT_YEAR,null);	
			SelectOptionVO obj6 = new SelectOptionVO(0L,"Select Mandal");
			mandals_o.add(0, obj6);
			session.setAttribute(ISessionConstants.MANDALS_O, mandals_o);
			
			
			//get villages/wards
			List<SelectOptionVO> villageNames_o = regionServiceDataImp.getHamletsOrWards(new Long(cadreInfo.getPmandal()),IConstants.PRESENT_YEAR);
			SelectOptionVO obj7 = new SelectOptionVO(0L,"Select Village");
			villageNames_o.add(0, obj7);
			session.setAttribute(ISessionConstants.VILLAGES_O, villageNames_o);	
			
			// get booths 
			List<SelectOptionVO> boothsList_o;
			if(cadreInfo.getPmandalName().contains(IConstants.GREATER_ELECTION_TYPE))
				
				boothsList_o = getRegionServiceDataImp().getboothsInWard(new Long(cadreInfo.getPvillage()),new Long(IConstants.PRESENT_ELECTION_YEAR),new Long(cadreInfo.getPconstituencyID()));
			else 
				boothsList_o = getRegionServiceDataImp().getBoothsInTehsilOrMunicipality(new Long(cadreInfo.getPmandal()),new Long(IConstants.PRESENT_ELECTION_YEAR),new Long(cadreInfo.getPconstituencyID()));
			session.setAttribute(ISessionConstants.BOOTHS_O, boothsList_o);
			
		} else if(cadreInfo.getSameAsCA() == true)
		{
			System.out.println("same as ca true");
			session.setAttribute(ISessionConstants.DISTRICTS_O, districtNames_c);
			session.setAttribute(ISessionConstants.CONSTITUENCIES_O, constituencynames_c);
			session.setAttribute(ISessionConstants.P_CONSTITUENCIES_O, pConstituencyList);
			session.setAttribute(ISessionConstants.MANDALS_O, mandals_c);
			session.setAttribute(ISessionConstants.VILLAGES_O, villageNames_c);
			session.setAttribute(ISessionConstants.BOOTHS_O, boothsList_c);
		}
		if(cadreInfo.getDesignation() != null)
			designationsList = cadreManagementService.getDesignationsInCommittee(cadreInfo.getPartyCommittee());
		
			
	}

    

}
