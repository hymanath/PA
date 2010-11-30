package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.IAnanymousUserService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

public class AnonymousUserRegistrationAction extends ActionSupport implements
		ServletContextAware, ServletResponseAware, ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ServletContext context;
	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession session;
	private String task;
	org.json.JSONObject jObj;
	
	private String userName;
	private String password; 
	private String reEnteredPassword; 
	
	private String firstName;
	private String lastName;
	private String gender;	
	private String dateOfBirth;
	private String day;
	private String month;
	private String year;
	private String email;
	private String phone;
	private String mobile;
	private String address;
	private String pincode;
		
	private String state;
	private String district;
	private String constituency;
	
    private String redirectLoc = null;
    private String name = null;
    private Long stateId = null;
    private Long districtId = null;
    private Long localBodyId = null;
    private Long constituencyId = null;
    private Long localBodyElectionTypeId = null;	
	private RegistrationVO regVO = new RegistrationVO();
	
	private IAnanymousUserService ananymousUserService;
		
	
	public IAnanymousUserService getAnanymousUserService() {
		return ananymousUserService;
	}
	public void setAnanymousUserService(IAnanymousUserService ananymousUserService) {
		this.ananymousUserService = ananymousUserService;
	}
	public RegistrationVO getRegVO() {
		return regVO;
	}
	public void setRegVO(RegistrationVO regVO) {
		this.regVO = regVO;
	}
	
	
//User Details Validation

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "UserName is required",shortCircuit=true)	
	public void setUserName(String userName) {
		this.regVO.setUserName(userName);
	}	
	public String getUserName() {
		return regVO.getUserName();
	}
	
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Password is required",shortCircuit=true)
	public void setPassword(String password) {
		this.regVO.setPassword(password);
		this.password = password;
	}
	public String getPassword() {
		return regVO.getPassword();
	}
		
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "ReenteredPassword field is required",shortCircuit=true)	
	public void setReEnteredPassword(String reEnteredPassword) {
		this.reEnteredPassword = reEnteredPassword;
	}	
	public String getReEnteredPassword() {
		return reEnteredPassword;
	}
	

//User Personal Details Validation
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "First Name is required",shortCircuit=true)	
	public void setFirstName(String firstName) {
		this.regVO.setFirstName(firstName);
	}
	public String getFirstName() {
		return regVO.getFirstName();
	}
	
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Last Name is required",shortCircuit=true)
	public void setLastName(String lastName) {
		this.regVO.setLastName(lastName);
	}
	public String getLastName() {
		return regVO.getLastName();
	}
	
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please select gender",shortCircuit=true)
	public void setGender(String gender) {
		this.regVO.setGender(gender);
	}
	public String getGender() {
		return regVO.getGender();
	}
	
	
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	
	
	
	public void setDay(String day) {
		this.day = day;
	}
	public String getDay() {
		return day;
	}	
	
	public void setMonth(String month) {
		this.month = month;
	}
	public String getMonth() {
			return month;
	}
	
	public void setYear(String year) {
		this.year = year;
	}
	public String getYear() {
		return year;
	}
	
	
	
	
	
//User Contact Details validation
	
	public void setMobile(String mobile) {
		this.regVO.setMobile(mobile);
	}
	public String getMobile() {
		return regVO.getMobile();
	}
	
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Address is required",shortCircuit=true)
	public void setAddress(String address) {
		this.regVO.setAddress(address);
	}
	public String getAddress() {
		return regVO.getAddress();
	}
	
	public String getEmail() {
		return regVO.getEmail();
	}

	public void setEmail(String email) {
		this.regVO.setEmail(email);
	}
	
	
	public String getPincode() {
		return regVO.getPincode();
	}
	public void setPincode(String pincode) {
		this.regVO.setPincode(pincode);
	}
	
	
	public void setState(String state) {
		this.regVO.setState(state);
		this.state = state;
	}
	public String getState() {
		return regVO.getState();
	}
		
	public void setDistrict(String district) {
		this.regVO.setDistrict(district);
		this.district = district;
	}
	public String getDistrict() {
		return regVO.getDistrict();
	}
		
	public void setConstituency(String constituency) {
		this.regVO.setConstituency(constituency);
		this.constituency = constituency;
	}
	public String getConstituency() {
		return regVO.getConstituency();
	}
	
	public String getPhone() {
		return regVO.getPhone();
	}

	public void setPhone(String phone) {
		this.regVO.setPhone(phone);
	}

		
	
	public ServletContext getContext() {
		return context;
	}
	public void setContext(ServletContext context) {
		this.context = context;
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
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public org.json.JSONObject getJObj() {
		return jObj;
	}
	public void setJObj(org.json.JSONObject obj) {
		jObj = obj;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
        this.response = response;
	}
	public void setServletContext(ServletContext context) {
		this.context = context;		
	}
	
	
	public String getRedirectLoc() {
		return redirectLoc;
	}
	public void setRedirectLoc(String redirectLoc) {
		this.redirectLoc = redirectLoc;
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
	public Long getLocalBodyElectionTypeId() {
		return localBodyElectionTypeId;
	}
	public void setLocalBodyElectionTypeId(Long localBodyElectionTypeId) {
		this.localBodyElectionTypeId = localBodyElectionTypeId;
	}
	public String execute(){
		String dob=day+"/"+month+"/"+year;
		this.regVO.setDateOfBirth(dob);
		Boolean savedSuccessfully = ananymousUserService.saveAnonymousUserDetails(regVO);
		
		if(savedSuccessfully){
			
			session = request.getSession();			
			String userFullName = regVO.getFirstName().concat(" ").concat(regVO.getLastName()); 
			regVO.setUserStatus(IConstants.FREE_USER);
			session.setAttribute(IConstants.USER,regVO);
			session.setAttribute("UserName", userFullName);
			session.setAttribute("loginStatus", "out");
			session.setAttribute("HiddenCount", 0);
		}
		
		if(redirectLoc != null && !"".equalsIgnoreCase(redirectLoc))
			return getRedirectPageDetails();
		else if("".equalsIgnoreCase(redirectLoc))
			return "connect";
		
		return SUCCESS;
	}
		
	   public void validate() {		       
		       if(reEnteredPassword==null || reEnteredPassword=="" || !reEnteredPassword.equalsIgnoreCase(password)){
					addFieldError("reEnteredPassword","Entered Password and Reentered Password are not Same.");			
		       }
		       if(state==null || state.equalsIgnoreCase("0")){
		    	   addFieldError("state","Please select a State.");
		       }
		       if(district==null || district.equalsIgnoreCase("0")){
		    	   addFieldError("district","Please select a District.");
		       }
		       if(constituency==null || constituency.equalsIgnoreCase("0")){
		    	   addFieldError("constituency","Please select a Constituency.");
		       }
		       if(new Long(ananymousUserService.checkForUserNameAvalilability(userName).getResultCode()) == 121){
		    	   addFieldError("userName","UserName does not exist.");
		       }
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
				}
			}
			
		 return Action.ERROR;
	}
	 
	 
}
