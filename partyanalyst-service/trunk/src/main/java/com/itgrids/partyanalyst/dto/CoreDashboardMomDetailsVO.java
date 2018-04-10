package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CoreDashboardMomDetailsVO {
	private Long meetingId;
	private String momPoints;
	private Long momPointsId;
	private String meetingName;
	private String date;
	private Long userId;
	private Long stateId;
	private Long meetingLevelId;
	private String meetingLevel;
	private String searchType;
	public Date startDate;
	public Date endDate;
	public List<Long> partyMetingLevelIdsList = new ArrayList<Long>(0);
	public Long locationLevelId;
	public List<Long> locationValuesList = new ArrayList<Long>(0);
	public Long sourceTypeId;
	private String sourceName;
	public String momType; // isactionable or not or general
	public List<CoreDashboardMomDetailsVO> minutesList = new ArrayList<CoreDashboardMomDetailsVO>(0);
	public List<KeyValueVO> filesList = new ArrayList<KeyValueVO>(0);
	public List<KeyValueVO> filesList1 = new ArrayList<KeyValueVO>(0);
	public AddressVO addressVO;
	
	public Long searchLevelId;
	public Long searchLevelValue;
	public String searchTypeStr;
	public List<Long> partyMetingTypeIdsList = new ArrayList<Long>(0);
	private Long totalCount;
	private Long partyCount;
	private Long govtCount;
	private Long generalCount;
	
	public Long getPartyCount() {
		return partyCount;
	}
	public void setPartyCount(Long partyCount) {
		this.partyCount = partyCount;
	}
	public Long getGovtCount() {
		return govtCount;
	}
	public void setGovtCount(Long govtCount) {
		this.govtCount = govtCount;
	}
	public Long getGeneralCount() {
		return generalCount;
	}
	public void setGeneralCount(Long generalCount) {
		this.generalCount = generalCount;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getMeetingId() {
		return meetingId;
	}
	public void setMeetingId(Long meetingId) {
		this.meetingId = meetingId;
	}
	public String getMomPoints() {
		return momPoints;
	}
	public void setMomPoints(String momPoints) {
		this.momPoints = momPoints;
	}
	public Long getMomPointsId() {
		return momPointsId;
	}
	public void setMomPointsId(Long momPointsId) {
		this.momPointsId = momPointsId;
	}
	public String getMeetingName() {
		return meetingName;
	}
	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public Long getMeetingLevelId() {
		return meetingLevelId;
	}
	public void setMeetingLevelId(Long meetingLevelId) {
		this.meetingLevelId = meetingLevelId;
	}
	public String getMeetingLevel() {
		return meetingLevel;
	}
	public void setMeetingLevel(String meetingLevel) {
		this.meetingLevel = meetingLevel;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
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
	public List<Long> getPartyMetingLevelIdsList() {
		return partyMetingLevelIdsList;
	}
	public void setPartyMetingLevelIdsList(List<Long> partyMetingLevelIdsList) {
		this.partyMetingLevelIdsList = partyMetingLevelIdsList;
	}
	public Long getSearchLevelId() {
		return searchLevelId;
	}
	public void setSearchLevelId(Long searchLevelId) {
		this.searchLevelId = searchLevelId;
	}
	public Long getSearchLevelValue() {
		return searchLevelValue;
	}
	public void setSearchLevelValue(Long searchLevelValue) {
		this.searchLevelValue = searchLevelValue;
	}
	public String getSearchTypeStr() {
		return searchTypeStr;
	}
	public void setSearchTypeStr(String searchTypeStr) {
		this.searchTypeStr = searchTypeStr;
	}
	public List<Long> getPartyMetingTypeIdsList() {
		return partyMetingTypeIdsList;
	}
	public void setPartyMetingTypeIdsList(List<Long> partyMetingTypeIdsList) {
		this.partyMetingTypeIdsList = partyMetingTypeIdsList;
	}
	public Long getLocationLevelId() {
		return locationLevelId;
	}
	public void setLocationLevelId(Long locationLevelId) {
		this.locationLevelId = locationLevelId;
	}
	public List<Long> getLocationValuesList() {
		return locationValuesList;
	}
	public void setLocationValuesList(List<Long> locationValuesList) {
		this.locationValuesList = locationValuesList;
	}
	public Long getSourceTypeId() {
		return sourceTypeId;
	}
	public void setSourceTypeId(Long sourceTypeId) {
		this.sourceTypeId = sourceTypeId;
	}
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	public String getMomType() {
		return momType;
	}
	public void setMomType(String momType) {
		this.momType = momType;
	}
	public List<CoreDashboardMomDetailsVO> getMinutesList() {
		return minutesList;
	}
	public void setMinutesList(List<CoreDashboardMomDetailsVO> minutesList) {
		this.minutesList = minutesList;
	}
	public List<KeyValueVO> getFilesList() {
		return filesList;
	}
	public void setFilesList(List<KeyValueVO> filesList) {
		this.filesList = filesList;
	}
	public AddressVO getAddressVO() {
		return addressVO;
	}
	public void setAddressVO(AddressVO addressVO) {
		this.addressVO = addressVO;
	}
	public List<KeyValueVO> getFilesList1() {
		return filesList1;
	}
	public void setFilesList1(List<KeyValueVO> filesList1) {
		this.filesList1 = filesList1;
	}
	
}
