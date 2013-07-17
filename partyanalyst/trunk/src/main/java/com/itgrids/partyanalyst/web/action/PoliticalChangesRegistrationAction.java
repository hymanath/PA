package com.itgrids.partyanalyst.web.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.PoliticalChangesVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.IPoliticalChangesService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

public class PoliticalChangesRegistrationAction extends ActionSupport implements
		ServletRequestAware {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ServletContext context;
    private HttpSession session;
	private HttpServletRequest request;
	private PoliticalChangesVO politicalChangesVO = new PoliticalChangesVO();
	private String title,description,occuredDate,reportedDate,party,effectRange,localPoliticalChangeId;
	private String type,externalPerson,name,mobile,telephoneNo,email,address,informationSource,range,rangeId;
	private int resultStatus = 3;
	private String successMsg;
	private IPoliticalChangesService politicalChangesService;
	
	
	public PoliticalChangesVO getPoliticalChangesVO() {
		return politicalChangesVO;
	}

	public void setPoliticalChangesVO(PoliticalChangesVO politicalChangesVO) {
		this.politicalChangesVO = politicalChangesVO;
	}	
	
	public int getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(int resultStatus) {
		this.resultStatus = resultStatus;
	}	
	
	public String getLocalPoliticalChangeId() {
		return politicalChangesVO.getLocalPoliticalChangeId().toString();
	}

	public void setLocalPoliticalChangeId(String localPoliticalChangeId) {
		this.politicalChangesVO.setLocalPoliticalChangeId(new Long(localPoliticalChangeId));
	}

	public String getRange() {
		return politicalChangesVO.getRange();
	}

	public void setRange(String range) {
		this.politicalChangesVO.setRange(range);
	}

	public String getRangeId() {
		return politicalChangesVO.getRangeId().toString();
	}

	public void setRangeId(String rangeId) {
		this.politicalChangesVO.setRangeId(new Long(rangeId));
	}

	public String getInformationSource() {
		return politicalChangesVO.getInformationSource();
	}

	public void setInformationSource(String informationSource) {
		this.politicalChangesVO.setInformationSource(informationSource);
	}

	public String getDescription() {
		return politicalChangesVO.getDescription();
	}

	public void setDescription(String description) {
		this.politicalChangesVO.setDescription(description);
	}

	public String getReportedDate() {
		return politicalChangesVO.getReportedDate();
	}

	public void setReportedDate(String reportedDate) {
		this.politicalChangesVO.setReportedDate(reportedDate);
	}

	public String getTelephoneNo() {
		return politicalChangesVO.getTelephoneNo();
	}

	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[0-9 ]+[0-9]*$", message = "Telephone Number should not contain alphabets and special characters")
	public void setTelephoneNo(String telephoneNo) {
		this.politicalChangesVO.setTelephoneNo(telephoneNo);
	}

	public String getEmail() {
		return politicalChangesVO.getEmail();
	}
	
	@EmailValidator(message = "Invalid Email", shortCircuit = true)
	public void setEmail(String email) {
		this.politicalChangesVO.setEmail(email);
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Address is Mandatory",shortCircuit=true)
	public String getAddress() {
		return politicalChangesVO.getAddress();
	}

	public void setAddress(String address) {
		this.politicalChangesVO.setAddress(address);
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Name is mandatory",shortCircuit=true)
	@RegexFieldValidator(type = ValidatorType.FIELD,expression = "^[a-zA-Z]+$", message = "Name should not contain special characters and numbers", shortCircuit = true)
	public String getName() {
		return politicalChangesVO.getName();
	}

	public void setName(String name) {
		this.politicalChangesVO.setName(name);
	}

		
	public String getMobile() {
		return politicalChangesVO.getMobile();
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Mobile Number is Mandatory", shortCircuit = true)
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^([789]{1})([02346789]{1})([0-9]{8})$", message = "Invalid Mobile Number", shortCircuit = true)
	@StringLengthFieldValidator(type = ValidatorType.FIELD, message = "Invalid Mobile number", minLength = "10", maxLength = "12")	
	public void setMobile(String mobile) {
		this.politicalChangesVO.setMobile(mobile);
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public IPoliticalChangesService getPoliticalChangesService() {
		return politicalChangesService;
	}

	public void setPoliticalChangesService(
			IPoliticalChangesService politicalChangesService) {
		this.politicalChangesService = politicalChangesService;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExternalPerson() {
		return externalPerson;
	}

	public void setExternalPerson(String externalPerson) {
		this.externalPerson = externalPerson;
	}
   
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please select Occured Date",shortCircuit=true)
	public String getOccuredDate() {
		return politicalChangesVO.getOccuredDate();
	}

	public void setOccuredDate(String occuredDate) {
		this.politicalChangesVO.setOccuredDate(occuredDate);
	}	
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Select Party is required",shortCircuit=true)
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Select Valid Party")
	public String getParty() {
		return politicalChangesVO.getParty().toString();
	}

	public void setParty(String party) {
		this.politicalChangesVO.setParty(new Long(party));
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Select Effected Range is required",shortCircuit=true)
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Select Valid Effective Scope")
	public String getEffectRange() {
		return effectRange;
	}

	public void setEffectRange(String effectRange) {
		this.effectRange = effectRange;
	}
		
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please Enter Title",shortCircuit=true)
	public void setTitle(String title) {
		this.politicalChangesVO.setTitle(title);
	}
	public String getTitle() {
		return politicalChangesVO.getTitle();
	}	
		
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}	
	
	public String getSuccessMsg() {
		return successMsg;
	}

	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}

	public String execute() throws Exception
	{
		session = request.getSession();
		externalPerson = IConstants.EXTERNAL_PERSON;
				
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		Long userID = user.getRegistrationID();
		
		politicalChangesVO.setUserId(userID);
		ResultStatus result = politicalChangesService.savePoliticalChangeDataReceivedFromUser(politicalChangesVO, type);
		resultStatus = result.getResultCode();
		if(resultStatus == 0)
		{
			politicalChangesVO = new PoliticalChangesVO();
			successMsg = "success";
		}
			
		else if(resultStatus == 1)
			return "fail";
		
				
		return Action.SUCCESS;	
	}
}

