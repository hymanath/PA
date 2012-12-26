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
	private String redirectLoc;
	private String task;
	JSONObject jObj;
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
	private List<ProblemBeanVO> problemBeanVOList,problemList;
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

	public List<ProblemBeanVO> getProblemList() {
		return problemList;
	}

	public void setProblemList(List<ProblemBeanVO> problemList) {
		this.problemList = problemList;
	}

	public String execute()
	{
		
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
	
	public String getProblemDetailsForPublicProfile()
	{
		try{
			 String param;
			 param = getTask();
			 jObj = new JSONObject(param);
			}catch (Exception e) {
			   log.error("Exception Occured in getProblemDetailsForPublicProfile(), Exception - "+e);
			}
			problemBeanVOList = problemManagementService.getProblemDetailsByProfileId(profileId,jObj.getInt("startIndex"),jObj.getInt("maxIndex"));
			return Action.SUCCESS;
	}
}

