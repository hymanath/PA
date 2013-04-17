package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.excel.booth.VoterModificationVO;
import com.itgrids.partyanalyst.service.IVoterModificationService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class BoothWiseModificationsCompleteInfoAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = -3748387108045099513L;
	private HttpServletRequest request;
	private HttpSession session;
	private String task;
	JSONObject jObj;
	private static final Logger LOG = Logger.getLogger(BoothWiseModificationsCompleteInfoAction.class);
	private Long constituencyId;
	private Long fromPublicationDateId;
	private Long toPublicationDateId;
	private Long locationValue;
	private Long localElectionBodyId;
	private String locationType;
	private IVoterModificationService voterModificationService;
	private List<VoterModificationVO> voterModificationVOList;
	private String locationName;
	private String locationTypeVariable;
	
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

	public Long getLocalElectionBodyId() {
		return localElectionBodyId;
	}
	public void setLocalElectionBodyId(Long localElectionBodyId) {
		this.localElectionBodyId = localElectionBodyId;
	}

	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public void setServletRequest(HttpServletRequest arg0) {
		
		this.request=arg0;
	}
	
	public IVoterModificationService getVoterModificationService() {
		return voterModificationService;
	}
	public void setVoterModificationService(
			IVoterModificationService voterModificationService) {
		this.voterModificationService = voterModificationService;
	}
	
	public List<VoterModificationVO> getVoterModificationVOList() {
		return voterModificationVOList;
	}
	public void setVoterModificationVOList(
			List<VoterModificationVO> voterModificationVOList) {
		this.voterModificationVOList = voterModificationVOList;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getLocationTypeVariable() {
		return locationTypeVariable;
	}
	public void setLocationTypeVariable(String locationTypeVariable) {
		this.locationTypeVariable = locationTypeVariable;
	}
	public String execute()
	{
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		if(user == null)
			return ERROR;
		locationName = voterModificationService.getLocationNameByLocationValue(locationType, locationValue);
		
		if(locationType.equalsIgnoreCase("localElectionBody") || locationType.equalsIgnoreCase(IConstants.LOCAL_BODY_ELECTION) || locationType.equalsIgnoreCase(IConstants.LOCALELECTIONBODY))
			locationTypeVariable = voterModificationService.getLocationTypeForLocalEleBodyByLocalEleBodyId(locationValue);
		else
			locationTypeVariable = locationType;
		
		return Action.SUCCESS;
	}
	
	public String ajaxHandler()
	{
		try{
			jObj = new JSONObject(getTask());
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in ajaxHandler() Method, Exception - "+e);
		}
		
		session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		if(user == null)
			return ERROR;
		
		Long constituencyId = jObj.getLong("constituencyId");
		Long locationValue = jObj.getLong("locationValue");
		String locationType = jObj.getString("locationType");
		Long publicationDateId = jObj.getLong("publicationDateId");
		voterModificationVOList = voterModificationService.getBoothWiseModificationsCompleteDetails(constituencyId, locationValue, publicationDateId, locationType);
		
		return Action.SUCCESS;
	}

}
