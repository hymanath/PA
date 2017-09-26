package com.itgrids.dto;

import java.util.List;

public class ItecOverviewVO {

	private Long id;
	private String name;
	private Long industryCount;
	private Long commitedInvestmentCount;
	private Long commitedEmploymentCount;
	private List<ItecOverviewVO> subList;
	
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
	public Long getIndustryCount() {
		return industryCount;
	}
	public void setIndustryCount(Long industryCount) {
		this.industryCount = industryCount;
	}
	public Long getCommitedInvestmentCount() {
		return commitedInvestmentCount;
	}
	public void setCommitedInvestmentCount(Long commitedInvestmentCount) {
		this.commitedInvestmentCount = commitedInvestmentCount;
	}
	public Long getCommitedEmploymentCount() {
		return commitedEmploymentCount;
	}
	public void setCommitedEmploymentCount(Long commitedEmploymentCount) {
		this.commitedEmploymentCount = commitedEmploymentCount;
	}
	public List<ItecOverviewVO> getSubList() {
		return subList;
	}
	public void setSubList(List<ItecOverviewVO> subList) {
		this.subList = subList;
	}
	
	
}
