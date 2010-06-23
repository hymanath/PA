package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class ElectionWiseMandalPartyResultVO  implements Serializable {

	private Long electionId;
	private Long electionYear;
	private String electionType;
	private Long totalVotersInMandal;
	private Long polledVotes;
	private String votingPercentage;
	private Long maleVoters;
	private Long femaleVoters;
	private Long votesPolled;
	private Long maleValidVotes;
	private Long femaleValidVotes;
	private Long maleOrFemaleValidVotes;
	private List<ConstituencyWiseDataForMandalVO> constituencyWiseDataForMandalVOs; 
	private List<PartyResultsVO> partyResultsVO;
	private List<PartyElectionResultVO> partyResultsInElection;
	private BoothTypeDetailsVO boothTypeDetailsVO;
	
	public Long getElectionId() {
		return electionId;
	}

	public void setElectionId(Long electionId) {
		this.electionId = electionId;
	}

	public Long getElectionYear() {
		return electionYear;
	}
	
	public void setElectionYear(Long electionYear) {
		this.electionYear = electionYear;
	}
	
	public Long getMaleVoters() {
		return maleVoters;
	}

	public void setMaleVoters(Long maleVoters) {
		this.maleVoters = maleVoters;
	}

	public Long getFemaleVoters() {
		return femaleVoters;
	}

	public void setFemaleVoters(Long femaleVoters) {
		this.femaleVoters = femaleVoters;
	}

	public Long getVotesPolled() {
		return votesPolled;
	}

	public void setVotesPolled(Long votesPolled) {
		this.votesPolled = votesPolled;
	}

	public String getElectionType() {
		return electionType;
	}
	
	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}
	
	public BoothTypeDetailsVO getBoothTypeDetailsVO() {
		return boothTypeDetailsVO;
	}
	
	public void setBoothTypeDetailsVO(BoothTypeDetailsVO boothTypeDetailsVO) {
		this.boothTypeDetailsVO = boothTypeDetailsVO;
	}
	
	public void setTotalVotersInMandal(Long totalVotersInMandal) {
		this.totalVotersInMandal = totalVotersInMandal;
	}
	
	public Long getTotalVotersInMandal() {
		return totalVotersInMandal;
	}
	
	public List<ConstituencyWiseDataForMandalVO> getConstituencyWiseDataForMandalVOs() {
		return constituencyWiseDataForMandalVOs;
	}

	public void setConstituencyWiseDataForMandalVOs(
			List<ConstituencyWiseDataForMandalVO> constituencyWiseDataForMandalVOs) {
		this.constituencyWiseDataForMandalVOs = constituencyWiseDataForMandalVOs;
	}

	public Long getPolledVotes() {
		return polledVotes;
	}

	public void setPolledVotes(Long polledVotes) {
		this.polledVotes = polledVotes;
	}

	public String getVotingPercentage() {
		return votingPercentage;
	}

	public void setVotingPercentage(String votingPercentage) {
		this.votingPercentage = votingPercentage;
	}

	public List<PartyResultsVO> getPartyResultsVO() {
		return partyResultsVO;
	}

	public void setPartyResultsVO(List<PartyResultsVO> partyResultsVO) {
		this.partyResultsVO = partyResultsVO;
	}

	public Long getMaleValidVotes() {
		return maleValidVotes;
	}

	public void setMaleValidVotes(Long maleValidVotes) {
		this.maleValidVotes = maleValidVotes;
	}

	public Long getFemaleValidVotes() {
		return femaleValidVotes;
	}

	public void setFemaleValidVotes(Long femaleValidVotes) {
		this.femaleValidVotes = femaleValidVotes;
	}

	public Long getMaleOrFemaleValidVotes() {
		return maleOrFemaleValidVotes;
	}

	public void setMaleOrFemaleValidVotes(Long maleOrFemaleValidVotes) {
		this.maleOrFemaleValidVotes = maleOrFemaleValidVotes;
	}

	public List<PartyElectionResultVO> getPartyResultsInElection() {
		return partyResultsInElection;
	}

	public void setPartyResultsInElection(
			List<PartyElectionResultVO> partyResultsInElection) {
		this.partyResultsInElection = partyResultsInElection;
	}

	@Override
	public boolean equals(Object obj){
		ElectionWiseMandalPartyResultVO voObj = (ElectionWiseMandalPartyResultVO) obj;
		return this.electionYear.equals(voObj.getElectionYear()) && this.electionType.equals(voObj.getElectionType());
	}
	
	@Override
	public int hashCode(){
		return this.electionYear.hashCode() + this.electionType.hashCode();
	}
}
