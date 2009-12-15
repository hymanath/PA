package com.itgrids.partyanalyst.dto;

import java.util.Date;



public class PartyImportantDatesVO  extends ResultStatus{

	
	private static final long serialVersionUID = 1L;
	
	private Long importantDateId ;
	private String date;
	private String importance ;
	private Long partyId;
	private String recursive;
	
	public Long getImportantDateId() {
		return importantDateId;
	}
	public void setImportantDateId(Long importantDateId) {
		this.importantDateId = importantDateId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getImportance() {
		return importance;
	}
	public void setImportance(String importance) {
		this.importance = importance;
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public String getRecursive() {
		return recursive;
	}
	public void setRecursive(String recursive) {
		this.recursive = recursive;
	}
	
	
}
