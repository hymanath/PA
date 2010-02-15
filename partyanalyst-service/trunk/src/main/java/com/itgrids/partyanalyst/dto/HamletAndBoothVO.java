package com.itgrids.partyanalyst.dto;

import java.util.List;

public class HamletAndBoothVO {

	private Long hamletId;
	private List<Long> boothConstituencyElectionIds;
	
	public HamletAndBoothVO(){
		
	}
			
	public HamletAndBoothVO(Long hamletId,
			List<Long> boothConstituencyElectionIds) {
		this.hamletId = hamletId;
		this.boothConstituencyElectionIds = boothConstituencyElectionIds;
	}

	public Long getHamletId() {
		return hamletId;
	}
	
	public void setHamletId(Long hamletId) {
		this.hamletId = hamletId;
	}
	
	public List<Long> getBoothConstituencyElectionIds() {
		return boothConstituencyElectionIds;
	}
	
	public void setBoothConstituencyElectionIds(
			List<Long> boothConstituencyElectionIds) {
		this.boothConstituencyElectionIds = boothConstituencyElectionIds;
	}
	
}
