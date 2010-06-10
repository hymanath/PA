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
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
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
	private ProblemManagementDataVO problemManagementDataVO;
	private ProblemBeanVO problemBeanFromDB;
	private Boolean isSuccessfullyInserted;
	
	//form inputs
	private String problem;
	private String description;
	private String state;	
	private String district;
	private String parliamentConstituency;
	private String constituency;
	private String mandal;
	private String village;
	private String hamlet;
	private String reportedDate;
	private String existingFromDate;
	private String probSource;
	private String name;
	private String mobile;
	private String phone;
	private String email;
	private String address;
	private Long status;
	
	
	public void setServletRequest(HttpServletRequest request) {
	this.request = request;
		
	}	
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
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

	public ProblemManagementDataVO getProblemManagementDataVO() {
		return problemManagementDataVO;
	}

	public void setProblemManagementDataVO(
			ProblemManagementDataVO problemManagementDataVO) {
		this.problemManagementDataVO = problemManagementDataVO;
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
		return problem;
	}
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Problem field is mandatory",shortCircuit=true)
	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getDescription() {
		return description;
	}
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Description field is mandatory",shortCircuit=true)
	public void setDescription(String description) {
		this.description = description;
	}

	public String getState() {
		return state;
	}
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please Select State",shortCircuit=true)
	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please Select District",shortCircuit=true)
	public void setDistrict(String district) {
		this.district = district;
	}

	public String getParliamentConstituency() {
		return parliamentConstituency;
	}

	public void setParliamentConstituency(String parliamentConstituency) {
		this.parliamentConstituency = parliamentConstituency;
	}

	public String getConstituency() {
		return constituency;
	}
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please Select Constituency",shortCircuit=true)
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}

	public String getMandal() {
		return mandal;
	}
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please Select Mandal",shortCircuit=true)
	public void setMandal(String mandal) {
		this.mandal = mandal;
	}

	public String getVillage() {
		return village;
	}
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please Select Village",shortCircuit=true)
	public void setVillage(String village) {
		this.village = village;
	}

	public String getHamlet() {
		return hamlet;
	}
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please Select Hamlet",shortCircuit=true)
	public void setHamlet(String hamlet) {
		this.hamlet = hamlet;
	}

	public String getReportedDate() {
		return reportedDate;
	}

	public void setReportedDate(String reportedDate) {
		this.reportedDate = reportedDate;
	}
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please Select Existing From Date",shortCircuit=true)
	public String getExistingFromDate() {
		return existingFromDate;
	}

	public void setExistingFromDate(String existingFromDate) {
		this.existingFromDate = existingFromDate;
	}

	public String getProbSource() {
		return probSource;
	}
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please Select ProblemSource",shortCircuit=true)
	public void setProbSource(String probSource) {
		this.probSource = probSource;
	}

	public String getName() {
		return name;
	}
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Name field is mandatory",shortCircuit=true)
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMobile() {
		return mobile;
	}
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Mobile field is mandatory",shortCircuit=true)
	@StringLengthFieldValidator(type = ValidatorType.FIELD,message = "Mobile number should be 10 digits", shortCircuit = true,  minLength = "10",  maxLength = "10")	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Address field is mandatory",shortCircuit=true)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}	
	
	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String execute() throws Exception
	{
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		if(user==null)
			return ERROR;
		
		ProblemBeanVO problemBeanVO = new ProblemBeanVO();
		problemManagementDataVO = new ProblemManagementDataVO();
		problemBeanVO.setUserID(user.getRegistrationID());
		problemBeanVO.setProblem(getProblem());
		problemBeanVO.setDescription(getDescription());
		problemBeanVO.setState(getState());
		problemBeanVO.setDistrict(getDistrict());
		problemBeanVO.setConstituency(getConstituency());
		problemBeanVO.setTehsil(getMandal());
		problemBeanVO.setVillage(getVillage());
		problemBeanVO.setHamlet(getHamlet());
		problemBeanVO.setReportedDate(getReportedDate());
		problemBeanVO.setExistingFrom(getExistingFromDate());
		problemBeanVO.setProbSourceId(new Long(getProbSource()));
		if(problemBeanVO.getProbSourceId() != 1L)
		{
			problemBeanVO.setName(getName());
			problemBeanVO.setMobile(getMobile());
			problemBeanVO.setPhone(getPhone());
			problemBeanVO.setEmail(getEmail());			
			problemBeanVO.setAddress(getAddress());
		}
		
		problemBeanVO.setProblemStatusId(getStatus());
		
		problemBeanVO.setYear(IConstants.PRESENT_YEAR);
					
		 log.debug(problemBeanVO.getProblem());
		 log.debug(problemBeanVO.getDescription());
		 log.debug(problemBeanVO.getState());
		 log.debug(problemBeanVO.getDistrict());
		 log.debug(problemBeanVO.getConstituency());
		 log.debug(problemBeanVO.getTehsil());
		 log.debug(problemBeanVO.getHamlet());
		 log.debug(problemBeanVO.getReportedDate());
		 log.debug(problemBeanVO.getExistingFrom());
		 log.debug(problemBeanVO.getProbSource());
		 log.debug(problemBeanVO.getName());
		 log.debug(problemBeanVO.getMobile());
		 log.debug(problemBeanVO.getPhone());
		 log.debug(problemBeanVO.getEmail());
		 log.debug(problemBeanVO.getAddress());
		 problemBeanFromDB = problemManagementService.saveNewProblemData(problemBeanVO);
		 if(problemBeanFromDB.getExceptionEncountered() == null)
		 {
			 /*session.removeAttribute("stateList");
			 session.removeAttribute("districtList");
			 session.removeAttribute("constituencyList");
			 session.removeAttribute("mandalList");
			 session.removeAttribute("parliamentConstituencyList");*/
			 
			 isSuccessfullyInserted = true;
		 } else 
			 isSuccessfullyInserted = false;
		 
		return SUCCESS;
	}

}
