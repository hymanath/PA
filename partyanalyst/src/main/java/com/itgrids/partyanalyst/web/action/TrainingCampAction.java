package com.itgrids.partyanalyst.web.action;

import java.io.File;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.CadreDetailsVO;
import com.itgrids.partyanalyst.dto.CadreFeedbackVO;
import com.itgrids.partyanalyst.dto.CadreVo;
import com.itgrids.partyanalyst.dto.CallBackCountVO;
import com.itgrids.partyanalyst.dto.CallStatusVO;
import com.itgrids.partyanalyst.dto.CallTrackingVO;
import com.itgrids.partyanalyst.dto.FeedbackInputVO;
import com.itgrids.partyanalyst.dto.FeedbackQuestionVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.KeyValueVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.MeetingVO;
import com.itgrids.partyanalyst.dto.PartyMeetingVO;
import com.itgrids.partyanalyst.dto.PartyMeetingWSVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SimpleDetailsVO;
import com.itgrids.partyanalyst.dto.SimpleVO;
import com.itgrids.partyanalyst.dto.TraingCampCallerVO;
import com.itgrids.partyanalyst.dto.TraingCampDataVO;
import com.itgrids.partyanalyst.dto.TrainingCadreVO;
import com.itgrids.partyanalyst.dto.TrainingCampCallStatusVO;
import com.itgrids.partyanalyst.dto.TrainingCampFeedBackDetailsVO;
import com.itgrids.partyanalyst.dto.TrainingCampScheduleVO;
import com.itgrids.partyanalyst.dto.TrainingCampSheduleDetailsVO;
import com.itgrids.partyanalyst.dto.TrainingCampVO;
import com.itgrids.partyanalyst.dto.TrainingMemberVO;
import com.itgrids.partyanalyst.dto.VerifierVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.notification.service.ISchedulerService;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
import com.itgrids.partyanalyst.service.ICadreRegistrationService;
import com.itgrids.partyanalyst.service.ITrainingCampService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;


public class TrainingCampAction  extends ActionSupport implements ServletRequestAware{

	private static final Logger LOG = Logger.getLogger(TrainingCampAction.class);
	
	private HttpServletRequest  request;
	private HttpSession session;
	private JSONObject	jObj;
	private String 		task;
	private ITrainingCampService trainingCampService;
	//private List<TrainingCampScheduleVO> trainingCampScheduleVOs;ISchedulerService
	private List<TraingCampCallerVO> statusCountList;
	private TrainingCampScheduleVO trainingCampScheduleVO;
	private EntitlementsHelper entitlementsHelper;
	private ISchedulerService schedulerService;
	
	private String status;
	private Long purposeId;
	private Long programId;
	private Long campId;
	private Long scheduleId;
	private TrainingMemberVO memberList ;
	private String statusType;
	private String batchId;
	private List<BasicVO> basicList;
	private ResultStatus resultStatus;
	private List<IdNameVO> idNameList;
	private Long availableCount;

	private TrainingCampVO trainingCampVO;
	private TrainingCampCallStatusVO trainingCampCallStatusVO;
	private List<IdNameVO> idnemIdNameVOs;
	private CallBackCountVO callBackCountVO;
	private List<CallStatusVO> retResult;
	private String today;
	private List<LocationWiseBoothDetailsVO> locations;
	private ICadreCommitteeService cadreCommitteeService;
	private Long campCallerId;
	private List<TrainingCampScheduleVO> trainingCampScheduleVOs;
	private Long partyMeetingId;
	private MeetingVO userAccessDetailsVO;
	private PartyMeetingVO meetingDetails;
	private String fileType;
	private List<CadreDetailsVO> finalList;
	
	private List<File> imageForDisplay = new ArrayList<File>();
	private List<String> imageForDisplayContentType = new ArrayList<String>();
	private List<String> imageForDisplayFileName = new ArrayList<String>();
	
	private StringBufferInputStream inputStream;
	private String documentType;
	
	private Long partyMeeting;
	private String partyMeetingType;
	private CadreDetailsVO cadreDetailsVO;
	private List<CallTrackingVO> docsResultList;
	private SimpleVO simpleVO;
	private Map<String,TrainingCampVO> returnResult;
	private CadreFeedbackVO finalVO;
	private List<SimpleVO> simpleVOList;
	private Long pd=0l;//programId
	private Long bd=0l;//batchId
	private Long cd=0l;//campId;
	private String dts;//fromDate&todate
	private List<CadreVo> cadreVoList;
	private List<TrainingCampVO> campVoList;	
	private ICadreRegistrationService cadreRegistrationService;
	private String callFrom;
	private List<FeedbackQuestionVO> quetsionsList;
	
	private Long cadreId;
	private String dates;
	
	private String updateMemberShipNumber;
	private String updateName;
	private String updateMobileNumber;
	private String updateRemarks;
	private String updateStatusId;
	private Long updateFinalyzeMeetingId;
	private List<PartyMeetingWSVO> partyMeetingWSVOList;
	private List<KeyValueVO> keyValueVOlist;
	private List<VerifierVO> verifyVOLst;
	private String minAndMaxDatesStr;
	private List<TrainingCampSheduleDetailsVO> trainingCampVOList;
	private List<SimpleDetailsVO> simpleDetailsVOList;
	private SimpleDetailsVO simpleDetailsVO;

	private List<TrainingCampFeedBackDetailsVO> tainingCampFeedBackList;

	public List<TrainingCampSheduleDetailsVO> getTrainingCampVOList() {
		return trainingCampVOList;
	}

	public void setTrainingCampVOList(List<TrainingCampSheduleDetailsVO> trainingCampVOList) {
		this.trainingCampVOList = trainingCampVOList;
	}
	public List<KeyValueVO> getKeyValueVOlist() {
		return keyValueVOlist;
	}

	public void setKeyValueVOlist(List<KeyValueVO> keyValueVOlist) {
		this.keyValueVOlist = keyValueVOlist;
	}

	public List<PartyMeetingWSVO> getPartyMeetingWSVOList() {
		return partyMeetingWSVOList;
	}

	public void setPartyMeetingWSVOList(List<PartyMeetingWSVO> partyMeetingWSVOList) {
		this.partyMeetingWSVOList = partyMeetingWSVOList;
	}

	public ISchedulerService getSchedulerService() {
		return schedulerService;
	}

