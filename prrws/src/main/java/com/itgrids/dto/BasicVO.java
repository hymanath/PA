package com.itgrids.dto;

import java.math.BigDecimal;

public class BasicVO {
	
	 private BigDecimal safeMLD;
	 private BigDecimal unsafeMLD;
	 private String assetType;
	 private Long physicalTestCount =0l;
	 private Long bacterialTestCount =0l;
	 private Long count=0l;
	 private Long workOngoingCount=0l;
	 private Long workComissionedCount=0l ;
	 private Long workCompletedCount=0l;
	 private Long workNotGroundedCount=0l;
	 
	 private Long total=0l;
		
	 
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
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
	

}
