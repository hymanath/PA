package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.BoothInchargeDetailsVO;
import com.itgrids.partyanalyst.dto.InputVO;
import com.itgrids.partyanalyst.service.IBoothDataValidationService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class BoothReportAction extends ActionSupport implements ServletRequestAware {

	
	private static final Logger LOG = Logger.getLogger(BoothReportAction.class);
	
	private HttpServletRequest request;
	private HttpSession 	   session;
	private String task;
	private JSONObject jObj;
	private List<BoothInchargeDetailsVO> boothInchargeDetailsList;
	private IBoothDataValidationService boothDataValidationService;
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
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
	public List<BoothInchargeDetailsVO> getBoothInchargeDetailsList() {
		return boothInchargeDetailsList;
	}
	public void setBoothInchargeDetailsList(List<BoothInchargeDetailsVO> boothInchargeDetailsList) {
		this.boothInchargeDetailsList = boothInchargeDetailsList;
	}
	public void setBoothDataValidationService(IBoothDataValidationService boothDataValidationService) {
		this.boothDataValidationService = boothDataValidationService;
	}
	@Override
	public String execute() throws Exception {
		LOG.debug("BoothReportAction.execute()... started");
		String strBoothID = request.getParameter("boothID");
		String strPartNo = request.getParameter("partNo");
		LOG.debug("strBoothID:::"+strBoothID);
		LOG.debug("strPartNo:::"+strPartNo);
		if(strBoothID==null)
			return ERROR;
		Long boothID = Long.valueOf(strBoothID);
		
		return SUCCESS;
	}
	public String getBoothInchargeDashboard(){
		
		return Action.SUCCESS;
	}
	public String getLocationLevelWiseBoothCount(){
		LOG.info("Entered into getLocationLevelWiseBoothCount()  of BoothReportAction ");
		try{
			jObj = new JSONObject(getTask());
			InputVO inputVO = new InputVO();
			inputVO.setLocationLevel(jObj.getString("locationLevel"));
			inputVO.setFilterLevel(jObj.getString("filterLevel"));
			inputVO.setFilterValue(jObj.getLong("filterValue"));
			inputVO.setBoothInchargeEnrollmentId(jObj.getLong("boothInchargeEnrollmentId"));
			inputVO.setFromDateStr(jObj.getString("startDate"));
			inputVO.setToDateStr(jObj.getString("endDate"));
			boothInchargeDetailsList = boothDataValidationService.getLocationLevelWiseBoothDetails(inputVO);
		}catch(Exception e){
			LOG.error("Exception raised at getLocationLevelWiseBoothCount() method of BoothReportAction", e);
		}
		return Action.SUCCESS;
	}
}
