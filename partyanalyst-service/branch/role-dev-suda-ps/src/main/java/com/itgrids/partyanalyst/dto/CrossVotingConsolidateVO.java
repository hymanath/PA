package com.itgrids.partyanalyst.dto;

import java.util.List;

public class CrossVotingConsolidateVO {

	private CrossVotedCandidateVO acCandidateData;
	private CrossVotedCandidateVO pcCandidateData;
	private List<CrossVotedMandalVO> mandals;
	private Long totalVotersInAC;
	private Long totalVotersInPC;
	private Long totalACPolledVotesInConstituency;
	private Long totalPCPolledVotesInConstituency;
	private String differenceInACAndPC;
	private String impactOfAssemblyOnParliament;
	boolean partyPartisipated = true;
	boolean hasAlliance = true;
	
	public CrossVotingConsolidateVO(){
		
	}

	public CrossVotingConsolidateVO(CrossVotedCandidateVO acCandidateData,
			CrossVotedCandidateVO pcCandidateData,
			List<CrossVotedMandalVO> mandals, Long totalVotersInAC,
			Long totalVotersInPC, Long totalACPolledVotesInConstituency,
			Long totalPCPolledVotesInConstituency, String differenceInACAndPC,
			String impactOfAssemblyOnParliament, boolean partyPartisipated,
			boolean hasAlliance) {
		this.acCandidateData = acCandidateData;
		this.pcCandidateData = pcCandidateData;
		this.mandals = mandals;
		this.totalVotersInAC = totalVotersInAC;
		this.totalVotersInPC = totalVotersInPC;
		this.totalACPolledVotesInConstituency = totalACPolledVotesInConstituency;
		this.totalPCPolledVotesInConstituency = totalPCPolledVotesInConstituency;
		this.differenceInACAndPC = differenceInACAndPC;
		this.impactOfAssemblyOnParliament = impactOfAssemblyOnParliament;
		this.partyPartisipated = partyPartisipated;
		this.hasAlliance = hasAlliance;
	}

	public CrossVotedCandidateVO getAcCandidateData() {
		return acCandidateData;
	}

	public void setAcCandidateData(CrossVotedCandidateVO acCandidateData) {
		this.acCandidateData = acCandidateData;
	}

	public CrossVotedCandidateVO getPcCandidateData() {
		return pcCandidateData;
	}

	public void setPcCandidateData(CrossVotedCandidateVO pcCandidateData) {
		this.pcCandidateData = pcCandidateData;
	}

	public List<CrossVotedMandalVO> getMandals() {
		return mandals;
	}

	public void setMandals(List<CrossVotedMandalVO> mandals) {
		this.mandals = mandals;
	}

	public Long getTotalVotersInAC() {
		return totalVotersInAC;
	}

	public void setTotalVotersInAC(Long totalVotersInAC) {
		this.totalVotersInAC = totalVotersInAC;
	}

	public Long getTotalVotersInPC() {
		return totalVotersInPC;
	}

	public void setTotalVotersInPC(Long totalVotersInPC) {
		this.totalVotersInPC = totalVotersInPC;
	}

	public Long getTotalACPolledVotesInConstituency() {
		return totalACPolledVotesInConstituency;
	}

	public void setTotalACPolledVotesInConstituency(
			Long totalACPolledVotesInConstituency) {
		this.totalACPolledVotesInConstituency = totalACPolledVotesInConstituency;
	}

	public Long getTotalPCPolledVotesInConstituency() {
		return totalPCPolledVotesInConstituency;
	}

	public void setTotalPCPolledVotesInConstituency(
			Long totalPCPolledVotesInConstituency) {
		this.totalPCPolledVotesInConstituency = totalPCPolledVotesInConstituency;
	}

	public String getImpactOfAssemblyOnParliament() {
		return impactOfAssemblyOnParliament;
	}

	public void setImpactOfAssemblyOnParliament(String impactOfAssemblyOnParliament) {
		this.impactOfAssemblyOnParliament = impactOfAssemblyOnParliament;
	}

	public boolean isPartyPartisipated() {
		return partyPartisipated;
	}

	public void setPartyPartisipated(boolean partyPartisipated) {
		this.partyPartisipated = partyPartisipated;
	}

	public boolean isHasAlliance() {
		return hasAlliance;
	}

	public void setHasAlliance(boolean hasAlliance) {
		this.hasAlliance = hasAlliance;
	}

	public String getDifferenceInACAndPC() {
		return differenceInACAndPC;
	}

	public void setDifferenceInACAndPC(String differenceInACAndPC) {
		this.differenceInACAndPC = differenceInACAndPC;
	}
	
	
}
