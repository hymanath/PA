package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class JanmabhoomiCommitteeVO {	
	private Long id;
	private String name;
	private Long  totalCommitteeCnt=0l;
	private Long  notStartedCommitteeCnt=0l;
	private Long  inprogressCommitteeCnt=0l;
	private Long  readyForApprovelCommitteeCnt=0l;
	private Long  totalApprovedCommitteeCnt=0l;    
	private Long  submitedCommittees=0l;
	private List<JanmabhoomiCommitteeVO>  positinsList= new ArrayList<JanmabhoomiCommitteeVO>();
	private String notStartedCommitteePerc="0";
	private String inprogressCommitteePerc="0";
	private String readyForApprovelCommitteeperc="0";
	private String totalApprovedCommitteeperc="0";
	private String submitedCommitteesperc="0";
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getTotalCommitteeCnt() {
		return totalCommitteeCnt;
	}
	public void setTotalCommitteeCnt(Long totalCommitteeCnt) {
		this.totalCommitteeCnt = totalCommitteeCnt;
	}
	public Long getNotStartedCommitteeCnt() {
		return notStartedCommitteeCnt;
	}
	public void setNotStartedCommitteeCnt(Long notStartedCommitteeCnt) {
		this.notStartedCommitteeCnt = notStartedCommitteeCnt;
	}
	public Long getInprogressCommitteeCnt() {
		return inprogressCommitteeCnt;
	}
	public void setInprogressCommitteeCnt(Long inprogressCommitteeCnt) {
		this.inprogressCommitteeCnt = inprogressCommitteeCnt;
	}
	public Long getReadyForApprovelCommitteeCnt() {
		return readyForApprovelCommitteeCnt;
	}
	public void setReadyForApprovelCommitteeCnt(Long readyForApprovelCommitteeCnt) {
		this.readyForApprovelCommitteeCnt = readyForApprovelCommitteeCnt;
	}
	public Long getTotalApprovedCommitteeCnt() {
		return totalApprovedCommitteeCnt;
	}
	public void setTotalApprovedCommitteeCnt(Long totalApprovedCommitteeCnt) {
		this.totalApprovedCommitteeCnt = totalApprovedCommitteeCnt;
	}
	public Long getSubmitedCommittees() {
		return submitedCommittees;
	}
	public void setSubmitedCommittees(Long submitedCommittees) {
		this.submitedCommittees = submitedCommittees;
	}
	public List<JanmabhoomiCommitteeVO> getPositinsList() {
		return positinsList;
	}
	public void setPositinsList(List<JanmabhoomiCommitteeVO> positinsList) {
		this.positinsList = positinsList;
	}
	public String getNotStartedCommitteePerc() {
		return notStartedCommitteePerc;
	}
	public void setNotStartedCommitteePerc(String notStartedCommitteePerc) {
		this.notStartedCommitteePerc = notStartedCommitteePerc;
	}
	public String getInprogressCommitteePerc() {
		return inprogressCommitteePerc;
	}
	public void setInprogressCommitteePerc(String inprogressCommitteePerc) {
		this.inprogressCommitteePerc = inprogressCommitteePerc;
	}
	public String getReadyForApprovelCommitteeperc() {
		return readyForApprovelCommitteeperc;
	}
	public void setReadyForApprovelCommitteeperc(
			String readyForApprovelCommitteeperc) {
		this.readyForApprovelCommitteeperc = readyForApprovelCommitteeperc;
	}
	public String getTotalApprovedCommitteeperc() {
		return totalApprovedCommitteeperc;
	}
	public void setTotalApprovedCommitteeperc(String totalApprovedCommitteeperc) {
		this.totalApprovedCommitteeperc = totalApprovedCommitteeperc;
	}
	public String getSubmitedCommitteesperc() {
		return submitedCommitteesperc;
	}
	public void setSubmitedCommitteesperc(String submitedCommitteesperc) {
		this.submitedCommitteesperc = submitedCommitteesperc;
	}
	}
