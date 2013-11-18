package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class WSResultVO implements Serializable{

	private static final long serialVersionUID = 520759742831949517L;
	
	private Long id;
	private String name;
	private String location;
	private String description;
	
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
