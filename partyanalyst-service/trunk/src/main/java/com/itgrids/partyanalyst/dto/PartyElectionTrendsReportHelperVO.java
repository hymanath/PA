package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class PartyElectionTrendsReportHelperVO  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1000000000L;
	//private String party;
	//private Integer electionYear;
//	private Long  totalVoters;
//	private Long totalVotesPolled;
	private String name;
	private Long votesEarned=0L;	
	private Long marginVotes=0L;
	private Double marginVotesPercentage=0.0;
	private Double percentage=0.0;
	private Long rank=0L;
	
	public Long getRank() {
		return rank;
	}
	public void setRank(Long rank) {
		this.rank = rank;
	}
	public Double getMarginVotesPercentage() {
		return marginVotesPercentage;
	}
	public void setMarginVotesPercentage(Double marginVotesPercentage) {
		this.marginVotesPercentage = marginVotesPercentage;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPercentage() {
		return percentage;
	}
	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
	public Long getMarginVotes() {
		return marginVotes;
	}
	public void setMarginVotes(Long marginVotes) {
		this.marginVotes = marginVotes;
	}
	public Long getVotesEarned() {
		return votesEarned;
	}
	public void setVotesEarned(Long votesEarned) {
		this.votesEarned = votesEarned;
	}

	public PartyElectionTrendsReportHelperVO(String name,Long votesEarned, Double percentage
			) {
		super();
		this.name = name;
		this.percentage = percentage;
		
		this.votesEarned = votesEarned;
	}
	/*public PartyElectionTrendsReportHelperVO(String name, Double percentage,
			Long marginVotes, Double marginVotesPercentage, Long votesEarned) {
		super();
		this.name = name;
		this.percentage = percentage;
		this.marginVotes = marginVotes;
		this.marginVotesPercentage = marginVotesPercentage;
		this.votesEarned = votesEarned;
	}*/
	public PartyElectionTrendsReportHelperVO(String name, Long votesEarned,
			Long marginVotes, Double marginVotesPercentage, Double percentage) {
		super();
		this.name = name;
		this.votesEarned = votesEarned;
		this.marginVotes = marginVotes;
		this.marginVotesPercentage = marginVotesPercentage;
		this.percentage = percentage;
	}
	public PartyElectionTrendsReportHelperVO() {
		super();
	}
	
	
	
	
	
	
	
	//private String electionType;
	//private Long voterSize;
//	private Long presVotesEarned;
//	private Long prevVotesEarned;
//	private String presPercentage;
//	private String prevPercentage;
	
	
}
