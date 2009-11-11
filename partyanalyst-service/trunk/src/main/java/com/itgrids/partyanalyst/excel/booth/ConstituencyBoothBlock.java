package com.itgrids.partyanalyst.excel.booth;

import java.util.List;

public class ConstituencyBoothBlock {
	
	private String parliamentConstituencyName;
	private Long stateId;
	private String constituencyName;
	private Long districtId;
	private List<CandidateBoothWiseResult> candidateResults;
	private List<BoothResultValueObject> boothResults;
	
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public List<CandidateBoothWiseResult> getCandidateResults() {
		return candidateResults;
	}
	public void setCandidateResults(List<CandidateBoothWiseResult> candidateResults) {
		this.candidateResults = candidateResults;
	}
	public List<BoothResultValueObject> getBoothResults() {
		return boothResults;
	}
	public void setBoothResults(List<BoothResultValueObject> boothResults) {
		this.boothResults = boothResults;
	}
	public String getParliamentConstituencyName() {
		return parliamentConstituencyName;
	}
	public void setParliamentConstituencyName(String parliamentConstituencyName) {
		this.parliamentConstituencyName = parliamentConstituencyName;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

}
