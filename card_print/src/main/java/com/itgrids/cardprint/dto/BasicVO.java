package com.itgrids.cardprint.dto;

import java.util.List;

public class BasicVO {
	
	private Long id;
	private String name;
	
	private List<BasicVO> subList ;
	
	public BasicVO(){}
	public BasicVO(Long id , String name){
		this.id = id;
		this.name = name;
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
	
	public List<BasicVO> getSubList() {
		return subList;
	}
	public void setSubList(List<BasicVO> subList) {
		this.subList = subList;
	}
	
}
