package com.itgrids.partyanalyst.dto;

import java.util.List;

public class PartyMeetingExceptionalReportVO {

	private Long totalCount;
	private Long conductedCount;
	private Long notConductedCount;
	private Double percentage;
	private AddressVO addressVO;
	
	private List<PartyMeetingExceptionalReportVO> subList;

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

	public List<PartyMeetingExceptionalReportVO> getSubList() {
		return subList;
	}

	public void setSubList(List<PartyMeetingExceptionalReportVO> subList) {
		this.subList = subList;
	}
	
	
}
