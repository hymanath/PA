package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;


public class CommentsDashBoardVO {

	private Long id;
	private String name;
	private String dateStr;
	private String district;
	private String constituency;
	private List<CommentsDashBoardVO> subList = new ArrayList<CommentsDashBoardVO>();
	
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
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
	public List<CommentsDashBoardVO> getSubList() {
		return subList;
	}
	public void setSubList(List<CommentsDashBoardVO> subList) {
		this.subList = subList;
	}
}
