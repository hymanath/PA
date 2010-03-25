package com.itgrids.partyanalyst.dto;

import java.util.List;

public class MandalWiseConstituencyElectionVO extends ResultStatus{

	private Long mandalId;
	private String mandalName;
	private Long totalVoters;
	private List<ConstituencyRevenueVillagesVO> constituencyRevenueVillagesVOs;
	
	public Long getMandalId() {
		return mandalId;
	}
	
	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}
	
	public String getMandalName() {
		return mandalName;
	}
	
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	
	public Long getTotalVoters() {
		return totalVoters;
	}
	
	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}
	
	public List<ConstituencyRevenueVillagesVO> getConstituencyRevenueVillagesVOs() {
		return constituencyRevenueVillagesVOs;
	}
	
	public void setConstituencyRevenueVillagesVOs(
			List<ConstituencyRevenueVillagesVO> constituencyRevenueVillagesVOs) {
		this.constituencyRevenueVillagesVOs = constituencyRevenueVillagesVOs;
	}
	
	
	
	
}
