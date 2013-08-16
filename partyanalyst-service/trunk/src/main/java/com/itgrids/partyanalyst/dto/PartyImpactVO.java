package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PartyImpactVO {
	
	private String name;
	private String previousElectionVotesPercent;
	private String presentElectionVotesPercent;
	private String difference;	
	private List<PartyImpactVO> partiesList;
	private String partyName;
	private List<Long> boothsList;
	private Long id;
	private String mainPartyPercentage;
	private List<String>  electionYears = new ArrayList<String>();
	private List<String> considerableParties;
	private List<String> conclusionStatements;
	private String fromPrpVoters;
	
	public List<String> getConclusionStatements() {
		return conclusionStatements;
	}
	public void setConclusionStatements(List<String> conclusionStatements) {
		this.conclusionStatements = conclusionStatements;
	}
	public List<String> getConsiderableParties() {
		return considerableParties;
	}
	public void setConsiderableParties(List<String> considerableParties) {
		this.considerableParties = considerableParties;
	}
	public List<String> getElectionYears() {
		return electionYears;
	}
	public void setElectionYears(List<String> electionYears) {
		this.electionYears = electionYears;
	}
	public String getMainPartyPercentage() {
		return mainPartyPercentage;
	}
	public void setMainPartyPercentage(String mainPartyPercentage) {
		this.mainPartyPercentage = mainPartyPercentage;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Long> getBoothsList() {
		return boothsList;
	}
	public void setBoothsList(List<Long> boothsList) {
		this.boothsList = boothsList;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPreviousElectionVotesPercent() {
		return previousElectionVotesPercent;
	}
	public void setPreviousElectionVotesPercent(String previousElectionVotesPercent) {
		this.previousElectionVotesPercent = previousElectionVotesPercent;
	}
	public String getPresentElectionVotesPercent() {
		return presentElectionVotesPercent;
	}
	public void setPresentElectionVotesPercent(String presentElectionVotesPercent) {
		this.presentElectionVotesPercent = presentElectionVotesPercent;
	}
	public String getDifference() {
		return difference;
	}
	public void setDifference(String difference) {
		this.difference = difference;
	}
	public List<PartyImpactVO> getPartiesList() {
		return partiesList;
	}
	public void setPartiesList(List<PartyImpactVO> partiesList) {
		this.partiesList = partiesList;
	}
	public String getFromPrpVoters() {
		return fromPrpVoters;
	}
	public void setFromPrpVoters(String fromPrpVoters) {
		this.fromPrpVoters = fromPrpVoters;
	}
	
}
