package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class HamletBoothsAndVotersVO implements Serializable {
	private Long hamletID;
	private String hamletName;
	private String boothPartNos;
	private Long totalVoters;
	public Long getHamletID() {
		return hamletID;
	}
	public void setHamletID(Long hamletID) {
		this.hamletID = hamletID;
	}
	public String getHamletName() {
		return hamletName;
	}
	public void setHamletName(String hamletName) {
		this.hamletName = hamletName;
	}
	public String getBoothPartNos() {
		return boothPartNos;
	}
	public void setBoothPartNos(String boothPartNos) {
		this.boothPartNos = boothPartNos;
	}
	public Long getTotalVoters() {
		return totalVoters;
	}
	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}
	
	
}
