package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CadreManagementVO;
import com.itgrids.partyanalyst.dto.ConstituenciesStatusVO;
import com.itgrids.partyanalyst.dto.DataTransferVO;
import com.itgrids.partyanalyst.dto.NavigationVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SpecialPageVO;
import com.itgrids.partyanalyst.dto.SubscriptionsMainVO;
import com.itgrids.partyanalyst.dto.UserCommentsInfoVO;
import com.itgrids.partyanalyst.dto.UserProfileVO;
import com.itgrids.partyanalyst.dto.UserSettingsVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IAnanymousUserService;
import com.itgrids.partyanalyst.service.IProblemManagementService;
import com.itgrids.partyanalyst.service.ISpecialPageService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.IUserCadreManagementService;
import com.itgrids.partyanalyst.service.IUserProfileService;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
public class UserProfileAction extends ActionSupport implements ServletRequestAware {

	private static final long serialVersionUID = -4620729281316958397L;
	private static final Logger log = Logger.getLogger(UserProfileAction.class);
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	private String redirectLoc;
	private String task;
	JSONObject jObj;
	private String loginUserName;
	private Long loginUserId;
	private String loginUserProfilePic;
	private IAnanymousUserService ananymousUserService;
	private IStaticDataService staticDataService;
	private IUserProfileService userProfileService;
	private ResultStatus resultStatus;
	private DataTransferVO userDetails;
	private DataTransferVO dataTransferVO,connectedUsers;
	private NavigationVO messageTypes;
	private ConstituenciesStatusVO constituenciesStatusVO;
	private IProblemManagementService problemManagementService;
	private String profileUserName;
	private List<RegistrationVO> registrationVOList;
	

	private ISpecialPageService specialPageService;
	private List<SpecialPageVO> specialPageVOList;
	private Long profileId;
	private UserCommentsInfoVO userComments;
	private ProblemBeanVO problemDetailsVO;
	private SubscriptionsMainVO subscriptionsMainVO;
	private List<ProblemBeanVO> problemBeanVOList,problemList,streamList;
	private UserSettingsVO userSettingsList;
	private String status;
	private List<UserSettingsVO> userFavoutiteLinks;
	private List<UserProfileVO> subscriptionsList;
	private String notLogged = "sessionExpired";
	
	private CadreManagementVO cadreManagementVO = null;
	private IUserCadreManagementService userCadreManagementService;
	private boolean hasNewsMonitoring = false;
	private EntitlementsHelper entitlementsHelper;
	private boolean hasSubUserEntitlement = false;
	private boolean hasCallCenterEntitlment = false;
	private boolean hasProfileManagement = false;
	private Boolean logInStatus = false;
	private String userType;
	private String connectStatus;
	
	
	public boolean isHasProfileManagement() {
		return hasProfileManagement;
	}

	public void setHasProfileManagement(boolean hasProfileManagement) {
		this.hasProfileManagement = hasProfileManagement;
	}

	public boolean isHasSubUserEntitlement() {
		return hasSubUserEntitlement;
	}

	public void setHasSubUserEntitlement(boolean hasSubUserEntitlement) {
		this.hasSubUserEntitlement = hasSubUserEntitlement;
	}

	public boolean isHasCallCenterEntitlment() {
		return hasCallCenterEntitlment;
	}

	public void setHasCallCenterEntitlment(boolean hasCallCenterEntitlment) {
		this.hasCallCenterEntitlment = hasCallCenterEntitlment;
	}

	public boolean isHasNewsMonitoring() {
		return hasNewsMonitoring;
	}

	public void setHasNewsMonitoring(boolean hasNewsMonitoring) {
		this.hasNewsMonitoring = hasNewsMonitoring;
	}

	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public CadreManagementVO getCadreManagementVO() {
		return cadreManagementVO;
	}

	public void setCadreManagementVO(CadreManagementVO cadreManagementVO) {
		this.cadreManagementVO = cadreManagementVO;
	}

	public IUserCadreManagementService getUserCadreManagementService() {
		return userCadreManagementService;
	}

