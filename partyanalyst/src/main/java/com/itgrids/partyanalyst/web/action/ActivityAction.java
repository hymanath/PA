
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
import com.itgrids.partyanalyst.dto.ActivityDetailsVO;
import com.itgrids.partyanalyst.dto.ActivityReqAttributesVO;
import com.itgrids.partyanalyst.dto.ActivityResponseVO;
import com.itgrids.partyanalyst.dto.ActivityVO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.EventFileUploadVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.MobileUserVO;
import com.itgrids.partyanalyst.dto.OptionsCountVo;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SearchAttributeVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IActivityAttendanceService;
import com.itgrids.partyanalyst.service.IActivityService;
import com.itgrids.partyanalyst.service.IAttendanceService;
import com.itgrids.partyanalyst.service.IMobileService;
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
	private String eventDateStr;
	private List<BasicVO> basicVOList;
	private IAttendanceService attendanceService;
	private List<String> dates;
	private IActivityAttendanceService activityAttendanceService;
	private ActivityAttendanceInfoVO attendanceVo;
	private Long					 activityLocationInfoId;
	private IMobileService			mobileService;
	private List<MobileUserVO> 		mobileUserVoList;
	private MobileUserVO 			mobileUserVO;
	private Long divisonId;
	private List<OptionsCountVo> 		optnsCountVOList;
	private ActivityResponseVO responseVO;
	private List<ActivityResponseVO> responsevoList;
	private ResultStatus result;
	private List<ActivityVO> activityVOList;
	private String activityLevelTextId;	
	private List<ActivityDetailsVO> resultList;
	private List<ActivityReqAttributesVO> attributeList;
	private String successMsg;
	private List<ActivityDetailsVO> activiyDetailsVOList = new ArrayList<ActivityDetailsVO>();
	
	public String getActivityLevelTextId() {
		return activityLevelTextId;
	}
	public void setActivityLevelTextId(String activityLevelTextId) {
		this.activityLevelTextId = activityLevelTextId;
	}
	public ResultStatus getResult() {
		return result;
	}
	public void setResult(ResultStatus result) {
		this.result = result;
	}
	public List<ActivityResponseVO> getResponsevoList() {
		return responsevoList;
	}
	public void setResponsevoList(List<ActivityResponseVO> responsevoList) {
		this.responsevoList = responsevoList;
	}
	public ActivityResponseVO getResponseVO() {
		return responseVO;
	}
	public void setResponseVO(ActivityResponseVO responseVO) {
		this.responseVO = responseVO;
	}
	public List<OptionsCountVo> getOptnsCountVOList() {
		return optnsCountVOList;
	}
	public void setOptnsCountVOList(List<OptionsCountVo> optnsCountVOList) {
		this.optnsCountVOList = optnsCountVOList;
	}
	public String getEventDateStr() {
		return eventDateStr;
	}
	public void setEventDateStr(String eventDateStr) {
		this.eventDateStr = eventDateStr;
	}
	public Long getDivisonId() {
		return divisonId;
	}
	public void setDivisonId(Long divisonId) {
		this.divisonId = divisonId;
	}
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
	public List<ActivityVO> getActivityVOList() {
		return activityVOList;
	}
	public void setActivityVOList(List<ActivityVO> activityVOList) {
		this.activityVOList = activityVOList;
	}
	public List<ActivityDetailsVO> getResultList() {
		return resultList;
	}
	public void setResultList(List<ActivityDetailsVO> resultList) {
		this.resultList = resultList;
	}
	
	public List<ActivityReqAttributesVO> getAttributeList() {
		return attributeList;
	}
	public void setAttributeList(List<ActivityReqAttributesVO> attributeList) {
		this.attributeList = attributeList;
	}
    public List<ActivityDetailsVO> getActiviyDetailsVOList() {
		return activiyDetailsVOList;
	}
	public void setActiviyDetailsVOList(List<ActivityDetailsVO> activiyDetailsVOList) {
		this.activiyDetailsVOList = activiyDetailsVOList;
	}
	public String getSuccessMsg() {
		return successMsg;
	}
	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}
	public String execute()
	{
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		if(regVO==null){
			return "input";
		}
		boolean noaccess = false;
		List<String> entitlements = null;
		if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
			entitlements = regVO.getEntitlements();
			if(!(entitlements.contains("PARTY_ACTIVITY_UPDATE") || entitlements.contains("ACTIVITIES_DASHBOARD_ENTITLEMENT"))){
				noaccess = true ;
			}
		
		/*if(!(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"PARTY_ACTIVITY_UPDATE") || 
				entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"ACTIVITIES_DASHBOARD_ENTITLEMENT"))){
			noaccess = true ;
		}*/
		
		if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
			noaccess = false;
		}
		if(noaccess){
			return "error";
		}

		basicVO = activityService.getActivityTypeList();
		idNameVOList = activityService.getActivityLevelsList();
		}		
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
			eventFileUploadVO.setEventDateStr(eventDateStr);//date value
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
					if(reqParamName.equalsIgnoreCase("tableName")){
						eventFileUploadVO.setTableName(reqValue!= null?reqValue.toString():null);
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
			String conductedDate = jObj.getString("conductedDate");
			
			finalvo.setId(regVO.getRegistrationID());
			finalvo.setActivityTypeId(activityScopeId);
			finalvo.setActivityLevelId(activityLevelId);
			finalvo.setLocationValue(activityLevelValue);
			finalvo.setConductedDate(conductedDate);
			finalvo.setDateStr(jObj.getString("day"));
			
			if(questionArr != null && questionArr.length() > 0){
				for (int i = 0; i < questionArr.length(); i++) {
					ActivityVO vo = new ActivityVO();
					
					JSONObject questionObj = questionArr.getJSONObject(i);
					vo.setQuestionId(questionObj.getLong("questionId"));
					vo.setOptionId(questionObj.getLong("optionId"));
					vo.setCount(questionObj.getLong("count"));
					vo.setOthers(questionObj.getString("others"));
					
					String remarksValue = questionObj.getString("remarks");
					if(remarksValue != null && !remarksValue.isEmpty() && remarksValue.trim().length()>1)
						vo.setRemarks(remarksValue);
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
			Long selectedDay = jObj.getLong("selectedDay");
			List<Long> seletedDays = new ArrayList<Long>();
			seletedDays.add(selectedDay);
			
			activityVO = activityService.getQuestionnaireForScope(jObj.getLong("scopeId"),jObj.getLong("requiredAttributeId"),jObj.getLong("questionId"),jObj.getLong("optionId"),seletedDays);
			
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
	
	public String getCanditeActivtyAttendanceLocationsDtls(){
		
		try {
			
			jObj = new JSONObject(getTask());
			Long cadreId = jObj.getLong("tdpCadreId");
			Long activityLevelId = jObj.getLong("activityLevelId");
			Long activityScopeId = jObj.getLong("activityScopeId");
			String statusCode = jObj.getString("statusCode");
			
			activityVO = activityService.getCanditeActivtyAttendanceLocationsDtls(cadreId,activityLevelId,activityScopeId,statusCode);
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
		List<String> entitlements = null;
		if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
			entitlements = regVO.getEntitlements();
			if(!(entitlements.contains("POLLING_MANAGEMENT_ENTITLEMENT") || entitlements.contains("POLLING_MANAGEMENT_ENTITLEMENT_ADMIN"))){
				noaccess = true ;
			}
		
		/*if(!(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"POLLING_MANAGEMENT_ENTITLEMENT") || 
				entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"POLLING_MANAGEMENT_ENTITLEMENT_ADMIN"))){
			noaccess = true ;
		}
		*/
		if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
			noaccess = false;
		}
		if(noaccess){
			return "error";
		}
		Long userId = regVO.getRegistrationID();
		
		//idNameVOList = activityService.getAccessValuesOfUserId(userId,"All");
		}		
		return Action.SUCCESS;
	}
	
	public String getAccessValuesOfUserId(){
		
		try {
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO==null){
				return "input";
			}
			Long userId = regVO.getRegistrationID();
			
			jObj = new JSONObject(getTask());
			String type = jObj.getString("type");
			
			idNameVOList = activityService.getAccessValuesOfUserId(userId,type);
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
			JSONArray usersArr = jObj.getJSONArray("usersArr");
			
			List<Long> locationIds = new ArrayList<Long>();
			List<String> usersList = new ArrayList<String>();
			
			if(locationArr != null && locationArr.length() > 0){
				for (int i = 0; i < locationArr.length(); i++) {
					Long locationId = (long) locationArr.getInt(i);
					locationIds.add(locationId);
				}
			}
			
			if(usersArr != null && usersArr.length() > 0){
				for (int i = 0; i < usersArr.length(); i++) {
					String userStr = usersArr.getString(i);
					usersList.add(userStr);
				}
			}
			
			mobileUserVoList = mobileService.locationWiseOverView(fromDateStr,toDateStr,locationIds,locationType,usersList);
			
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
			JSONArray locationArr = jObj.getJSONArray("locationIds");
			JSONArray usersArr = jObj.getJSONArray("usersArr");
			
			List<Long> locationIds = new ArrayList<Long>();
			List<String> usersList = new ArrayList<String>();
			
			if(locationArr != null && locationArr.length() > 0){
				for (int i = 0; i < locationArr.length(); i++) {
					Long locationId = (long) locationArr.getInt(i);
					locationIds.add(locationId);
				}
			}
			
			if(usersArr != null && usersArr.length() > 0){
				for (int i = 0; i < usersArr.length(); i++) {
					String userStr = usersArr.getString(i);
					usersList.add(userStr);
				}
			}
			
			mobileUserVO = mobileService.overAllDivisionsSummary(fromDateStr,toDateStr,locationIds,usersList);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getActivityLocationWiseDetailsByScopeId()", e);
		}
		return Action.SUCCESS;
	}
	
	public String pollManagmentDashboard()
	{
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		if(regVO==null){
			return "input";
		}
		boolean noaccess = false;
		List<String> entitlements = null;
		if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
			entitlements = regVO.getEntitlements();
			if(!(entitlements.contains("POLLING_MANAGEMENT_ENTITLEMENT") || entitlements.contains("POLLING_MANAGEMENT_ENTITLEMENT_ADMIN"))){
				noaccess = true ;
			}
		
		
		/*if(!(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"POLLING_MANAGEMENT_ENTITLEMENT") || 
				entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"POLLING_MANAGEMENT_ENTITLEMENT_ADMIN"))){
			noaccess = true ;
		}*/
		
		if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
			noaccess = false;
		}
		if(noaccess){
			return "error";
		}
		}		
		return Action.SUCCESS;
	}
	
	public String pollManagmentAreaWiseInfo()
	{
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		if(regVO==null){
			return "input";
		}
		boolean noaccess = false;
		List<String> entitlements = null;
		if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
			entitlements = regVO.getEntitlements();
			if(!(entitlements.contains("POLLING_MANAGEMENT_ENTITLEMENT") || entitlements.contains("POLLING_MANAGEMENT_ENTITLEMENT_ADMIN"))){
				noaccess = true ;
			}
		
		
		/*if(!(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"POLLING_MANAGEMENT_ENTITLEMENT") || 
				entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"POLLING_MANAGEMENT_ENTITLEMENT_ADMIN"))){
			noaccess = true ;
		}*/
		
		if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
			noaccess = false;
		}
		if(noaccess){
			return "error";
		}
		Long userId = regVO.getRegistrationID();
		
		//idNameVOList = activityService.getAccessValuesOfUserId(userId,"All");
		}		
		return Action.SUCCESS;
	}
	public String getActivityQuestionAnswerCountReasonWise(){
		try{
		   LOG.info("Enter into getActivityQuestionAnswerCountReasonWise() in ActivityAction");
			jObj = new JSONObject(getTask());
		    idNameVOList=activityService.getActivityStatusDetailsByScopeId(jObj.getLong("scopeId"),jObj.getLong("questionsId"));
		}catch(Exception e){
			LOG.info("Error raised at getActivityQuestionAnswerCountReasonWise() in ActivityAction");
		}
		return Action.SUCCESS;
	}
	
	public String getActivityStatusDetailsByScopeIdAndLocationValue(){
		try{
		   LOG.info("Enter into getActivityStatusDetailsByScopeIdAndLocationValue() in ActivityAction");
			jObj = new JSONObject(getTask());
		    idNameVOList=activityService.getActivityStatusDetailsByScopeIdAndLocationValue(jObj.getLong("scopeId"),jObj.getLong("constituencyId"),jObj.getString("mandalId"),jObj.getString("villageId"),jObj.getLong("questionId"));
		}catch(Exception e){
			LOG.info("Error raised at getActivityStatusDetailsByScopeIdAndLocationValue() in ActivityAction");
		}
		return Action.SUCCESS;
	}
	
