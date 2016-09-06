package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CadreRegisterInfo;
import com.itgrids.partyanalyst.dto.CommitteeBasicVO;
import com.itgrids.partyanalyst.dto.CommitteeDataVO;
import com.itgrids.partyanalyst.dto.CommitteeVO;
import com.itgrids.partyanalyst.dto.CoreDebateVO;
import com.itgrids.partyanalyst.dto.DashboardCommentVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.TrainingCampProgramVO;
import com.itgrids.partyanalyst.dto.UserDataVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;
import com.itgrids.partyanalyst.service.ICoreDashboardGenericService;
import com.itgrids.partyanalyst.service.ICoreDashboardMainService;
import com.itgrids.partyanalyst.service.ICoreDashboardPartyMeetingService;
import com.itgrids.partyanalyst.service.ICoreDashboardService;
import com.itgrids.partyanalyst.service.ICoreDashboardService1;
import com.itgrids.partyanalyst.service.INewsCoreDashBoardService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CoreDashboardAction extends ActionSupport implements ServletRequestAware{
    
	private final static Logger LOG = Logger.getLogger(CoreDashboardAction.class);
	
	//Instance variables
	private HttpServletRequest request;
	private String task;
	private JSONObject jObj;
	private List<UserDataVO> userDataVOList;
	private UserDataVO userDataVO;
	private List<CommitteeBasicVO> committeeBasicVO;
	private List<CommitteeVO> committeeVOList;
	private CommitteeVO committeeVO ;
	private UserTypeVO userTypeVO;
	private List<CommitteeDataVO> committeeDataVOList;
	private CommitteeDataVO committeeDataVO;
	private List<List<UserTypeVO>> userTypeVOList;
	private TrainingCampProgramVO trainingCampProgramVO;
	private List<TrainingCampProgramVO> trainingCampProgramVOList;
	private List<UserTypeVO> activityMembersList;
	//Attributes
	private ICoreDashboardService coreDashboardService;
	private ICoreDashboardService1 coreDashboardService1;
	private ICoreDashboardMainService coreDashboardMainService;
	private ICoreDashboardGenericService coreDashboardGenericService;
	
	private List<CoreDebateVO> codeDebateVoList;
	private INewsCoreDashBoardService newsCoreDashBoardService;
    private ICoreDashboardPartyMeetingService coreDashboardPartyMeetingService;
	private ResultStatus 						resultStatus;
	private List<DashboardCommentVO> 						dashboardCommentVo;
	private String status;
	
	//setters And Getters
	public void setCoreDashboardService(ICoreDashboardService coreDashboardService) {
		this.coreDashboardService = coreDashboardService;
	}
	
	public void setCoreDashboardService1(ICoreDashboardService1 coreDashboardService1) {
		this.coreDashboardService1 = coreDashboardService1;
	}
	public TrainingCampProgramVO getTrainingCampProgramVO() {
		return trainingCampProgramVO;
	}

	public void setTrainingCampProgramVO(TrainingCampProgramVO trainingCampProgramVO) {
		this.trainingCampProgramVO = trainingCampProgramVO;
	}
  public List<TrainingCampProgramVO> getTrainingCampProgramVOList() {
		return trainingCampProgramVOList;
	}

	public void setTrainingCampProgramVOList(
			List<TrainingCampProgramVO> trainingCampProgramVOList) {
		this.trainingCampProgramVOList = trainingCampProgramVOList;
	}
   public PartyMeetingsVO getPartyMeetingsVO() {
		return partyMeetingsVO;
	}

	public void setPartyMeetingsVO(PartyMeetingsVO partyMeetingsVO) {
		this.partyMeetingsVO = partyMeetingsVO;
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
	
	public List<UserDataVO> getUserDataVOList() {
		return userDataVOList;
	}

	public void setUserDataVOList(List<UserDataVO> userDataVOList) {
		this.userDataVOList = userDataVOList;
	}

	public UserDataVO getUserDataVO() {
		return userDataVO;
	}

	public void setUserDataVO(UserDataVO userDataVO) {
		this.userDataVO = userDataVO;
	}

	public List<CommitteeBasicVO> getCommitteeBasicVO() {
		return committeeBasicVO;
	}

	public void setCommitteeBasicVO(List<CommitteeBasicVO> committeeBasicVO) {
		this.committeeBasicVO = committeeBasicVO;
	}
	
	public List<CommitteeVO> getCommitteeVOList() {
		return committeeVOList;
	}

	public void setCommitteeVOList(List<CommitteeVO> committeeVOList) {
		this.committeeVOList = committeeVOList;
	}
	
	public CommitteeVO getCommitteeVO() {
		return committeeVO;
	}

	public void setCommitteeVO(CommitteeVO committeeVO) {
		this.committeeVO = committeeVO;
	}
	
	public UserTypeVO getUserTypeVO() {
		return userTypeVO;
	}

	public void setUserTypeVO(UserTypeVO userTypeVO) {
		this.userTypeVO = userTypeVO;
	}
	
	public List<CommitteeDataVO> getCommitteeDataVOList() {
		return committeeDataVOList;
	}

	public void setCommitteeDataVOList(List<CommitteeDataVO> committeeDataVOList) {
		this.committeeDataVOList = committeeDataVOList;
	}

	public CommitteeDataVO getCommitteeDataVO() {
		return committeeDataVO;
	}

	public void setCommitteeDataVO(CommitteeDataVO committeeDataVO) {
		this.committeeDataVO = committeeDataVO;
	}
	
	
	public List<List<UserTypeVO>> getUserTypeVOList() {
		return userTypeVOList;
	}

	public void setUserTypeVOList(List<List<UserTypeVO>> userTypeVOList) {
		this.userTypeVOList = userTypeVOList;
	}
	
	public void setCoreDashboardMainService(
			ICoreDashboardMainService coreDashboardMainService) {
		this.coreDashboardMainService = coreDashboardMainService;
	}
	

	public void setCoreDashboardGenericService(
			ICoreDashboardGenericService coreDashboardGenericService) {
		this.coreDashboardGenericService = coreDashboardGenericService;
	}
	
	public List<UserTypeVO> getActivityMembersList() {
		return activityMembersList;
	}

	public void setActivityMembersList(List<UserTypeVO> activityMembersList) {
		this.activityMembersList = activityMembersList;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	public List<DashboardCommentVO> getDashboardCommentVo() {
		return dashboardCommentVo;
	}

	public void setDashboardCommentVo(List<DashboardCommentVO> dashboardCommentVo) {
		this.dashboardCommentVo = dashboardCommentVo;
	}
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	//Implementation method
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public List<CoreDebateVO> getCodeDebateVoList() {
		return codeDebateVoList;
	}

	public void setCodeDebateVoList(List<CoreDebateVO> codeDebateVoList) {
		this.codeDebateVoList = codeDebateVoList;
	}
	
	public INewsCoreDashBoardService getNewsCoreDashBoardService() {
		return newsCoreDashBoardService;
	}

	public void setNewsCoreDashBoardService(
			INewsCoreDashBoardService newsCoreDashBoardService) {
		this.newsCoreDashBoardService = newsCoreDashBoardService;
	}
   public ICoreDashboardPartyMeetingService getCoreDashboardPartyMeetingService() {
		return coreDashboardPartyMeetingService;
	}

	public void setCoreDashboardPartyMeetingService(
			ICoreDashboardPartyMeetingService coreDashboardPartyMeetingService) {
		this.coreDashboardPartyMeetingService = coreDashboardPartyMeetingService;
	}

	//business methods
	public String execute(){
		try {
			
			final HttpSession session = request.getSession();
			
			final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null || user.getRegistrationID() == null){
				return ERROR;
			}
		}catch(Exception e) {
			LOG.error("Exception raised at execute() in CoreDashBoard Action class", e);
		}
		return Action.SUCCESS;
	}
	public String sessionCheckingForCoreDashBoard(){
		try {
			
			final HttpSession session = request.getSession();
			
			final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null || user.getRegistrationID() == null){
				return ERROR;
			}
			
			userDataVO = coreDashboardService.getUserBasicDetails(user.getRegistrationID());
			List<UserDataVO> committeeDataVOList = coreDashboardMainService.getbasicCommitteeDetails();
			if(committeeDataVOList!=null && committeeDataVOList.size()>0){
				userDataVO.setSubList(committeeDataVOList);
			}
			
		}catch(Exception e) {
			LOG.error("Exception raised at execute() in CoreDashBoard Action class", e);
		}
		return Action.SUCCESS;
	}
	public String getUserBasicDetails(){
		try{
			LOG.info("Entered into getUserBasicDetails()  of CoreDashboardAction");
			jObj = new JSONObject(getTask());
			userDataVO = coreDashboardService.getUserBasicDetails(jObj.getLong("userId"));
			
		}catch(Exception e){
			LOG.error("Exception raised at getUserBasicDetails() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}
	
	public String getCommitteesCumulativeBasicReportChart(){
		try{
			jObj = new JSONObject(getTask());
			
			Long userAccessLevelId = jObj.getLong("userAccessLevelId");
			List<Long> userAccessLevelValues=new ArrayList<Long>();
			JSONArray userAccessLevelValuesArray=jObj.getJSONArray("userAccessLevelValuesArray");
			if(userAccessLevelValuesArray!=null &&  userAccessLevelValuesArray.length()>0){
				for( int i=0;i<userAccessLevelValuesArray.length();i++){
					userAccessLevelValues.add(Long.valueOf(userAccessLevelValuesArray.getString(i)));
				}
			}
			String state = jObj.getString("state");
			Long basicCommitteeId = jObj.getLong("basicCommitteeId");
			String startDateString = jObj.getString("startDateString");
			String endDateString = jObj.getString("endDateString");
			
			committeeVO = coreDashboardService.getCommitteesCumulativeBasicReportChart(userAccessLevelId,userAccessLevelValues,state,basicCommitteeId,startDateString,endDateString);
		}catch(Exception e){
			LOG.error("Exception raised at getMainCommitteeCountDetails() method of coreDashboardAction", e);
		}
		
		return Action.SUCCESS;
	}
	
	public String getCommitteesCumulativeOverallReportCharts(){
		try{
			LOG.info("Entered into getCommitteesCumulaticeOverallReportCharts()  of CoreDashboardAction");
			jObj = new JSONObject(getTask());
			
			Long userAccessLevelId = jObj.getLong("userAccessLevelId");
			
			List<Long> userAccessLevelValues=new ArrayList<Long>();
			JSONArray userAccessLevelValuesArray=jObj.getJSONArray("userAccessLevelValuesArray");
			if(userAccessLevelValuesArray!=null &&  userAccessLevelValuesArray.length()>0){
				for( int i=0;i<userAccessLevelValuesArray.length();i++){
					userAccessLevelValues.add(Long.valueOf(userAccessLevelValuesArray.getString(i)));
				}
			}
			String state = jObj.getString("state");
			
			List<Long> basicCommitteeIds = new ArrayList<Long>();
			JSONArray basicCommitteeIdsArray=jObj.getJSONArray("basicCommitteeIdsArray");
			if(basicCommitteeIdsArray!=null &&  basicCommitteeIdsArray.length()>0){
				for( int i=0;i<basicCommitteeIdsArray.length();i++){
					basicCommitteeIds.add(Long.valueOf(basicCommitteeIdsArray.getString(i)));
				}
			}
			
			String startDateString = jObj.getString("startDateString");
			String endDateString = jObj.getString("endDateString");
			
			committeeVOList = coreDashboardService.getCommitteesCumulativeOverallReportCharts(userAccessLevelId,userAccessLevelValues,state,basicCommitteeIds,startDateString,endDateString);
			
		}catch(Exception e){
			LOG.error("Exception raised at getCommitteesCumulaticeOverallReportCharts() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}
	
	public String getCommitteesComparativeBascicReportChart(){
		try{
			LOG.info("Entered into getCommitteesComparativeBascicReportChart()  of CoreDashboardAction");
			jObj = new JSONObject(getTask());
			
			Long userAccessLevelId = jObj.getLong("userAccessLevelId");
			
			List<Long> userAccessLevelValues=new ArrayList<Long>();
			JSONArray userAccessLevelValuesArray=jObj.getJSONArray("userAccessLevelValuesArray");
			if(userAccessLevelValuesArray!=null &&  userAccessLevelValuesArray.length()>0){
				for( int i=0;i<userAccessLevelValuesArray.length();i++){
					userAccessLevelValues.add(Long.valueOf(userAccessLevelValuesArray.getString(i)));
				}
			}
			String state = jObj.getString("state");
			
			List<Long> basicCommitteeIds = new ArrayList<Long>();
			JSONArray basicCommitteeIdsArray=jObj.getJSONArray("basicCommitteeIdsArray");
			if(basicCommitteeIdsArray!=null &&  basicCommitteeIdsArray.length()>0){
				for( int i=0;i<basicCommitteeIdsArray.length();i++){
					basicCommitteeIds.add(Long.valueOf(basicCommitteeIdsArray.getString(i)));
				}
			}
			
			String firstMonthString = jObj.getString("firstMonthString");
			String secondMonthString = jObj.getString("secondMonthString");
			
			committeeVOList = coreDashboardService.getCommitteesComparativeBascicReportChart(userAccessLevelId,userAccessLevelValues,state,basicCommitteeIds,firstMonthString,secondMonthString);
			
		}catch(Exception e){
			LOG.error("Exception raised at getCommitteesComparativeBascicReportChart() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}
	
	public String getCommitteesComparativeOverallReportChart(){
		try{
			LOG.info("Entered into levelWiseComparativeCountsByBasicCommittees()  of CoreDashboardAction");
			jObj = new JSONObject(getTask());
			
			Long userAccessLevelId = jObj.getLong("userAccessLevelId");
			
			List<Long> userAccessLevelValues=new ArrayList<Long>();
			JSONArray userAccessLevelValuesArray=jObj.getJSONArray("userAccessLevelValuesArray");
			if(userAccessLevelValuesArray!=null &&  userAccessLevelValuesArray.length()>0){
				for( int i=0;i<userAccessLevelValuesArray.length();i++){
					userAccessLevelValues.add(Long.valueOf(userAccessLevelValuesArray.getString(i)));
				}
			}
			String state = jObj.getString("state");
			
			List<Long> basicCommitteeIds = new ArrayList<Long>();
			JSONArray basicCommitteeIdsArray=jObj.getJSONArray("basicCommitteeIdsArray");
			if(basicCommitteeIdsArray!=null &&  basicCommitteeIdsArray.length()>0){
				for( int i=0;i<basicCommitteeIdsArray.length();i++){
					basicCommitteeIds.add(Long.valueOf(basicCommitteeIdsArray.getString(i)));
				}
			}
			
			String firstMonthString = jObj.getString("firstMonthString");
			String secondMonthString = jObj.getString("secondMonthString");
			
			committeeVOList = coreDashboardService.getCommitteesComparativeOverallReportChart(userAccessLevelId,userAccessLevelValues,state,basicCommitteeIds,firstMonthString,secondMonthString);
			
		}catch(Exception e){
			LOG.error("Exception raised at getCommitteesComparativeOverallReportChart() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}
	
	public String getLoggedInUserStructure(){
		try{
			LOG.info("Entered into getLoggedInUserStructure()  of CoreDashboardAction");
			jObj = new JSONObject(getTask());
			userTypeVO = coreDashboardService.getLoggedInUserStructure(jObj.getLong("userId"));
			
		}catch(Exception e){
			LOG.error("Exception raised at getLoggedInUserStructure() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}
	
	public String getDistrictWiseCommitteesCountReport(){
		try{
			LOG.info("Entered into getDistrictWiseCommitteesCountReport()  of CoreDashboardAction");
			jObj = new JSONObject(getTask());
			String state = jObj.getString("state");
			
			List<Long> basicCommitteeIds = new ArrayList<Long>();
			JSONArray basicCommitteeIdsArray=jObj.getJSONArray("basicCommitteeIdsArray");
			if(basicCommitteeIdsArray!=null &&  basicCommitteeIdsArray.length()>0){
				for( int i=0;i<basicCommitteeIdsArray.length();i++){
					basicCommitteeIds.add(Long.valueOf(basicCommitteeIdsArray.getString(i)));
				}
			}
			
			String startDateString = jObj.getString("startDateString");
			String endDateString = jObj.getString("endDateString");
			
			committeeDataVOList = coreDashboardService1.getDistrictWiseCommitteesCountReport(state,basicCommitteeIds,startDateString,endDateString);
			
		}catch(Exception e){
			LOG.error("Exception raised at getDistrictWiseCommitteesCountReport() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}
	///////////////////COREDASHBOARD COMMITTEES////////////////////////////////////
	
	
	public Map<Long,List<Long>> getLevelWiseBasicCommittees(JSONObject jObj){
		
		Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap = new HashMap<Long, List<Long>>(0);
		try{
			
			JSONArray levelWiseBasicCommitteesArray = jObj.getJSONArray("levelWiseBasicCommitteesArray");
			
			if(levelWiseBasicCommitteesArray!=null &&  levelWiseBasicCommitteesArray.length()>0){
				
				for(int i=0;i<levelWiseBasicCommitteesArray.length();i++){
					
						JSONObject tdpCommitteeLevelObject= levelWiseBasicCommitteesArray.getJSONObject(i);
						
						JSONArray basicCommitteeIdsArray = tdpCommitteeLevelObject.getJSONArray("basicCommitteeIds");
						
						if(basicCommitteeIdsArray!=null && basicCommitteeIdsArray.length()>0){
							
							List<Long> committeeIds = new ArrayList<Long>();
							
							for(int j=0;j<basicCommitteeIdsArray.length();j++){
								
								committeeIds.add(Long.valueOf(basicCommitteeIdsArray.getString(j)));
							}
							committeeLevelBasedCommitteeIdsMap.put(tdpCommitteeLevelObject.getLong("committeeLevelId"), committeeIds);
						}
				}
			}
		}catch(Exception e){
			LOG.error("Exception raised at getLevelWiseBasicCommittees() method of CoreDashBoard", e);
		}
		return committeeLevelBasedCommitteeIdsMap;
	}
	public String getCommitteesBasicCountReport(){
		
		try{
			LOG.info("Entered into getCommitteesBasicCountReport()  of CoreDashboardAction");
			jObj = new JSONObject(getTask());
			Long userAccessLevelId = jObj.getLong("userAccessLevelId");
			
			List<Long> userAccessLevelValues=new ArrayList<Long>();
			JSONArray userAccessLevelValuesArray=jObj.getJSONArray("userAccessLevelValuesArray");
			if(userAccessLevelValuesArray!=null &&  userAccessLevelValuesArray.length()>0){
				for( int i=0;i<userAccessLevelValuesArray.length();i++){
					userAccessLevelValues.add(Long.valueOf(userAccessLevelValuesArray.getString(i)));
				}
			}
			String state = jObj.getString("state");
			
			Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap = getLevelWiseBasicCommittees(jObj);
			
			String dateString = jObj.getString("dateString");
			
			
			committeeDataVO = coreDashboardMainService.getCommitteesBasicCountReport(userAccessLevelId,userAccessLevelValues,state,committeeLevelBasedCommitteeIdsMap,dateString);
			
		}catch(Exception e){
			LOG.error("Exception raised at getCommitteesBasicCountReport() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}
	public String getUserTypeWiseCommitteesCompletedCounts(){
		
		try{
			LOG.info("Entered into getUserTypeWiseCommitteesCompletedCounts()  of CoreDashboardAction");
			jObj = new JSONObject(getTask());
			
			Long userId = jObj.getLong("userId");
			Long activityMemberId = jObj.getLong("activityMemberId");
			Long userTypeId = jObj.getLong("userTypeId");
			
			String state = jObj.getString("state");
			
			List<Long> basicCommitteeIds = new ArrayList<Long>();
			JSONArray basicCommitteeIdsArray=jObj.getJSONArray("basicCommitteeIdsArray");
			if(basicCommitteeIdsArray!=null &&  basicCommitteeIdsArray.length()>0){
				for( int i=0;i<basicCommitteeIdsArray.length();i++){
					basicCommitteeIds.add(Long.valueOf(basicCommitteeIdsArray.getString(i)));
				}
			}
			
			String startDateString = jObj.getString("startDateString");
			String endDateString = jObj.getString("endDateString");
			
			//userTypeVOList = coreDashboardService1.getUserTypeWiseCommitteesCompletedCounts(userId,activityMemberId,userTypeId,state,basicCommitteeIds,startDateString,endDateString);
			
		}catch(Exception e){
			LOG.error("Exception raised at getUserTypeWiseCommitteesCompletedCounts() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}

	public String getUserTypeWiseCommitteesCompletedCounts1(){
		LOG.info("Entered into getUserTypeWiseCommitteesCompletedCounts1()  of CoreDashboardAction");
		try{
			
			 HttpSession session = request.getSession();
			 RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			 if(user == null || user.getRegistrationID() == null){
				return ERROR;
			 }
			
			jObj = new JSONObject(getTask());
			
			Long userId = user.getRegistrationID();
			
			Long activityMemberId = jObj.getLong("activityMemberId");
			Long userTypeId = jObj.getLong("userTypeId");
			
			String state = jObj.getString("state");
			
			Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap = getLevelWiseBasicCommittees(jObj);
	
			String dateString = jObj.getString("dateString");
			
			
			userTypeVOList = coreDashboardMainService.getUserTypeWiseCommitteesCompletedCounts1(userId,activityMemberId,userTypeId,state,committeeLevelBasedCommitteeIdsMap,dateString);
		}catch(Exception e){
			LOG.error("Exception raised at getUserTypeWiseCommitteesCompletedCounts1() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}
	public String getLevelWiseBasicCommitteesCountReport(){
		
		try{
			LOG.info("Entered into getLevelWiseBasicCommitteesCountReport()  of CoreDashboardAction");
			jObj = new JSONObject(getTask());
			Long userAccessLevelId = jObj.getLong("userAccessLevelId");
			
			List<Long> userAccessLevelValues=new ArrayList<Long>();
			JSONArray userAccessLevelValuesArray=jObj.getJSONArray("userAccessLevelValuesArray");
			if(userAccessLevelValuesArray!=null &&  userAccessLevelValuesArray.length()>0){
				for( int i=0;i<userAccessLevelValuesArray.length();i++){
					userAccessLevelValues.add(Long.valueOf(userAccessLevelValuesArray.getString(i)));
				}
			}
			String state = jObj.getString("state");
			
			Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap = getLevelWiseBasicCommittees(jObj);
			
			String dateString = jObj.getString("dateString");
			
			committeeDataVOList = coreDashboardMainService.getLevelWiseBasicCommitteesCountReport(userAccessLevelId,userAccessLevelValues,state,committeeLevelBasedCommitteeIdsMap,dateString);
			
		}catch(Exception e){
			LOG.error("Exception raised at getLevelWiseBasicCommitteesCountReport() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}
	public String committeesPerformanceCohort(){
		try{
			LOG.info("Entered into committeesPerformanceCohort()  of CoreDashboardAction");
			jObj = new JSONObject(getTask());
			
			
			List<Long> tdpCommitteeLevelIdsClicked = new ArrayList<Long>();
			JSONArray tdpCommitteeLevelIdsClickedArray=jObj.getJSONArray("tdpCommitteeLevelIdsClickedArray");
			if(tdpCommitteeLevelIdsClickedArray!=null &&  tdpCommitteeLevelIdsClickedArray.length()>0){
				for( int i=0;i<tdpCommitteeLevelIdsClickedArray.length();i++){
					tdpCommitteeLevelIdsClicked.add(Long.valueOf(tdpCommitteeLevelIdsClickedArray.getString(i)));
				}
			}
			
			
			String committeeStatus = jObj.getString("committeeStatus");
			Long userLocationLevelId = jObj.getLong("userLocationLevelId");
			
			List<Long> userLocationLevelValues = new ArrayList<Long>();
			JSONArray userLocationLevelValuesArray=jObj.getJSONArray("userLocationLevelValuesArray");
			if(userLocationLevelValuesArray!=null &&  userLocationLevelValuesArray.length()>0){
				for( int i=0;i<userLocationLevelValuesArray.length();i++){
					userLocationLevelValues.add(Long.valueOf(userLocationLevelValuesArray.getString(i)));
				}
			}
			  
			String dateString = jObj.getString("dateString");
			String state = jObj.getString("state");
			Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap = getLevelWiseBasicCommittees(jObj);
			
			committeeDataVOList = coreDashboardMainService.committeesPerformanceCohort(tdpCommitteeLevelIdsClicked,committeeLevelBasedCommitteeIdsMap,committeeStatus,userLocationLevelId,userLocationLevelValues,dateString,state);
			
		}catch(Exception e){
			LOG.error("Exception raised at committeesPerformanceCohort() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	
	}
	public String getChildUserTypesByItsParentUserType(){
		try{
			
			jObj = new JSONObject(getTask());
			userDataVOList = coreDashboardGenericService.getChildUserTypesByItsParentUserType(jObj.getLong("parentUserTypeId"));
			
		}catch(Exception e){
			LOG.error("Exception raised at getChildUserTypesByItsParentUserType() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}
	
	
	
	public String getSelectedChildUserTypeMembers(){
		LOG.info("Entered into getSelectedChildUserTypeMembers()  of CoreDashboardAction");
		try{
			
			jObj = new JSONObject(getTask());
			
			Long parentActivityMemberId = jObj.getLong("parentActivityMemberId");
			Long childUserTypeId = jObj.getLong("childUserTypeId");
			
			String state = jObj.getString("state");
			
			String dateString = jObj.getString("dateString");
			
			Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap = getLevelWiseBasicCommittees(jObj);
			
			activityMembersList = coreDashboardMainService.getSelectedChildUserTypeMembers(parentActivityMemberId,childUserTypeId,state,committeeLevelBasedCommitteeIdsMap,dateString);
			
		}catch(Exception e){
			LOG.error("Exception raised at getUserTypeWiseCommitteesCompletedCounts1() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}
	public String getDirectChildActivityMemberCommitteeDetails(){
		LOG.info("Entered into getDirectChildActivityMemberCommitteeDetails()  of CoreDashboardAction");
		try{
			
			jObj = new JSONObject(getTask());
			
			Long activityMemberId = jObj.getLong("activityMemberId");
			Long userTypeId = jObj.getLong("userTypeId");
			
			String state = jObj.getString("state");
			
			String dateString = jObj.getString("dateString");
			
			Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap = getLevelWiseBasicCommittees(jObj);
			
			activityMembersList = coreDashboardMainService.getDirectChildActivityMemberCommitteeDetails(activityMemberId,userTypeId,state,committeeLevelBasedCommitteeIdsMap,dateString);
			
		}catch(Exception e){
			LOG.error("Exception raised at getDirectChildActivityMemberCommitteeDetails() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}
public String getTopPoorPerformancecommittees(){
		LOG.info("Entered into getTopPoorPerformancecommittees()  of CoreDashboardAction");
		try{
			
			jObj = new JSONObject(getTask());
			
			Long activityMemberId = jObj.getLong("activityMemberId");
			
			String state = jObj.getString("state");
			String dateString = jObj.getString("dateString");
			Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap = getLevelWiseBasicCommittees(jObj);
			
			committeeDataVO = coreDashboardMainService.getTopPoorPerformancecommittees(activityMemberId,committeeLevelBasedCommitteeIdsMap,state,dateString);
			
		}catch(Exception e){
			LOG.error("Exception raised at getTopPoorPerformancecommittees() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
}	
	public String getTopPoorCommitteeLocations(){
		LOG.info("Entered into getTopPoorCommitteeLocations()  of CoreDashboardAction");
		try{
			
			jObj = new JSONObject(getTask());
			
			Long activityMemberId = jObj.getLong("activityMemberId");
			
			Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap = getLevelWiseBasicCommittees(jObj);
			
			String state = jObj.getString("state");
			String dateString = jObj.getString("dateString");
			
			committeeDataVOList = coreDashboardMainService.getTopPoorCommitteeLocations(activityMemberId,committeeLevelBasedCommitteeIdsMap,state,dateString);
			
		}catch(Exception e){
			LOG.error("Exception raised at getTopPoorCommitteeLocations() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}
  public String getTrainingCampBasicDetailsCntOverview(){
	  try{
			LOG.info("Entered into getTotalEligibleAttendedAndNotAttenedOverviewCount()  of CoreDashboardAction");
			jObj = new JSONObject(getTask());
			Long userAccessLevelId = jObj.getLong("userAccessLevelId");
			
			List<Long> userAccessLevelValues=new ArrayList<Long>();
			JSONArray userAccessLevelValuesArray=jObj.getJSONArray("userAccessLevelValuesArray");
			if(userAccessLevelValuesArray!=null &&  userAccessLevelValuesArray.length()>0){
				for( int i=0;i<userAccessLevelValuesArray.length();i++){
					userAccessLevelValues.add(Long.valueOf(userAccessLevelValuesArray.getString(i)));
				}
			}
			Long stateId = jObj.getLong("stateId");
			String dateStr = jObj.getString("dateStr");
			trainingCampProgramVO = coreDashboardMainService.getTrainingCampBasicDetailsCntOverview(userAccessLevelId,userAccessLevelValues,stateId,dateStr);
			
		}catch(Exception e){
			LOG.error("Exception raised at getTotalEligibleAttendedAndNotAttenedOverviewCount() method of CoreDashBoardAction", e);
		}
		return Action.SUCCESS;
  }
  public String getTrainingCampProgramsDetailsCntByDistrict(){
	  try{
			LOG.info("Entered into getTotalEligibleAttendedAndNotAttenedOverviewCount()  of CoreDashboardAction");
			jObj = new JSONObject(getTask());
			Long userAccessLevelId = jObj.getLong("userAccessLevelId");
			
			List<Long> userAccessLevelValues=new ArrayList<Long>();
			JSONArray userAccessLevelValuesArray=jObj.getJSONArray("userAccessLevelValuesArray");
			if(userAccessLevelValuesArray!=null &&  userAccessLevelValuesArray.length()>0){
				for( int i=0;i<userAccessLevelValuesArray.length();i++){
					userAccessLevelValues.add(Long.valueOf(userAccessLevelValuesArray.getString(i)));
				}
			}
			Long stateId = jObj.getLong("stateId");
			String dateStr = jObj.getString("dateStr");
			trainingCampProgramVOList = coreDashboardMainService.getTrainingCampProgramsDetailsCntByDistrict(userAccessLevelId,userAccessLevelValues,stateId,dateStr);
			
		}catch(Exception e){
			LOG.error("Exception raised at getTotalEligibleAttendedAndNotAttenedOverviewCount() method of CoreDashBoardAction", e);
		}
		return Action.SUCCESS;
  }
 public String getUserTypeWiseTotalEligibleAndAttendedCnt(){
	 
	 try{
		 
		 jObj = new JSONObject(getTask());
		 
		   final HttpSession session = request.getSession();
			
			final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null || user.getRegistrationID() == null){
				return ERROR;
			}
		  //  Long userId = jObj.getLong("userId");
			Long activityMemberId = jObj.getLong("activityMemberId");
			Long userTypeId = jObj.getLong("userTypeId");
		    Long userAccessLevelId = jObj.getLong("userAccessLevelId");
			List<Long> userAccessLevelValues=new ArrayList<Long>();
			JSONArray userAccessLevelValuesArray=jObj.getJSONArray("userAccessLevelValuesArray");
			if(userAccessLevelValuesArray!=null &&  userAccessLevelValuesArray.length()>0){
				for( int i=0;i<userAccessLevelValuesArray.length();i++){
					userAccessLevelValues.add(Long.valueOf(userAccessLevelValuesArray.getString(i)));
				}
			}
			Long stateId = jObj.getLong("stateId");
			String dateStr = jObj.getString("dateStr");
			userTypeVOList = coreDashboardMainService.getUserTypeWiseTotalEligibleAndAttendedCnt(user.getRegistrationID(),userTypeId,activityMemberId,userAccessLevelId,userAccessLevelValues,stateId,dateStr);
	 }catch(Exception e){
		 LOG.error("Exception raised at getUserTypeWiseTotalEligibleAndAttendedCnt() method of CoreDashBoardAction", e); 
	 }
	 return Action.SUCCESS;
 }
public String getSelectedChildTypeMembersForTrainingProgram(){
	try{
		 
		    jObj = new JSONObject(getTask());
		 
		 	Long parentActivityMemberId = jObj.getLong("parentActivityMemberId");
			Long childUserTypeId = jObj.getLong("childUserTypeId");
		    Long userAccessLevelId = jObj.getLong("userAccessLevelId");
		    String reportType = jObj.getString("reportType");
			List<Long> userAccessLevelValues=new ArrayList<Long>();
			JSONArray userAccessLevelValuesArray=jObj.getJSONArray("userAccessLevelValuesArray");
			if(userAccessLevelValuesArray!=null &&  userAccessLevelValuesArray.length()>0){
				for( int i=0;i<userAccessLevelValuesArray.length();i++){
					userAccessLevelValues.add(Long.valueOf(userAccessLevelValuesArray.getString(i)));
				}
			}
			Long stateId = jObj.getLong("stateId");
			String dateStr = jObj.getString("dateStr");
			activityMembersList = coreDashboardMainService.getSelectedChildTypeMembersForTrainingProgram(parentActivityMemberId,childUserTypeId,userAccessLevelId,userAccessLevelValues,reportType,stateId,dateStr);
	 }catch(Exception e){
		 LOG.error("Exception raised at getSelectedChildTypeMembersForTrainingProgram() method of CoreDashBoardAction", e); 
	 }
	 return Action.SUCCESS;
}
public String getDirectChildActivityTrainingProgramMemberDetails(){
	try{
		 
		    jObj = new JSONObject(getTask());
		 
		 	Long activityMemberId = jObj.getLong("activityMemberId");
			Long userTypeId = jObj.getLong("userTypeId");
			 String reportType = jObj.getString("reportType");
			 Long stateId = jObj.getLong("stateId");
			 String dateStr = jObj.getString("dateStr");
		 	activityMembersList = coreDashboardMainService.getSelectedChildTypeMembersForTrainingProgram(activityMemberId,userTypeId,null,null,reportType,stateId,dateStr);
	 }catch(Exception e){
		 LOG.error("Exception raised at getSelectedChildTypeMembersForTrainingProgram() method of CoreDashBoardAction", e); 
	 }
	 return Action.SUCCESS;
}
public String getTrainingProgramPoorCompletedLocationDtls(){
	  try{
			LOG.info("Entered into getTrainingProgramPoorCompletedLocationDtls()  of CoreDashboardAction");
			jObj = new JSONObject(getTask());
			Long userTypeId = jObj.getLong("userTypeId");
			Long activityMemberId = jObj.getLong("activityMemberId");
			 Long stateId = jObj.getLong("stateId");
			 String dateStr = jObj.getString("dateStr");
			trainingCampProgramVO = coreDashboardMainService.getTrainingProgramPoorCompletedLocationDtls(userTypeId,activityMemberId,stateId,dateStr);
		}catch(Exception e){
			LOG.error("Exception raised at getTrainingProgramPoorCompletedLocationDtls() method of CoreDashBoardAction", e);
		}
		return Action.SUCCESS;
}
public String getTrainingProgramBasicCnt(){
	
	try{
		 
	    jObj = new JSONObject(getTask());
	 
	     Long userAccessLevelId = jObj.getLong("userAccessLevelId");
		List<Long> userAccessLevelValues=new ArrayList<Long>();
		JSONArray userAccessLevelValuesArray=jObj.getJSONArray("userAccessLevelValuesArray");
		if(userAccessLevelValuesArray!=null &&  userAccessLevelValuesArray.length()>0){
			for( int i=0;i<userAccessLevelValuesArray.length();i++){
				userAccessLevelValues.add(Long.valueOf(userAccessLevelValuesArray.getString(i)));
			}
		}
		 Long stateId = jObj.getLong("stateId");
		 String dateStr = jObj.getString("dateStr");
		trainingCampProgramVOList = coreDashboardMainService.getTrainingCampProgramsBasicCountDetails(userAccessLevelId,userAccessLevelValues,stateId,dateStr);
 }catch(Exception e){
	 LOG.error("Exception raised at getTrainingProgramBasicCnt() method of CoreDashBoardAction", e); 
 }
 return Action.SUCCESS;
}
//Debate Action Methods

public String getPartyWiseTotalDebateDetails(){
	
	try{
		jObj = new JSONObject(getTask());
		codeDebateVoList = coreDashboardMainService.getPartyWiseTotalDebateDetails(jObj.getString("startDate"),jObj.getString("endDate"),jObj.getString("state"));
		
	}catch (Exception e) {
		LOG.error("Exception raised at getPartyWiseTotalDebateDetails() method of CoreDashBoardAction", e);
	}
	
	return Action.SUCCESS;
}
public String getSpokesPersonWiseDebate(){
	
	try{
		
		jObj = new JSONObject(getTask());
		codeDebateVoList = coreDashboardMainService.getSpokesPersonWiseDebate(jObj.getString("startDate"),jObj.getString("endDate"),jObj.getString("searchType"),jObj.getString("state"));
		
	}catch (Exception e) {
		LOG.error("Exception raised at getSpokesPersonWiseDebate() method of CoreDashBoardAction", e);
	}
	
	return Action.SUCCESS;
}
public String getScaleBasedPerformanceCohort(){

	try{
		
		jObj = new JSONObject(getTask());
		codeDebateVoList = coreDashboardMainService.getScaleBasedPerformanceCohort(jObj.getString("startDate"),jObj.getString("endDate"),jObj.getString("state"));
		
	}catch (Exception e) {
		LOG.error("Exception raised at getScaleBasedPerformanceCohort() method of CoreDashBoardAction", e);
	}
	return Action.SUCCESS;
}
public String getCandidateOverAllPerformanceCohort(){
	
	try{
		
		jObj = new JSONObject(getTask());
		codeDebateVoList = coreDashboardMainService.getCandidateOverAllPerformanceCohort(jObj.getString("startDate"),jObj.getString("endDate"),jObj.getString("state"));
		
	}catch (Exception e) {
		LOG.error("Exception raised at getCandidateOverAllPerformanceCohort() method of CoreDashBoardAction", e);
	}
	
	return Action.SUCCESS;
}
public String getChannelAndPartyWiseDetails(){

	try{
		
		jObj = new JSONObject(getTask());
		codeDebateVoList = coreDashboardMainService.getChannelAndPartyWiseDetails(jObj.getString("startDate"),jObj.getString("endDate"),jObj.getString("state"));
		
	}catch (Exception e) {
		LOG.error("Exception raised at getChannelAndPartyWiseDetails() method of CoreDashBoardAction", e);
	}
	return Action.SUCCESS;
}
public String getRoleBasedPerformanceCohort(){
	
	try{
		
		jObj = new JSONObject(getTask());
		codeDebateVoList = coreDashboardMainService.getRoleBasedPerformanceCohort(jObj.getString("startDate"),jObj.getString("endDate"),jObj.getString("state"));
				
	}catch (Exception e) {
		LOG.error("Exception raised at getRoleBasedPerformanceCohort() method of CoreDashBoardAction", e);
	}
	
	return Action.SUCCESS;
}

	
	public String getUserTypeWiseNewsCounts(){
		try {
			
			HttpSession session = request.getSession();
			 RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			 if(user == null || user.getRegistrationID() == null){
				return ERROR;
			 }
			
			jObj = new JSONObject(getTask());
			
			Long userId = user.getRegistrationID();
			
			newsCoreDashBoardService.getUserTypeWiseNewsCounts(userId,jObj.getLong("activityMemberId"),jObj.getLong("userTypeId"),
					jObj.getString("state"),jObj.getString("fromDate"),jObj.getString("toDate"),jObj.getLong("benefitId"));
		} catch (Exception e) {
			LOG.error("Exception raised at getUserTypeWiseNewsCounts", e);
		}
		return Action.SUCCESS;
	}
	public String getPartyMeetingBasicCountDetails(){
		  try{
				LOG.info("Entered into getPartyMeetingBasicCountDetails()  of CoreDashboardAction");
				jObj = new JSONObject(getTask());
				Long activityMemberId = jObj.getLong("activityMemberId");
				Long stateId = jObj.getLong("stateId");
				String fromDate = jObj.getString("fromDate");
				String toDate = jObj.getString("toDate");
				partyMeetingsVO = coreDashboardPartyMeetingService.getPartyMeetingBasicCountDetails(activityMemberId,stateId,fromDate,toDate);
			}catch(Exception e){
				LOG.error("Exception raised at getPartyMeetingBasicCountDetails() method of CoreDashBoardAction", e);
			}
			return Action.SUCCESS;
	}
public String getUserTypeWiseMeetingCounductedNotCounductedMayBeDetailsCnt(){
	  try{
			LOG.info("Entered into getUserTypeWiseMeetingCounductedNotCounductedMayBeDetailsCnt()  of CoreDashboardAction");
			
			HttpSession session = request.getSession();
			 RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			 if(user == null || user.getRegistrationID() == null){
				return ERROR;
			 }
			
			jObj = new JSONObject(getTask());
			Long userTypeId = jObj.getLong("userTypeId");
			Long activityMemberId = jObj.getLong("activityMemberId");
			 Long stateId = jObj.getLong("stateId");
			 String fromDate = jObj.getString("fromDate");
			 String toDate = jObj.getString("toDate");
			 userTypeVOList = coreDashboardPartyMeetingService.getUserTypeWiseMeetingCounductedNotCounductedMayBeDetailsCnt(user.getRegistrationID(),userTypeId,activityMemberId,stateId,fromDate,toDate);
		}catch(Exception e){
			LOG.error("Exception raised at getUserTypeWiseMeetingCounductedNotCounductedMayBeDetailsCnt() method of CoreDashBoardAction", e);
		}
		return Action.SUCCESS; 
	  
}

public String savingDashboardComment()
{
	try
	{
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		if(regVO==null){
			return "input";
		}
		Long userId = regVO.getRegistrationID();
		jObj = new JSONObject(getTask());
		DashboardCommentVO Vo = new DashboardCommentVO();
	    Vo.setDashBoardCommentId(jObj.getLong("dashboardCommentId"));
	    Vo.setDashboardComponentId(jObj.getLong("dashboardComponentId"));
	    Vo.setComment(jObj.getString("comment"));
	    //Vo.setUserId(regVO.getUser); 
	    resultStatus = coreDashboardService1.savingDashboardComment(Vo,userId);
		
	}catch(Exception e)
	{
		LOG.error("Exception Occured in savechangeAddressForNominatedPost() in NominatedPostProfileAction ",e);
	}
	return Action.SUCCESS;
}

public String displayDashboardComments(){
	try{
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		if(regVO==null){
			return "input";
		}
		Long userId = regVO.getRegistrationID();
		jObj = new JSONObject(getTask());
		
		dashboardCommentVo = coreDashboardService1.displayDashboardComments(userId,jObj.getLong("dashBoardComponentId"));
		
	}catch (Exception e) {
		LOG.error("Entered into displayDashboardComments Action",e);
	}
	
	return Action.SUCCESS;
}
public String deleteDashBoardcomments()
{
	try{
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		if(regVO==null){
			return "input";
		}
		Long userId = regVO.getRegistrationID();
		jObj = new JSONObject(getTask());
		status = coreDashboardService1.deleteDashBoardcomments(jObj.getLong("dashboardCommentId"));
	}
	catch (Exception e) {
		e.printStackTrace();
		LOG.error("Exception rised in deleteDashBoardcomments",e);
	}
	return Action.SUCCESS;	
}

}
