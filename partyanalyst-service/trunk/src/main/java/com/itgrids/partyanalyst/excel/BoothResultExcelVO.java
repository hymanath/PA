package com.itgrids.partyanalyst.excel;

public class BoothResultExcelVO {

	private Long partNo;
	private Double votesEarned;
	
	public BoothResultExcelVO(){
		
	}

	public BoothResultExcelVO(Long partNo, Double votesEarned) {
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
