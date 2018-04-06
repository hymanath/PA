package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class InchargeMemberVO implements Serializable {
	private Long zeroPerCount =0l;
	private Long oneToTenCount=0l;
	private Long tenToTwentyCount=0l;
	private Long twentyToThirCount =0l;
	private Long thirToFrtyCount =0l;
	private Long frtyToFivtyCount =0l;
	private Long fivtyToSixtyCount =0l;
	private Long sixtyToSeventyCount =0l;
	private Long seventyToEightyCount =0l;
	private Long eightyToNightyCount =0l;
	private Long aboveNighty =0l;
	private Long count =0l;
	private Double assignPer =0.0;
	private String name;
	public Long getZeroPerCount() {
		return zeroPerCount;
	}
	public void setZeroPerCount(Long zeroPerCount) {
		this.zeroPerCount = zeroPerCount;
	}
	public Long getOneToTenCount() {
		return oneToTenCount;
	}
	public void setOneToTenCount(Long oneToTenCount) {
		this.oneToTenCount = oneToTenCount;
	}
	public Long getTenToTwentyCount() {
		return tenToTwentyCount;
	}
	public void setTenToTwentyCount(Long tenToTwentyCount) {
		this.tenToTwentyCount = tenToTwentyCount;
	}
	public Long getTwentyToThirCount() {
		return twentyToThirCount;
	}
	public void setTwentyToThirCount(Long twentyToThirCount) {
		this.twentyToThirCount = twentyToThirCount;
	}
	public Long getThirToFrtyCount() {
		return thirToFrtyCount;
	}
	public void setThirToFrtyCount(Long thirToFrtyCount) {
		this.thirToFrtyCount = thirToFrtyCount;
	}
	public Long getFrtyToFivtyCount() {
		return frtyToFivtyCount;
	}
	public void setFrtyToFivtyCount(Long frtyToFivtyCount) {
		this.frtyToFivtyCount = frtyToFivtyCount;
	}
	public Long getFivtyToSixtyCount() {
		return fivtyToSixtyCount;
	}
	public void setFivtyToSixtyCount(Long fivtyToSixtyCount) {
		this.fivtyToSixtyCount = fivtyToSixtyCount;
	}
	public Long getSixtyToSeventyCount() {
		return sixtyToSeventyCount;
	}
	public void setSixtyToSeventyCount(Long sixtyToSeventyCount) {
		this.sixtyToSeventyCount = sixtyToSeventyCount;
	}
	public Long getSeventyToEightyCount() {
		return seventyToEightyCount;
	}
	public void setSeventyToEightyCount(Long seventyToEightyCount) {
		this.seventyToEightyCount = seventyToEightyCount;
	}
	public Long getEightyToNightyCount() {
		return eightyToNightyCount;
	}
	public void setEightyToNightyCount(Long eightyToNightyCount) {
		this.eightyToNightyCount = eightyToNightyCount;
	}
	public Long getAboveNighty() {
		return aboveNighty;
	}
	public void setAboveNighty(Long aboveNighty) {
		this.aboveNighty = aboveNighty;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Double getAssignPer() {
		return assignPer;
	}
	public void setAssignPer(Double assignPer) {
		this.assignPer = assignPer;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
