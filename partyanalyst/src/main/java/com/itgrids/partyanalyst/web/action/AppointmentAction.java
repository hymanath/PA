package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.AppointmentBasicInfoVO;
import com.itgrids.partyanalyst.dto.AppointmentVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.IAppointmentService;
import com.itgrids.partyanalyst.utils.IConstants;
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
	private List<IdNameVO> appointmentStatusList; 
	private List<IdNameVO> appointmentCandDesigList;
	private List<IdNameVO> appointmentPrirityList;
	private List<IdNameVO> appointmentLblStatusList;
	private List<AppointmentBasicInfoVO> appointmentUserDtlsList;
	
	public List<AppointmentBasicInfoVO> getAppointmentUserDtlsList() {
		return appointmentUserDtlsList;
	}

	public void setAppointmentUserDtlsList(
			List<AppointmentBasicInfoVO> appointmentUserDtlsList) {
		this.appointmentUserDtlsList = appointmentUserDtlsList;
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
	
	public List<IdNameVO> getAppointmentStatusList() {
		return appointmentStatusList;
	}
	
	public String execute(){
		return Action.SUCCESS;
	}
	
	public void setAppointmentStatusList(List<IdNameVO> appointmentStatusList) {
		this.appointmentStatusList = appointmentStatusList;
	}
	public List<IdNameVO> getAppointmentCandDesigList() {
		return appointmentCandDesigList;
	}

	public void setAppointmentCandDesigList(List<IdNameVO> appointmentCandDesigList) {
		this.appointmentCandDesigList = appointmentCandDesigList;
	}

	public List<IdNameVO> getAppointmentPrirityList() {
		return appointmentPrirityList;
	}

	public void setAppointmentPrirityList(List<IdNameVO> appointmentPrirityList) {
		this.appointmentPrirityList = appointmentPrirityList;
	}

	public List<IdNameVO> getAppointmentLblStatusList() {
		return appointmentLblStatusList;
	}

	public void setAppointmentLblStatusList(List<IdNameVO> appointmentLblStatusList) {
		this.appointmentLblStatusList = appointmentLblStatusList;
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
	public String getAppointmentStatus(){
		try{
			LOG.info("Entered into getAppointmentStatus() method of AppointmentAction");
			appointmentStatusList = appointmentService.getAppointmentStatusList();
			
		}catch(Exception e){
			LOG.error("Exception raised at getAppointmentStatus() method of AppointmentAction", e);
		}
		return Action.SUCCESS;
	}
	public String getAppCandidateDesigList(){
		try{
			LOG.info("Entered into getAppCandidateDesigList() method of AppointmentAction");
			appointmentCandDesigList = appointmentService.getAppCandidateDesigList();
			
		}catch(Exception e){
			LOG.error("Exception raised at getAppCandidateDesigList() method of AppointmentAction", e);
		}
		return Action.SUCCESS;
	}
	public String getAppointmentPriorityList(){
		try{
			LOG.info("Entered into getAppointmentPriorityList() method of AppointmentAction");
			appointmentPrirityList = appointmentService.getAppointmentPriorityList();
			
		}catch(Exception e){
			LOG.error("Exception raised at getAppointmentPriorityList() method of AppointmentAction", e);
		}
		return Action.SUCCESS;
	}
	public String getAppmntLblStatusList(){
		try{
			LOG.info("Entered into getAppmntLblStatusList() method of AppointmentAction");
			appointmentLblStatusList = appointmentService.getAppmntLblStatusList();
			
		}catch(Exception e){
			LOG.error("Exception raised at getAppmntLblStatusList() method of AppointmentAction", e);
		}
		return Action.SUCCESS;
	}
	public String getAppointmentUsersDtls(){
		session = request.getSession();
		final RegistrationVO registrationVO = (RegistrationVO) session.getAttribute(IConstants.USER);
		try{
			LOG.info("Entered into getAppointmentUsersDtls() method of AppointmentAction");
			if(registrationVO!=null){
				appointmentUserDtlsList=appointmentService.getAppointmentUsersDtlsByUserId(registrationVO.getRegistrationID());
		}
		}catch(Exception e){
		 LOG.error("Exception raised at getAppointmentUsersDtls() method of AppointmentAction", e);
		}
		return Action.SUCCESS;
	}
	public String getLabelDtls(){
       try{
    	   LOG.info("Entered into getLabelDtls() method of AppointmentAction");
    	     jObj = new JSONObject(getTask());
    	     appointmentUserDtlsList=appointmentService.getLabelDtslByDate(jObj.getString("currentDate"));
       }catch(Exception e){
    	   LOG.error("Exception raised at getLabelDtls() method of AppointmentAction", e);
       }
		return Action.SUCCESS;
	}
}
