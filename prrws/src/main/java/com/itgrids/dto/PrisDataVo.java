package com.itgrids.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

	/*
	 * Date : 04/07/2017
	 * Author :Nandhini
	 * @description : PrisDataVo Vo Class
	 */
public class PrisDataVo implements Serializable{
	private Long totalHouseHolds;
	private Long targetOverall;
	private Double targetOverallPercent=0.00;
	private Long achievedOverall;
	private Double achievedOverallpercent=0.00;
	private Long subTarget;
	private Double subTargetPercentage=0.00;
	private Long subAchieved;
	private Double subAchievedPercentage=0.00;
	private Long subTotal;
	
	public Long getTotalHouseHolds() {
		return totalHouseHolds;
	}
	public void setTotalHouseHolds(Long totalHouseHolds) {
		this.totalHouseHolds = totalHouseHolds;
	}
	public Long getTargetOverall() {
		return targetOverall;
	}
	public void setTargetOverall(Long targetOverall) {
		this.targetOverall = targetOverall;
	}
	public Double getTargetOverallPercent() {
		return targetOverallPercent;
	}
	public void setTargetOverallPercent(Double targetOverallPercent) {
		this.targetOverallPercent = targetOverallPercent;
	}
	public Long getAchievedOverall() {
		return achievedOverall;
	}
	public void setAchievedOverall(Long achievedOverall) {
		this.achievedOverall = achievedOverall;
	}
	public Double getAchievedOverallpercent() {
		return achievedOverallpercent;
	}
	public void setAchievedOverallpercent(Double achievedOverallpercent) {
		this.achievedOverallpercent = achievedOverallpercent;
	}
	public Long getSubTarget() {
		return subTarget;
	}
	public void setSubTarget(Long subTarget) {
		this.subTarget = subTarget;
	}
	public Double getSubTargetPercentage() {
		return subTargetPercentage;
	}
	public void setSubTargetPercentage(Double subTargetPercentage) {
		this.subTargetPercentage = subTargetPercentage;
	}
	public Long getSubAchieved() {
		return subAchieved;
	}
	public void setSubAchieved(Long subAchieved) {
		this.subAchieved = subAchieved;
	}
	public Double getSubAchievedPercentage() {
		return subAchievedPercentage;
	}
	public void setSubAchievedPercentage(Double subAchievedPercentage) {
		this.subAchievedPercentage = subAchievedPercentage;
	}
	public Long getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(Long subTotal) {
		this.subTotal = subTotal;
	}
	
}
