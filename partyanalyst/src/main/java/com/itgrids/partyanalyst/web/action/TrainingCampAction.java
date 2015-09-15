package com.itgrids.partyanalyst.web.action;

import java.io.File;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
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
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.MeetingVO;
import com.itgrids.partyanalyst.dto.PartyMeetingVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SimpleVO;
import com.itgrids.partyanalyst.dto.TraingCampCallerVO;
import com.itgrids.partyanalyst.dto.TraingCampDataVO;
import com.itgrids.partyanalyst.dto.TrainingCadreVO;
import com.itgrids.partyanalyst.dto.TrainingCampCallStatusVO;
import com.itgrids.partyanalyst.dto.TrainingCampScheduleVO;
import com.itgrids.partyanalyst.dto.TrainingCampVO;
import com.itgrids.partyanalyst.dto.TrainingMemberVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
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
	//private List<TrainingCampScheduleVO> trainingCampScheduleVOs;
	private List<TraingCampCallerVO> statusCountList;
	private TrainingCampScheduleVO trainingCampScheduleVO;
	private EntitlementsHelper entitlementsHelper;
	
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
			if(entitlementsHelper.checkForEntitlementToViewReport(regVO,"TRAINING_CAMP_CALLER_ADMIN")){
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
		if(entitlementsHelper.checkForEntitlementToViewReport(regVO,"TRAINING_CAMP_CALLER") || 
				 entitlementsHelper.checkForEntitlementToViewReport(regVO,"TRAINING_CAMP_CALLER_ADMIN") || 
				 entitlementsHelper.checkForEntitlementToViewReport(regVO,"ADMIN_PAGE")){
			return Action.SUCCESS;
		}
		return Action.INPUT;
	}
	
	public String checkLoginForPartyMeeting(){
		RegistrationVO regVO =(RegistrationVO) request.getSession().getAttribute("USER");
		if(regVO!=null && regVO.getRegistrationID() >0l){
			return "success";
		}else{
			return "input";
		}
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
				Long constiId = Long.valueOf(jsonArray2.get(i).toString());
				constiIds.add(constiId);
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
			
			meetingDetails = trainingCampService.getPartyMeetingMinutesAtrDetails(partyMeetingId);
			
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
					if(!entitlementsHelper.checkForEntitlementToViewReport(regVO,"TRAINING_CAMP_FEEDBACK_UPDATE_ENTITLEMENT") &&
							!entitlementsHelper.checkForEntitlementToViewReport(regVO,"ADMIN_PAGE")){
						return Action.ERROR;
					}
				//}
			}else{
				return Action.INPUT;
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
			
			if(regVO.getIsAdmin().equalsIgnoreCase("true")){//Admin
				
				simpleVO=trainingCampService.getAllProgramsAndCamps();
				
			}else if(entitlementsHelper.checkForEntitlementToViewReport(regVO,"TRAINING_CAMP_FEEDBACK_UPDATE_ENTITLEMENT")){//entitled user.
				
				simpleVO=trainingCampService.getProgramsByUser(userId);
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
			
			cadreDetailsVO= trainingCampService.getDetailsForACadre(tdpCadreId,batchId);
			
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
			
			docsResultList = trainingCampService.getDocsOfPartyMeetingId(partyMeetingId,docSourceType);
			
		}catch(Exception e) {
			LOG.error("Exception Occured in getTdpCadreDetailsforASchedule() method, Exception - ",e);
		}
		return Action.SUCCESS;
    }
    
    public String saveDetailsOfCadreAction()
    {
    	
    	try{
    		RegistrationVO regVO =(RegistrationVO) request.getSession().getAttribute("USER");
    		Long userId = regVO.getRegistrationID();
    		/*if(regVO!=null){
    			Long userId = regVO.getRegistrationID();
    		}else{
    			return Action.INPUT;
    		}*/
    		jObj = new JSONObject(getTask());
    		Long tdpCadreId = jObj.getLong("tdpCadreId");
    		Long batchId = jObj.getLong("batchId");
    		
    		List<String> achieveList=new ArrayList<String>();
    		JSONArray achieveArray = jObj.getJSONArray("achieveArray");
    		for(int i = 0; i < achieveArray.length(); i++){
    			achieveList.add(achieveArray.get(i).toString());
    		}
    	   List<SimpleVO> goallist=new ArrayList<SimpleVO>();
           JSONArray goalArray = jObj.getJSONArray("goalArray");
			
			if(goalArray != null && goalArray.length() > 0)
			{
			  for(int i=0;i<goalArray.length();i++)
			 {
				JSONObject jObj=(JSONObject)goalArray.get(i);
				SimpleVO goal=new SimpleVO();
				goal.setName(jObj.getString("goal"));
				goal.setDateString(jObj.getString("date"));
				goallist.add(goal);
			  }
			}
    		
    		Long leaderShipLevelId = jObj.getLong("leaderShipLevel");
    		Long communicationSkillsId = jObj.getLong("communicationSkills");
    		Long leaderShipSkillsId = jObj.getLong("leaderShipSkills");
    		Long healthId = jObj.getLong("health");
    		String comments = jObj.getString("comments");
    		
    		String smartPhoneId = jObj.getString("smartPhoneId");
    		String whatsappId = jObj.getString("whatsappId");
    		String whatsappShareId = jObj.getString("whatsappShareId");
    		String facebookId = jObj.getString("facebookId");
    		
    		cadreDetailsVO = trainingCampService.saveDetailsOfCadre(tdpCadreId,batchId,achieveList,goallist,leaderShipLevelId,communicationSkillsId,leaderShipSkillsId,healthId,comments,userId,smartPhoneId,whatsappId,whatsappShareId,facebookId);
    		
    	}catch(Exception e) {
    		LOG.error("Exception Occured in saveAllDetailsAction() method, Exception - ",e);
    	}
    	
    	return Action.SUCCESS;
    }
    
    public String getAllBatchesByProgramAndCenter()
    {
    	try{
    		
    		jObj = new JSONObject(getTask());
    		Long programId = jObj.getLong("programId");
    		Long centerId = jObj.getLong("centerId");
    		
    		finalList = trainingCampService.getSchedulesListByProgramAndCenter(programId,centerId);
    		
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
    public String checkLoginForTrainingCenterDashBoard(){
    	RegistrationVO regVO =(RegistrationVO) request.getSession().getAttribute("USER");
		if(regVO!=null && regVO.getRegistrationID() >0l){
			return "success";
		}else{
			return "input";
		}
    }
    public String getAttendedCountForBatchesByLocation()
    {
    	try{
    		jObj = new JSONObject(getTask());
			String selDate = jObj.getString("selectedDate");
			
			String temp[] = selDate.split("-");
    		idNameList = trainingCampService.getAttendedCountForBatchesByLocation(temp[0].trim(),temp[1].trim(),0l);
    		
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
			
			String temp[] = selDate.split("-");
    		simpleVO = trainingCampService.getInvitedAttendedCadreCountByBatchIds(temp[0].trim(),temp[1].trim(),0l);
    		
    	}catch(Exception e){
    		LOG.error("Exception Occured in getAttendedCountForBatchesByLocation() method, Exception - ",e);
    	}
    	
    	return Action.SUCCESS;
    }
    
    public String trainingProgramDashBoard(){
    	RegistrationVO regVO =(RegistrationVO) request.getSession().getAttribute("USER");
		if(regVO!=null && regVO.getRegistrationID() >0l){
			return "success";
		}else{
			return "input";
		}
    }
    
    public String getTrainingCenterDetailsBasedOnDates(){
    	try {
			LOG.info("Entered into getTrainingCenterDetailsBasedOnDates");
			
			jObj = new JSONObject(getTask());
			String selDate = jObj.getString("selectedDate");
			
			String temp[] = selDate.split("-");
			returnResult = trainingCampService.getCompletedRunningUpcomingBatchIds(temp[1].trim(),temp[0].trim(),0l,"All");
		} catch (Exception e) {
			LOG.error("Exception raised at TODO: handle exception", e);
		}
    	return Action.SUCCESS;
    }
    
    public String getattendedcountByFeedBacks()
    {
    	try{
    		
    		jObj = new JSONObject(getTask());
    		
    		Long programId = jObj.getLong("programId");
    		if(programId==0l){
    			programId=null;
    		}
    		Long campId = jObj.getLong("campId");
    		if(campId==0l){
    			campId=null;
    		}
    		Long batchId = jObj.getLong("batchId");
    		if(batchId==0l){
    			batchId=null;
    		}
    		String dates[] = jObj.getString("dates").split("-");
    		
    		finalVO = trainingCampService.getattendedcountByFeedBacks(programId,campId,batchId,dates[0].trim(),dates[1].trim());
    		
    	}catch(Exception e){
    		LOG.error("Exception Occured in getattendedcountByFeedBacks() method, Exception - ",e);
    	}
    	
    	return Action.SUCCESS;
    }
    
    public String getAttendedCountsByProgramOrCampOrBatch()
    {
    	try{
    		
    		jObj = new JSONObject(getTask());
    		Long programId = jObj.getLong("programId");
    		if(programId==0l){
    			programId=null;
    		}
    		Long campId = jObj.getLong("campId");
    		if(campId==0l){
    			campId=null;
    		}
    		Long batchId = jObj.getLong("batchId");
    		if(batchId==0l){
    			batchId=null;
    		}
    		
    		String dates[] = jObj.getString("dates").split("-");
    		simpleVOList = trainingCampService.getAttendedCountsByProgramOrCampOrBatch(programId,campId,batchId,dates[0].trim(),dates[1].trim());
    		
    	}catch(Exception e){
    		LOG.error("Exception Occured in getAttendedCountsByProgramOrCampOrBatch() method, Exception - ",e);
    	}
    	
    	return Action.SUCCESS;
    }
    
    public String getAttendedCountSummaryByBatch()
    {
    	try{
    		
    		jObj = new JSONObject(getTask());
    		Long batchId = jObj.getLong("batchId");
    		String dates[] = jObj.getString("dates").split("-");
    		simpleVO = trainingCampService.getAttendedCountSummaryByBatch(batchId,dates[0].trim(),dates[1].trim());
    		
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
    		simpleVO = trainingCampService.getProgramSummary(programId,date[0].trim(),date[1].trim());
    		
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
    		simpleVO = trainingCampService.getCampSummary(programId,campId,date[0].trim(),date[1].trim());
    		
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
    		cadreVoList = trainingCampService.getDateWiseAttendedAndAbsentCandidates(batchId);
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
    	memberList = trainingCampService.getMaxNumberForBatch(batchId);
    			
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
    
}
