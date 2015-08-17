package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.PartyMeetingVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.model.Registration;
import com.itgrids.partyanalyst.service.IPartyMeetingService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class PartyMeetingAction extends ActionSupport  implements ServletRequestAware{
	
	private static final Logger LOG = Logger.getLogger(PartyMeetingAction.class);
	
	private HttpServletRequest  request;
	private HttpSession session;
	private JSONObject	jObj;
	private String 		task;
	private IPartyMeetingService partyMeetingService;
	private PartyMeetingVO partyMeetingVO;
	private String status;
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public IPartyMeetingService getPartyMeetingService() {
		return partyMeetingService;
	}

	public PartyMeetingVO getPartyMeetingVO() {
		return partyMeetingVO;
	}

	public void setPartyMeetingService(IPartyMeetingService partyMeetingService) {
		this.partyMeetingService = partyMeetingService;
	}

	public void setPartyMeetingVO(PartyMeetingVO partyMeetingVO) {
		this.partyMeetingVO = partyMeetingVO;
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
	
	public String getPartyMeetingsOverViewForCadre()
	{
		try {
			 jObj = new JSONObject(getTask());
		     Long tdpCadreId = jObj.getLong("tdpCadreId");
		     partyMeetingVO = partyMeetingService.getPartyMeetingsForCadrePeople(tdpCadreId);
		} catch (Exception e) {
			LOG.error(" Exception occured in getPartyMeetingDetailsForCadre in PartyMeetingAction Class.",e);
		}
		return Action.SUCCESS;
	}
	
	public String getPartyMeetingDetailsForCadre()
	{
		try {
			 jObj = new JSONObject(getTask());
		     Long tdpCadreId = jObj.getLong("tdpCadreId");
		     partyMeetingVO = partyMeetingService.getPartyMeetingDetailsBySearchType(tdpCadreId);
		} catch (Exception e) {
			LOG.error(" Exception occured in getPartyMeetingDetailsForCadre in PartyMeetingAction Class.",e);
		}
		return Action.SUCCESS;
	}
	
	public String getMeetingWiseDetailsByMeetingTypeId()
	{
		try {
			 jObj = new JSONObject(getTask());
		     Long meetingTypeId = jObj.getLong("meetingTypeId");
		    partyMeetingVO = partyMeetingService.getMeetingTypeWiseDescription(meetingTypeId);
		} catch (Exception e) {
			LOG.error(" Exception occured in getPartyMeetingDetailsForCadre in PartyMeetingAction Class.",e);
		}
		return Action.SUCCESS;
	}
	
	public String updateMeetingPoint(){
		try {
			LOG.info("Entered into updateMeetingPoint");
			jObj = new JSONObject(getTask());
			Long minuteId = jObj.getLong("minuteId");
			String minuteText = jObj.getString("minuteText");
			Long loggedUser=0l;
			RegistrationVO regVo = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVo!=null && regVo.getRegistrationID()!=null){
				loggedUser = regVo.getRegistrationID();
			}
			
			status = partyMeetingService.updateMeetingPoint(minuteId,minuteText,loggedUser);
		} catch (Exception e) {
			LOG.error("Exception raise at updateMeetingPoint", e);
		}
		return Action.SUCCESS;
	}
	
	public String deleteMeetingMinutePoint(){
		try {
			LOG.info("Entered into deleteMeetingMinutePoint");
			jObj = new JSONObject(getTask());
			Long minuteId = jObj.getLong("minuteId");
			Long loggedUser=0l;
			RegistrationVO regVo = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVo!=null && regVo.getRegistrationID()!=null){
				loggedUser = regVo.getRegistrationID();
			}
			
			status = partyMeetingService.deleteMeetingMinutePoint(minuteId,loggedUser);
		} catch (Exception e) {
			LOG.error("Exception raise at deleteMeetingMinutePoint", e);
		}
		return Action.SUCCESS;
	}
}
