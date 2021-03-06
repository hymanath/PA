package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * 
 * @author Narender Akula
 *
 */
public class PartyInfoVO implements Serializable{

	private String partyShortName;
	private String partyLongName;
	private Long seatsParticipated = new Long(0);
	private Long seatsWin;
	private BigDecimal percentageOfVotes;
	private Long partyTotalVotes = new Long(0);
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
	
}
