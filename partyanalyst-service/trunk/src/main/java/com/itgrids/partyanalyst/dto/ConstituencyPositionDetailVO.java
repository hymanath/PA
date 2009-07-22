/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */
package com.itgrids.partyanalyst.dto;

import java.math.BigDecimal;

public class ConstituencyPositionDetailVO {

	private static final long serialVersionUID = 1L;
	
	private String constiuencyName;
	private String candidateName;
	private BigDecimal percentageOfVotes;
	private BigDecimal prevElectionPercentage;
	private String oppositeParty;
	private String oppositePartyCandidate;
	private BigDecimal percentageOfVotesPolled;
	private String prevElectionCandidateName;
	private BigDecimal prevElectionPercentageOfVotesPolled;
	private int prevElectionVotes;

	public ConstituencyPositionDetailVO() {}

	public String getConstiuencyName() {
		return constiuencyName;
	}

	public void setConstiuencyName(String constiuencyName) {
		this.constiuencyName = constiuencyName;
	}

	public BigDecimal getPercentageOfVotesPolled() {
		return percentageOfVotesPolled;
	}

	public void setPercentageOfVotesPolled(BigDecimal percentageOfVotesPolled) {
		this.percentageOfVotesPolled = percentageOfVotesPolled;
	}

	public String getPrevElectionCandidateName() {
		return prevElectionCandidateName;
	}

	public void setPrevElectionCandidateName(String prevElectionCandidateName) {
		this.prevElectionCandidateName = prevElectionCandidateName;
	}

	public BigDecimal getPrevElectionPercentageOfVotesPolled() {
		return prevElectionPercentageOfVotesPolled;
	}

	public void setPrevElectionPercentageOfVotesPolled(
			BigDecimal prevElectionPercentageOfVotesPolled) {
		this.prevElectionPercentageOfVotesPolled = prevElectionPercentageOfVotesPolled;
	}

	public int getPrevElectionVotes() {
		return prevElectionVotes;
	}

	public void setPrevElectionVotes(int prevElectionVotes) {
		this.prevElectionVotes = prevElectionVotes;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public BigDecimal getPercentageOfVotes() {
		return percentageOfVotes;
	}

	public void setPercentageOfVotes(BigDecimal percentageOfVotes) {
		this.percentageOfVotes = percentageOfVotes;
	}

	public BigDecimal getPrevElectionPercentage() {
		return prevElectionPercentage;
	}

	public void setPrevElectionPercentage(BigDecimal prevElectionPercentage) {
		this.prevElectionPercentage = prevElectionPercentage;
	}

	public String getOppositeParty() {
		return oppositeParty;
	}

	public void setOppositeParty(String oppositeParty) {
		this.oppositeParty = oppositeParty;
	}

	public String getOppositePartyCandidate() {
		return oppositePartyCandidate;
	}

	public void setOppositePartyCandidate(String oppositePartyCandidate) {
		this.oppositePartyCandidate = oppositePartyCandidate;
	}

}
