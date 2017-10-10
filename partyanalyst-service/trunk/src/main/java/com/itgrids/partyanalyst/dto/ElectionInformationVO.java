package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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
	
	private List<ElectionInformationVO> list = new CopyOnWriteArrayList<ElectionInformationVO>();

	private List<ElectionInformationVO> subList1 = new ArrayList<ElectionInformationVO>();
	

	public ElectionInformationVO(){}
	public ElectionInformationVO(Long partyId,String name){
		this.partyId=partyId;
		this.partyName=name;
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

}
