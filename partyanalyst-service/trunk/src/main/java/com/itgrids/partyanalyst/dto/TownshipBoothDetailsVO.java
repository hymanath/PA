package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Set;

public class TownshipBoothDetailsVO implements Serializable {
	//0-townshipID, 1-townshipName, 2-totalVoters, 3-validVoters, 
	//4-boothID, 5-partNo, 6-hamletId, 7-hamletName
	
	private Long townshipID;
	private String townshipName;
	private Long totalVoters;
	private Long validVoters;
	private Set<SelectOptionVO> booths;
	/*private Long boothID;
	private String partNo;*/

	private Set<SelectOptionVO> hamlets;
	/*private Long hamletId;
	private String hamletName;*/
	
	// logged in user voters total and percentage
	private String userVotesInfo;

	public Long getTownshipID() {
		return townshipID;
	}

	public void setTownshipID(Long townshipID) {
		this.townshipID = townshipID;
	}

	public String getTownshipName() {
		return townshipName;
	}

	public void setTownshipName(String townshipName) {
		this.townshipName = townshipName;
	}

	public Long getTotalVoters() {
		return totalVoters;
	}

	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}

	public Long getValidVoters() {
		return validVoters;
	}

	public void setValidVoters(Long validVoters) {
		this.validVoters = validVoters;
	}

	public Set<SelectOptionVO> getBooths() {
		return booths;
	}

	public void setBooths(Set<SelectOptionVO> booths) {
		this.booths = booths;
	}

	public Set<SelectOptionVO> getHamlets() {
		return hamlets;
	}

	public void setHamlets(Set<SelectOptionVO> hamlets) {
		this.hamlets = hamlets;
	}

	public String getUserVotesInfo() {
		return userVotesInfo;
	}

	public void setUserVotesInfo(String userVotesInfo) {
		this.userVotesInfo = userVotesInfo;
	}
}
