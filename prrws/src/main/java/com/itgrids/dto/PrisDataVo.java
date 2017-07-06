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
	private Double targetOverallPercent;
	private Long achievedOverall;
	private Double achievedOverallpercent;
	private Long subTarget;
	private Double subTargetPercentage;
	private Long subAchieved;
	private Double subAchievedPercentage;
	private Long subTotal;
	
	private List<PrisDataVo> totalList = new ArrayList<PrisDataVo>(0);
	private List<PrisDataVo> subTotalList = new ArrayList<PrisDataVo>(0);
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
	public List<PrisDataVo> getTotalList() {
		return totalList;
	}
	public void setTotalList(List<PrisDataVo> totalList) {
		this.totalList = totalList;
	}
	public List<PrisDataVo> getSubTotalList() {
		return subTotalList;
	}
	public void setSubTotalList(List<PrisDataVo> subTotalList) {
		this.subTotalList = subTotalList;
	}
	public Long getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(Long subTotal) {
		this.subTotal = subTotal;
	}
	
}
