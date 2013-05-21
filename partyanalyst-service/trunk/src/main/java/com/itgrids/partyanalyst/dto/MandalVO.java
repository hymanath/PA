package com.itgrids.partyanalyst.dto;

import java.util.List;
import java.util.Set;


public class MandalVO extends ResultStatus{

	private static final long serialVersionUID = 1L;
	private String electionType;
	private String electionYear;
	private String name;
	private Long id;
	private Long stateId;
	private Long electionTypeId;
	private List<SelectOptionVO> partiesInMandal;
	private Set<SelectOptionVO> electionsInMandal;
	private List<SelectOptionVO> electionsInMandalList;
	private String chartName;
	private String isPartial;
	private List<PartyElectionResultsVO> wardwiseResultsForParty;; 
	
	public MandalVO(){}
	
	public MandalVO(Long id, String name){
		this.id = id;
		this.name = name;
	}
	
	
	public String getIsPartial() {
		return isPartial;
	}

	public void setIsPartial(String isPartial) {
		this.isPartial = isPartial;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Set<SelectOptionVO> getElectionsInMandal() {
		return electionsInMandal;
	}

	public void setElectionsInMandal(Set<SelectOptionVO> electionsInMandal) {
		this.electionsInMandal = electionsInMandal;
	}

	public List<SelectOptionVO> getPartiesInMandal() {
		return partiesInMandal;
	}

	public void setPartiesInMandal(List<SelectOptionVO> partiesInMandal) {
		this.partiesInMandal = partiesInMandal;
	}

	public List<PartyElectionResultsVO> getWardwiseResultsForParty() {
		return wardwiseResultsForParty;
	}

	public void setWardwiseResultsForParty(
			List<PartyElectionResultsVO> wardwiseResultsForParty) {
		this.wardwiseResultsForParty = wardwiseResultsForParty;
	}

	public String getChartName() {
		return chartName;
	}

	public void setChartName(String chartName) {
		this.chartName = chartName;
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

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public Long getElectionTypeId() {
		return electionTypeId;
	}

	public void setElectionTypeId(Long electionTypeId) {
		this.electionTypeId = electionTypeId;
	}

	public List<SelectOptionVO> getElectionsInMandalList() {
		return electionsInMandalList;
	}

	public void setElectionsInMandalList(List<SelectOptionVO> electionsInMandalList) {
		this.electionsInMandalList = electionsInMandalList;
	}

	
}
