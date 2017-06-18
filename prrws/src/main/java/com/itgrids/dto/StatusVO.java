package com.itgrids.dto;

public class StatusVO {
	
	private Long id;
	private String name;
	private String status;
	private Long count=0l;
	private Long stressedCount;
	private Double percentage=0.0d;
	private String color;
	private Long target;
	private Long achived;
	
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
	public Long getStressedCount() {
		return stressedCount;
	}
	public void setStressedCount(Long stressedCount) {
		this.stressedCount = stressedCount;
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
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Long getTarget() {
		return target;
	}
	public void setTarget(Long target) {
		this.target = target;
	}
	public Long getAchived() {
		return achived;
	}
	public void setAchived(Long achived) {
		this.achived = achived;
	}
	
	

}
