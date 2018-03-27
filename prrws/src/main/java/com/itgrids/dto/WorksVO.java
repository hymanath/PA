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
    private Long locationId;
    private String locationName;
    private Long parentLocationId;
    private String ParentLocationName;
    private Long districtId;
    private String districtName;

    private Long AdminSanctionedCount = 0l;
    private Long technicalSanctionedCount = 0l;
    private Long entrustedCount = 0l;
    private Long groundedCount = 0l;
    private Long undrProcessCount = 0l;
    private Long completedCount = 0l;
    private Long commissionedCount = 0l;
    private Long notgroundedCount = 0l;
    
		
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
	public Long getParentLocationId() {
		return parentLocationId;
	}
	public void setParentLocationId(Long parentLocationId) {
		this.parentLocationId = parentLocationId;
	}
	public String getParentLocationName() {
		return ParentLocationName;
	}
	public void setParentLocationName(String parentLocationName) {
		ParentLocationName = parentLocationName;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public Long getAdminSanctionedCount() {
		return AdminSanctionedCount;
	}
	public void setAdminSanctionedCount(Long adminSanctionedCount) {
		AdminSanctionedCount = adminSanctionedCount;
	}
	public Long getTechnicalSanctionedCount() {
		return technicalSanctionedCount;
	}
	public void setTechnicalSanctionedCount(Long technicalSanctionedCount) {
		this.technicalSanctionedCount = technicalSanctionedCount;
	}
	public Long getEntrustedCount() {
		return entrustedCount;
	}
	public void setEntrustedCount(Long entrustedCount) {
		this.entrustedCount = entrustedCount;
	}
	public Long getGroundedCount() {
		return groundedCount;
	}
	public void setGroundedCount(Long groundedCount) {
		this.groundedCount = groundedCount;
	}
	public Long getUndrProcessCount() {
		return undrProcessCount;
	}
	public void setUndrProcessCount(Long undrProcessCount) {
		this.undrProcessCount = undrProcessCount;
	}
	public Long getCompletedCount() {
		return completedCount;
	}
	public void setCompletedCount(Long completedCount) {
		this.completedCount = completedCount;
	}
	public Long getCommissionedCount() {
		return commissionedCount;
	}
	public void setCommissionedCount(Long commissionedCount) {
		this.commissionedCount = commissionedCount;
	}
	public Long getNotgroundedCount() {
		return notgroundedCount;
	}
	public void setNotgroundedCount(Long notgroundedCount) {
		this.notgroundedCount = notgroundedCount;
	}
	
	
}
