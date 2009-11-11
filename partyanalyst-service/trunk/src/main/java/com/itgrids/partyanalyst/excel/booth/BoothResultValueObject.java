package com.itgrids.partyanalyst.excel.booth;

public class BoothResultValueObject {
	
	
	private Long totalNoOfValidVotes;
	private Long rejectedVotes;
	private Long totalVotes;
	private Long tenderedVotes;
	private String partNumber;
	
	public BoothResultValueObject() {
		totalNoOfValidVotes = new Long(0);
		rejectedVotes = new Long(0);
		totalVotes = new Long(0);
		tenderedVotes = new Long(0);
		partNumber = "";
	}

	public Long getTotalNoOfValidVotes() {
		return totalNoOfValidVotes;
	}
	public void setTotalNoOfValidVotes(Long totalNoOfValidVotes) {
		this.totalNoOfValidVotes = totalNoOfValidVotes;
	}
	public Long getRejectedVotes() {
		return rejectedVotes;
	}
	public void setRejectedVotes(Long rejectedVotes) {
		this.rejectedVotes = rejectedVotes;
	}
	public Long getTotalVotes() {
		return totalVotes;
	}
	public void setTotalVotes(Long totalVotes) {
		this.totalVotes = totalVotes;
	}
	public Long getTenderedVotes() {
		return tenderedVotes;
	}
	public void setTenderedVotes(Long tenderedVotes) {
		this.tenderedVotes = tenderedVotes;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

}
