package com.itgrids.partyanalyst.dto;
import java.util.List;

public class EffectedBoothsVo {
	
	private Long constId;
	private List<Long> panchayatIds;
	private Long wonPartyId;
	public Long getConstId() {
		return constId;
	}
	public void setConstId(Long constId) {
		this.constId = constId;
	}
	public List<Long> getPanchayatIds() {
		return panchayatIds;
	}
	public void setPanchayatIds(List<Long> panchayatIds) {
		this.panchayatIds = panchayatIds;
	}
	public Long getWonPartyId() {
		return wonPartyId;
	}
	public void setWonPartyId(Long wonPartyId) {
		this.wonPartyId = wonPartyId;
	}
	
	

}
