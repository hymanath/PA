package com.itgrids.partyanalyst.dto;

import java.util.List;

public class ConsolidatedExceptionalReportVO {

	private Long locationId;
	private String locationName;
	private Long totalCount = 0l;
	private Long conductedCount = 0l;
	private Double conductedPercentage;
	private Long notConductedCount = 0l;
	private Double notConductedPercentage;
	private Double percentage=0.0;
	private Double percentage1=0.0;
	private AddressVO addressVO;
	private Long mayBeCount = 0l;
	private Double mayBePercentage;
	private Long sortNo = 0l;
	
	private List<ConsolidatedExceptionalReportVO> subList1;
	private List<ConsolidatedExceptionalReportVO> subList2;

	
	public Long getSortNo() {
		return sortNo;
	}
	public void setSortNo(Long sortNo) {
		this.sortNo = sortNo;
	}

	public Double getPercentage1() {
		return percentage1;
	}

	public void setPercentage1(Double percentage1) {
		this.percentage1 = percentage1;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Long getConductedCount() {
		return conductedCount;
	}

	public void setConductedCount(Long conductedCount) {
		this.conductedCount = conductedCount;
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

	public List<ConsolidatedExceptionalReportVO> getSubList1() {
		return subList1;
	}

	public void setSubList1(List<ConsolidatedExceptionalReportVO> subList1) {
		this.subList1 = subList1;
	}

    public List<ConsolidatedExceptionalReportVO> getSubList2() {
		return subList2;
	}

	public void setSubList2(List<ConsolidatedExceptionalReportVO> subList2) {
		this.subList2 = subList2;
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

	public Double getConductedPercentage() {
		return conductedPercentage;
	}

	public void setConductedPercentage(Double conductedPercentage) {
		this.conductedPercentage = conductedPercentage;
	}

	public Double getNotConductedPercentage() {
		return notConductedPercentage;
	}

	public void setNotConductedPercentage(Double notConductedPercentage) {
		this.notConductedPercentage = notConductedPercentage;
	}

	public Long getMayBeCount() {
		return mayBeCount;
	}

	public void setMayBeCount(Long mayBeCount) {
		this.mayBeCount = mayBeCount;
	}

	public Double getMayBePercentage() {
		return mayBePercentage;
	}

	public void setMayBePercentage(Double mayBePercentage) {
		this.mayBePercentage = mayBePercentage;
	}
	
    
	
	
	
}
