package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MandalAllElectionDetailsVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String tehsilName;
	private Long tehsilId;
	private String electionType;
	private String electionYear;
	private Long totalVoters;
	private Long validVoters;
	private Long rejectedVoters;
	private Long tenderedVoters;
	private String candidateName;
	private String polledVotes;
	private Float votesDifference;
	private String rank;
	private String partyVotesPercentage;
	private Long earnedVotes;
	private Long electionScopeID;
	private String partyShortName;
	private Long electionID;
	private Long electionTypeID;
	private String partyFlag;
	private Long partyId;
	private int zptcCount;
	private int mptcCount;
	private Float votesPolled;
	private Float votesEarned;
	private String votesPercentage;
	private Long constituencyId;
	private String constituencyName;
	private BigDecimal marginVotesPercentage;
	private String reservationZone;
	private List<MandalAllElectionDetailsVO> zptcMandalAllElectionDetailsVO;
	private List<MandalAllElectionDetailsVO> mptcMandalAllElectionDetailsVO;
	private List<MandalAllElectionDetailsVO> allVotersDetails;
	private List<SelectOptionVO> partyInfo;
	private ResultStatus resultStatus;
	private List<String> electionYears = new ArrayList<String>();
	private List<String> electionTypes = new ArrayList<String>();
	private Long totalVotersEarned;
	private String votesGainedPercent;
	
	public String getVotesGainedPercent() {
		return votesGainedPercent;
	}
	public void setVotesGainedPercent(String votesGainedPercent) {
		this.votesGainedPercent = votesGainedPercent;
	}
	public Long getTotalVotersEarned() {
		return totalVotersEarned;
	}
	public void setTotalVotersEarned(Long totalVotersEarned) {
		this.totalVotersEarned = totalVotersEarned;
	}
	public List<String> getElectionYears() {
		return electionYears;
	}
	public void setElectionYears(List<String> electionYears) {
		this.electionYears = electionYears;
	}
	public List<String> getElectionTypes() {
		return electionTypes;
	}
	public void setElectionTypes(List<String> electionTypes) {
		this.electionTypes = electionTypes;
	}
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
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
	public void setVotesDifference(Float votesDifference) {
		this.votesDifference = votesDifference;
	}
	public Float getVotesDifference() {
		return votesDifference;
	}
	public List<SelectOptionVO> getPartyInfo() {
		return partyInfo;
	}
	
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public void setPartyInfo(List<SelectOptionVO> partyInfo) {
		this.partyInfo = partyInfo;
	}
	public List<MandalAllElectionDetailsVO> getAllVotersDetails() {
		return allVotersDetails;
	}
	public void setAllVotersDetails(
			List<MandalAllElectionDetailsVO> allVotersDetails) {
		this.allVotersDetails = allVotersDetails;
	}
	public Long getEarnedVotes() {
		return earnedVotes;
	}
	public void setEarnedVotes(Long earnedVotes) {
		this.earnedVotes = earnedVotes;
	}
	public int getZptcCount() {
		return zptcCount;
	}
	public void setZptcCount(int zptcCount) {
		this.zptcCount = zptcCount;
	}
	public int getMptcCount() {
		return mptcCount;
	}
	public void setMptcCount(int mptcCount) {
		this.mptcCount = mptcCount;
	}
	public List<MandalAllElectionDetailsVO> getZptcMandalAllElectionDetailsVO() {
		return zptcMandalAllElectionDetailsVO;
	}
	public String getReservationZone() {
		return reservationZone;
	}
	public void setReservationZone(String reservationZone) {
		this.reservationZone = reservationZone;
	}
	public void setZptcMandalAllElectionDetailsVO(
			List<MandalAllElectionDetailsVO> zptcMandalAllElectionDetailsVO) {
		this.zptcMandalAllElectionDetailsVO = zptcMandalAllElectionDetailsVO;
	}
	public List<MandalAllElectionDetailsVO> getMptcMandalAllElectionDetailsVO() {
		return mptcMandalAllElectionDetailsVO;
	}
	public void setMptcMandalAllElectionDetailsVO(
			List<MandalAllElectionDetailsVO> mptcMandalAllElectionDetailsVO) {
		this.mptcMandalAllElectionDetailsVO = mptcMandalAllElectionDetailsVO;
	}
	public Long getTehsilId() {
		return tehsilId;
	}
	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}
	public String getPartyFlag() {
		return partyFlag;
	}
	public String getPolledVotes() {
		return polledVotes;
	}
	public String getVotesPercentage() {
		return votesPercentage;
	}
	public void setVotesPercentage(String votesPercentage) {
		this.votesPercentage = votesPercentage;
	}
	public void setPolledVotes(String polledVotes) {
		this.polledVotes = polledVotes;
	}
	public void setPartyFlag(String partyFlag) {
		this.partyFlag = partyFlag;
	}
	public String getTehsilName() {
		return tehsilName;
	}	
	public BigDecimal getMarginVotesPercentage() {
		return marginVotesPercentage;
	}
	public void setMarginVotesPercentage(BigDecimal marginVotesPercentage) {
		this.marginVotesPercentage = marginVotesPercentage;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public Float getVotesEarned() {
		return votesEarned;
	}
	public void setVotesEarned(Float votesEarned) {
		this.votesEarned = votesEarned;
	}
	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}
	public String getElectionType() {
		return electionType;
	}
	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}
	public String getElectionYear() {
		return electionYear;
	}
	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}
	public Long getTotalVoters() {
		return totalVoters;
	}
	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}
	
	public Long getValidVoters() {
		return validVoters;
	}
	public void setValidVoters(Long validVoters) {
		this.validVoters = validVoters;
	}
	public Long getRejectedVoters() {
		return rejectedVoters;
	}
	public void setRejectedVoters(Long rejectedVoters) {
		this.rejectedVoters = rejectedVoters;
	}
	public Long getTenderedVoters() {
		return tenderedVoters;
	}
	public void setTenderedVoters(Long tenderedVoters) {
		this.tenderedVoters = tenderedVoters;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getPartyVotesPercentage() {
		return partyVotesPercentage;
	}
	public void setPartyVotesPercentage(String partyVotesPercentage) {
		this.partyVotesPercentage = partyVotesPercentage;
	}
	public Long getElectionScopeID() {
		return electionScopeID;
	}
	public void setElectionScopeID(Long electionScopeID) {
		this.electionScopeID = electionScopeID;
	}
	public String getPartyShortName() {
		return partyShortName;
	}
	public void setPartyShortName(String partyShortName) {
		this.partyShortName = partyShortName;
	}
	public Long getElectionID() {
		return electionID;
	}
	public void setElectionID(Long electionID) {
		this.electionID = electionID;
	}
	public Long getElectionTypeID() {
		return electionTypeID;
	}
	public Float getVotesPolled() {
		return votesPolled;
	}
	public void setVotesPolled(Float votesPolled) {
		this.votesPolled = votesPolled;
	}
	public void setElectionTypeID(Long electionTypeID) {
		this.electionTypeID = electionTypeID;
	}
	
}
