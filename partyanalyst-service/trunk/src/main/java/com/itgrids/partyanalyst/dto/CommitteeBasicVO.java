package com.itgrids.partyanalyst.dto;

public class CommitteeBasicVO {
	
	private Long mainCommTotalCount = 0l;
	private Long mainCommStartedCount = 0l;
	private Long mainCommCompletedCount = 0l;
	private Long mainCommTotalMembers = 0l;
	
	private Long affiCommTotalCount = 0l;
	private Long affiCommStartedCount= 0l;
	private Long affiCommCompletedCount= 0l;
	private Long affiCommTotalMembers= 0l;
	private String locationType;
	private String areaType="";
	
	private Long mainCommNotYetStarted =0l;
	private String commiteeName;
	
	
	
	public Long getMainCommNotYetStarted() {
		return mainCommNotYetStarted;
	}
	public void setMainCommNotYetStarted(Long mainCommNotYetStarted) {
		this.mainCommNotYetStarted = mainCommNotYetStarted;
	}
	public String getCommiteeName() {
		return commiteeName;
	}
	public void setCommiteeName(String commiteeName) {
		this.commiteeName = commiteeName;
	}
	public Long getMainCommTotalCount() {
		return mainCommTotalCount;
	}
	public void setMainCommTotalCount(Long mainCommTotalCount) {
		this.mainCommTotalCount = mainCommTotalCount;
	}
	public Long getMainCommStartedCount() {
		return mainCommStartedCount;
	}
	public void setMainCommStartedCount(Long mainCommStartedCount) {
		this.mainCommStartedCount = mainCommStartedCount;
	}
	public Long getMainCommCompletedCount() {
		return mainCommCompletedCount;
	}
	public void setMainCommCompletedCount(Long mainCommCompletedCount) {
		this.mainCommCompletedCount = mainCommCompletedCount;
	}
	public Long getMainCommTotalMembers() {
		return mainCommTotalMembers;
	}
	public void setMainCommTotalMembers(Long mainCommTotalMembers) {
		this.mainCommTotalMembers = mainCommTotalMembers;
	}
	public Long getAffiCommTotalCount() {
		return affiCommTotalCount;
	}
	public void setAffiCommTotalCount(Long affiCommTotalCount) {
		this.affiCommTotalCount = affiCommTotalCount;
	}
	public Long getAffiCommStartedCount() {
		return affiCommStartedCount;
	}
	public void setAffiCommStartedCount(Long affiCommStartedCount) {
		this.affiCommStartedCount = affiCommStartedCount;
	}
	public Long getAffiCommCompletedCount() {
		return affiCommCompletedCount;
	}
	public void setAffiCommCompletedCount(Long affiCommCompletedCount) {
		this.affiCommCompletedCount = affiCommCompletedCount;
	}
	public Long getAffiCommTotalMembers() {
		return affiCommTotalMembers;
	}
	public void setAffiCommTotalMembers(Long affiCommTotalMembers) {
		this.affiCommTotalMembers = affiCommTotalMembers;
	}
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	public String getAreaType() {
		return areaType;
	}
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	
	
	
}
