package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SurveyorPersonalInfoVO;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.ISurveyAnalysisService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.itgrids.partyanalyst.service.impl.RegionServiceDataImp;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.ActionSupport;

public class SurveyorProfileSaveAction extends ActionSupport implements ServletRequestAware, ServletResponseAware,ServletContextAware,Preparable,ModelDriven<SurveyorPersonalInfoVO>{

	private static final Logger LOG = Logger.getLogger(SurveyorProfileSaveAction.class);
	private SurveyorPersonalInfoVO surveyorPersonalInfoVO;
	//private List<SelectOptionVO> statesList,districtsList,tehsilsList,townshipsList,villagesList;
	private ISurveyAnalysisService surveyAnalysisService;
	private ResultStatus resultStatus;
	private HttpSession session;
	private HttpServletRequest request;
	private HttpServletResponse response;; 
	private ServletContext context;
	private ICrossVotingEstimationService crossVotingEstimationService;
	private List<SelectOptionVO> constituencyList, userAccessConstituencyList;
	private List<SelectOptionVO> stateList,districtList,eduStatus,occupationsList;
	private List<SelectOptionVO> casteList;
	private IVotersAnalysisService votersAnalysisService;
	private CadreManagementService cadreManagementService;
	private IStaticDataService staticDataService;
	private String task;
	JSONObject jObj;
	private RegionServiceDataImp regionServiceDataImp;
	private List<SelectOptionVO> villagesList;
	private List<SelectOptionVO> tehsilList,hamletList;
	private String surveyorId;
	private Long userAddressId;
	private Long surveyorProfileId;
	private Long updationDetailsId;
	
	public Long getUserAddressId() {
		return userAddressId;
	}

	public void setUserAddressId(Long userAddressId) {
		this.userAddressId = userAddressId;
	}

	public Long getSurveyorProfileId() {
		return surveyorProfileId;
	}

	public void setSurveyorProfileId(Long surveyorProfileId) {
		this.surveyorProfileId = surveyorProfileId;
	}

	public Long getUpdationDetailsId() {
		return updationDetailsId;
	}

	public void setUpdationDetailsId(Long updationDetailsId) {
		this.updationDetailsId = updationDetailsId;
	}

	public String getSurveyorId() {
		return surveyorId;
	}

	public void setSurveyorId(String surveyorId) {
		this.surveyorId = surveyorId;
	}

	public List<SelectOptionVO> getHamletList() {
		return hamletList;
	}

	public void setHamletList(List<SelectOptionVO> hamletList) {
		this.hamletList = hamletList;
	}

	public List<SelectOptionVO> getTehsilList() {
		return tehsilList;
	}

	public void setTehsilList(List<SelectOptionVO> tehsilList) {
		this.tehsilList = tehsilList;
	}

	public List<SelectOptionVO> getVillagesList() {
		return villagesList;
	}

	public void setVillagesList(List<SelectOptionVO> villagesList) {
		this.villagesList = villagesList;
	}

	public RegionServiceDataImp getRegionServiceDataImp() {
		return regionServiceDataImp;
	}

	public void setRegionServiceDataImp(RegionServiceDataImp regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public JSONObject getjObj() {
		return jObj;
	}

	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}

	public ICrossVotingEstimationService getCrossVotingEstimationService() {
		return crossVotingEstimationService;
	}

	public void setCrossVotingEstimationService(
			ICrossVotingEstimationService crossVotingEstimationService) {
		this.crossVotingEstimationService = crossVotingEstimationService;
	}

	public List<SelectOptionVO> getConstituencyList() {
		return constituencyList;
	}

	public void setConstituencyList(List<SelectOptionVO> constituencyList) {
		this.constituencyList = constituencyList;
	}

	public List<SelectOptionVO> getUserAccessConstituencyList() {
		return userAccessConstituencyList;
	}

