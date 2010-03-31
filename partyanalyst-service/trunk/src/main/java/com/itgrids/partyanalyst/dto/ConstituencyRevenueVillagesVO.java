package com.itgrids.partyanalyst.dto;

import java.util.List;

public class ConstituencyRevenueVillagesVO {

	private Long totalVoters;
	private Long constituencyId;
	private String constituencyName;
	private String electionType;
	private List<CandidatePartyInfoVO> candidateNamePartyAndStatus;
	private List<RevenueVillageElectionVO> revenueVillageElectionVO;
	private List<ConstituencyOrMandalWiseElectionVO> constituencyOrMandalWiseElectionVO;
	private String chartPath;
	private List<SelectOptionVO> elections;
	
	public List<ConstituencyOrMandalWiseElectionVO> getConstituencyOrMandalWiseElectionVO() {
		return constituencyOrMandalWiseElectionVO;
	}

	public void setConstituencyOrMandalWiseElectionVO(
			List<ConstituencyOrMandalWiseElectionVO> constituencyOrMandalWiseElectionVO) {
		this.constituencyOrMandalWiseElectionVO = constituencyOrMandalWiseElectionVO;
	}

	public Long getTotalVoters() {
		return totalVoters;
	}

	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}

	public List<CandidatePartyInfoVO> getCandidateNamePartyAndStatus() {
		return candidateNamePartyAndStatus;
	}

	public void setCandidateNamePartyAndStatus(
			List<CandidatePartyInfoVO> candidateNamePartyAndStatus) {
		this.candidateNamePartyAndStatus = candidateNamePartyAndStatus;
	}

	public List<RevenueVillageElectionVO> getRevenueVillageElectionVO() {
		return revenueVillageElectionVO;
	}

	public void setRevenueVillageElectionVO(
			List<RevenueVillageElectionVO> revenueVillageElectionVO) {
		this.revenueVillageElectionVO = revenueVillageElectionVO;
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

	public String getChartPath() {
		return chartPath;
	}

	public void setChartPath(String chartPath) {
		this.chartPath = chartPath;
	}

	public List<SelectOptionVO> getElections() {
		return elections;
	}

	public void setElections(List<SelectOptionVO> elections) {
		this.elections = elections;
	}

	public String getElectionType() {
		return electionType;
	}

	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}

	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof ConstituencyRevenueVillagesVO))
			return false;
		ConstituencyRevenueVillagesVO voObj = (ConstituencyRevenueVillagesVO) obj;
		return this.constituencyId.equals(voObj.getConstituencyId());
	}
	
	@Override
	public int hashCode(){
		return this.constituencyId.hashCode();
	}
	
}
