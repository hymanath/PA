package com.itgrids.partyanalyst.dto;

import java.util.List;

public class PartyMeetingMOMDtlsVO {

	
	private Long meetingId;
	private String meetingName;
	private Long meetingLevelId;
	private String meetingLevel;
	private Long meetingTypeId;
	private String meetingType;
	private Long locationId;
	private String location;
	private String conductedDate;
	private String isConducted;
	private String startDate;
	private String endDate;
	private Long totalMeetingCount = 0l;
	private Long momPointsCount = 0l;
	private Long documentCount = 0l;
	private String status;
	
	private PartyMeetingMOMDtlsVO overviewDtls;
	private List<PartyMeetingMOMDtlsVO> subList;
	
	
	public Long getMeetingId() {
		return meetingId;
	}
	public void setMeetingId(Long meetingId) {
		this.meetingId = meetingId;
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Long getTotalMeetingCount() {
		return totalMeetingCount;
	}
	public void setTotalMeetingCount(Long totalMeetingCount) {
		this.totalMeetingCount = totalMeetingCount;
	}
	public Long getMomPointsCount() {
		return momPointsCount;
	}
	public void setMomPointsCount(Long momPointsCount) {
		this.momPointsCount = momPointsCount;
	}
	public Long getDocumentCount() {
		return documentCount;
	}
	public void setDocumentCount(Long documentCount) {
		this.documentCount = documentCount;
	}
	public PartyMeetingMOMDtlsVO getOverviewDtls() {
		return overviewDtls;
	}
	public void setOverviewDtls(PartyMeetingMOMDtlsVO overviewDtls) {
		this.overviewDtls = overviewDtls;
	}
	public List<PartyMeetingMOMDtlsVO> getSubList() {
		return subList;
	}
	public void setSubList(List<PartyMeetingMOMDtlsVO> subList) {
		this.subList = subList;
	}
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public String getMeetingName() {
		return meetingName;
	}
	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}
	public String getIsConducted() {
		return isConducted;
	}
	public void setIsConducted(String isConducted) {
		this.isConducted = isConducted;
	}
	public Long getMeetingTypeId() {
		return meetingTypeId;
	}
	public void setMeetingTypeId(Long meetingTypeId) {
		this.meetingTypeId = meetingTypeId;
	}
	public String getMeetingType() {
		return meetingType;
	}
	public void setMeetingType(String meetingType) {
		this.meetingType = meetingType;
	}
	public String getConductedDate() {
		return conductedDate;
	}
	public void setConductedDate(String conductedDate) {
		this.conductedDate = conductedDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
