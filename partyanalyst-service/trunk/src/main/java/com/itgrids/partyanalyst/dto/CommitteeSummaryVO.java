package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
public class CommitteeSummaryVO implements Serializable{

	private Long mainComittees = 0l;
	private Long mainComitteesConformed = 0l;
	private Long mainComitteesNotConformed = 0l;
	
	private String affilatedCommitteeName;
	private Long affComitteesConformed = 0l;
	private Long affComitteesNotConformed = 0l;
	private Long totalAffilatedCommittees = 0l;
	
	private Long startedCount = 0l;
	private Long affilatedStartedCount = 0l;
	
	public Long getMainComittees() {
		return mainComittees;
	}
	public void setMainComittees(Long mainComittees) {
		this.mainComittees = mainComittees;
	}
	public Long getMainComitteesConformed() {
		return mainComitteesConformed;
	}
	public void setMainComitteesConformed(Long mainComitteesConformed) {
		this.mainComitteesConformed = mainComitteesConformed;
	}
	public Long getMainComitteesNotConformed() {
		return mainComitteesNotConformed;
	}
	public void setMainComitteesNotConformed(Long mainComitteesNotConformed) {
		this.mainComitteesNotConformed = mainComitteesNotConformed;
	}
	public String getAffilatedCommitteeName() {
		return affilatedCommitteeName;
	}
	public void setAffilatedCommitteeName(String affilatedCommitteeName) {
		this.affilatedCommitteeName = affilatedCommitteeName;
	}
	public Long getAffComitteesConformed() {
		return affComitteesConformed;
	}
	public void setAffComitteesConformed(Long affComitteesConformed) {
		this.affComitteesConformed = affComitteesConformed;
	}
	public Long getAffComitteesNotConformed() {
		return affComitteesNotConformed;
	}
	public void setAffComitteesNotConformed(Long affComitteesNotConformed) {
		this.affComitteesNotConformed = affComitteesNotConformed;
	}
	public Long getTotalAffilatedCommittees() {
		return totalAffilatedCommittees;
	}
	public void setTotalAffilatedCommittees(Long totalAffilatedCommittees) {
		this.totalAffilatedCommittees = totalAffilatedCommittees;
	}
	public Long getStartedCount() {
		return startedCount;
	}
	public void setStartedCount(Long startedCount) {
		this.startedCount = startedCount;
	}
	public Long getAffilatedStartedCount() {
		return affilatedStartedCount;
	}
	public void setAffilatedStartedCount(Long affilatedStartedCount) {
		this.affilatedStartedCount = affilatedStartedCount;
	}

	
	
	
	
}