	public void setSchedulerService(ISchedulerService schedulerService) {
		this.schedulerService = schedulerService;
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public Long getCadreId() {
		return cadreId;
	}

	public void setCadreId(Long cadreId) {
		this.cadreId = cadreId;
	}

	public List<FeedbackQuestionVO> getQuetsionsList() {
		return quetsionsList;
	}

	public void setQuetsionsList(List<FeedbackQuestionVO> quetsionsList) {
		this.quetsionsList = quetsionsList;
	}

	public String getCallFrom() {
		return callFrom;
	}

	public void setCallFrom(String callFrom) {
		this.callFrom = callFrom;
	}

	public ICadreRegistrationService getCadreRegistrationService() {
		return cadreRegistrationService;
	}

	public void setCadreRegistrationService(
			ICadreRegistrationService cadreRegistrationService) {
		this.cadreRegistrationService = cadreRegistrationService;
	}

	public List<TrainingCampVO> getCampVoList() {
		return campVoList;
	}

	public void setCampVoList(List<TrainingCampVO> campVoList) {
		this.campVoList = campVoList;
	}

	public Long getPd() {
		return pd;
	}

	public void setPd(Long pd) {
		this.pd = pd;
	}

	public Long getBd() {
		return bd;
	}

	public void setBd(Long bd) {
		this.bd = bd;
	}

	public Long getCd() {
		return cd;
	}

	public void setCd(Long cd) {
		this.cd = cd;
	}

	public String getDts() {
		return dts;
	}

	public void setDts(String dts) {
		this.dts = dts;
	}
	public List<CadreVo> getCadreVoList() {
		return cadreVoList;
	}

	public void setCadreVoList(List<CadreVo> cadreVoList) {
		this.cadreVoList = cadreVoList;
	}

	public CadreFeedbackVO getFinalVO() {
		return finalVO;
	}

	public void setFinalVO(CadreFeedbackVO finalVO) {
		this.finalVO = finalVO;
	}

	public Map<String, TrainingCampVO> getReturnResult() {
		return returnResult;
	}

	public void setReturnResult(Map<String, TrainingCampVO> returnResult) {
		this.returnResult = returnResult;
	}

	public List<CallTrackingVO> getDocsResultList() {
		return docsResultList;
	}

	public void setDocsResultList(List<CallTrackingVO> docsResultList) {
		this.docsResultList = docsResultList;
	}

	public Long getPartyMeeting() {
		return partyMeeting;
	}

	public void setPartyMeeting(Long partyMeeting) {
		this.partyMeeting = partyMeeting;
	}

	public String getPartyMeetingType() {
		return partyMeetingType;
	}

	public void setPartyMeetingType(String partyMeetingType) {
		this.partyMeetingType = partyMeetingType;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public StringBufferInputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(StringBufferInputStream inputStream) {
		this.inputStream = inputStream;
	}

	public List<File> getImageForDisplay() {
		return imageForDisplay;
	}

	public void setImageForDisplay(List<File> imageForDisplay) {
		this.imageForDisplay = imageForDisplay;
	}

	public List<String> getImageForDisplayContentType() {
		return imageForDisplayContentType;
	}

	public void setImageForDisplayContentType(
			List<String> imageForDisplayContentType) {
		this.imageForDisplayContentType = imageForDisplayContentType;
	}

	public List<String> getImageForDisplayFileName() {
		return imageForDisplayFileName;
	}

	public void setImageForDisplayFileName(List<String> imageForDisplayFileName) {
		this.imageForDisplayFileName = imageForDisplayFileName;
	}
	
	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public List<CadreDetailsVO> getFinalList() {
		return finalList;
	}

	public void setFinalList(List<CadreDetailsVO> finalList) {
		this.finalList = finalList;
	}

	public MeetingVO getUserAccessDetailsVO() {
		return userAccessDetailsVO;
	}

	public void setUserAccessDetailsVO(MeetingVO userAccessDetailsVO) {
		this.userAccessDetailsVO = userAccessDetailsVO;
	}
	public PartyMeetingVO getMeetingDetails() {
		return meetingDetails;
	}

	public void setMeetingDetails(PartyMeetingVO meetingDetails) {
		this.meetingDetails = meetingDetails;
	}

	public Long getPartyMeetingId() {
		return partyMeetingId;
	}

	public void setPartyMeetingId(Long partyMeetingId) {
		this.partyMeetingId = partyMeetingId;
	}

	public ICadreCommitteeService getCadreCommitteeService() {
		return cadreCommitteeService;
	}

	public void setCadreCommitteeService(
			ICadreCommitteeService cadreCommitteeService) {
		this.cadreCommitteeService = cadreCommitteeService;
	}

	public List<LocationWiseBoothDetailsVO> getLocations() {
		return locations;
	}

	public void setLocations(List<LocationWiseBoothDetailsVO> locations) {
		this.locations = locations;
	}

	public List<CallStatusVO> getRetResult() {
		return retResult;
	}

	public void setRetResult(List<CallStatusVO> retResult) {
		this.retResult = retResult;
	}
	
	public Long getAvailableCount() {
		return availableCount;
	}

	public void setAvailableCount(Long availableCount) {
		this.availableCount = availableCount;
	}

	public TrainingCampVO getTrainingCampVO() {
		return trainingCampVO;
	}

	public void setTrainingCampVO(TrainingCampVO trainingCampVO) {
		this.trainingCampVO = trainingCampVO;
	}

	
	public List<IdNameVO> getIdNameList() {
		return idNameList;
	}

	public void setIdNameList(List<IdNameVO> idNameList) {
		this.idNameList = idNameList;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public List<BasicVO> getBasicList() {
		return basicList;
	}

	public void setBasicList(List<BasicVO> basicList) {
		this.basicList = basicList;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public TrainingMemberVO getMemberList() {
		return memberList;
	}

	public void setMemberList(TrainingMemberVO memberList) {
		this.memberList = memberList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getPurposeId() {
		return purposeId;
	}

	public void setPurposeId(Long purposeId) {
		this.purposeId = purposeId;
	}

	public Long getProgramId() {
		return programId;
	}

	public void setProgramId(Long programId) {
		this.programId = programId;
	}

	public Long getCampId() {
		return campId;
	}

	public void setCampId(Long campId) {
		this.campId = campId;
	}

	public Long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}

	public List<TraingCampCallerVO> getStatusCountList() {
		return statusCountList;
	}

	public void setStatusCountList(List<TraingCampCallerVO> statusCountList) {
		this.statusCountList = statusCountList;
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

	public ITrainingCampService getTrainingCampService() {
		return trainingCampService;
	}

	public void setTrainingCampService(ITrainingCampService trainingCampService) {
		this.trainingCampService = trainingCampService;
	}
	
	public TrainingCampScheduleVO getTrainingCampScheduleVO() {
		return trainingCampScheduleVO;
	}

	public void setTrainingCampScheduleVO(
			TrainingCampScheduleVO trainingCampScheduleVO) {
		this.trainingCampScheduleVO = trainingCampScheduleVO;
	}

	
	public TrainingCampCallStatusVO getTrainingCampCallStatusVO() {
		return trainingCampCallStatusVO;
	}

	public void setTrainingCampCallStatusVO(
			TrainingCampCallStatusVO trainingCampCallStatusVO) {
		this.trainingCampCallStatusVO = trainingCampCallStatusVO;
	}

	public List<IdNameVO> getIdnemIdNameVOs() {
		return idnemIdNameVOs;
	}

	public void setIdnemIdNameVOs(List<IdNameVO> idnemIdNameVOs) {
		this.idnemIdNameVOs = idnemIdNameVOs;
	}
	
	public CallBackCountVO getCallBackCountVO() {
		return callBackCountVO;
	}

	public void setCallBackCountVO(CallBackCountVO callBackCountVO) {
		this.callBackCountVO = callBackCountVO;
	}

	public String getToday() {
		return today;
	}

	public void setToday(String today) {
		this.today = today;
	}
	
	public List<TrainingCampScheduleVO> getTrainingCampScheduleVOs() {
		return trainingCampScheduleVOs;
	}

	public void setTrainingCampScheduleVOs(
			List<TrainingCampScheduleVO> trainingCampScheduleVOs) {
		this.trainingCampScheduleVOs = trainingCampScheduleVOs;
	}

	public Long getCampCallerId() {
		return campCallerId;
	}

	public void setCampCallerId(Long campCallerId) {
		this.campCallerId = campCallerId;
	}

	public String execute(){
		return Action.SUCCESS;
	}
	
	public CadreDetailsVO getCadreDetailsVO() {
		return cadreDetailsVO;
	}

	public void setCadreDetailsVO(CadreDetailsVO cadreDetailsVO) {
		this.cadreDetailsVO = cadreDetailsVO;
	}
    
	public SimpleVO getSimpleVO() {
		return simpleVO;
	}

	public void setSimpleVO(SimpleVO simpleVO) {
		this.simpleVO = simpleVO;
	}
	
	 
	public List<SimpleVO> getSimpleVOList() {
		return simpleVOList;
	}

	public void setSimpleVOList(List<SimpleVO> simpleVOList) {
		this.simpleVOList = simpleVOList;
	}

	public String getUpdateMemberShipNumber() {
		return updateMemberShipNumber;
	}

	public void setUpdateMemberShipNumber(String updateMemberShipNumber) {
		this.updateMemberShipNumber = updateMemberShipNumber;
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public String getUpdateMobileNumber() {
		return updateMobileNumber;
	}

	public void setUpdateMobileNumber(String updateMobileNumber) {
		this.updateMobileNumber = updateMobileNumber;
	}

	public String getUpdateRemarks() {
		return updateRemarks;
	}

	public void setUpdateRemarks(String updateRemarks) {
		this.updateRemarks = updateRemarks;
	}
	
	public Long getUpdateFinalyzeMeetingId() {
		return updateFinalyzeMeetingId;
	}

	public void setUpdateFinalyzeMeetingId(Long updateFinalyzeMeetingId) {
		this.updateFinalyzeMeetingId = updateFinalyzeMeetingId;
	}	
	public String getUpdateStatusId() {
		return updateStatusId;
	}

	public void setUpdateStatusId(String updateStatusId) {
		this.updateStatusId = updateStatusId;
	}

	public List<VerifierVO> getVerifyVOLst() {
		return verifyVOLst;
	}

	public void setVerifyVOLst(List<VerifierVO> verifyVOLst) {
		this.verifyVOLst = verifyVOLst;
	}

	public String getMinAndMaxDatesStr() {
		return minAndMaxDatesStr;
	}

	public void setMinAndMaxDatesStr(String minAndMaxDatesStr) {
		this.minAndMaxDatesStr = minAndMaxDatesStr;
	}

	public List<SimpleDetailsVO> getSimpleDetailsVOList() {
		return simpleDetailsVOList;
	}

	public void setSimpleDetailsVOList(List<SimpleDetailsVO> simpleDetailsVOList) {
		this.simpleDetailsVOList = simpleDetailsVOList;
	}

	public SimpleDetailsVO getSimpleDetailsVO() {
		return simpleDetailsVO;
	}

	public void setSimpleDetailsVO(SimpleDetailsVO simpleDetailsVO) {
		this.simpleDetailsVO = simpleDetailsVO;
	}

	public List<TrainingCampFeedBackDetailsVO> getTainingCampFeedBackList() {
		return tainingCampFeedBackList;
	}

	public void setTainingCampFeedBackList(
			List<TrainingCampFeedBackDetailsVO> tainingCampFeedBackList) {
		this.tainingCampFeedBackList = tainingCampFeedBackList;
	}

	public String callCenterTrainingAdmin()
	{
		try
		{
			RegistrationVO regVO =(RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO!=null){
				Long userId = regVO.getRegistrationID();
			}else{
				return Action.INPUT;
			}
			/*if(entitlementsHelper.checkForEntitlementToViewReport(regVO,"TRAINING_CAMP_CALLER_ADMIN")){
				return Action.SUCCESS;
			}*/
			List<String> entitlements = null;
		    if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
		      entitlements = regVO.getEntitlements();
			if(entitlements.contains("TRAINING_CAMP_CALLER_ADMIN"))
				return Action.SUCCESS;
			
		}
		}catch (Exception e) {
			LOG.error(" Exception occured in callCenterTrainingAdmin method in TrainingCampAction class.",e);
		}
		return Action.SUCCESS;
	}
	 
	public String callCenterTrainingAgent(){
		RegistrationVO regVO =(RegistrationVO) request.getSession().getAttribute("USER");
		if(regVO!=null){
			Long userId = regVO.getRegistrationID();
		}else{
			return Action.INPUT;
		}
		/*if(entitlementsHelper.checkForEntitlementToViewReport(regVO,"TRAINING_CAMP_CALLER") ||
				entitlementsHelper.checkForEntitlementToViewReport(regVO,"TRAINING_CAMP_CALLER_ADMIN") || 
				entitlementsHelper.checkForEntitlementToViewReport(regVO,"TRAINING_CAMP_SUPER_ADMIN") ){
			return Action.SUCCESS;
		}*/
		List<String> entitlements = null;
	    if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
	      entitlements = regVO.getEntitlements();
	      if(entitlements.contains("TRAINING_CAMP_CALLER")||entitlements.contains("TRAINING_CAMP_CALLER_ADMIN")||entitlements.contains("TRAINING_CAMP_SUPER_ADMIN")){
	    	  return Action.SUCCESS;
	      }
	    else
		{
			return Action.ERROR;
		}
	  }
	    return Action.SUCCESS;
		
	}
	
	public String checkLoginForPartyMeeting(){
		/*RegistrationVO regVO =(RegistrationVO) request.getSession().getAttribute("USER");
		if(regVO!=null && regVO.getRegistrationID() >0l){
			return "success";
		}else{
			return "input";
		}*/
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		if(regVO==null){
			return "input";
		}
		boolean noaccess = false;
		/*if(!(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"PARTY_MEETINGS_ENTITLEMENT") || 
				entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"PARTY_MEETINGS_ADMIN_ENTITLEMENT"))){
			noaccess = true ;
		}*/
		
		if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
			noaccess = false;
		}
		if(noaccess){
			return "error";
		}
		
		return Action.SUCCESS;
	}
	
	public String getUserAccessLocationDetails(){
		RegistrationVO regVO =(RegistrationVO) request.getSession().getAttribute("USER");
		if(regVO!=null){
			Long userId = regVO.getRegistrationID();
			userAccessDetailsVO = trainingCampService.getUserAccessLevelAndLocations(userId);
		}else{
			return Action.INPUT;
		}
		return Action.SUCCESS;
	}
	public String getTrainingAdminDashboard(){
		return Action.SUCCESS;
	}
	public String getScheduleCallStatusCount()
	{
		try{
		RegistrationVO regVo =(RegistrationVO) request.getSession().getAttribute("USER");
		Long campCallerId = null;
		jObj = new JSONObject(getTask());
		
		campCallerId = jObj.getLong("campCallerId");
		if(campCallerId == null || campCallerId.longValue() == 0l)
			campCallerId = regVo.getRegistrationID();
		
		statusCountList = trainingCampService.getScheduleCallStatusCount(campCallerId,jObj.getLong("callPurposeId"));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String getScheduleCallMemberDetails()
	{
		try{
			RegistrationVO regVo =(RegistrationVO) request.getSession().getAttribute("USER");
			jObj = new JSONObject(getTask());
			
			TraingCampDataVO inputVo = new TraingCampDataVO();
			inputVo.setCampId(jObj.getLong("campId"));
			inputVo.setProgramId(jObj.getLong("programId"));
			inputVo.setScheduleId(jObj.getLong("scheduleId"));
			inputVo.setPurposeId(jObj.getLong("purposeId"));
			inputVo.setStatus(jObj.getString("status"));
			//inputVo.setUserId(regVo.getRegistrationID());
			inputVo.setUserId(jObj.getLong("campCallerId"));
			inputVo.setBatchId(jObj.getLong("batchId"));
			inputVo.setStatusType(jObj.getString("statusType"));
			inputVo.setDateStr(jObj.getString("toDayDate"));
			if(jObj.getString("task").equalsIgnoreCase("filterWiseCount"))
			{
				inputVo.setDistrictId(jObj.getLong("districtId"));
				inputVo.setConstituencyId(jObj.getLong("constituencyId"));
				inputVo.setMandalId(jObj.getLong("mandalId"));
				inputVo.setVillageId(jObj.getLong("villageId"));
				inputVo.setCommitteeLevelId(jObj.getLong("committeeLevelId"));
				inputVo.setSearchType("filter");
			}
			Integer startIndex = jObj.getInt("startIndex");
			Integer maxIndex = jObj.getInt("maxIndex");
			memberList = trainingCampService.getScheduleCallMemberDetails(inputVo,startIndex,maxIndex);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return Action.SUCCESS;	
	}
	public String getBatchCallStatusCount()
	{
		try{
			Long campCallerId = null;
			RegistrationVO regVo =(RegistrationVO) request.getSession().getAttribute("USER");
			jObj = new JSONObject(getTask());
			campCallerId = jObj.getLong("campCallerId");
			if(campCallerId == null || campCallerId.longValue() == 0l)
				campCallerId = regVo.getRegistrationID();
			statusCountList = trainingCampService.getBatchCallStatusCount(campCallerId,jObj.getLong("callPurposeId"));
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return Action.SUCCESS;
	}
	public String getCallerWiseCallsDetails(){
		
		try{
			jObj=new JSONObject(getTask());
			RegistrationVO regVo =(RegistrationVO) request.getSession().getAttribute("USER");
			String searchType=jObj.getString("searchType");
			String fromDate=jObj.getString("fromdate");
			String toDate=jObj.getString("toDate");
			String agentType = jObj.getString("agentType");
			
			boolean isAdmin=false;
			if(regVo.getIsAdmin().equalsIgnoreCase("true")){
				isAdmin = true;
			}
			
			List<Long> userIds=trainingCampService.getTrainingCampUserTypeIds(regVo.getRegistrationID(),isAdmin); 
			
			if(userIds !=null){
				trainingCampScheduleVO = trainingCampService.getCallerWiseCallsDetails(userIds, searchType, fromDate, toDate,agentType);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return Action.SUCCESS;
	}
	public String getAllPrograms()
	{
		try{
			jObj=new JSONObject(getTask());
			basicList = trainingCampService.getAllPrograms();
		}
	catch(Exception e){
	e.printStackTrace();
	}

	return Action.SUCCESS;
	}
	public String getCampsByProgramId()
	{
		try{
			jObj=new JSONObject(getTask());
			basicList = trainingCampService.getCampsByProgramId(jObj.getLong("programId"));
			
		}
	catch(Exception e){
	e.printStackTrace();
	}

	return Action.SUCCESS;
	}
	public String getSchedulesByCampId()
	{
		try{
			jObj=new JSONObject(getTask());
			basicList = trainingCampService.getSchedulesByCampId(jObj.getLong("campId"));
		}
	catch(Exception e){
	e.printStackTrace();
	}

	return Action.SUCCESS;
	}
	public String getBatchesByScheduleId()
	{
		try{
			jObj=new JSONObject(getTask());
			basicList = trainingCampService.getBatchesByScheduleId(jObj.getLong("scheduleId"));
		}
	catch(Exception e){
	e.printStackTrace();
	}

	return Action.SUCCESS;
	}
	
	public String getScheduleStatusList()
	{
		try{
			jObj=new JSONObject(getTask());
			statusCountList = trainingCampService.getScheduleStatusList();
		}
	catch(Exception e){
	e.printStackTrace();
	}

	return Action.SUCCESS;
	}
	
	
	public String getCallStatusList()
	{
		try{
			jObj=new JSONObject(getTask());
			statusCountList = trainingCampService.getStatusList();
		}
	catch(Exception e){
	e.printStackTrace();
	}

	return Action.SUCCESS;
	}
	public String getTrainingProgramMembersBatchCount(){
		
		try{
			jObj=new JSONObject(getTask());
			String fromDate=jObj.getString("fromdate");
			String toDate=jObj.getString("toDate");
			
			if(fromDate !=null && toDate !=null){
				trainingCampScheduleVO=trainingCampService.getTrainingProgramMembersBatchCount(fromDate,toDate);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return Action.SUCCESS;
	}
public String getScheduleAndConfirmationCallsOfCallerToAgent(){
		
		try{
			jObj=new JSONObject(getTask());
			String fromDate=jObj.getString("fromdate");
			String toDate=jObj.getString("toDate");
			RegistrationVO regVO =(RegistrationVO) request.getSession().getAttribute("USER");
			boolean isAdmin=false;
			if(regVO.getIsAdmin().equalsIgnoreCase("true")){
				isAdmin = true;
			}
			List<Long> userIds=trainingCampService.getTrainingCampUserTypeIds(regVO.getRegistrationID(),isAdmin); 
			
			if(fromDate !=null && toDate !=null){
				trainingCampScheduleVO=trainingCampService.getScheduleAndConfirmationCallsOfCallerToAgent(userIds,fromDate,toDate);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return Action.SUCCESS;
	}
	
	public String getAllCampBatchesAction(){
		
		try{
			idNameList = trainingCampService.getAllTrainingCampsInfoByDistrictIds(null);
		}catch (Exception e) {
			LOG.error(" Exception occured in getAllCampBatchesAction method in TrainingCampAction class.",e);
		}
		
		return Action.SUCCESS;
	
	}
	
	public String getAllProgramsListAction(){
		
		try{
			jObj = new JSONObject(getTask());
			Long campId = jObj.getLong("campId");
			List<Long> campIds = new ArrayList<Long>();
			campIds.add(campId);
			
			idNameList = trainingCampService.getProgrammesDetailsByCamps(campIds);
		}catch (Exception e) {
			LOG.error(" Exception occured in getAllProgramsListAction method in TrainingCampAction class.",e);
		}
		
		return Action.SUCCESS;
	
	}
	
	public String getAllScheduleListAction(){
		
		try{
			jObj = new JSONObject(getTask());
			Long programId = jObj.getLong("programId");
			List<Long> programIds = new ArrayList<Long>();
			programIds.add(programId);
			
			idNameList = trainingCampService.getScheduledDetailsByProgrammes(programIds);
		}catch (Exception e) {
			LOG.error(" Exception occured in getAllScheduleListAction method in TrainingCampAction class.",e);
		}
		
		return Action.SUCCESS;
	
	}
	
	public String saveAllDetailsAction(){
		
		try{
			RegistrationVO regVo =(RegistrationVO) request.getSession().getAttribute("USER");
			jObj = new JSONObject(getTask());
			Long scheduleId = jObj.getLong("scheduleId");
			Long membersCount = Long.parseLong(jObj.getString("membersCount"));
			Long callerId = jObj.getLong("callerId");
			Long callPurposeId = jObj.getLong("callPurposeId");
			
					
			if(callPurposeId == 1){
				List<TrainingCampVO> areasVOList = new ArrayList<TrainingCampVO>();
				JSONArray distarr = jObj.getJSONArray("districtIds");
				if(distarr != null && distarr.length() > 0)
				{
					TrainingCampVO vo = new TrainingCampVO();
					vo.setLocationTypeId(IConstants.DISTRICT_SCOPE_ID);
					for(int i=0; i<distarr.length();i++)
					{
						TrainingCampVO vo1 = new TrainingCampVO();
						
						vo1.setId(Long.parseLong(distarr.get(i).toString()));
						vo.getTrainingCampVOList().add(vo1);
					}
					areasVOList.add(vo);
				}
				
				
				JSONArray parliamentArr = jObj.getJSONArray("parliamentIds");
				if(parliamentArr != null && parliamentArr.length() > 0)
				{
					TrainingCampVO parlVo = new TrainingCampVO();
					parlVo.setLocationTypeId(IConstants.PARLIAMENT_CONSTITUENCY_SCOPE_ID);
					for(int i=0; i<parliamentArr.length();i++)
					{
						TrainingCampVO parlmentVo = new TrainingCampVO();
						
						parlmentVo.setId(Long.parseLong(parliamentArr.get(i).toString()));
						parlVo.getTrainingCampVOList().add(parlmentVo);
					}
					areasVOList.add(parlVo);
				}
					
					JSONArray constarr = jObj.getJSONArray("constiIds");
					if(constarr != null && constarr.length() > 0)
					{
						TrainingCampVO constvo = new TrainingCampVO();
						constvo.setLocationTypeId(IConstants.CONSTITUENCY_SCOPE_ID);
						for(int i=0; i<constarr.length();i++)
						{
							TrainingCampVO vo2 = new TrainingCampVO();
							
							vo2.setId(Long.parseLong(constarr.get(i).toString()));
							constvo.getTrainingCampVOList().add(vo2);
						}
						areasVOList.add(constvo);
					}		
						
						JSONArray mandalarr = jObj.getJSONArray("mandalIds");
						if(mandalarr != null && mandalarr.length() > 0)
						{
							TrainingCampVO mandalvo = new TrainingCampVO();
							mandalvo.setLocationTypeId(IConstants.TEHSIL_SCOPE_ID);
							for(int i=0; i<mandalarr.length();i++)
							{
								TrainingCampVO vo3 = new TrainingCampVO();
								
								vo3.setId(Long.parseLong(mandalarr.get(i).toString()));
								mandalvo.getTrainingCampVOList().add(vo3);
							}
							areasVOList.add(mandalvo);
				}
						
					JSONArray municipalityArr = jObj.getJSONArray("municipalitys");
						if(municipalityArr != null && municipalityArr.length() > 0)
						{
							TrainingCampVO municipalityVo = new TrainingCampVO();
							municipalityVo.setLocationTypeId(IConstants.MUNICIPAL_CORP_GMC_SCOPE_ID);
							for(int i=0; i<municipalityArr.length();i++)
							{
								TrainingCampVO vo3 = new TrainingCampVO();
								
								vo3.setId(Long.parseLong(municipalityArr.get(i).toString()));
								municipalityVo.getTrainingCampVOList().add(vo3);
							}
							areasVOList.add(municipalityVo);
				}
						
				
				resultStatus = trainingCampService.assignMembersToCallerForMemberConfirmation(regVo.getRegistrationID(),scheduleId,membersCount,callerId,callPurposeId,areasVOList);
			}else if(callPurposeId == 2){
				Long batchId = jObj.getLong("batchId");
				List<Long> otherUserIdsList = new ArrayList<Long>(0);
				JSONArray userIdsArr = jObj.getJSONArray("userIds");
				if(userIdsArr != null && userIdsArr.length() > 0)
				{
					
					for(int i=0; i<userIdsArr.length();i++)
					{
						if(!otherUserIdsList.contains(Long.valueOf(userIdsArr.get(i).toString())))
						otherUserIdsList.add(Long.valueOf(userIdsArr.get(i).toString()));
					}
					
				}
				Boolean isOwnMembers = false;
				resultStatus = trainingCampService.assignMembersToCallerForBatchConfirmation(regVo.getRegistrationID(),isOwnMembers,scheduleId,membersCount,callerId,callPurposeId,batchId,otherUserIdsList);
			}
			
		}catch (Exception e) {
			LOG.error(" Exception occured in saveAllDetailsAction method in TrainingCampAction class.",e);
		}
		return Action.SUCCESS;
	}
	
	public String getCampusWiseDateWiseInterestedMembersDetails(){
		
		try{
			
			jObj = new JSONObject(getTask());
			String searchType = jObj.getString("searchType");
			String startDate = jObj.getString("fromdate");
			String endDate = jObj.getString("toDate");
			
			trainingCampVO = trainingCampService.getCampusWiseDateWiseInterestedMembersDetails(searchType,startDate,endDate);
		}catch(Exception e) {
			LOG.error(" Exception occured in getCampusWiseDateWiseInterestedMembersDetails method in TrainingCampAction class.",e);
		}
		return Action.SUCCESS;
	}
	
	public String getCampusWiseBatchWiseMembersDetails(){
		
		try{
			
			List<Long> callerIdsList = new ArrayList<Long>(0);
			jObj = new JSONObject(getTask());
			String searchType = jObj.getString("searchType");
			String startDate = jObj.getString("fromdate");
			String endDate = jObj.getString("toDate");
			
			trainingCampVO = trainingCampService.getCampusWiseBatchWiseMembersDetails(callerIdsList,searchType,startDate,endDate);
		}catch(Exception e) {
			LOG.error(" Exception occured in getCampusWiseBatchWiseMembersDetails method in TrainingCampAction class.",e);
		}
		return Action.SUCCESS;
	}
	
	public String getAvailableMembersCountDetailsAction()
	{
		try{
			jObj = new JSONObject(getTask());
			Long scheduleId = jObj.getLong("schedduleeId");
			Long callerId = jObj.getLong("caallerrId");
			
			memberList = trainingCampService.getAvailableMembersCountDetails(scheduleId,callerId);
			
		}catch (Exception e) {
			LOG.error(" Exception occured in getAvailableMembersCountDetailsAction method in TrainingCampAction class.",e);
		}
		return Action.SUCCESS;
	}
	public String updateCadreStatusForTraining()
	{
		try{
			RegistrationVO regVo = (RegistrationVO) request.getSession().getAttribute("USER");
			jObj = new JSONObject(getTask());
			TrainingCadreVO inputVo = new TrainingCadreVO();
			JSONArray jarr = jObj.getJSONArray("dataArray");
			
			if(jarr != null && jarr.length() > 0)
			{
				for(int i=0;i<jarr.length();i++)
				{
			JSONObject jObj=(JSONObject) jarr.get(i);
			inputVo.setInvitteId(jObj.getLong("inviteeId"));
			inputVo.setInviteeCallerId(jObj.getLong("inviteeCallerId"));
			inputVo.setTdpCadreId(jObj.getLong("tdpCadreId"));
			inputVo.setBatchId(jObj.getLong("batchId"));
			inputVo.setRamarks(jObj.getString("ramarks"));
			inputVo.setScheduleStatusId(jObj.getLong("scheduleStatusId"));
			inputVo.setCallStatusId(jObj.getLong("callstatusId"));
			inputVo.setStatus(jObj.getString("status"));
			inputVo.setLaterCallBackDate(jObj.getString("laterCallBackDate"));
			inputVo.setLaterCallBackTime(jObj.getString("laterCallBackTime"));
			inputVo.setLaterRemarks(jObj.getString("laterRemarks"));
			inputVo.setUserId(regVo.getRegistrationID());
			inputVo.setPurposeId(jObj.getLong("callPurposeId"));
			resultStatus = trainingCampService.updateCadreStatusForTraining(inputVo);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return Action.SUCCESS;
	}
	
	public String updateCallBackStatus()
	{
		try{
			RegistrationVO regVo = (RegistrationVO) request.getSession().getAttribute("USER");
			jObj = new JSONObject(getTask());
			TrainingCadreVO inputVo = new TrainingCadreVO();
			JSONArray jarr = jObj.getJSONArray("dataArray");
			
			if(jarr != null && jarr.length() > 0)
			{
				for(int i=0;i<jarr.length();i++)
				{
			JSONObject jObj=(JSONObject) jarr.get(i);
			inputVo.setInvitteId(jObj.getLong("inviteeId"));
			inputVo.setInviteeCallerId(jObj.getLong("inviteeCallerId"));
			inputVo.setTdpCadreId(jObj.getLong("tdpCadreId"));
			inputVo.setRamarks(jObj.getString("ramarks"));
			inputVo.setUserId(regVo.getRegistrationID());
			inputVo.setScheduleStatusId(jObj.getLong("scheduleStatusId"));
			inputVo.setCallBackTime(jObj.getString("time"));
			inputVo.setCallBackDate(jObj.getString("callbackDate"));
			inputVo.setPurposeId(jObj.getLong("callPurposeId"));
			
			resultStatus = trainingCampService.updateCallBackCadreStatusForTraining(inputVo);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return Action.SUCCESS;
	}
	
	public String getCallStatusCountByTrainingCampCallerId()
	{
		try{
			RegistrationVO regVo = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVo == null)
				 return "error";
			
			trainingCampCallStatusVO = trainingCampService.getCallStatusCountByTrainingCampCallerId(regVo.getRegistrationID());
		}catch (Exception e) {
			LOG.error(" Exception occured in getCallStatusCountByTrainingCampCallerId method in TrainingCampAction class.",e);
		}
		
		return Action.SUCCESS;
	}
	public String getUsersofUserType(){
		
		try{
			idnemIdNameVOs=trainingCampService.getUserIdsByType();
		}
		catch(Exception e){
			LOG.error(" Exception occured in getUsersofUserType method in TrainingCampAction class.",e);
		}
		
		return Action.SUCCESS;
	}
	
	
	public String getMembersCountByBatchStatus()
	{
		try{
		 	RegistrationVO regVO = (RegistrationVO)request.getSession().getAttribute("USER");
		 	
			if(regVO == null)
			 return "error";
			
			statusCountList = trainingCampService.getMembersCountByBatchStatus(regVO.getRegistrationID(),request.getParameter("batchStatus"));
			
		}catch (Exception e) {
			LOG.error(" Exception occured in getMembersCountByBatchStatus method in TrainingCampAction class.",e);
		}
		return Action.SUCCESS;
	}
	
	public String getAvailableCountForMemberConfirmation()
	{
		try{
			RegistrationVO regVO = (RegistrationVO)request.getSession().getAttribute("USER");
			jObj = new JSONObject(getTask());
			Long scheduleId = jObj.getLong("scheduleId");
		 	
			if(regVO == null)
			 return "error";
			
			availableCount = trainingCampService.getAvailableCountForMemberConfirmation(scheduleId);
			
		}catch (Exception e) {
			LOG.error(" Exception occured in getAvailableCountForMemberConfirmation method in TrainingCampAction class.",e);
		}
		return Action.SUCCESS;
	}
	
	public String getTheMeetingLevelDetails(){
		try{
			LOG.info("Entered into getTheMeetingLevelDetails");
			
			RegistrationVO regVo = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVo!=null && regVo.getRegistrationID()!=null){
				Long userId=regVo.getRegistrationID();
				retResult = trainingCampService.getTheMeetingLevelDetails(userId);
			}
			
		}catch (Exception e) {
			LOG.error("Exception raised in getTheMeetingLevelDetails", e);
		}
		return Action.SUCCESS;
	}
	
	public String getMeetingTypes(){
		try{
			LOG.info("Entered into getMeetingTypes");
			jObj = new JSONObject(getTask());
			
			retResult = trainingCampService.getMeetingTypes(jObj.getLong("locationLevel"));
		}catch (Exception e) {
			LOG.error("Exception raised into getMeetingTypes",e);
		}
		return Action.SUCCESS;
	}
	
	public String getAllMeetings(){
		try {
			LOG.info("entered into getAllMeetings");
			jObj = new JSONObject(getTask());
			
			List<Long> stateIds = new ArrayList<Long>(0);
			List<Long> distIds = new ArrayList<Long>(0);
			List<Long> constIds = new ArrayList<Long>(0);
			List<Long> manTowDivIds = new ArrayList<Long>(0);
			List<Long> villWardIds = new ArrayList<Long>(0);
			
			JSONArray jsonArray = jObj.getJSONArray("sateId");
			for (int i = 0; i < jsonArray.length(); i++) {
				Long sateId1 = Long.valueOf(jsonArray.get(i).toString());
				stateIds.add(sateId1);
			}
			
			JSONArray jsonArray1 = jObj.getJSONArray("districtId");
			for (int i = 0; i < jsonArray1.length(); i++) {
				Long distId1 = Long.valueOf(jsonArray1.get(i).toString());
				distIds.add(distId1);
			}
			
			JSONArray jsonArray2 = jObj.getJSONArray("constituencyId");
			for (int i = 0; i < jsonArray2.length(); i++) {
				Long constId1 = Long.valueOf(jsonArray2.get(i).toString());
				constIds.add(constId1);
			}
			
			JSONArray jsonArray3 = jObj.getJSONArray("mandalTownDivisonId");
			for (int i = 0; i < jsonArray3.length(); i++) {
				Long mtdId1 = Long.valueOf(jsonArray3.get(i).toString());
				manTowDivIds.add(mtdId1);
			}
			
			JSONArray jsonArray4 = jObj.getJSONArray("villageWardId");
			for (int i = 0; i < jsonArray4.length(); i++) {
				Long vwId1 = Long.valueOf(jsonArray4.get(i).toString());
				villWardIds.add(vwId1);
			}
			
			Long meetingType = jObj.getLong("meetingType");
			Long locationLevel = jObj.getLong("locationLevel");
			//Long stateId = jObj.getLong("sateId");
			//Long districtId = jObj.getLong("districtId");
			//Long constituencyId = jObj.getLong("constituencyId");
			//Long mandalTownDivisonId = jObj.getLong("mandalTownDivisonId");
			//Long villageWardId = jObj.getLong("villageWardId");
			String startDate = jObj.getString("startDate");
			String endDate = jObj.getString("endDate");
			
			retResult = trainingCampService.getAllMeetings(meetingType,locationLevel,stateIds,distIds,constIds,manTowDivIds,villWardIds,startDate,endDate);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getAllMeetings",e);
		}
		return Action.SUCCESS;
	}
	
	public String getCallBackDayWiseDetails()
	{
		try{
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			
			callBackCountVO = trainingCampService.getCallBackDayWiseDetails(regVO.getRegistrationID());
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getCallBackDayWiseDetails() method, Exception - ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getCallerAgentDistricts()
	{
		try{
			jObj = new JSONObject(getTask());
			RegistrationVO regVo = (RegistrationVO) request.getSession().getAttribute("USER");
			idNameList = trainingCampService.getCallerAgentDistricts(jObj.getLong("campCallerId"));
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return Action.SUCCESS;
	}
	public String getCallerAgentConstituenciesByDistrict()
	{
		try{
			jObj = new JSONObject(getTask());
			RegistrationVO regVo = (RegistrationVO) request.getSession().getAttribute("USER");
			idNameList = trainingCampService. getCallerAgentConstituencies(jObj.getLong("campCallerId"),jObj.getLong("id"));
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return Action.SUCCESS;
	}
	public String getCallerAgentMandalsByConstiteuncy()
	{
		try{
			jObj = new JSONObject(getTask());
			RegistrationVO regVo = (RegistrationVO) request.getSession().getAttribute("USER");
			idNameList = trainingCampService.getCallerAgentMandals(jObj.getLong("campCallerId"),jObj.getLong("id"));
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return Action.SUCCESS;
	}
	public String getCallerAgentVillagesByMandal()
	{
		try{
			jObj = new JSONObject(getTask());
			RegistrationVO regVo = (RegistrationVO) request.getSession().getAttribute("USER");
			idNameList = trainingCampService.getCallerAgentVillages(jObj.getLong("campCallerId"),jObj.getLong("id"));
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return Action.SUCCESS;
	}
	
	public String getSubLevelLctnsForConstituencyAndMandal(){
		LOG.info("entered into getSubLevelLctnsForConstituencyAndMandal");
		try {
			
			jObj = new JSONObject(getTask());
			
			Long stateId = jObj.getLong("stateId");
			String mandalId = jObj.getString("mandalId");
			Long locationLevel = jObj.getLong("locationLevel");
			
			List<Long> distIds = new ArrayList<Long>();
			JSONArray jsonArray1 = jObj.getJSONArray("districtId");
			for (int i = 0; i < jsonArray1.length(); i++) {
				Long distId1 = Long.valueOf(jsonArray1.get(i).toString());
				distIds.add(distId1);
			}
			
			List<Long> constiIds = new ArrayList<Long>();
			JSONArray jsonArray2 = jObj.getJSONArray("constituencyId");
			for (int i = 0; i < jsonArray2.length(); i++) {
				if(jsonArray2.get(i) != null && !jsonArray2.get(i).toString().equalsIgnoreCase("null")){
					Long constiId = Long.valueOf(jsonArray2.get(i).toString());
					constiIds.add(constiId);
				}
				
			}
						
			locations = cadreCommitteeService.getLocationsOfSublevelConstituencyMandal(stateId,distIds,constiIds,mandalId,locationLevel);
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getCallBackDayWiseDetails() method, Exception - ",e);
		}
		return Action.SUCCESS;
	}
	public String getAgentCallDetailsByCampCallerId()
	{
		try{
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			Long campCallerId = null;
			 campCallerId = new Long(request.getParameter("campCallerId"));
			if(campCallerId == null || campCallerId.longValue() == 0l)
				campCallerId = regVO.getRegistrationID();
			
			statusCountList =  trainingCampService.getAgentCallDetailsByCampCallerId(campCallerId);
		}catch (Exception e) {
			LOG.error("Exception Occured in getAgentCallDetailsByCampCallerId() method, Exception - ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getAgentsByCampCallerAdminId()
	{
		try{
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			
			boolean isAdmin=false;
			if(regVO.getIsAdmin().equalsIgnoreCase("true")){
				isAdmin = true;
			}
			
			basicList = trainingCampService.getAgentsByCampCallerAdminId(new Long(regVO.getRegistrationID()),isAdmin);
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getAgentsByCampCallerAdminId() method, Exception - ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getCallsDetailsOfCallCenterAdmin(){
		try{
			jObj = new JSONObject(getTask());
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			RegistrationVO regVO =(RegistrationVO) request.getSession().getAttribute("USER");
			boolean isAdmin=false;
			if(regVO.getIsAdmin().equalsIgnoreCase("true")){
				isAdmin = true;
			}
			List<Long> userIds=trainingCampService.getTrainingCampUserTypeIds(regVO.getRegistrationID(),isAdmin);
			
			trainingCampScheduleVOs=trainingCampService.getCallsDetailsOfCallCenterAdmin(userIds,fromDate,toDate);
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getCallsDetailsOfCallCenterAdmin() method, Exception - ",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String getUpComingBatchDetails(){
		
		try{
			jObj = new JSONObject(getTask());
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			
			trainingCampScheduleVO=trainingCampService.getUpComingBatchDetails(fromDate,toDate);
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getUpComingBatchDetails() method, Exception - ",e);
		}
		
		return Action.SUCCESS;
		
	} 
	
	public String getNewDistrictsOfStateSplitted(){
		try{
			jObj = new JSONObject(getTask());
			Long stateId = jObj.getLong("stateId");
			idnemIdNameVOs=cadreCommitteeService.getDistrictsOfStateWithSplitted(stateId);
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getCallsDetailsOfCallCenterAdmin() method, Exception - ",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String getPartyMeetingMinutesAtrDetails(){
		try{
			LOG.info("Entered into getPartyMeetingMinutesAtrDetails");
			jObj = new JSONObject(getTask());
			Long partyMeetingId = jObj.getLong("partyMeetingID");
			
			
			String accessType=null;
			String accessValue=null;
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO !=null){
				accessType = regVO.getAccessType();
				accessValue = regVO.getAccessValue();
			}
			
			meetingDetails = trainingCampService.getPartyMeetingMinutesAtrDetails(partyMeetingId,accessType,accessValue);
			
		}catch (Exception e) {
			LOG.error("Exception raised at getPartyMeetingMinutesAtrDetails",e);
		}
		return Action.SUCCESS;
	}
	
	public String getScheduleAvailableCallsCountLocationWiseInfo()
	{
		try{

			jObj = new JSONObject(getTask());
			statusCountList = trainingCampService.getScheduleAvailableCallsCountLocationWiseInfo(jObj.getLong("campId"),jObj.getLong("programId"),jObj.getLong("scheduleId"),jObj.getString("type"));
				
		}
		catch (Exception e) {
			LOG.error("Exception Occured in getScheduleAvailableCallsCountLocationWiseInfo() method, Exception - ",e);
		}
		return Action.SUCCESS;
	}
	public String getConstituenciesOfDistrict(){
		try{

			jObj = new JSONObject(getTask());
			Long stateId = jObj.getLong("stateId");
			
			List<Long> distIds = new ArrayList<Long>();
			JSONArray jsonArray1 = jObj.getJSONArray("districtId");
			for (int i = 0; i < jsonArray1.length(); i++) {
				Long distId1 = Long.valueOf(jsonArray1.get(i).toString());
				distIds.add(distId1);
			}
			
			
			locations = cadreCommitteeService.getConstituencyOfDistrict(stateId, distIds);
				
		}
		catch (Exception e) {
			LOG.error("Exception Occured in getScheduleAvailableCallsCountLocationWiseInfo() method, Exception - ",e);
		}
		return Action.SUCCESS;
	}
	
	public String uploadDocsAction(){
		try{
			 String imageName=null;
			 
			 RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			 
			 if(regVO!=null){
				 Long userId = regVO.getRegistrationID();
				 
				 for(int i=0;i<imageForDisplay.size();i++){
		        	  String fileType = imageForDisplayContentType.get(i).substring(imageForDisplayContentType.get(i).indexOf("/")+1, imageForDisplayContentType.get(i).length());
			        	 
			          imageName= UUID.randomUUID().toString()+"_"+imageForDisplayFileName.get(i);
			          
			          String dtOfArticle=new DateUtilService().getCurrentDateInStringFormatYYYYMMDD();
				         String ttlDir = FolderForArticles(dtOfArticle);
				         if(ttlDir.equalsIgnoreCase("FAILED")){
				        	 inputStream = new StringBufferInputStream("fail");
				        	 return "SUCCESS";
				         }
				         String filePath=IConstants.LOCAL_FILES_FOLDER+"/"+ttlDir;
			        	 
			          File fileToCreate = new File(filePath,imageName);
					  FileUtils.copyFile(imageForDisplay.get(i), fileToCreate);
					  
					  trainingCampService.saveFilePaths(partyMeeting,fileType,partyMeetingType,ttlDir+"/"+imageName,userId, imageForDisplayFileName.get(i));
					  
					  inputStream = new StringBufferInputStream("success");
		          }
			 }else{
				 inputStream = new StringBufferInputStream("login failed");
			 }
		      
		}catch(Exception e){
			inputStream = new StringBufferInputStream("failed");
			LOG.error(e);
		}
		
		return Action.SUCCESS;
		  //getting image path to store the image.
			 
	         
	         //copy image to folder.
	         
	}
	
	public String getCallerWiseOverView()
	{
		try{
			jObj = new JSONObject(getTask());
			List<Long> ids = new ArrayList<Long>();
			ids.add(jObj.getLong("callerId"));
			trainingCampVO= trainingCampService.getCallerWiseOverView(ids);
		}
		catch (Exception e) {
			LOG.error("Exception Occured in getCallerWiseOverView() method, Exception - ",e);
		} 
		return Action.SUCCESS;
	}
	
	public String getAdminCallersWiseOverView()
	{
		try{
			jObj = new JSONObject(getTask());
			RegistrationVO regVo = (RegistrationVO) request.getSession().getAttribute("USER");
			
			boolean isAdmin=false;
			if(regVo.getIsAdmin().equalsIgnoreCase("true")){
				isAdmin = true;
			}
			
			trainingCampVO= trainingCampService.getAdminCallersWiseOverView(regVo.getRegistrationID(),jObj.getLong("campId"),jObj.getLong("programId"),jObj.getLong("scheduleId"),jObj.getLong("batchId"),isAdmin);
		}
		catch (Exception e) {
			LOG.error("Exception Occured in getCallerWiseOverView() method, Exception - ",e);
		} 
		return Action.SUCCESS;
	}
	
	public static String FolderForArticles(String totalDate){
	  	 try {
	  		 LOG.debug(" in FolderForArticle ");
			String string = totalDate;
			 String[] parts = string.split("-");
			
			 String yr = parts[0]; // YEAR YYYY
			 String yrDir = IConstants.LOCAL_FILES_FOLDER+"/"+yr;
			 String yrFldrSts = createFolderForArticles(yrDir);
			 if(!yrFldrSts.equalsIgnoreCase("SUCCESS")){
				 return "FAILED";
			 }
			 
			 String mnth = parts[1];
			 String mnthDir = IConstants.LOCAL_FILES_FOLDER+"/"+yr+"/"+mnth;
			 String mnthDirSts = createFolderForArticles(mnthDir);
			 if(!mnthDirSts.equalsIgnoreCase("SUCCESS")){
				 return "FAILED";
			 }
			 
			 String ttlDateDir = IConstants.LOCAL_FILES_FOLDER+"/"+yr+"/"+mnth+"/"+totalDate;
			 String ttlDateDirStts = createFolderForArticles(ttlDateDir);
			 if(!ttlDateDirStts.equalsIgnoreCase("SUCCESS")){
				 return "FAILED";
			 }
			 
			 return yr+"/"+mnth+"/"+totalDate;
			 
		} catch (Exception e) {
			LOG.error(totalDate+" Failed to Create");
			return "FAILED";
		}
		 
		 
		 
 }
	
	public static String CreateDateFolder(String totalDate,String folder){
	  	 try {
	  		String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
	  		 LOG.debug(" in FolderForArticle ");
			String string = totalDate;
			 String[] parts = string.split("-");
			
			 String yr = parts[0]; // YEAR YYYY
			 String yrDir = IConstants.STATIC_CONTENT_FOLDER_URL+folder+pathSeperator+yr;
			 String yrFldrSts = createFolderForArticles(yrDir);
			 if(!yrFldrSts.equalsIgnoreCase("SUCCESS")){
				 return "FAILED";
			 }
			 
			 String mnth = parts[1];
			 String mnthDir = IConstants.STATIC_CONTENT_FOLDER_URL+folder+pathSeperator+yr+pathSeperator+mnth;
			 String mnthDirSts = createFolderForArticles(mnthDir);
			 if(!mnthDirSts.equalsIgnoreCase("SUCCESS")){
				 return "FAILED";
			 }
			 
			
			 
			 return yr+"/"+mnth;
			 
		} catch (Exception e) {
			LOG.error(totalDate+" Failed to Create");
			return "FAILED";
		}
		 
}
	
	public static String createFolderForArticles(String dir){
	 	try {
			File theDir = new File(dir);
			  // if the directory does not exist, create it
			  if (!theDir.exists()) {
			    boolean result = false;

			    try{
			        theDir.mkdir();
			        result = true;
			     } catch(SecurityException se){
			        //handle it
			     }        
			     if(result) {    
			      LOG.debug("DIR With Name "+dir+" created");  
			     }
			  }else{
				  LOG.debug("DIR With Name "+dir+" EXISTS");
			  }
			  return "SUCCESS";
		} catch (Exception e) {
			LOG.error(dir+" Failed to Create");
			return "FAILED";
		}
	}
	public String trainingCampMainDashboard()
	{
		try
		{
			RegistrationVO regVO =(RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO!=null){
				//Long userId = regVO.getRegistrationID();
				//if(!regVO.getIsAdmin().equalsIgnoreCase("true")){
					/*if(entitlementsHelper.checkForEntitlementToViewReport(regVO,"TRAINING_CAMP_FEEDBACK_UPDATE_ENTITLEMENT") ||
							entitlementsHelper.checkForEntitlementToViewReport(regVO,"TRAINING_CAMP_SUPER_ADMIN") ){
						return Action.SUCCESS;
					}*/
				List<String> entitlements = null;
			    if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
			      entitlements = regVO.getEntitlements();
			      if(entitlements.contains("TRAINING_CAMP_FEEDBACK_UPDATE_ENTITLEMENT")||entitlements.contains("TRAINING_CAMP_SUPER_ADMIN")){
			    	  return Action.SUCCESS;
			      }

					else
					{
						return Action.ERROR;
					}
				//}
			}else{
				return Action.INPUT;
			}
		}
		}catch (Exception e) {
			LOG.error(" Exception occured in trainingCampMainDashboard method in TrainingCampAction class.",e);
		}
		
		return Action.SUCCESS;
	}
	public String getProgramsAction(){
		
		try{
			
			RegistrationVO regVO =(RegistrationVO) request.getSession().getAttribute("USER");
			Long userId = regVO.getRegistrationID();
			List<String> entitlements = null;
			
			if(regVO.getIsAdmin().equalsIgnoreCase("true")){//Admin
				
				simpleDetailsVO=trainingCampService.getAllProgramsAndCamps();
				
			}
			/*else if(entitlementsHelper.checkForEntitlementToViewReport(regVO,"TRAINING_CAMP_FEEDBACK_UPDATE_ENTITLEMENT")){//entitled user.
				
				simpleVO=trainingCampService.getProgramsByUser(userId);
			}*/
			else if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
		        entitlements = regVO.getEntitlements();
		        if(entitlements.contains("TRAINING_CAMP_FEEDBACK_UPDATE_ENTITLEMENT")){
		        	simpleDetailsVO=trainingCampService.getProgramsByUser(userId);
		        }

			}			
		}catch(Exception e){
			LOG.error(" Exception occured in getProgramsAction method in TrainingCampAction class.",e);
		}
		return Action.SUCCESS;
	}
	public String getCampsByProgramAndUser(){
		
		try{
			
			RegistrationVO regVO =(RegistrationVO) request.getSession().getAttribute("USER");
			Long userId = regVO.getRegistrationID();
			jObj = new JSONObject(getTask());
			Long campProgramId = jObj.getLong("campProgramId");
			idNameList=trainingCampService.getCampsByProgramAndUser(campProgramId,userId);
			
		}catch(Exception e){
			LOG.error(" Exception occured in getProgramsAction method in TrainingCampAction class.",e);
		}
		return Action.SUCCESS;
	}
	public String getTdpCadreDetailsforASchedule(){
		
		try{
			
			jObj = new JSONObject(getTask());
			Long scheduleId = jObj.getLong("scheduleId");
			
			//finalList = trainingCampService.getTdpCadreDetailsforASchedule(scheduleId);
			
		}catch(Exception e) {
			LOG.error("Exception Occured in getTdpCadreDetailsforASchedule() method, Exception - ",e);
		}
		return Action.SUCCESS;
	}
    public String getDetailsForACadre(){
		
		try{
			
			jObj = new JSONObject(getTask());
			Long tdpCadreId = jObj.getLong("tdpCadreId");
			Long batchId = jObj.getLong("batchId");
			Long enrollmentYearId = jObj.getLong("enrollmentYearId");
			cadreDetailsVO= trainingCampService.getDetailsForACadre(tdpCadreId,batchId,enrollmentYearId);
			
		}catch(Exception e) {
			LOG.error("Exception Occured in getTdpCadreDetailsforASchedule() method, Exception - ",e);
		}
		return Action.SUCCESS;
	}
    public String getAllStatusForCadre(){
		
		try{
			cadreDetailsVO= trainingCampService.getAllStatusForCadre();
			
		}catch(Exception e) {
			LOG.error("Exception Occured in getAllStatusForCadre() method, Exception - ",e);
		}
		return Action.SUCCESS;
	}
    
    public String getDocsOfMeeting(){
    	try{
			
			jObj = new JSONObject(getTask());
			Long partyMeetingId = jObj.getLong("partyMeetingId");
			String docSourceType = jObj.getString("docSourceType");
			
			String accessType=null;
			String accessValue=null;
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO !=null){
				accessType = regVO.getAccessType();
				accessValue = regVO.getAccessValue();
			}
			
			docsResultList = trainingCampService.getDocsOfPartyMeetingId(partyMeetingId,docSourceType,accessType,accessValue);
			
		}catch(Exception e) {
			LOG.error("Exception Occured in getTdpCadreDetailsforASchedule() method, Exception - ",e);
		}
		return Action.SUCCESS;
    }
    
  
   public String saveDetailsOfCadreAction()
   {
   	
   	try{
   		DateUtilService dateService = new DateUtilService();
   		RegistrationVO regVO =(RegistrationVO) request.getSession().getAttribute("USER");
   		Long userId = regVO.getRegistrationID();
   		List<String> filePaths = null;
   		List<String> feedbackDocuments= null;
   		MultiPartRequestWrapper multiPartRequestWrapper = (MultiPartRequestWrapper)request;
   		
   		Enumeration<String> fileParams = multiPartRequestWrapper.getFileParameterNames();
   		Long tdpCadreId = Long.parseLong(request.getParameter("tdpCadreId"));
   		//String storeFilePath ="" ;
   		String fileUrl = "" ;
   		if(fileParams.hasMoreElements())
   		{
   		
   			String inputValue = "image";
		   			File[] files = multiPartRequestWrapper.getFiles(inputValue);
		   			filePaths = new ArrayList<String>();
		   			if(files != null && files.length > 0)
		   			for(File f : files)
		   			{
		   				fileUrl = "" ;
		   				//storeFilePath ="" ;
		   				
		   				String destinationPath ="";
		   				String destPath ="";
		   				String[] extension  =multiPartRequestWrapper.getFileNames(inputValue)[0].split("\\.");
						String ext = "";
						if(extension.length > 1){
							ext = extension[extension.length-1];
						}
						String dateString = dateService.getCurrentDateAndTimeInStringFormat();
						
						String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
						 fileUrl = CreateDateFolder(dateString,IConstants.HEALTH_CARD_FOLDER);
						 String RandomNumber = UUID.randomUUID().toString();
						  destPath =  "/"+fileUrl+"/"+RandomNumber+"_"+tdpCadreId+"."+ext;
						  destinationPath =  pathSeperator+fileUrl+pathSeperator+RandomNumber+"_"+tdpCadreId+"."+ext;
						filePaths.add(destPath);
						 copyFile(f.getAbsolutePath(),IConstants.STATIC_CONTENT_FOLDER_URL+IConstants.HEALTH_CARD_FOLDER+destinationPath);
		   			}
		   			
   			
		   			String inputValue1 = "feedbackDoc";
   				File[] docs = multiPartRequestWrapper.getFiles(inputValue1);
   				
	   			feedbackDocuments= new ArrayList<String>();
	   			if(docs != null && docs.length > 0)
	   			for(File f : docs)
	   			{
	   			 fileUrl = "" ;
	   			// storeFilePath ="" ;
	   			 String destinationPath ="";
	   			String destPath ="";
	   				String[] extension  =multiPartRequestWrapper.getFileNames(inputValue1)[0].split("\\.");
					String ext = "";
					if(extension.length > 1){
						ext = extension[extension.length-1];
					}
					String dateString = dateService.getCurrentDateAndTimeInStringFormat();
					
					String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
					 fileUrl = CreateDateFolder(dateString,IConstants.CADRE_FEEDBACK_DOCUMENT);
					 String RandomNumber = UUID.randomUUID().toString();
					  destPath =  "/"+fileUrl+"/"+RandomNumber+"_"+tdpCadreId+"."+ext;
					 destinationPath =  pathSeperator+fileUrl+pathSeperator+RandomNumber+"_"+tdpCadreId+"."+ext;
					 feedbackDocuments.add(destPath);
					 copyFile(f.getAbsolutePath(),IConstants.STATIC_CONTENT_FOLDER_URL+IConstants.CADRE_FEEDBACK_DOCUMENT+destinationPath);
					
	   			}
   			
   		}	
   		
   		
   		Long batchId = Long.parseLong(request.getParameter("batchId"));
   		
   		List<String> achieveList=new ArrayList<String>();
   		String[] achieveArray = request.getParameterValues("achieveArray");
   		for(int i = 0; i < achieveArray.length; i++){
   			if(achieveArray[i].toString().trim().length()>0){
   				achieveList.add(achieveArray[i].toString());
   			}
   		}
   		
   	   List<SimpleVO> goallist=new ArrayList<SimpleVO>();
   	   String[] goalArray = request.getParameterValues("goalArray");
	   if(goalArray != null && goalArray.length > 0)
	   {
		  for(int i=0;i<goalArray.length;i++)
		  {
			String goalObj= goalArray[i].toString();
			if(goalObj.trim().length()>0){
				String[] obj = goalObj.split(",");
				for(int j=0;j<obj.length;j++)
				{
					SimpleVO goal=new SimpleVO();
					String[] obj1 = obj[j].toString().split("-");
					goal.setName(obj1[0].toString());
					goal.setDateString(obj1[1].toString());
					goallist.add(goal);
				}
			}
		  }
	   }
	   
	   List<SimpleVO> feedbackCategories=new ArrayList<SimpleVO>();
 	   String[] feedback = request.getParameterValues("feedbackCategoriesArray");
	   if(feedback != null && feedback.length > 0)
	   {
		  for(int i=0;i<feedback.length;i++)
		  {
			String feedbackObj= feedback[i].toString();
			if(feedbackObj.trim().length()>0){
				String[] obj = feedbackObj.split(",");
				for(int j=0;j<obj.length;j++)
				{
					String[] obj1 = obj[j].toString().split("-");
					SimpleVO feedbackVO=new SimpleVO();
					feedbackVO.setId(new Long(obj1[0].toString()));
					feedbackVO.setName(obj1[1].toString());
					feedbackCategories.add(feedbackVO);
				}
			}
		  }
	   }	
			
   		Long leaderShipLevelId = Long.parseLong(request.getParameter("leaderShipLevel"));
   		Long communicationSkillsId = Long.parseLong(request.getParameter("communicationSkills"));
   		Long leaderShipSkillsId = Long.parseLong(request.getParameter("leaderShipSkills"));
   		Long healthId = Long.parseLong(request.getParameter("health"));
   		String comments = request.getParameter("comments");
   		String smartPhoneId = request.getParameter("smartPhoneId");
   		String whatsappId = request.getParameter("whatsappId");
   		String whatsappShareId = request.getParameter("whatsappShareId");
   		String facebookId = request.getParameter("facebookId");
   		Long programId = Long.parseLong(request.getParameter("programId")); ;
   	
   		
   	 
   		cadreDetailsVO = trainingCampService.saveDetailsOfCadre(tdpCadreId,batchId,achieveList,goallist,leaderShipLevelId,communicationSkillsId,leaderShipSkillsId,healthId,comments,userId,smartPhoneId,whatsappId,whatsappShareId,facebookId,filePaths,feedbackDocuments,feedbackCategories,programId);
   		
   	
   	}	
   		catch(Exception e) {
   		LOG.error("Exception Occured in saveAllDetailsAction() method, Exception - ",e);
   	}
   	
   	return Action.SUCCESS;
   }
   
   
  

	public static String createFolderForDate(String dir){
		 	try {
				File theDir = new File(dir);
				  // if the directory does not exist, create it
				  if (!theDir.exists()) {
				    boolean result = false;

				    try{
				        theDir.mkdir();
				        result = true;
				     } catch(SecurityException se){
				        //handle it
				     }        
				     if(result) {    
				    	 LOG.debug("DIR With Name "+dir+" created");  
				     }
				  }else{
					  LOG.debug("DIR With Name "+dir+" EXISTS");
				  }
				  return "SUCCESS";
			} catch (Exception e) {
				LOG.error(dir+" Failed to Create");
				return "FAILED";
			}
		}
	public String copyFile(String sourcePath,String destinationPath){
		 try{
			File destFile = new File(destinationPath);
			 if (!destFile.exists()) 
				 destFile.createNewFile();
			 File file = new File(sourcePath);
			if(file.exists()){
				FileUtils.copyFile(file,destFile);
				LOG.error("Copy success");
				return "success";
			}
		  }catch(Exception e){
			  LOG.error("Exception raised in copyFile ", e);
			  LOG.error("Copy error");
			  return "error";
		  }
		 return "failure";
		}
	
   
    public String getAllBatchesByProgramAndCenter()
    {
    	try{
    		
    		jObj = new JSONObject(getTask());
    		Long programId = jObj.getLong("programId");
    		Long centerId = jObj.getLong("centerId");
    		Long batchId = jObj.getLong("batchId");
    		Long enrollmentYearId = jObj.getLong("enrollmentYearId");
    		
    		finalList = trainingCampService.getSchedulesListByProgramAndCenter(programId,centerId,batchId,enrollmentYearId);
    		
    	}catch(Exception e){
    		LOG.error("Exception Occured in getAllBatchesByProgramAndCenter() method, Exception - ",e);
    	}
    	
    	return Action.SUCCESS;
    }
	
    
    //public String uploadDocsAction(){
    public String uploadMeetingDocs(){
		try{
			 String imageName=null;
			 
			 RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			 
			 if(regVO!=null){
				 Long userId = regVO.getRegistrationID();
				 
				 for(int i=0;i<imageForDisplay.size();i++){
		        	  String fileType = imageForDisplayContentType.get(i).substring(imageForDisplayContentType.get(i).indexOf("/")+1, imageForDisplayContentType.get(i).length());
			        	 
			          imageName= UUID.randomUUID().toString()+"_"+imageForDisplayFileName.get(i);
			          
			          String dtOfArticle=new DateUtilService().getCurrentDateInStringFormatYYYYMMDD();
				         String ttlDir = FolderForArticles(dtOfArticle);
				         if(ttlDir.equalsIgnoreCase("FAILED")){
				        	 inputStream = new StringBufferInputStream("fail");
				        	 return "SUCCESS";
				         }
				         String filePath=IConstants.LOCAL_FILES_FOLDER+"/"+ttlDir;
			        	 
			          File fileToCreate = new File(filePath,imageName);
					  FileUtils.copyFile(imageForDisplay.get(i), fileToCreate);
					  
					  trainingCampService.saveFilePaths(partyMeeting,fileType,partyMeetingType,ttlDir+"/"+imageName,userId, imageForDisplayFileName.get(i));
					  
					  inputStream = new StringBufferInputStream("success");
		          }
			 }else{
				 inputStream = new StringBufferInputStream("login failed");
			 }
		      
		}catch(Exception e){
			inputStream = new StringBufferInputStream("failed");
			LOG.error(e);
		}
		
		return Action.SUCCESS;
		  //getting image path to store the image.
			 
	         
	         //copy image to folder.
	         
	}
    public String openTrainingCenterDashBoard(){ 
    	minAndMaxDatesStr=trainingCampService.getMinAndMaxDatesOfTraingCamp();
    	RegistrationVO regVO =(RegistrationVO) request.getSession().getAttribute("USER");
		if(regVO!=null){
			Long userId = regVO.getRegistrationID();
		}else{
			return Action.INPUT;
		}
		/*if(entitlementsHelper.checkForEntitlementToViewReport(regVO,"TRAINING_CAMP_ADMIN") || 
				entitlementsHelper.checkForEntitlementToViewReport(regVO,"TRAINING_CAMP_SUPER_ADMIN") ){
			return Action.SUCCESS;
		}else
		return Action.INPUT;
	}*/
		List<String> entitlements = null;
	    if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
	      entitlements = regVO.getEntitlements();
	      if(entitlements.contains("TRAINING_CAMP_ADMIN")||entitlements.contains("TRAINING_CAMP_SUPER_ADMIN")){
	    	  return Action.SUCCESS;
	      }else
	  		return Action.INPUT;
	    }
		return Action.SUCCESS;
    }
	      
    public String getAttendedCountForBatchesByLocation()
    {
    	try{
    		jObj = new JSONObject(getTask());
			String selDate = jObj.getString("selectedDate");
			List<Long> enrollmentYearIds=new ArrayList<Long>();
			JSONArray enrollmentYearIdsArray=jObj.getJSONArray("enrollmentYearIdsList");
			if(enrollmentYearIdsArray!=null &&  enrollmentYearIdsArray.length()>0){
				for( int i=0;i<enrollmentYearIdsArray.length();i++){
					enrollmentYearIds.add(Long.valueOf(enrollmentYearIdsArray.getString(i)));
				}
			}
			String temp[] = selDate.split("-");
			List<Long> programYearIds=new ArrayList<Long>();
			  JSONArray programIdsArray=jObj.getJSONArray("programIdsList");
						if(programIdsArray!=null &&  programIdsArray.length()>0){
							for( int i=0;i<programIdsArray.length();i++){
								programYearIds.add(Long.valueOf(programIdsArray.getString(i)));
							}
						}
			if(temp.length>1){
				idNameList = trainingCampService.getAttendedCountForBatchesByLocation(temp[0].trim(),temp[1].trim(),1l,enrollmentYearIds,programYearIds);
			}else{
				idNameList = trainingCampService.getAttendedCountForBatchesByLocation(null,null,0l,enrollmentYearIds,programYearIds);
			}
    		
    		
    	}catch(Exception e){
    		LOG.error("Exception Occured in getAttendedCountForBatchesByLocation() method, Exception - ",e);
    	}
    	
    	return Action.SUCCESS;
    }
    public String getInvitedAttendedCadreCountByBatchIds()
    {
    	try{
    		jObj = new JSONObject(getTask());
			String selDate = jObj.getString("selectedDate");
			List<Long> enrollmentYearIds=new ArrayList<Long>();
			JSONArray enrollmentYearIdsArray=jObj.getJSONArray("enrollmentYearIdsList");
			if(enrollmentYearIdsArray!=null &&  enrollmentYearIdsArray.length()>0){
				for( int i=0;i<enrollmentYearIdsArray.length();i++){
					enrollmentYearIds.add(Long.valueOf(enrollmentYearIdsArray.getString(i)));
				}
			}
			String temp[] = selDate.split("-");
			List<Long> programYearIds=new ArrayList<Long>();
			 JSONArray programIdsArray=jObj.getJSONArray("programIdsList");
						if(programIdsArray!=null &&  programIdsArray.length()>0){
							for( int i=0;i<programIdsArray.length();i++){
								programYearIds.add(Long.valueOf(programIdsArray.getString(i)));
							}
						}
			if(temp.length>1){
				simpleVO = trainingCampService.getInvitedAttendedCadreCountByBatchIds(temp[0].trim(),temp[1].trim(),1l,enrollmentYearIds,programYearIds);
			}else{
				simpleVO = trainingCampService.getInvitedAttendedCadreCountByBatchIds(null,null,0l,enrollmentYearIds,programYearIds);
			}
    		
    		
    	}catch(Exception e){
    		LOG.error("Exception Occured in getAttendedCountForBatchesByLocation() method, Exception - ",e);
    	}
    	
    	return Action.SUCCESS;
    }
    
    public String trainingProgramDashBoard(){
    	RegistrationVO regVO =(RegistrationVO) request.getSession().getAttribute("USER");
		if(regVO!=null){
			Long userId = regVO.getRegistrationID();
		}else{
			return Action.INPUT;
		}
		/*if(entitlementsHelper.checkForEntitlementToViewReport(regVO,"TRAINING_CAMP_ADMIN") || 
				entitlementsHelper.checkForEntitlementToViewReport(regVO,"TRAINING_CAMP_SUPER_ADMIN") ){
			return Action.SUCCESS;
		}*/
		List<String> entitlements = null;
	    if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
	      entitlements = regVO.getEntitlements();
	      if(entitlements.contains("AINING_CAMP_ADMIN")||entitlements.contains("TRAINING_CAMP_SUPER_ADMIN")){
	    	  return Action.SUCCESS;
	      }
	    }
		return Action.INPUT;
	}
    
    public String getTrainingCenterDetailsBasedOnDates(){
    	try {
			LOG.info("Entered into getTrainingCenterDetailsBasedOnDates");
			
			jObj = new JSONObject(getTask());
			String selDate = jObj.getString("selectedDate");
			
			List<Long> enrollmentYearIds=new ArrayList<Long>();
			JSONArray enrollmentYearIdsArray=jObj.getJSONArray("enrollmentYearIdsList");
			if(enrollmentYearIdsArray!=null &&  enrollmentYearIdsArray.length()>0){
				for( int i=0;i<enrollmentYearIdsArray.length();i++){
					enrollmentYearIds.add(Long.valueOf(enrollmentYearIdsArray.getString(i)));
				}
			}
			List<Long> programYearIds =new ArrayList<Long>();
			JSONArray programIdsArray=jObj.getJSONArray("programIdsList");
			if(programIdsArray!=null &&  programIdsArray.length()>0){
				for( int i=0;i<programIdsArray.length();i++){
					programYearIds.add(Long.valueOf(programIdsArray.getString(i)));
				}
			}
			String temp[] = selDate.split("-");
			
			if(temp.length>1){
				//returnResult = trainingCampService.getCompletedRunningUpcomingBatchIds(temp[1].trim(),temp[0].trim(),1l,"All",enrollmentYearIds,programYearIds);
				returnResult = trainingCampService.getAllTrainingProgWiseCompletedRunningUpcomingBatchIds(temp[1].trim(),temp[0].trim(),1l,"All",enrollmentYearIds,programYearIds);
			}else{
				//returnResult = trainingCampService.getCompletedRunningUpcomingBatchIds(null,null,0l,"All",enrollmentYearIds,programYearIds);
				returnResult = trainingCampService.getAllTrainingProgWiseCompletedRunningUpcomingBatchIds(null,null,0l,"All",enrollmentYearIds,programYearIds);
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised at TODO: handle exception", e);
		}
    	return Action.SUCCESS;
    }
    
    public String getattendedcountByFeedBacks()
    {
    	try{
    		
    		jObj = new JSONObject(getTask());
    		
    		/*Long programId = jObj.getLong("programId");
    		if(programId==0l){
    			programId=null;
    		}*/
    		Long campId = jObj.getLong("campId");
    		if(campId==0l){
    			campId=null;
    		}
    		Long batchId = jObj.getLong("batchId");
    		if(batchId==0l){
    			batchId=null;
    		}
    		List<Long> enrollmentYrIds = new ArrayList<Long>(0);
    		JSONArray enrollmentYrIdsArr = jObj.getJSONArray("enrollmentYrIds");
    		if(enrollmentYrIdsArr != null && enrollmentYrIdsArr.length() > 0)
    		{
    			for(int i=0;i<enrollmentYrIdsArr.length();i++)
    				enrollmentYrIds.add(new Long(enrollmentYrIdsArr.get(i).toString()));
    		}
    		List<Long> programIds = new ArrayList<Long>(0);
    		JSONArray programIdsArr = jObj.getJSONArray("programIds");
    		if(programIdsArr != null && programIdsArr.length() > 0)
    		{
    			for(int i=0;i<programIdsArr.length();i++)
    				programIds.add(new Long(programIdsArr.get(i).toString()));
    		}
    		String callFrom = jObj.getString("callFrom");
    		
    		String dates[] = jObj.getString("dates").split("-");
    		if(dates.length>1){
    			finalVO = trainingCampService.getattendedcountByFeedBacks(programIds,campId,batchId,dates[0].trim(),dates[1].trim(),callFrom,enrollmentYrIds);
    		}else{
    			finalVO = trainingCampService.getattendedcountByFeedBacks(programIds,campId,batchId,null,null,callFrom,enrollmentYrIds);
    		}
    		
    		
    	}catch(Exception e){
    		LOG.error("Exception Occured in getattendedcountByFeedBacks() method, Exception - ",e);
    	}
    	
    	return Action.SUCCESS;
    }
    
    public String getAttendedCountsByProgramOrCampOrBatch()
    {
    	try{
    		
    		jObj = new JSONObject(getTask());
    		/*Long programId = jObj.getLong("programId");
    		if(programId==0l){
    			programId=null;
    		}*/
    		Long campId = jObj.getLong("campId");
    		if(campId==0l){
    			campId=null;
    		}
    		Long batchId = jObj.getLong("batchId");
    		if(batchId==0l){
    			batchId=null;
    		}
    		String fromType = jObj.getString("fromType");
    		String dates[] = jObj.getString("dates").split("-");
    		String callFrom = jObj.getString("callFrom");
    		JSONArray enrollmentYrIdsArr = jObj.getJSONArray("enrollmentYrIds");
    		List<Long> enrollmentYrIds = new ArrayList<Long>(0);
    		
    		if(enrollmentYrIdsArr != null && enrollmentYrIdsArr.length() > 0){
    			for(int i=0;i<enrollmentYrIdsArr.length();i++){
    				enrollmentYrIds.add(Long.parseLong(enrollmentYrIdsArr.getString(i)));
    			}
    		}
    		
    		List<Long> programIds = new ArrayList<Long>(0);
    		JSONArray programIdsArr = jObj.getJSONArray("programIds");
    		if(programIdsArr != null && programIdsArr.length() > 0)
    		{
    			for(int i=0;i<programIdsArr.length();i++)
    				programIds.add(new Long(programIdsArr.get(i).toString()));
    		}
    		if(dates.length>1){
    			simpleDetailsVOList = trainingCampService.getAttendedCountsByProgramOrCampOrBatch(programIds,campId,batchId,dates[0].trim(),dates[1].trim(),fromType,callFrom,enrollmentYrIds);
    		}else{
    			simpleDetailsVOList = trainingCampService.getAttendedCountsByProgramOrCampOrBatch(programIds,campId,batchId,null,null,fromType,callFrom,enrollmentYrIds);
    		}
    		
    	}catch(Exception e){
    		LOG.error("Exception Occured in getAttendedCountsByProgramOrCampOrBatch() method, Exception - ",e);
    	}
    	
    	return Action.SUCCESS;
    }
    
    public String getAttendedCountSummaryByBatch()
    {
    	try{
    		
    		jObj = new JSONObject(getTask());
    		//Long programId = jObj.getLong("programId");
    		Long campId = jObj.getLong("campId");
    		Long batchId = jObj.getLong("batchId");
    		String dates[] = jObj.getString("dates").split("-");
    		String callFrom = jObj.getString("callFrom");
    		 
    		JSONArray enrollmentYrIdsArr = jObj.getJSONArray("enrollmentYrIds");
    		List<Long> enrollmentYrIds = new ArrayList<Long>(0);
    		
    		if(enrollmentYrIdsArr != null && enrollmentYrIdsArr.length() > 0){
    			for(int i=0;i<enrollmentYrIdsArr.length();i++){
    				enrollmentYrIds.add(Long.parseLong(enrollmentYrIdsArr.getString(i)));
    			}
    		}
    		
    		List<Long> programIds = new ArrayList<Long>(0);
    		JSONArray programIdsArr = jObj.getJSONArray("programIds");
    		if(programIdsArr != null && programIdsArr.length() > 0)
    		{
    			for(int i=0;i<programIdsArr.length();i++)
    				programIds.add(new Long(programIdsArr.get(i).toString()));
    		}
    		
    		if(dates.length>1){
    			simpleVOList = trainingCampService.getAttendedCountSummaryByBatch(campId,batchId,dates[0].trim(),dates[1].trim(),callFrom,enrollmentYrIds,programIds);
    		}else{
    			simpleVOList = trainingCampService.getAttendedCountSummaryByBatch(campId,batchId,null,null,callFrom,enrollmentYrIds,programIds);
    		}
    		
    		
    	}catch(Exception e){
    		LOG.error("Exception Occured in getAttendedCountSummaryByBatch() method, Exception - ",e);
    	}
    	
    	return Action.SUCCESS;
    }
    
    public String getProgramSummary()
    {
    	try{
    		
    		jObj = new JSONObject(getTask());
    		Long programId = jObj.getLong("programId");
    		String date[] = jObj.getString("dates").split("-");
    		if(date.length>1){
    			simpleVO = trainingCampService.getProgramSummary(programId,date[0].trim(),date[1].trim());
    		}else{
    			simpleVO = trainingCampService.getProgramSummary(programId,null,null);
    		}
    		
    		
    	}catch(Exception e){
    		LOG.error("Exception Occured in getProgramSummary() method, Exception - ",e);
    	}
    	
    	return Action.SUCCESS;
    }
    public String getCampSummary()
    {
    	try{
    		
    		jObj = new JSONObject(getTask());
    		
    		Long programId = jObj.getLong("programId");
    		Long campId = jObj.getLong("campId");
    		
    		String date[] = jObj.getString("dates").split("-");
    		if(date.length>1){
    			simpleVO = trainingCampService.getCampSummary(programId,campId,date[0].trim(),date[1].trim());
    		}else{
    			simpleVO = trainingCampService.getCampSummary(programId,campId,null,null);
    		}
    		
    		
    	}catch(Exception e){
    		LOG.error("Exception Occured in getCampSummary() method, Exception - ",e);
    	}
    	
    	return Action.SUCCESS;
    }
    
    public String getProgCampBatchNames(){
    	try {
			LOG.info("Entered into getProgCampBatchNames");
			jObj = new JSONObject(getTask());
			simpleVO = trainingCampService.getProgCampBatchNames(jObj.getLong("programId"),jObj.getLong("campId"),jObj.getLong("batchId"));
		} catch (Exception e) {
			LOG.error("Exception raised at getProgCampBatchNames",e);
		}
    	return Action.SUCCESS;
    }
    public String getDateWiseAttendedAndAbsentCandidates(){
    	try{
    		jObj = new JSONObject(getTask());
    		Long batchId = jObj.getLong("batchId");
    		Long enrollmentYearId = jObj.getLong("enrollmentYearId");
    		cadreVoList = trainingCampService.getDateWiseAttendedAndAbsentCandidates(batchId,enrollmentYearId);
    	}
    	catch(Exception e){
    		LOG.error("Exception Occured in getDateWiseAttendedAndAbsentCandidates() method, Exception - ",e);
    	}
    	return Action.SUCCESS;
    }
    
    public String getBatchesForCentre(){
    	try{
    		jObj = new JSONObject(getTask());
    		Long programId = jObj.getLong("programId");
    		Long campId = jObj.getLong("campId");
    		idNameList = trainingCampService.getBatchesForCentre(programId,campId);
    		
    	}catch(Exception e){
    		LOG.error("Exception Occured in getBatchesForCentre() method, Exception - ",e);
    	}
    	return Action.SUCCESS;
    }
    
    public String getMaxNumberForBatch()
    {
    	try{
    	jObj = new JSONObject(getTask());
    	Long batchId = jObj.getLong("batchId");
    	Long statusId = jObj.getLong("scheduleStatusId");
    	String callPurpose =jObj.getString("callPurpose");
    	
    	memberList = trainingCampService.getMaxNumberForBatch(batchId,statusId,callPurpose);
    			
    	}catch(Exception e){
    		LOG.error("Exception Occured in getMaxNumberForBatch() method, Exception - ",e);
    	}
    	return Action.SUCCESS;
    }
    
    public String getCallBackLaterMembersCount()
    {
    	try{
    		
	    	jObj = new JSONObject(getTask());
	    	Long campId = jObj.getLong("campId");
	    	String startDateStr = jObj.getString("startDate");
	    	String endDateStr = jObj.getString("endDate");
	    	
	    	campVoList = trainingCampService.getCallBackLaterMembersCount(campId,startDateStr,endDateStr);
    			
    	}catch(Exception e){
    		LOG.error("Exception Occured in getCallBackLaterMembersCount() method, Exception - ",e);
    	}
    	return Action.SUCCESS;
    }    
    
    public String updateMobileNumberForCadre()
    {
    	try{
    		
    		RegistrationVO regVO =(RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO!=null){
				Long userId = regVO.getRegistrationID();
				jObj = new JSONObject(getTask());
	    		Long tdpCadreId = jObj.getLong("tdpCadreId");
	    		String mobileNo = jObj.getString("mobileNo");
	    		resultStatus = cadreRegistrationService.updateMobileNumberForCadre(tdpCadreId,mobileNo,userId);
			}else{
				return Action.INPUT;
			}
			
    		 
    	}catch (Exception e) {
    		LOG.error("Exception Occured in updateMobileNumber() method, Exception - ",e);
		}
    	return Action.SUCCESS;
    }
    
    public String getDayWiseCountsForRunningBatches(){
    	try {
			LOG.info("Entered into getDayWiseCountsForRunningBatches");
			jObj=new JSONObject(getTask());
			
			String selDate = jObj.getString("selectedDate");
			List<Long> enrollmentYearIds=new ArrayList<Long>();
			JSONArray enrollmentYearIdsArray=jObj.getJSONArray("enrollmentYearIdsList");
			if(enrollmentYearIdsArray!=null &&  enrollmentYearIdsArray.length()>0){
				for( int i=0;i<enrollmentYearIdsArray.length();i++){
					enrollmentYearIds.add(Long.valueOf(enrollmentYearIdsArray.getString(i)));
				}
			}
			String temp[] = selDate.split("-");
			List<Long> programYearIds =new ArrayList<Long>();
			JSONArray programIdsArray=jObj.getJSONArray("programIdsList");
			if(programIdsArray!=null &&  programIdsArray.length()>0){
				for( int i=0;i<programIdsArray.length();i++){
					programYearIds.add(Long.valueOf(programIdsArray.getString(i)));
				}
			}
			
			if(temp.length>1){
				simpleVOList = trainingCampService.getDayWiseCountsForRunningBatches(temp[0].trim(),temp[1].trim(),1l,enrollmentYearIds,programYearIds);
			}else{
				simpleVOList = trainingCampService.getDayWiseCountsForRunningBatches(null,null,1l,enrollmentYearIds,programYearIds);
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised at getDayWiseCountsForRunningBatches", e);
		}
    	return Action.SUCCESS;
    }
    
    public String getDayWiseAttendnenceForBatch(){
    	try {
			LOG.info("Entered into getDayWiseAttendnenceForBatch");
			jObj=new JSONObject(getTask());
			
			Long selDate = jObj.getLong("batchId");
			//Long enrollmentYearId = jObj.getLong("enrollmentYearId");
			 List<Long> enrollmentYearIds=new ArrayList<Long>();
				JSONArray enrollmentYearIdsArray=jObj.getJSONArray("enrollmentYearIdsList");
				if(enrollmentYearIdsArray!=null &&  enrollmentYearIdsArray.length()>0){
					for( int i=0;i<enrollmentYearIdsArray.length();i++){
						enrollmentYearIds.add(Long.valueOf(enrollmentYearIdsArray.getString(i)));
					}
				}
				List<Long> programYearIds=new ArrayList<Long>();
				 JSONArray programIdsArray=jObj.getJSONArray("programIdsList");
							if(programIdsArray!=null &&  programIdsArray.length()>0){
								for( int i=0;i<programIdsArray.length();i++){
									programYearIds.add(Long.valueOf(programIdsArray.getString(i)));
								}
							}
			simpleVO=trainingCampService.getDayWiseAttendnenceForBatch(selDate,enrollmentYearIds,programYearIds);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getDayWiseAttendnenceForBatch", e);
		}
    	return Action.SUCCESS;
    }
    public String getAttendenceForTrainers(){
    	try {
			LOG.info("ntered into getAttendenceForTrainers");
			jObj=new JSONObject(getTask());
			String type=jObj.getString("type");
			String searchType=jObj.getString("searchType");
			List<Long> enrollmentYearIds=new ArrayList<Long>();
			JSONArray enrollmentYearIdsArray=jObj.getJSONArray("enrollmentYearIdsList");
			if(enrollmentYearIdsArray!=null &&  enrollmentYearIdsArray.length()>0){
				for( int i=0;i<enrollmentYearIdsArray.length();i++){
					enrollmentYearIds.add(Long.valueOf(enrollmentYearIdsArray.getString(i)));
				}
			}
			List<Long> programYearIds=new ArrayList<Long>();
			 JSONArray programIdsArray=jObj.getJSONArray("programIdsList");
						if(programIdsArray!=null &&  programIdsArray.length()>0){
							for( int i=0;i<programIdsArray.length();i++){
								programYearIds.add(Long.valueOf(programIdsArray.getString(i)));
							}
						}
			simpleVOList = trainingCampService.getAttendenceForTrainers(type,searchType,enrollmentYearIds,programYearIds);
		} catch (Exception e) {
			LOG.error("Exception raised at getAttendenceForTrainers", e);
		}
    	return Action.SUCCESS;
    }
    
    public String getFeedbackCategories()
    {
    	try{
    		
    		jObj = new JSONObject(getTask());
    		Long batchId = jObj.getLong("batchId");
    		Long programId = jObj.getLong("programId");
    		Long campId = jObj.getLong("campId");
    		idnemIdNameVOs = trainingCampService.getFeedbackCategoriesForTraining(programId,campId,batchId);
    		
    	}catch (Exception e) {
    		LOG.error("Exception Occured in getFeedbackCategories() method, Exception - ",e);
		}
    	return Action.SUCCESS;
    }
    
    public String getTrainingFeedBackQuestionsList()
    {
    	try{
    		FeedbackInputVO vo = new FeedbackInputVO();
    		List<Long> categoryIds = new ArrayList<Long>();
    		jObj = new JSONObject(getTask());
    		vo.setBatchId(jObj.getLong("batchId"));
    		vo.setProgramId(jObj.getLong("programId"));
    		vo.setCampId(jObj.getLong("campId"));
    		vo.setId(jObj.getLong("cadreId"));
    		JSONArray arr = jObj.getJSONArray("categoryIds");
    		if(arr != null && arr.length() > 0)
    		{
    			for(int i=0;i<arr.length();i++)
    			categoryIds.add(new Long(arr.get(i).toString()));
    		}
    		quetsionsList = trainingCampService.getTrainingFeedBackQuestionsList(vo,categoryIds);
    		
    	}catch (Exception e) {
    		LOG.error("Exception Occured in getFeedbackCategories() method, Exception - ",e);
		}
    	return Action.SUCCESS;
    }
    
    public String getBatches(){
    	try {
			LOG.info(" Entered into getBatches ");
			jObj=new JSONObject(getTask());
			String programType = jObj.getString("programType");
			Long programId = jObj.getLong("programId");
			Long campId = jObj.getLong("campId");
			
			idNameList = trainingCampService.getBatches(programType,programId,campId);
		} catch (Exception e) {
			LOG.error("Exception raised at getBatches", e);
		}
    	return Action.SUCCESS;
    }
    
    public String saveCategorysDetails()
    {
    	try {
    		jObj = new JSONObject(getTask());
    	
    		List<SimpleVO> categoryAnsrList = new ArrayList<SimpleVO>(0);
    		Long tdpCadreId =0L;
    			JSONArray jarr = jObj.getJSONArray("arr");
			
			if(jarr != null && jarr.length() > 0)
			{
				for(int i=0;i<jarr.length();i++)
				{
				SimpleVO inputVo =  new SimpleVO();
				JSONObject jObj=(JSONObject) jarr.get(i);
				inputVo.setId(jObj.getLong("id"));
				inputVo.setName(jObj.getString("answer"));
				categoryAnsrList.add(inputVo);
				}
			}
    		status = trainingCampService.saveCadreFeedBackAnswers(jObj.getLong("tdpCadreId"),categoryAnsrList);
		} catch (Exception e) {
			LOG.error("Exception raised at saveCategorysDetailsAction", e);
		}
    	
    	return Action.SUCCESS;
    }
    
    public String getProgramCampBatchDetailsForAMemberBasedOnCadreId(){
    	try {
			LOG.info("Entered into getProgramCampBatchDetailsForAMemberBasedOnCadreId");
			jObj = new JSONObject(getTask());
			
			Long cadreId = jObj.getLong("cadreId");
			String type = jObj.getString("type");
			
			List<Long> cadteIdList = new ArrayList<Long>(0);
			cadteIdList.add(cadreId);
			
			simpleVOList = trainingCampService.getProgramCampBatchDetailsForAMemberBasedOnCadreId(cadteIdList,type);
		} catch (Exception e) {
			LOG.error("Exception raised at getProgramCampBatchDetailsForAMemberBasedOnCadreId", e);
		}
    	return Action.SUCCESS;
    }
    
    public String getFeedBackCountsOfTraining(){
    	try{    
    		
    		jObj = new JSONObject(getTask());	
    		
    		String selDate = jObj.getString("dates");
			
    		String temp[] = null;
    		if(selDate !=null && !selDate.isEmpty()){
    			temp = selDate.split("-");
    		}
    		List<Long> enrollmentYearIds=new ArrayList<Long>();
			JSONArray enrollmentYearIdsArray=jObj.getJSONArray("enrollmentYearIdsList");
			if(enrollmentYearIdsArray!=null &&  enrollmentYearIdsArray.length()>0){
				for( int i=0;i<enrollmentYearIdsArray.length();i++){
					enrollmentYearIds.add(Long.valueOf(enrollmentYearIdsArray.getString(i)));
				}
			}
			List<Long> programYearIds=new ArrayList<Long>();
			 JSONArray programIdsArray=jObj.getJSONArray("programIdsList");
						if(programIdsArray!=null &&  programIdsArray.length()>0){
							for( int i=0;i<programIdsArray.length();i++){
								programYearIds.add(Long.valueOf(programIdsArray.getString(i)));
							}
						}
    		if(temp !=null && temp.length>0){
    			campVoList = trainingCampService.getFeedBackCountsOfTraining(temp[0].toString(),temp[1].toString(),enrollmentYearIds,programYearIds);
    		}else{
    			campVoList = trainingCampService.getFeedBackCountsOfTraining("","",enrollmentYearIds,programYearIds);
    		}
    		
    		
    		
    	}catch (Exception e) {
    		LOG.error("Exception raised at getFeedBackCountsOfTraining", e);
		}
    	return Action.SUCCESS;
    }
    
    public String feedbackOverViewDetails(){
    	return Action.SUCCESS;
    }
    public String getProgramsForFeedBack(){
    	try{
    		
    		idNameList = trainingCampService.getProgramsForFeedBack();
    		
    	}catch (Exception e) {
    		LOG.error("Exception raised at getProgramsForFeedBack", e);
		}
    	return Action.SUCCESS;
    }
    
    public String getFeedbackCategoryCountsCenterWise(){
    	try{
    		jObj = new JSONObject(getTask());			
			Long programId = jObj.getLong("programId");
			
			String selDate = jObj.getString("dates");
			
    		String temp[] = null;
    		if(selDate !=null && !selDate.isEmpty()){
    			temp = selDate.split("-");
    		}
			
    		if(temp !=null && temp.length>0){
    			campVoList = trainingCampService.getFeedbackCategoryCountsCenterWise(programId,temp[0].trim(),temp[1]);
    		}else{
    			campVoList = trainingCampService.getFeedbackCategoryCountsCenterWise(programId,null,null);
    		}

    		
    	}catch (Exception e) {
    		LOG.error("Exception raised at getFeedbackCategoryCountsCenterWise", e);
		}
    	return Action.SUCCESS;
    }
    public String getFeedbackDetailsOfCadre(){
    	try{
    		jObj = new JSONObject(getTask());			
			Long programId = jObj.getLong("programId");
			Long locationId = jObj.getLong("locationId");
			String type = jObj.getString("type");
			Long categoryId = jObj.getLong("categoryId");
			String selDate = jObj.getString("dates");
			
			String temp[] = null;
    		if(selDate !=null && !selDate.isEmpty()){
    			temp = selDate.split("-");
    		}
			
    		if(temp !=null && temp.length>0){
    			simpleVOList = trainingCampService.getFeedbackDetailsOfCadre(locationId,programId,type,temp[0].trim(),temp[1],categoryId);
    		}else{
    			simpleVOList = trainingCampService.getFeedbackDetailsOfCadre(locationId,programId,type,null,null,categoryId);
    		}
    		
    		
    	}catch (Exception e) {
    		LOG.error("Exception raised at getFeedbackCategoryCountsCenterWise", e);
		}
    	return Action.SUCCESS;
    }
    public String getAllDistrictsByState(){
    	try{
    		jObj = new JSONObject(getTask());			
			Long stateId = jObj.getLong("stateId");
			
			idNameList=trainingCampService.getAllDistrictsByState(stateId);
			
    	}catch(Exception e){
    		LOG.error("Exception raised at getAllDistrictsByState", e);
    	}
    	return Action.SUCCESS;
    }
    public String getAllConstituencysByDistrict(){
    	try{
    		jObj = new JSONObject(getTask());			
			Long districtId = jObj.getLong("districtId");
			
			idNameList=trainingCampService.getAllConstituencysByDistrict(districtId);
			
    	}catch(Exception e){
    		LOG.error("Exception raised at getAllConstituencysByDistrict", e);
    	}
    	return Action.SUCCESS;
    }
    
    public String getFeedbackDetailsOfEachDistrictAndConstituencyWise(){
    	try {
			LOG.info("Entered into getFeedbackDetailsOfEachDistrictAndConstituencyWise");
			
			jObj = new JSONObject(getTask());
			
			Long districtId = jObj.getLong("districtId");
			Long constituencyId = jObj.getLong("constituencyId");
			Long categoryId = jObj.getLong("categoryId");
			
			String selDate = jObj.getString("dates");
			
			String temp[] = null;
    		if(selDate !=null && !selDate.isEmpty()){
    			temp = selDate.split("-");
    		}
			
			
			List<Long> districtIds = new ArrayList<Long>(0);
			if(districtId>0l){
				districtIds.add(districtId);
			}
			
			List<Long> constituencyIds = new ArrayList<Long>(0);
			if(constituencyId>0l){
				constituencyIds.add(constituencyId);
			}
			
			List<Long> categoryIds = new ArrayList<Long>(0);
			if(categoryId>0l){
				categoryIds.add(categoryId);
			}
			
			if(temp !=null && temp.length>0){
				campVoList = trainingCampService.getFeedbackDetailsOfEachDistrictAndConstituencyWise(districtIds,constituencyIds,categoryIds,jObj.getLong("programId"),jObj.getString("type"),temp[0].trim(),temp[1]);
			}else{
				campVoList = trainingCampService.getFeedbackDetailsOfEachDistrictAndConstituencyWise(districtIds,constituencyIds,categoryIds,jObj.getLong("programId"),jObj.getString("type"),null,null);
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised at getFeedbackDetailsOfEachDistrictAndConstituencyWise", e);
		}
    	return Action.SUCCESS;
    }
    
    public String getAllCategories(){
    	try {
			LOG.info("Entered into getAllCategories");
			idNameList = trainingCampService.getAllCategories();
		} catch (Exception e) {
			LOG.error("Entered into getAllCategories", e);
		}
    	return Action.SUCCESS; 
    }
    public String getDaysAttendedCadreDetails(){
    	try{
    		
    		jObj = new JSONObject(getTask());
    		
    		Long batchId = jObj.getLong("batchId");
    		String dataType=jObj.getString("dataType");
    		String type=jObj.getString("type");
    		JSONArray enrollmentYrIdsArr = jObj.getJSONArray("enrollmentYrIds");
    		List<Long> enrollmentYrIds = new ArrayList<Long>(0);
    		
    		if(enrollmentYrIdsArr != null && enrollmentYrIdsArr.length() > 0){
    			for(int i=0;i<enrollmentYrIdsArr.length();i++){
    				enrollmentYrIds.add(Long.parseLong(enrollmentYrIdsArr.getString(i)));
    			}
    		}
    		List<Long> programYearIds=new ArrayList<Long>();
			 JSONArray programIdsArray=jObj.getJSONArray("programIdsList");
						if(programIdsArray!=null &&  programIdsArray.length()>0){
							for( int i=0;i<programIdsArray.length();i++){
								programYearIds.add(Long.valueOf(programIdsArray.getString(i)));
							}
						}
    		simpleVOList=trainingCampService.getDaysAttendedCadreDetails(batchId,dataType,type,enrollmentYrIds,programYearIds);
    	}catch (Exception e) {
    		LOG.error("Exception raised at getDaysAttendedCadreDetails", e);
		}
    	return Action.SUCCESS;
    }
public String getUpdateTrainingCampSpeakersDetails(){
    	try{
    		jObj = new JSONObject(getTask());
    		partyMeetingType =schedulerService.updateTrainingCampSpeakersDetails();
    	}catch (Exception e) {
    		LOG.error("Exception raised at getUpdateTrainingCampSpeakersDetails", e);
		}
    	return Action.SUCCESS;
    }
public String getMandalsForDistrictIdDetails(){
	try{
		jObj = new JSONObject(getTask());
		JSONArray arr = jObj.getJSONArray("districtId");
		List<Long> list = new ArrayList<Long>(0);
		
		if(arr != null && arr.length() > 0){
			for(int i=0;i<arr.length();i++){
				list.add(Long.parseLong(arr.getString(i)));
			}
		}
		
		simpleVOList =trainingCampService.getMandalsForDistrictIdDetails(list);
	}catch (Exception e) {
		LOG.error("Exception raised at getMandalsForDistrictIdDetails", e);
	}
	return Action.SUCCESS;
}
public String getVillagesForDistrictIdDetails(){
	try{
		jObj = new JSONObject(getTask());
		JSONArray arr = jObj.getJSONArray("districtId");
		List<Long> list = new ArrayList<Long>(0);
		
		if(arr != null && arr.length() > 0){
			for(int i=0;i<arr.length();i++){
				list.add(Long.parseLong(arr.getString(i)));
			}
		}
		
		simpleVOList =trainingCampService.getVillagesForDistrictIdDetails(list);
	}catch (Exception e) {
		LOG.error("Exception raised at getVillagesForDistrictIdDetails", e);
	}
	return Action.SUCCESS;
}
public String getMeetingTypesNew(){
	try{
		LOG.info("Entered into getMeetingTypes");
		jObj = new JSONObject(getTask());
		
		JSONArray arr = jObj.getJSONArray("locationLevelsArr");
		List<Long> list = new ArrayList<Long>(0);
		
		if(arr != null && arr.length() > 0){
			for(int i=0;i<arr.length();i++){
				list.add(Long.parseLong(arr.getString(i)));
			}
		}
		
		retResult = trainingCampService.getMeetingTypesNew(list);
	}catch (Exception e) {
		LOG.error("Exception raised into getMeetingTypesNew",e);
	}
	return Action.SUCCESS;
}
public String getMomPartyMeetingDetails(){
	return Action.SUCCESS;
}
public String getFinalAllMeetings(){
	try {
		LOG.info("entered into getAllMeetings");
		jObj = new JSONObject(getTask());
		
		List<Long> stateIds = new ArrayList<Long>();
		List<Long> distIds = new ArrayList<Long>();
		List<Long> constIds = new ArrayList<Long>();
		List<Long> manTowDivIds = new ArrayList<Long>();
		List<Long> villWardIds = new ArrayList<Long>();
		
		JSONArray jsonArray = jObj.getJSONArray("sateId");
		for (int i = 0; i < jsonArray.length(); i++) {
			Long sateId1 = Long.valueOf(jsonArray.get(i).toString());
			stateIds.add(sateId1);
		}
		
		JSONArray jsonArray1 = jObj.getJSONArray("districtId");
		for (int i = 0; i < jsonArray1.length(); i++) {
			Long distId1 = Long.valueOf(jsonArray1.get(i).toString());
			distIds.add(distId1);
		}
		
		JSONArray jsonArray2 = jObj.getJSONArray("constituencyId");
		for (int i = 0; i < jsonArray2.length(); i++) {
			Long constId1 = Long.valueOf(jsonArray2.get(i).toString());
			constIds.add(constId1);
		}
		
		JSONArray jsonArray3 = jObj.getJSONArray("mandalTownDivisonId");
		for (int i = 0; i < jsonArray3.length(); i++) {
			Long mtdId1 = Long.valueOf(jsonArray3.get(i).toString());
			manTowDivIds.add(mtdId1);
		}
		
		JSONArray jsonArray4 = jObj.getJSONArray("villageWardId");
		for (int i = 0; i < jsonArray4.length(); i++) {
			Long vwId1 = Long.valueOf(jsonArray4.get(i).toString());
			villWardIds.add(vwId1);
		}
		
		Long meetingType = jObj.getLong("meetingType");
		Long locationLevel = jObj.getLong("locationLevel");
		//Long stateId = jObj.getLong("sateId");
		//Long districtId = jObj.getLong("districtId");
		//Long constituencyId = jObj.getLong("constituencyId");
		//Long mandalTownDivisonId = jObj.getLong("mandalTownDivisonId");
		//Long villageWardId = jObj.getLong("villageWardId");
		String startDate = jObj.getString("startDate");
		String endDate = jObj.getString("endDate");
		String mayBe = jObj.getString("mayBe");
		
		retResult = trainingCampService.getFinalAllMeetings(meetingType,locationLevel,stateIds,distIds,constIds,manTowDivIds,villWardIds,startDate,endDate,mayBe);
		
	} catch (Exception e) {
		LOG.error("Exception raised at getAllMeetings",e);
	}
	return Action.SUCCESS;
}
public String saveFinalizedMeetingDetails(){
	try{
		LOG.info("Entered into saveFinalizedMeetingDetails");
		RegistrationVO regVo =(RegistrationVO) request.getSession().getAttribute("USER");
		/*jObj = new JSONObject(getTask());
		
		Long partymeetingId = jObj.getLong("partyMeetingId");
		String memberType = jObj.getString("memberType");
		String membershipId = jObj.getString("membershipId");
		String name = jObj.getString("name");
		String mobileNo = jObj.getString("mobileNo");
		String remark =jObj.getString("remark");
		String statusId = jObj.getString("statusId");
		String updateBy = jObj.getString("updatedBy");*/
		
		String imageName=null;
		List<String> fileNamesList = new ArrayList<String>(0);
		
		for(int i=0;i<imageForDisplay.size();i++){
      	  String fileType = imageForDisplayContentType.get(i).substring(imageForDisplayContentType.get(i).indexOf("/")+1, imageForDisplayContentType.get(i).length());
	        	 
	          imageName= UUID.randomUUID().toString()+"_"+imageForDisplayFileName.get(i);
	          fileNamesList.add(IConstants.PARTY_MEETINGS+"/"+imageName);
	          
	          String filePath=IConstants.STATIC_CONTENT_FOLDER_PATH+"/"+IConstants.PARTY_MEETINGS;
	        	 
	          File fileToCreate = new File(filePath,imageName);
			  FileUtils.copyFile(imageForDisplay.get(i), fileToCreate);
			  
			  inputStream = new StringBufferInputStream("success");
        }
		
		Long userId = regVo.getRegistrationID();
		status = trainingCampService.saveFinalizedMeetingDetails(updateFinalyzeMeetingId,"",updateMemberShipNumber,updateName,updateMobileNumber,updateRemarks,updateStatusId,"IC",userId,fileNamesList);
	}catch (Exception e) {
		LOG.error("Exception raised into saveFinalizedMeetingDetails",e);
	}
	return Action.SUCCESS;
}
public String getCommentsMeetingDetails(){
	try{
		LOG.info("Entered into getCommentsMeetingDetails");
		RegistrationVO regVo =(RegistrationVO) request.getSession().getAttribute("USER");
		jObj = new JSONObject(getTask());
		
		Long partymeetingId = jObj.getLong("partyMeetingId");
		Long userId = regVo.getRegistrationID();
		retResult = trainingCampService.getCommentsMeetingDetails(partymeetingId);
	}catch (Exception e) {
		LOG.error("Exception raised into getCommentsMeetingDetails",e);
	}
	return Action.SUCCESS;
}

	public String getattendedcountByFeedBacksCounts(){
		try {
			RegistrationVO regVo =(RegistrationVO) request.getSession().getAttribute("USER");
			jObj = new JSONObject(getTask());
			
			Long campId = jObj.getLong("campId");
    		if(campId==0l){
    			campId=null;
    		}
    		Long batchId = jObj.getLong("batchId");
    		if(batchId==0l){
    			batchId=null;
    		}
    		List<Long> enrollmentYrIds = new ArrayList<Long>(0);
    		JSONArray enrollmentYrIdsArr = jObj.getJSONArray("enrollmentYrIds");
    		if(enrollmentYrIdsArr != null && enrollmentYrIdsArr.length() > 0)
    		{
    			for(int i=0;i<enrollmentYrIdsArr.length();i++)
    				enrollmentYrIds.add(new Long(enrollmentYrIdsArr.get(i).toString()));
    		}
    		List<Long> programIds = new ArrayList<Long>(0);
    		JSONArray programIdsArr = jObj.getJSONArray("programIds");
    		if(programIdsArr != null && programIdsArr.length() > 0)
    		{
    			for(int i=0;i<programIdsArr.length();i++)
    				programIds.add(new Long(programIdsArr.get(i).toString()));
    		}
    		
    		String dates[] = jObj.getString("dates").split("-");
    		
    		if(dates.length>1){
    			partyMeetingWSVOList = trainingCampService.getattendedcountByFeedBacksCounts(programIds,campId,batchId,dates[0].trim(),dates[1].trim(),jObj.getString("callFrom"),enrollmentYrIds,jObj.getString("skillType"),jObj.getLong("statusId"));
    		}else{
    			partyMeetingWSVOList = trainingCampService.getattendedcountByFeedBacksCounts(programIds,campId,batchId,null,null,jObj.getString("callFrom"),enrollmentYrIds,jObj.getString("skillType"),jObj.getLong("statusId"));
    		}
			
		} catch (Exception e) {
			LOG.error("Exception raised at getattendedcountByFeedBacksCounts Action", e);
		}
		return Action.SUCCESS;
	}
	
	public String getAllMomAtrClickDetails(){
		try {

			LOG.info("entered into getAllMeetings");
			
			String accessType=null;
			String accessValue=null;
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO !=null){
				accessType = regVO.getAccessType();
				accessValue = regVO.getAccessValue();
			}
			
			jObj = new JSONObject(getTask());
			
			List<Long> stateIds = new ArrayList<Long>();
			List<Long> distIds = new ArrayList<Long>();
			List<Long> constIds = new ArrayList<Long>();
			List<Long> manTowDivIds = new ArrayList<Long>();
			List<Long> villWardIds = new ArrayList<Long>();
			
			JSONArray jsonArray = jObj.getJSONArray("sateId");
			for (int i = 0; i < jsonArray.length(); i++) {
				Long sateId1 = Long.valueOf(jsonArray.get(i).toString());
				stateIds.add(sateId1);
			}
			
			JSONArray jsonArray1 = jObj.getJSONArray("districtId");
			for (int i = 0; i < jsonArray1.length(); i++) {
				Long distId1 = Long.valueOf(jsonArray1.get(i).toString());
				distIds.add(distId1);
			}
			
			JSONArray jsonArray2 = jObj.getJSONArray("constituencyId");
			for (int i = 0; i < jsonArray2.length(); i++) {
				Long constId1 = Long.valueOf(jsonArray2.get(i).toString());
				constIds.add(constId1);
			}
			
			JSONArray jsonArray3 = jObj.getJSONArray("mandalTownDivisonId");
			for (int i = 0; i < jsonArray3.length(); i++) {
				Long mtdId1 = Long.valueOf(jsonArray3.get(i).toString());
				manTowDivIds.add(mtdId1);
			}
			
			JSONArray jsonArray4 = jObj.getJSONArray("villageWardId");
			for (int i = 0; i < jsonArray4.length(); i++) {
				Long vwId1 = Long.valueOf(jsonArray4.get(i).toString());
				villWardIds.add(vwId1);
			}
			
			Long meetingType = jObj.getLong("meetingType");
			Long locationLevel = jObj.getLong("locationLevel");
			
			String startDate = jObj.getString("startDate");
			String endDate = jObj.getString("endDate");
			
			
			
			keyValueVOlist = trainingCampService.getAllMomAtrClickDetails(meetingType,locationLevel,stateIds,distIds,constIds,manTowDivIds,villWardIds,startDate,endDate,jObj.getString("type"),accessType,accessValue);
			
		
		} catch (Exception e) {
			LOG.error("Exception raised at getAllMomAtrClickDetails Action", e);
		}
		return Action.SUCCESS;
	}
	public String getTrainingSurveyDetail(){
		try {
			LOG.info("Entered into trainingCampAction of getTrainingSurveyDetail()");
			jObj = new JSONObject(getTask());
			Long trainingProgramId  =jObj.getLong("trainingProgramId");
			Long trainingCampId  =jObj.getLong("trainingCampId");
			Long trainignBatchId  =jObj.getLong("trainignBatchId");
			verifyVOLst = trainingCampService.getTrainingSurveyDetails(trainingProgramId,trainingCampId,trainignBatchId);
		} catch (Exception e) {
			LOG.error("Exception raised at getTrainingSurveyDetail Action", e);
		}
		return Action.SUCCESS;
	}
	public String getTrainingCampDetailsByCampIds(){
		try {
			LOG.info("Entered into getTrainingCampDetailsByCampIds of getTrainingSurveyDetail()");
			List<Long> triningCampIdsList=new ArrayList<Long>();
			jObj = new JSONObject(getTask());
			JSONArray jsonArry=jObj.getJSONArray("traingCampIds");
			for(int i=0;i <jsonArry.length(); i++){
				triningCampIdsList.add(Long.valueOf(jsonArry.get(i).toString()));
			}
			keyValueVOlist=trainingCampService.getTrainingCampDetailsByCampIds(triningCampIdsList);
		} catch (Exception e) {
			LOG.error("Exception raised at getTrainingCampDetailsByCampIds method", e);
		}
		return Action.SUCCESS;
	}
	public String getTrainingProgramDetailsByProgramIds(){
		try {
			LOG.info("Entered into getTrainingProgramDetailsByProgramIds of getTrainingSurveyDetail()");
			List<Long> triningProgramIdsList=new ArrayList<Long>();
			jObj = new JSONObject(getTask());
			JSONArray jsonArry=jObj.getJSONArray("traingProgramIds");
			for(int i=0;i <jsonArry.length(); i++){
				triningProgramIdsList.add(Long.valueOf(jsonArry.get(i).toString()));
			}
			keyValueVOlist=trainingCampService.getTrainingProgramDetailsByProgramIds(triningProgramIdsList);
		} catch (Exception e) {
			LOG.error("Exception raised at getTrainingProgramDetailsByProgramIds method", e);
		}
		return Action.SUCCESS;
	}
	public String getInviteeAndNonInviteeTrainingDetails(){
		try {
			LOG.info("Entered into trainingCampAction of getTrainingSurveyDetail()");
			jObj = new JSONObject(getTask());
			JSONArray enrollmentYrIdsArr = jObj.getJSONArray("enrollmentYrIds");
			List<Long> enrollmentYrIds =new ArrayList<Long>();
    		if(enrollmentYrIdsArr != null && enrollmentYrIdsArr.length() > 0){
    			for(int i=0;i<enrollmentYrIdsArr.length();i++)
    				enrollmentYrIds.add(new Long(enrollmentYrIdsArr.get(i).toString()));
    		}
    		List<Long> programIds = new ArrayList<Long>(0);
    		JSONArray programIdsArr = jObj.getJSONArray("programIds");	
    		if(programIdsArr != null && programIdsArr.length() > 0)
    		{
    			for(int i=0;i<programIdsArr.length();i++)
    				programIds.add(new Long(programIdsArr.get(i).toString()));
    		}
    		
    		String dates[] = jObj.getString("dates").split("-");
    		if(dates.length>1){
    			trainingCampVOList = trainingCampService.getInviteeAndNonInviteeTrainingCampWiseDetails(dates[0].trim(),dates[1].trim(),enrollmentYrIds,programIds);
    		}else{
    			trainingCampVOList = trainingCampService.getInviteeAndNonInviteeTrainingCampWiseDetails(null,null,enrollmentYrIds,programIds);
    		}
		} catch (Exception e) {
			LOG.error("Exception raised at getInviteeAndNonInviteeTrainingList Action", e);
		}
		return Action.SUCCESS;
	}
	public String getTrainingCampFeedBAckDeatilesByTdpCadreId(){
		try {
			LOG.info("Entered into getTrainingCampFeedBAckDeatilesByTdpCadreId");
			jObj = new JSONObject(getTask());
			Long tdpCadreId=jObj.getLong("cadreId");
			tainingCampFeedBackList = trainingCampService.getTrainingCampFeedBAckDeatilesByTdpCadreId(tdpCadreId);
		}catch(Exception e){
			LOG.error("Exception raised at getTrainingCampFeedBAckDeatilesByTdpCadreId Action", e);
		}
		return Action.SUCCESS;
	}
}
