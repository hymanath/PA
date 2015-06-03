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
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dto.CadreCommitteeMemberVO;
import com.itgrids.partyanalyst.dto.CadreVo;
import com.itgrids.partyanalyst.dto.InviteesVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.TdpCadreVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.IMahaNaduService;
import com.itgrids.partyanalyst.service.IPartyStrengthService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.itgrids.partyanalyst.service.impl.CadreCommitteeService;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.itgrids.partyanalyst.service.impl.RegionServiceDataImp;
import com.itgrids.partyanalyst.util.IWebConstants;
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
	private static final Logger LOG = Logger.getLogger(MahaNaduAction.class);
	InputStream inputStream;
	private ServletContext context;
	private HttpServletRequest request;
	private HttpSession session;
	private CadreVo cadreVo,populateVo;
	JSONObject jObj = null;
	private String task = null;
	 private EntitlementsHelper entitlementsHelper;
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
	private String partyDesigIds="";
	private String govtDesigIds="";
	//private RegistrationVO registrationVO;
	
	private IConstituencyDAO constituencyDAO;
	private List<TdpCadreVO> commiteeMembersList = new ArrayList<TdpCadreVO>();
	private CadreCommitteeService cadreCommitteeService;
	
	
	public List<TdpCadreVO> getCommiteeMembersList() {
		return commiteeMembersList;
	}

	public void setCommiteeMembersList(List<TdpCadreVO> commiteeMembersList) {
		this.commiteeMembersList = commiteeMembersList;
	}

	public CadreCommitteeService getCadreCommitteeService() {
		return cadreCommitteeService;
	}

	public void setCadreCommitteeService(CadreCommitteeService cadreCommitteeService) {
		this.cadreCommitteeService = cadreCommitteeService;
	}

	public CadreVo getPopulateVo() {
		return populateVo;
	}

	public void setPopulateVo(CadreVo populateVo) {
		this.populateVo = populateVo;
	}

	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public String getPartyDesigIds() {
		return partyDesigIds;
	}

	public void setPartyDesigIds(String partyDesigIds) {
		this.partyDesigIds = partyDesigIds;
	}

	public String getGovtDesigIds() {
		return govtDesigIds;
	}

	public void setGovtDesigIds(String govtDesigIds) {
		this.govtDesigIds = govtDesigIds;
	}

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
		if(LOG.isDebugEnabled())
			LOG.debug("MahaNaduAction.execute() start");
	  try{
		session = request.getSession();
		
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		if(regVO==null)
			return INPUT;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),"MAHANADU")){
			return ERROR;
		}
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
		 
	    if(!(cadreId != null && (Long.valueOf(cadreId)) > 0)){
	    	
		    session.setAttribute(ISessionConstants.CONSTITUENCIES,new ArrayList<SelectOptionVO>());
			//session.setAttribute(ISessionConstants.MANDALS,new ArrayList<SelectOptionVO>());	
			//session.setAttribute(ISessionConstants.VILLAGES, new ArrayList<SelectOptionVO>());
			session.setAttribute(ISessionConstants.BOOTHS,new ArrayList<SelectOptionVO>());	
		
	    }
			
			
			gender = new ArrayList<String>();
			gender.add("Male");
			gender.add("Female");
			session.setAttribute(ISessionConstants.GENDERS, gender);
			
			List<SelectOptionVO> cadreVerified = new ArrayList<SelectOptionVO>();
			SelectOptionVO no = new SelectOptionVO();
			SelectOptionVO yes = new SelectOptionVO();
			cadreVerified.add(no);
			cadreVerified.add(yes);
			no.setId(1l);
			no.setName("NO");
			
			yes.setId(2l);
			yes.setName("YES");
			
			session.setAttribute("cadreVerified", cadreVerified);
			
			
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
		
		
			List<SelectOptionVO> casteCategory = mahaNaduService.getCasteCategories();
			session.setAttribute("casteCategory", casteCategory);
		
		
		if(session.getAttribute("partyDesig") == null){
			List<SelectOptionVO> partyDesig = mahaNaduService.getPartyDesignations();
			session.setAttribute("partyDesig",partyDesig);
		}
		
		if(session.getAttribute("govDesig") == null){
			List<SelectOptionVO> govDesig = mahaNaduService.getgovernmentDesignations();
			session.setAttribute("govDesig",govDesig);	
		}
			
		constituencyList = staticDataService.getLatestConstituenciesByStateId(1L);
	  }catch(Exception e){
		  LOG.error(" exception occured in execute() in mahanaduAction class.",e);
	  }
		return Action.SUCCESS;
	}

	public void prepare() throws Exception {
	 try{	
		   cadreId = request.getParameter("cadreId");
	       if(cadreId != null && (Long.valueOf(cadreId)) > 0)
	        {	
	        	//cadreInfo = cadreManagementService.getCadreCompleteInfo(Long.valueOf(cadreId));
	    	   cadreVo = mahaNaduService.getCadreCompleteInfo(Long.valueOf(cadreId));
	            prepopulateLocations(cadreVo);
	        } 
		}catch(Exception e){
			LOG.error(" exception occured in prepare() in mahanaduAction class.",e);
		}
	}    
     
    public void prepopulateLocations(CadreVo  cadreVo)
    {
    	
    
    		session = request.getSession();
    		LOG.info("inside method populate const");
    		
    		//get districts
    		List<SelectOptionVO> districtNames_c = new ArrayList<SelectOptionVO>();
	    		 districtNames_c=cadreManagementService.findDistrictsByState("1");			
	    		SelectOptionVO obj = new SelectOptionVO(0L,"Select District");
	    		districtNames_c.add(0, obj);
    		
    		session.setAttribute(ISessionConstants.DISTRICTS, districtNames_c);
			
    		//get constituencies
    		List<SelectOptionVO> constituencynames_c = new ArrayList<SelectOptionVO>();
    		if(cadreVo.getDistrictId() != null)
    		{
    			constituencynames_c=regionServiceDataImp.getConstituenciesByDistrictID(cadreVo.getDistrictId());	
    			SelectOptionVO obj1 = new SelectOptionVO(0L,"Select Constituency");
    			constituencynames_c.add(0, obj1);      
    			
    			session.setAttribute(ISessionConstants.CONSTITUENCIES, constituencynames_c);
    		}
    		
    			
    			
    		
			
			/*List<SelectOptionVO> mandals_c=regionServiceDataImp.getSubRegionsInConstituency(Long.valueOf(cadreInfo.getConstituencyID()),IConstants.PRESENT_YEAR,null);
			SelectOptionVO obj2 = new SelectOptionVO(0L,"Select Mandal");
			mandals_c.add(0, obj2);
			
			
			session.setAttribute(ISessionConstants.MANDALS, mandals_c);
			
			//get villages
			List<SelectOptionVO> villageNames_c = regionServiceDataImp.getHamletsOrWards(Long.valueOf(cadreInfo.getMandal()),IConstants.PRESENT_YEAR);
			SelectOptionVO obj3 = new SelectOptionVO(0L,"Select Village");
			villageNames_c.add(0, obj3);
			
			session.setAttribute(ISessionConstants.VILLAGES, villageNames_c);
			String areaFlag = cadreInfo.getMandal().substring(0,1);
			Long id = Long.valueOf(cadreInfo.getMandal().substring(1));
			Long localElecId = null;
			Long tehsilId = null;
			if(areaFlag.equalsIgnoreCase(IConstants.URBAN_TYPE)){
				localElecId = id;
			}else{
				tehsilId = id;
			}*/
    		if(cadreVo.getConstituencyId() != null){
				List<SelectOptionVO> boothsList_c = mahaNaduService.getBoothsInAConstituency(cadreVo.getConstituencyId(),10l,null,null);
				if(boothsList_c != null){
					 obj = new SelectOptionVO(0L,"Select Booth");
					boothsList_c.add(0, obj);
				}
				session.setAttribute(ISessionConstants.BOOTHS, boothsList_c);
    		}
    	
	}

    public String getBooths(){
      try{
    	jObj = new JSONObject(getTask());
    	//Long locationId = jObj.getLong("id");
		Long constituencyId = jObj.getLong("constId");
		//String areaFlag = locationId.toString().substring(0,1);
		//Long id = Long.valueOf(locationId.toString().substring(1));
		//Long localElecId = null;
		// tehsilId = null;
		//if(areaFlag.equalsIgnoreCase(IConstants.URBAN_TYPE)){
		//	localElecId = id;
		//}else{
		//	tehsilId = id;
		//}
			
		boothsList =mahaNaduService.getBoothsInAConstituency(constituencyId,10l,null,null);
		if(boothsList != null){
			SelectOptionVO obj = new SelectOptionVO(0L,"Select Booth");
			boothsList.add(0, obj);
		}
      }catch(Exception e){
    	  LOG.error("Exception rised in getBooths", e);
      }
      return Action.SUCCESS;
    }

    public String saveOrUpdataCadre(){
      try{
    	  session = request.getSession();
  		
  		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
  		if(regVO == null){
  			inputStream = new StringBufferInputStream("logout");
  			return Action.SUCCESS;
  		}
  		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),"MAHANADU")){
  			inputStream = new StringBufferInputStream("noaccess");
  			return Action.SUCCESS;
		}
    	  cadreVo.setUserId(regVO.getRegistrationID());
    	  if(partyDesigIds != null && partyDesigIds.length() > 0){
    		  List<Long> pIds = new ArrayList<Long>();
    		  String[] ids = partyDesigIds.split(",");
    		  for(String id:ids){
    			  pIds.add(Long.parseLong(id));
    		  }
    		  cadreVo.setPartyDesignationList(pIds);
    	  }
    	  
    	  if(govtDesigIds != null && govtDesigIds.length() > 0){
    		  List<Long> govIds = new ArrayList<Long>();
    		  String[] ids = govtDesigIds.split(",");
    		  for(String id:ids){
    			  govIds.add(Long.parseLong(id));
    		  }
    		  cadreVo.setGovtDesignationList(govIds);
    	  }
    	  cadreVo.setPath(IWebConstants.STATIC_CONTENT_FOLDER_URL);
    	ResultStatus result = mahaNaduService.saveCadreInfoForMahaNadu(cadreVo);
    	
		if(result.getResultCode() == ResultCodeMapper.SUCCESS){
			LOG.debug("fileuploades is sucess Method");
			if(cadreVo.getCadreId() != null && cadreVo.getCadreId().longValue() > 0 ){
				 inputStream = new StringBufferInputStream("update");
			}else{
			  inputStream = new StringBufferInputStream(SUCCESS);
			}
		}
		else
			inputStream = new StringBufferInputStream("fail");
      }catch(Exception e){
    	  inputStream = new StringBufferInputStream("fail");
    	  LOG.error("Exception rised in saveOrUpdataCadre", e);
      }
    	return Action.SUCCESS;
    }
    


    public String searchCadreInfo(){
    try {
		HttpSession session = request.getSession();
		RegistrationVO userDetls = (RegistrationVO) session.getAttribute("USER");
		if(userDetls == null){
			cadreVo = new CadreVo();
			cadreVo.setFirstName("logout");
			return Action.SUCCESS;
		}
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),"MAHANADU")){
			cadreVo = new CadreVo();
			cadreVo.setFirstName("noaccess");
  			return Action.SUCCESS;
		}   	
    	String constituencyId = request.getParameter("cosntituencyId");
    	String searchBy = request.getParameter("searchBy");
    	String sort = request.getParameter("dir");
    	String sortBy = request.getParameter("sort");
    	String startIndex = request.getParameter("startIndex");
    	String maxResult = request.getParameter("results");
    	String searchType = request.getParameter("searchType");
    	
    	cadreVo =  mahaNaduService.searchCadreDetails(userDetls.getRegistrationID(),Long.valueOf(constituencyId),searchBy.trim(),searchType,sort,sortBy,Integer.valueOf(startIndex),Integer.valueOf(maxResult));
	} catch (Exception e) {
		LOG.error(" exception occured in searchCadreInfo() in mahanaduAction class.",e);
	}    
    return Action.SUCCESS;
    }
    
    public String searchVoterInfo(){
    	 try {
    	HttpSession session = request.getSession();
		RegistrationVO userDetls = (RegistrationVO) session.getAttribute("USER");
		if(userDetls == null){
			cadreVo = new CadreVo();
			cadreVo.setFirstName("logout");
			return Action.SUCCESS;
		}
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),"MAHANADU")){
			cadreVo = new CadreVo();
			cadreVo.setFirstName("noaccess");
  			return Action.SUCCESS;
		}   	
    	String boothId = request.getParameter("boothId");
    	String searchBy = request.getParameter("searchName");
    	String sort = request.getParameter("dir");
    	String sortBy = request.getParameter("sort");
    	String startIndex = request.getParameter("startIndex");
    	String maxResult = request.getParameter("results");
    	String searchType  =  request.getParameter("searchType");
    	
    	cadreVo =  mahaNaduService.searchVoterInfo(userDetls.getRegistrationID(),Long.valueOf(boothId),searchBy.trim(),searchType,sort,sortBy,Integer.valueOf(startIndex),Integer.valueOf(maxResult));
	} catch (Exception e) {
		LOG.error(" exception occured in searchVoterInfo() in mahanaduAction class.",e);
	}    
    return Action.SUCCESS;
    }    
    
    public String getDetailToPopulateByVoterIdCardNo()
    {
    	try{
    		jObj = new JSONObject(getTask());
    		populateVo = mahaNaduService.getDetailToPopulate(jObj.getString("voterIdCardNo"),10l);
    	List<SelectOptionVO> districtNames_c = new ArrayList<SelectOptionVO>();
   		districtNames_c=cadreManagementService.findDistrictsByState("1");			
   		SelectOptionVO obj = new SelectOptionVO(0L,"Select District");
   		districtNames_c.add(0, obj);
   		List<SelectOptionVO> constituencynames_c = new ArrayList<SelectOptionVO>();
   		if(populateVo.getDistrictId() != null)
		{
			constituencynames_c=regionServiceDataImp.getConstituenciesByDistrictID(populateVo.getDistrictId());	
			SelectOptionVO obj1 = new SelectOptionVO(0L,"Select Constituency");
			constituencynames_c.add(0, obj1);    
    	}
   		List<SelectOptionVO> boothsList_c  = new ArrayList<SelectOptionVO>();
   		if(populateVo.getConstituencyId() != null){
			 boothsList_c = mahaNaduService.getBoothsInAConstituency(populateVo.getConstituencyId(),10l,null,null);
			if(boothsList_c != null){
				 obj = new SelectOptionVO(0L,"Select Booth");
				boothsList_c.add(0, obj);
			}
   		}
   		populateVo.setDistricts(districtNames_c);
   		populateVo.setConstituencies(constituencynames_c);
   		populateVo.setBooths(boothsList_c);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	return Action.SUCCESS;
    }

    public String getPanchayatsByCosntiId(){
    	
    	try{
    		jObj = new JSONObject(getTask());
        	designationsList = staticDataService.getPanchayatiesByConstituencyId(jObj.getLong("constiId"));
        	if(designationsList != null){
        	
        		SelectOptionVO obj = new SelectOptionVO(0L,"Select Panchayat");
        		designationsList.add(0, obj);
   			
        	}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	return Action.SUCCESS;
    	
    }
    
	public String createMahanaduInvitees()
	{
    	try{
    		session = request.getSession();    		
    		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
    		if(regVO==null)
    		{
    			return Action.ERROR;
    		}
    		jObj = new JSONObject(getTask());
    		JSONArray  submitArr = jObj.getJSONArray("submitArr");
    		Long stateId = jObj.getLong("stateId");
    		String actionType = jObj.getString("actionType");
    		String stateStr = jObj.getString("stateStr");
    		Long eventId = jObj.getLong("eventId");
    		String reportType = jObj.getString("reportType");
    		List<InviteesVO> inviteeGroupVOList = null;
    		if(submitArr != null && submitArr.length()>0)
    		{
    			 inviteeGroupVOList = new ArrayList<InviteesVO>();
    			for(int i=0;i<submitArr.length();i++)
    			{    				
    				InviteesVO committeeLevelVO = new InviteesVO();
    				JSONObject levelObj = submitArr.getJSONObject(i);
    				JSONArray levelArr = levelObj.getJSONArray("levelArr");
    				committeeLevelVO.setLevelStr(levelObj.getString("levelStr").trim());
    				List<InviteesVO> levelWiseList = new ArrayList<InviteesVO>();
    				if(levelArr != null && levelArr.length()>0)
    				{
    					for(int j=0;j<levelArr.length();j++)
    					{
    						JSONObject commiteeVO = levelArr.getJSONObject(j);
    						InviteesVO vo = new InviteesVO();
    						vo.setName(commiteeVO.getString("searchType"));
    						if(vo.getName().trim().equalsIgnoreCase("CadreCommittee"))
    						{
    							vo.setCommitteeId(commiteeVO.getLong("committeeId"));
    						}    							
    						
    						vo.setLevelId(commiteeVO.getLong("levelId"));
    						vo.setLevelValue(commiteeVO.getLong("levelValue"));
    						vo.setLevelStr(commiteeVO.getString("selectedLevel"));
    						
    			        	vo.setDistrictId(commiteeVO.getLong("districtId"));
    			        	vo.setConstituencyId(commiteeVO.getLong("constituencyId"));
    			        	vo.setMandalId(commiteeVO.getLong("mandalId"));
    			        	vo.setPanchayatId(commiteeVO.getLong("panchayatId"));
    			        	
    						JSONArray rolesIds = commiteeVO.getJSONArray("rolesIds");
    						List<Long> roles = new ArrayList<Long>();
    						if(rolesIds != null && rolesIds.length()>0)
    						{
    							for(int k=0;k<rolesIds.length();k++)
    							{
    								roles.add(Long.valueOf(rolesIds.getInt(k)));
    							}
    						}
    						vo.setRolesIds(roles);
    						
    						levelWiseList.add(vo);
    					}
    				}
    				
    				if(committeeLevelVO.getLevelStr().trim().equalsIgnoreCase("state"))
    				{
    					committeeLevelVO.setStateLevelVOList(levelWiseList);
    				}
    				else if(committeeLevelVO.getLevelStr().trim().equalsIgnoreCase("district"))
    				{
    					committeeLevelVO.setDistrictLevelVOList(levelWiseList);
    				}
    				else if(committeeLevelVO.getLevelStr().trim().equalsIgnoreCase("mandal"))
    				{
    					committeeLevelVO.setMandalLevelVOList(levelWiseList);
    				}
    				else if(committeeLevelVO.getLevelStr().trim().equalsIgnoreCase("village"))
    				{
    					committeeLevelVO.setVillageLevelVOList(levelWiseList);
    				}
    				
    				inviteeGroupVOList.add(committeeLevelVO);
    			}
    		}
    		
    		commiteeMembersList = cadreCommitteeService.getEventInviteesList(regVO.getRegistrationID(),regVO.getAccessType(),regVO.getAccessValue(),stateId,inviteeGroupVOList,eventId,actionType,stateStr,reportType,jObj.getInt("startIndex"),jObj.getInt("maxIndex"));
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	return Action.SUCCESS;
    	
	}
}
