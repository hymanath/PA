package com.itgrids.dto;

public class WaterSourceVO {
	private Long  safeSurfaceWaterSourceCount=0l,totalGroundWaterSourceCount=0l,unSafeGroundWaterSourceCount=0l,
			safeGroundWaterSourceCount=0l,unSafeSurfaceWaterSourceCount=0l,totalSurfaceWaterSourceCount=0l;
	private Long locationId,parentLocationId;
	private String locationName;

	
	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public Long getParentLocationId() {
		return parentLocationId;
	}

	public void setParentLocationId(Long parentLocationId) {
		this.parentLocationId = parentLocationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public Long getSafeSurfaceWaterSourceCount() {
		return safeSurfaceWaterSourceCount;
	}

	public void setSafeSurfaceWaterSourceCount(Long safeSurfaceWaterSourceCount) {
		this.safeSurfaceWaterSourceCount = safeSurfaceWaterSourceCount;
	}

	public Long getTotalGroundWaterSourceCount() {
		return totalGroundWaterSourceCount;
	}

	public void setTotalGroundWaterSourceCount(Long totalGroundWaterSourceCount) {
		this.totalGroundWaterSourceCount = totalGroundWaterSourceCount;
	}

	public Long getUnSafeGroundWaterSourceCount() {
		return unSafeGroundWaterSourceCount;
	}

	public void setUnSafeGroundWaterSourceCount(Long unSafeGroundWaterSourceCount) {
		this.unSafeGroundWaterSourceCount = unSafeGroundWaterSourceCount;
	}

	public Long getSafeGroundWaterSourceCount() {
		return safeGroundWaterSourceCount;
	}

	public void setSafeGroundWaterSourceCount(Long safeGroundWaterSourceCount) {
		this.safeGroundWaterSourceCount = safeGroundWaterSourceCount;
	}

	public Long getUnSafeSurfaceWaterSourceCount() {
		return unSafeSurfaceWaterSourceCount;
	}

	public void setUnSafeSurfaceWaterSourceCount(Long unSafeSurfaceWaterSourceCount) {
		this.unSafeSurfaceWaterSourceCount = unSafeSurfaceWaterSourceCount;
	}

	public Long getTotalSurfaceWaterSourceCount() {
		return totalSurfaceWaterSourceCount;
	}

	public void setTotalSurfaceWaterSourceCount(Long totalSurfaceWaterSourceCount) {
		this.totalSurfaceWaterSourceCount = totalSurfaceWaterSourceCount;
	}
	
}
