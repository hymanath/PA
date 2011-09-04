package com.itgrids.partyanalyst.web.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.jfree.util.Log;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IAnanymousUserService;
import com.itgrids.partyanalyst.service.IMailService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

public class AnonymousUserRegistrationAction extends ActionSupport implements
		ServletRequestAware ,ServletContextAware{

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
    private List<String> accept;
    private RegistrationVO regVO = new RegistrationVO();
	
	private IAnanymousUserService ananymousUserService;
	private IMailService mailService;
	
	private static final org.apache.log4j.Logger log = Logger.getLogger(AnonymousUserRegistrationAction.class);
   
	//For UserImage
    private File uploadImage;
    private String uploadImageContentType;
    private String uploadImageFileName;
    private HttpSession session;
    private ServletContext context;
    
    private List<SelectOptionVO> districts;
    private List<SelectOptionVO> constituencies;
    private IRegionServiceData regionServiceDataImp;
    
 
	public IMailService getMailService() {
		return mailService;
	}
	public void setMailService(IMailService mailService) {
		this.mailService = mailService;
	}
	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}
	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}
	public List<SelectOptionVO> getDistricts() {
		return districts;
	}
	public void setDistricts(List<SelectOptionVO> districts) {
		this.districts = districts;
	}
	public List<SelectOptionVO> getConstituencies() {
		return constituencies;
	}
	public void setConstituencies(List<SelectOptionVO> constituencies) {
		this.constituencies = constituencies;
	}
	public String getUploadImageContentType() {
		return regVO.getUserProfilePic();
	}
	public void setUploadImageContentType(String uploadImageContentType) {
				this.regVO.setUserProfilePic(uploadImageContentType);
				this.uploadImageContentType=uploadImageContentType;
	}
	public String getUploadImageFileName() {
		return uploadImageFileName;
	}
	public void setUploadImageFileName(String uploadImageFileName) {
		this.uploadImageFileName = uploadImageFileName;
	}
	public File getUploadImage() {
		return uploadImage;
	}
	public void setUploadImage(File uploadImage) {
		this.uploadImage = uploadImage;
	}
	public ServletContext getContext() {
		return context;
	}
	public void setServletContext(ServletContext context) {
		this.context = context;
	}
	
	public IAnanymousUserService getAnanymousUserService() {
		return ananymousUserService;
	}
	public void setAnanymousUserService(IAnanymousUserService ananymousUserService) {
		this.ananymousUserService = ananymousUserService;
	}
	
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public RegistrationVO getRegVO() {
		return regVO;
	}
	
	public void setRegVO(RegistrationVO regVO) {
		this.regVO = regVO;
	}
	
	
