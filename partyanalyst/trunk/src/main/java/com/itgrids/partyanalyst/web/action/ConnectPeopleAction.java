package com.itgrids.partyanalyst.web.action;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.RequestUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.imgscalr.Scalr;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CandidateCommentsVO;
import com.itgrids.partyanalyst.dto.CandidateVO;
import com.itgrids.partyanalyst.dto.ConstituenciesStatusVO;
import com.itgrids.partyanalyst.dto.DataTransferVO;
import com.itgrids.partyanalyst.dto.EmailDetailsVO;
import com.itgrids.partyanalyst.dto.NavigationVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.ProblemDetailsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.UserCommentsInfoVO;
import com.itgrids.partyanalyst.service.IAnanymousUserService;
import com.itgrids.partyanalyst.service.IMailService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.IThumbnailService;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ConnectPeopleAction extends ActionSupport implements ServletRequestAware, ServletContextAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5360785727347677445L;

	private static final Logger log = Logger.getLogger(ConnectPeopleAction.class);
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	private String redirectLoc;
	private String districtId;
	private String districtName;
	private String constituencyId;
	private String constituencyName;
	private String task;
	JSONObject jObj;
	private List<CandidateVO> candidates;
	private IAnanymousUserService ananymousUserService;
	private ResultStatus resultStatus;
	private DataTransferVO userDetails;
	private DataTransferVO dataTransferVO,connectedUsers;
	private Long loginUserId;
	private String message;	
	private List<CandidateCommentsVO> candidateCommentsVO;
	private ProblemBeanVO problemDetailsVO;
	private NavigationVO messageTypes;
	private ConstituenciesStatusVO constituenciesStatusVO;
	private IStaticDataService staticDataService;
	private CandidateCommentsVO commentVO;
	private UserCommentsInfoVO userComments;
	private String loginUserName;
	private ProblemDetailsVO problemDetails;
	private ServletContext context;
	
	private File upload;//The actual file
    private String uploadContentType; //The content type of the file
    private String uploadFileName;  
    private ResultStatus uploadStatus;
    private String photoUploadStatus;
    private InputStream inputStream;
    private String loginUserProfilePic;
    private String pwdVal;
    private IMailService mailService;
    private String xcoardinate;
    private String ycoardinate;
    private String width;
    private String height;
    private InputStream inputErrorStream;
    private  IThumbnailService thumbnailService;
    
    private String coverImg;
	
   	public String getCoverImg() {
		return coverImg;
	}

	public void setCoverImg(String coverImg) {
		this.coverImg = coverImg;
	}
    
   	public IThumbnailService getThumbnailService() {
   		return thumbnailService;
   	}

   	public void setThumbnailService(IThumbnailService thumbnailService) {
   		this.thumbnailService = thumbnailService;
   	}
    public String getXcoardinate() {
		return xcoardinate;
	}


	public void setXcoardinate(String xcoardinate) {
		this.xcoardinate = xcoardinate;
	}


	public String getYcoardinate() {
		return ycoardinate;
	}


	public void setYcoardinate(String ycoardinate) {
		this.ycoardinate = ycoardinate;
	}


	public String getWidth() {
		return width;
	}


	public void setWidth(String width) {
		this.width = width;
	}


	public String getHeight() {
		return height;
	}


	public void setHeight(String height) {
		this.height = height;
	}


	public IMailService getMailService() {
		return mailService;
	}


	public void setMailService(IMailService mailService) {
		this.mailService = mailService;
	}


	public String getPwdVal() {
		return pwdVal;
	}


	public void setPwdVal(String pwdVal) {
		this.pwdVal = pwdVal;
	}


	public String getLoginUserProfilePic() {
		return loginUserProfilePic;
	}


	public void setLoginUserProfilePic(String loginUserProfilePic) {
		this.loginUserProfilePic = loginUserProfilePic;
	}


	public InputStream getInputStream() {
        return inputStream;
    }

	
	public String getPhotoUploadStatus() {
		return photoUploadStatus;
	}

	public void setPhotoUploadStatus(String photoUploadStatus) {
		this.photoUploadStatus = photoUploadStatus;
	}

	public ResultStatus getUploadStatus() {
		return uploadStatus;
	}

	public void setUploadStatus(ResultStatus uploadStatus) {
		this.uploadStatus = uploadStatus;
	}

	public ServletContext getContext() {
		return context;
	}	
	
	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public DataTransferVO getConnectedUsers() {
		return connectedUsers;
	}

	public void setConnectedUsers(DataTransferVO connectedUsers) {
		this.connectedUsers = connectedUsers;
	}

	public ProblemDetailsVO getProblemDetails() {
		return problemDetails;
	}

	public void setProblemDetails(ProblemDetailsVO problemDetails) {
		this.problemDetails = problemDetails;
	}

	public String getLoginUserName() {
		return loginUserName;
	}

	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}

	public UserCommentsInfoVO getUserComments() {
		return userComments;
	}

	public void setUserComments(UserCommentsInfoVO userComments) {
		this.userComments = userComments;
	}

	public CandidateCommentsVO getCommentVO() {
		return commentVO;
	}

	public void setCommentVO(CandidateCommentsVO commentVO) {
		this.commentVO = commentVO;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public ConstituenciesStatusVO getConstituenciesStatusVO() {
		return constituenciesStatusVO;
	}

	public void setConstituenciesStatusVO(
			ConstituenciesStatusVO constituenciesStatusVO) {
		this.constituenciesStatusVO = constituenciesStatusVO;
	}

	public NavigationVO getMessageTypes() {
		return messageTypes;
	}

	public void setMessageTypes(NavigationVO messageTypes) {
		this.messageTypes = messageTypes;
	}

	public ProblemBeanVO getProblemDetailsVO() {
		return problemDetailsVO;
	}

	public void setProblemDetailsVO(ProblemBeanVO problemDetailsVO) {
		this.problemDetailsVO = problemDetailsVO;
	}

	public List<CandidateCommentsVO> getCandidateCommentsVO() {
		return candidateCommentsVO;
	}

	public void setCandidateCommentsVO(List<CandidateCommentsVO> candidateCommentsVO) {
		this.candidateCommentsVO = candidateCommentsVO;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public Long getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(Long loginUserId) {
		this.loginUserId = loginUserId;
	}

	public DataTransferVO getDataTransferVO() {
		return dataTransferVO;
	}

	public void setDataTransferVO(DataTransferVO dataTransferVO) {
		this.dataTransferVO = dataTransferVO;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(String constituencyId) {
		this.constituencyId = constituencyId;
	}

	public String getConstituencyName() {
		return constituencyName;
	}

	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}

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
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		if(user == null || ((RegistrationVO)user).getRegistrationID() == null){
			log.error(" No User Log In .....");			
			return IConstants.NOT_LOGGED_IN;
		}
		
		if(session.getAttribute(IWebConstants.PARTY_ANALYST_USER_ROLE) !=null && session.getAttribute(IWebConstants.PARTY_ANALYST_USER_ROLE).equals(true) && 
				ananymousUserService.getDetailsOfUserByUserId(user.getRegistrationID()) == null)
			return ERROR;
		
		/* if(!session.getAttribute(IWebConstants.FREE_USER_ROLE).equals(true))
			return ERROR;*/
			
		List<Long> userId = new ArrayList<Long>(0);
		loginUserId = user.getRegistrationID();
		loginUserName = user.getFirstName()+" "+user.getLastName();
		loginUserProfilePic = ananymousUserService.getUserProfileImageByUserId(user.getRegistrationID());
		/*if(user.getUserProfilePic() != null || user.getUserProfilePic() != "")
			loginUserProfilePic = "pictures\\"+IConstants.PROFILE_PIC+"\\"+user.getUserProfilePic();*/
				
		userId.add(loginUserId);
		
		dataTransferVO = ananymousUserService.getDataForAUserProfile(userId,IConstants.COMPLETE_DETAILS);
		
		messageTypes = ananymousUserService.getAllMessageTypes();
		
		if(dataTransferVO.getDistrictId() != null)
			constituenciesStatusVO = staticDataService.getConstituenciesWinnerInfo(dataTransferVO.getDistrictId());	
		
		return Action.SUCCESS;
		
	}
	
	public String getConnectedPeopleForUser()
	{
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		List<Long> userId = new ArrayList<Long>(0);
		userId.add(user.getRegistrationID());
		if(user == null)
		{
			return IConstants.NOT_LOGGED_IN ;
		}		
		connectedUsers = ananymousUserService.getAllPeopleConnectedPeopleForUser(userId);
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
		String senderName = user.getFirstName() +" "+user.getLastName();
		List<Long> userId = new ArrayList<Long>();
		userId.add(user.getRegistrationID());
		
		List<Long> recepientId = new ArrayList<Long>();
		recepientId.add(jObj.getLong("recepientId"));
		
		
		if(jObj.getString("task").equalsIgnoreCase("acceptRequest"))
		{
			resultStatus = ananymousUserService.saveCommunicationDataBetweenUsers(userId,recepientId,IConstants.CONNECTED,"",senderName);
		}
		if(jObj.getString("task").equalsIgnoreCase("rejectRequest"))
		{
			resultStatus =  ananymousUserService.saveCommunicationDataBetweenUsers(userId,recepientId,IConstants.REJECTED,"",senderName);
		}
		if(jObj.getString("task").equalsIgnoreCase("blockRequest"))
		{
			resultStatus =  ananymousUserService.saveCommunicationDataBetweenUsers(userId,recepientId,IConstants.BLOCK,"",senderName);
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
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO)session.getAttribute("USER");
		if(regVO == null)
		{
			return IConstants.NOT_LOGGED_IN;
		}
		String senderName = regVO.getFirstName()+" "+regVO.getLastName();
		try {
			jObj = new JSONObject(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Long locationId = new Long(jObj.getString("locationId"));
		Long connectUserId = new Long(jObj.getString("connectUserId"));
		Long userId = new Long(jObj.getString("userId"));
		String subject = jObj.getString("connectMessage");
		String locationType = jObj.getString("locationType");
		
		String locationTypeConst = null;
		if(locationType.equalsIgnoreCase("DISTRICT"))
			locationTypeConst = IConstants.DISTRICT_LEVEL;
		else if(locationType.equalsIgnoreCase("CONSTITUENCY"))
			locationTypeConst = IConstants.CONSTITUENCY_LEVEL;
			
		List<Long> locationIds = new ArrayList<Long>();
		locationIds.add(locationId);
		
		List<Long> senderIds = new ArrayList<Long>();
		senderIds.add(userId);
		
		List<Long> connectUserIds = new ArrayList<Long>(); 
		connectUserIds.add(connectUserId);
		
		//resultStatus = ananymousUserService.saveCommunicationDataBetweenUsers(senderIds, connectUserIds, IConstants.FRIEND_REQUEST, subject);
		userDetails = ananymousUserService.getAllUsersAfterAcceptingRequest(locationIds, locationTypeConst, IConstants.MAX_ANONYMOUS_USER_DISPLAY, userId, senderIds, connectUserIds, IConstants.FRIEND_REQUEST, subject,senderName);
		return Action.SUCCESS;
	}
	
	public String changeUserPassword()
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
		pwdVal=ananymousUserService.changeUserPassword(jObj.getString("crntPassword"),jObj.getString("newPassword"),user.getRegistrationID(),user.getEmail());
		
		return Action.SUCCESS;
	}
	
	/*public String changeUserPassword()
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
		String email = (String) session.getAttribute("userName");
		pwdVal=ananymousUserService.changeUserPassword(jObj.getString("crntPassword"),jObj.getString("newPassword"),email);
		
		return Action.SUCCESS;
	}*/
	
	public String getAllConnectedUsersInDistrict() throws Exception
	{
		String param;
		param = getTask();
		
		
		try {
			jObj = new JSONObject(param);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	
		 Long startIndex = Long.parseLong(request.getParameter("startIndex"));
		 Long results = Long.parseLong(request.getParameter("resultsCount"));
		Long userId = 0l;
		String loginId = jObj.getString("userId");
		Long locationId = new Long(jObj.getString("locationId"));
		if(!loginId.equalsIgnoreCase("")){
		userId = new Long(loginId);
		}
		String locationType = jObj.getString("locationType");
		String nameString = ""; 
		String locationTypeConst = null;
		if(locationType.equalsIgnoreCase("DISTRICT"))
			locationTypeConst = IConstants.DISTRICT_LEVEL;
		else if(locationType.equalsIgnoreCase("CONSTITUENCY"))
			locationTypeConst = IConstants.CONSTITUENCY_LEVEL;
		
		List<Long> listOfDistricts = new ArrayList<Long>();
		listOfDistricts.add(locationId);
		
		//userDetails = ananymousUserService.getAllRegisteredAnonymousUserBasedOnLocationAndStatus(listOfDistricts,locationTypeConst,results,userId,IConstants.ALL,startIndex,nameString);
		userDetails = ananymousUserService.getAllConnectedUsersBasedonLocationType(listOfDistricts,locationTypeConst,results,userId,IConstants.ALL,startIndex,nameString);
		
		return Action.SUCCESS;
	}
	
	public String connectToUserSet() throws Exception
	{
		String param;
		param = getTask();
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO)session.getAttribute("USER");
		if(regVO == null)
		{
			return IConstants.NOT_LOGGED_IN;
		}
		String senderName = regVO.getFirstName()+" "+regVO.getLastName();
		try {
			jObj = new JSONObject(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Long districtId = new Long(jObj.getString("connetLocationId"));		
		Long userId = new Long(jObj.getString("userId"));
		String subject = jObj.getString("connectMessage");
		String locationType = jObj.getString("locationType");
		
		String locationTypeConst = null;
		if(locationType.equalsIgnoreCase("DISTRICT"))
			locationTypeConst = IConstants.DISTRICT_LEVEL;
		else if(locationType.equalsIgnoreCase("CONSTITUENCY"))
			locationTypeConst = IConstants.CONSTITUENCY_LEVEL;
		
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
		userDetails = ananymousUserService.getAllUsersAfterAcceptingRequest(locationIds, locationTypeConst, IConstants.ALL_CONNECTED_USER_DISPLAY, userId, senderIds, connectUserIdsList, IConstants.FRIEND_REQUEST, subject,senderName);
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
		
		Long startIndex = Long.parseLong(request.getParameter("startIndex"));
		Long results = Long.parseLong(request.getParameter("resultsCount"));		
		
		String statusText = jObj.getString("statusText");
		String nameString  = jObj.getString("nameString");
		JSONArray constituencyIds = jObj.getJSONArray("constituencyIds");		
		List<Long> listOfConstituencies = new ArrayList<Long>();
		
		for(int i=0; i<constituencyIds.length();i++)
			listOfConstituencies.add(new Long(constituencyIds.getString(i)));
		
		if(user == null)
		{
   			userDetails = ananymousUserService.getAllRegisteredAnonymousUserBasedOnLocationAndStatus(listOfConstituencies,IConstants.CONSTITUENCY_LEVEL,results,0l,statusText,startIndex,nameString);	
   		}else
   		{
   			userDetails = ananymousUserService.getAllRegisteredAnonymousUserBasedOnLocationAndStatus(listOfConstituencies,IConstants.CONSTITUENCY_LEVEL,results,user.getRegistrationID(),statusText,startIndex,nameString);
   		}
   		
		return Action.SUCCESS;
	}
	
	public String sendMessageToConnectedUser()
	{
		String param;
		param = getTask();
		session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		if(user == null)
		{
			return IConstants.NOT_LOGGED_IN;
		}
		String senderName = user.getFirstName() +" "+user.getLastName();
		
		try {
			jObj = new JSONObject(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Long loginUserId = new Long(jObj.getString("loginUserId"));		
		Long recipientId = new Long(jObj.getString("recipientId"));
		String message = jObj.getString("message");		
		
		List<Long> senderIds = new ArrayList<Long>();
		senderIds.add(loginUserId);
		
		List<Long> recipientIds = new ArrayList<Long>();
		recipientIds.add(recipientId);
		
		if(jObj.getString("type").equalsIgnoreCase("Message")){
			resultStatus = ananymousUserService.saveCommunicationDataBetweenUsers(senderIds, recipientIds, IConstants.COMMENTS,message,senderName);
		}else if(jObj.getString("type").equalsIgnoreCase("Connect")){
			resultStatus = ananymousUserService.saveCommunicationDataBetweenUsers(senderIds, recipientIds,IConstants.FRIEND_REQUEST,message,senderName);
		}
		
		return Action.SUCCESS;
	}
	
	public String updateReadMessageStatusInDB(){
		String param;
		param = getTask();

		try {
			jObj = new JSONObject(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Long loginUserId = new Long(jObj.getString("customMasgId"));
		message = ananymousUserService.displayMessageAndUpdateUnread(loginUserId);
		return SUCCESS;
	}
	
	
	public String getPostedProblemsCount()
	{
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		if(user==null){
			return IConstants.NOT_LOGGED_IN;
		}
		
		problemDetails = ananymousUserService.getPostedProblemsCount(user.getRegistrationID());
		return Action.SUCCESS;
	}
	
	public String getAllPostedProblemsData()
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
		
		problemDetailsVO = ananymousUserService.getAllPostedProblemsByUserId(user.getRegistrationID(), 
				startIndex, results, order, columnName, reasonType);
		return Action.SUCCESS;
	}
	
	public String getAllPostedReasonsStatusUser()
	{		
		try {
			jObj = new JSONObject(getTask());			
		} catch (Exception e) {
			e.printStackTrace();
		}
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		if(user==null){
			return IConstants.NOT_LOGGED_IN;
		}
		
		commentVO = ananymousUserService.getAllPostedReasonsCountByUserId(user.getRegistrationID());
		
		return Action.SUCCESS;
	}
	
	public String getAllPostedReasonsData()
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
		
		userComments = ananymousUserService.getAllPostedReasonsByUserId(user.getRegistrationID(), startIndex, results, order, columnName, reasonType);
		//candidateCommentsVO = ananymousUserService.getAllPostedReasonsByUserId(user.getRegistrationID());
		
		return Action.SUCCESS;
	}
	
	
	public String uploadUserPic()
	{
		String coverImg= request.getParameter("coverImg");
		
		
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		String sPath = (String)session.getAttribute("imagePath");
		
		 String filePath = "";
		
		 String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
		 if(coverImg == null)
		 {
		 if(request.getRequestURL().toString().contains(IConstants.PARTYANALYST_SITE))
			filePath = IWebConstants.STATIC_CONTENT_FOLDER_URL + "pictures" + pathSeperator + IConstants.PROFILE_PIC + pathSeperator;
		 else
			 filePath = context.getRealPath("/")+"pictures\\"+IConstants.PROFILE_PIC+"\\";	
		 }
		 else if(coverImg != null && coverImg.equalsIgnoreCase("true"))
		 {
			 if(request.getRequestURL().toString().contains(IConstants.PARTYANALYST_SITE))
			 
					filePath = IWebConstants.STATIC_CONTENT_FOLDER_URL + "pictures" + pathSeperator + IConstants.COVER_PIC + pathSeperator;
			
				 else
				
					 filePath = context.getRealPath("/")+"pictures\\"+IConstants.COVER_PIC+"\\";
				
		 }
		BufferedImage image = null;
        try {
            
            image = ImageIO.read(this.upload);
           int t=   image.getType();
           int IMG_WIDTH =Integer.parseInt(width);
           int IMG_HEIGHT =Integer.parseInt(height);
           int x =Integer.parseInt(xcoardinate);
           int y =Integer.parseInt(ycoardinate);
               
         /*  BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT,t);
                Graphics2D g = resizedImage.createGraphics();
                g.drawImage(image, x, y, IMG_WIDTH, IMG_HEIGHT, null);
                g.dispose();
*/

 

          image=   Scalr.resize(image, Scalr.Method.ULTRA_QUALITY,Scalr.Mode.FIT_EXACT, 300, 300);	 
          BufferedImage resizedImage =Scalr.crop(image, x, y, IMG_WIDTH, IMG_HEIGHT);
 
                
            
            String constiName[] = uploadContentType.split("/");
            //String filePath = context.getRealPath("/")+"pictures\\"+IConstants.PROFILE_PIC+"\\";
            if(constiName[1].equalsIgnoreCase("jpeg") || !(constiName[1].equalsIgnoreCase("jpeg")))
            	constiName[1]="jpeg";
          
            String fileName = filePath+user.getRegistrationID()+"."+constiName[1];
            //String fileName = filePath+this.uploadFileName;
            String imageName =  user.getRegistrationID()+"."+constiName[1] ;
            uploadStatus = ananymousUserService.saveUserProfileImageName(user.getRegistrationID(), imageName);
                        
            if(uploadStatus.getResultCode() > 0)
            {
            	
            	try {
                	
                	    FileImageOutputStream filName = new FileImageOutputStream(new File(fileName));
    	
    	                log.info("Image Name :" + image);
    	                log.info("File :" + filName);
    	                ImageIO.write(resizedImage, constiName[1],filName);
    	                filName.close();
    	                
    	                photoUploadStatus = "true";
    	            	inputStream = new StringBufferInputStream("File Uploaded successfully");
    	            	String status=thumbnailService.crateThumnaiForProfiles(fileName,IWebConstants.STATIC_CONTENT_FOLDER_URL);
    	            	 if(status.equalsIgnoreCase("sucess"))
    	            		 inputStream = new StringBufferInputStream(" File Uploaded successfully As Thumb"); 
    	            	 else
    	            		 inputStream = new StringBufferInputStream(" File Uploaded successfully  Xthumb"); 

    			} catch (Exception e) {
    				inputStream = new StringBufferInputStream("File not uploaded due to failed saving file in machine but database is sucess");
    				log.error(e);
    			}
            	/*ImageIO.write(image, constiName[1],new File(fileName));
            	photoUploadStatus = "true";
            	inputStream = new StringBufferInputStream("File Uploaded successfully");*/

            }
            else
            {
            	photoUploadStatus = "false";
            	inputStream = new StringBufferInputStream("File not uploaded due to some exception");
            }
            	
           request.setAttribute("uploadStatus", photoUploadStatus);
 
        } catch (IOException e) {
			inputStream = new StringBufferInputStream("File not uploaded due to converting resize  file  ");

        	e.printStackTrace();
        }		
		
		return Action.SUCCESS;
	}
	

	public String candidateUploadUserPic()
	{
		session = request.getSession();
		String myPath=request.getParameter("param");
		String candidateName=request.getParameter("candidateName");
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		String sPath = (String)session.getAttribute("imagePath");
		
		 String filePath = "";
		 
		 String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
		
			 filePath=IWebConstants.STATIC_CONTENT_FOLDER_URL+"images\\candidates\\";
		
		BufferedImage image = null;
        try {
            
            image = ImageIO.read(this.upload);
            String constiName[] = uploadContentType.split("/");
            //String filePath = context.getRealPath("/")+"pictures\\"+IConstants.PROFILE_PIC+"\\";
           
            if(constiName[1].equalsIgnoreCase("jpeg") || !(constiName[1].equalsIgnoreCase("jpeg")))
                  	constiName[1]="jpg";
          
         
            String fileName = filePath+candidateName+"."+constiName[1];
            String  imageName =  candidateName+"."+constiName[1];
        
            uploadStatus = ananymousUserService.saveUserProfileImageName(user.getRegistrationID(), imageName);
                        
            if(uploadStatus.getResultCode() > 0)
            {
            	
            	try {
                	
                	    FileImageOutputStream filName = new FileImageOutputStream(new File(fileName));
    	
    	                log.info("Image Name :" + image);
    	                log.info("File :" + filName);
    	                ImageIO.write(image, constiName[1],filName);
    	                filName.close();
    	                
    	                photoUploadStatus = "true";
    	            	inputStream = new StringBufferInputStream("File Uploaded successfully");
                	

    			} catch (Exception e) {
    			
    				log.error(e);
    			}
            	/*ImageIO.write(image, constiName[1],new File(fileName));
            	photoUploadStatus = "true";
            	inputStream = new StringBufferInputStream("File Uploaded successfully");*/

            }
            else
            {
            	photoUploadStatus = "false";
            	inputStream = new StringBufferInputStream("File not uploaded due to some exception");
            }
            	
           request.setAttribute("uploadStatus", photoUploadStatus);
 
        } catch (IOException e) {
        	e.printStackTrace();
        }		
		
		return Action.SUCCESS;
	}
	
	public String referralSendingMails(){
		try{
			jObj = new JSONObject(getTask());	
			JSONArray mailDtls = jObj.getJSONArray("invmainobj");
			String requestFrom="";
			if(request.getRequestURL().toString().contains(IConstants.PARTYANALYST_SITE)){
				 requestFrom = IConstants.SERVER;	
	        }else{
	        	requestFrom = IConstants.LOCALHOST;		
	        }   
			 List<EmailDetailsVO> emaildtlslist=new ArrayList();
		
			 if(mailDtls.length()>0){
			for(int i=0;i<mailDtls.length();i++){
				EmailDetailsVO emaildtlsVo=new EmailDetailsVO();
				JSONObject mailobj=(JSONObject)mailDtls.get(i);	
				String fname=mailobj.getString("firstName");
				String email=mailobj.getString("email");
				emaildtlsVo.setToAddress(email);
				if(fname==null){
					fname="";
				}
				emaildtlsVo.setWelcomeName(fname);
				if(email!=null && !email.equalsIgnoreCase("")){
					emaildtlslist.add(emaildtlsVo);	
				}
				
			}
			}
			 ResultStatus rs = mailService.freeUserSendingMailsToFriends(emaildtlslist,requestFrom);
				if(rs.getResultCode()==0){
				message=IWebConstants.SUCCESS;
				}else{
					message=IWebConstants.FAILURE;
				}
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		 return SUCCESS;
	}
	public String getPath() {

		String requestURL = request.getRequestURL().toString();
		String actionURL = RequestUtils.getServletPath(request);

		String path = requestURL.replace(actionURL, "");

		 log.info("Path for invitation link :" + path);

		return path;
	}
	
	
	public String deleteMessageFromInbox()
	{
		String param;
		param = getTask();
		session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		
		if(user == null)
		{
			return IConstants.NOT_LOGGED_IN;
		}
		Long userId = user.getRegistrationID();
		
		try {
			jObj = new JSONObject(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(jObj.getString("type").equalsIgnoreCase("Message"))
		resultStatus = ananymousUserService.deleteMessageFromInbox(userId,jObj.getLong("senderId"),IConstants.COMMENTS,jObj.getLong("customMessageId"),jObj.getString("btnName"));
		return Action.SUCCESS;
		
	}
	
	public String getAllConnectedUsersByFilterViews() throws Exception
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
		
		Long startIndex = Long.parseLong(request.getParameter("startIndex"));
		Long results = Long.parseLong(request.getParameter("resultsCount"));		
		
		String statusText = jObj.getString("statusText");
		String nameString  = jObj.getString("nameString");
		JSONArray constituencyIds = jObj.getJSONArray("constituencyIds");		
		List<Long> listOfConstituencies = new ArrayList<Long>();
		
		for(int i=0; i<constituencyIds.length();i++)
			listOfConstituencies.add(new Long(constituencyIds.getString(i)));
		
		if(user == null)
		{
   			userDetails = ananymousUserService.getAllConnectedUsersBasedonLocationType(listOfConstituencies,IConstants.CONSTITUENCY_LEVEL,results,0l,statusText,startIndex,nameString);	
   		}else
   		{
   			userDetails = ananymousUserService.getAllConnectedUsersBasedonLocationType(listOfConstituencies,IConstants.CONSTITUENCY_LEVEL,results,user.getRegistrationID(),statusText,startIndex,nameString);
   		}
   		
		return Action.SUCCESS;
	}
	
	public String getAllConnectedUsersByLocation() throws Exception
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
		
		Long startIndex = Long.parseLong(request.getParameter("startIndex"));
		Long results = Long.parseLong(request.getParameter("resultsCount"));		
		
		String statusText = jObj.getString("statusText");
		String nameString  = jObj.getString("nameString");
		JSONArray constituencyIds = jObj.getJSONArray("constituencyIds");		
		List<Long> listOfConstituencies = new ArrayList<Long>();
		
		for(int i=0; i<constituencyIds.length();i++)
			listOfConstituencies.add(new Long(constituencyIds.getString(i)));
		
		if(user == null)
		{
   			userDetails = ananymousUserService.getAllConnectedUsersBasedonLocationType(listOfConstituencies,IConstants.CONSTITUENCY_LEVEL,results,0l,statusText,startIndex,nameString);	
   		}else
   		{
   			userDetails = ananymousUserService.getAllConnectedUsersBasedonLocationType(listOfConstituencies,IConstants.CONSTITUENCY_LEVEL,results,user.getRegistrationID(),statusText,startIndex,nameString);
   		}
   		
		return Action.SUCCESS;
	}
}
