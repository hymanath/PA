package com.itgrids.partyanalyst.web.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import com.itgrids.partyanalyst.dto.InfluencingPeopleBeanVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.IInfluencingPeopleService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

public class InfluencingPeopleSaveAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private IInfluencingPeopleService influencingPeopleService;
	private String registrationId;
	private int resultStatus = 3;
	private HttpSession session;
	
	private String scopeState,scopeDistrict,scopeConstituency;
	private String scopeMandal,scopeVillage,scopeBooth;
	private Long pConstituencyId;
	private InfluencingPeopleBeanVO influencingPeopleBeanVO = new InfluencingPeopleBeanVO();
	
	public int getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(int resultStatus) {
		this.resultStatus = resultStatus;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.setRequest(request);
	}
	
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public IInfluencingPeopleService getInfluencingPeopleService() {
		return influencingPeopleService;
	}

	public void setInfluencingPeopleService(
			IInfluencingPeopleService influencingPeopleService) {
		this.influencingPeopleService = influencingPeopleService;
	}
	
	
	public String getFirstName() {
		return influencingPeopleBeanVO.getFirstName();
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "First Name is Mandatory",  shortCircuit = true)
	@RegexFieldValidator(type = ValidatorType.FIELD,expression = "^[a-zA-Z ]+$", message = "First Name should not contain special characters and numbers", shortCircuit = true)
	public void setFirstName(String firstName) {
		this.influencingPeopleBeanVO.setFirstName(firstName);
	}

	
	public String getLastName() {
		return influencingPeopleBeanVO.getLastName();
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Last Name is Mandatory")
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[a-zA-Z ]+$", message = "Last Name should not contain special characters and numbers", shortCircuit = true)
	public void setLastName(String lastName) {
		this.influencingPeopleBeanVO.setLastName(lastName);
	}

	public String getEmail() {
		return influencingPeopleBeanVO.getEmail();
	}
	 @EmailValidator(type = ValidatorType.FIELD, message = " enter a valid for email.")
	public void setEmail(String email) {
		this.influencingPeopleBeanVO.setEmail(email);
	}

	public String getMobile() {
		return influencingPeopleBeanVO.getMobile();
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Mobile Number is Mandatory", shortCircuit = true)
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^([789]{1})([012346789]{1})([0-9]{8})$", message = "Invalid Mobile Number", shortCircuit = true)
	@StringLengthFieldValidator(type = ValidatorType.FIELD, message = "Invalid Mobile number", minLength = "10", maxLength = "12")	
	public void setMobile(String mobile) {
		this.influencingPeopleBeanVO.setMobile(mobile);
	}


	public String getGender() {
		return influencingPeopleBeanVO.getGender();
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please select Gender")
	public void setGender(String gender) {
		this.influencingPeopleBeanVO.setGender(gender);
	}

	public String getCast() {
		return influencingPeopleBeanVO.getCast();
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please select the Caste Category")
	public void setCast(String cast) {
		this.influencingPeopleBeanVO.setCast(cast);
	}

	public String getOccupation() {
		return influencingPeopleBeanVO.getOccupation();
	}

	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Invalid Occupation Selection")
	public void setOccupation(String occupation) {
		this.influencingPeopleBeanVO.setOccupation(occupation);
	}

	
	public String getState() {
		return influencingPeopleBeanVO.getState();
	}

	//@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Invalid State Selection")
	public void setState(String state) {
		this.influencingPeopleBeanVO.setState(state);
	}

	
	public String getDistrict() {
		return influencingPeopleBeanVO.getDistrict();
	}

	//@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Invalid District Selection")
	public void setDistrict(String district) {
		this.influencingPeopleBeanVO.setDistrict(district);
	}


	public String getConstituency() {
		return influencingPeopleBeanVO.getConstituency();
	}

	//@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Invalid Constituency Selection")
	public void setConstituency(String constituency) {
		this.influencingPeopleBeanVO.setConstituency(constituency);
	}

	
	public String getMandal() {
		return influencingPeopleBeanVO.getMandal();
	}

	//@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Invalid Tehsil/Muncipality Selection")
	public void setMandal(String mandal) {
		this.influencingPeopleBeanVO.setMandal(mandal);
	}
	
	
	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public String getVillage() {
		return influencingPeopleBeanVO.getVillage();
	}

	public void setVillage(String village) {
		this.influencingPeopleBeanVO.setVillage(village);
	}

	public String getHamlet() {
		return influencingPeopleBeanVO.getHamlet();
	}

	public void setHamlet(String hamlet) {
		this.influencingPeopleBeanVO.setHamlet(hamlet);
	}

	public String getParty() {
		return influencingPeopleBeanVO.getParty();
	}

	
	public void setParty(String party) {
		this.influencingPeopleBeanVO.setParty(party);
	}

	
	public String getPosition() {
		return influencingPeopleBeanVO.getPosition();
	}

	
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Invalid Position Selection")
	public void setPosition(String position) {
		this.influencingPeopleBeanVO.setPosition(position);
	}

	
	public String getInfluencingRange() {
		return influencingPeopleBeanVO.getInfluencingRange();
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please Select Influencing Range",shortCircuit=true)
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Invalid Influencing Range Selection")
	public void setInfluencingRange(String influencingRange) {
		this.influencingPeopleBeanVO.setInfluencingRange(influencingRange);
	}

	public String getMiddleName() {
		return influencingPeopleBeanVO.getMiddleName();
	}

	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[a-zA-Z ]+$", message = "Middle Name should not contain special characters and numbers", shortCircuit = true)
	public void setMiddleName(String middleName) {
		this.influencingPeopleBeanVO.setMiddleName(middleName);
	}

	
	public String getFatherOrSpouseName() {
		return influencingPeopleBeanVO.getFatherOrSpouseName();
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Father or Spouse name is mandatory")
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[a-zA-Z ]+$", message = "Father or Spouse Name should not contain special characters and numbers", shortCircuit = true)
	public void setFatherOrSpouseName(String fatherOrSpouseName) {
		this.influencingPeopleBeanVO.setFatherOrSpouseName(fatherOrSpouseName);
	}

	public String getHouseNo() {
		return influencingPeopleBeanVO.getHouseNo();
	}

	public void setHouseNo(String houseNo) {
		this.influencingPeopleBeanVO.setHouseNo(houseNo);
	}

	public String getStreetName() {
		return influencingPeopleBeanVO.getStreetName();
	}

	public void setStreetName(String streetName) {
		this.influencingPeopleBeanVO.setStreetName(streetName);
	}

	public String getPincode() {
		return influencingPeopleBeanVO.getPincode();
	}

	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "[0-9][0-9][0-9][0-9][0-9][0-9]", message = "Pin Code should contain digits ", shortCircuit = true)
	@StringLengthFieldValidator(type = ValidatorType.FIELD, message = "Pincode length should be 6 digits only", minLength = "6", maxLength = "6")
	public void setPincode(String pincode) {
		this.influencingPeopleBeanVO.setPincode(pincode);
	}

	
	public String getWardOrHamlet() {
		return influencingPeopleBeanVO.getWardOrHamlet();
	}

	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Invalid ward or hamlet selection")
	public void setWardOrHamlet(String wardOrHamlet) {
		this.influencingPeopleBeanVO.setWardOrHamlet(wardOrHamlet);
	}

	public String getWindowTask() {
		return influencingPeopleBeanVO.getWindowTask();
	}

	public void setWindowTask(String windowTask) {
		this.influencingPeopleBeanVO.setWindowTask(windowTask);
	}


	public String getInfluencingPersonId() {
		return influencingPeopleBeanVO.getInfluencingPersonId();
	}

	public void setInfluencingPersonId(String influencingPersonId) {
		this.influencingPeopleBeanVO.setInfluencingPersonId(influencingPersonId);
	}

	
	public String getInfluencingScopeValue() {
		return influencingPeopleBeanVO.getInfluencingScopeValue();
	}

	public void setInfluencingScopeValue(String influencingScopeValue) {
		this.influencingPeopleBeanVO.setInfluencingScopeValue(influencingScopeValue);
	}

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public String getBooth() {
		return influencingPeopleBeanVO.getBooth();
	}

	public void setBooth(String booth) {
		this.influencingPeopleBeanVO.setBooth(booth);
	}

	public String getScopeState() {
		return scopeState;
	}

	public void setScopeState(String scopeState) {
		this.scopeState = scopeState;
	}

	public String getScopeDistrict() {
		return scopeDistrict;
	}

	public void setScopeDistrict(String scopeDistrict) {
		this.scopeDistrict = scopeDistrict;
	}

	public String getScopeConstituency() {
		return scopeConstituency;
	}

	public void setScopeConstituency(String scopeConstituency) {
		this.scopeConstituency = scopeConstituency;
	}

	public String getScopeMandal() {
		return scopeMandal;
	}

	public void setScopeMandal(String scopeMandal) {
		this.scopeMandal = scopeMandal;
	}

	public String getScopeVillage() {
		return scopeVillage;
	}

	public void setScopeVillage(String scopeVillage) {
		this.scopeVillage = scopeVillage;
	}

	public String getScopeBooth() {
		return scopeBooth;
	}

	public void setScopeBooth(String scopeBooth) {
		this.scopeBooth = scopeBooth;
	}
	
	public Long getPConstituencyId() {
		return influencingPeopleBeanVO.getPConstituencyId();
	}

	public void setPConstituencyId(Long constituencyId) {
		influencingPeopleBeanVO.setPConstituencyId(constituencyId);
	}
	
	public Long getDefaultPConstituency()
	{
		return pConstituencyId; 
	}


	public String execute() throws Exception{
		
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO)session.getAttribute("USER");
		
		influencingPeopleBeanVO.setRegistrationId(regVO.getRegistrationID().toString());
		influencingPeopleBeanVO.setAccessType(regVO.getAccessType());
				
		if("2".equalsIgnoreCase(getInfluencingRange()))
		{
			influencingPeopleBeanVO.setInfluencingRange(IConstants.STATE_LEVEL);
			influencingPeopleBeanVO.setInfluencingScopeValue(getScopeState());
		}
		
		else if("3".equalsIgnoreCase(getInfluencingRange()))
		{
			influencingPeopleBeanVO.setInfluencingRange(IConstants.DISTRICT_LEVEL);
			influencingPeopleBeanVO.setInfluencingScopeValue(getScopeDistrict());
		}
		
		else if("4".equalsIgnoreCase(getInfluencingRange()))
		{
			influencingPeopleBeanVO.setInfluencingRange(IConstants.CONSTITUENCY_LEVEL);
			influencingPeopleBeanVO.setInfluencingScopeValue(getScopeConstituency());
		}
		
		else if("5".equalsIgnoreCase(getInfluencingRange()))
		{
			influencingPeopleBeanVO.setInfluencingRange(IConstants.MANDAL_LEVEL);
			influencingPeopleBeanVO.setInfluencingScopeValue(getScopeMandal().substring(1));
		}
		
		else if("6".equalsIgnoreCase(getInfluencingRange()))
		{
			influencingPeopleBeanVO.setInfluencingRange(IConstants.CENSUS_VILLAGE_LEVEL);
			influencingPeopleBeanVO.setInfluencingScopeValue(getScopeVillage().substring(1));
		}
		
		else if("7".equalsIgnoreCase(getInfluencingRange()))
		{
			influencingPeopleBeanVO.setInfluencingRange(IConstants.MUNCIPALITY_CORPORATION_LEVEL);
			influencingPeopleBeanVO.setInfluencingScopeValue(this.getScopeMandal().substring(1));
		}
		
		else if("8".equalsIgnoreCase(getInfluencingRange()))
		{
			influencingPeopleBeanVO.setInfluencingRange(IConstants.WARD);
			influencingPeopleBeanVO.setInfluencingScopeValue(getScopeVillage().substring(1));
		}
		
		else if("9".equalsIgnoreCase(getInfluencingRange()))
		{
			influencingPeopleBeanVO.setInfluencingRange(IConstants.BOOTH);
			influencingPeopleBeanVO.setInfluencingScopeValue(getScopeBooth());
		}
						
		InfluencingPeopleBeanVO result = influencingPeopleService.saveInfluencePeopleInfo(influencingPeopleBeanVO);
		
		if(result.getExceptionEncountered() != null){			
			return ERROR;
		}
		else{
			influencingPeopleBeanVO =new InfluencingPeopleBeanVO();
		}
		resultStatus = result.getResultCode();
		return SUCCESS;
	}
	
	public void validate()
	{/*
		boolean sstate = scopeState.equalsIgnoreCase("0");
		boolean sDistrict = sstate || scopeDistrict.equalsIgnoreCase("0");
		boolean sConstituency = sDistrict || scopeConstituency.equalsIgnoreCase("0");
		boolean sMandal = sConstituency || scopeMandal.equalsIgnoreCase("0");
		boolean sVillage = sMandal || scopeVillage.equalsIgnoreCase("0");
		boolean sBooth =  sMandal || scopeBooth.equalsIgnoreCase("0");
		
		if(influencingRange.equalsIgnoreCase("2") && sstate)
		{
			addFieldError("influencingRange","Please Select All Required Fields in Influencing Scope Details");
			
		}
		else if(influencingRange.equalsIgnoreCase("3") && sDistrict)
		{
			addFieldError("influencingRange","Please Select All Required Fields in Influencing Scope Details");
			
		}
		else if(influencingRange.equalsIgnoreCase("4") && sConstituency)
		{
			addFieldError("influencingRange","Please Select All Required Fields in Influencing Scope Details");
			
		}
		else if(influencingRange.equalsIgnoreCase("5") && sMandal)
		{
			addFieldError("influencingRange","Please Select All Required Fields in Influencing Scope Details");
			
		}
		else if(influencingRange.equalsIgnoreCase("6") && sVillage)
		{
			addFieldError("influencingRange","Please Select All Required Fields in Influencing Scope Details");
			
		}
		else if(influencingRange.equalsIgnoreCase("7") && sMandal)
		{
			addFieldError("influencingRange","Please Select All Required Fields in Influencing Scope Details");
			
		}
		else if(influencingRange.equalsIgnoreCase("8") && sVillage)
		{
			addFieldError("influencingRange","Please Select All Required Fields in Influencing Scope Details");
			
		}
		else if(influencingRange.equalsIgnoreCase("9") && sBooth)
		{
			addFieldError("influencingRange","Please Select All Required Fields in Influencing Scope Details");
			
		}
		*/
				
	}

}