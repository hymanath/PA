package com.itgrids.partyanalyst.dto;

import java.util.List;

public class SMSSearchCriteriaVO {
	
	private boolean isAgeSelected;
	private boolean isFamilySelected;
	private boolean isCasteSelected;
	private boolean isNameSelected;
	private boolean isGenderSelected;
	
	private int startAge;
	private int endAge;
	private String houseNo;
	private String casteIds;
	private String gender;
	private String name;
	
	private String locationType;
	private Long locationValue;
	private Long userId;
	private List<Long> selectedCastes;	
	private Long publicationDateId;
	private Long mobileNumber;
	
	private Integer startIndex;
	private Integer maxRecords;
	private String order;
	private String columnName;
	private String voterIdCardNo;
	private Long totalCount;
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public String getVoterIdCardNo() {
		return voterIdCardNo;
	}
	public void setVoterIdCardNo(String voterIdCardNo) {
		this.voterIdCardNo = voterIdCardNo;
	}
	private List<SMSSearchCriteriaVO> resultVotersList;
	private int totalResultsCount;
	
	
	public int getTotalResultsCount() {
		return totalResultsCount;
	}
	public void setTotalResultsCount(int totalResultsCount) {
		this.totalResultsCount = totalResultsCount;
	}
	public List<SMSSearchCriteriaVO> getResultVotersList() {
		return resultVotersList;
	}
	public void setResultVotersList(List<SMSSearchCriteriaVO> resultVotersList) {
		this.resultVotersList = resultVotersList;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public Integer getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	public Integer getMaxRecords() {
		return maxRecords;
	}
	public void setMaxRecords(Integer maxRecords) {
		this.maxRecords = maxRecords;
	}
	public Long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public Long getPublicationDateId() {
		return publicationDateId;
	}
	public void setPublicationDateId(Long publicationDateId) {
		this.publicationDateId = publicationDateId;
	}
	public List<Long> getSelectedCastes() {
		return selectedCastes;
	}
	public void setSelectedCastes(List<Long> selectedCastes) {
		this.selectedCastes = selectedCastes;
	}
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStartAge() {
		return startAge;
	}
	public void setStartAge(int startAge) {
		this.startAge = startAge;
	}
	public int getEndAge() {
		return endAge;
	}
	public void setEndAge(int endAge) {
		this.endAge = endAge;
	}
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public String getCasteIds() {
		return casteIds;
	}
	public void setCasteIds(String casteIds) {
		this.casteIds = casteIds;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public boolean isAgeSelected() {
		return isAgeSelected;
	}
	public void setAgeSelected(boolean isAgeSelected) {
		this.isAgeSelected = isAgeSelected;
	}
	public boolean isFamilySelected() {
		return isFamilySelected;
	}
	public void setFamilySelected(boolean isFamilySelected) {
		this.isFamilySelected = isFamilySelected;
	}
	public boolean isCasteSelected() {
		return isCasteSelected;
	}
	public void setCasteSelected(boolean isCasteSelected) {
		this.isCasteSelected = isCasteSelected;
	}
	public boolean isNameSelected() {
		return isNameSelected;
	}
	public void setNameSelected(boolean isNameSelected) {
		this.isNameSelected = isNameSelected;
	}
	public boolean isGenderSelected() {
		return isGenderSelected;
	}
	public void setGenderSelected(boolean isGenderSelected) {
		this.isGenderSelected = isGenderSelected;
	}

}
