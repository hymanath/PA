package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class BoothRangeVO implements Serializable{

	private static final long serialVersionUID = 1850066220803411882L;

	private String range;
	private Integer noOfFamilies = 0;
	private Integer totalVoters = 0;
	private Integer cadreAvailableFamilies = 0;
	private Integer cadreCount = 0;
	private Integer cadreAvailableFamiliesVoters = 0;
	
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	public Integer getNoOfFamilies() {
		return noOfFamilies;
	}
	public void setNoOfFamilies(Integer noOfFamilies) {
		this.noOfFamilies = noOfFamilies;
	}
	public Integer getTotalVoters() {
		return totalVoters;
	}
	public void setTotalVoters(Integer totalVoters) {
		this.totalVoters = totalVoters;
	}
	public Integer getCadreAvailableFamilies() {
		return cadreAvailableFamilies;
	}
	public void setCadreAvailableFamilies(Integer cadreAvailableFamilies) {
		this.cadreAvailableFamilies = cadreAvailableFamilies;
	}
	public Integer getCadreCount() {
		return cadreCount;
	}
	public void setCadreCount(Integer cadreCount) {
		this.cadreCount = cadreCount;
	}
	public Integer getCadreAvailableFamiliesVoters() {
		return cadreAvailableFamiliesVoters;
	}
	public void setCadreAvailableFamiliesVoters(Integer cadreAvailableFamiliesVoters) {
		this.cadreAvailableFamiliesVoters = cadreAvailableFamiliesVoters;
	}
	
}
