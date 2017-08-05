package com.itgrids.dto;

import java.io.Serializable;

public class FundVO implements Serializable{
	
	private static final long serialVersionUID = -5436365311016546551L;
	
	private Long id;
	private String name;
	private Long highLocId;
	private Long lowLocId;
	private String highLocName;
	private String lowLocName;
	private Long highTotalAmt;
	private String highCroreAmt;
	private Long lowTotalAmt;
	private String lowCroreAmt;
	private String avgCroreAmt;
	private Long avgAmt;
	private Long levelId;
	
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
	public Long getHighLocId() {
		return highLocId;
	}
	public void setHighLocId(Long highLocId) {
		this.highLocId = highLocId;
	}
	public Long getLowLocId() {
		return lowLocId;
	}
	public void setLowLocId(Long lowLocId) {
		this.lowLocId = lowLocId;
	}
	public String getHighLocName() {
		return highLocName;
	}
	public void setHighLocName(String highLocName) {
		this.highLocName = highLocName;
	}
	public String getLowLocName() {
		return lowLocName;
	}
	public void setLowLocName(String lowLocName) {
		this.lowLocName = lowLocName;
	}
	
	public String getAvgCroreAmt() {
		return avgCroreAmt;
	}
	public void setAvgCroreAmt(String avgCroreAmt) {
		this.avgCroreAmt = avgCroreAmt;
	}
	public Long getAvgAmt() {
		return avgAmt;
	}
	public void setAvgAmt(Long avgAmt) {
		this.avgAmt = avgAmt;
	}
	public Long getHighTotalAmt() {
		return highTotalAmt;
	}
	public void setHighTotalAmt(Long highTotalAmt) {
		this.highTotalAmt = highTotalAmt;
	}
	public String getHighCroreAmt() {
		return highCroreAmt;
	}
	public void setHighCroreAmt(String highCroreAmt) {
		this.highCroreAmt = highCroreAmt;
	}
	public Long getLowTotalAmt() {
		return lowTotalAmt;
	}
	public void setLowTotalAmt(Long lowTotalAmt) {
		this.lowTotalAmt = lowTotalAmt;
	}
	public String getLowCroreAmt() {
		return lowCroreAmt;
	}
	public void setLowCroreAmt(String lowCroreAmt) {
		this.lowCroreAmt = lowCroreAmt;
	}
	public Long getLevelId() {
		return levelId;
	}
	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}
	
	
	
	
}
