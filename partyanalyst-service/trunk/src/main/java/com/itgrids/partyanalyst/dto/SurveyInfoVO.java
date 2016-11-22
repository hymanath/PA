package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class SurveyInfoVO implements Serializable{
	
	private Long contryId;
	private Long stateId;
	private Long districtId;
	private Long constituencyId;
	private Long mandalId;
	private Long muncipalityId;
	private Long panchayatId;
	private Long wardId;
	private Long boothId;
	private Long hamletId;
	private String name;
	private String VoterCardNo;
	private Long voterId;
	private Long age;
	private String mobileNo;
	private Long casteId;
	private String caste;
	private Long occupationId;
	private String occupation;
	private Long educateionId;
	private String educateion;
	private String gender;
	private String landmark;
	private Long localBodyElectionId;
	private Long townshipId;
	private Long parlemtId;
	private Long locationValue;
	private Long locationId;
	private String phoneNo;
	private String emailId;
	private Long surveyorId;
	private Long teamleadId;
	private String surveyorName;
	private String teamleadName;

	
	private Long targetCount=0l;
	private Long todayCount=0l;
	private Long totalCount=0l;
	
	private Long total2014Cadre=0l;
	private Long totalRenewalCadre=0l;
	private Long remainingRenewalCadre=0l;
	
	private String message;
	
	
	
	private List<SurveyInfoVO> surveyInfoVOList = new ArrayList<SurveyInfoVO>(0);
	
	public Long getContryId() {
		return contryId;
	}
	public void setContryId(Long contryId) {
		this.contryId = contryId;
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
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public Long getMandalId() {
		return mandalId;
	}
	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}
	public Long getMuncipalityId() {
		return muncipalityId;
	}
	public void setMuncipalityId(Long muncipalityId) {
		this.muncipalityId = muncipalityId;
	}
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	public Long getWardId() {
		return wardId;
	}
	public void setWardId(Long wardId) {
		this.wardId = wardId;
	}
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	public Long getHamletId() {
		return hamletId;
	}
	public void setHamletId(Long hamletId) {
		this.hamletId = hamletId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVoterCardNo() {
		return VoterCardNo;
	}
	public void setVoterCardNo(String voterCardNo) {
		VoterCardNo = voterCardNo;
	}
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Long getCasteId() {
		return casteId;
	}
	public void setCasteId(Long casteId) {
		this.casteId = casteId;
	}
	public String getCaste() {
		return caste;
	}
	public void setCaste(String caste) {
		this.caste = caste;
	}
	public Long getOccupationId() {
		return occupationId;
	}
	public void setOccupationId(Long occupationId) {
		this.occupationId = occupationId;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public Long getEducateionId() {
		return educateionId;
	}
	public void setEducateionId(Long educateionId) {
		this.educateionId = educateionId;
	}
	public String getEducateion() {
		return educateion;
	}
	public void setEducateion(String educateion) {
		this.educateion = educateion;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public Long getLocalBodyElectionId() {
		return localBodyElectionId;
	}
	public void setLocalBodyElectionId(Long localBodyElectionId) {
		this.localBodyElectionId = localBodyElectionId;
	}
	public Long getTownshipId() {
		return townshipId;
	}
	public void setTownshipId(Long townshipId) {
		this.townshipId = townshipId;
	}
	public Long getParlemtId() {
		return parlemtId;
	}
	public void setParlemtId(Long parlemtId) {
		this.parlemtId = parlemtId;
	}
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Long getSurveyorId() {
		return surveyorId;
	}
	public void setSurveyorId(Long surveyorId) {
		this.surveyorId = surveyorId;
	}
	public Long getTeamleadId() {
		return teamleadId;
	}
	public void setTeamleadId(Long teamleadId) {
		this.teamleadId = teamleadId;
	}
	public String getSurveyorName() {
		return surveyorName;
	}
	public void setSurveyorName(String surveyorName) {
		this.surveyorName = surveyorName;
	}
	public String getTeamleadName() {
		return teamleadName;
	}
	public void setTeamleadName(String teamleadName) {
		this.teamleadName = teamleadName;
	}
	public Long getTargetCount() {
		return targetCount;
	}
	public void setTargetCount(Long targetCount) {
		this.targetCount = targetCount;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getTodayCount() {
		return todayCount;
	}
	public void setTodayCount(Long todayCount) {
		this.todayCount = todayCount;
	}
	public List<SurveyInfoVO> getSurveyInfoVOList() {
		return surveyInfoVOList;
	}
	public void setSurveyInfoVOList(List<SurveyInfoVO> surveyInfoVOList) {
		this.surveyInfoVOList = surveyInfoVOList;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getTotal2014Cadre() {
		return total2014Cadre;
	}
	public void setTotal2014Cadre(Long total2014Cadre) {
		this.total2014Cadre = total2014Cadre;
	}
	public Long getTotalRenewalCadre() {
		return totalRenewalCadre;
	}
	public void setTotalRenewalCadre(Long totalRenewalCadre) {
		this.totalRenewalCadre = totalRenewalCadre;
	}
	public Long getRemainingRenewalCadre() {
		return remainingRenewalCadre;
	}
	public void setRemainingRenewalCadre(Long remainingRenewalCadre) {
		this.remainingRenewalCadre = remainingRenewalCadre;
	}

	
	
}
