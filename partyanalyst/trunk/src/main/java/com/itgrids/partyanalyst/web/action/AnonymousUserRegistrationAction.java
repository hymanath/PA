package com.itgrids.partyanalyst.web.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

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
	
	private String fname;
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
	
	private String country;
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
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Username is required",shortCircuit=true)	
	public void setUserName(String userName) {
		this.regVO.setUserName(userName);
	}
	
	public String getUserName() {
		return regVO.getUserName();
	}
	
	public String getPassword() {
		return regVO.getPassword();
	}
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Password is required",shortCircuit=true)
	public void setPassword(String password) {
		this.regVO.setPassword(password);
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "name is required",shortCircuit=true)
	public String getFname() {
		return regVO.getName();
	}
	public void setFname(String fname) {
		this.regVO.setName(fname);
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
	
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "day is required",shortCircuit=true)
	public void setDay(String day) {
		this.day = day;
	}
	public String getDay() {
		return day;
	}
	
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "month is required",shortCircuit=true)		
	public void setMonth(String month) {
		this.month = month;
	}
	public String getMonth() {
			return month;
	}

	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "year is required",shortCircuit=true)	
	public void setYear(String year) {
		this.year = year;
	}
	public String getYear() {
		return year;
	}
	
	
	public String getEmail() {
		return regVO.getEmail();
	}

	public void setEmail(String email) {
		this.regVO.setEmail(email);
	}
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Mobile number is required",shortCircuit=true)
	@StringLengthFieldValidator(type = ValidatorType.FIELD,message = "mobile number should be 10 digits", shortCircuit = true,  minLength = "10",  maxLength = "10")
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
	
	//@RequiredStringValidator(type = ValidatorType.FIELD, message = "Pin Code is required",shortCircuit=true)
	public String getPincode() {
		return regVO.getPincode();
	}
	public void setPincode(String pincode) {
		this.regVO.setPincode(pincode);
	}
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please Select Country",shortCircuit=true)
	public void setCountry(String country) {
		this.regVO.setCountry(country);
	}
	public String getCountry() {
		return regVO.getCountry();
	}
	
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please Select State",shortCircuit=true)
	public void setState(String state) {
		this.regVO.setState(state);
	}
	public String getState() {
		return regVO.getState();
	}
	
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please Select District",shortCircuit=true)
	public void setDistrict(String district) {
		this.regVO.setDistrict(district);
	}
	public String getDistrict() {
		return regVO.getDistrict();
	}
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please Select District",shortCircuit=true)
	public void setConstituency(String constituency) {
		this.regVO.setConstituency(constituency);
	}
	public String getConstituency() {
		return regVO.getConstituency();
	}
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please Enter Phone Number",shortCircuit=true)
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
		System.out.println("dob---->"+dob);
		
		Boolean savedSuccessfully = ananymousUserService.saveAnonymousUserDetails(regVO);
		
		if(savedSuccessfully){
			
			session = request.getSession();
			String userFullName = regVO.getName() + " "; 
			regVO.setUserStatus(IConstants.FREE_USER);
			session.setAttribute(IConstants.USER,regVO);
			session.setAttribute("UserName", userFullName);
			session.setAttribute("loginStatus", "out");
			session.setAttribute("HiddenCount", 0);
		}
		
		if(redirectLoc != null && redirectLoc != "")
			return getRedirectPageDetails();
		
		return SUCCESS;
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
