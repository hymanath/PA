package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.DashBoardResultsVO;
import com.itgrids.partyanalyst.service.IDashBoardElectionResultsService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class DashBoardElectionResultsAction extends ActionSupport implements ServletRequestAware {
	
	private HttpServletRequest request;
	private String task;
	private JSONObject jObj;
	private IDashBoardElectionResultsService dashBoardElectionResultsService;
	private DashBoardResultsVO resultVO;
	private List<Object[]> constituencyDetails;
	private List<DashBoardResultsVO> matrixReport;
	private List<DashBoardResultsVO> subReport;
	private List<DashBoardResultsVO> dashBoardConstiResults;
	
	
	public List<DashBoardResultsVO> getDashBoardConstiResults() {
		return dashBoardConstiResults;
	}

	public void setDashBoardConstiResults(
			List<DashBoardResultsVO> dashBoardConstiResults) {
		this.dashBoardConstiResults = dashBoardConstiResults;
	}

	public List<DashBoardResultsVO> getSubReport() {
		return subReport;
	}

	public void setSubReport(List<DashBoardResultsVO> subReport) {
		this.subReport = subReport;
	}

	public List<DashBoardResultsVO> getMatrixReport() {
		return matrixReport;
	}

	public void setMatrixReport(List<DashBoardResultsVO> matrixReport) {
		this.matrixReport = matrixReport;
	}

	public List<Object[]> getConstituencyDetails() {
		return constituencyDetails;
	}

	public void setConstituencyDetails(List<Object[]> constituencyDetails) {
		this.constituencyDetails = constituencyDetails;
	}

	public DashBoardResultsVO getResultVO() {
		return resultVO;
	}

	public void setResultVO(DashBoardResultsVO resultVO) {
		this.resultVO = resultVO;
	}

	public IDashBoardElectionResultsService getDashBoardElectionResultsService() {
		return dashBoardElectionResultsService;
	}

	public void setDashBoardElectionResultsService(
			IDashBoardElectionResultsService dashBoardElectionResultsService) {
		this.dashBoardElectionResultsService = dashBoardElectionResultsService;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
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
	
	public String execute()
	{
		return Action.SUCCESS;
		
	}
	
	public String getElectionResultsSummary()
	{
		resultVO = dashBoardElectionResultsService.getElectionResultsSummary();
		return Action.SUCCESS;
	}
	
	public String getConstituenciesDetailsForSubReport()
	{
		
		constituencyDetails = dashBoardElectionResultsService
				.getConstituenciesDetailsForSubReport(
						request.getParameter("type"),
						Long.parseLong(request.getParameter("partyId")),
						Long.parseLong(request.getParameter("locationId")),
						Long.parseLong(request.getParameter("scopeId")));
		return Action.SUCCESS;
		
	}
	
	public String getMatrixReportForElectionResult()
	{
		try
		{
			jObj = new JSONObject(getTask());
			
			
			Long electionId = jObj.getLong("electionId");
			Long scopeId = jObj.getLong("scopeId");
			
			 JSONArray jArray = jObj.getJSONArray("locationIds");
			 List<Long> locationIds = new ArrayList<Long>();
			 
			   for (int i = 0; i < jArray.length(); i++) 
			   {
				   locationIds.add(new Long(jArray.get(i).toString()));
			   }
			   
			matrixReport = dashBoardElectionResultsService.getMatrixReportForElectionResult(electionId,locationIds,scopeId);
			
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return Action.SUCCESS;
	}
	
	public String getSubReportForElectionResultByConstituencyReservationType()
	{
		try
		{
			jObj = new JSONObject(getTask());
			
			
			Long electionId = jObj.getLong("electionId");
			Long scopeId = jObj.getLong("scopeId");
			
			 JSONArray jArray = jObj.getJSONArray("locationIds");
			 List<Long> locationIds = new ArrayList<Long>();
			 
			   for (int i = 0; i < jArray.length(); i++) 
			   {
				   locationIds.add(new Long(jArray.get(i).toString()));
			   }
			   
			   subReport = dashBoardElectionResultsService.getSubReportForElectionResultByConstituencyReservationType(electionId,locationIds,scopeId);
			
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return Action.SUCCESS;
	}
	
	public String getSubReportForElectionResultByConstituencyType()
	{
		try
		{
			jObj = new JSONObject(getTask());
			
			
			Long electionId = jObj.getLong("electionId");
			Long scopeId = jObj.getLong("scopeId");
			
			 JSONArray jArray = jObj.getJSONArray("locationIds");
			 List<Long> locationIds = new ArrayList<Long>();
			 
			   for (int i = 0; i < jArray.length(); i++) 
			   {
				   locationIds.add(new Long(jArray.get(i).toString()));
			   }
			   
			   subReport = dashBoardElectionResultsService.getSubReportForElectionResultByConstituencyType(electionId,locationIds,scopeId);
			
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return Action.SUCCESS;
	}
	public String getdashBoardConstituencyWiseResults(){
		try{
			jObj = new JSONObject(getTask());
			Long electionId= jObj.getLong("electionId");
			
			 JSONArray jArray = jObj.getJSONArray("constituencyIds");
			 List<Long> locationIds = new ArrayList<Long>();
			 
			   for (int i = 0; i < jArray.length(); i++) 
			   {
				   locationIds.add(new Long(jArray.get(i).toString()));
			   }
			
			dashBoardConstiResults  = dashBoardElectionResultsService.getConstituencyWiseLiveResults(electionId,locationIds);
		} catch (Exception e) {
			LOG.error("Exception occured in getConstituencyWiseResults() ",e);
			return Action.ERROR;
		}		
		return Action.SUCCESS;
	}
}
