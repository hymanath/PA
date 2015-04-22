package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserEventDetailsVO implements Serializable{
	
	private Long id;
	private String name;
	private String pwd;
	private List<UserEventDetailsVO> subList = new ArrayList<UserEventDetailsVO>();
	
	
	public List<UserEventDetailsVO> getSubList() {
		return subList;
	}
	public void setSubList(List<UserEventDetailsVO> subList) {
		this.subList = subList;
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
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	

}