	public void setUserAccessConstituencyList(
			List<SelectOptionVO> userAccessConstituencyList) {
		this.userAccessConstituencyList = userAccessConstituencyList;
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

	public List<SelectOptionVO> getEduStatus() {
		return eduStatus;
	}

	public void setEduStatus(List<SelectOptionVO> eduStatus) {
		this.eduStatus = eduStatus;
	}

	public List<SelectOptionVO> getOccupationsList() {
		return occupationsList;
	}

	public void setOccupationsList(List<SelectOptionVO> occupationsList) {
		this.occupationsList = occupationsList;
	}

	public List<SelectOptionVO> getCasteList() {
		return casteList;
	}

	public void setCasteList(List<SelectOptionVO> casteList) {
		this.casteList = casteList;
	}

	public IVotersAnalysisService getVotersAnalysisService() {
		return votersAnalysisService;
	}

	public void setVotersAnalysisService(
			IVotersAnalysisService votersAnalysisService) {
		this.votersAnalysisService = votersAnalysisService;
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

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public SurveyorPersonalInfoVO getSurveyorPersonalInfoVO() {
		return surveyorPersonalInfoVO;
	}

	public void setSurveyorPersonalInfoVO(
			SurveyorPersonalInfoVO surveyorPersonalInfoVO) {
		this.surveyorPersonalInfoVO = surveyorPersonalInfoVO;
	}
/*
	public List<SelectOptionVO> getStatesList() {
		return statesList;
	}
	
	public void setStatesList(List<SelectOptionVO> statesList) {
		this.statesList = statesList;
	}
	
	public List<SelectOptionVO> getDistrictsList() {
		return districtsList;
	}

	public void setDistrictsList(List<SelectOptionVO> districtsList) {
		this.districtsList = districtsList;
	}

	public List<SelectOptionVO> getTehsilsList() {
		return tehsilsList;
	}

	public void setTehsilsList(List<SelectOptionVO> tehsilsList) {
		this.tehsilsList = tehsilsList;
	}

	public List<SelectOptionVO> getTownshipsList() {
		return townshipsList;
	}

	public void setTownshipsList(List<SelectOptionVO> townshipsList) {
		this.townshipsList = townshipsList;
	}

	public List<SelectOptionVO> getVillagesList() {
		return villagesList;
	}

	public void setVillagesList(List<SelectOptionVO> villagesList) {
		this.villagesList = villagesList;
	}
*/
	public ISurveyAnalysisService getSurveyAnalysisService() {
		return surveyAnalysisService;
	}

	public void setSurveyAnalysisService(
			ISurveyAnalysisService surveyAnalysisService) {
		this.surveyAnalysisService = surveyAnalysisService;
	}

	public String execute(){
		
		 
		 return Action.SUCCESS;
	}

	public SurveyorPersonalInfoVO getModel() {
		// TODO Auto-generated method stub
			return surveyorPersonalInfoVO;
	}
	
	public String saveSurveyorInfo(){
		//surveyorId = request.getParameter("surveyorId");
		session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		Long userId = 0l;
		if(user == null)
			return ERROR;
		else
	 userId = user.getRegistrationID();

		resultStatus=surveyAnalysisService.saveSurveyorInfo(userId,surveyorPersonalInfoVO);
		if(surveyorPersonalInfoVO.getDistrict() != null && surveyorPersonalInfoVO.getDistrict() >0){
			constituencyList = getRegionServiceDataImp().getConstituenciesByDistrictID(surveyorPersonalInfoVO.getDistrict());
			constituencyList.add(0,new SelectOptionVO(0l,"Select Constituency"));
			}
		if(surveyorPersonalInfoVO.getConstituency() != null && surveyorPersonalInfoVO.getConstituency() >0){
			tehsilList = regionServiceDataImp.getSubRegionsInConstituency(surveyorPersonalInfoVO.getConstituency(), IConstants.PRESENT_YEAR, null);
			tehsilList.add(0,new SelectOptionVO(0l,"Select Mandal"));
			}
			if(surveyorPersonalInfoVO.getVillage() != null && surveyorPersonalInfoVO.getTehsil() >0){
				hamletList = regionServiceDataImp.getHamletsOrWards(new Long(surveyorPersonalInfoVO.getTehsil()), IConstants.PRESENT_YEAR);
				hamletList.add(0,new SelectOptionVO(0l,"Select villiage"));
			}
		return Action.SUCCESS;
	}
	
	public void prepare() throws Exception {
		surveyorId = request.getParameter("surveyorId");
		if(surveyorId !=null && surveyorId !="")
		{
			surveyorPersonalInfoVO = new SurveyorPersonalInfoVO();
			surveyorPersonalInfoVO = surveyAnalysisService.getSurveyorInfoBasedOnSurveyId(new Long(surveyorId));
			if(surveyorPersonalInfoVO.getDistrict() != null && surveyorPersonalInfoVO.getDistrict() >0){
				constituencyList = getRegionServiceDataImp().getConstituenciesByDistrictID(surveyorPersonalInfoVO.getDistrict());
				}
			if(surveyorPersonalInfoVO.getConstituency() != null){
			tehsilList = regionServiceDataImp.getSubRegionsInConstituency(surveyorPersonalInfoVO.getConstituency(), IConstants.PRESENT_YEAR, null);
			tehsilList.add(0,new SelectOptionVO(0l,"Select Mandal"));
			}
			if(surveyorPersonalInfoVO.getVillage() != null){
				hamletList = regionServiceDataImp.getHamletsOrWards(new Long(surveyorPersonalInfoVO.getTehsil()), IConstants.PRESENT_YEAR);
				hamletList.add(0,new SelectOptionVO(0l,"Select villiage"));
			}
		}else {
			surveyorPersonalInfoVO = new SurveyorPersonalInfoVO();
		}
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		if(regVO.getRegistrationID() != null && regVO.getRegistrationID() > 0)
		{
		Long accessValue           = Long.valueOf(regVO.getAccessValue());
		userAccessConstituencyList = crossVotingEstimationService.getConstituenciesForElectionYearAndTypeWithUserAccess(regVO.getRegistrationID(),Long.valueOf(IConstants.PRESENT_ELECTION_YEAR),Long.valueOf(IConstants.ASSEMBLY_ELECTION_TYPE_ID));
		if(constituencyList == null){
		constituencyList           = votersAnalysisService.getConstituencyList(userAccessConstituencyList);
		}
		stateList                  = cadreManagementService.findStatesByCountryID(accessValue.toString());
		districtList               = staticDataService.getDistricts(accessValue);
		eduStatus                  = staticDataService.getAllEducationalQualifications();
		occupationsList            = staticDataService.getAllOccupations();
		casteList                  = surveyAnalysisService.getAllCastesForUser(regVO.getRegistrationID());
		
		constituencyList.add(0, new SelectOptionVO(0L,"Select Constituency"));
		stateList.add(0,new SelectOptionVO(0l,"Select State"));
		districtList.add(0,new SelectOptionVO(0l,"Select District"));
		casteList.add(0,new SelectOptionVO(0l,"Select Caste"));
		eduStatus.add(0,new SelectOptionVO(0l,"Select Education"));
		occupationsList.add(0,new SelectOptionVO(0l,"Select Occupation"));
		//statesList=surveyAnalysisService.getStatesList();
		}
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;		
	}

	public void setServletRequest(HttpServletRequest arg) {
		this.request = arg;
	}
	
	public String AjaxHandler()
	{
		String param;
		param = getTask();
		HttpSession session = request.getSession();
		RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
		if(user == null)
		return ERROR;
		Long userID = user.getRegistrationID();
		if(user.getMainAccountId() != null){
			userID = user.getMainAccountId();
		}
		try{
			jObj = new JSONObject(param);	
			
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in getRequestMessagesForUser() Method,Exception is- "+e);
		}
	
		 if(jObj.getString("task").equalsIgnoreCase("hamletsOrWardsInRegion"))
		{
			Long locationId = jObj.getLong("id");
			List<SelectOptionVO> hamletsOrWards = new ArrayList<SelectOptionVO>();
			if(locationId !=0){
			 hamletsOrWards = getRegionServiceDataImp().getHamletsOrWards(locationId, IConstants.PRESENT_YEAR);
			}
			 hamletsOrWards.add(0, new SelectOptionVO(0l,"Select Location"));
			 setVillagesList(hamletsOrWards);
		}
		 return Action.SUCCESS;
	}
}
