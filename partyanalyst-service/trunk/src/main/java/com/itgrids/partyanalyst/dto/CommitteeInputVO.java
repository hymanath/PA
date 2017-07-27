package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CommitteeInputVO implements Serializable{
	
	private List<Long> tehsilIds;
	private List<Long> assemblyConstIds;
	private List<Long> parliamentConstIds;
	private List<Long> districtIds;
	private List<Long> stateIds;
	
	private String     state;
	private Long  stateId;
	private List<Long> basicCommitteeIds;
	private List<Long> tdpCommitteeLevelIds;
	private List<Long> partyMeetingTypeIds;
	private Date startDate;
	private Date endDate;
	private Date date;
	private String status;
	
	private Long basicCommitteeId;
	private String groupingLocation;
	private List<Long> enrollmentYearList;
	
	private List<String> statusList; 
	private StringBuilder queryString;
	private StringBuilder committeesQueryString;
	
	private String committeType;
	
	public List<Long> getTehsilIds() {
		return tehsilIds;
	}
	public void setTehsilIds(List<Long> tehsilIds) {
		this.tehsilIds = tehsilIds;
	}
	public List<Long> getAssemblyConstIds() {
		return assemblyConstIds;
	}
	public void setAssemblyConstIds(List<Long> assemblyConstIds) {
		this.assemblyConstIds = assemblyConstIds;
	}
	public List<Long> getDistrictIds() {
		return districtIds;
	}
	public void setDistrictIds(List<Long> districtIds) {
		this.districtIds = districtIds;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<Long> getBasicCommitteeIds() {
		return basicCommitteeIds;
	}
	public void setBasicCommitteeIds(List<Long> basicCommitteeIds) {
		this.basicCommitteeIds = basicCommitteeIds;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Long> getStateIds() {
		return stateIds;
	}
	public void setStateIds(List<Long> stateIds) {
		this.stateIds = stateIds;
	}
	public List<Long> getTdpCommitteeLevelIds() {
		return tdpCommitteeLevelIds;
	}
	public void setTdpCommitteeLevelIds(List<Long> tdpCommitteeLevelIds) {
		this.tdpCommitteeLevelIds = tdpCommitteeLevelIds;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public Long getBasicCommitteeId() {
		return basicCommitteeId;
	}
	public void setBasicCommitteeId(Long basicCommitteeId) {
		this.basicCommitteeId = basicCommitteeId;
	}
	public String getGroupingLocation() {
		return groupingLocation;
	}
	public void setGroupingLocation(String groupingLocation) {
		this.groupingLocation = groupingLocation;
	}
	public List<Long> getParliamentConstIds() {
		return parliamentConstIds;
	}
	public void setParliamentConstIds(List<Long> parliamentConstIds) {
		this.parliamentConstIds = parliamentConstIds;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<String> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<String> statusList) {
		this.statusList = statusList;
	}
	public StringBuilder getQueryString() {
		return queryString;
	}
	public void setQueryString(StringBuilder queryString) {
		this.queryString = queryString;
	}
	public StringBuilder getCommitteesQueryString() {
		return committeesQueryString;
	}
	public void setCommitteesQueryString(StringBuilder committeesQueryString) {
		this.committeesQueryString = committeesQueryString;
	}
	public List<Long> getPartyMeetingTypeIds() {
		return partyMeetingTypeIds;
	}
	public void setPartyMeetingTypeIds(List<Long> partyMeetingTypeIds) {
		this.partyMeetingTypeIds = partyMeetingTypeIds;
	}
	public List<Long> getEnrollmentYearList() {
		return enrollmentYearList;
	}
	public void setEnrollmentYearList(List<Long> enrollmentYearList) {
		this.enrollmentYearList = enrollmentYearList;
	}
	public String getCommitteType() {
		return committeType;
	}
	public void setCommitteType(String committeType) {
		this.committeType = committeType;
	}
}
