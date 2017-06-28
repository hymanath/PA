package com.itgrids.dto;

import java.util.ArrayList;
import java.util.List;

public class StatusVO {
	
	private Long id;
	private String name;
	private String status;
	private Long count=0l;
	private Long stressedCount;
	private Double percentage=0.0d;
	private String color;
	private Long target = 0l;
	private Long achived = 0l;
	private Long population = 0l;
	private Long budgetPreparedHubs;
	private Long popInStressedHubs;
	
	private List<StatusVO> statusList = new ArrayList<StatusVO>(0);
	
	private Long groundWaterSourceTotalMlpdCount;
	private Long surfaceWaterSourceTotalMlpdCount;
	private Long targetPopulation = 0l,achivedPopulation=0l;
	
	private Double percentageOne=0.0d;
	
	
	public Double getPercentageOne() {
		return percentageOne;
	}
	public void setPercentageOne(Double percentageOne) {
		this.percentageOne = percentageOne;
	}
	public Long getTargetPopulation() {
		return targetPopulation;
	}
	public void setTargetPopulation(Long targetPopulation) {
		this.targetPopulation = targetPopulation;
	}
	public Long getAchivedPopulation() {
		return achivedPopulation;
	}
	public void setAchivedPopulation(Long achivedPopulation) {
		this.achivedPopulation = achivedPopulation;
	}
	public Long getGroundWaterSourceTotalMlpdCount() {
		return groundWaterSourceTotalMlpdCount;
	}
	public void setGroundWaterSourceTotalMlpdCount(
			Long groundWaterSourceTotalMlpdCount) {
		this.groundWaterSourceTotalMlpdCount = groundWaterSourceTotalMlpdCount;
	}
	public Long getSurfaceWaterSourceTotalMlpdCount() {
		return surfaceWaterSourceTotalMlpdCount;
	}
	public void setSurfaceWaterSourceTotalMlpdCount(
			Long surfaceWaterSourceTotalMlpdCount) {
		this.surfaceWaterSourceTotalMlpdCount = surfaceWaterSourceTotalMlpdCount;
	}
	public List<StatusVO> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<StatusVO> statusList) {
		this.statusList = statusList;
	}
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
	public Long getPopulation() {
		return population;
	}
	public void setPopulation(Long population) {
		this.population = population;
	}
	public Long getBudgetPreparedHubs() {
		return budgetPreparedHubs;
	}
	public void setBudgetPreparedHubs(Long budgetPreparedHubs) {
		this.budgetPreparedHubs = budgetPreparedHubs;
	}
	public Long getPopInStressedHubs() {
		return popInStressedHubs;
	}
	public void setPopInStressedHubs(Long popInStressedHubs) {
		this.popInStressedHubs = popInStressedHubs;
	}
	
	

}
