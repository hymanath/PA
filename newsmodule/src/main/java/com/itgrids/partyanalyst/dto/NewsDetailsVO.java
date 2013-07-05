package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class NewsDetailsVO implements Serializable{
	
	private Long id;
	private Long selectedPartyId;
	private String name;
	private String locationScope;
	private String tempVar;
	private String fromDateStr;
	private String newsType;
	private String toDateStr;
	private String galleryIdsList;
	private String categoryIdsList;
	private String locationIdsList;
	private Integer startIndex;
	private Integer maxIndex;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getSelectedPartyId() {
		return selectedPartyId;
	}
	public void setSelectedPartyId(Long selectedPartyId) {
		this.selectedPartyId = selectedPartyId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocationScope() {
		return locationScope;
	}
	public void setLocationScope(String locationScope) {
		this.locationScope = locationScope;
	}
	public String getTempVar() {
		return tempVar;
	}
	public void setTempVar(String tempVar) {
		this.tempVar = tempVar;
	}
	public String getFromDateStr() {
		return fromDateStr;
	}
	public void setFromDateStr(String fromDateStr) {
		this.fromDateStr = fromDateStr;
	}
	public String getToDateStr() {
		return toDateStr;
	}
	public void setToDateStr(String toDateStr) {
		this.toDateStr = toDateStr;
	}
	
	public String getNewsType() {
		return newsType;
	}
	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}
	public String getGalleryIdsList() {
		return galleryIdsList;
	}
	public void setGalleryIdsList(String galleryIdsList) {
		this.galleryIdsList = galleryIdsList;
	}
	public String getCategoryIdsList() {
		return categoryIdsList;
	}
	public void setCategoryIdsList(String categoryIdsList) {
		this.categoryIdsList = categoryIdsList;
	}
	public String getLocationIdsList() {
		return locationIdsList;
	}
	public void setLocationIdsList(String locationIdsList) {
		this.locationIdsList = locationIdsList;
	}
	public Integer getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	public Integer getMaxIndex() {
		return maxIndex;
	}
	public void setMaxIndex(Integer maxIndex) {
		this.maxIndex = maxIndex;
	}
	
	

}
