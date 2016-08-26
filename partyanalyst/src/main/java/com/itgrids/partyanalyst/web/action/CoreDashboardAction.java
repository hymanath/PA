package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CommitteeBasicVO;
import com.itgrids.partyanalyst.dto.CommitteeDataVO;
import com.itgrids.partyanalyst.dto.CommitteeVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.UserDataVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;
import com.itgrids.partyanalyst.service.ICoreDashboardGenericService;
import com.itgrids.partyanalyst.service.ICoreDashboardMainService;
import com.itgrids.partyanalyst.service.ICoreDashboardService;
import com.itgrids.partyanalyst.service.ICoreDashboardService1;
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
	private List<CommitteeDataVO> CommitteeDataVOList;
	private CommitteeDataVO committeeDataVO;
	private List<List<UserTypeVO>> userTypeVOList;
	private List<UserTypeVO> activityMembersList;
	
	//Attributes
	private ICoreDashboardService coreDashboardService;
	private ICoreDashboardService1 coreDashboardService1;
	private ICoreDashboardMainService coreDashboardMainService;
	private ICoreDashboardGenericService coreDashboardGenericService;
	
	//setters And Getters
	public void setCoreDashboardService(ICoreDashboardService coreDashboardService) {
		this.coreDashboardService = coreDashboardService;
	}
	
	public void setCoreDashboardService1(ICoreDashboardService1 coreDashboardService1) {
		this.coreDashboardService1 = coreDashboardService1;
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
		return CommitteeDataVOList;
	}

	public void setCommitteeDataVOList(List<CommitteeDataVO> committeeDataVOList) {
		CommitteeDataVOList = committeeDataVOList;
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

	//Implementation method
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
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
			
			CommitteeDataVOList = coreDashboardService1.getDistrictWiseCommitteesCountReport(state,basicCommitteeIds,startDateString,endDateString);
			
		}catch(Exception e){
			LOG.error("Exception raised at getDistrictWiseCommitteesCountReport() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
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
			
			List<Long> basicCommitteeIds = new ArrayList<Long>();
			JSONArray basicCommitteeIdsArray=jObj.getJSONArray("basicCommitteeIdsArray");
			if(basicCommitteeIdsArray!=null &&  basicCommitteeIdsArray.length()>0){
				for( int i=0;i<basicCommitteeIdsArray.length();i++){
					basicCommitteeIds.add(Long.valueOf(basicCommitteeIdsArray.getString(i)));
				}
			}
			
			String startDateString = jObj.getString("startDateString");
			String endDateString = jObj.getString("endDateString");
			
			committeeDataVO = coreDashboardService1.getCommitteesBasicCountReport(userAccessLevelId,userAccessLevelValues,state,basicCommitteeIds,startDateString,endDateString);
			
		}catch(Exception e){
			LOG.error("Exception raised at getCommitteesBasicCountReport() method of CoreDashBoard", e);
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
			
			List<Long> basicCommitteeIds = new ArrayList<Long>();
			JSONArray basicCommitteeIdsArray=jObj.getJSONArray("basicCommitteeIdsArray");
			if(basicCommitteeIdsArray!=null &&  basicCommitteeIdsArray.length()>0){
				for( int i=0;i<basicCommitteeIdsArray.length();i++){
					basicCommitteeIds.add(Long.valueOf(basicCommitteeIdsArray.getString(i)));
				}
			}
			
			String startDateString = jObj.getString("startDateString");
			String endDateString = jObj.getString("endDateString");
			
			CommitteeDataVOList = coreDashboardService1.getLevelWiseBasicCommitteesCountReport(userAccessLevelId,userAccessLevelValues,state,basicCommitteeIds,startDateString,endDateString);
			
		}catch(Exception e){
			LOG.error("Exception raised at getLevelWiseBasicCommitteesCountReport() method of CoreDashBoard", e);
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
			
			userTypeVOList = coreDashboardService1.getUserTypeWiseCommitteesCompletedCounts(userId,activityMemberId,userTypeId,state,basicCommitteeIds,startDateString,endDateString);
			
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
			
			List<Long> basicCommitteeIds = new ArrayList<Long>();
			JSONArray basicCommitteeIdsArray=jObj.getJSONArray("basicCommitteeIdsArray");
			if(basicCommitteeIdsArray!=null &&  basicCommitteeIdsArray.length()>0){
				for( int i=0;i<basicCommitteeIdsArray.length();i++){
					basicCommitteeIds.add(Long.valueOf(basicCommitteeIdsArray.getString(i)));
				}
			}
			
			String startDateString = jObj.getString("startDateString");
			String endDateString = jObj.getString("endDateString");
			
			userTypeVOList = coreDashboardMainService.getUserTypeWiseCommitteesCompletedCounts1(userId,activityMemberId,userTypeId,state,basicCommitteeIds,startDateString);
		}catch(Exception e){
			LOG.error("Exception raised at getUserTypeWiseCommitteesCompletedCounts1() method of CoreDashBoard", e);
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
			
			List<Long> basicCommitteeIds = new ArrayList<Long>();
			JSONArray basicCommitteeIdsArray=jObj.getJSONArray("basicCommitteeIdsArray");
			if(basicCommitteeIdsArray!=null &&  basicCommitteeIdsArray.length()>0){
				for( int i=0;i<basicCommitteeIdsArray.length();i++){
					basicCommitteeIds.add(Long.valueOf(basicCommitteeIdsArray.getString(i)));
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
			
			 List<String> groupingLocationsList =null;
			 JSONArray groupingLocationsListArray = jObj.getJSONArray("groupingLocationsListArray");
			  if(groupingLocationsListArray != null && groupingLocationsListArray.length() > 0){
				  groupingLocationsList = new ArrayList<String>(); 
					for (int i = 0; i < groupingLocationsListArray.length(); i++) {
						groupingLocationsList.add(groupingLocationsListArray.get(i).toString());
					}
			   }
			  
			String startDateString = jObj.getString("startDateString");
			String endDateString = jObj.getString("endDateString");
			String state = jObj.getString("state");
			
			CommitteeDataVOList = coreDashboardService1.committeesPerformanceCohort(tdpCommitteeLevelIdsClicked,basicCommitteeIds,committeeStatus,userLocationLevelId,userLocationLevelValues,groupingLocationsList,startDateString,endDateString,state);
			
		}catch(Exception e){
			LOG.error("Exception raised at committeesPerformanceCohort() method of CoreDashBoard", e);
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
			List<Long> basicCommitteeIds = new ArrayList<Long>();
			JSONArray basicCommitteeIdsArray=jObj.getJSONArray("basicCommitteeIdsArray");
			if(basicCommitteeIdsArray!=null &&  basicCommitteeIdsArray.length()>0){
				for( int i=0;i<basicCommitteeIdsArray.length();i++){
					basicCommitteeIds.add(Long.valueOf(basicCommitteeIdsArray.getString(i)));
				}
			}
			String dateString = jObj.getString("dateString");
			
			activityMembersList = coreDashboardMainService.getSelectedChildUserTypeMembers(parentActivityMemberId,childUserTypeId,state,basicCommitteeIds,dateString);
			
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
			List<Long> basicCommitteeIds = new ArrayList<Long>();
			JSONArray basicCommitteeIdsArray=jObj.getJSONArray("basicCommitteeIdsArray");
			if(basicCommitteeIdsArray!=null &&  basicCommitteeIdsArray.length()>0){
				for( int i=0;i<basicCommitteeIdsArray.length();i++){
					basicCommitteeIds.add(Long.valueOf(basicCommitteeIdsArray.getString(i)));
				}
			}
			String dateString = jObj.getString("dateString");
			
			activityMembersList = coreDashboardMainService.getDirectChildActivityMemberCommitteeDetails(activityMemberId,userTypeId,state,basicCommitteeIds,dateString);
			
		}catch(Exception e){
			LOG.error("Exception raised at getDirectChildActivityMemberCommitteeDetails() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}
}
