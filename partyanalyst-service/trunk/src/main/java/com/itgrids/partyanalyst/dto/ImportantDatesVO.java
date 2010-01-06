package com.itgrids.partyanalyst.dto;

import java.util.Date;



public class ImportantDatesVO extends ResultStatus implements Comparable<ImportantDatesVO> {

	
	private static final long serialVersionUID = 1L;
	
	private Long importantDateId ;
	private String eventType;
	private Long eventId;
	private Date startDate;
	private Date impDate;
	private Date endDate;
	private String title ;
	private String importance ;
	private String frequency;
	private String isDeleted;
	
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getImpDate() {
		return impDate;
	}
	public void setImpDate(Date impDate) {
		this.impDate = impDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
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
	public int compareTo(ImportantDatesVO obj) {
		int result = obj.getImpDate().compareTo(this.getImpDate());
		return result;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
