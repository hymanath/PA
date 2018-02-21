package com.itgrids.partyanalyst.dto;

import java.util.List;

public class ActivityExceptionalReportVO {

	private Long totalCount = 0l;
	private Long notConductedCount;
	private Double percentage = 0d;
	private AddressVO addressVO;
	private Long locationId;
	private String locationName;
	
	private List<ActivityExceptionalReportVO> subList1;
	private List<ActivityExceptionalReportVO> subList2;
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getNotConductedCount() {
		return notConductedCount;
	}
	public void setNotConductedCount(Long notConductedCount) {
		this.notConductedCount = notConductedCount;
	}
	public Double getPercentage() {
		return percentage;
	}
	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
	public AddressVO getAddressVO() {
		return addressVO;
	}
	public void setAddressVO(AddressVO addressVO) {
		this.addressVO = addressVO;
	}
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
	public List<ActivityExceptionalReportVO> getSubList1() {
		return subList1;
	}
	public void setSubList1(List<ActivityExceptionalReportVO> subList1) {
		this.subList1 = subList1;
	}
	public List<ActivityExceptionalReportVO> getSubList2() {
		return subList2;
	}
	public void setSubList2(List<ActivityExceptionalReportVO> subList2) {
		this.subList2 = subList2;
	}
        	
	
}
