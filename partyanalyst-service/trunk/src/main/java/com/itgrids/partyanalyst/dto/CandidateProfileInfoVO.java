package com.itgrids.partyanalyst.dto;

import java.util.List;

public class CandidateProfileInfoVO {

	private List<CandidateElectionProfileVO> candidateElectionProfile;

	public List<CandidateElectionProfileVO> getCandidateElectionProfile() {
		return candidateElectionProfile;
	}

	public void setCandidateElectionProfile(
			List<CandidateElectionProfileVO> candidateElectionProfile) {
		this.candidateElectionProfile = candidateElectionProfile;
	} 
}
