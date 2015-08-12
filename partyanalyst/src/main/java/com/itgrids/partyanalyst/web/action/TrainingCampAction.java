package com.itgrids.partyanalyst.web.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.CallStatusVO;
import com.itgrids.partyanalyst.dto.CallBackCountVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.TraingCampCallerVO;
import com.itgrids.partyanalyst.dto.TraingCampDataVO;
import com.itgrids.partyanalyst.dto.TrainingCadreVO;
import com.itgrids.partyanalyst.dto.TrainingCampCallStatusVO;
import com.itgrids.partyanalyst.dto.TrainingCampScheduleVO;
import com.itgrids.partyanalyst.dto.TrainingCampVO;
import com.itgrids.partyanalyst.dto.TrainingMemberVO;
import com.itgrids.partyanalyst.service.ITrainingCampService;
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

	public String execute(){
		return Action.SUCCESS;
	}
	public String callCenterTrainingAdmin()
	{
		try
		{
			
		}catch (Exception e) {
			LOG.error(" Exception occured in callCenterTrainingAdmin method in TrainingCampAction class.",e);
		}
		return Action.SUCCESS;
	}
	public String callCenterTrainingAgent(){
		return Action.SUCCESS;
	}
	public String getTrainingAdminDashboard(){
		return Action.SUCCESS;
	}
	public String getScheduleCallStatusCount()
	{
		try{
		RegistrationVO regVo =(RegistrationVO) request.getSession().getAttribute("USER");
		jObj = new JSONObject(getTask());
		statusCountList = trainingCampService.getScheduleCallStatusCount(regVo.getRegistrationID(),jObj.getLong("callPurposeId"));
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
			inputVo.setUserId(regVo.getRegistrationID());
			inputVo.setBatchId(jObj.getLong("batchId"));
			inputVo.setStatusType(jObj.getString("statusType"));
			memberList = trainingCampService.getScheduleCallMemberDetails(inputVo);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return Action.SUCCESS;	
	}
	public String getBatchCallStatusCount()
	{
		try{
			RegistrationVO regVo =(RegistrationVO) request.getSession().getAttribute("USER");
			jObj = new JSONObject(getTask());
			statusCountList = trainingCampService.getBatchCallStatusCount(regVo.getRegistrationID(),jObj.getLong("callPurposeId"));
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return Action.SUCCESS;
	}
	public String getCallerWiseCallsDetails(){
		
		try{
			jObj=new JSONObject(getTask());
			
			String searchType=jObj.getString("searchType");
			String fromDate=jObj.getString("fromdate");
			String toDate=jObj.getString("toDate");
			
			List<Long> userIds=trainingCampService.getTrainingCampUserTypeIds(); 
			
			if(userIds !=null){
				trainingCampScheduleVO = trainingCampService.getCallerWiseCallsDetails(userIds, searchType, fromDate, toDate);
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
			
			List<Long> userIds=trainingCampService.getTrainingCampUserTypeIds(); 
			
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
			Boolean isOwnMembers = jObj.getBoolean("availCalls");
			if(callPurposeId == 1){
				resultStatus = trainingCampService.assignMembersToCallerForMemberConfirmation(regVo.getRegistrationID(),scheduleId,membersCount,callerId,callPurposeId);
			}
			else if(callPurposeId == 2){
				resultStatus = trainingCampService.assignMembersToCallerForBatchConfirmation(regVo.getRegistrationID(),isOwnMembers,scheduleId,membersCount,callerId,callPurposeId);
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
			inputVo.setUserId(regVo.getRegistrationID());
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
			if(regVo.getRegistrationID()!=null){
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
			retResult = trainingCampService.getMeetingTypes();
		}catch (Exception e) {
			LOG.error("Exception raised into getMeetingTypes",e);
		}
		return Action.SUCCESS;
	}
	
	public String getAllMeetings(){
		try {
			LOG.info("entered into getAllMeetings");
			jObj = new JSONObject(getTask());
			
			Long meetingType = jObj.getLong("meetingType");
			Long locationLevel = jObj.getLong("locationLevel");
			Long meetingLocation = jObj.getLong("meetingLocation");
			trainingCampService.getAllMeetings(meetingType,locationLevel,meetingLocation);
			
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
}
