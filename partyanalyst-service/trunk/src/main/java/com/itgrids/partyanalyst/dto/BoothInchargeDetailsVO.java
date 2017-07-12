package com.itgrids.partyanalyst.dto;

public class BoothInchargeDetailsVO {
	
	private Long locationId;
	private String locationName;
	private Long totalBoothCount;
	private Long startedBoothCount;
	private Long notStartedBoothCount;
	private Long completedBoothCount;
	
	private BoothAddressVO boothAddressVO;
	
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public Long getTotalBoothCount() {
		return totalBoothCount;
	}
	public void setTotalBoothCount(Long totalBoothCount) {
		this.totalBoothCount = totalBoothCount;
	}
	public Long getStartedBoothCount() {
		return startedBoothCount;
	}
	public void setStartedBoothCount(Long startedBoothCount) {
		this.startedBoothCount = startedBoothCount;
	}
	public Long getNotStartedBoothCount() {
		return notStartedBoothCount;
	}
	public void setNotStartedBoothCount(Long notStartedBoothCount) {
		this.notStartedBoothCount = notStartedBoothCount;
	}
	public Long getCompletedBoothCount() {
		return completedBoothCount;
	}
	public void setCompletedBoothCount(Long completedBoothCount) {
		this.completedBoothCount = completedBoothCount;
	}
	public BoothAddressVO getBoothAddressVO() {
		return boothAddressVO;
	}
	public void setBoothAddressVO(BoothAddressVO boothAddressVO) {
		this.boothAddressVO = boothAddressVO;
	}
	
	
	
}
