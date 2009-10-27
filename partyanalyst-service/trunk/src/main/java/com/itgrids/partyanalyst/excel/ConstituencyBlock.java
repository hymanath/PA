package com.itgrids.partyanalyst.excel;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * This class holds constituency details and candidate details
 * who were participated in this constituency.
 * @author USER
 *
 */

public class ConstituencyBlock {

	private String constituencyName;
	private String margin;
	private String remarks;
	private Double totalElectors;
	private Double validVotes;
	private Double rejectedVotes;
	private Double missingVotes;
	private Double totalVotesPolled;
	private Double tenderedVotes;
	private String reservationInfo;
	List<CandidateElectionResult> candidateElectionlst;
	public ConstituencyBlock(){
		constituencyName="";
		totalElectors= new Double(0);
		validVotes= new Double(0);
		rejectedVotes= new Double(0);
		missingVotes= new Double(0);
		totalVotesPolled= new Double(0);
		tenderedVotes= new Double(0);
		reservationInfo="";
	}
	
	public ConstituencyBlock(String constituencyName, Double totalElectors,
			Double validVotes, Double rejectedVotes, Double missingVotes,
			Double totalVotesPolled, Double tenderedVotes,
			List<CandidateElectionResult> candidateElectionlst) {
		super();
		this.constituencyName = constituencyName;
		this.totalElectors = totalElectors;
		this.validVotes = validVotes;
		this.rejectedVotes = rejectedVotes;
		this.missingVotes = missingVotes;
		this.totalVotesPolled = totalVotesPolled;
		this.tenderedVotes = tenderedVotes;
		this.candidateElectionlst = candidateElectionlst;
	}	
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	
	
	
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("constituencyName",
				constituencyName).append("totalElectors", totalElectors)
				.append("validVotes", validVotes).append("rejectedVotes",
						rejectedVotes).append("missingVotes", missingVotes)
				.append("totalVotesPolled", totalVotesPolled).append(
						"tenderedVotes", tenderedVotes).append(
						"candidateElectionlst", candidateElectionlst)
				.toString();
	}
	public Double getTotalElectors() {
		return totalElectors;
	}
	public void setTotalElectors(Double totalElectors) {
		
		this.totalElectors = totalElectors;
	}
	public Double getValidVotes() {
		return validVotes;
	}
	public void setValidVotes(Double validVotes) {
		this.validVotes = validVotes;
	}
	public Double getRejectedVotes() {
		return rejectedVotes;
	}
	public void setRejectedVotes(Double rejectedVotes) {
		this.rejectedVotes = rejectedVotes;
	}
	public Double getMissingVotes() {
		return missingVotes;
	}
	public void setMissingVotes(Double missingVotes) {
		this.missingVotes = missingVotes;
	}
	public Double getTotalVotesPolled() {
		return totalVotesPolled;
	}
	public void setTotalVotesPolled(Double totalVotesPolled) {
		this.totalVotesPolled = totalVotesPolled;
	}
	public Double getTenderedVotes() {
		return tenderedVotes;
	}
	public void setTenderedVotes(Double tenderedVotes) {
		this.tenderedVotes = tenderedVotes;
	}
	public List<CandidateElectionResult> getCandidateElectionlst() {
		return candidateElectionlst;
	}
	public void setCandidateElectionlst(
			List<CandidateElectionResult> candidateElectionlst) {
		this.candidateElectionlst = candidateElectionlst;
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
	public String getReservationInfo() {
		return reservationInfo;
	}

	public void setReservationInfo(String reservationInfo) {
		this.reservationInfo = reservationInfo;
	}

}
