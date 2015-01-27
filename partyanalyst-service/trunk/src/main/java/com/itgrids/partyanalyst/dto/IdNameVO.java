package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class IdNameVO implements Serializable{
	private Long id;
	private String name;
	
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
	
	
}
