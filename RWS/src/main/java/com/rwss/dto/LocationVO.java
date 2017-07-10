package com.rwss.dto;

import java.util.ArrayList;
import java.util.List;

public class LocationVO {
	
	private String districtId;
	private String locationId;
	private String locationName;
	private Long stressedHabitationCount;
	private Long totalCount;
	private List<StatusVO> statusList;
	private List<LocationVO> subList = new ArrayList<LocationVO>(0);
	private String status;
	private String exceptionMessage;
	private String assetType;
	private Long workOngoingCount;
	private Long workComissionedCount ;
	private Long workCompletedCount;
	private Long workNotGroundedCount;
	private String name;
	private Long count;
	private Long safeSurfaceCount;
	private Long unSafeSurfaceCount;
	private Long safeGroundCount;
	private Long unSafeGroundCount;
	private Long groundTotalCount;
	private Long surfaceTotalCount;
	
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public List<StatusVO> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<StatusVO> statusList) {
		this.statusList = statusList;
	}
	public Long getStressedHabitationCount() {
		return stressedHabitationCount;
	}
	public void setStressedHabitationCount(Long stressedHabitationCount) {
		this.stressedHabitationCount = stressedHabitationCount;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public List<LocationVO> getSubList() {
		return subList;
	}
	public void setSubList(List<LocationVO> subList) {
		this.subList = subList;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getExceptionMessage() {
		return exceptionMessage;
	}
	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getSafeSurfaceCount() {
		return safeSurfaceCount;
	}
	public void setSafeSurfaceCount(Long safeSurfaceCount) {
		this.safeSurfaceCount = safeSurfaceCount;
	}
	public Long getUnSafeSurfaceCount() {
		return unSafeSurfaceCount;
	}
	public void setUnSafeSurfaceCount(Long unSafeSurfaceCount) {
		this.unSafeSurfaceCount = unSafeSurfaceCount;
	}
	public Long getSafeGroundCount() {
		return safeGroundCount;
	}
	public void setSafeGroundCount(Long safeGroundCount) {
		this.safeGroundCount = safeGroundCount;
	}
	public Long getUnSafeGroundCount() {
		return unSafeGroundCount;
	}
	public void setUnSafeGroundCount(Long unSafeGroundCount) {
		this.unSafeGroundCount = unSafeGroundCount;
	}
	public Long getGroundTotalCount() {
		return groundTotalCount;
	}
	public void setGroundTotalCount(Long groundTotalCount) {
		this.groundTotalCount = groundTotalCount;
	}
	public Long getSurfaceTotalCount() {
		return surfaceTotalCount;
	}
	public void setSurfaceTotalCount(Long surfaceTotalCount) {
		this.surfaceTotalCount = surfaceTotalCount;
	}
	
   
}
