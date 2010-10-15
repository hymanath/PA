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
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.IAnanymousUserService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ConnectPeopleAction extends ActionSupport implements ServletRequestAware{
	
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
	
	public String connectToUser() throws Exception
	{
		String param;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Long districtId = new Long(jObj.getString("districtId"));
		String districtName = jObj.getString("districtName");
		Long connectUserId = new Long(jObj.getString("connectUserId"));
		String connectUserName = jObj.getString("connectUserName");
		Long userId = new Long(jObj.getString("userId"));
		String subject = jObj.getString("connectMessage");
		
		
		List<Long> senderIds = new ArrayList<Long>();
		senderIds.add(userId);
		
		List<Long> connectUserIds = new ArrayList<Long>(); 
		connectUserIds.add(connectUserId);
		
		resultStatus = ananymousUserService.saveCommunicationDataBetweenUsers(senderIds, connectUserIds, IConstants.FRIEND_REQUEST, subject);
		
		return Action.SUCCESS;
	}
	
	public String getAllConnectedUsersInDistrict() throws Exception
	{
		String param;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Long districtId = new Long(jObj.getString("districtId"));
		String districtName = jObj.getString("districtName");		
		Long userId = new Long(jObj.getString("userId"));
		String subject = jObj.getString("connectMessage");
		
		List<Long> senderIds = new ArrayList<Long>();
		senderIds.add(userId);
		
		JSONArray connectUserIds = jObj.getJSONArray("connectUserIds");
		
		List<Long> connectUserIdsList = new ArrayList<Long>();
		int size = connectUserIds.length();
		
		for(int j=0;j<size;j++)
		{			
			connectUserIdsList.add(new Long(connectUserIds.getString(j)));
		}		
		
		resultStatus = ananymousUserService.saveCommunicationDataBetweenUsers(senderIds, connectUserIdsList, IConstants.FRIEND_REQUEST, subject);
		
		return Action.SUCCESS;
	}
	
}
