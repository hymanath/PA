package com.itgrids.partyanalyst.dto;

import java.util.Date;



public class ImportantDatesVO  extends ResultStatus{

	
	private static final long serialVersionUID = 1L;
	
	private Long importantDateId ;
	private String eventType;
	private Long eventId;
	private String startDate;
	private String impDate;
	private String endDate;
	private String title ;
	private String importance ;
	
	public Long getImportantDateId() {
		return importantDateId;
	}
	public void setImportantDateId(Long importantDateId) {
		this.importantDateId = importantDateId;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getImpDate() {
		return impDate;
	}
	public void setImpDate(String impDate) {
		this.impDate = impDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImportance() {
		return importance;
	}
	public void setImportance(String importance) {
		this.importance = importance;
	}
	
}
