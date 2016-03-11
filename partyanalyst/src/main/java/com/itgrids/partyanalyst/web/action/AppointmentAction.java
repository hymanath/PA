package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.AppointmentVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.IAppointmentService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class AppointmentAction extends ActionSupport implements ServletRequestAware{
	
	private final static Logger LOG = Logger.getLogger(AppointmentAction.class);
	private HttpServletRequest request;
	private HttpSession session;
	private String task;
	private JSONObject jObj;
	private IAppointmentService appointmentService; 
	private ResultStatus resultStatus;
	private AppointmentVO appointmentVO = new AppointmentVO();
	private RegistrationVO regVO = new RegistrationVO();
	
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

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public IAppointmentService getAppointmentService() {
		return appointmentService;
	}

	public void setAppointmentService(IAppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}
	
	public AppointmentVO getAppointmentVO() {
		return appointmentVO;
	}

	public void setAppointmentVO(AppointmentVO appointmentVO) {
		this.appointmentVO = appointmentVO;
	}
	
	public RegistrationVO getRegVO() {
		return regVO;
	}

	public void setRegVO(RegistrationVO regVO) {
		this.regVO = regVO;
	}
	
	public String execute(){
		return Action.SUCCESS;
	}
	
	public String saveAppointment(){
		try {
			final HttpSession session = request.getSession();
			final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null || user.getRegistrationID() == null){
				return ERROR;
			}
			jObj = new JSONObject(getTask());
			
			resultStatus = appointmentService.saveAppointment(appointmentVO,user.getRegistrationID());
			
		} catch (Exception e) {
			LOG.error("Exception raised at saveAppointment", e);
		}
		
		return Action.SUCCESS;
	}
	
	
	
}
