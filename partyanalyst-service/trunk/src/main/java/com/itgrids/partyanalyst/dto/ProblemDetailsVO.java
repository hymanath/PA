package com.itgrids.partyanalyst.dto;

import java.util.Date;

public class ProblemDetailsVO {

	private Long problemID;
	private String definition;
	private String description;
	private Date identifiedDate;
	private String location;	
	private String source;
	
	public Long getProblemID() {
		return problemID;
	}
	public void setProblemID(Long problemID) {
		this.problemID = problemID;
	}
	public String getDefinition() {
		return definition;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getIdentifiedDate() {
		return identifiedDate;
	}
	public void setIdentifiedDate(Date identifiedDate) {
		this.identifiedDate = identifiedDate;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
}
