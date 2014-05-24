package com.itgrids.partyanalyst.web.action;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dto.CadreInfo;
import com.itgrids.partyanalyst.dto.CadreVo;
import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.IMahaNaduService;
import com.itgrids.partyanalyst.service.IPartyStrengthService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.itgrids.partyanalyst.service.impl.RegionServiceDataImp;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.ISessionConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class MahaNaduAction extends ActionSupport implements ServletRequestAware,ServletContextAware, Preparable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 2018627765397943688L;
	private CadreManagementService cadreManagementService;
	private static final Logger log = Logger.getLogger(MahaNaduAction.class);
	InputStream inputStream;
	private ServletContext context;
	private HttpServletRequest request;
	private HttpSession session;
	private CadreVo cadreVo;
	JSONObject jObj = null;
	private String task = null;
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
	
	
	//to display or hide official address form inputs.if set to true, the form inputs are hidden, if set to false form inputs are shown
	
	private String cadreId;
 
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
	private List<SelectOptionVO> bloodGroupTypes;
	private Long voterId;
	private IPartyStrengthService partyStrengthService;
	private IVotersAnalysisService votersAnalysisService;
	private IMahaNaduService mahaNaduService;
	private String name;
	private Long onlineRegId;
	//private RegistrationVO registrationVO;
	
	private IConstituencyDAO constituencyDAO;
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public CadreVo getCadreVo() {
		return cadreVo;
	}

	public void setCadreVo(CadreVo cadreVo) {
		this.cadreVo = cadreVo;
	}

	public List<SelectOptionVO> getBoothsList() {
		return boothsList;
	}

	public void setBoothsList(List<SelectOptionVO> boothsList) {
		this.boothsList = boothsList;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

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

	public IMahaNaduService getMahaNaduService() {
		return mahaNaduService;
	}

	public void setMahaNaduService(IMahaNaduService mahaNaduService) {
		this.mahaNaduService = mahaNaduService;
	}

	public String execute(){
		if(log.isDebugEnabled())
			log.debug("MahaNaduAction.execute() start");
		
		session = request.getSession();
		
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		if(regVO==null)
			return ERROR;
		
		
		
		
		
		if(session.getAttribute(ISessionConstants.BLOOD_GROUPS) == null){
			  bloodGroupTypes = cadreManagementService.getAllBloodGroupTypes();
		      session.setAttribute(ISessionConstants.BLOOD_GROUPS,bloodGroupTypes);
		}
		
		if(session.getAttribute(ISessionConstants.DISTRICTS) == null){
		    List<SelectOptionVO> districtNames_c = new ArrayList<SelectOptionVO>();
 		    districtNames_c=cadreManagementService.findDistrictsByState("1");			
 		    SelectOptionVO obj = new SelectOptionVO(0L,"Select District");
 		    districtNames_c.add(0, obj);
		
		    session.setAttribute(ISessionConstants.DISTRICTS, districtNames_c);
		}
		
		 cadreId = request.getParameter("cadreId");
		 
	    if(!(cadreId != null && (new Long(cadreId)) > 0)){
	    	
		    session.setAttribute(ISessionConstants.CONSTITUENCIES,new ArrayList<SelectOptionVO>());
			//session.setAttribute(ISessionConstants.MANDALS,new ArrayList<SelectOptionVO>());	
			//session.setAttribute(ISessionConstants.VILLAGES, new ArrayList<SelectOptionVO>());
			session.setAttribute(ISessionConstants.BOOTHS,new ArrayList<SelectOptionVO>());	
		
	    }
			
			
			gender = new ArrayList<String>();
			gender.add("Male");
			gender.add("Female");
			session.setAttribute(ISessionConstants.GENDERS, gender);
			
		if(session.getAttribute(ISessionConstants.EDU_QUALIFICATIONS) == null){
			eduStatus = staticDataService.getAllEducationalQualifications();
			session.setAttribute(ISessionConstants.EDU_QUALIFICATIONS, eduStatus);
		}	
		
		if(session.getAttribute(ISessionConstants.OCCUPATIONS) == null){
			occupationsList = staticDataService.getAllOccupations();
			session.setAttribute(ISessionConstants.OCCUPATIONS, occupationsList);
		}
		
		if(session.getAttribute("incSource") == null){
			List<SelectOptionVO> incSource = mahaNaduService.getIncomeSources();
			session.setAttribute("incSource", incSource);
		}
		
		if(session.getAttribute("casteCategory") == null){
			List<SelectOptionVO> casteCategory = mahaNaduService.getCasteCategories();
			session.setAttribute("casteCategory", casteCategory);
		}
		
		if(session.getAttribute("partyDesig") == null){
			List<SelectOptionVO> partyDesig = mahaNaduService.getPartyDesignations();
			session.setAttribute("partyDesig",partyDesig);
		}
		
		if(session.getAttribute("govDesig") == null){
			List<SelectOptionVO> govDesig = mahaNaduService.getgovernmentDesignations();
			session.setAttribute("govDesig",govDesig);	
		}
			
				
		return Action.SUCCESS;
	}

	public void prepare() throws Exception {
		
		cadreId = request.getParameter("cadreId");
       if(cadreId != null && (new Long(cadreId)) > 0)
        {	
        	//cadreInfo = cadreManagementService.getCadreCompleteInfo(new Long(cadreId));
    	   cadreVo = mahaNaduService.getCadreCompleteInfo(new Long(cadreId));
            prepopulateLocations(cadreVo);
        }  
	}    
     
    public void prepopulateLocations(CadreVo  cadreVo)
    {
    		session = request.getSession();
    		System.out.println("inside method populate const");
    		
    		//get districts
    		List<SelectOptionVO> districtNames_c = new ArrayList<SelectOptionVO>();
	    		 districtNames_c=cadreManagementService.findDistrictsByState("1");			
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
    		
    			
    			constituencynames_c = staticDataService.getLatestConstituenciesByStateIdAndType(new Long(cadreInfo.getState().trim()),IConstants.ASSEMBLY_ELECTION_TYPE);
    			constituencynames_c.add(0, new SelectOptionVO(0l,"Select Location"));
    			
    			session.setAttribute(ISessionConstants.CONSTITUENCIES, constituencynames_c);
    		
			
			/*List<SelectOptionVO> mandals_c=regionServiceDataImp.getSubRegionsInConstituency(new Long(cadreInfo.getConstituencyID()),IConstants.PRESENT_YEAR,null);
			SelectOptionVO obj2 = new SelectOptionVO(0L,"Select Mandal");
			mandals_c.add(0, obj2);
			
			
			session.setAttribute(ISessionConstants.MANDALS, mandals_c);
			
			//get villages
			List<SelectOptionVO> villageNames_c = regionServiceDataImp.getHamletsOrWards(new Long(cadreInfo.getMandal()),IConstants.PRESENT_YEAR);
			SelectOptionVO obj3 = new SelectOptionVO(0L,"Select Village");
			villageNames_c.add(0, obj3);
			
			session.setAttribute(ISessionConstants.VILLAGES, villageNames_c);
			String areaFlag = cadreInfo.getMandal().substring(0,1);
			Long id = new Long(cadreInfo.getMandal().substring(1));
			Long localElecId = null;
			Long tehsilId = null;
			if(areaFlag.equalsIgnoreCase(IConstants.URBAN_TYPE)){
				localElecId = id;
			}else{
				tehsilId = id;
			}*/
			List<SelectOptionVO> boothsList_c = mahaNaduService.getBoothsInAConstituency(new Long(cadreInfo.getConstituencyID()),10l,null,null);
			if(boothsList_c != null){
				 obj = new SelectOptionVO(0L,"Select Booth");
				boothsList_c.add(0, obj);
			}
			session.setAttribute(ISessionConstants.BOOTHS, boothsList_c);
		
	}

    public String getBooths(){
      try{
    	jObj = new JSONObject(getTask());
    	//Long locationId = jObj.getLong("id");
		Long constituencyId = jObj.getLong("constId");
		//String areaFlag = locationId.toString().substring(0,1);
		//Long id = new Long(locationId.toString().substring(1));
		//Long localElecId = null;
		// tehsilId = null;
		//if(areaFlag.equalsIgnoreCase(IConstants.URBAN_TYPE)){
		//	localElecId = id;
		//}else{
		//	tehsilId = id;
		//}
			
		boothsList =mahaNaduService.getBoothsInAConstituency(constituencyId,10l,null,null);
      }catch(Exception e){
    	  LOG.error("Exception rised in getBooths", e);
      }
      return Action.SUCCESS;
    }

    public String saveOrUpdataCadre(){
      try{
    	ResultStatus result = mahaNaduService.saveCadreInfoForMahaNadu(cadreVo);
    	
		if(result.getResultCode() == ResultCodeMapper.SUCCESS){
			log.debug("fileuploades is sucess Method");
			inputStream = new StringBufferInputStream(SUCCESS);
		}
		else
			inputStream = new StringBufferInputStream("fail");
      }catch(Exception e){
    	  LOG.error("Exception rised in saveOrUpdataCadre", e);
      }
    	return Action.SUCCESS;
    }
}
