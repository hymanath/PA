package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class PartyPositionDisplayVO implements Serializable{
	private String constituencyName;
	private String candidateName;
	
	private Integer rank;
	private BigDecimal votePercentage;
	private List<PartyPostionInfoVO> oppPartyPositionInfoList;
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
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public BigDecimal getVotePercentage() {
		return votePercentage;
	}
	public void setVotePercentage(BigDecimal votePercentage) {
		this.votePercentage = votePercentage;
	}
	public List<PartyPostionInfoVO> getOppPartyPositionInfoList() {
		return oppPartyPositionInfoList;
	}
	public void setOppPartyPositionInfoList(
			List<PartyPostionInfoVO> oppPartyPositionInfoList) {
		this.oppPartyPositionInfoList = oppPartyPositionInfoList;
	}
}
