package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class PartyPositionDisplayVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4170436723087708081L;
	private Long constituencyId;
	private String constituencyName;
	private String candidateName;
	private Long partyId;
	private String partyName;
	private Long electionId;
	private Long rank;
	private String votePercentage;
	private List<PartyPostionInfoVO> oppPartyPositionInfoList;
	
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public Long getElectionId() {
		return electionId;
	}
	public void setElectionId(Long electionId) {
		this.electionId = electionId;
	}
	public Long getRank() {
		return rank;
	}
	public void setRank(Long rank) {
		this.rank = rank;
	}
	public String getVotePercentage() {
		return votePercentage;
	}
	public void setVotePercentage(String votePercentage) {
		this.votePercentage = votePercentage;
	}
	public List<PartyPostionInfoVO> getOppPartyPositionInfoList() {
		return oppPartyPositionInfoList;
	}
	public void setOppPartyPositionInfoList(
			List<PartyPostionInfoVO> oppPartyPositionInfoList) {
		this.oppPartyPositionInfoList = oppPartyPositionInfoList;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
}
