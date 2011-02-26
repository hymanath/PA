package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * 
 * @author Narender Akula
 *
 */
public class PartyInfoVO implements Serializable{

	private static final long serialVersionUID = -4867498044624505368L;
	private Long partyId;
	private String partyShortName;
	private String partyLongName;
	private Long seatsParticipated = new Long(0);
	private Long seatsWin;
	private Float averageSeatsWon; 
	private BigDecimal percentageOfVotes;
	private Long partyTotalVotes = new Long(0);
	private Long validVotes = 0l;
	private String electionYear;
	
	public String getPartyShortName() {
		return partyShortName;
	}
	
	public void setPartyShortName(String partyShortName) {
		this.partyShortName = partyShortName;
	}
	
	public String getPartyLongName() {
		return partyLongName;
	}
	
	public void setPartyLongName(String partyLongName) {
		this.partyLongName = partyLongName;
	}
	
	public Long getSeatsParticipated() {
		return seatsParticipated;
	}
	
	public void setSeatsParticipated(Long seatsParticipated) {
		this.seatsParticipated = seatsParticipated;
	}
	
	public Long getSeatsWin() {
		return seatsWin;
	}
	
	public Float getAverageSeatsWon() {
		return averageSeatsWon;
	}

	public void setAverageSeatsWon(Float averageSeatsWon) {
		this.averageSeatsWon = averageSeatsWon;
	}

	public void setSeatsWin(Long seatsWin) {
		this.seatsWin = seatsWin;
	}
	
	public BigDecimal getPercentageOfVotes() {
		return percentageOfVotes;
	}
	
	public void setPercentageOfVotes(BigDecimal percentageOfVotes) {
		this.percentageOfVotes = percentageOfVotes;
	}
	
	public Long getPartyTotalVotes() {
		return partyTotalVotes;
	}
	
	public void setPartyTotalVotes(Long partyTotalVotes) {
		this.partyTotalVotes = partyTotalVotes;
	}
	
	public String getElectionYear() {
		return electionYear;
	}
	
	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}

	public Long getValidVotes() {
		return validVotes;
	}

	public void setValidVotes(Long validVotes) {
		this.validVotes = validVotes;
	}

	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

}
