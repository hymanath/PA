package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class IdNameVO implements Serializable{
	private Long id;
	private String name;
	private Long districtid; 
	
	public IdNameVO(){}
	
	public IdNameVO(Long id,String name){
		this.id=id;
		this.name=name;
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
