package com.itgrids.partyanalyst.dto;

import java.util.List;

public class ConstituencyRevenueVillagesVO {

	private Long totalVoters;
	private Long constituencyId;
	private String constituencyName;
	private List<CandidatePartyInfoVO> candidateNamePartyAndStatus;
	private List<RevenueVillageElectionVO> revenueVillageElectionVO;
	private List<ConstituencyOrMandalWiseElectionVO> constituencyOrMandalWiseElectionVO;
	 
	
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
