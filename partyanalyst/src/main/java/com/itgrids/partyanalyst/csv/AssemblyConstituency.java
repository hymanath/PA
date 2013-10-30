package com.itgrids.partyanalyst.csv;

import java.util.ArrayList;
import java.util.List;

public class AssemblyConstituency {
	private String name;
	private List<AssemblyConstCandidate> candidateDetails;
	private long totalVotes;
	private long validVotes;
	private long polledVotes;
	private long missingVotes;
	private long rejectedVotes;
	private long tenderedVotes;
	
	public List<AssemblyConstCandidate> getCandidateDetails() {
		return candidateDetails;
	}
	public void setCandidateDetails(
		List<AssemblyConstCandidate> candidateDetails) {
		this.candidateDetails = candidateDetails;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getTotalVotes() {
		return totalVotes;
	}
	public void setTotalVotes(long totalVotes) {
		this.totalVotes = totalVotes;
	}
	public long getValidVotes() {
		return validVotes;
	}
	public void setValidVotes(long validVotes) {
		this.validVotes = validVotes;
	}
	public long getPolledVotes() {
		return polledVotes;
	}
	public void setPolledVotes(long polledVotes) {
		this.polledVotes = polledVotes;
	}
	public long getMissingVotes() {
		return missingVotes;
	}
	public void setMissingVotes(long missingVotes) {
		this.missingVotes = missingVotes;
	}
	public long getRejectedVotes() {
		return rejectedVotes;
	}
	public void setRejectedVotes(long rejectedVotes) {
		this.rejectedVotes = rejectedVotes;
	}
	public long getTenderedVotes() {
		return tenderedVotes;
	}
	public void setTenderedVotes(long tenderedVotes) {
		this.tenderedVotes = tenderedVotes;
	}
	
}
