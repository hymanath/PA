package com.itgrids.partyanalyst.csv;

public class Assembly {
	private String index;
	private String constituency;
	private String candidate;
	private String party;
	private String votesPolled;
	private String margin;
	private String remarks;
	
	public String getCandidate() {
		return candidate;
	}
	public String getIndex() {
		return index;
	}
	
	public void setIndex(String index) {
		this.index = index;
	}
	public void setCandidate(String candidate) {
		this.candidate = candidate;
	}
	
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	public String getVotesPolled() {
		return votesPolled;
	}
	public void setVotesPolled(String votesPolled) {
		this.votesPolled = votesPolled;
	}
	public String getMargin() {
		return margin;
	}
	public void setMargin(String margin) {
		this.margin = margin;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
