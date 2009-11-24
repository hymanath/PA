package com.itgrids.partyanalyst.dto;

import java.util.List;

public class CrossVotingConsolidateVO {

	private CrossVotedCandidateVO acCandidateData;
	private CrossVotedCandidateVO pcCandidateData;
	private List<CrossVotedMandalVO> mandals;
	boolean partyPartisipated = true;
	boolean hasAlliance = true;
	
	public CrossVotingConsolidateVO(){
		
	}

	public CrossVotingConsolidateVO(CrossVotedCandidateVO acCandidateData,
			CrossVotedCandidateVO pcCandidateData,
			List<CrossVotedMandalVO> mandals) {
		this.acCandidateData = acCandidateData;
		this.pcCandidateData = pcCandidateData;
		this.mandals = mandals;
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

	public boolean getPartyPartisipated() {
		return partyPartisipated;
	}

	public void setPartyPartisipated(boolean partyPartisipated) {
		this.partyPartisipated = partyPartisipated;
	}

	public boolean getHasAlliance() {
		return hasAlliance;
	}

	public void setHasAlliance(boolean hasAlliance) {
		this.hasAlliance = hasAlliance;
	}
	
}
