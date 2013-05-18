package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class ElectionResultsVerificationInfoVO implements Serializable{
	
	private Long constituencyId;
	private String constituencyName;
	private Long validVotes;
	private Long totalVotes;
	private String partNo;
	private Long maleVotes;
	private Long feMaleVotes;
	
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public Long getValidVotes() {
		return validVotes;
	}
	public void setValidVotes(Long validVotes) {
		this.validVotes = validVotes;
	}
	public Long getTotalVotes() {
		return totalVotes;
	}
	public void setTotalVotes(Long totalVotes) {
		this.totalVotes = totalVotes;
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	public Long getMaleVotes() {
		return maleVotes;
	}
	public void setMaleVotes(Long maleVotes) {
		this.maleVotes = maleVotes;
	}
	public Long getFeMaleVotes() {
		return feMaleVotes;
	}
	public void setFeMaleVotes(Long feMaleVotes) {
		this.feMaleVotes = feMaleVotes;
	}
	
}
