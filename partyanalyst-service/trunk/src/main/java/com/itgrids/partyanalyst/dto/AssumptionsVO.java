package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class AssumptionsVO  implements Serializable{

	
	
	private static final long serialVersionUID = 3369048332209155758L;
	
	private String heading1;
	private String heading2;
	private String heading3;
	
	private Long totalVoters;
	private Long expPerc;
	private Long targetedPolledVotees;
	
	private Long targetedVotesForTDP;
	private Long addtionalVoters;
	private Long addtionalPerc;
	public Long getTotalVoters() {
		return totalVoters;
	}
	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}
	public Long getExpPerc() {
		return expPerc;
	}
	public void setExpPerc(Long expPerc) {
		this.expPerc = expPerc;
	}
	public Long getTargetedPolledVotees() {
		return targetedPolledVotees;
	}
	public void setTargetedPolledVotees(Long targetedPolledVotees) {
		this.targetedPolledVotees = targetedPolledVotees;
	}
	public Long getTargetedVotesForTDP() {
		return targetedVotesForTDP;
	}
	public void setTargetedVotesForTDP(Long targetedVotesForTDP) {
		this.targetedVotesForTDP = targetedVotesForTDP;
	}
	public Long getAddtionalVoters() {
		return addtionalVoters;
	}
	public void setAddtionalVoters(Long addtionalVoters) {
		this.addtionalVoters = addtionalVoters;
	}
	public Long getAddtionalPerc() {
		return addtionalPerc;
	}
	public void setAddtionalPerc(Long addtionalPerc) {
		this.addtionalPerc = addtionalPerc;
	}
	public String getHeading1() {
		return heading1;
	}
	public void setHeading1(String heading1) {
		this.heading1 = heading1;
	}
	public String getHeading2() {
		return heading2;
	}
	public void setHeading2(String heading2) {
		this.heading2 = heading2;
	}
	public String getHeading3() {
		return heading3;
	}
	public void setHeading3(String heading3) {
		this.heading3 = heading3;
	}
	
	
	
}
