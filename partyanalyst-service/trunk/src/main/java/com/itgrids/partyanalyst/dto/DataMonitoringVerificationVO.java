package com.itgrids.partyanalyst.dto;

public class DataMonitoringVerificationVO {

	private Long id;
	private String name;
	
	private Long count = 0l;
	private Long verifiedCount = 0l;
	private Long notVerifiedCount = 0l;
	private Long openIssues = 0l;
	private Long fixedIssues = 0l;
	private Long closedIssues = 0l;
	private Long rejectedCount = 0l;
	
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
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getVerifiedCount() {
		return verifiedCount;
	}
	public void setVerifiedCount(Long verifiedCount) {
		this.verifiedCount = verifiedCount;
	}
	public Long getNotVerifiedCount() {
		return notVerifiedCount;
	}
	public void setNotVerifiedCount(Long notVerifiedCount) {
		this.notVerifiedCount = notVerifiedCount;
	}
	public Long getOpenIssues() {
		return openIssues;
	}
	public void setOpenIssues(Long openIssues) {
		this.openIssues = openIssues;
	}
	public Long getFixedIssues() {
		return fixedIssues;
	}
	public void setFixedIssues(Long fixedIssues) {
		this.fixedIssues = fixedIssues;
	}
	public Long getClosedIssues() {
		return closedIssues;
	}
	public void setClosedIssues(Long closedIssues) {
		this.closedIssues = closedIssues;
	}
	public Long getRejectedCount() {
		return rejectedCount;
	}
	public void setRejectedCount(Long rejectedCount) {
		this.rejectedCount = rejectedCount;
	}
}
