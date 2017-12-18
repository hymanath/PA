package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.GrievanceReportVO;
import com.itgrids.partyanalyst.dto.ComplaintStatusCountVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.ICoreDashboardCoreService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CoreDashBoardGrivanceAction extends ActionSupport implements ServletRequestAware {
	private final static Logger LOG = Logger.getLogger(CoreDashBoardGrivanceAction.class);
	private HttpServletRequest request;
	private HttpSession session;
	
	private JSONObject 	jObj;
	private String 	task;
	
	private ICoreDashboardCoreService coreDashboardCoreService;
	private List<GrievanceReportVO> grievanceReportVO;
	private List<ComplaintStatusCountVO> complaintStatusCountVOs;
	

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}

	public void setComplaintStatusCountVOs(
			List<ComplaintStatusCountVO> complaintStatusCountVOs) {
		this.complaintStatusCountVOs = complaintStatusCountVOs;
	}
	
	
	public List<ComplaintStatusCountVO> getComplaintStatusCountVOs() {
		return complaintStatusCountVOs;
	}
	
	public void setCoreDashboardCoreService(
			ICoreDashboardCoreService coreDashboardCoreService) {
		this.coreDashboardCoreService = coreDashboardCoreService;
	}
	public ICoreDashboardCoreService getCoreDashboardCoreService() {
		return coreDashboardCoreService;
	}
	public String execute(){
		try {
			
			final HttpSession session = request.getSession();
			
			final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null || user.getRegistrationID() == null){
				return ERROR;
			}
		}catch(Exception e) {
			LOG.error("Exception raised at execute() in CoreDashBoardGrivanceAction class", e);
		}
		return Action.SUCCESS;
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
	public List<GrievanceReportVO> getGrievanceReportVO() {
		return grievanceReportVO;
	}

	public void setGrievanceReportVO(List<GrievanceReportVO> grievanceReportVO) {
		this.grievanceReportVO = grievanceReportVO;
	}
	
	public String getCategoryAndIssuetypeStatusCount(){
		try {
			LOG.info("Entered into getCategoryAndIssuetypeStatusCount()  of CoreDashBoardGrivanceAction");
			jObj = new JSONObject(getTask());
			String inputType = jObj.getString("inputType");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			String stateIds = jObj.getString("stateIds");
			Long enrollmentYrId = jObj.getLong("enrollmentYrId");
			String task = jObj.getString("task");
			complaintStatusCountVOs = coreDashboardCoreService.getCategoryAndIssuetypeStatusCount(inputType,fromDate,toDate,stateIds,enrollmentYrId,task);
		} catch (Exception e) {
			LOG.error("Exception raised at getCategoryAndIssuetypeStatusCount() method of CoreDashBoardGrivanceAction", e);
		}
		return Action.SUCCESS;
	}


	public String getEffiencyDetailsForgrivanceByDates(){
		try{
			LOG.info("Entered into getEffiencyDetailsForgrivanceByDates()  of CoreDashBoardGrivance Action");
			jObj = new JSONObject(getTask());
			
			JSONArray dysArr = jObj.getJSONArray("daysArr");
			List<Integer> daysList = new ArrayList<Integer>();
			for (int i = 0; i < dysArr.length(); i++) {
				Integer desgId = (Integer)dysArr.get(i);
				daysList.add(desgId);
			}
			
			String grievanceReqType = jObj.getString("grievanceReqType"); 
			Long enrollmentyearId = jObj.getLong("enrollmentyearId");
			grievanceReportVO=coreDashboardCoreService.getEffiencyDetailsForgrivanceByDates(daysList,grievanceReqType,enrollmentyearId);
			
		}catch(Exception e){
			LOG.error("Exception getEffiencyDetailsForgrivanceByDates() in CoreDashBoardGrivance Action class", e);
		}
		return Action.SUCCESS;
		
	}
	public String getgrivanceDetailsByIssueTypeForGraph(){
		try{
			LOG.info("Entered into getgrivanceDetailsByIssueTypeForGraph()  of CoreDashBoardGrivance Action");
			jObj = new JSONObject(getTask());
			
			Long enrollmentyearId = jObj.getLong("enrollmentyearId");
			String searchType = jObj.getString("searchType");
			String searchValue=jObj.getString("searchValue");
			grievanceReportVO=coreDashboardCoreService.getgrivanceDetailsByIssueType(enrollmentyearId,searchType,searchValue);
			
		}catch(Exception e){
			LOG.error("Exception getgrivanceDetailsByIssueTypeForGraph() in CoreDashBoardGrivance Action class", e);
		}
		return Action.SUCCESS;
		
	}
	public String getgrivanceDetailsBySearch(){
		try{
			LOG.info("Entered into getgrivanceDetailsBySearch()  of CoreDashBoardGrivance Action");
			jObj = new JSONObject(getTask());
			
			Long enrollmentyearId = jObj.getLong("enrollmentyearId");
			String searchType = jObj.getString("searchType");
			String searchValue=jObj.getString("searchValue");
			grievanceReportVO=coreDashboardCoreService.getgrivanceDetailsBySearch(enrollmentyearId,searchType,searchValue);
			
		}catch(Exception e){
			LOG.error("Exception getgrivanceDetailsBySearch() in CoreDashBoardGrivance Action class", e);
		}
		return Action.SUCCESS;
		
	}
	
}
