package com.itgrids.dto;

public class VehicleTrackingVO {
	
	private Long id;
	private String name;
	private Long offFieldVehicles = 0L;
	private Long pendingTrips = 0L;
	private Long targetTrips = 0L;
	private Long compltedTrips = 0L;
	private Long inProgressTrips = 0L;
	private Long totalVehicles = 0L;
	private Long onFieldVehicles = 0L;
	private String onFieldVehiclePerc;
	private String offFieldVehiclePerc;
	private String targetWater;
	private String suppliedWater;
	private String notSuppliedWater;
	private String suppliedWaterPerc;
	private String notSuppliedWaterPerc;
	private String inProgressTripsPerc;
	private String compltedTripsPerc;
	private Long totalStressedHabs = 0L;
	private Long coveredHabs = 0L;
	private Long notCoveredHabs = 0L;
	private String coveredHabsPerc;
	private String notCoveredHabsPerc;
	private Long withGPSTracking = 0L;
	private Long withOutGPSTracking = 0L;
	private Long withGPSInProgress = 0L;
	private Long withGPSComplted  = 0L;
	private String withGPSInProgressPerc;
	private String withGPSCompltedPerc;
	private Long inProgressOnLineVehicleStatus = 0L;
	private Long inProgressOffLineVehicleStatus = 0L;
	private Long withOutGPSInProgress = 0L;
	private Long withOutGPSComplted  = 0L;
	private String withOutGPSInProgressPerc;
	private String withOutGPSCompltedPerc;
	
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
	public Long getOffFieldVehicles() {
		return offFieldVehicles;
	}
	public void setOffFieldVehicles(Long offFieldVehicles) {
		this.offFieldVehicles = offFieldVehicles;
	}
	public Long getPendingTrips() {
		return pendingTrips;
	}
	public void setPendingTrips(Long pendingTrips) {
		this.pendingTrips = pendingTrips;
	}
	public Long getTargetTrips() {
		return targetTrips;
	}
	public void setTargetTrips(Long targetTrips) {
		this.targetTrips = targetTrips;
	}
	public Long getCompltedTrips() {
		return compltedTrips;
	}
	public void setCompltedTrips(Long compltedTrips) {
		this.compltedTrips = compltedTrips;
	}
	public Long getInProgressTrips() {
		return inProgressTrips;
	}
	public void setInProgressTrips(Long inProgressTrips) {
		this.inProgressTrips = inProgressTrips;
	}
	public Long getTotalVehicles() {
		return totalVehicles;
	}
	public void setTotalVehicles(Long totalVehicles) {
		this.totalVehicles = totalVehicles;
	}
	public Long getOnFieldVehicles() {
		return onFieldVehicles;
	}
	public void setOnFieldVehicles(Long onFieldVehicles) {
		this.onFieldVehicles = onFieldVehicles;
	}
	public String getOnFieldVehiclePerc() {
		return onFieldVehiclePerc;
	}
	public void setOnFieldVehiclePerc(String onFieldVehiclePerc) {
		this.onFieldVehiclePerc = onFieldVehiclePerc;
	}
	public String getOffFieldVehiclePerc() {
		return offFieldVehiclePerc;
	}
	public void setOffFieldVehiclePerc(String offFieldVehiclePerc) {
		this.offFieldVehiclePerc = offFieldVehiclePerc;
	}
	public String getSuppliedWaterPerc() {
		return suppliedWaterPerc;
	}
	public void setSuppliedWaterPerc(String suppliedWaterPerc) {
		this.suppliedWaterPerc = suppliedWaterPerc;
	}
	public String getNotSuppliedWaterPerc() {
		return notSuppliedWaterPerc;
	}
	public void setNotSuppliedWaterPerc(String notSuppliedWaterPerc) {
		this.notSuppliedWaterPerc = notSuppliedWaterPerc;
	}
	public String getInProgressTripsPerc() {
		return inProgressTripsPerc;
	}
	public void setInProgressTripsPerc(String inProgressTripsPerc) {
		this.inProgressTripsPerc = inProgressTripsPerc;
	}
	public String getCompltedTripsPerc() {
		return compltedTripsPerc;
	}
	public void setCompltedTripsPerc(String compltedTripsPerc) {
		this.compltedTripsPerc = compltedTripsPerc;
	}
	public Long getTotalStressedHabs() {
		return totalStressedHabs;
	}
	public void setTotalStressedHabs(Long totalStressedHabs) {
		this.totalStressedHabs = totalStressedHabs;
	}
	public Long getCoveredHabs() {
		return coveredHabs;
	}
	public void setCoveredHabs(Long coveredHabs) {
		this.coveredHabs = coveredHabs;
	}
	public Long getNotCoveredHabs() {
		return notCoveredHabs;
	}
	public void setNotCoveredHabs(Long notCoveredHabs) {
		this.notCoveredHabs = notCoveredHabs;
	}
	public String getCoveredHabsPerc() {
		return coveredHabsPerc;
	}
	public void setCoveredHabsPerc(String coveredHabsPerc) {
		this.coveredHabsPerc = coveredHabsPerc;
	}
	public String getNotCoveredHabsPerc() {
		return notCoveredHabsPerc;
	}
	public void setNotCoveredHabsPerc(String notCoveredHabsPerc) {
		this.notCoveredHabsPerc = notCoveredHabsPerc;
	}
	public Long getWithGPSTracking() {
		return withGPSTracking;
	}
	public void setWithGPSTracking(Long withGPSTracking) {
		this.withGPSTracking = withGPSTracking;
	}
	public Long getWithOutGPSTracking() {
		return withOutGPSTracking;
	}
	public void setWithOutGPSTracking(Long withOutGPSTracking) {
		this.withOutGPSTracking = withOutGPSTracking;
	}
	public Long getWithGPSInProgress() {
		return withGPSInProgress;
	}
	public void setWithGPSInProgress(Long withGPSInProgress) {
		this.withGPSInProgress = withGPSInProgress;
	}
	public Long getWithGPSComplted() {
		return withGPSComplted;
	}
	public void setWithGPSComplted(Long withGPSComplted) {
		this.withGPSComplted = withGPSComplted;
	}
	public String getWithGPSInProgressPerc() {
		return withGPSInProgressPerc;
	}
	public void setWithGPSInProgressPerc(String withGPSInProgressPerc) {
		this.withGPSInProgressPerc = withGPSInProgressPerc;
	}
	public String getWithGPSCompltedPerc() {
		return withGPSCompltedPerc;
	}
	public void setWithGPSCompltedPerc(String withGPSCompltedPerc) {
		this.withGPSCompltedPerc = withGPSCompltedPerc;
	}
	public Long getInProgressOnLineVehicleStatus() {
		return inProgressOnLineVehicleStatus;
	}
	public void setInProgressOnLineVehicleStatus(Long inProgressOnLineVehicleStatus) {
		this.inProgressOnLineVehicleStatus = inProgressOnLineVehicleStatus;
	}
	public Long getInProgressOffLineVehicleStatus() {
		return inProgressOffLineVehicleStatus;
	}
	public void setInProgressOffLineVehicleStatus(Long inProgressOffLineVehicleStatus) {
		this.inProgressOffLineVehicleStatus = inProgressOffLineVehicleStatus;
	}
	public Long getWithOutGPSInProgress() {
		return withOutGPSInProgress;
	}
	public void setWithOutGPSInProgress(Long withOutGPSInProgress) {
		this.withOutGPSInProgress = withOutGPSInProgress;
	}
	public Long getWithOutGPSComplted() {
		return withOutGPSComplted;
	}
	public void setWithOutGPSComplted(Long withOutGPSComplted) {
		this.withOutGPSComplted = withOutGPSComplted;
	}
	public String getWithOutGPSInProgressPerc() {
		return withOutGPSInProgressPerc;
	}
	public void setWithOutGPSInProgressPerc(String withOutGPSInProgressPerc) {
		this.withOutGPSInProgressPerc = withOutGPSInProgressPerc;
	}
	public String getWithOutGPSCompltedPerc() {
		return withOutGPSCompltedPerc;
	}
	public void setWithOutGPSCompltedPerc(String withOutGPSCompltedPerc) {
		this.withOutGPSCompltedPerc = withOutGPSCompltedPerc;
	}
	public String getTargetWater() {
		return targetWater;
	}
	public void setTargetWater(String targetWater) {
		this.targetWater = targetWater;
	}
	public String getSuppliedWater() {
		return suppliedWater;
	}
	public void setSuppliedWater(String suppliedWater) {
		this.suppliedWater = suppliedWater;
	}
	public String getNotSuppliedWater() {
		return notSuppliedWater;
	}
	public void setNotSuppliedWater(String notSuppliedWater) {
		this.notSuppliedWater = notSuppliedWater;
	}
}
