/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class ConstituencyPositionDetailVO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long constituencyId;
	private String constiuencyName;
	private String candidateName;
	private BigDecimal percentageOfVotes; //percentage of votes candidate got in the present election
	private BigDecimal prevElectionPercentage; //percentage of votes candidate got in the previous election
	private String oppositeParty;// opposite party short name
	private String oppositePartyLongName;// opposite party long name
	private String oppositePartyCandidate;
	private BigDecimal oppositePartyPercentageOfVotes;
	private BigDecimal percentageOfVotesPolled;//percentage of votes polled in the present election
	private String prevElectionCandidateName;
	private BigDecimal prevElectionPercentageOfVotesPolled;//percentage of votes polled in the previous election
	private double prevElectionVotes; //total votes in the previous election
	
	private double presentElectionVotes; // total votes in the present election
	private int rank;
	private int oppositePartyRank;
	private String partyName;  
	private BigDecimal percentageDiffBetweenTop2;
	private String marginVotesPercentage;
	
	
	public double getPresentElectionVotes(){
		return presentElectionVotes;
	}
	
	public void setPresentElectionVotes(double presentElectionVotes){
		this.presentElectionVotes = presentElectionVotes;
	}
	
	public BigDecimal getPercentageDiffBetweenTop2(){
		return percentageDiffBetweenTop2;
	}
	
	public void setPercentageDiffBetweenTop2(BigDecimal bigDecimal){
		this.percentageDiffBetweenTop2 = bigDecimal;
	}
	
	public int getRank(){
		return rank;
	}

	public void setRank(int rank){
		this.rank = rank;
	}
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

	public double getPrevElectionVotes() {
		return prevElectionVotes;
	}

	public void setPrevElectionVotes(double prevElectionVotes) {
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
	
	public BigDecimal getOppositePartyPercentageOfVotes() {
		return oppositePartyPercentageOfVotes;
	}

	public void setOppositePartyPercentageOfVotes(
			BigDecimal oppositePartyPercentageOfVotes) {
		this.oppositePartyPercentageOfVotes = oppositePartyPercentageOfVotes;
	}


	public void setOppositePartyCandidate(String oppositePartyCandidate) {
		this.oppositePartyCandidate = oppositePartyCandidate;
	}

	public String getOppositePartyLongName(){
		return oppositePartyLongName;
	}

	public void setOppositePartyLongName(String oppositePartyLongName){
		this.oppositePartyLongName = oppositePartyLongName;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public int getOppositePartyRank() {
		return oppositePartyRank;
	}

	public void setOppositePartyRank(int oppositePartyRank) {
		this.oppositePartyRank = oppositePartyRank;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	public Long getConstituencyId() {
		return constituencyId;
	}

	public String getMarginVotesPercentage() {
		return marginVotesPercentage;
	}

	public void setMarginVotesPercentage(String marginVotesPercentage) {
		this.marginVotesPercentage = marginVotesPercentage;
	}

}
