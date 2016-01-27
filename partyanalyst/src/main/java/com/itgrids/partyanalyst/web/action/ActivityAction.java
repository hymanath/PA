package com.itgrids.partyanalyst.web.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ActivityAttendanceInfoVO;
import com.itgrids.partyanalyst.dto.ActivityAttendanceVO;
import com.itgrids.partyanalyst.dto.ActivityVO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.EventFileUploadVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.MobileUserVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SearchAttributeVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IActivityAttendanceService;
import com.itgrids.partyanalyst.service.IActivityService;
import com.itgrids.partyanalyst.service.IAttendanceService;
import com.itgrids.partyanalyst.service.IMobileService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Srishailam Pittala 
 *
 */
/**
 * @author Client
 *
 */
public class ActivityAction extends ActionSupport implements ServletRequestAware{

	private static final Logger         		LOG = Logger.getLogger(ActivityAction.class);
	private HttpServletRequest         			request;
	private HttpSession 						session;
	private IActivityService                    activityService;
	private JSONObject							jObj;
	private String 								task;
	private ActivityVO							activityVO;
	private EntitlementsHelper 					entitlementsHelper;
	private BasicVO 							basicVO = new BasicVO();
	private List<IdNameVO>  					idNameVOList;
	private ResultStatus resultStatus;
	private Long activityScopeId;
	private Long locationValue;
	private Long activityLevel;
	private String locationName;
	private String temp;
	private List<BasicVO> basicVOList;
	private IAttendanceService attendanceService;
	private List<String> dates;
	private IActivityAttendanceService activityAttendanceService;
	private ActivityAttendanceInfoVO attendanceVo;
	private Long					 activityLocationInfoId;
	private IMobileService			mobileService;
	private List<MobileUserVO> 		mobileUserVoList;
	private MobileUserVO 			mobileUserVO;
	
	
	public MobileUserVO getMobileUserVO() {
		return mobileUserVO;
	}
	public void setMobileUserVO(MobileUserVO mobileUserVO) {
		this.mobileUserVO = mobileUserVO;
	}
	public IMobileService getMobileService() {
		return mobileService;
	}
	public void setMobileService(IMobileService mobileService) {
		this.mobileService = mobileService;
	}
	public List<MobileUserVO> getMobileUserVoList() {
		return mobileUserVoList;
	}
	public void setMobileUserVoList(List<MobileUserVO> mobileUserVoList) {
		this.mobileUserVoList = mobileUserVoList;
	}
	public Long getActivityLocationInfoId() {
		return activityLocationInfoId;
	}
	public void setActivityLocationInfoId(Long activityLocationInfoId) {
		this.activityLocationInfoId = activityLocationInfoId;
	}
	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}
	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}
	public ActivityAttendanceInfoVO getAttendanceVo() {
		return attendanceVo;
	}
	public void setAttendanceVo(ActivityAttendanceInfoVO attendanceVo) {
		this.attendanceVo = attendanceVo;
	}
	public List<String> getDates() {
		return dates;
	}
	public void setDates(List<String> dates) {
		this.dates = dates;
	}
	public IAttendanceService getAttendanceService() {
		return attendanceService;
	}
	public void setAttendanceService(IAttendanceService attendanceService) {
		this.attendanceService = attendanceService;
	}
	public BasicVO getBasicVO() {
		return basicVO;
	}
	public void setBasicVO(BasicVO basicVO) {
		this.basicVO = basicVO;
	}
	public List<IdNameVO> getIdNameVOList() {
		return idNameVOList;
	}
	public void setIdNameVOList(List<IdNameVO> idNameVOList) {
		this.idNameVOList = idNameVOList;
	}
	public ActivityVO getActivityVO() {
		return activityVO;
	}
	public void setActivityVO(ActivityVO activityVO) {
		this.activityVO = activityVO;
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
	public IActivityService getActivityService() {
		return activityService;
	}
	public void setActivityService(IActivityService activityService) {
		this.activityService = activityService;
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
	
	
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	
	public Long getActivityScopeId() {
		return activityScopeId;
	}
	public void setActivityScopeId(Long activityScopeId) {
		this.activityScopeId = activityScopeId;
	}
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	public Long getActivityLevel() {
		return activityLevel;
	}
	public void setActivityLevel(Long activityLevel) {
		this.activityLevel = activityLevel;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	
	public List<BasicVO> getBasicVOList() {
		return basicVOList;
	}
	public void setBasicVOList(List<BasicVO> basicVOList) {
		this.basicVOList = basicVOList;
	}
	public IActivityAttendanceService getActivityAttendanceService() {
		return activityAttendanceService;
	}
	public void setActivityAttendanceService(
			IActivityAttendanceService activityAttendanceService) {
		this.activityAttendanceService = activityAttendanceService;
	}
	public String execute()
	{
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		if(regVO==null){
			return "input";
		}
		boolean noaccess = false;
		if(!(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"PARTY_ACTIVITY_UPDATE") || 
				entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"ACTIVITIES_DASHBOARD_ENTITLEMENT"))){
			noaccess = true ;
		}
		
		if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
			noaccess = false;
		}
		if(noaccess){
			return "error";
		}

		basicVO = activityService.getActivityTypeList();
		idNameVOList = activityService.getActivityLevelsList();
				
		return Action.SUCCESS;
	}
	
	public String getActivityDetailsBySearchCriteria()
	{
		
		try {
			/*RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			boolean noaccess = false;
			if(regVO == null)
				return Action.ERROR;*/
			
			jObj = new JSONObject(getTask());
			String searchType = jObj.getString("searchType");
			Long locationId = jObj.getLong("locationId");
			Long locationValue = jObj.getLong("stateId");
			Long activityLevelId = jObj.getLong("activityLevelId");
			Long activityScopeId = jObj.getLong("activityScopeId");
			String conditionType = jObj.getString("conditionType");
			String startDateStr = jObj.getString("startDate");
			String endDateStr = jObj.getString("endDate");
			String teamSearchType = jObj.getString("teamSearchType");
			Long teamLeaderId = jObj.getLong("teamLeaderId");
			Long teamMemberId = jObj.getLong("teamMemberId");
			String radioSearch = jObj.getString("radioSearch");
			Long districtId = jObj.getLong("districtId");
			
			
			SearchAttributeVO searchVO = new SearchAttributeVO();			
			searchVO.setTypeId(locationValue);
			searchVO.setSearchType(searchType);
			searchVO.setLocationId(locationId);
			searchVO.setLocationValue(locationValue);
			searchVO.getAttributesIdsList().add(activityScopeId);
			searchVO.setLevelId(activityLevelId);
			searchVO.setConditionType(conditionType);
			searchVO.setTeamSearchType(teamSearchType);
			searchVO.setTeamLeaderId(teamLeaderId);
			searchVO.setTeamMemberId(teamMemberId);
			searchVO.setRadioSearch(radioSearch);
			searchVO.setDistrictId(districtId);
			if(searchVO.getSearchType() != null && searchVO.getConditionType().trim().contains("daywiseResult"))
			searchVO.setCallFrom(jObj.getString("callFrom"));
			
			try {
				SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
				searchVO.setStartDate(format.parse(startDateStr));
				searchVO.setEndDate(format.parse(endDateStr));
			} catch (Exception e) {}
			
			activityVO = activityService.getActivityDetailsBySearchCriteria(searchVO,1L,false);
			
		} catch (Exception e) {
			LOG.error("Exception occured when loading getActivityDetailsBySearchCriteria() ActivityAction Controller... ",e);
		}
		return Action.SUCCESS;
	}
	
	public String eventsUploadForm()
	{
		try{
			
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			
			
			EventFileUploadVO eventFileUploadVO = new EventFileUploadVO();
			eventFileUploadVO.setUserId(regVO.getRegistrationID());
			eventFileUploadVO.setUpdatedBy(regVO.getRegistrationID());
			
		       MultiPartRequestWrapper multiPartRequestWrapper = (MultiPartRequestWrapper)request;
		       Enumeration<String> fileParams = multiPartRequestWrapper.getFileParameterNames();
		       String fileUrl = "" ;
		       List<String> filePaths = null;
		   		while(fileParams.hasMoreElements())
		   		{
		   			String key = fileParams.nextElement();
		   			
				   			File[] files = multiPartRequestWrapper.getFiles(key);
				   			filePaths = new ArrayList<String>();
				   			if(files != null && files.length > 0)
				   			for(File f : files)
				   			{
				   				String[] extension  =multiPartRequestWrapper.getFileNames(key)[0].split("\\.");
				   	            String ext = "";
				   	            if(extension.length > 1){
				   	              ext = extension[extension.length-1];
				   	           eventFileUploadVO.setFileExtension(ext);
				   	        eventFileUploadVO.setFile(f);
				   	            }
				   			}
		   		}
		     
		   		Enumeration enumeration= request.getParameterNames();
				while (enumeration.hasMoreElements()) {
					String reqParamName = (String) enumeration.nextElement();
					Object reqValue = request.getParameter(reqParamName) ;
					
				if(reqParamName != null && reqValue != null && !reqValue.toString().equalsIgnoreCase("undefined"))
				{	
					if(reqParamName.equalsIgnoreCase("levelId")){
						eventFileUploadVO.setLevelId(reqValue!= null?Long.parseLong(reqValue.toString()):0l);
					}
					if(reqParamName.equalsIgnoreCase("levelValue")){
						eventFileUploadVO.setLevelValue(reqValue!= null?Long.parseLong(reqValue.toString()):0l);
					}
					if(reqParamName.equalsIgnoreCase("day")){
						eventFileUploadVO.setDay(reqValue!= null?Long.parseLong(reqValue.toString()):0l);
					}
					if(reqParamName.equalsIgnoreCase("activityScopeId")){
						eventFileUploadVO.setActivityScopeId(reqValue!= null?Long.parseLong(reqValue.toString()):0l);
					}
					if(reqParamName.equalsIgnoreCase("activityDate")){
						eventFileUploadVO.setActivityDate(reqValue!= null?reqValue.toString():null);
					 }
					if(reqParamName.equalsIgnoreCase("gobalTempVar")){
						eventFileUploadVO.setTemp(reqValue!= null?reqValue.toString():null);
					}
					if(reqParamName.equalsIgnoreCase("insertType")){
						eventFileUploadVO.setInsertType(reqValue!= null?reqValue.toString():null);
					}
					if(reqParamName.equalsIgnoreCase("activityLocationInfoId")){
						eventFileUploadVO.setActivityLocationInfoId(reqValue!= null?Long.valueOf(reqValue.toString()):null);
					}
				  }
					
				}
				resultStatus = activityService.eventsUploadForm(eventFileUploadVO);	
		       
			
		}catch (Exception e) {
			LOG.error("Exception Occured in eventsUploadForm() method, Exception - ",e); 
		}
		return Action.SUCCESS;	
	}
	
	/*public String eventFieUpload()
	{
		try{
			basicVO = activityService.getActivityTypeList();
			idNameVOList = activityService.getActivityLevelsList();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return Action.SUCCESS;	
	}*/
	
	public String deleteUploadedFile()
	{
		try{
			
			jObj = new JSONObject(getTask());
			resultStatus = activityService.deleteEventUploadFilebyActivityInfoDocId(jObj.getString("acitivityInfoDocId"));
			
		}catch (Exception e) {
			LOG.error("Exception Occured in deleteUploadedFile() method, Exception - ",e); 
		}
		return Action.SUCCESS;
	}
	
	public String saveActivityQuestionnaireDetails(){
		
		try {
			
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			
			ActivityVO finalvo = new ActivityVO();
			List<ActivityVO> questionList = new ArrayList<ActivityVO>();
			
			jObj = new JSONObject(getTask());
			Long activityScopeId = jObj.getLong("activityScopeId");
			Long activityLevelId = jObj.getLong("activityLevelId");
			Long activityLevelValue = jObj.getLong("activityLevelValue");
			JSONArray questionArr = jObj.getJSONArray("responseArray");
			
			finalvo.setId(regVO.getRegistrationID());
			finalvo.setActivityTypeId(activityScopeId);
			finalvo.setActivityLevelId(activityLevelId);
			finalvo.setLocationValue(activityLevelValue);
			
			if(questionArr != null && questionArr.length() > 0){
				for (int i = 0; i < questionArr.length(); i++) {
					ActivityVO vo = new ActivityVO();
					
					JSONObject questionObj = questionArr.getJSONObject(i);
					vo.setQuestionId(questionObj.getLong("questionId"));
					vo.setOptionId(questionObj.getLong("optionId"));
					vo.setRemarks(questionObj.getString("remarks"));
					vo.setCount(questionObj.getLong("count"));
					vo.setOthers(questionObj.getString("others"));
					
					questionList.add(vo);
				}
			}
			
			finalvo.setActivityVoList(questionList);
			
			resultStatus = activityService.saveActivityQuestionnaireDetails(finalvo);
			
		} catch (Exception e) {
			LOG.error("Exception Occured in saveActivityQuestionnaireDetails() method, Exception - ",e); 
		}
		
		return Action.SUCCESS;
	}

	
	public String getQuestionnaireForScope(){
		try {
			LOG.info("Entered into getQuestionnaireForScope");
			
			jObj = new JSONObject(getTask());
			
			activityVO = activityService.getQuestionnaireForScope(jObj.getLong("scopeId"),jObj.getLong("requiredAttributeId"));
			
		} catch (Exception e) {
			LOG.error("Exception raised at getQuestionnaireForScope", e);
		}
		return Action.SUCCESS;
	}

	public String getTeamLeadersByActivityScope(){
		try {
			
			jObj = new JSONObject(getTask());
			
			Long activityScopeId = jObj.getLong("activityId");
			
			List<Long> activityScopeIds = new ArrayList<Long>();
			activityScopeIds.add(activityScopeId);
			
			idNameVOList = activityService.getActivityLeadersDetailsByActivityScope(activityScopeIds,false);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getQuestionnaireForScope", e);
		}
		return Action.SUCCESS;
	}
	
	public String getTeamMembersByTeamLeaderAndActivityScope(){
		try {
			
			jObj = new JSONObject(getTask());
			
			Long activityScopeId = jObj.getLong("activityId");
			Long teamLeaderId = jObj.getLong("leaderId");
			
			List<Long> activityScopeIds = new ArrayList<Long>();
			List<Long> teamLeaderIds = new ArrayList<Long>();
			
			activityScopeIds.add(activityScopeId);
			teamLeaderIds.add(teamLeaderId);
			
			idNameVOList = activityService.getTeamMembersByLeaderAndActivityScope(teamLeaderIds,activityScopeIds);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getQuestionnaireForScope", e);
		}
		return Action.SUCCESS;
	}
	
	public String getActivityDocumentsImages()
	{
		try{
			
			jObj = new JSONObject(getTask());
			String tempVar = jObj.getString("tempVar");
			
			basicVOList = activityService.getActivityDocumentsImages(jObj.getLong("levelId"),jObj.getLong("levelValue"),jObj.getLong("day"),jObj.getInt("startIndex"),jObj.getInt("maxIndex"),jObj.getLong("activityScopeId"),jObj.getString("activityDate"),tempVar);
			
		}catch (Exception e) {
			LOG.error("Exception raised at getActivityDocumentsImages()", e);
		}
		return Action.SUCCESS;
	}
	public String saveCadreActivityAttendanceInfo()
	{
		try{
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			jObj = new JSONObject(getTask());
			ActivityAttendanceVO VO = new ActivityAttendanceVO();
			VO.setActivityLocationInfoId(jObj.getLong("activityLocationInfoId"));
			VO.setTdpCadreId(jObj.getLong("tdpCadreId"));
			VO.setActivityDate(jObj.getString("conductedDate"));
			VO.setSyncType("WEB");
			VO.setUserType("C");								// SET TO CADRE BECAUSE -- THERE ARE SEPARATE METHODS FOR PUBLIC AND CADRE SAVING
			
			resultStatus = attendanceService.saveCadreActivityAttendance(VO,regVO.getRegistrationID());	
			
						
		}catch (Exception e) {
			LOG.error("Exception raised at saveCadreActivityAttendanceInfo()", e);
		}
		return Action.SUCCESS;
	}
	
	public String savePublicActivityAttendanceInfo()
	{
		try{
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			jObj = new JSONObject(getTask());
			ActivityAttendanceVO VO = new ActivityAttendanceVO();
			VO.setActivityLocationInfoId(jObj.getLong("activityLocationInfoId"));
			VO.setName(jObj.getString("name"));
			VO.setMobileNumber(jObj.getString("mobileNumber"));
			VO.setVoterCard(jObj.getString("voterCard"));
			VO.setActivityDate(jObj.getString("conductedDate"));
			if(jObj.getLong("tdpCadreId") > 0l)
			VO.setTdpCadreId(jObj.getLong("tdpCadreId"));
			VO.setUserType("P");								// SET TO PUBLIC BECAUSE -- THERE ARE SEPARATE METHODS FOR PUBLIC AND CADRE SAVING
			VO.setSyncType("WEB");
				resultStatus = attendanceService.savePublicActivityAttendance(VO,regVO.getRegistrationID());		
		}catch (Exception e) {
			LOG.error("Exception raised at savePublicActivityAttendanceInfo()", e);
		}
		return Action.SUCCESS;
	}
	
	public String getActivityDatesByScope()
	{
		try{
		
			jObj = new JSONObject(getTask());
			dates =activityService.getActivityDates(jObj.getLong("activityScopeId"));
		}catch (Exception e) {
			LOG.error("Exception raised at getActivityDatesByScope()", e);
		}
		return Action.SUCCESS;
	}
	
	public String getActivityDatesByScopeId(){
		try{
		
			jObj = new JSONObject(getTask());
			dates =activityService.getActivityScopeDates(jObj.getLong("activityScopeId"));
		}catch (Exception e) {
			LOG.error("Exception raised at getActivityDatesByScope()", e);
		}
		return Action.SUCCESS;
	}
	
	public String getRequiredAttributesByActScopeId()
	{
		try{
			jObj = new JSONObject(getTask());
			basicVOList = activityService.getRequiredAttributesListForScope(jObj.getLong("activityScopeId"));
			
		}catch (Exception e) {
			LOG.error("Exception raised at getRequiredAttributesByActScopeId()", e);
		}
		return Action.SUCCESS;
	}

	public String getLocationWiseActivityDetails()
	{
		try{
			
			jObj = new JSONObject(getTask());
			String searchType = jObj.getString("searchType");
			Long locationValue = jObj.getLong("locationValue");
			Long activityScopeId = jObj.getLong("activityScopeId");
			SearchAttributeVO searchVO = new SearchAttributeVO();			
			searchVO.setSearchType(searchType);
			searchVO.setLocationValue(locationValue);
			List<Long>  activityScopeIdsList = new ArrayList<Long>();
			activityScopeIdsList.add(activityScopeId);
			searchVO.setAttributesIdsList(activityScopeIdsList);
			attendanceVo = activityAttendanceService.getLocationWiseActivityDetails(searchVO,0L);
		}catch (Exception e) {
			LOG.error("Exception raised at getLocationWiseActivityDetails()", e);
		}
		return Action.SUCCESS;
	}
	
	public String getActivityLocationWiseDetailsByScopeId(){
		
		try {
			
			jObj = new JSONObject(getTask());
			Long scopeId = jObj.getLong("scopeId");
			
			basicVO = activityService.getActivityLocationWiseDetailsByScopeId(scopeId);
		} catch (Exception e) {
			LOG.error("Exception raised at getActivityLocationWiseDetailsByScopeId()", e);
		}
		return Action.SUCCESS;
	}
	
	public String getActivityDetailsByTdpCadreId(){
		
		try {
			
			jObj = new JSONObject(getTask());
			Long cadreId = jObj.getLong("tdpCadreId");
			
			activityVO = activityService.getActivityDetailsForCadre(cadreId);
		} catch (Exception e) {
			LOG.error("Exception raised at getActivityLocationWiseDetailsByScopeId()", e);
		}
		return Action.SUCCESS;
	}
	
	public String getActivityDetailsByActivityLevelIdAndCadreId(){
		
		try {
			
			jObj = new JSONObject(getTask());
			Long cadreId = jObj.getLong("tdpCadreId");
			Long activityLvlId = jObj.getLong("activityLevelId");
			Long locationId = jObj.getLong("locationId");
			Long boothId = jObj.getLong("cadreBoothId");
			Long panchayatId = jObj.getLong("cadrePanchaytId");
			Long mandalId = jObj.getLong("cadremandalId");
			Long constituencyId = jObj.getLong("cadreConstituencyId");
			Long districtId = jObj.getLong("cadreDistrictId");
			Long stateId = jObj.getLong("cadreStateId");
			
			activityVO = activityService.getActivityDetailsByActivityLevelIdAndCadreId(activityLvlId, cadreId, locationId, boothId, panchayatId, mandalId, constituencyId, districtId, stateId);
		} catch (Exception e) {
			LOG.error("Exception raised at getActivityLocationWiseDetailsByScopeId()", e);
		}
		return Action.SUCCESS;
	}
	
	public String pollingManagment()
	{
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		if(regVO==null){
			return "input";
		}
		boolean noaccess = false;
		if(!(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"POLLING_MANAGEMENT_ENTITLEMENT") || 
				entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"POLLING_MANAGEMENT_ENTITLEMENT_ADMIN"))){
			noaccess = true ;
		}
		
		if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
			noaccess = false;
		}
		if(noaccess){
			return "error";
		}
		Long userId = regVO.getRegistrationID();
		
		idNameVOList = activityService.getAccessValuesOfUserId(userId);
				
		return Action.SUCCESS;
	}
	
	public String getAccessValuesOfUserId(){
		
		try {
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO==null){
				return "input";
			}
			Long userId = regVO.getRegistrationID();
			
			idNameVOList = activityService.getAccessValuesOfUserId(userId);
		} catch (Exception e) {
			LOG.error("Exception raised at getActivityLocationWiseDetailsByScopeId()", e);
		}
		return Action.SUCCESS;
	}
	
	public String getLocationWiseDetails(){
		
		try {
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO==null){
				return "input";
			}
			
			jObj = new JSONObject(getTask());
			
			String fromDateStr = jObj.getString("fromDate");
			String toDateStr = jObj.getString("toDate");
			JSONArray locationArr = jObj.getJSONArray("locationIds");
			String locationType = jObj.getString("locationType");
			List<Long> locationIds = new ArrayList<Long>();
			
			if(locationArr != null && locationArr.length() > 0){
				for (int i = 0; i < locationArr.length(); i++) {
					Long locationId = (long) locationArr.getInt(i);
					locationIds.add(locationId);
				}
			}
			
			mobileUserVoList = mobileService.locationWiseOverView(fromDateStr,toDateStr,locationIds,locationType);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getActivityLocationWiseDetailsByScopeId()", e);
		}
		return Action.SUCCESS;
	}

	public String getTotalDetails(){
		
		try {
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO==null){
				return "input";
			}
			
			jObj = new JSONObject(getTask());
			
			String fromDateStr = jObj.getString("fromDate");
			String toDateStr = jObj.getString("toDate");
			
			mobileUserVO = mobileService.overAllDivisionsSummary(fromDateStr,toDateStr);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getActivityLocationWiseDetailsByScopeId()", e);
		}
		return Action.SUCCESS;
	}
}
