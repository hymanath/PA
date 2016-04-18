package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class AppointmentCountsVO implements Serializable{
	
	private Long count=0l;
	private Long apCount=0l;
	private Long tsCount=0l; 
	
	private Long uniqueCount=0l;
	private Long uniqueApCount=0l;
	private Long uniqueTsCount=0l;
	
	private Long id;
	private String name;
	
	private List<AppointmentCountsVO> totalCountsVOList;
	private List<AppointmentCountsVO> scheduledCountsVOList;
	private List<AppointmentCountsVO> waitingCountsVOList;
	
	private String resultMsg;
	
	
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getApCount() {
		return apCount;
	}
	public void setApCount(Long apCount) {
		this.apCount = apCount;
	}
	public Long getTsCount() {
		return tsCount;
	}
	public void setTsCount(Long tsCount) {
		this.tsCount = tsCount;
	}
	public Long getUniqueCount() {
		return uniqueCount;
	}
	public void setUniqueCount(Long uniqueCount) {
		this.uniqueCount = uniqueCount;
	}
	public Long getUniqueApCount() {
		return uniqueApCount;
	}
	public void setUniqueApCount(Long uniqueApCount) {
		this.uniqueApCount = uniqueApCount;
	}
	public Long getUniqueTsCount() {
		return uniqueTsCount;
	}
	public void setUniqueTsCount(Long uniqueTsCount) {
		this.uniqueTsCount = uniqueTsCount;
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
	public List<AppointmentCountsVO> getTotalCountsVOList() {
		return totalCountsVOList;
	}
	public void setTotalCountsVOList(List<AppointmentCountsVO> totalCountsVOList) {
		this.totalCountsVOList = totalCountsVOList;
	}
	public List<AppointmentCountsVO> getScheduledCountsVOList() {
		return scheduledCountsVOList;
	}
	public void setScheduledCountsVOList(
			List<AppointmentCountsVO> scheduledCountsVOList) {
		this.scheduledCountsVOList = scheduledCountsVOList;
	}
	public List<AppointmentCountsVO> getWaitingCountsVOList() {
		return waitingCountsVOList;
	}
	public void setWaitingCountsVOList(List<AppointmentCountsVO> waitingCountsVOList) {
		this.waitingCountsVOList = waitingCountsVOList;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	
	
}
