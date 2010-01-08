package com.itgrids.partyanalyst.dto;

public class MandalVO {

	private static final long serialVersionUID = 1L;
	private String name;
	private Long id;
	
	public MandalVO(){}
	
	public MandalVO(Long id, String name){
		this.id = id;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
