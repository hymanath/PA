package com.itgrids.dto;

public class VehicleTrackingVO {
	
	private Long id;
	private String name;
	private Long offFieldVehicles = 0L;
	private Long pendingTrips = 0L;
	private Long targetTrips = 0L;
	private Long compltedTrips = 0L;
	private Long inProgressTrips = 0L;
	
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
}
