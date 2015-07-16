package com.itgrids.partyanalyst.dto;



import java.util.List;



public class WebServiceResultVO implements java.io.Serializable{
	
	/*
	 * 	@ Author  SASI
	 *  @ Since 15-07-2015
	 *  
	 */
	
	
	private BasicVO candidateSummary;
	private Long locationSummary;
	private List<ArticleReportVO> DepartmentSummary;
	
	private Long candidateArticlesList;
	private Long locationArticlesList;
	private Long departmentArticlesList;
	
	private String name = "";
	
	private Long totalCount;
	private Long positiveCount;
	private Long negativeCount;
	private String category;
	
	
	
	public Long getLocationSummary() {
		return locationSummary;
	}

	public void setLocationSummary(Long locationSummary) {
		this.locationSummary = locationSummary;
	}

	public Long getCandidateArticlesList() {
		return candidateArticlesList;
	}

	public void setCandidateArticlesList(Long candidateArticlesList) {
		this.candidateArticlesList = candidateArticlesList;
	}

	public Long getLocationArticlesList() {
		return locationArticlesList;
	}

	public void setLocationArticlesList(Long locationArticlesList) {
		this.locationArticlesList = locationArticlesList;
	}

	public Long getDepartmentArticlesList() {
		return departmentArticlesList;
	}

	public void setDepartmentArticlesList(Long departmentArticlesList) {
		this.departmentArticlesList = departmentArticlesList;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Long getPositiveCount() {
		return positiveCount;
	}

	public void setPositiveCount(Long positiveCount) {
		this.positiveCount = positiveCount;
	}

	public Long getNegativeCount() {
		return negativeCount;
	}

	public void setNegativeCount(Long negativeCount) {
		this.negativeCount = negativeCount;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<ArticleReportVO> getDepartmentSummary() {
		return DepartmentSummary;
	}

	public void setDepartmentSummary(List<ArticleReportVO> departmentSummary) {
		DepartmentSummary = departmentSummary;
	}
	
	

	public BasicVO getCandidateSummary() {
		return candidateSummary;
	}

	public void setCandidateSummary(BasicVO candidateSummary) {
		this.candidateSummary = candidateSummary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
