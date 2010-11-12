package com.itgrids.partyanalyst.web.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.ILoginService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

public class LoginAction extends ActionSupport implements ServletContextAware, ServletRequestAware{

	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext context;
	private String userName = null;
	private String password = null;
	private ILoginService loginService;
	private String userFullName = null;
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

	public String execute(){

		session = request.getSession();
		RegistrationVO regVO = null;
		
		//Free User
		if(userType != null && userType.equalsIgnoreCase("2")){
			regVO = loginService.checkForValidNormalUser(userName, password);
		}
		//Party Analyst Commercial User
		else{
		    regVO = loginService.checkForValidUser(userName, password);
		    
		    //Free user Check in other situations
		    if (regVO.getRegistrationID() == null){
		    	regVO = loginService.checkForValidNormalUser(userName, password);
		    	if(regVO.getRegistrationID() != null){
		    		userType = "2";
		    	}
		    }else if(regVO.getRegistrationID() != null){
		    	userType = "1";
		    }
		}
		
		//Check User Availability
		if (regVO.getRegistrationID() == null) {
			session.setAttribute("loginStatus", "in");
			addActionError("Invalid user name or password! Please try again!");
			//if(userType != null && userType.equalsIgnoreCase("2"))
				return IConstants.NOT_LOGGED_IN;
			/*else
			    return ERROR;	*/		
		} else {
			
			int hiden = 0;
			if(userType != null){
				if(userType.equalsIgnoreCase("1")){
					regVO.setUserStatus(IConstants.PARTY_ANALYST_USER);
					userFullName = regVO.getFirstName() + " " + regVO.getLastName();
					session.setAttribute(IConstants.USER,regVO);
					session.setAttribute("UserName", userFullName);
					session.setAttribute("loginStatus", "out");
					session.setAttribute("UserType", "PartyAnalyst");
					session.setAttribute("HiddenCount", hiden);
					
					if(src != null && !"null".equalsIgnoreCase(src))
						return src;
					else
						return SUCCESS;
				}
				else if(userType.equalsIgnoreCase("2")){
					userFullName = regVO.getFirstName() + " "; 
					regVO.setUserStatus(IConstants.FREE_USER);
					session.setAttribute(IConstants.USER,regVO);
					session.setAttribute("UserName", userFullName);
					session.setAttribute("UserType", "FreeUser");
					session.setAttribute("loginStatus", "out");
					session.setAttribute("HiddenCount", hiden);
					
					//task = "pm_redirect";
					return getRedirectPageDetails();
				}
			}
			else{
				if(src != null && !"null".equalsIgnoreCase(src))
					return src;
				else
					return SUCCESS;			
			}
		}
	 return Action.ERROR;
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
			}
		}
		
		return Action.SUCCESS;
	}
}