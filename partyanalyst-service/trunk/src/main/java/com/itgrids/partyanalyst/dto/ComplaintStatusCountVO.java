package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class ComplaintStatusCountVO {

	private Long count = 0l;
	private String status;
	private String color;
	private List<ComplaintStatusCountVO> subList = new ArrayList<ComplaintStatusCountVO>();

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

	public List<ComplaintStatusCountVO> getSubList() {
		return subList;
	}

	public void setSubList(List<ComplaintStatusCountVO> subList) {
		this.subList = subList;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	
	
	
	
	
	
	
}
