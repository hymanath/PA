package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class GrivenceStatusVO {
	
	
	private String grivenceType;
	private String name;
	private Long count;
	private List<GrivenceStatusVO> subList=new ArrayList<GrivenceStatusVO>(0);
	
	public String getGrivenceType() {
		return grivenceType;
	}
	public void setGrivenceType(String grivenceType) {
		this.grivenceType = grivenceType;
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
	public List<GrivenceStatusVO> getSubList() {
		return subList;
	}
	public void setSubList(List<GrivenceStatusVO> subList) {
		this.subList = subList;
	}
	
	
}
