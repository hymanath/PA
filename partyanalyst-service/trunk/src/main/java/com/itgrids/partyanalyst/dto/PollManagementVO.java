package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class PollManagementVO implements Serializable{
    
	private Long divisions;
	private Long booths;
	private Long capturedVoters;
	private Long totalVoters;
	private Long totalCadre;
	private Long capturedCadre;
	private Long boothsCount;
	private Long inclinedVoters=0l;
	private Long unDecidedVoters=0l;
	private Long otherPartyVoters=0l;
	private Long nonOptedVoters=0l;
	
	
	public Long getCapturedVoters() {
		return capturedVoters;
	}
	public void setCapturedVoters(Long capturedVoters) {
		this.capturedVoters = capturedVoters;
	}
	public Long getTotalVoters() {
		return totalVoters;
	}
	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}
	public Long getTotalCadre() {
		return totalCadre;
	}
	public void setTotalCadre(Long totalCadre) {
		this.totalCadre = totalCadre;
	}
	public Long getCapturedCadre() {
		return capturedCadre;
	}
	public void setCapturedCadre(Long capturedCadre) {
		this.capturedCadre = capturedCadre;
	}
	public Long getBoothsCount() {
		return boothsCount;
	}
	public void setBoothsCount(Long boothsCount) {
		this.boothsCount = boothsCount;
	}
	public Long getInclinedVoters() {
		return inclinedVoters;
	}
	public void setInclinedVoters(Long inclinedVoters) {
		this.inclinedVoters = inclinedVoters;
	}
	public Long getUnDecidedVoters() {
		return unDecidedVoters;
	}
	public void setUnDecidedVoters(Long unDecidedVoters) {
		this.unDecidedVoters = unDecidedVoters;
	}
	public Long getOtherPartyVoters() {
		return otherPartyVoters;
	}
	public void setOtherPartyVoters(Long otherPartyVoters) {
		this.otherPartyVoters = otherPartyVoters;
	}
	public Long getNonOptedVoters() {
		return nonOptedVoters;
	}
	public void setNonOptedVoters(Long nonOptedVoters) {
		this.nonOptedVoters = nonOptedVoters;
	}
	public Long getDivisions() {
		return divisions;
	}
	public void setDivisions(Long divisions) {
		this.divisions = divisions;
	}
	public Long getBooths() {
		return booths;
	}
	public void setBooths(Long booths) {
		this.booths = booths;
	}
	
	
	
}
