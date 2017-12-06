package com.itgrids.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class WorksVO {

	private String assetType;
	private Long count=0l;
	private Long ongoingPWSExceededCount = 0l;
	private Long completedPWSExceededCount = 0l;
	private Long commissionedPWSExceededCount = 0l;
	private Long workOngoingCount = 0l;
	private Long workComissionedCount = 0l;
	private Long workCompletedCount = 0l;
	private Long workNotGroundedCount = 0l;

	private Long workOngoingPWsCount;
	private Double percentageOne = 0.0d;
	private Double percentageTwo = 0.0d;
	private Double percentageThree = 0.0d; 
	private Double percentageFour = 0.0d;
    private String workStatus;
    private List<WorksVO> subList = new ArrayList<WorksVO>();

		
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getOngoingPWSExceededCount() {
		return ongoingPWSExceededCount;
	}
	public void setOngoingPWSExceededCount(Long ongoingPWSExceededCount) {
		this.ongoingPWSExceededCount = ongoingPWSExceededCount;
	}
	public Long getCompletedPWSExceededCount() {
		return completedPWSExceededCount;
	}
	public void setCompletedPWSExceededCount(Long completedPWSExceededCount) {
		this.completedPWSExceededCount = completedPWSExceededCount;
	}
	public Long getCommissionedPWSExceededCount() {
		return commissionedPWSExceededCount;
	}
	public void setCommissionedPWSExceededCount(Long commissionedPWSExceededCount) {
		this.commissionedPWSExceededCount = commissionedPWSExceededCount;
	}
	public Long getWorkOngoingCount() {
		return workOngoingCount;
	}
	public void setWorkOngoingCount(Long workOngoingCount) {
		this.workOngoingCount = workOngoingCount;
	}
	public Long getWorkComissionedCount() {
		return workComissionedCount;
	}
	public void setWorkComissionedCount(Long workComissionedCount) {
		this.workComissionedCount = workComissionedCount;
	}
	public Long getWorkCompletedCount() {
		return workCompletedCount;
	}
	public void setWorkCompletedCount(Long workCompletedCount) {
		this.workCompletedCount = workCompletedCount;
	}
	public Long getWorkNotGroundedCount() {
		return workNotGroundedCount;
	}
	public void setWorkNotGroundedCount(Long workNotGroundedCount) {
		this.workNotGroundedCount = workNotGroundedCount;
	}
	public Long getWorkOngoingPWsCount() {
		return workOngoingPWsCount;
	}
	public void setWorkOngoingPWsCount(Long workOngoingPWsCount) {
		this.workOngoingPWsCount = workOngoingPWsCount;
	}
	public Double getPercentageOne() {
		return percentageOne;
	}
	public void setPercentageOne(Double percentageOne) {
		this.percentageOne = percentageOne;
	}
	public Double getPercentageTwo() {
		return percentageTwo;
	}
	public void setPercentageTwo(Double percentageTwo) {
		this.percentageTwo = percentageTwo;
	}
	public Double getPercentageThree() {
		return percentageThree;
	}
	public void setPercentageThree(Double percentageThree) {
		this.percentageThree = percentageThree;
	}
	public Double getPercentageFour() {
		return percentageFour;
	}
	public void setPercentageFour(Double percentageFour) {
		this.percentageFour = percentageFour;
	}
	public String getWorkStatus() {
		return workStatus;
	}
	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}
	public List<WorksVO> getSubList() {
		return subList;
	}
	public void setSubList(List<WorksVO> subList) {
		this.subList = subList;
	}
	
	
	
	
}
