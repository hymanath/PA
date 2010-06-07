package com.itgrids.partyanalyst.web.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.InfluencingPeopleBeanVO;
import com.itgrids.partyanalyst.service.IInfluencingPeopleService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

public class InfluencingPeopleSaveAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	private IInfluencingPeopleService influencingPeopleService;
	private String firstName,lastName,email,mobile,gender,cast,occupation,state,district,
						constituency,mandal,village,hamlet,party,position,influencingRange;
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public IInfluencingPeopleService getInfluencingPeopleService() {
		return influencingPeopleService;
	}

	public void setInfluencingPeopleService(
			IInfluencingPeopleService influencingPeopleService) {
		this.influencingPeopleService = influencingPeopleService;
	}
	
	@RequiredStringValidator(key="requiredstring" ,shortCircuit=true)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getConstituency() {
		return constituency;
	}

	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}

	public String getMandal() {
		return mandal;
	}

	public void setMandal(String mandal) {
		this.mandal = mandal;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

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
		
		return SUCCESS;
	}
	
	
}
