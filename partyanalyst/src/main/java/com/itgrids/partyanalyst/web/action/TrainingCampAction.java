package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.TraingCampCallerVO;
import com.itgrids.partyanalyst.dto.TraingCampDataVO;
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
	private List<IdNameVO> idNameList;
	private ResultStatus resultStatus;
	private TrainingCampVO trainingCampVO;
	

	
	public TrainingCampVO getTrainingCampVO() {
		return trainingCampVO;
	}

	public void setTrainingCampVO(TrainingCampVO trainingCampVO) {
		this.trainingCampVO = trainingCampVO;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public List<IdNameVO> getIdNameList() {
		return idNameList;
	}

	public void setIdNameList(List<IdNameVO> idNameList) {
		this.idNameList = idNameList;
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
		}
	catch(Exception e){
	e.printStackTrace();
	}

	return Action.SUCCESS;
	}
	public String getAllCamps()
	{
		try{
			jObj=new JSONObject(getTask());
		}
	catch(Exception e){
	e.printStackTrace();
	}

	return Action.SUCCESS;
	}
	public String getAllschedules()
	{
		try{
			jObj=new JSONObject(getTask());
		}
	catch(Exception e){
	e.printStackTrace();
	}

	return Action.SUCCESS;
	}
	public String getAllBatches()
	{
		try{
			jObj=new JSONObject(getTask());
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
			
			resultStatus = trainingCampService.assignMembersToCallerForMemberConfirmation(regVo.getRegistrationID(),scheduleId,membersCount,callerId,callPurposeId);
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
	
}
