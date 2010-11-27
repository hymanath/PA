package com.itgrids.partyanalyst.excel.booth;

public class BoothResultValueObject {
	
	
	private String totalNoOfValidVotes;
	private String rejectedVotes;
	private String totalVotes;
	private String tenderedVotes;
	private String partNumber;
	
	public String getTotalNoOfValidVotes() {
		return totalNoOfValidVotes;
	}

	public void setTotalNoOfValidVotes(String totalNoOfValidVotes) {
		this.totalNoOfValidVotes = totalNoOfValidVotes;
	}

	public String getRejectedVotes() {
		return rejectedVotes;
	}

	public void setRejectedVotes(String rejectedVotes) {
		this.rejectedVotes = rejectedVotes;
	}

	public String getTotalVotes() {
		return totalVotes;
	}

	public void setTotalVotes(String totalVotes) {
		this.totalVotes = totalVotes;
	}

	public String getTenderedVotes() {
		return tenderedVotes;
	}

	public void setTenderedVotes(String tenderedVotes) {
		this.tenderedVotes = tenderedVotes;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

}
