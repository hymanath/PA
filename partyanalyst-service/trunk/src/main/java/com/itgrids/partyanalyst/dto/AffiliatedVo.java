package com.itgrids.partyanalyst.dto;

public class AffiliatedVo {
	
	private Long totalMembers=0l;
	private Long totalCovered=0l;
	private Long todayCovered=0l;
	private Long totalRegistration=0l;
	private Long todayRegistration=0l;
	private Long totalLoanApplied=0l;
	private Long todayLoanApplied=0l;
	private Long locationId=0l;
	private String loctionValue;
	
	public Long getTotalMembers() {
		return totalMembers;
	}
	public void setTotalMembers(Long totalMembers) {
		this.totalMembers = totalMembers;
	}
	public Long getTotalCovered() {
		return totalCovered;
	}
	public void setTotalCovered(Long totalCovered) {
		this.totalCovered = totalCovered;
	}
	public Long getTodayCovered() {
		return todayCovered;
	}
	public void setTodayCovered(Long todayCovered) {
		this.todayCovered = todayCovered;
	}
	public Long getTotalRegistration() {
		return totalRegistration;
	}
	public void setTotalRegistration(Long totalRegistration) {
		this.totalRegistration = totalRegistration;
	}
	public Long getTodayRegistration() {
		return todayRegistration;
	}
	public void setTodayRegistration(Long todayRegistration) {
		this.todayRegistration = todayRegistration;
	}
	public Long getTotalLoanApplied() {
		return totalLoanApplied;
	}
	public void setTotalLoanApplied(Long totalLoanApplied) {
		this.totalLoanApplied = totalLoanApplied;
	}
	public Long getTodayLoanApplied() {
		return todayLoanApplied;
	}
	public void setTodayLoanApplied(Long todayLoanApplied) {
		this.todayLoanApplied = todayLoanApplied;
	}
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public String getLoctionValue() {
		return loctionValue;
	}
	public void setLoctionValue(String loctionValue) {
		this.loctionValue = loctionValue;
	}
	
	
}
