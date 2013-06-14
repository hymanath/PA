package com.itgrids.partyanalyst.web.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.ILoginService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements ServletContextAware, ServletRequestAware{

	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext context;
	private String userName = null;
	private String password = null;
	private ILoginService loginService;
	 private String task = null;
	 private ResultStatus resultStatus;
	public void setServletContext(ServletContext context) {
		this.context = context;
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
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String value) {
		userName = value;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String value) {
		password = value;
	}
	
	/*private String userFullName = null;
	private String src = null;
	private String userType = null;
	//for problem management login
    private String redirectLoc = null;
    
    private String task = null;
    private String name = null;
    private Long stateId = null;
    private Long districtId = null;
    private Long localBodyId = null;
    private Long constituencyId = null;
    private Long localBodyElectionTypeId = null;
    
    private String districtName;	
	private String constituencyName;
	
	private String url;
	
	//for election analyze page
	private String electionId;
	private String stateID;
	private String electionType;
	private Long electionTypeId;
	private String stateName;
	private String year;
	
	private Long parliamentConstiId;    
    private String taskType;
    private Long problemHistoryId;
    
    private String party;
	private String electionId1;
	private String electionId2;
	private String allianceCheck;
	private String selectedPartyName;
	
	 
	private String selectedPartyShortName;
	private String selectedPartyId;
	private String selectedElectionTypeName;
	private String selectedLocationName;
	private String reportLevel;
	private String stateSelectName;
	private String partySelectName;
	private String constituencySelectName;
    private String districtSelectName;
    private String candidateId;
    private String changedUserName = "false";
    private boolean hasPartyAnalystUserRole;
    private boolean hasFreeUserRole;
    private List<String> userRoles;
   // JSONObject jObj = null;
    private String resultForAjax;
    private String SUCCESS = "success";
    private String FAILURE = "failure";
    
    
    public String getResultForAjax() {
		return resultForAjax;
	}

	public void setResultForAjax(String resultForAjax) {
		this.resultForAjax = resultForAjax;
	}*/

	/*public JSONObject getjObj() {
		return jObj;
	}

	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}*/

	public ILoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	/*public List<String> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<String> userRoles) {
		this.userRoles = userRoles;
	}

	public boolean isHasPartyAnalystUserRole() {
		return hasPartyAnalystUserRole;
	}

	public void setHasPartyAnalystUserRole(boolean hasPartyAnalystUserRole) {
		this.hasPartyAnalystUserRole = hasPartyAnalystUserRole;
	}

	public boolean isHasFreeUserRole() {
		return hasFreeUserRole;
	}

	public void setHasFreeUserRole(boolean hasFreeUserRole) {
		this.hasFreeUserRole = hasFreeUserRole;
	}

	public String getDistrictSelectName() {
		return districtSelectName;
	}

	public void setDistrictSelectName(String districtSelectName) {
		this.districtSelectName = districtSelectName;
	}

	public String getSelectedPartyShortName() {
		return selectedPartyShortName;
	}

	public void setSelectedPartyShortName(String selectedPartyShortName) {
		this.selectedPartyShortName = selectedPartyShortName;
	}

	public String getSelectedPartyId() {
		return selectedPartyId;
	}

	public void setSelectedPartyId(String selectedPartyId) {
		this.selectedPartyId = selectedPartyId;
	}

	public String getSelectedElectionTypeName() {
		return selectedElectionTypeName;
	}

	public void setSelectedElectionTypeName(String selectedElectionTypeName) {
		this.selectedElectionTypeName = selectedElectionTypeName;
	}

	public String getSelectedLocationName() {
		return selectedLocationName;
	}

	public void setSelectedLocationName(String selectedLocationName) {
		this.selectedLocationName = selectedLocationName;
	}

	public String getReportLevel() {
		return reportLevel;
	}

	public void setReportLevel(String reportLevel) {
		this.reportLevel = reportLevel;
	}

	public String getStateSelectName() {
		return stateSelectName;
	}

	public void setStateSelectName(String stateSelectName) {
		this.stateSelectName = stateSelectName;
	}

	public String getPartySelectName() {
		return partySelectName;
	}

	public void setPartySelectName(String partySelectName) {
		this.partySelectName = partySelectName;
	}

	public String getConstituencySelectName() {
		return constituencySelectName;
	}

	public void setConstituencySelectName(String constituencySelectName) {
		this.constituencySelectName = constituencySelectName;
	}

	public String getSelectedPartyName() {
		return selectedPartyName;
	}

	public void setSelectedPartyName(String selectedPartyName) {
		this.selectedPartyName = selectedPartyName;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public String getElectionId1() {
		return electionId1;
	}

	public void setElectionId1(String electionId1) {
		this.electionId1 = electionId1;
	}

	public String getElectionId2() {
		return electionId2;
	}

	public void setElectionId2(String electionId2) {
		this.electionId2 = electionId2;
	}

	public String getAllianceCheck() {
		return allianceCheck;
	}

	public void setAllianceCheck(String allianceCheck) {
		this.allianceCheck = allianceCheck;
	}

	public Long getParliamentConstiId() {
		return parliamentConstiId;
	}

	public void setParliamentConstiId(Long parliamentConstiId) {
		this.parliamentConstiId = parliamentConstiId;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getConstituencyName() {
		return constituencyName;
	}

	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}

	private static final Logger log = Logger.getLogger(LoginAction.class);
	
	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	
	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getLocalBodyId() {
		return localBodyId;
	}

	public void setLocalBodyId(Long localBodyId) {
		this.localBodyId = localBodyId;
	}

	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	public String getRedirectLoc() {
		return redirectLoc;
	}

	public void setRedirectLoc(String redirectLoc) {
		this.redirectLoc = redirectLoc;
	}

	public Long getLocalBodyElectionTypeId() {
		return localBodyElectionTypeId;
	}

	public void setLocalBodyElectionTypeId(Long localBodyElectionTypeId) {
		this.localBodyElectionTypeId = localBodyElectionTypeId;
	}

	public String getElectionId() {
		return electionId;
	}

	public void setElectionId(String electionId) {
		this.electionId = electionId;
	}

	public String getStateID() {
		return stateID;
	}

	public void setStateID(String stateID) {
		this.stateID = stateID;
	}

	public String getElectionType() {
		return electionType;
	}

	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}

	public Long getElectionTypeId() {
		return electionTypeId;
	}

	public void setElectionTypeId(Long electionTypeId) {
		this.electionTypeId = electionTypeId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@RequiredStringValidator(key="requiredstring" ,shortCircuit=true)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String value) {
		userName = value;
	}

	@RequiredStringValidator(key="requiredstring" ,shortCircuit=true)
	public String getPassword() {
		return password;
	}
	public void setPassword(String value) {
		password = value;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		
		this.url = url;
	}

	public Long getProblemHistoryId() {
		return problemHistoryId;
	}

	public void setProblemHistoryId(Long problemHistoryId) {
		this.problemHistoryId = problemHistoryId;
	}
	
	public String getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}
*/
	public String execute(){
		return Action.SUCCESS;
		/*

		session = request.getSession();
		
		RegistrationVO regVO = loginService.checkForValidUser(userName, password);
		
		//Check User Availability
		if (regVO == null || regVO.getRegistrationID() == null)
		{
			session.setAttribute("loginStatus", "in");
			addActionError("Invalid username or password! Please try again!");
			session.setAttribute("checkedTypeValue", userType);
			return INPUT;
		}	
		
		if(!userIpCheck(regVO))
		{
			session.setAttribute("loginStatus", "in");
			addActionError(" You do not have Permission to Access Site From this Network  ");
			session.setAttribute("checkedTypeValue", userType);
			return INPUT;
		}
		
		int hiden = 0;
		
		userFullName = regVO.getFirstName() + " " + regVO.getLastName();
		session.setAttribute("loginStatus", "out");
		session.setAttribute("HiddenCount", hiden);
		session.setAttribute("UserName", userFullName);
		session.setAttribute(IConstants.USER,regVO);
		
		userRoles = regVO.getUserRoles();
		
		if(userRoles.contains(IConstants.PARTY_ANALYST_USER))
		{
			hasPartyAnalystUserRole = true;
			session.setAttribute(IWebConstants.PARTY_ANALYST_USER_ROLE, true);
			session.setAttribute("UserType", "PartyAnalyst");
			
			if(userRoles.contains(IConstants.FREE_USER))
			{
				session.setAttribute(IWebConstants.FREE_USER_ROLE, true);
				hasFreeUserRole = true;
			}
			saveUserSessionDetails(IWebConstants.LOGIN);
		}
		
		else if(userRoles.contains(IConstants.FREE_USER))
		{
			session.setAttribute(IWebConstants.FREE_USER_ROLE, true);
			hasFreeUserRole = true;
			session.setAttribute("UserType", "FreeUser");
			changedUserName = "true";
			saveUserSessionDetails(IWebConstants.LOGIN);
			//return getRedirectPageDetails();	
		}
				
		return finalResultString();
	*/}
	
    /* private String finalResultString() {
    	System.out.print("URL"+url);
    	if(src != null && !"null".equalsIgnoreCase(src))
    	{
    		log.debug(src);
 			return src;
    	}
 		else if(url != null && url.length() > 0 && url.contains(".action") && !url.contains("loginAction")){
 			url = StringUtils.split(url,".")[0].substring(1);
 			return "redirectUrl";
 		}
    	if(userRoles.contains(IConstants.PARTY_ANALYST_USER))
				return IConstants.PARTY_ANALYST_USER;
		
    	return IConstants.FREE_USER;
     }

	public String getRedirectPageDetails(){
		
		if(redirectLoc != null){
			if(redirectLoc.equalsIgnoreCase(IConstants.STATE)){
				return "statePageRedirect";
			}else if(redirectLoc.equalsIgnoreCase(IConstants.DISTRICT)){
				return "districtPageRedirect";
			}else if(redirectLoc.equalsIgnoreCase(IConstants.CONSTITUENCY)){
				return "constituencyPageRedirect";
			}else if(redirectLoc.equalsIgnoreCase(IConstants.LOCAL_BODY_ELECTION)){
				return "localElectionBodyPageRedirect";
			}else if(redirectLoc.equalsIgnoreCase(IConstants.CONNECT_REDIRECT)){
				return "connectPeoplePageRedirect";
			}else if(redirectLoc.equalsIgnoreCase(IConstants.PROBLEM_DISCUSSION)){
				return "problemDiscussionRedirect";
			}else if(redirectLoc.equalsIgnoreCase("CANDIDATE_PROFILE")){
				return "candidatePageRedirect";
			}
		}
		return finalResultString();
	}

	public String getChangedUserName() {
		return changedUserName;
	}

	public void setChangedUserName(String changedUserName) {
		this.changedUserName = changedUserName;
	}
	
public String saveUserSessionDetails(String status)
{
	try
	{
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute(IConstants.USER);
		UserTrackingVO userTrackingVO = new UserTrackingVO();
		
		userTrackingVO.setRegistrationId(regVO.getRegistrationID());
		userTrackingVO.setRemoteAddress(request.getRemoteAddr());
		userTrackingVO.setStatus(status);
		userTrackingVO.setSessionId(session.getId());
		loginService.saveUserSessionDetails(userTrackingVO);
		return SUCCESS;
	}catch (Exception e) {
		log.error("Exception Occured in saveUserSessionDetails() Method, Exception is - "+e);
		return IWebConstants.FAILURE;
	}
}*/

public String ajaxCallForLoginPopup(){
	
	//String param = null;
	//param = getTask();
	try {
		//jObj = new JSONObject(param);
		//System.out.println(jObj);
		if(getTask().equalsIgnoreCase("validateUserForLogin"))
		{
			userName = getUserName();
			password=getPassword();
			 
			session = request.getSession();
			
			//RegistrationVO regVO = loginService.checkForValidUser(userName, password);
			resultStatus = loginService.checkForUserNameAndPassword(userName, password);
			
			if(resultStatus.getResultCode() == 0)
			{
				System.out.println("success");
				RegistrationVO regVO = loginService.getUserByUserNameAndPassword(userName, password);
				session.setAttribute("USER", regVO);
				
				if(regVO.getUserAccessType().equalsIgnoreCase("subuser"))
					resultStatus.setResultCode(2);
			} 
			else
			{
				System.out.println("failure");
			}
			/*//Check User Availability
			if (regVO == null || regVO.getRegistrationID() == null)
			{
				session.setAttribute("loginStatus", "in");
				//addActionError("Invalid username or password! Please try again!");
				session.setAttribute("checkedTypeValue", userType);
				resultForAjax=FAILURE;
				return Action.SUCCESS;
			}	
					
			
			if(!userIpCheck(regVO))
			{
				resultForAjax="IPFAILURE";
				return Action.SUCCESS;
			}
		
			//end
			int hiden = 0;
			
			userFullName = regVO.getFirstName() + " " + regVO.getLastName();
			session.setAttribute("loginStatus", "out");
			session.setAttribute("HiddenCount", hiden);
			session.setAttribute("UserName", userFullName);
			session.setAttribute(IConstants.USER,regVO);
			
			userRoles = regVO.getUserRoles();
			
			if(userRoles.contains(IConstants.PARTY_ANALYST_USER))
			{
				hasPartyAnalystUserRole = true;
				session.setAttribute(IWebConstants.PARTY_ANALYST_USER_ROLE, true);
				session.setAttribute("UserType", "PartyAnalyst");
				
				if(userRoles.contains(IConstants.FREE_USER))
				{
					session.setAttribute(IWebConstants.FREE_USER_ROLE, true);
					hasFreeUserRole = true;
				}
				saveUserSessionDetails(IWebConstants.LOGIN);
			}
			
			else if(userRoles.contains(IConstants.FREE_USER))
			{
				session.setAttribute(IWebConstants.FREE_USER_ROLE, true);
				hasFreeUserRole = true;
				session.setAttribute("UserType", "FreeUser");
				changedUserName = "true";
				saveUserSessionDetails(IWebConstants.LOGIN);
				//return getRedirectPageDetails();	
			}*/
		}
		/* String typeOfUser= finalResultString();
		 if(typeOfUser!=null)
			 resultForAjax=SUCCESS;*/
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return Action.SUCCESS;
}
	/*public boolean userIpCheck(RegistrationVO regVO) 
	{
		try{
		String restiction = regVO.getLoginRestriction();
		
		if(restiction.equalsIgnoreCase("true"))
		{
			String ipAddress = request.getRemoteAddr();
			boolean result = loginService.checkForUserAccessIPAddress(regVO.getRegistrationID(),ipAddress);
			
			if(!result)
				loginService.sendMailToAdminGroup(regVO,ipAddress);
			
			return result;
		}else return true;
		}catch (Exception e) {
			return false;
		}
	}
*/



}