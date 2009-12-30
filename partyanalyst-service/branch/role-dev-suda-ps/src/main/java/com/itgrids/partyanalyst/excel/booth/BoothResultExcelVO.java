package com.itgrids.partyanalyst.excel.booth;

public class BoothResultExcelVO {

	private String partNo;
	private Long votesEarned;
	
	public BoothResultExcelVO(){
		
	}

	public BoothResultExcelVO(String partNo, Long votesEarned) {
		this.partNo = partNo;
		this.votesEarned = votesEarned;
	}

	public String getPartNo() {
		return partNo;
	}

	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}

	public Long getVotesEarned() {
		return votesEarned;
	}

	public void setVotesEarned(Long votesEarned) {
		this.votesEarned = votesEarned;
	}
	
}
