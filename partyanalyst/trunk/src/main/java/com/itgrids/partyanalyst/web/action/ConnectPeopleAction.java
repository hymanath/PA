package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CandidateVO;
import com.itgrids.partyanalyst.dto.DataTransferVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.IAnanymousUserService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ConnectPeopleAction extends ActionSupport implements ServletRequestAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5360785727347677445L;

	private static final Logger log = Logger.getLogger(ProblemPostControlAction.class);
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	private String redirectLoc;
	private String districtId;
	private String task;
	JSONObject jObj;
	private List<CandidateVO> candidates;
	private IAnanymousUserService ananymousUserService;
	private ResultStatus resultStatus;
	private DataTransferVO userDetails;
	
	public DataTransferVO getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(DataTransferVO userDetails) {
		this.userDetails = userDetails;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public IAnanymousUserService getAnanymousUserService() {
		return ananymousUserService;
	}

	public void setAnanymousUserService(IAnanymousUserService ananymousUserService) {
		this.ananymousUserService = ananymousUserService;
	}

	public List<CandidateVO> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<CandidateVO> candidates) {
		this.candidates = candidates;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getRedirectLoc() {
		return redirectLoc;
	}

	public void setRedirectLoc(String redirectLoc) {
		this.redirectLoc = redirectLoc;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}


	public void setServletRequest(HttpServletRequest request) {
			
		this.request = request;
	}
	
	
	public String execute() throws Exception
	{
		session = request.getSession();
		Object regVO = session.getAttribute(IConstants.USER);
		
		if(regVO == null){
			log.error(" No User Log In .....");			
			return IConstants.NOT_LOGGED_IN;
		}
		
		return Action.SUCCESS;
	}
	public String updateUserStatus() throws Exception
	{
		String param;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		if(user==null){
			return IConstants.NOT_LOGGED_IN;
		}
		List<Long> userId = new ArrayList<Long>();
		userId.add(user.getRegistrationID());
		
		List<Long> recepientId = new ArrayList<Long>();
		recepientId.add(jObj.getLong("recepientId"));
		
		
		if(jObj.getString("task").equalsIgnoreCase("acceptRequest"))
		{
			resultStatus = ananymousUserService.saveCommunicationDataBetweenUsers(userId,recepientId,IConstants.CONNECTED,"");
		}
		if(jObj.getString("task").equalsIgnoreCase("rejectRequest"))
		{
			resultStatus =  ananymousUserService.saveCommunicationDataBetweenUsers(userId,recepientId,IConstants.REJECTED,"");
		}
		
		return Action.SUCCESS;
	}
	
	public String getRequestMessagesForUser() throws Exception
	{
		String param;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		if(user==null){
			return IConstants.NOT_LOGGED_IN;
		}
		List<Long> userId = new ArrayList<Long>();
		userId.add(user.getRegistrationID());
		userDetails = ananymousUserService.getDataForAUserProfile(userId,IConstants.FRIEND_REQUEST);
		return Action.SUCCESS;
	}
	public String connectToUser() throws Exception
	{
		String param;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Long districtId = new Long(jObj.getString("districtId"));
		String districtName = jObj.getString("districtName");
		Long connectUserId = new Long(jObj.getString("connectUserId"));
		String connectUserName = jObj.getString("connectUserName");
		Long userId = new Long(jObj.getString("userId"));
		String subject = jObj.getString("connectMessage");
		
		List<Long> locationIds = new ArrayList<Long>();
		locationIds.add(districtId);
		
		List<Long> senderIds = new ArrayList<Long>();
		senderIds.add(userId);
		
		List<Long> connectUserIds = new ArrayList<Long>(); 
		connectUserIds.add(connectUserId);
		
		//resultStatus = ananymousUserService.saveCommunicationDataBetweenUsers(senderIds, connectUserIds, IConstants.FRIEND_REQUEST, subject);
		userDetails = ananymousUserService.getAllUsersAfterAcceptingRequest(locationIds, IConstants.DISTRICT_LEVEL, IConstants.MAX_ANONYMOUS_USER_DISPLAY, userId, senderIds, connectUserIds, IConstants.FRIEND_REQUEST, subject);
		return Action.SUCCESS;
	}
	
	public String getAllConnectedUsersInDistrict() throws Exception
	{
		String param;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		Long districtId = new Long(jObj.getString("districtId"));
		String districtName = jObj.getString("districtName");
		Long userId = new Long(jObj.getString("userId"));
		
		List<Long> listOfDistricts = new ArrayList<Long>();
		listOfDistricts.add(districtId);
		
		userDetails = ananymousUserService.getAllRegisteredAnonymousUserBasedOnLocation(listOfDistricts,IConstants.DISTRICT_LEVEL,IConstants.ALL_CONNECTED_USER_DISPLAY,userId,IConstants.ALL);
		
		return Action.SUCCESS;
	}
	
	public String connectToUserSet() throws Exception
	{
		String param;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Long districtId = new Long(jObj.getString("districtId"));
		String districtName = jObj.getString("districtName");		
		Long userId = new Long(jObj.getString("userId"));
		String subject = jObj.getString("connectMessage");
		
		List<Long> locationIds = new ArrayList<Long>();
		locationIds.add(districtId);
		
		List<Long> senderIds = new ArrayList<Long>();
		senderIds.add(userId);
		
		JSONArray connectUserIds = jObj.getJSONArray("connectUserIds");
		
		List<Long> connectUserIdsList = new ArrayList<Long>();
		int size = connectUserIds.length();
		
		for(int j=0;j<size;j++)
		{			
			connectUserIdsList.add(new Long(connectUserIds.getString(j)));
		}		
		
		//resultStatus = ananymousUserService.saveCommunicationDataBetweenUsers(senderIds, connectUserIdsList, IConstants.FRIEND_REQUEST, subject);
		userDetails = ananymousUserService.getAllUsersAfterAcceptingRequest(locationIds, IConstants.DISTRICT_LEVEL, IConstants.ALL_CONNECTED_USER_DISPLAY, userId, senderIds, connectUserIdsList, IConstants.FRIEND_REQUEST, subject);
		return Action.SUCCESS;
	}
	
	public String getAllConnectedUsersByFilterView() throws Exception
	{
		HttpSession session = request.getSession();		
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
   		
		String param;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Long districtId = new Long(jObj.getString("districtId"));
		String districtName = jObj.getString("districtName");		
		Long userId = new Long(jObj.getString("userId"));	
		String statusText = jObj.getString("statusText");
		
		JSONArray constituencyIds = jObj.getJSONArray("constituencyIds");		
		List<Long> listOfConstituencies = new ArrayList<Long>();
		
		for(int i=0; i<constituencyIds.length();i++)
			listOfConstituencies.add(new Long(constituencyIds.getString(i)));
		
		
		
		String status = null;
		if(statusText.equalsIgnoreCase(IConstants.ALL))
			status = IConstants.ALL;
		else if(statusText.equalsIgnoreCase(IConstants.CONNECTED))
			status = IConstants.CONNECTED;
		else if(statusText.equalsIgnoreCase(IConstants.DISCONNECTED))
			status = IConstants.DISCONNECTED;
		else if(statusText.equalsIgnoreCase(IConstants.PENDING))
			status = IConstants.PENDING;
		else if(statusText.equalsIgnoreCase(IConstants.FRIEND_REQUEST))
			status = IConstants.FRIEND_REQUEST;
		else if(statusText.equalsIgnoreCase(IConstants.COMMENTS))
			status = IConstants.COMMENTS;
		else if(statusText.equalsIgnoreCase(IConstants.SCRAP))
			status = IConstants.SCRAP;
		
		
		if(user==null){
   			userDetails = ananymousUserService.getAllRegisteredAnonymousUserBasedOnLocation(listOfConstituencies,IConstants.CONSTITUENCY_LEVEL,IConstants.ALL_CONNECTED_USER_DISPLAY,0l,status);	
   		}else{
   			userDetails = ananymousUserService.getAllRegisteredAnonymousUserBasedOnLocation(listOfConstituencies,IConstants.CONSTITUENCY_LEVEL,IConstants.ALL_CONNECTED_USER_DISPLAY,user.getRegistrationID(),status);
   		}
   		
		return Action.SUCCESS;
	}
	
}
