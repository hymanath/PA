package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class VoterReportVO implements Serializable{

	private static final long serialVersionUID = -2642174001738867220L;
	private Long totalVoters;
	private Long maleVoters;
	private Long femaleVoters;
	
	
	public Long getTotalVoters() {
		return totalVoters;
	}
	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}
	public Long getMaleVoters() {
		return maleVoters;
	}
	public void setMaleVoters(Long maleVoters) {
		this.maleVoters = maleVoters;
	}
	public Long getFemaleVoters() {
		return femaleVoters;
	}
	public void setFemaleVoters(Long femaleVoters) {
		this.femaleVoters = femaleVoters;
	}
	
}
