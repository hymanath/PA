package com.itgrids.partyanalyst.dto;

import java.util.List;

public class LocationwiseProblemStatusInfoVO {
	private Long locationId;
	private int totalProblemsCount;
	private List<ProblemsCountByStatus> problemsCountByStatus;
	private List<ProblemsCountByStatus> problemsCountByStatusForChart;
	private String lineChartPath;
	private String problemsStatusChartName;
	private String problemsPostedInLastTenDays;
	private String problemsSolvedInLastTenDays;
	private String problemsPostedInLastThirtyDays;
	private String problemsSolvedInLastThirtyDays;
	private String lastTenDaysProblemsDetailsBarChartName;
	private String lastThirtyDaysProblemsDetailsBarChartName;
	private List<ProblemBeanVO> recentProblems; 
		
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public int getTotalProblemsCount() {
		return totalProblemsCount;
	}
	public void setTotalProblemsCount(int totalProblemsCount) {
		this.totalProblemsCount = totalProblemsCount;
	}
	public List<ProblemsCountByStatus> getProblemsCountByStatus() {
		return problemsCountByStatus;
	}
	public void setProblemsCountByStatus(
			List<ProblemsCountByStatus> problemsCountByStatus) {
		this.problemsCountByStatus = problemsCountByStatus;
	}
	public String getProblemsStatusChartName() {
		return problemsStatusChartName;
	}
	public void setProblemsStatusChartName(String problemsStatusChartName) {
		this.problemsStatusChartName = problemsStatusChartName;
	}
	public String getProblemsPostedInLastTenDays() {
		return problemsPostedInLastTenDays;
	}
	public void setProblemsPostedInLastTenDays(String problemsPostedInLastTenDays) {
		this.problemsPostedInLastTenDays = problemsPostedInLastTenDays;
	}
	public String getProblemsSolvedInLastTenDays() {
		return problemsSolvedInLastTenDays;
	}
	public void setProblemsSolvedInLastTenDays(String problemsSolvedInLastTenDays) {
		this.problemsSolvedInLastTenDays = problemsSolvedInLastTenDays;
	}
	public String getProblemsPostedInLastThirtyDays() {
		return problemsPostedInLastThirtyDays;
	}
	public void setProblemsPostedInLastThirtyDays(
			String problemsPostedInLastThirtyDays) {
		this.problemsPostedInLastThirtyDays = problemsPostedInLastThirtyDays;
	}
	public String getProblemsSolvedInLastThirtyDays() {
		return problemsSolvedInLastThirtyDays;
	}
	public void setProblemsSolvedInLastThirtyDays(
			String problemsSolvedInLastThirtyDays) {
		this.problemsSolvedInLastThirtyDays = problemsSolvedInLastThirtyDays;
	}
	public String getLastTenDaysProblemsDetailsBarChartName() {
		return lastTenDaysProblemsDetailsBarChartName;
	}
	public void setLastTenDaysProblemsDetailsBarChartName(
			String lastTenDaysProblemsDetailsBarChartName) {
		this.lastTenDaysProblemsDetailsBarChartName = lastTenDaysProblemsDetailsBarChartName;
	}
	public String getLastThirtyDaysProblemsDetailsBarChartName() {
		return lastThirtyDaysProblemsDetailsBarChartName;
	}
	public void setLastThirtyDaysProblemsDetailsBarChartName(
			String lastThirtyDaysProblemsDetailsBarChartName) {
		this.lastThirtyDaysProblemsDetailsBarChartName = lastThirtyDaysProblemsDetailsBarChartName;
	}
	public void setRecentProblems(List<ProblemBeanVO> recentProblems) {
		this.recentProblems = recentProblems;
	}
	public List<ProblemBeanVO> getRecentProblems() {
		return recentProblems;
	}
	public List<ProblemsCountByStatus> getProblemsCountByStatusForChart() {
		return problemsCountByStatusForChart;
	}
	public void setProblemsCountByStatusForChart(
			List<ProblemsCountByStatus> problemsCountByStatusForChart) {
		this.problemsCountByStatusForChart = problemsCountByStatusForChart;
	}
	public String getLineChartPath() {
		return lineChartPath;
	}
	public void setLineChartPath(String lineChartPath) {
		this.lineChartPath = lineChartPath;
	}
	
}
