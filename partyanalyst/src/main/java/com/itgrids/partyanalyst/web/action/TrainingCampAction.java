package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.TraingCampCallerVO;
import com.itgrids.partyanalyst.model.Job;
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
	private List<TraingCampCallerVO> statusCountList;
	
	
	public List<TraingCampCallerVO> getStatusCountList() {
		return statusCountList;
	}

	public void setStatusCountList(List<TraingCampCallerVO> statusCountList) {
		this.statusCountList = statusCountList;
	}

	public ITrainingCampService getTrainingCampService() {
		return trainingCampService;
	}

	public void setTrainingCampService(ITrainingCampService trainingCampService) {
		this.trainingCampService = trainingCampService;
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

	public String execute(){
		return Action.SUCCESS;
	}
	public String callCenterTrainingAdmin(){
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
}
