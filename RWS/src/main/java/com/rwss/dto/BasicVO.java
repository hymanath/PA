package com.rwss.dto;

import java.math.BigDecimal;

public class BasicVO {
	
	 private BigDecimal safeMLD;
	 private BigDecimal unsafeMLD;
	 private String assetType;
	 private Long physicalTestCount ;
	 private Long bacterialTestCount ;
	 private Long count;
	 private Long workOngoingCount;
	 private Long workComissionedCount ;
	 private Long workCompletedCount;
	 private Long workNotGroundedCount;
	 private String status;
	 private Long groundWaterSourceCount;
	 private Long surfaceWaterSourceCount;
	 private Long groundWaterSourceTotalLpdCount;
	 private Long surfaceWaterSourceTotalLpdCount;
	 private String exceptionMessage;

		
	public BigDecimal getSafeMLD() {
		return safeMLD;
	}
	public void setSafeMLD(BigDecimal safeMLD) {
		this.safeMLD = safeMLD;
	}
	public BigDecimal getUnsafeMLD() {
		return unsafeMLD;
	}
	public void setUnsafeMLD(BigDecimal unsafeMLD) {
		this.unsafeMLD = unsafeMLD;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getGroundWaterSourceCount() {
		return groundWaterSourceCount;
	}
	public void setGroundWaterSourceCount(Long groundWaterSourceCount) {
		this.groundWaterSourceCount = groundWaterSourceCount;
	}
	public Long getSurfaceWaterSourceCount() {
		return surfaceWaterSourceCount;
	}
	public void setSurfaceWaterSourceCount(Long surfaceWaterSourceCount) {
		this.surfaceWaterSourceCount = surfaceWaterSourceCount;
	}
	public Long getGroundWaterSourceTotalLpdCount() {
		return groundWaterSourceTotalLpdCount;
	}
	public void setGroundWaterSourceTotalLpdCount(
			Long groundWaterSourceTotalLpdCount) {
		this.groundWaterSourceTotalLpdCount = groundWaterSourceTotalLpdCount;
	}
	public Long getSurfaceWaterSourceTotalLpdCount() {
		return surfaceWaterSourceTotalLpdCount;
	}
	public void setSurfaceWaterSourceTotalLpdCount(
			Long surfaceWaterSourceTotalLpdCount) {
		this.surfaceWaterSourceTotalLpdCount = surfaceWaterSourceTotalLpdCount;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}
	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
	

}
