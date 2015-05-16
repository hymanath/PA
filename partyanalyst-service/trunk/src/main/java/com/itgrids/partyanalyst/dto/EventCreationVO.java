package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class EventCreationVO implements java.io.Serializable{
	
	private Long eventId;
	private Long rfidL;
	private Integer selectorL;
	private Integer blockNoL;
	private String regTextStr;
	private String rfidStr;
	
	private List<EventCreationVO> subList=new ArrayList<EventCreationVO>();
	
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public Long getRfidL() {
		return rfidL;
	}
	public void setRfidL(Long rfidL) {
		this.rfidL = rfidL;
	}
	public Integer getSelectorL() {
		return selectorL;
	}
	public void setSelectorL(Integer selectorL) {
		this.selectorL = selectorL;
	}
	
	public Integer getBlockNoL() {
		return blockNoL;
	}
	public void setBlockNoL(Integer blockNoL) {
		this.blockNoL = blockNoL;
	}
	public String getRegTextStr() {
		return regTextStr;
	}
	public void setRegTextStr(String regTextStr) {
		this.regTextStr = regTextStr;
	}
	public String getRfidStr() {
		return rfidStr;
	}
	public void setRfidStr(String rfidStr) {
		this.rfidStr = rfidStr;
	}
	public List<EventCreationVO> getSubList() {
		return subList;
	}
	public void setSubList(List<EventCreationVO> subList) {
		this.subList = subList;
	}
	
	
	

}