//User Details Validation
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[a-zA-Z ]+$", message = "Username Should not Contain Special characters and Numbers.", shortCircuit = true)
	@StringLengthFieldValidator(type = ValidatorType.FIELD, message = "Please enter Username below 20 characters ", minLength = "1", maxLength = "20")	
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
		return regVO.getRegistrationID();
	}
	
	public void setRegistrationId(Long registrationId) {
		this.registrationId = registrationId;
		regVO.setRegistrationID(registrationId);
	}
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "First Name is Mandatory",  shortCircuit = true)
	@RegexFieldValidator(type = ValidatorType.FIELD,expression = "^[a-zA-Z ]+$", message = "First name Should not contain Special Characters and Numbers.", shortCircuit = true)
	@StringLengthFieldValidator(type = ValidatorType.FIELD, message = "Please enter First Name below 20 characters ", minLength = "1", maxLength = "20")	
	public void setFirstName(String firstName) {
		this.regVO.setFirstName(firstName);
	}
	public String getFirstName() {
		return regVO.getFirstName();
	}
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Last Name is Mandatory")
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[a-zA-Z ]+$", message = "Last name Should not Contain Special Characters and Numbers.", shortCircuit = true)
	@StringLengthFieldValidator(type = ValidatorType.FIELD, message = "Please enter Last Name below 20 characters", minLength = "1", maxLength = "20")	
	public void setLastName(String lastName) {
		this.regVO.setLastName(lastName);
	}
	
	public String getLastName() {
		return regVO.getLastName();
	}
	
	public void setGender(String gender) {
		this.regVO.setGender(gender);
	}
	
	public String getGender() {
		return regVO.getGender();
	}
	
	public String getDateOfBirth() {
		return regVO.getDateOfBirth();
	}
	
	public void setDateOfBirth(String dateOfBirth) {
		this.regVO.setDateOfBirth(dateOfBirth);
	}
	
	//User Contact Details validation
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^([789]{1})([012346789]{1})([0-9]{8})$", message = "Invalid Mobile Number", shortCircuit = true)
	@StringLengthFieldValidator(type = ValidatorType.FIELD, message = "Invalid Mobile number", minLength = "10", maxLength = "12")	
	public void setMobile(String mobile) {
		this.regVO.setMobile(mobile);
	}
	
	public String getMobile() {
		return regVO.getMobile();
	}
	
	public void setAddress(String address) {
		this.regVO.setAddress(address);
	}
	public String getAddress() {
		return regVO.getAddress();
	}
	
	public String getEmail() {
		return regVO.getEmail();
	}
	@EmailValidator(type = ValidatorType.FIELD , message = " Enter a valid email.")
	public void setEmail(String email) {
		this.regVO.setEmail(email);
	}
	
	public String getPincode() {
		return regVO.getPincode();
	}
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^([0123456789]{6})$", message = "Invalid Pincode", shortCircuit = true)
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
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^([789]{1})([012346789]{1})([0-9]{8})$", message = "Invalid Telephone Number", shortCircuit = true)
	@StringLengthFieldValidator(type = ValidatorType.FIELD, message = "Invalid Telephone number", minLength = "10", maxLength = "12")	
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
	
	public List<String> getAccept() {
		return accept;
	}
	public void setAccept(List<String> accept) {
		this.accept = accept;
	}
	
	public String execute(){
		
		
		session = request.getSession();
		
		String sPath = (String)session.getAttribute("imagePath");
		
		Boolean savedSuccessfully;
		
		BufferedImage imageFile = null;
		
		 String  imageName=null;
		 String constiName[]=null; 
		 String fileName=null;; 
		 try {
			 if(this.uploadImage !=null)
			 {
			 imageFile = ImageIO.read(this.uploadImage);
			 }
	         //String filePath = context.getRealPath("/")+"pictures\\"+IConstants.PROFILE_PIC+"\\";
			 String filePath = "";
			 
			 if(sPath != null)
				 filePath = sPath;
			 else
				 filePath = context.getRealPath("/")+"pictures\\"+IConstants.PROFILE_PIC+"\\";				 
				 
             if(uploadImageContentType!=null)
             {
              constiName = uploadImageContentType.split("/");
		      imageName =  regVO.getRegistrationID()+"."+constiName[1];    
             }
	           
			
            if(registrationId != null && registrationId > 0){
				loginUserId = registrationId;
				regVO.setRegistrationID(registrationId);
				regVO.setUserProfilePic(imageName);
				savedSuccessfully = ananymousUserService.saveAnonymousUserDetails(regVO, true);
			}else
			
			  savedSuccessfully = ananymousUserService.saveAnonymousUserDetails(regVO, false);
            String requestURL= request.getRequestURL().toString();
			String requestFrom = "";
			if(requestURL.contains("www.partyanalyst.com"))
				requestFrom = IConstants.SERVER;
			else
				requestFrom = IConstants.LOCALHOST;
			
            	// For sending notification after registration
			 if(!(registrationId != null && registrationId > 0)){
			mailService.sendRegistrationNotification(regVO,requestFrom);
			 }
         //    if(regVO.getRegistrationID()!=null){
            
            
            
            try {
            	
            	if(this.uploadImage !=null){
                    fileName = filePath+regVO.getRegistrationID()+"."+constiName[1];
                    imageName =  regVO.getRegistrationID()+"."+constiName[1];
	                FileImageOutputStream filName = new FileImageOutputStream(new File(fileName));
	
	                log.info("Image Name :" + imageFile);
	                log.info("File :" + filName);
	                ImageIO.write(imageFile, constiName[1],filName);
	                filName.close();
            	}

			} catch (Exception e) {
			
				log.error(e);
			}
            /*if(this.uploadImage !=null){
               fileName = filePath+regVO.getRegistrationID()+"."+constiName[1];
               imageName =  regVO.getRegistrationID()+"."+constiName[1];  
	           ImageIO.write(imageFile, constiName[1],new File(fileName));
             }       	*/
			if(savedSuccessfully){	
				
				HttpSession session = request.getSession();			
				String userFullName = regVO.getFirstName().concat(" ").concat(regVO.getLastName()); 
				regVO.setUserStatus(IConstants.FREE_USER);
				session.setAttribute(IConstants.USER,regVO);
				session.setAttribute("UserName", userFullName);
				session.setAttribute("UserType", "FreeUser");
				session.setAttribute("loginStatus", "out");
				session.setAttribute("HiddenCount", 0);
				
				
				session.removeAttribute("districts");
				session.removeAttribute("constituencies");
			}
			 
        
		 }
		 catch(Exception e){
			 e.printStackTrace();
			 
			 return "failure";
		 }
		if(redirectLoc != null && !"".equalsIgnoreCase(redirectLoc))
			return getRedirectPageDetails();
		else if("".equalsIgnoreCase(redirectLoc))
			return "connect";
		
		return SUCCESS;
		
	}
	
	public void validate() {		       
		HttpSession session = request.getSession();
		if(registrationId == null || registrationId == 0){
			/*if(userName == null || userName.trim().length() == 0)
				addFieldError("userName","Username is required");*/
		
			if(password == null || password.trim().length() == 0)
				addFieldError("password","Password is required.");
			
			if(reEnteredPassword==null || reEnteredPassword=="" || !reEnteredPassword.equals(password)){
				addFieldError("reEnteredPassword","Entered Password and Reentered Password are not Same.");			
			}
		}
		
		
		if(state==null || state.equalsIgnoreCase("0")){
			addFieldError("state","Please select a State.");
		}
		/*if(district==null || district.equalsIgnoreCase("0")){
			if(Long.parseLong(state)!=0l){
				districts = regionServiceDataImp.getDistrictsByStateID(Long.parseLong(state));
				session.setAttribute("districts", districts);
			}
			addFieldError("district","Please select a District.");
		}*/
		if(constituency==null || constituency.equalsIgnoreCase("0")){
			/*if(Long.parseLong(district)!=0l){
				constituencies = regionServiceDataImp.getConstituenciesByDistrictID(Long.parseLong(district));
				
				 * Modified by ravi 
				 * please refer previous version to check for original code.
				  
				if(constituencies == null || constituencies.size() == 0)
					constituencies.add(0, new SelectOptionVO(0L,"Select Constituency"));
				
				if(constituencies != null && constituencies.size() > 1)
					constituencies.add(0, new SelectOptionVO(0L,"Select Constituency"));
				
				session.setAttribute("constituencies", constituencies);
			}*/
			addFieldError("constituency","Please select a Constituency.");
		}
		
		if(registrationId == null || registrationId == 0)
		{	
			Long result = new Long(ananymousUserService.checkForUserNameAvalilability(getEmail()).getResultCode());
				
			if(result != 121L){
				addFieldError("userName","UserName does not exist.");
			}
		}
		
		if(accept.get(0).equalsIgnoreCase("false"))
		{
			addFieldError("userName","You must accept Terms of use and Policy of website to Register.");
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
