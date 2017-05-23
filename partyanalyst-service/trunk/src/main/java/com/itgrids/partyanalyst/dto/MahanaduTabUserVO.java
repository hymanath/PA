package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class MahanaduTabUserVO implements java.io.Serializable{

	private Long id;
	private String name;
	private Long count;
	private String date;
	private List<MahanaduTabUserVO> subList=new ArrayList<MahanaduTabUserVO>(0);
	
	public List<MahanaduTabUserVO> getSubList() {
		return subList;
	}
	public void setSubList(List<MahanaduTabUserVO> subList) {
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
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
