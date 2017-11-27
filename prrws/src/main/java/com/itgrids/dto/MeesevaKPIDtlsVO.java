package com.itgrids.dto;

public class MeesevaKPIDtlsVO {

	private Long id;
	private String name;
	private String range;
	private Long target;
	private Long acheived;
	private Double acheivedPer;
	private Long cumulative;
	private Double cumulativePer;
	private Long previousYearAchievement;
	private Long previousYearAchievementCount;
	private Long currentYearAchievement;
	private Long currentYearAchievementTarget;
	private Long currentYearCurrentCount;
	private Long currentYearCumulativeCount;
	private Double currentYearCumulativePer;
	
	private Long establishedFrom2014 = 0L;
	private Long establishedLastYear = 0L;
	private Long establishedThisYear = 0L;
	private Long establishedLastOneMonth = 0L;
	private Long totalMeesevaCentres = 0L;
	
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
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	public Long getTarget() {
		return target;
	}
	public void setTarget(Long target) {
		this.target = target;
	}
	public Long getAcheived() {
		return acheived;
	}
	public void setAcheived(Long acheived) {
		this.acheived = acheived;
	}
	public Double getAcheivedPer() {
		return acheivedPer;
	}
	public void setAcheivedPer(Double acheivedPer) {
		this.acheivedPer = acheivedPer;
	}
	public Long getCumulative() {
		return cumulative;
	}
	public void setCumulative(Long cumulative) {
		this.cumulative = cumulative;
	}
	public Double getCumulativePer() {
		return cumulativePer;
	}
	public void setCumulativePer(Double cumulativePer) {
		this.cumulativePer = cumulativePer;
	}
	public Long getPreviousYearAchievement() {
		return previousYearAchievement;
	}
	public void setPreviousYearAchievement(Long previousYearAchievement) {
		this.previousYearAchievement = previousYearAchievement;
	}
	public Long getPreviousYearAchievementCount() {
		return previousYearAchievementCount;
	}
	public void setPreviousYearAchievementCount(Long previousYearAchievementCount) {
		this.previousYearAchievementCount = previousYearAchievementCount;
	}
	public Long getCurrentYearAchievement() {
		return currentYearAchievement;
	}
	public void setCurrentYearAchievement(Long currentYearAchievement) {
		this.currentYearAchievement = currentYearAchievement;
	}
	public Long getCurrentYearAchievementTarget() {
		return currentYearAchievementTarget;
	}
	public void setCurrentYearAchievementTarget(Long currentYearAchievementTarget) {
		this.currentYearAchievementTarget = currentYearAchievementTarget;
	}
	public Long getCurrentYearCurrentCount() {
		return currentYearCurrentCount;
	}
	public void setCurrentYearCurrentCount(Long currentYearCurrentCount) {
		this.currentYearCurrentCount = currentYearCurrentCount;
	}
	public Long getCurrentYearCumulativeCount() {
		return currentYearCumulativeCount;
	}
	public void setCurrentYearCumulativeCount(Long currentYearCumulativeCount) {
		this.currentYearCumulativeCount = currentYearCumulativeCount;
	}
	public Double getCurrentYearCumulativePer() {
		return currentYearCumulativePer;
	}
	public void setCurrentYearCumulativePer(Double currentYearCumulativePer) {
		this.currentYearCumulativePer = currentYearCumulativePer;
	}
	public Long getEstablishedFrom2014() {
		return establishedFrom2014;
	}
	public void setEstablishedFrom2014(Long establishedFrom2014) {
		this.establishedFrom2014 = establishedFrom2014;
	}
	public Long getEstablishedLastYear() {
		return establishedLastYear;
	}
	public void setEstablishedLastYear(Long establishedLastYear) {
		this.establishedLastYear = establishedLastYear;
	}
	public Long getEstablishedThisYear() {
		return establishedThisYear;
	}
	public void setEstablishedThisYear(Long establishedThisYear) {
		this.establishedThisYear = establishedThisYear;
	}
	public Long getEstablishedLastOneMonth() {
		return establishedLastOneMonth;
	}
	public void setEstablishedLastOneMonth(Long establishedLastOneMonth) {
		this.establishedLastOneMonth = establishedLastOneMonth;
	}
	public Long getTotalMeesevaCentres() {
		return totalMeesevaCentres;
	}
	public void setTotalMeesevaCentres(Long totalMeesevaCentres) {
		this.totalMeesevaCentres = totalMeesevaCentres;
	}
	
}
