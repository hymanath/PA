package com.itgrids.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BasicVO {
	
	 private BigDecimal surfaceWaterSafeMLD,surfaceWaterUnSafeMLD,groundWaterUnSafeMLD,groundWaterSafeMLD,totalUnSafeWaterInMLD,totalSafeWaterInMLD;
	 private String assetType;
	 private Long physicalTestCount =0l;
	 private Long bacterialTestCount =0l;
	 private Long count=0l;
	 private Long workOngoingCount=0l;
	 private Long workComissionedCount=0l ;
	 private Long workCompletedCount=0l;
	 private Long workNotGroundedCount=0l;
	 
	 private Long total=0l;
	 private Double percentage=0.0d;	 
	 private Double percentageOne = 0.0d,percentageTwo = 0.0d,percentageThree = 0.0d,percentageFour = 0.0d;
	 
	 private Long id;
	 private String name;
	 private Long locationId;
	 private String locationName;
	 private List<BasicVO> basicList = new ArrayList<BasicVO>(0);
	 
	 
	public List<BasicVO> getBasicList() {
		return basicList;
	}
	public void setBasicList(List<BasicVO> basicList) {
		this.basicList = basicList;
	}
	public BigDecimal getTotalUnSafeWaterInMLD() {
		return totalUnSafeWaterInMLD;
	}
	public void setTotalUnSafeWaterInMLD(BigDecimal totalUnSafeWaterInMLD) {
		this.totalUnSafeWaterInMLD = totalUnSafeWaterInMLD;
	}
	public BigDecimal getTotalSafeWaterInMLD() {
		return totalSafeWaterInMLD;
	}
	public void setTotalSafeWaterInMLD(BigDecimal totalSafeWaterInMLD) {
		this.totalSafeWaterInMLD = totalSafeWaterInMLD;
	}
	public BigDecimal getSurfaceWaterSafeMLD() {
		return surfaceWaterSafeMLD;
	}
	public void setSurfaceWaterSafeMLD(BigDecimal surfaceWaterSafeMLD) {
		this.surfaceWaterSafeMLD = surfaceWaterSafeMLD;
	}
	public BigDecimal getSurfaceWaterUnSafeMLD() {
		return surfaceWaterUnSafeMLD;
	}
	public void setSurfaceWaterUnSafeMLD(BigDecimal surfaceWaterUnSafeMLD) {
		this.surfaceWaterUnSafeMLD = surfaceWaterUnSafeMLD;
	}
	public BigDecimal getGroundWaterUnSafeMLD() {
		return groundWaterUnSafeMLD;
	}
	public void setGroundWaterUnSafeMLD(BigDecimal groundWaterUnSafeMLD) {
		this.groundWaterUnSafeMLD = groundWaterUnSafeMLD;
	}
	public BigDecimal getGroundWaterSafeMLD() {
		return groundWaterSafeMLD;
	}
	public void setGroundWaterSafeMLD(BigDecimal groundWaterSafeMLD) {
		this.groundWaterSafeMLD = groundWaterSafeMLD;
	}
	public Double getPercentageOne() {
		return percentageOne;
	}
	public void setPercentageOne(Double percentageOne) {
		this.percentageOne = percentageOne;
	}
	public Double getPercentage() {
		return percentage;
	}
	public void setPercentage(Double percentage) {
		this.percentage = percentage;
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
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
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
	public Long getPhysicalTestCount() {
		return physicalTestCount;
	}
	public void setPhysicalTestCount(Long physicalTestCount) {
		this.physicalTestCount = physicalTestCount;
	}
	public Long getBacterialTestCount() {
		return bacterialTestCount;
	}
	public void setBacterialTestCount(Long bacterialTestCount) {
		this.bacterialTestCount = bacterialTestCount;
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
	

}
