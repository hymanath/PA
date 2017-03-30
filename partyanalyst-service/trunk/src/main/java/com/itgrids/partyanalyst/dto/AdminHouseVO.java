package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AdminHouseVO implements Serializable{
	
	private Long id;
	private String date;
	private String name;
	private Long sessionId;
	private Long count = 0l;
	private Long partyId;
	private String partyName;
	private String startDate;
	private List<AdminHouseVO> partyList = new ArrayList<AdminHouseVO>(0); 
	private Long adminHouseSessionId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getSessionId() {
		return sessionId;
	}
	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}
	public Long getCount() {
		return count;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public List<AdminHouseVO> getPartyList() {
		return partyList;
	}
	public void setPartyList(List<AdminHouseVO> partyList) {
		this.partyList = partyList;
	}
	public Long getAdminHouseSessionId() {
		return adminHouseSessionId;
	}
	public void setAdminHouseSessionId(Long adminHouseSessionId) {
		this.adminHouseSessionId = adminHouseSessionId;
	}
	
}
