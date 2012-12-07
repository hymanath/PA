package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ConstituenciesStatusVO;
import com.itgrids.partyanalyst.dto.DataTransferVO;
import com.itgrids.partyanalyst.dto.NavigationVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SpecialPageVO;
import com.itgrids.partyanalyst.dto.SubscriptionsMainVO;
import com.itgrids.partyanalyst.dto.UserCommentsInfoVO;
import com.itgrids.partyanalyst.service.IAnanymousUserService;
import com.itgrids.partyanalyst.service.IProblemManagementService;
import com.itgrids.partyanalyst.service.ISpecialPageService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class PublicProfileAction extends ActionSupport implements ServletRequestAware {

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
	private ResultStatus resultStatus;
	private DataTransferVO userDetails;
	private DataTransferVO dataTransferVO,connectedUsers;
	private NavigationVO messageTypes;
	private ConstituenciesStatusVO constituenciesStatusVO;
	private ISpecialPageService specialPageService;
	private List<SpecialPageVO> specialPageVOList;
	private Long profileId;
	private UserCommentsInfoVO userComments;
	private ProblemBeanVO problemDetailsVO;
	private SubscriptionsMainVO subscriptionsMainVO;
	private String profileUserName;
	private List<ProblemBeanVO> problemBeanVOList;
	private IProblemManagementService problemManagementService;
	private List<RegistrationVO> registrationVOList;
	
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
	
	public String getProfileUserName() {
		return profileUserName;
	}

	public void setProfileUserName(String profileUserName) {
		this.profileUserName = profileUserName;
	}

	public List<ProblemBeanVO> getProblemBeanVOList() {
		return problemBeanVOList;
	}

	public void setProblemBeanVOList(List<ProblemBeanVO> problemBeanVOList) {
		this.problemBeanVOList = problemBeanVOList;
	}

	public IProblemManagementService getProblemManagementService() {
		return problemManagementService;
	}

	public void setProblemManagementService(
			IProblemManagementService problemManagementService) {
		this.problemManagementService = problemManagementService;
	}

	public List<RegistrationVO> getRegistrationVOList() {
		return registrationVOList;
	}

	public void setRegistrationVOList(List<RegistrationVO> registrationVOList) {
		this.registrationVOList = registrationVOList;
	}

	public String execute()
	{
		
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		 List<Long> userId = new ArrayList<Long>(0);
		 if(user !=null)
		 {
			 loginUserId = user.getRegistrationID();
			 loginUserName = user.getFirstName()+" "+user.getLastName();
		 }
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
			
		return Action.SUCCESS;
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
		if(user==null){
			return IConstants.NOT_LOGGED_IN;
		}
		List<Long> userId = new ArrayList<Long>();
		userId.add(jObj.getLong("profileId"));
		dataTransferVO = ananymousUserService.getAllMessagesForLoggedUser(userId,IConstants.COMMENTS);
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
		subscriptionsMainVO = specialPageService.getAllUserSubScriptions(user.getRegistrationID(), jObj.getLong("profileId"));
		
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
			return IConstants.NOT_LOGGED_IN ;
		}	
		List<Long> userId = new ArrayList<Long>(0);
		if(jObj.getString("task").equalsIgnoreCase("getLatestFriendsList"))
		{
			userId.add(jObj.getLong("profileId"));
			connectedUsers = ananymousUserService.getAllPeopleConnectedPeopleForUser(userId);
		}
		else if(jObj.getString("task").equalsIgnoreCase("getAllRequestMessagesForUser"))
		{
			userId.add(jObj.getLong("profileId"));
			userDetails = ananymousUserService.getDataForAUserProfile(userId,IConstants.FRIEND_REQUEST);
		}
		return Action.SUCCESS;
	}
	

	//political comments
	public String getAllPostedReasonsDataForProfilePage()
	{
		Integer startIndex = Integer.parseInt(request.getParameter("startIndex"));
		Integer results = Integer.parseInt(request.getParameter("resultsCount"));
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
		
		
		
		
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		userComments = ananymousUserService.getAllPostedReasonsByUserId(jObj.getLong("profileId"), startIndex, results, order, columnName, reasonType);
		//candidateCommentsVO = ananymousUserService.getAllPostedReasonsByUserId(user.getRegistrationID());
		
		return Action.SUCCESS;
	}
	
	
	//get problems
	
	public String getAllPostedProblemsForProfilePage()
	{		
		Integer startIndex = Integer.parseInt(request.getParameter("startIndex"));
		Integer results = Integer.parseInt(request.getParameter("resultsCount"));
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
		
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		if(user==null){
			return IConstants.NOT_LOGGED_IN;
		}
		
		problemDetailsVO = ananymousUserService.getAllPostedProblemsByUserId(jObj.getLong("profileId"), 
				startIndex, results, order, columnName, reasonType);
		return Action.SUCCESS;
	}
	
	

}