public String getQuestions(){
		try{
		   LOG.info("Enter into getQuestions() in ActivityAction");

		   jObj = new JSONObject(getTask());
			Long scopeId = jObj.getLong("scopeId");
			
		    idNameVOList = activityService.getQuestions(scopeId);
		}catch(Exception e){
			LOG.info("Error raised at getQuestions() in ActivityAction");
		}
		return Action.SUCCESS;
	}

public String getOptionsForQuestion(){
	try{
	   LOG.info("Enter into getOptionsForQuestion() in ActivityAction");

	   jObj = new JSONObject(getTask());
		Long questionId = jObj.getLong("questionId");
		
	    idNameVOList = activityService.getOptionsForQuestion(questionId);
	}catch(Exception e){
		LOG.info("Error raised at getOptionsForQuestion() in ActivityAction");
	}
	return Action.SUCCESS;
}
public String getQuestionsForReportType(){
		
		try {
			jObj = new JSONObject(getTask());
			
			Long scopeId = jObj.getLong("scopeId");
			//Long reportType = jObj.getLong("reportType");
			
			idNameVOList = activityService.getQuestionsForReportType(scopeId);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getQuestionsForReportType()", e);
		}
		return Action.SUCCESS;
	}
public String getOptionDetailsForQuestion(){
	
	try {
		jObj = new JSONObject(getTask());
		
		Long scopeId 		= jObj.getLong("scopeId");
		Long reportType 	= jObj.getLong("reportType");
		Long qstnId 		= jObj.getLong("questionId");
		optnsCountVOList = activityService.getOptionDetailsForQuestion(scopeId, reportType, qstnId);
		
	} catch (Exception e) {
		LOG.error("Exception raised at getOptionDetailsForQuestion()", e);
	}
	return Action.SUCCESS;
}
public String getCommentDetails(){
	
	try {
		jObj = new JSONObject(getTask());
		
		Long scopeId 		= jObj.getLong("scopeId");
		Long reportType 	= jObj.getLong("reportType");
		Long qstnId 		= jObj.getLong("questionId");
		Long levelId 		= jObj.getLong("levelId");
		Long reportTypeId 		= jObj.getLong("reportTypeId");
		optnsCountVOList = activityService.getCommentDetails(scopeId, reportType, qstnId, levelId, reportTypeId);
		
	} catch (Exception e) {
		LOG.error("Exception raised at getOptionDetailsForQuestion()", e);
	}
	return Action.SUCCESS;
}

 public String getActivityQuestionnnaireWiseReport(){
	 try {

			jObj = new JSONObject(getTask());
			
			Long stateId = jObj.getLong("stateId");

			Long activityScopeId = jObj.getLong("activityScopeId");
			String searchType = jObj.getString("searchType");
			
			SearchAttributeVO searchvo = new SearchAttributeVO();
			searchvo.setScopeId(activityScopeId);
			searchvo.setSearchType(searchType);
			searchvo.setScopeValue(stateId);
			
			JSONArray activityLevelIdsArr = jObj.getJSONArray("activityLevelIdsArr");
			if(activityLevelIdsArr != null && activityLevelIdsArr.length() > 0){
				for (int i = 0; i < activityLevelIdsArr.length(); i++) {
					searchvo.getAttributesIdsList().add(Long.valueOf(activityLevelIdsArr.get(i).toString()));
				}
			}
			
			JSONArray questionArr = jObj.getJSONArray("questionArr");
			if(questionArr != null && questionArr.length() > 0){
				for (int i = 0; i < questionArr.length(); i++) {
					searchvo.getQuestionnaireIdsList().add(Long.valueOf(questionArr.get(i).toString()));
				}
			}
			
			responseVO = activityService.getActivityQuestionnnaireWiseReport(searchvo);
			
	} catch (Exception e) {
		LOG.error("Exception raised at getActivityQuestionnnaireWiseReport()", e);
	}
	 return Action.SUCCESS;
 }
 
 public String getActivityLocationInfoDetailsByActivityScope(){
	 try {

			jObj = new JSONObject(getTask());
			
			Long activityLvl = jObj.getLong("activityLevelId");
			Long activityScopeId = jObj.getLong("activityScopeId");
			
			JSONArray questionIdsArr = jObj.getJSONArray("questionIds");
			List<Long> questionIds = new ArrayList<Long>(0);
			if(questionIdsArr != null && questionIdsArr.length() > 0){
				for (int i = 0; i < questionIdsArr.length(); i++) {
					questionIds.add(Long.valueOf(questionIdsArr.get(i).toString()));
				}
			}
			
			responsevoList = activityService.getActivityLocationInfoDetailsByActivityScope(activityLvl, activityScopeId, questionIds);
			
	} catch (Exception e) {
		LOG.error("Exception raised at getActivityLocationInfoDetailsByActivityScope()", e);
	}
	 return Action.SUCCESS;
 }
 
 
 	public String getActivityQuestionaryOptionsByActivityDate(){
	 try {

			jObj = new JSONObject(getTask());
			activityVO = activityService.getActivityQuestionaryOptionsByActivityDate(jObj.getString("activityDate"),jObj.getLong("day"),jObj.getLong("activityScopeId"));
			
	} catch (Exception e) {
		LOG.error("Exception raised at getActivityQuestionaryOptionsByActivityDate()", e);
	}
	 return Action.SUCCESS;
 }
 
 public String getquestinaireForRetrieving(){
 		 try {

 				jObj = new JSONObject(getTask());
 				responsevoList = activityService.getquestinaireForRetrieving(jObj.getLong("day"));
 				
 		} catch (Exception e) {
 			LOG.error("Exception raised at getActivityQuestionaryOptionsByActivityDate()", e);
 		}
 		 return Action.SUCCESS;
 	 }
 public String getAllCallingPurpose(){
		 try {

				jObj = new JSONObject(getTask());
				responsevoList = activityService.getAllCallingPurpose();
				
		} catch (Exception e) {
			LOG.error("Exception raised at getAllCallingPurpose()", e);
		}
		 return Action.SUCCESS;
	 }
 public String getCallSuportType(){
	 try {

			jObj = new JSONObject(getTask());
			responsevoList = activityService.getCallSuportType();
			
	} catch (Exception e) {
		LOG.error("Exception raised at getCallSuportType()", e);
	}
	 return Action.SUCCESS;
 }
 public String getCallStatus(){
	 try {

			jObj = new JSONObject(getTask());
			responsevoList = activityService.getCallStatus();
			
	} catch (Exception e) {
		LOG.error("Exception raised at getCallStatus()", e);
	}
	 return Action.SUCCESS;
 }
 public String saveCallerFeedBackDetailsForCadre(){
	 try {
		 RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO==null){
				return "input";
			}
		 jObj = new JSONObject(getTask());
		 Long callPurposeId = jObj.getLong("callPurposeId");
		 Long callStatusId = jObj.getLong("callStatusId");
		 Long callSupportId = jObj.getLong("callSupportTypId");
		 String description = jObj.getString("desription");
		 Long cadreId = jObj.getLong("cadreId");
		 Long calledBy = regVO.getRegistrationID();
		 result = activityService.saveCallerFeedBackDetailsForCadre(callPurposeId,callStatusId,callSupportId,description,cadreId,calledBy);
	} catch (Exception e) {
		LOG.error("Exception raised at getCallStatus()", e);
	}
	 return Action.SUCCESS;
 }
 public String getQustionList(){
	 try{
		 
		 jObj = new JSONObject(getTask());
		 activityVO = activityService.getActivitiesQuesDetails(jObj.getLong("activityId"),jObj.getLong("activityScopeId"),jObj.getString("fromDateStr"),jObj.getString("endDateStr"));
	 }catch(Exception e){
		 LOG.error("Exception raised at getQustionList()", e);
	 }
	 return Action.SUCCESS;
 }
 
 public String getDistrictList(){
	 try{
		 
		 jObj = new JSONObject(getTask());
		    Long activityMemberId = jObj.getLong("activityMemberId");
			Long stateId = jObj.getLong("stateId");
			Long userTypeId = jObj.getLong("userTypeId");
			String startDateStr = jObj.getString("startDate");
			String endDateStr = jObj.getString("endDate");
			
		 activityVOList = activityService.getDistrictNamesByScopeId(jObj.getLong("activityScopeId"),activityMemberId,stateId,userTypeId,startDateStr,endDateStr);
	 }catch(Exception e){
		 LOG.error("Exception raised at getDistrictList()", e);
	 }
	 return Action.SUCCESS;
 }
 public String getConstituencyList(){
	 try{
		 
		 jObj = new JSONObject(getTask());
		 activityVOList = activityService.getConstByDistrictId(jObj.getLong("activityScopeId"),jObj.getLong("districtId"),jObj.getString("fromDate"),jObj.getString("toDate"));
	 }catch(Exception e){
		 LOG.error("Exception raised at getConstituencyList()", e);
	 }
	 return Action.SUCCESS;
 }
 public String getMandalOrMuncpalityList(){
	 try{
		 
		 jObj = new JSONObject(getTask());
		 activityVOList = activityService.getMandOrMuncByconstituencyId(jObj.getLong("activityScopeId"),jObj.getLong("constituencyId"),jObj.getString("fromDate"),jObj.getString("toDate"));
	 }catch(Exception e){
		 LOG.error("Exception raised at getConstituencyList()", e);
	 }
	 return Action.SUCCESS;
 }
 public String getPanchayatOrWardList(){
	 try{
		 
		 jObj = new JSONObject(getTask());
		 activityVOList = activityService.getPanchayatOrWardsByMandalOrMuncId(jObj.getLong("activityScopeId"),jObj.getLong("mandalOrMuncipalityId"),jObj.getString("fromDate"),jObj.getString("toDate"));
	 }catch(Exception e){
		 LOG.error("Exception raised at getConstituencyList()", e);
	 }
	 return Action.SUCCESS;
 }
  public String getQuestionByAcivityScope(){
		try{
		
			jObj = new JSONObject(getTask());
			resultList =activityService.getActivityDayWiseAndNormalQuestionOptionDtls(jObj.getLong("activityScopeId"), jObj.getLong("activityLocationInfoId"),jObj.getString("activityDate"));
		}catch (Exception e) {
			LOG.error("Exception raised at getQuestionByAcivityScope()", e);
		}
		return Action.SUCCESS;
	}
  public String getActivityAttribute(){
		try{
			
			jObj = new JSONObject(getTask());
			attributeList = activityService.getActivityAttribute(jObj.getLong("activityScopeId"));
		}catch (Exception e) {
			LOG.error("Exception raised at getActivityAttribute()", e);
		}
		return Action.SUCCESS;
	}
  public String saveActivityAnswerDetails(){
		try { 
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if (regVO != null ) {
				resultStatus = activityService.saveActivityAnswerDetailsByWeb(activiyDetailsVOList,regVO.getRegistrationID());
				if (resultStatus != null ){
					successMsg = resultStatus.getMessage();
				}
			}
			
		 } catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception raised at saveActivityAnswerDetails", e);
		}
		
		return Action.SUCCESS;
	}
}
