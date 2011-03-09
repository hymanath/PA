package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.IAnanymousUserService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

public class AnonymousUserRegistrationAction extends ActionSupport implements
		ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	private String task;
	org.json.JSONObject jObj;
	
	private Long registrationId;
	private String userName;
	private String password; 
	private String reEnteredPassword; 
	
	private String state;
	private String district;
	private String constituency;
	
    private String redirectLoc = null;
    private Long stateId = null;
    private Long districtId = null;
    private Long localBodyId = null;
    private Long constituencyId = null;
    private Long localBodyElectionTypeId = null;
    private Long loginUserId;
    
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
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[a-zA-Z ]+$", message = "Username Should not Contain Special characters and Numbers.", shortCircuit = true)
	public void setUserName(String userName) {
		this.userName = userName;
		this.regVO.setUserName(userName);
	}	
	public String getUserName() {
		return regVO.getUserName();
	}
	
	public void setPassword(String password) {
		this.password = password;
		this.regVO.setPassword(password);
	}
	
	public String getPassword() {
		return regVO.getPassword();
	}
	
	public void setReEnteredPassword(String reEnteredPassword) {
		this.reEnteredPassword = reEnteredPassword;
	}	
	public String getReEnteredPassword() {
		return reEnteredPassword;
	}
	

//User Personal Details Validation
	
	public Long getRegistrationId() {
		return registrationId;
	}
	
	public void setRegistrationId(Long registrationId) {
		this.registrationId = registrationId;
	}
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "First Name is Mandatory",  shortCircuit = true)
	@RegexFieldValidator(type = ValidatorType.FIELD,expression = "^[a-zA-Z ]+$", message = "First name Should not contain Special Characters and Numbers.", shortCircuit = true)
	public void setFirstName(String firstName) {
		this.regVO.setFirstName(firstName);
	}
	public String getFirstName() {
		return regVO.getFirstName();
	}
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Last Name is Mandatory")
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[a-zA-Z ]+$", message = "Last name Should not Contain Special Characters and Numbers.", shortCircuit = true)
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
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Date Of Birth is required")
	public String getDateOfBirth() {
		return regVO.getDateOfBirth();
	}
	
	public void setDateOfBirth(String dateOfBirth) {
		this.regVO.setDateOfBirth(dateOfBirth);
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
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^([123456789]{1})([012346789]{5})$", message = "Invalid Pincode", shortCircuit = true)
	@StringLengthFieldValidator(type = ValidatorType.FIELD, message = "Invalid Pincode", minLength = "6", maxLength = "7")	
	public void setPincode(String pincode) {
		this.regVO.setPincode(pincode);
	}
	
	public void setState(String state) {
		this.state = state;
		this.regVO.setState(state);
	}
	public String getState() {
		return regVO.getState();
	}
		
	public void setDistrict(String district) {
		this.district = district;
		this.regVO.setDistrict(district);
	}
	
	public String getDistrict() {
		return regVO.getDistrict();
	}
		
	public void setConstituency(String constituency) {
		this.constituency = constituency;
		this.regVO.setConstituency(constituency);
	}
	
	public String getConstituency() {
		return regVO.getConstituency();
	}
	
	public String getPhone() {
		return regVO.getPhone();
	}
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^([789]{1})([012346789]{1})([0-9]{8})$", message = "Invalid Mobile Number", shortCircuit = true)
	@StringLengthFieldValidator(type = ValidatorType.FIELD, message = "Invalid Mobile number", minLength = "10", maxLength = "12")	
	public void setPhone(String phone) {
		this.regVO.setPhone(phone);
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
	public List<Long> getProfileOpts() {
		return regVO.getProfileOpts();
	}
	public void setProfileOpts(List<Long> profileOpts) {
		regVO.setProfileOpts(profileOpts);
	}
	public String execute(){
		
		Boolean savedSuccessfully;
		
		if(registrationId != null){
			loginUserId = registrationId;
			regVO.setRegistrationID(registrationId);
			savedSuccessfully = ananymousUserService.saveAnonymousUserDetails(regVO, true);
		}else
			savedSuccessfully = ananymousUserService.saveAnonymousUserDetails(regVO, false);
			
		if(savedSuccessfully){
			
			HttpSession session = request.getSession();			
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
		
		if(registrationId == null || registrationId == 0){
			if(userName == null || userName.trim().length() == 0)
				addFieldError("userName","Username Is Required");
		
			if(password == null || password.trim().length() == 0)
				addFieldError("password","Password Is Required.");
			
			if(reEnteredPassword==null || reEnteredPassword=="" || !reEnteredPassword.equalsIgnoreCase(password)){
				addFieldError("reEnteredPassword","Entered Password and Reentered Password are not Same.");			
			}
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
		
		Long result = new Long(ananymousUserService.checkForUserNameAvalilability(userName).getResultCode());
			
		if(result != 121L){
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
	
	 public Long getLoginUserId() {
		return loginUserId;
	 }
	
	 public void setLoginUserId(Long loginUserId) {
		this.loginUserId = loginUserId;
	 }
	
	 
}
