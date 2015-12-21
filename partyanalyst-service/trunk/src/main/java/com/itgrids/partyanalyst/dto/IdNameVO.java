package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class IdNameVO implements Serializable{
	private Long id;
	private String name;
	private Long districtid;
	private Long availableCount;
	private Long actualCount=0l;
	
	public IdNameVO(){}
	
	public IdNameVO(Long id,String name){
		this.id=id;
		this.name=name;
	}
	
	public Long getAvailableCount() {
		return availableCount;
	}

	public void setAvailableCount(Long availableCount) {
		this.availableCount = availableCount;
	}

	public Long getActualCount() {
		return actualCount;
	}

	public void setActualCount(Long actualCount) {
		this.actualCount = actualCount;
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

	public Long getDistrictid() {
		return districtid;
	}

	public void setDistrictid(Long districtid) {
		this.districtid = districtid;
	}
	
	
	
}
