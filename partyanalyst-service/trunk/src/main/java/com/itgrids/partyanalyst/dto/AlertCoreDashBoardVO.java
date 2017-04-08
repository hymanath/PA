package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AlertCoreDashBoardVO implements Serializable{

	private Long id;
	private String name;
	private String title;
	private String desc;
	
	private Long totalCount=0l;
	private Long count=0l;
	
	private Double countPerc=0.0;
	private Long categoryId;
	private String category;
	
	private String createdDate;
	private String updatedDate;
	private Long statusId;
	private String status;
	private Long interval;
	private String alertLevel;
	private String location;
	private String source;
	private String severtyColor;
	private Long subTaskCount = 0L;
	
	
	private List<AlertCoreDashBoardVO> subList = new ArrayList<AlertCoreDashBoardVO>(0);
	private List<AlertCoreDashBoardVO> subList1 = new ArrayList<AlertCoreDashBoardVO>(0);
	private Set<Long> setList = new HashSet<Long>(0);

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public List<AlertCoreDashBoardVO> getSubList() {
		return subList;
	}

	public void setSubList(List<AlertCoreDashBoardVO> subList) {
		this.subList = subList;
	}

	public Double getCountPerc() {
		return countPerc;
	}

	public void setCountPerc(Double countPerc) {
		this.countPerc = countPerc;
	}

	public Set<Long> getSetList() {
		return setList;
	}

	public void setSetList(Set<Long> setList) {
		this.setList = setList;
	}

	public List<AlertCoreDashBoardVO> getSubList1() {
		return subList1;
	}

	public void setSubList1(List<AlertCoreDashBoardVO> subList1) {
		this.subList1 = subList1;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getInterval() {
		return interval;
	}

	public void setInterval(Long interval) {
		this.interval = interval;
	}

	public String getAlertLevel() {
		return alertLevel;
	}

	public void setAlertLevel(String alertLevel) {
		this.alertLevel = alertLevel;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSevertyColor() {
		return severtyColor;
	}

	public void setSevertyColor(String severtyColor) {
		this.severtyColor = severtyColor;
	}

	public Long getSubTaskCount() {
		return subTaskCount;
	}

	public void setSubTaskCount(Long subTaskCount) {
		this.subTaskCount = subTaskCount;
	}
	
	
}
