package com.itgrids.partyanalyst.dto;

import java.util.List;

public class BoothPanelVO extends ResultStatus {

	private String partNo;
	private String Location;
	private String villagesCovered;
	private Long maleVoters;
	private Long femaleVoters;
	private Long totalVoters;
	private String mandal;
	private String wardInfo;
	private List<CastVO> castInfo;
	private List<ElectionInfoVO> elections; 
	
	public String getPartNo() {
		return partNo;
	}
	
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	
	public String getLocation() {
		return Location;
	}
	
	public void setLocation(String location) {
		Location = location;
	}
	
	public String getVillagesCovered() {
		return villagesCovered;
	}
	
	public void setVillagesCovered(String villagesCovered) {
		this.villagesCovered = villagesCovered;
	}
	
	public Long getMaleVoters() {
		return maleVoters;
	}
	
	public void setMaleVoters(Long maleVoters) {
		this.maleVoters = maleVoters;
	}
	
	public Long getFemaleVoters() {
		return femaleVoters;
	}
	
	public void setFemaleVoters(Long femaleVoters) {
		this.femaleVoters = femaleVoters;
	}
	
	public Long getTotalVoters() {
		return totalVoters;
	}
	
	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}
	
	public List<CastVO> getCastInfo() {
		return castInfo;
	}
	
	public void setCastInfo(List<CastVO> castInfo) {
		this.castInfo = castInfo;
	}

	public List<ElectionInfoVO> getElections() {
		return elections;
	}

	public void setElections(List<ElectionInfoVO> elections) {
		this.elections = elections;
	}

	public String getMandal() {
		return mandal;
	}

	public void setMandal(String mandal) {
		this.mandal = mandal;
	}

	public String getWardInfo() {
		return wardInfo;
	}

	public void setWardInfo(String wardInfo) {
		this.wardInfo = wardInfo;
	}
	
	
	
	
	
}
