package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.VoterAgeRangeVO;
import com.itgrids.partyanalyst.service.IVoterModificationService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class VoterModificationReportAction extends ActionSupport implements ServletRequestAware{
	
	
	private static final long serialVersionUID = -8550656684382574240L;
	private HttpServletRequest request;
	private HttpSession session;
	private String task;
	JSONObject jObj;
	private static final Logger LOG = Logger.getLogger(VoterModificationReportAction.class);
	private List<VoterAgeRangeVO> voterAgeRangeVOList;
	private Long constituencyId;
	private Long fromPublicationDateId;
	private Long toPublicationDateId;
	private Long locationValue;
	private String locationType;
	private IVoterModificationService voterModificationService;
	
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

	public void setServletRequest(HttpServletRequest arg0) {
		
		this.request=arg0;
	}
	
	public List<VoterAgeRangeVO> getVoterAgeRangeVOList() {
		return voterAgeRangeVOList;
	}
	public void setVoterAgeRangeVOList(List<VoterAgeRangeVO> voterAgeRangeVOList) {
		this.voterAgeRangeVOList = voterAgeRangeVOList;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public Long getFromPublicationDateId() {
		return fromPublicationDateId;
	}
	public void setFromPublicationDateId(Long fromPublicationDateId) {
		this.fromPublicationDateId = fromPublicationDateId;
	}
	public Long getToPublicationDateId() {
		return toPublicationDateId;
	}
	public void setToPublicationDateId(Long toPublicationDateId) {
		this.toPublicationDateId = toPublicationDateId;
	}
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	
	public IVoterModificationService getVoterModificationService() {
		return voterModificationService;
	}
	public void setVoterModificationService(
			IVoterModificationService voterModificationService) {
		this.voterModificationService = voterModificationService;
	}
	public String execute()throws Exception
	{
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		if(user == null)
			return ERROR;
		
		return Action.SUCCESS;
	}

	public String ajaxHandler()
	{
		String param;
		param = getTask();
		
		try{
			jObj = new JSONObject(param);	
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in ajaxHandler() Method, Exception - "+e);
		}
		Long constituencyId = jObj.getLong("constituencyId");
		Long fromPublicationDateId = jObj.getLong("fromPublicationDateId");
		Long toPublicationDateId = jObj.getLong("toPublicationDateId");
		String locationType = jObj.getString("locationType");
		Long locationValue = jObj.getLong("locationValue");
		voterAgeRangeVOList = voterModificationService.getVoterInfoByPublicationDateList(locationType,locationValue,constituencyId,fromPublicationDateId,toPublicationDateId);
		return Action.SUCCESS;
	}
	
}
