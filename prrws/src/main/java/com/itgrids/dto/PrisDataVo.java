package com.itgrids.dto;

import java.io.Serializable;

	/*
	 * Date : 04/07/2017
	 * Author :Nandhini
	 * @description : PrisDataVo Vo Class
	 */
public class PrisDataVo implements Serializable{
	private Long totalHouseHolds;
	private Long targetOverall;
	private String targetOverallPercent;
	private Long achievedOverall;
	private String achievedOverallpercent;
	private Long subTarget;
	private String subTargetPercentage;
	private Long subAchieved;
	private String subAchievedPercentage;
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
	public String getTargetOverallPercent() {
		return targetOverallPercent;
	}
	public void setTargetOverallPercent(String targetOverallPercent) {
		this.targetOverallPercent = targetOverallPercent;
	}
	public Long getAchievedOverall() {
		return achievedOverall;
	}
	public void setAchievedOverall(Long achievedOverall) {
		this.achievedOverall = achievedOverall;
	}
	public String getAchievedOverallpercent() {
		return achievedOverallpercent;
	}
	public void setAchievedOverallpercent(String achievedOverallpercent) {
		this.achievedOverallpercent = achievedOverallpercent;
	}
	public Long getSubTarget() {
		return subTarget;
	}
	public void setSubTarget(Long subTarget) {
		this.subTarget = subTarget;
	}
	public String getSubTargetPercentage() {
		return subTargetPercentage;
	}
	public void setSubTargetPercentage(String subTargetPercentage) {
		this.subTargetPercentage = subTargetPercentage;
	}
	public Long getSubAchieved() {
		return subAchieved;
	}
	public void setSubAchieved(Long subAchieved) {
		this.subAchieved = subAchieved;
	}
	public String getSubAchievedPercentage() {
		return subAchievedPercentage;
	}
	public void setSubAchievedPercentage(String subAchievedPercentage) {
		this.subAchievedPercentage = subAchievedPercentage;
	}
}
