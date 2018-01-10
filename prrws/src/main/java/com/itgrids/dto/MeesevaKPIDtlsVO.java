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
	private Long previousYearAchievementCount = 0L;
	private Long currentYearAchievement = 0L;
	private Long currentYearAchievementTarget;
	private Long currentYearCurrentCount;
	private Long currentYearCumulativeCount;
	private Double currentYearCumulativePer;
	
	private Long establishedFrom2014 = 0L;
	private Long establishedLastYear = 0L;
	private Long establishedThisYear = 0L;
	private Long establishedLastOneMonth = 0L;
	private Long totalMeesevaCentres = 0L;
	private Long onLineServicesCount = 0L;
	private String serviceName;
	private Long onLineServices2014 = 0L;
	private Long onLineServices2015 = 0L;
	private Long onLineServices2016 = 0L;
	private Long onLineServices2017 = 0L;
	private Long mobileAppServices2014 = 0L;
	private Long mobileAppServices2015 = 0L;
	private Long mobileAppServices2016 = 0L;
	private Long mobileAppServices2017 = 0L;
	private Long totalMobileAppServices = 0L;
	private String percenatge;
	private String districtIdStr;
	private String mandalName;
	private String villageName;
	private String agentId;
	private String agentName;
	private String mobileNo;
	private String address;
	private String centerType;
	private String estDate;
	
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
	public Long getOnLineServicesCount() {
		return onLineServicesCount;
	}
	public void setOnLineServicesCount(Long onLineServicesCount) {
		this.onLineServicesCount = onLineServicesCount;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public Long getOnLineServices2014() {
		return onLineServices2014;
	}
	public void setOnLineServices2014(Long onLineServices2014) {
		this.onLineServices2014 = onLineServices2014;
	}
	public Long getOnLineServices2015() {
		return onLineServices2015;
	}
	public void setOnLineServices2015(Long onLineServices2015) {
		this.onLineServices2015 = onLineServices2015;
	}
	public Long getOnLineServices2016() {
		return onLineServices2016;
	}
	public void setOnLineServices2016(Long onLineServices2016) {
		this.onLineServices2016 = onLineServices2016;
	}
	public Long getOnLineServices2017() {
		return onLineServices2017;
	}
	public void setOnLineServices2017(Long onLineServices2017) {
		this.onLineServices2017 = onLineServices2017;
	}
	public Long getMobileAppServices2014() {
		return mobileAppServices2014;
	}
	public void setMobileAppServices2014(Long mobileAppServices2014) {
		this.mobileAppServices2014 = mobileAppServices2014;
	}
	public Long getMobileAppServices2015() {
		return mobileAppServices2015;
	}
	public void setMobileAppServices2015(Long mobileAppServices2015) {
		this.mobileAppServices2015 = mobileAppServices2015;
	}
	public Long getMobileAppServices2016() {
		return mobileAppServices2016;
	}
	public void setMobileAppServices2016(Long mobileAppServices2016) {
		this.mobileAppServices2016 = mobileAppServices2016;
	}
	public Long getMobileAppServices2017() {
		return mobileAppServices2017;
	}
	public void setMobileAppServices2017(Long mobileAppServices2017) {
		this.mobileAppServices2017 = mobileAppServices2017;
	}
	public Long getTotalMobileAppServices() {
		return totalMobileAppServices;
	}
	public void setTotalMobileAppServices(Long totalMobileAppServices) {
		this.totalMobileAppServices = totalMobileAppServices;
	}
	public String getPercenatge() {
		return percenatge;
	}
	public void setPercenatge(String percenatge) {
		this.percenatge = percenatge;
	}
	public String getDistrictIdStr() {
		return districtIdStr;
	}
	public void setDistrictIdStr(String districtIdStr) {
		this.districtIdStr = districtIdStr;
	}
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	public String getVillageName() {
		return villageName;
	}
	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCenterType() {
		return centerType;
	}
	public void setCenterType(String centerType) {
		this.centerType = centerType;
	}
	public String getEstDate() {
		return estDate;
	}
	public void setEstDate(String estDate) {
		this.estDate = estDate;
	} 
	
}
