package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.ProblemManagementDataVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.IProblemManagementService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

public class AddNewProblemSubmitAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(AddNewProblemSubmitAction.class);
	private HttpServletRequest request;
	private IProblemManagementService problemManagementService;
	private HttpSession session;
	private ProblemBeanVO problemBeanFromDB;
	private Boolean isSuccessfullyInserted;
	ProblemBeanVO problemBeanVO = new ProblemBeanVO();
	
	//form inputs
	private String problem;
	private String description;
	private String state;	
	private String district;
	private String parliamentConstituency;
	private String constituency;
	private String mandal;
	private String village;
	private String booth;
	private String reportedDate;
	private String existingFrom;
	private String probSource;
	private String name;
	private String mobile;
	private String phone;
	private String email;
	private String address;
	private Long status;
	private Long problemScope;
	private Long problemLocationId;
	private String defaultScopeId;
	private String defaultStateId;
	private String defaultDistId;
	private String defaultConstId;
	public Boolean isParliament;
	private Long pConstituencyId;
	private Long cadreId;
	
		
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}	
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public ProblemBeanVO getProblemBeanVO() {
		return problemBeanVO;
	}

	public void setProblemBeanVO(ProblemBeanVO problemBeanVO) {
		this.problemBeanVO = problemBeanVO;
	}

	public IProblemManagementService getProblemManagementService() {
		return problemManagementService;
	}

	public void setProblemManagementService(
			IProblemManagementService problemManagementService) {
		this.problemManagementService = problemManagementService;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	public ProblemBeanVO getProblemBeanFromDB() {
		return problemBeanFromDB;
	}

	public void setProblemBeanFromDB(ProblemBeanVO problemBeanFromDB) {
		this.problemBeanFromDB = problemBeanFromDB;
	}
	
	public Boolean getIsSuccessfullyInserted() {
		return isSuccessfullyInserted;
	}

	public void setIsSuccessfullyInserted(Boolean isSuccessfullyInserted) {
		this.isSuccessfullyInserted = isSuccessfullyInserted;
	}

	//form input setters and getters
	public String getProblem() {
		return problemBeanVO.getProblem();
	}
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Problem field is mandatory",shortCircuit=true)
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[a-zA-Z ]+$", message = "Problem field should not contain special characters and numbers", shortCircuit = true)
	public void setProblem(String problem) {
		this.problemBeanVO.setProblem(problem);
	}

	public String getDescription() {
		return problemBeanVO.getDescription();
	}
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Description field is mandatory",shortCircuit=true)
	@StringLengthFieldValidator(type = ValidatorType.FIELD, message = "Description Should be below 500 characters ", maxLength = "500")	
	public void setDescription(String description) {
		this.problemBeanVO.setDescription(description);
	}

	public String getState() {
		return problemBeanVO.getState();
	}
	
	public void setState(String state) {
		this.problemBeanVO.setState(state);
	}

	public String getDistrict() {
		return problemBeanVO.getDistrict();
	}	
	
	public void setDistrict(String district) {
		this.problemBeanVO.setDistrict(district);
	}

	public String getParliamentConstituency() {
		return parliamentConstituency;
	}
	
	public void setParliamentConstituency(String parliamentConstituency) {
		this.parliamentConstituency = parliamentConstituency;
	}

	public String getConstituency() {
		return problemBeanVO.getConstituency();
	}	
	
	public void setConstituency(String constituency) {
		this.problemBeanVO.setConstituency(constituency);
	}

	public String getMandal() {
		return problemBeanVO.getTehsil();
	}	
	
	public void setMandal(String mandal) {
		this.problemBeanVO.setTehsil(mandal);
	}

	public String getVillage() {
		return problemBeanVO.getVillage();
	}	
	
	public void setVillage(String village) {
		this.problemBeanVO.setVillage(village);
	}
	
	public String getBooth() {
		return problemBeanVO.getBooth();
	}

	public void setBooth(String booth) {
		this.problemBeanVO.setBooth(booth);
	}

	public String getReportedDate() {
		return problemBeanVO.getReportedDate();
	}

	public void setReportedDate(String reportedDate) {
		this.problemBeanVO.setReportedDate(reportedDate);
	}
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please Select Existing From Date",shortCircuit=true)
	public String getExistingFrom() {
		return problemBeanVO.getExistingFrom();
	}

	public void setExistingFrom(String existingFromDate) {
		this.problemBeanVO.setExistingFrom(existingFromDate);
	}

	public String getProbSource() {
		return probSource;
	}
	
	public void setProbSource(String probSource) {
		this.probSource = probSource;
		this.problemBeanVO.setProbSourceId(Long.parseLong(probSource));
	}

	public String getName() {
		return name;
	}
	
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[a-zA-Z ]+$", message = "Complained Person Name field should not contain special characters and numbers", shortCircuit = true)
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^([789]{1})([0123456789]{1})([0-9]{8})$", message = "Please Enter Mobile Number in Complained Person Details.", shortCircuit = true)
	@StringLengthFieldValidator(type = ValidatorType.FIELD, message = "Invalid Mobile number", minLength = "10", maxLength = "12")
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}
	@RegexFieldValidator(type = ValidatorType.FIELD, expression ="^[0-9 ]+[0-9]*$", message = "Invalid Telephone Number", shortCircuit = true)
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}
	@EmailValidator(type = ValidatorType.FIELD , message = "Please Enter a valid Email in Complained Person Details.")
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}	
	
	public Long getStatus() {
		return problemBeanVO.getProblemStatusId();
	}

	public void setStatus(Long status) {
		this.problemBeanVO.setProblemStatusId(status);
	}	

	public Long getProblemScope() {
		return problemBeanVO.getProblemImpactLevelId();
	}

	public void setProblemScope(Long problemScope) {
		this.problemBeanVO.setProblemImpactLevelId(problemScope);
	}	
	
	public String getDefaultStateId() {
		return defaultStateId;
	}

	public void setDefaultStateId(String defaultStateId) {
		this.defaultStateId = defaultStateId;
	}

	public String getDefaultDistId() {
		return defaultDistId;
	}

	public void setDefaultDistId(String defaultDistId) {
		this.defaultDistId = defaultDistId;
	}

	public String getDefaultConstId() {
		return defaultConstId;
	}

	public void setDefaultConstId(String defaultConstId) {
		this.defaultConstId = defaultConstId;
	}

	public String getDefaultState() {
		if(this.problemBeanVO.getState() != null && !this.problemBeanVO.getState().isEmpty())
			return this.problemBeanVO.getState();
		return getDefaultStateId();
	}
	
	public String getDefaultDistrict() {
		if(this.problemBeanVO.getDistrict() != null && !this.problemBeanVO.getDistrict().isEmpty())
			return this.problemBeanVO.getDistrict();		
		return getDefaultDistId();
	}	

	public String getDefaultConstituency() {
		if(this.problemBeanVO.getConstituency() != null && !this.problemBeanVO.getConstituency().isEmpty())
			return this.problemBeanVO.getConstituency();
		
		return getDefaultConstId();
	}

	public Long getProblemLocationId() {
		return problemBeanVO.getProblemImpactLevelValue();
	}
	
	@RequiredFieldValidator(type = ValidatorType.FIELD, message = "Problem Location is Mandatory",shortCircuit=true)
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Select valid Problem Location")	
	public void setProblemLocationId(Long problemLocationId) {
		this.problemBeanVO.setProblemImpactLevelValue(problemLocationId);
	}	

	public String getDefaultScopeId() {
		return defaultScopeId;
	}

	public void setDefaultScopeId(String defaultScopeId) {
		this.defaultScopeId = defaultScopeId;
	}
	
	public String getDefaultScope()
	{
		if(this.problemBeanVO.getProblemImpactLevelId() != null)
			return problemBeanVO.getProblemImpactLevelId().toString();
		return this.defaultScopeId;
	}	

	public Boolean getIsParliament() {
		return isParliament;
	}

	public void setIsParliament(Boolean isParliament) {
		this.isParliament = isParliament;
	}	

	public Long getPConstituencyId() {
		return pConstituencyId;
	}

	public void setPConstituencyId(Long constituencyId) {
		pConstituencyId = constituencyId;
	}
	
	public Long getCadreId() {
		return 	this.problemBeanVO.getCadreId();
	}

	public void setCadreId(Long cadreId) {
		this.problemBeanVO.setCadreId(cadreId);
	}

	public Long getDefaultPConstituency()
	{
		return pConstituencyId; 
	}

	public String execute() throws Exception
	{
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		if(user==null)
			return ERROR;
		
		problemBeanVO.setUserID(user.getRegistrationID());
		//problemBeanVO.setProblem(getProblem());
		//problemBeanVO.setDescription(getDescription());
		
		if(user.getUserStatus().equals(IConstants.PARTY_ANALYST_USER))
			problemBeanVO.setProblemPostedBy(IConstants.PARTY_ANALYST_USER);
		else
			problemBeanVO.setProblemPostedBy(IConstants.FREE_USER);
		problemBeanVO.setIsParliament(isParliament);
		/*problemBeanVO.setState(getState());
		problemBeanVO.setDistrict(getDistrict());
		problemBeanVO.setConstituency(getConstituency());
		problemBeanVO.setTehsil(getMandal());
		problemBeanVO.setVillage(getVillage());
		problemBeanVO.setHamlet(getHamlet());*/
		//problemBeanVO.setReportedDate(getReportedDate());
		//problemBeanVO.setExistingFrom(getExistingFromDate());
		//problemBeanVO.setProblemImpactLevelId(getProblemScope());
		//problemBeanVO.setProblemImpactLevelValue(getProblemLocationId());
		
		if(problemBeanVO.getProbSourceId() == 2L || problemBeanVO.getProbSourceId() == 3L)
		{
			problemBeanVO.setName(getName());
			problemBeanVO.setMobile(getMobile());
			problemBeanVO.setPhone(getPhone());
			problemBeanVO.setEmail(getEmail());			
			problemBeanVO.setAddress(getAddress());
		}
		
		//problemBeanVO.setProblemStatusId(getStatus());
		
		problemBeanVO.setYear(IConstants.PRESENT_YEAR);
		problemBeanVO.setDescription(problemBeanVO.getDescription().replace("\r\n"," "));	
		
		 problemBeanFromDB = problemManagementService.saveNewProblemData(problemBeanVO);
		 if(problemBeanFromDB.getExceptionEncountered() == null)
		 {
			 isSuccessfullyInserted = true;
			 problemBeanVO = new ProblemBeanVO();
			 
		 } else 
			 isSuccessfullyInserted = false;
		 
		return SUCCESS;
	}
	
	public void validate()
	{
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		if(user.getUserStatus().equals(IConstants.PARTY_ANALYST_USER))
		{
			if(problemBeanVO.getProbSourceId() == 0)
				addFieldError("sourceInput","Please Select Problem Source.");
			
			if(problemBeanVO.getProbSourceId() == 2 || problemBeanVO.getProbSourceId() == 3)
			{
				if(getName() == null || getName().trim().length() == 0)
					addFieldError("nameInput","Please Enter Name in Complained Person Details.");
			/*	if(getMobile() == null || getMobile().trim().length() == 0)
					addFieldError("mobileInput","Please Enter Mobile Number in Complained Person Details.");*/
				if(getAddress() == null || getAddress().trim().length() == 0)
					addFieldError("addressInput","Please Enter Address in Complained Person Details.");
			}
			
			if(problemBeanVO.getProbSourceId() == 4 && (problemBeanVO.getCadreId() == null ||
					problemBeanVO.getCadreId() == 0l))
			{
				addFieldError("cadreInput","Please Select Cadre From cadre Search.");
			}
		}
		
		user = null;
	 }

}
