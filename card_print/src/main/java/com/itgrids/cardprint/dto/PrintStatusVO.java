package com.itgrids.cardprint.dto;

import java.io.Serializable;

public class PrintStatusVO implements Serializable{
	
	private Long id;
	private String name;
	private Long count = 0l;
	
	private String status;
	
	public PrintStatusVO(){}
	
	public PrintStatusVO(Long id , String name){
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
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
