package com.itgrids.partyanalyst.dto;

import java.util.List;

public class MandalTownshipWiseBoothDetailsVO extends ResultStatus {
	
	private Long mandalTotalVoters;
	private Long mandalValidVoters;
	private Long constituencyID;
	private String constituencyName;
	private Long candidateID;
	private String candidateName;
	private Long candidateRank;
	private List<TownshipBoothDetailsVO> townshipBoothDetailsVOs;
	private List<TownshipPartyResultsVO> allPartyWiseVotesForMandal;
	private List<TownshipPartyResultsVO> userPartyVotesTownshipWise;
	
	public Long getMandalTotalVoters() {
		return mandalTotalVoters;
	}
	public void setMandalTotalVoters(Long mandalTotalVoters) {
		this.mandalTotalVoters = mandalTotalVoters;
	}
	public Long getMandalValidVoters() {
		return mandalValidVoters;
	}
	public void setMandalValidVoters(Long mandalValidVoters) {
		this.mandalValidVoters = mandalValidVoters;
	}
	public Long getConstituencyID() {
		return constituencyID;
	}
	public void setConstituencyID(Long constituencyID) {
		this.constituencyID = constituencyID;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public Long getCandidateID() {
		return candidateID;
	}
	public void setCandidateID(Long candidateID) {
		this.candidateID = candidateID;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public Long getCandidateRank() {
		return candidateRank;
	}
	public void setCandidateRank(Long candidateRank) {
		this.candidateRank = candidateRank;
	}
	public List<TownshipBoothDetailsVO> getTownshipBoothDetailsVOs() {
		return townshipBoothDetailsVOs;
	}
	public void setTownshipBoothDetailsVOs(
			List<TownshipBoothDetailsVO> townshipBoothDetailsVOs) {
		this.townshipBoothDetailsVOs = townshipBoothDetailsVOs;
	}
	public List<TownshipPartyResultsVO> getAllPartyWiseVotesForMandal() {
		return allPartyWiseVotesForMandal;
	}
	public void setAllPartyWiseVotesForMandal(
			List<TownshipPartyResultsVO> allPartyWiseVotesForMandal) {
		this.allPartyWiseVotesForMandal = allPartyWiseVotesForMandal;
	}
	public List<TownshipPartyResultsVO> getUserPartyVotesTownshipWise() {
		return userPartyVotesTownshipWise;
	}
	public void setUserPartyVotesTownshipWise(
			List<TownshipPartyResultsVO> userPartyVotesTownshipWise) {
		this.userPartyVotesTownshipWise = userPartyVotesTownshipWise;
	}
}
