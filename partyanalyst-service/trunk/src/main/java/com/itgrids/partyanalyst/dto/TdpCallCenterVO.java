package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class TdpCallCenterVO {
private Long id;
private String name;
private String date;
private String time;
private String purpose;
private String suggestions;
private Long executiveId;
private String executiveName;
private Long total=0l;
private Long todayTotal =0l;
private List<TdpCallCenterVO> subList = new ArrayList<TdpCallCenterVO>();

public List<TdpCallCenterVO> getSubList() {
	return subList;
}
public void setSubList(List<TdpCallCenterVO> subList) {
	this.subList = subList;
}
public Long getTotal() {
	return total;
}
public void setTotal(Long total) {
	this.total = total;
}
public Long getTodayTotal() {
	return todayTotal;
}
public void setTodayTotal(Long todayTotal) {
	this.todayTotal = todayTotal;
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
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}
public String getPurpose() {
	return purpose;
}
public void setPurpose(String purpose) {
	this.purpose = purpose;
}
public String getSuggestions() {
	return suggestions;
}
public void setSuggestions(String suggestions) {
	this.suggestions = suggestions;
}
public Long getExecutiveId() {
	return executiveId;
}
public void setExecutiveId(Long executiveId) {
	this.executiveId = executiveId;
}
public String getExecutiveName() {
	return executiveName;
}
public void setExecutiveName(String executiveName) {
	this.executiveName = executiveName;
}


}
