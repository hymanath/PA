package com.itgrids.partyanalyst.dto;

import java.util.Random;

public class GISVisualizationBasicVO implements java.io.Serializable{

	private Long id;
	private String name;
	private String status;
	private Long count=0L;
	private Long totalCount=0L;
	private String perc="0.0";
	private String colorCode;
	
	public int hashCode()
	{
	    int result = 1;
	    return result;
	}
	
	public boolean equals(Object obj){
		if(obj == null)
			 return false;
		if(obj == this)
			return true;
		if(getClass() != obj.getClass())
			return false;
		GISVisualizationBasicVO vo = (GISVisualizationBasicVO) obj;
		return this.getName() == vo.getName();
	}
	
	
	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public String getPerc() {
		return perc;
	}
	public void setPerc(String perc) {
		this.perc = perc;
	}
	
	
}
