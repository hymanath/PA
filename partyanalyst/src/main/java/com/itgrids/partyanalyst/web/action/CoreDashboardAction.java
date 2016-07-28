package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.UserDataVO;
import com.itgrids.partyanalyst.service.ICoreDashboardService;
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
	//Attributes
	private ICoreDashboardService coreDashboardService;
	
	//setters And Getters
	public void setCoreDashboardService(ICoreDashboardService coreDashboardService) {
		this.coreDashboardService = coreDashboardService;
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
	public String getUserAccessLevelAndValues(){
		try{
			LOG.info("Entered into getUserAccessLevelAndValues()  of CoreDashboardAction");
			jObj = new JSONObject(getTask());
			userDataVOList = coreDashboardService.getUserAccessLevelAndValues(jObj.getLong("userId"));
			
		}catch(Exception e){
			LOG.error("Exception raised at getUserAccessLevelAndValues() method of AppointmentAction", e);
		}
		return Action.SUCCESS;
	}
	public String getUserTypeByUserId(){
		try{
			LOG.info("Entered into getUserTypeByUserId()  of CoreDashboardAction");
			jObj = new JSONObject(getTask());
			userDataVO = coreDashboardService.getUserTypeByUserId(jObj.getLong("userId"));
			
		}catch(Exception e){
			LOG.error("Exception raised at getUserTypeByUserId() method of AppointmentAction", e);
		}
		return Action.SUCCESS;
	}
	
	
}
