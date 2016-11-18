package com.itgrids.partyanalyst.dto;

import java.util.List;

public class GISIssuesVO {
	private String name;
	private Long id;
	private Long totalIssues = 0l;
	private Long count = 0l;
	private List<GISIssuesVO> issuesList;
	private List<GISIssuesVO> subList;
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
	public Long getTotalIssues() {
		return totalIssues;
	}
	public void setTotalIssues(Long totalIssues) {
		this.totalIssues = totalIssues;
	}
	
	public List<GISIssuesVO> getIssuesList() {
		return issuesList;
	}
	public void setIssuesList(List<GISIssuesVO> issuesList) {
		this.issuesList = issuesList;
	}
	public List<GISIssuesVO> getSubList() {
		return subList;
	}
	public void setSubList(List<GISIssuesVO> subList) {
		this.subList = subList;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
	
	
}
