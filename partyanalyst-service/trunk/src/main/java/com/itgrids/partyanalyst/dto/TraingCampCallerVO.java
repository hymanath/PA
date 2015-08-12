package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class TraingCampCallerVO {
	private Long id;
	private String name;
	private Long total = 0l;
	private Long count =0l;
	private Long spanCnt = 0l;
	private Long todayCnt;
	
	
	public Long getTodayCnt() {
		return todayCnt;
	}
	public void setTodayCnt(Long todayCnt) {
		this.todayCnt = todayCnt;
	}
	public Long getSpanCnt() {
		return spanCnt;
	}
	public void setSpanCnt(Long spanCnt) {
		this.spanCnt = spanCnt;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	private List<TraingCampCallerVO> subList = new ArrayList<TraingCampCallerVO>();
	private List<TraingCampCallerVO> scheduleStatusList = new ArrayList<TraingCampCallerVO>();
	
	public List<TraingCampCallerVO> getScheduleStatusList() {
		return scheduleStatusList;
	}
	public void setScheduleStatusList(List<TraingCampCallerVO> scheduleStatusList) {
		this.scheduleStatusList = scheduleStatusList;
	}
	private String batchName;
	
	
	public String getBatchName() {
		return batchName;
	}
	public void setBatchName(String batchName) {
		this.batchName = batchName;
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
	public List<TraingCampCallerVO> getSubList() {
		return subList;
	}
	public void setSubList(List<TraingCampCallerVO> subList) {
		this.subList = subList;
	}
	

}
