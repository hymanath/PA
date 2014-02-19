package com.itgrids.partyanalyst.dto;

import java.util.List;

public class HouseHoldVotersVO {
	
	private Long voterId;
	private Long voterFamilyRelationId;
	private Long educationId;
	private Long occupationId;
	private Long socialPstnId;
	
	private List<HouseHoldVotersVO> houseHoldsVoters;
	private Long boothId;
	private String houseNo;
	
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public List<HouseHoldVotersVO> getHouseHoldsVoters() {
		return houseHoldsVoters;
	}
	public void setHouseHoldsVoters(List<HouseHoldVotersVO> houseHoldsVoters) {
		this.houseHoldsVoters = houseHoldsVoters;
	}
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	public Long getVoterFamilyRelationId() {
		return voterFamilyRelationId;
	}
	public void setVoterFamilyRelationId(Long voterFamilyRelationId) {
		this.voterFamilyRelationId = voterFamilyRelationId;
	}
	public Long getEducationId() {
		return educationId;
	}
	public void setEducationId(Long educationId) {
		this.educationId = educationId;
	}
	public Long getOccupationId() {
		return occupationId;
	}
	public void setOccupationId(Long occupationId) {
		this.occupationId = occupationId;
	}
	public Long getSocialPstnId() {
		return socialPstnId;
	}
	public void setSocialPstnId(Long socialPstnId) {
		this.socialPstnId = socialPstnId;
	}
}
