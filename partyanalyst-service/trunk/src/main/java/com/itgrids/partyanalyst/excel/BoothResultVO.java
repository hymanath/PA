package com.itgrids.partyanalyst.excel;

public class BoothResultVO {

	private Long partNo;
	private Double votesEarned;
	
	public BoothResultVO(){
		
	}
	
	public BoothResultVO(Long partNo, Double votesEarned) {
		this.partNo = partNo;
		this.votesEarned = votesEarned;
	}

	public Long getPartNo() {
		return partNo;
	}

	public void setPartNo(Long partNo) {
		this.partNo = partNo;
	}

	public Double getVotesEarned() {
		return votesEarned;
	}

	public void setVotesEarned(Double votesEarned) {
		this.votesEarned = votesEarned;
	}
	
}
