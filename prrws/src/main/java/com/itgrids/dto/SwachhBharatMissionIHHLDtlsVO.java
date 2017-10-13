package com.itgrids.dto;

import java.util.List;

public class SwachhBharatMissionIHHLDtlsVO {

	private Long id;
	private Long target;
	private Long achivement;
	private Double percentage;
	private Long locationId;
	private String name;
	private Long grounded;
	private Long noTGrounded;
	private Long completed;
	private Long inProgress;
	private List<SwachhBharatMissionIHHLDtlsVO> subList;
	
	private Long districtCount;
	private Long constituencyCount;
	private Long mandalCount;
	
	private String range;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTarget() {
		return target;
	}
	public void setTarget(Long target) {
		this.target = target;
	}
	public Long getAchivement() {
		return achivement;
	}
	public void setAchivement(Long achivement) {
		this.achivement = achivement;
	}
	public Double getPercentage() {
		return percentage;
	}
	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	public Long getGrounded() {
		return grounded;
	}
	public void setGrounded(Long grounded) {
		this.grounded = grounded;
	}
	public Long getNoTGrounded() {
		return noTGrounded;
	}
	public void setNoTGrounded(Long noTGrounded) {
		this.noTGrounded = noTGrounded;
	}
	public Long getCompleted() {
		return completed;
	}
	public void setCompleted(Long completed) {
		this.completed = completed;
	}
	public Long getInProgress() {
		return inProgress;
	}
	public void setInProgress(Long inProgress) {
		this.inProgress = inProgress;
	}
	public List<SwachhBharatMissionIHHLDtlsVO> getSubList() {
		return subList;
	}
	public void setSubList(List<SwachhBharatMissionIHHLDtlsVO> subList) {
		this.subList = subList;
	}
	public Long getDistrictCount() {
		return districtCount;
	}
	public void setDistrictCount(Long districtCount) {
		this.districtCount = districtCount;
	}
	public Long getConstituencyCount() {
		return constituencyCount;
	}
	public void setConstituencyCount(Long constituencyCount) {
		this.constituencyCount = constituencyCount;
	}
	public Long getMandalCount() {
		return mandalCount;
	}
	public void setMandalCount(Long mandalCount) {
		this.mandalCount = mandalCount;
	}
	
	
}
