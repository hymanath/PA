package com.itgrids.dto;

import java.util.ArrayList;
import java.util.List;

public class KPIVO {
	
	private List<Long> kpiVoList = new ArrayList<Long>(0);
	private String type="";
	private Long targetCount = 0l,achivmentCount = 0l;
	private String perc = "";
	
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public Long getTargetCount() {
		return targetCount;
	}
	public void setTargetCount(Long targetCount) {
		this.targetCount = targetCount;
	}
	public Long getAchivmentCount() {
		return achivmentCount;
	}
	public void setAchivmentCount(Long achivmentCount) {
		this.achivmentCount = achivmentCount;
	}
	public String getPerc() {
		return perc;
	}
	public void setPerc(String perc) {
		this.perc = perc;
	}
	public List<Long> getKpiVoList() {
		return kpiVoList;
	}
	public void setKpiVoList(List<Long> kpiVoList) {
		this.kpiVoList = kpiVoList;
	}
	
	
}
