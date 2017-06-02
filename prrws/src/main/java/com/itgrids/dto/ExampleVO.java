package com.itgrids.dto;

import java.io.Serializable;

public class ExampleVO implements Serializable{
	
	private static final long serialVersionUID = -5436365311016546551L;
	
	private String subject;
	private String description;
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
