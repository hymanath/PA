package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SearchAttributeVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Date startDate;
	private Date endDate;
	private String searchType;
	private List<Long> locationIdsList = new ArrayList<Long>();
	private List<Long> locationTypeIdsList = new ArrayList<Long>();
	private Long locationId;
	private Long locationValue;
	private List<Long> attributesIdsList = new ArrayList<Long>(0);
	private Long typeId;
	private String conditionType;
	private Long levelId;
	private Long scopeId;
	private Long scopeValue;
	private String attributeType;
	private List<Long> questionnaireIdsList = new ArrayList<Long>(0);
	private Long constituencyId;
	
	
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public List<Long> getQuestionnaireIdsList() {
		return questionnaireIdsList;
	}
	public void setQuestionnaireIdsList(List<Long> questionnaireIdsList) {
		this.questionnaireIdsList = questionnaireIdsList;
	}
	public Long getScopeId() {
		return scopeId;
	}
	public void setScopeId(Long scopeId) {
		this.scopeId = scopeId;
	}
	public Long getScopeValue() {
		return scopeValue;
	}
	public void setScopeValue(Long scopeValue) {
		this.scopeValue = scopeValue;
	}
	public Long getLevelId() {
		return levelId;
	}
	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	public String getAttributeType() {
		return attributeType;
	}
	public void setAttributeType(String attributeType) {
		this.attributeType = attributeType;
	}
	public String getConditionType() {
		return conditionType;
	}
	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public List<Long> getLocationIdsList() {
		return locationIdsList;
	}
	public void setLocationIdsList(List<Long> locationIdsList) {
		this.locationIdsList = locationIdsList;
	}
	public List<Long> getLocationTypeIdsList() {
		return locationTypeIdsList;
	}
	public void setLocationTypeIdsList(List<Long> locationTypeIdsList) {
		this.locationTypeIdsList = locationTypeIdsList;
	}
	public List<Long> getAttributesIdsList() {
		return attributesIdsList;
	}
	public void setAttributesIdsList(List<Long> attributesIdsList) {
		this.attributesIdsList = attributesIdsList;
	}
}
