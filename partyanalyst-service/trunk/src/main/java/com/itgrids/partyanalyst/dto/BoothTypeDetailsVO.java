package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Set;

public class BoothTypeDetailsVO implements Serializable {
	private Set<BoothTotalVotesVO> maleBoothVotes;
	private Set<BoothTotalVotesVO> femaleBoothVotes;
	private Set<BoothTotalVotesVO> maleFemailBoothVotes;
	public Set<BoothTotalVotesVO> getMaleBoothVotes() {
		return maleBoothVotes;
	}
	public void setMaleBoothVotes(Set<BoothTotalVotesVO> maleBoothVotes) {
		this.maleBoothVotes = maleBoothVotes;
	}
	public Set<BoothTotalVotesVO> getFemaleBoothVotes() {
		return femaleBoothVotes;
	}
	public void setFemaleBoothVotes(Set<BoothTotalVotesVO> femaleBoothVotes) {
		this.femaleBoothVotes = femaleBoothVotes;
	}
	public Set<BoothTotalVotesVO> getMaleFemailBoothVotes() {
		return maleFemailBoothVotes;
	}
	public void setMaleFemailBoothVotes(Set<BoothTotalVotesVO> maleFemailBoothVotes) {
		this.maleFemailBoothVotes = maleFemailBoothVotes;
	}
	
}