	public void setUserCadreManagementService(
			IUserCadreManagementService userCadreManagementService) {
		this.userCadreManagementService = userCadreManagementService;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UserSettingsVO getUserSettingsList() {
		return userSettingsList;
	}

	public void setUserSettingsList(UserSettingsVO userSettingsList) {
		this.userSettingsList = userSettingsList;
	}

	
	public ProblemBeanVO getProblemDetailsVO() {
		return problemDetailsVO;
	}

	public void setProblemDetailsVO(ProblemBeanVO problemDetailsVO) {
		this.problemDetailsVO = problemDetailsVO;
	}

	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}

	public void setServletContext(ServletContext arg0) {
		
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public String getRedirectLoc() {
		return redirectLoc;
	}

	public void setRedirectLoc(String redirectLoc) {
		this.redirectLoc = redirectLoc;
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

	public String getLoginUserName() {
		return loginUserName;
	}

	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}

	public Long getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(Long loginUserId) {
		this.loginUserId = loginUserId;
	}

	public String getLoginUserProfilePic() {
		return loginUserProfilePic;
	}

	public void setLoginUserProfilePic(String loginUserProfilePic) {
		this.loginUserProfilePic = loginUserProfilePic;
	}

	public IAnanymousUserService getAnanymousUserService() {
		return ananymousUserService;
	}

	public void setAnanymousUserService(IAnanymousUserService ananymousUserService) {
		this.ananymousUserService = ananymousUserService;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public DataTransferVO getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(DataTransferVO userDetails) {
		this.userDetails = userDetails;
	}

	public DataTransferVO getDataTransferVO() {
		return dataTransferVO;
	}

	public void setDataTransferVO(DataTransferVO dataTransferVO) {
		this.dataTransferVO = dataTransferVO;
	}

	public DataTransferVO getConnectedUsers() {
		return connectedUsers;
	}

	public void setConnectedUsers(DataTransferVO connectedUsers) {
		this.connectedUsers = connectedUsers;
	}

	public NavigationVO getMessageTypes() {
		return messageTypes;
	}

	public void setMessageTypes(NavigationVO messageTypes) {
		this.messageTypes = messageTypes;
	}

	public ConstituenciesStatusVO getConstituenciesStatusVO() {
		return constituenciesStatusVO;
	}

	public void setConstituenciesStatusVO(
			ConstituenciesStatusVO constituenciesStatusVO) {
		this.constituenciesStatusVO = constituenciesStatusVO;
	}

	public ISpecialPageService getSpecialPageService() {
		return specialPageService;
	}

	public void setSpecialPageService(ISpecialPageService specialPageService) {
		this.specialPageService = specialPageService;
	}

	public List<SpecialPageVO> getSpecialPageVOList() {
		return specialPageVOList;
	}

	public void setSpecialPageVOList(List<SpecialPageVO> specialPageVOList) {
		this.specialPageVOList = specialPageVOList;
	}

	public UserCommentsInfoVO getUserComments() {
		return userComments;
	}

	public void setUserComments(UserCommentsInfoVO userComments) {
		this.userComments = userComments;
	}
	
	public SubscriptionsMainVO getSubscriptionsMainVO() {
		return subscriptionsMainVO;
	}

	public void setSubscriptionsMainVO(SubscriptionsMainVO subscriptionsMainVO) {
		this.subscriptionsMainVO = subscriptionsMainVO;
	}
	
	public List<ProblemBeanVO> getProblemBeanVOList() {
		return problemBeanVOList;
	}

	public void setProblemBeanVOList(List<ProblemBeanVO> problemBeanVOList) {
		this.problemBeanVOList = problemBeanVOList;
	}
	
	public List<UserSettingsVO> getUserFavoutiteLinks() {
		return userFavoutiteLinks;
	}

	public void setUserFavoutiteLinks(List<UserSettingsVO> userFavoutiteLinks) {
		this.userFavoutiteLinks = userFavoutiteLinks;
	}

	public IUserProfileService getUserProfileService() {
		return userProfileService;
	}

	public void setUserProfileService(IUserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}

	public List<UserProfileVO> getSubscriptionsList() {
		return subscriptionsList;
	}

	public void setSubscriptionsList(List<UserProfileVO> subscriptionsList) {
		this.subscriptionsList = subscriptionsList;
	}

	public String getNotLogged() {
		return notLogged;
	}

	public void setNotLogged(String notLogged) {
		this.notLogged = notLogged;
	}

	public IProblemManagementService getProblemManagementService() {
		return problemManagementService;
	}

	public void setProblemManagementService(
			IProblemManagementService problemManagementService) {
		this.problemManagementService = problemManagementService;
	}

	public String getProfileUserName() {
		return profileUserName;
	}

	public void setProfileUserName(String profileUserName) {
		this.profileUserName = profileUserName;
	}

	public List<ProblemBeanVO> getProblemList() {
		return problemList;
	}

	public void setProblemList(List<ProblemBeanVO> problemList) {
		this.problemList = problemList;
	}

	public List<RegistrationVO> getRegistrationVOList() {
		return registrationVOList;
	}

	public void setRegistrationVOList(List<RegistrationVO> registrationVOList) {
		this.registrationVOList = registrationVOList;
	}

	public List<ProblemBeanVO> getStreamList() {
		return streamList;
	}

	public void setStreamList(List<ProblemBeanVO> streamList) {
		this.streamList = streamList;
	}

	public Boolean getLogInStatus() {
		return logInStatus;
	}

	public void setLogInStatus(Boolean logInStatus) {
		this.logInStatus = logInStatus;
	}
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public String getConnectStatus() {
		return connectStatus;
	}

	public void setConnectStatus(String connectStatus) {
		this.connectStatus = connectStatus;
	}

	public String execute()
	{
		String userStatusType = null;
		session = request.getSession();
		if(profileId == null){
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		if(user == null || ((RegistrationVO)user).getRegistrationID() == null){
			log.error(" No User Log In .....");			
			return "error";
		}
		
		
		
		 List<Long> userId = new ArrayList<Long>(0);
		 
		 loginUserId = user.getRegistrationID();
		
		 loginUserName = user.getFirstName()+" "+user.getLastName();
		 loginUserProfilePic = ananymousUserService.getUserProfileImageByUserId(loginUserId);
		 
		 userId.add(loginUserId);
		 
		 
		 dataTransferVO = ananymousUserService.getDataForAUserProfile(userId,IConstants.COMPLETE_DETAILS);
		//For Both roles
	   		if(user !=null)
	   		{
	   	 
			
	   	 if(session.getAttribute(IWebConstants.PARTY_ANALYST_USER_ROLE) != null && (Boolean)session.getAttribute(IWebConstants.PARTY_ANALYST_USER_ROLE))
			userStatusType = IConstants.PARTY_ANALYST_USER;
	   	dataTransferVO.setUserStatusType(userStatusType);
	   	 if(session.getAttribute(IWebConstants.FREE_USER_ROLE) != null && (Boolean)session.getAttribute(IWebConstants.FREE_USER_ROLE))
			userStatusType = IConstants.FREE_USER;
	   	dataTransferVO.setUserStatusType(userStatusType);
		  if(session.getAttribute(IWebConstants.FREE_USER_ROLE) != null && session.getAttribute(IWebConstants.PARTY_ANALYST_USER_ROLE) != null && (Boolean)session.getAttribute(IWebConstants.FREE_USER_ROLE) && (Boolean)session.getAttribute(IWebConstants.PARTY_ANALYST_USER_ROLE))
			userStatusType = IConstants.BOTH;
		  dataTransferVO.setUserStatusType(userStatusType);
	   		}
	      else{
	        userStatusType = IConstants.NOT_LOGGED_IN;
	        dataTransferVO.setUserStatusType(userStatusType);
			}
	   		
	   	 if(user!=null && entitlementsHelper.checkForEntitlementToViewReport(user, IConstants.NEWS_MONITORING_ENTITLEMENT)){
	        	hasNewsMonitoring = true;
	        	//fileVOList = candidateDetailsService.getNewsGalleryByUserIdFromUserGallery(user.getRegistrationID());
	        }
	             
	       if(user != null && entitlementsHelper.checkForEntitlementToViewReport(user, IConstants.ADD_SUBUSER_ENTITLEMENT)){
	    	   hasSubUserEntitlement = true;
	       }
	       if(user != null && entitlementsHelper.checkForEntitlementToViewReport(user, IConstants.CALL_CENTER_ENTITLEMENT)){
	    	   hasCallCenterEntitlment = true;
	       }
	       if(user != null && entitlementsHelper.checkForEntitlementToViewReport(user, IConstants.PROFILE_MANAGEMENT_ENTITLEMENT))
	    	   hasProfileManagement = true;
			messageTypes = ananymousUserService.getAllMessageTypes();
			
			if(dataTransferVO.getDistrictId() != null)
				constituenciesStatusVO = staticDataService.getConstituenciesWinnerInfo(dataTransferVO.getDistrictId());
			
			specialPageVOList = specialPageService.getSpecialPageListForHomePage();
			cadreManagementVO = userCadreManagementService.getUserData(user);
			if(cadreManagementVO!=null && cadreManagementVO.getExceptionEncountered()!=null)
				log.error(cadreManagementVO.getExceptionEncountered().getMessage());
		return Action.SUCCESS;
	  }else{

 
		  RegistrationVO regVO = (RegistrationVO)session.getAttribute("USER");
		  if(regVO != null)
		  {
			  logInStatus = true;
			  loginUserId = regVO.getRegistrationID();
			  List<Long> profileIdList = new ArrayList<Long>(0);
			  profileIdList.add(profileId);
			  List<Long> userIdList = new ArrayList<Long>(0);
			  userIdList.add(loginUserId);
			  
			  if(profileId.equals(regVO.getRegistrationID()))
			  {
				  userType = "LoggedUser";
				  connectStatus = "LoggedUser";
			  }
			  else
			  {
				  userType = "OtherUser";
				  connectStatus = userProfileService.getUserConnectStatus(profileId,loginUserId);
			  }
			
		  }
		  else
		  {
			  logInStatus = false;
			  userType = "OtherUser";
			  connectStatus = "false";
		  }
		  
		  
			 List<Long> userId = new ArrayList<Long>(0);
			 loginUserProfilePic = ananymousUserService.getUserProfileImageByUserId(profileId);
			 
			 userId.add(profileId);
			 
			 problemBeanVOList = problemManagementService.getProblemDetailsForProfilePage(0,10);
			 
			 
			 dataTransferVO = ananymousUserService.getDataForAUserProfile(userId,IConstants.COMPLETE_DETAILS);
				
				messageTypes = ananymousUserService.getAllMessageTypes();
				
				if(dataTransferVO.getDistrictId() != null)
					constituenciesStatusVO = staticDataService.getConstituenciesWinnerInfo(dataTransferVO.getDistrictId());
				
				specialPageVOList = specialPageService.getSpecialPageListForHomePage();
				
				profileUserName = specialPageService.getProfileUserName(profileId); 
					
				registrationVOList = ananymousUserService.getFriendsListForUserProfile(profileId);
				
				problemList = problemManagementService.getProblemDetailsByProfileId(profileId,0,10);
				
				
			return "publicprofile";
	  }
	}
	
	public String getRequestMessagesForUser()
	{
		String param;
		param = getTask();
		
		try{
			jObj = new JSONObject(param);	
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getRequestMessagesForUser() Method,Exception is- "+e);
		}
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		String startIndex    = request.getParameter("startIndex");
		String resultsCount  = request.getParameter("resultsCount");
		
		if(user==null){
			return "error";
		}
		List<Long> userId = new ArrayList<Long>();
		userId.add(user.getRegistrationID());
		dataTransferVO = ananymousUserService.getAllMessagesForLoggedUser(userId,IConstants.COMMENTS, Integer.parseInt(startIndex), Integer.parseInt(resultsCount));
		return Action.SUCCESS;
				
	}
	
	public String getSentBoxMessagesForUser()
	{
		String param;
		param = getTask();
		
		try{
			jObj = new JSONObject(param);	
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getRequestMessagesForUser() Method,Exception is- "+e);
		}
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		if(user==null){
			return IConstants.NOT_LOGGED_IN;
		}
		String startIndex    = request.getParameter("startIndex");
		String resultsCount  = request.getParameter("resultsCount");
		List<Long> userId = new ArrayList<Long>();
		userId.add(user.getRegistrationID());
		dataTransferVO = ananymousUserService.getAllSentMessagesForLoggedUser(userId,IConstants.COMMENTS, Integer.parseInt(startIndex), Integer.parseInt(resultsCount));
		return Action.SUCCESS;
				
	}
	
	public String getSpecialPages()
	{
		String param;
		param = getTask();
		try{
			jObj = new JSONObject(param);
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getSpecialPages() method, Exception- "+e);
		}
		specialPageVOList = specialPageService.getAllSpecialPageListForHomePage();
		return Action.SUCCESS;
	}
	
	
	public String getUserSubScription()
	{
		String param;
		param = getTask();
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		try{
			jObj = new JSONObject(param);
		
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getUserSubScription() Method, Exception- "+e);
		}
		//subscriptionsMainVO = specialPageService.getAllProfileSubScriptions(user.getRegistrationID(), jObj.getLong("profileId"));
		
		subscriptionsMainVO = specialPageService.getUserProfileSubScriptions(user.getRegistrationID());
		
		return Action.SUCCESS;
	}
	
	public String AjaxHandler()
	{
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		if(user == null)
		{
			return "error" ;
		}	
		List<Long> userId = new ArrayList<Long>(0);
		if(jObj.getString("task").equalsIgnoreCase("getLatestFriendsList"))
		{
			userId.add(user.getRegistrationID());
			connectedUsers = ananymousUserService.getAllPeopleConnectedPeopleForUser(userId);
		}
		else if(jObj.getString("task").equalsIgnoreCase("getAllRequestMessagesForUser"))
		{
			userId.add(user.getRegistrationID());
			userDetails = ananymousUserService.getDataForAUserProfile(userId,IConstants.FRIEND_REQUEST);
		}
		return Action.SUCCESS;
	}
	

	//political comments
	public String getPostedReasonsDataForProfilePage()
	{
		String param;
		param = getTask();
		
		try{
			jObj = new JSONObject(param);	
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getPostedReasonsDataForProfilePage() Method,Exception is- "+e);
		}
		Integer startIndex = jObj.getInt("startIndex");
		Integer results = jObj.getInt("maxIndex");
		String order = request.getParameter("dir");
		String columnName = request.getParameter("sort");
		String type = request.getParameter("type");
		String reasonType = "";
			
		if(IConstants.TOTAL.equalsIgnoreCase(type))
			reasonType = IConstants.TOTAL;
		else if (IConstants.LOGGED_USER.equalsIgnoreCase(type))
			reasonType = IConstants.LOGGED_USER;
		else if (IConstants.OTHERUSERS.equalsIgnoreCase(type))
			reasonType = IConstants.OTHERUSERS;
		else if (IConstants.APPROVED.equalsIgnoreCase(type))
			reasonType = IConstants.APPROVED;
		else if (IConstants.REJECTED.equalsIgnoreCase(type)) 
			reasonType = IConstants.REJECTED;
		else if (IConstants.NOTCONSIDERED.equalsIgnoreCase(type))
			reasonType = IConstants.NOTCONSIDERED;
		else if("ConnectedUserPoliticalReasons".equalsIgnoreCase(type))
			reasonType = "ConnectedUserPoliticalReasons";
		
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		if(user==null){
			return IConstants.NOT_LOGGED_IN;
		}
		userComments = ananymousUserService.getAllPostedReasonsByUserId(user.getRegistrationID(), startIndex, results, order, columnName, reasonType);
		//candidateCommentsVO = ananymousUserService.getAllPostedReasonsByUserId(user.getRegistrationID());
		
		return Action.SUCCESS;
	}
	
	
	
	public String getTotalSettingsOptionsOfAnUser(){
		
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
	
		userSettingsList = ananymousUserService.getTotalSettingsOptionsOfAnUser(user.getRegistrationID());
		
		return Action.SUCCESS;
		
	}
	
	
	public String updateUserSettingsDetailsAction(){
		
		String param;
		param = getTask();
		
		try{
			jObj = new JSONObject(param);	
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getRequestMessagesForUser() Method,Exception is- "+e);
		}
		
		
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		status = ananymousUserService.updateUserSettingsDetailsAction(jObj.getLong("selectedValue"),user.getRegistrationID());;
		
		
		return Action.SUCCESS;
		
		
	}
	
	
	
	
		
	public String getPostedProblemsForProfilePage()
	{		
		
		String param;
		param = getTask();
		
		try{
			jObj = new JSONObject(param);	
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getPostedProblemsForProfilePage() Method,Exception is- "+e);
		}
		Integer startIndex = jObj.getInt("startIndex");
		Integer results = jObj.getInt("maxIndex");
		String order = request.getParameter("dir");
		String columnName = request.getParameter("sort");
		String type = request.getParameter("type");
		String reasonType = "";
		
		if(IConstants.TOTAL.equalsIgnoreCase(type))
			reasonType = IConstants.TOTAL;
		else if (IConstants.LOGGED_USER.equalsIgnoreCase(type))
			reasonType = IConstants.LOGGED_USER;
		else if (IConstants.OTHERUSERS.equalsIgnoreCase(type))
			reasonType = IConstants.OTHERUSERS;
		else if (IConstants.APPROVED.equalsIgnoreCase(type))
			reasonType = IConstants.APPROVED;
		else if (IConstants.REJECTED.equalsIgnoreCase(type)) 
			reasonType = IConstants.REJECTED;
		else if (IConstants.NOTCONSIDERED.equalsIgnoreCase(type))
			reasonType = IConstants.NOTCONSIDERED;
		else if("ConnectedUserProblems".equalsIgnoreCase(type))
			reasonType = "ConnectedUserProblems";
		
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		if(user==null){
			return IConstants.NOT_LOGGED_IN;
		}
		
		problemDetailsVO = ananymousUserService.getAllPostedProblemsByUserId(user.getRegistrationID(), 
				startIndex, results, order, columnName, reasonType);
		//problemBeanVOList = ananymousUserService.getProblemDetailsForProfilePage(user.getRegistrationID(), startIndex, results, order, columnName, reasonType);
		return Action.SUCCESS;
	}
	
	
	public String getFavouriteLinksAction(){
		String param;
		String returnString = "";
		param = getTask();
		
		try{
			jObj = new JSONObject(param);	
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in removeFavouriteLinkAction() Method,Exception is- "+e);
		}
		
		session = request.getSession();
		
		
		String task=jObj.getString("task");
		if(task.equalsIgnoreCase("forAllFavLinks")){
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			userFavoutiteLinks = ananymousUserService.getUserFavAllLinksAction(user.getRegistrationID());
			returnString= "favLinks";
		}
		else if (task.equalsIgnoreCase("getFavouriteLinks")){
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			userFavoutiteLinks = ananymousUserService.getUserFavouriteLinksAction(user.getRegistrationID());
			returnString=Action.SUCCESS;
		}
		return returnString;
	}
	
	
	public String removeFavouriteLinkAction(){
		
		String param;
		param = getTask();
		
		try{
			jObj = new JSONObject(param);	
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in removeFavouriteLinkAction() Method,Exception is- "+e);
		}
		
		Long favouriteLinkId = jObj.getLong("linkId");
		status = ananymousUserService.removeFavouriteLink(favouriteLinkId);
		
		return Action.SUCCESS;
		
	}
	
	
	public String saveUserFavouriteLink(){
		
		String param;
		param = getTask();
		
		try{
			jObj = new JSONObject(param);	
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in saveUserFavouriteLink() Method,Exception is- "+e);
		}
		
		String link = jObj.getString("link");
		String pageTitle = jObj.getString("pageTitle");
		String queryString = jObj.getString("queryString");
		String environment = jObj.getString("environment");
		
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		
		status = ananymousUserService.saveUserFavouriteLink( user.getRegistrationID(), link, pageTitle , queryString , environment);
		
		return Action.SUCCESS;
		
		
	}
	

	
	public String getSubscriptions()
	{
		String param;
		param = getTask();
		try{
			session = request.getSession();
			
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			
			if(user == null || ((RegistrationVO)user).getRegistrationID() == null){
				log.error(" No User Log In .....");			
				return "error";
			}
			jObj = new JSONObject(param);
			if(jObj.getString("type").equalsIgnoreCase("allsubscriptions")){
				int count = 0;
				DateUtilService dateUtilService = new DateUtilService();
				Date toDate = dateUtilService.getCurrentDateAndTime();
				Calendar cal = Calendar.getInstance();
				cal.setTime(toDate);
				cal.add(Calendar.DATE, -7);
				Date fromDate = cal.getTime();
				session.setAttribute("fromDate", fromDate);
				session.setAttribute("toDate", toDate);
				while(count < 10){
				 if(subscriptionsList == null || subscriptionsList.size() == 0){
					 Date newFromDate = null;
					 Date newToDate = null;
					 if(count == 0){
						 newFromDate = fromDate;
						 newToDate = toDate;
					 }else{
						 cal = Calendar.getInstance();
						 cal.setTime(fromDate);
						 cal.add(Calendar.DATE, -7);
						 newFromDate = cal.getTime();
						 newToDate = fromDate;
						 fromDate = newFromDate;
						 session.setAttribute("fromDate", fromDate);
					 }
				     subscriptionsList = userProfileService.getPartyAnalystLatestUpdates(newFromDate,newToDate,user.getRegistrationID());
				   count = count+1;
				 }else{
					 break;
				 }
				}
			 }else if(jObj.getString("type").equalsIgnoreCase("latestsubscriptions")){
				Date toDate = (Date)session.getAttribute("toDate");
				DateUtilService dateUtilService = new DateUtilService();
				Date newToDate = dateUtilService.getCurrentDateAndTime();
				session.setAttribute("toDate",newToDate);
				subscriptionsList = userProfileService.getPartyAnalystLatestUpdates(toDate,newToDate,user.getRegistrationID());
			}else if(jObj.getString("type").equalsIgnoreCase("oldersubscriptions")){
				int count = 0;
				while(count < 20){
				 if(subscriptionsList == null || subscriptionsList.size() == 0){
				   Date fromDate = (Date)session.getAttribute("fromDate");
				   Calendar cal = Calendar.getInstance();
				   cal.setTime(fromDate);
				   cal.add(Calendar.DATE, -2);
				   Date newFromDate = cal.getTime();
				   session.setAttribute("fromDate",newFromDate);
				   subscriptionsList = userProfileService.getPartyAnalystLatestUpdates(newFromDate,fromDate,user.getRegistrationID());
				   count = count+1;
				 }else{
					 break;
				 }
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getSubscriptions() method, Exception- "+e);
		}
		
		return Action.SUCCESS;
	}
	
	public String getFriendsList()
	{
		String param;
		param = getTask();
		try{
			jObj = new JSONObject(param);	
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occured in getFriendsList() Method, Exception - " +e); 
		}
		session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		if(user == null)
			return "error" ;
		List<Long> userId = new ArrayList<Long>(0);
		userId.add(user.getRegistrationID());
		dataTransferVO = ananymousUserService.getDataForAUserProfile(userId,IConstants.COMPLETE_DETAILS);
		return Action.SUCCESS;
	}
	
	public String getStreamingDataForPublicProfileByProfileId()
	{
		String param;
		param = getTask();
		try{
			jObj = new JSONObject(param);	
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occured in getStreamingDataForPublicProfileByProfileId() Method, Exception - " +e); 
		}
		streamList = userProfileService.getStreamingDataForPublicProfileByProfileId(jObj.getLong("profileId"),jObj.getInt("startIndex"),jObj.getInt("maxIndex"));
		return Action.SUCCESS;
	}
	
	
	public String connectToSelectedUser()
	{
		
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO)session.getAttribute("USER");
		if(regVO == null)
		{
			return "error";
		}
		String senderName = regVO.getFirstName()+" "+regVO.getLastName();
		String param;
		param = getTask();
		try{
			jObj = new JSONObject(param);	
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occured in connectToSelectedUser() Method, Exception - " +e); 
		}
		
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
		
		resultStatus = ananymousUserService.saveCommunicationDataBetweenUsers(senderIds, connectUserIdsList, IConstants.FRIEND_REQUEST, subject, senderName);
		return Action.SUCCESS;
	}

}
