package com.itgrids.partyanalyst.dto;

public class CadreCommitteeReportVO {

	
	private Long committeesCount =0l;
	private Long startedCommittees  =0l;
	private Long mainCommittees  =0l;
	private Long afflCommittees  =0l;
	private Long completedCommittees  =0l;
	private Long membersCount = 0L;
	private Long totalMembers  =0l;
	private Long totalCommittees  =0l;
	private Long totalCompleted  =0l;
	private Long affliatedCompleted  =0l;
	private Double totalCntPerc ;
	private Double startedCommitteePerc;
	private Double completedCommitteePerc;
	private String name;
	private Long id;
	private Long membersCount1 = 0L;
	private Long membersCount2 = 0L;
	private Long membersCount3 = 0L;
	private Double affliatedCompletedPerc;
	
	
	public Long getCommitteesCount() {
		return committeesCount;
	}
	public void setCommitteesCount(Long committeesCount) {
		this.committeesCount = committeesCount;
	}
	public Long getStartedCommittees() {
		return startedCommittees;
	}
	public void setStartedCommittees(Long startedCommittees) {
		this.startedCommittees = startedCommittees;
	}
	public Long getAfflCommittees() {
		return afflCommittees;
	}
	public void setAfflCommittees(Long afflCommittees) {
		this.afflCommittees = afflCommittees;
	}
	public Long getCompletedCommittees() {
		return completedCommittees;
	}
	public void setCompletedCommittees(Long completedCommittees) {
		this.completedCommittees = completedCommittees;
	}
	public Long getMembersCount() {
		return membersCount;
	}
	public void setMembersCount(Long membersCount) {
		this.membersCount = membersCount;
	}
	public Long getTotalMembers() {
		return totalMembers;
	}
	public void setTotalMembers(Long totalMembers) {
		this.totalMembers = totalMembers;
	}
	public Long getTotalCommittees() {
		return totalCommittees;
	}
	public void setTotalCommittees(Long totalCommittees) {
		this.totalCommittees = totalCommittees;
	}
	public Long getMainCommittees() {
		return mainCommittees;
	}
	public void setMainCommittees(Long mainCommittees) {
		this.mainCommittees = mainCommittees;
	}
	public Long getTotalCompleted() {
		return totalCompleted;
	}
	public void setTotalCompleted(Long totalCompleted) {
		this.totalCompleted = totalCompleted;
	}
	public Double getTotalCntPerc() {
		return totalCntPerc;
	}
	public void setTotalCntPerc(Double totalCntPerc) {
		this.totalCntPerc = totalCntPerc;
	}
	public Double getStartedCommitteePerc() {
		return startedCommitteePerc;
	}
	public void setStartedCommitteePerc(Double startedCommitteePerc) {
		this.startedCommitteePerc = startedCommitteePerc;
	}
	public Double getCompletedCommitteePerc() {
		return completedCommitteePerc;
	}
	public void setCompletedCommitteePerc(Double completedCommitteePerc) {
		this.completedCommitteePerc = completedCommitteePerc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMembersCount1() {
		return membersCount1;
	}
	public void setMembersCount1(Long membersCount1) {
		this.membersCount1 = membersCount1;
	}
	public Long getMembersCount2() {
		return membersCount2;
	}
	public void setMembersCount2(Long membersCount2) {
		this.membersCount2 = membersCount2;
	}
	public Long getMembersCount3() {
		return membersCount3;
	}
	public void setMembersCount3(Long membersCount3) {
		this.membersCount3 = membersCount3;
	}
	
	public Long getAffliatedCompleted() {
		return affliatedCompleted;
	}
	public void setAffliatedCompleted(Long affliatedCompleted) {
		this.affliatedCompleted = affliatedCompleted;
	}
	public Double getAffliatedCompletedPerc() {
		return affliatedCompletedPerc;
	}
	public void setAffliatedCompletedPerc(Double affliatedCompletedPerc) {
		this.affliatedCompletedPerc = affliatedCompletedPerc;
	}
	
	
}
