package com.itgrids.partyanalyst.dto;

import java.util.Date;

public class SimpleVO implements java.io.Serializable{
	
   private Long id;
   private String name;
   
   private String dateString;
   private Date date;
   
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
	public String getDateString() {
		return dateString;
	}
	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
   
   
   
}
