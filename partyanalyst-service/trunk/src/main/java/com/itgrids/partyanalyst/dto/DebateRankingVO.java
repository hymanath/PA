package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class DebateRankingVO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long firstRank = 0l;
	private Long secondRank = 0l;
	private Long thirdRank = 0l;
	private Long fourthRank = 0l;
	
	public Long getFirstRank() {
		return firstRank;
	}
	public void setFirstRank(Long firstRank) {
		this.firstRank = firstRank;
	}
	public Long getSecondRank() {
		return secondRank;
	}
	public void setSecondRank(Long secondRank) {
		this.secondRank = secondRank;
	}
	public Long getThirdRank() {
		return thirdRank;
	}
	public void setThirdRank(Long thirdRank) {
		this.thirdRank = thirdRank;
	}
	public Long getFourthRank() {
		return fourthRank;
	}
	public void setFourthRank(Long fourthRank) {
		this.fourthRank = fourthRank;
	}
	
	
	
	
}
