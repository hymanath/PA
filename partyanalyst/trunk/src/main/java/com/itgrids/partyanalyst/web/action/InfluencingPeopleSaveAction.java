package com.itgrids.partyanalyst.web.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.InfluencingPeopleBeanVO;
import com.itgrids.partyanalyst.service.IInfluencingPeopleService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

public class InfluencingPeopleSaveAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private IInfluencingPeopleService influencingPeopleService;
	private String firstName,lastName,email,mobile,gender,cast,occupation,state,district,
						constituency,mandal,village,hamlet,party,position,influencingRange;
	private int resultStatus = 3;
	
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
	
	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please Enter First Name",shortCircuit=true)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please Enter Last Name",shortCircuit=true)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please Select Gender",shortCircuit=true)
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCast() {
		return cast;
	}

	public void setCast(String cast) {
		this.cast = cast;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please Select State",shortCircuit=true)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please Select District",shortCircuit=true)
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please Select Constituency",shortCircuit=true)
	public String getConstituency() {
		return constituency;
	}

	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please Select Mandal",shortCircuit=true)
	public String getMandal() {
		return mandal;
	}

	public void setMandal(String mandal) {
		this.mandal = mandal;
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please Select Village",shortCircuit=true)
	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please Select Hamlet",shortCircuit=true)
	public String getHamlet() {
		return hamlet;
	}

	public void setHamlet(String hamlet) {
		this.hamlet = hamlet;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please Select Position",shortCircuit=true)
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, message = "Please Select Influencing Range",shortCircuit=true)
	public String getInfluencingRange() {
		return influencingRange;
	}

	public void setInfluencingRange(String influencingRange) {
		this.influencingRange = influencingRange;
	}

	public String execute() throws Exception{
		InfluencingPeopleBeanVO influencingPeopleBeanVO = new InfluencingPeopleBeanVO();
		influencingPeopleBeanVO.setFirstName(firstName);
		influencingPeopleBeanVO.setLastName(lastName);
		influencingPeopleBeanVO.setEmail(email);
		influencingPeopleBeanVO.setMobile(mobile);
		influencingPeopleBeanVO.setGender(gender);
		influencingPeopleBeanVO.setOccupation(occupation);
		influencingPeopleBeanVO.setCast(cast);
		influencingPeopleBeanVO.setParty(party);
		influencingPeopleBeanVO.setInfluencingRange(influencingRange);
		influencingPeopleBeanVO.setPosition(position);
		influencingPeopleBeanVO.setHamlet(hamlet);
		Map<String, Long> influRangeAndValueMap = new HashMap<String, Long>();
		try{
			influRangeAndValueMap.put(IConstants.STATE_LEVEL, new Long(state));
			influRangeAndValueMap.put(IConstants.DISTRICT_LEVEL, new Long(district));
			influRangeAndValueMap.put(IConstants.CONSTITUENCY_LEVEL, new Long(constituency));
			influRangeAndValueMap.put(IConstants.TEHSIL_LEVEL, new Long(mandal));
			influRangeAndValueMap.put(IConstants.REVENUE_VILLAGE_LEVEL, new Long(village));
			influRangeAndValueMap.put(IConstants.HAMLET_LEVEL,new Long(hamlet));	
		}catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
		
		
		InfluencingPeopleBeanVO result = influencingPeopleService.saveInfluencePeopleInfo(influencingPeopleBeanVO, influRangeAndValueMap);
		
		if(result.getExceptionEncountered() != null){			
			return ERROR;
		}
		resultStatus = result.getResultCode();
		return SUCCESS;
	}

	
	
	
}
