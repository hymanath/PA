package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class CadreFamilyVO implements Serializable{

	
	private static final long serialVersionUID = 6884330323884968857L;
	private Long voterId;
	private String voterCadreNO;
	private Long educationId;
	private Long occupationId;
	private String voterName;
	private Long age;
	private String gender;
	

	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	public String getVoterCadreNO() {
		return voterCadreNO;
	}
	public void setVoterCadreNO(String voterCadreNO) {
		this.voterCadreNO = voterCadreNO;
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
	public String getVoterName() {
		return voterName;
	}
	public void setVoterName(String voterName) {
		this.voterName = voterName;
	}
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	
}
