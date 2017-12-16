package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ComplaintStatusCountVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.ICoreDashboardCoreService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CoreDashBoardGrivanceAction extends ActionSupport implements ServletRequestAware {

	private HttpServletRequest request;
	private HttpSession session;
	
	private JSONObject 	jObj;
	private String 	task;
	
	private ICoreDashboardCoreService coreDashboardCoreService;
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
	
}
