package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
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
		
		constituencyDetails = dashBoardElectionResultsService.getConstituenciesDetailsForSubReport(request.getParameter("type"),Long.parseLong(request.getParameter("partyId")));
		return Action.SUCCESS;
		
	}


}
