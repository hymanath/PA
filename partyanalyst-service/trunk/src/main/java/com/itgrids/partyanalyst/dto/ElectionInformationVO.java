package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ElectionInformationVO implements Serializable, Cloneable {
	
	/**
	 * sanjeev
	 */
	private static final long serialVersionUID = 1L;
	private Long partyId;
	private String partyName;
	private Long electionId;
	private String electionYear;
	private Long electionTypeId;
	private String electionType;
	private Long locationId;
	private String locationName;
	private Long totalVoters=0l;
	private Long validVoters=0l;
	private Long missedVotes=0l;
	private Long rejectedVotes=0l;
	private Long earnedVotes=0l;
	private Long earnedVotesPerc;
	private Long marginVotes=0l;
	private Long wonSeatsCount=0l;
	private Long totalSeatsCount=0L;
	private Long participatedSeatsCount=0L;
	private String perc="0.0";
	private String partyFlag;
	private String earnedVote;
	private String status;
	private String range;
	private Long rank=0L;
	private Long assemblyValidVoters =0l;
	private Long parliamentValidVoters = 0l;
	private Double earnedVotersPerc;
	private Long assemblyEarndVotes = 0l;
	private Long parliamentEarnedVotes=0l;
	private Double earnedVotersPerc1;
	private Long id;
	private String name;
	private Double percentage = 0.0;
	private Long min;
	private Long max;
	private Long candidateId;
	private Long groupId;
	
	private Long crossVotingCount=0L;
	private String crossVotingPerc="";
	private List<ElectionInformationVO> list = new ArrayList<ElectionInformationVO>();
	private List<ElectionInformationVO> subList1 = new ArrayList<ElectionInformationVO>();
	private List<ElectionInformationVO> subList2 = new ArrayList<ElectionInformationVO>();
	private List<ElectionInformationVO> subList3 = new ArrayList<ElectionInformationVO>();

	private List<Long> idsList=new LinkedList<Long>();
	private List<ElectionInformationVO> schemesList = new ArrayList<ElectionInformationVO>();
	private List<String> partyNamesList=new ArrayList<String>();
	
	private  Long mlaCandidateEarnedVotes=0L;
	private  Long mpCandidateEarnedVotes=0L;
	private  Long assemblyValidVotes=0L;
	private  Long parliamentValidVotes=0L;
	
	private String totalSeatsCount1;
	private String participatedSeatsCount1;
	private String status1;
	
	public ElectionInformationVO(){}
	public ElectionInformationVO(Long partyId,String name){
		this.partyId=partyId;
		this.partyName=name;
	}
	public ElectionInformationVO(String name,String range,Long min,Long max,Long rank){
		this.min=min;
		this.max=max;
		this.partyName=name;
		this.range=range;
		this.rank = rank;
	}
	public Double getPercentage() {
		return percentage;
	}
	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
	public ElectionInformationVO(String status,String range,Long wonSeatsCount){
		this.status=status;
		this.range=range;
		this.wonSeatsCount = wonSeatsCount;
	}
	
	public Long getCrossVotingCount() {
		return crossVotingCount;
	}
	public void setCrossVotingCount(Long crossVotingCount) {
		this.crossVotingCount = crossVotingCount;
	}
	public String getCrossVotingPerc() {
		return crossVotingPerc;
	}
	public void setCrossVotingPerc(String crossVotingPerc) {
		this.crossVotingPerc = crossVotingPerc;
	}
	public Long getMin() {
		return min;
	}
	public void setMin(Long min) {
		this.min = min;
	}
	public Long getMax() {
		return max;
	}
	public void setMax(Long max) {
		this.max = max;
	}
	public Long getRank() {
		return rank;
	}
	public void setRank(Long rank) {
		this.rank = rank;
	}
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	public String getPartyFlag() {
		return partyFlag;
	}
	public void setPartyFlag(String partyFlag) {
		this.partyFlag = partyFlag;
	}
	public Long getParticipatedSeatsCount() {
		return participatedSeatsCount;
	}
	public void setParticipatedSeatsCount(Long participatedSeatsCount) {
		this.participatedSeatsCount = participatedSeatsCount;
	}
	public String getPerc() {
		return perc;
	}
	public void setPerc(String perc) {
		this.perc = perc;
	}
	public Long getTotalSeatsCount() {
		return totalSeatsCount;
	}
	public void setTotalSeatsCount(Long totalSeatsCount) {
		this.totalSeatsCount = totalSeatsCount;
	}
	public ElectionInformationVO(String electionYear,Long electionId,String electionType){
		this.electionYear=electionYear;
		this.electionId= electionId;
		this.electionType=electionType;
	}

	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public Long getElectionId() {
		return electionId;
	}
	public void setElectionId(Long electionId) {
		this.electionId = electionId;
	}
	public String getElectionYear() {
		return electionYear;
	}
	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}
	public Long getElectionTypeId() {
		return electionTypeId;
	}
	public void setElectionTypeId(Long electionTypeId) {
		this.electionTypeId = electionTypeId;
	}
	public String getElectionType() {
		return electionType;
	}
	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
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
	public Long getMissedVotes() {
		return missedVotes;
	}
	public void setMissedVotes(Long missedVotes) {
		this.missedVotes = missedVotes;
	}
	public Long getRejectedVotes() {
		return rejectedVotes;
	}
	public void setRejectedVotes(Long rejectedVotes) {
		this.rejectedVotes = rejectedVotes;
	}
	public Long getEarnedVotes() {
		return earnedVotes;
	}
	public void setEarnedVotes(Long earnedVotes) {
		this.earnedVotes = earnedVotes;
	}
	public Long getEarnedVotesPerc() {
		return earnedVotesPerc;
	}
	public void setEarnedVotesPerc(Long earnedVotesPerc) {
		this.earnedVotesPerc = earnedVotesPerc;
	}
	public Long getMarginVotes() {
		return marginVotes;
	}
	public void setMarginVotes(Long marginVotes) {
		this.marginVotes = marginVotes;
	}
	public Long getWonSeatsCount() {
		return wonSeatsCount;
	}
	public void setWonSeatsCount(Long wonSeatsCount) {
		this.wonSeatsCount = wonSeatsCount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<ElectionInformationVO> getSubList1() {
		return subList1;
	}
	public void setSubList1(List<ElectionInformationVO> subList1) {
		this.subList1 = subList1;
	}
	public List<ElectionInformationVO> getList() {
		return list;
	}
	public void setList(List<ElectionInformationVO> list) {
		this.list = list;
	}
	public String getEarnedVote() {
		return earnedVote;
	}
	public void setEarnedVote(String earnedVote) {
		this.earnedVote = earnedVote;
	}
	public Long getAssemblyValidVoters() {
		return assemblyValidVoters;
	}
	public void setAssemblyValidVoters(Long assemblyValidVoters) {
		this.assemblyValidVoters = assemblyValidVoters;
	}
	public Long getParliamentValidVoters() {
		return parliamentValidVoters;
	}
	public void setParliamentValidVoters(Long parliamentValidVoters) {
		this.parliamentValidVoters = parliamentValidVoters;
	}
	public Double getEarnedVotersPerc() {
		return earnedVotersPerc;
	}
	public void setEarnedVotersPerc(Double earnedVotersPerc) {
		this.earnedVotersPerc = earnedVotersPerc;
	}
	public Long getAssemblyEarndVotes() {
		return assemblyEarndVotes;
	}
	public void setAssemblyEarndVotes(Long assemblyEarndVotes) {
		this.assemblyEarndVotes = assemblyEarndVotes;
	}
	public Long getParliamentEarnedVotes() {
		return parliamentEarnedVotes;
	}
	public void setParliamentEarnedVotes(Long parliamentEarnedVotes) {
		this.parliamentEarnedVotes = parliamentEarnedVotes;
	}
	public Double getEarnedVotersPerc1() {
		return earnedVotersPerc1;
	}
	public void setEarnedVotersPerc1(Double earnedVotersPerc1) {
		this.earnedVotersPerc1 = earnedVotersPerc1;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ElectionInformationVO> getSchemesList() {
		return schemesList;
	}
	public void setSchemesList(List<ElectionInformationVO> schemesList) {
		this.schemesList = schemesList;
	}
	public List<Long> getIdsList() {
		return idsList;
	}
	public void setIdsList(List<Long> idsList) {
		this.idsList = idsList;
	}
	public List<ElectionInformationVO> getSubList2() {
		return subList2;
	}
	public void setSubList2(List<ElectionInformationVO> subList2) {
		this.subList2 = subList2;
	}
	public Long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}

	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public List<String> getPartyNamesList() {
		return partyNamesList;
	}
	public void setPartyNamesList(List<String> partyNamesList) {
		this.partyNamesList = partyNamesList;
	}
	public List<ElectionInformationVO> getSubList3() {
		return subList3;
	}
	public void setSubList3(List<ElectionInformationVO> subList3) {
		this.subList3 = subList3;
	}
	public String getTotalSeatsCount1() {
		return totalSeatsCount1;
	}
	public void setTotalSeatsCount1(String totalSeatsCount1) {
		this.totalSeatsCount1 = totalSeatsCount1;
	}
	public String getParticipatedSeatsCount1() {
		return participatedSeatsCount1;
	}
	public void setParticipatedSeatsCount1(String participatedSeatsCount1) {
		this.participatedSeatsCount1 = participatedSeatsCount1;
	}
	public String getStatus1() {
		return status1;
	}
	public void setStatus1(String status1) {
		this.status1 = status1;
	}
	public Long getMlaCandidateEarnedVotes() {
		return mlaCandidateEarnedVotes;
	}
	public void setMlaCandidateEarnedVotes(Long mlaCandidateEarnedVotes) {
		this.mlaCandidateEarnedVotes = mlaCandidateEarnedVotes;
	}
	public Long getMpCandidateEarnedVotes() {
		return mpCandidateEarnedVotes;
	}
	public void setMpCandidateEarnedVotes(Long mpCandidateEarnedVotes) {
		this.mpCandidateEarnedVotes = mpCandidateEarnedVotes;
	}
	public Long getAssemblyValidVotes() {
		return assemblyValidVotes;
	}
	public void setAssemblyValidVotes(Long assemblyValidVotes) {
		this.assemblyValidVotes = assemblyValidVotes;
	}
	public Long getParliamentValidVotes() {
		return parliamentValidVotes;
	}
	public void setParliamentValidVotes(Long parliamentValidVotes) {
		this.parliamentValidVotes = parliamentValidVotes;
	}
}
