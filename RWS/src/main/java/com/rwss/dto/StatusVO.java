package com.rwss.dto;

public class StatusVO {
	
	private String status;
	private Long count=0l;
	private Long stressedHabitationCount;
	private Double percentage=0.0d;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Double getPercentage() {
		return percentage;
	}
	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
	public Long getStressedHabitationCount() {
		return stressedHabitationCount;
	}
	public void setStressedHabitationCount(Long stressedHabitationCount) {
		this.stressedHabitationCount = stressedHabitationCount;
	}
	
	
	

}
