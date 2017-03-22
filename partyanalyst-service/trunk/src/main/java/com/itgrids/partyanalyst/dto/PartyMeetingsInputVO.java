package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PartyMeetingsInputVO implements Serializable{
	
	private Long        partyMeetingMainTypeId;
	private List<Long>  partyMeetingTypeIds;
	private List<Long>        partyMeetingIds;
	private Long        stateId;
  	private Date        startDate;
	private Date        endDate;
	private Long 		distId;
	private Long partyMeetingId;
	private String category = "";
	private String status;
	private Long partyMeetingGroupId;
	private Long sessionId;
	private List<Long> categoryIdList = new ArrayList<Long>();
	
	
	public Long getSessionId() {
		return sessionId;
	}
	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}
	public Long getPartyMeetingMainTypeId() {
		return partyMeetingMainTypeId;
	}
	public void setPartyMeetingMainTypeId(Long partyMeetingMainTypeId) {
		this.partyMeetingMainTypeId = partyMeetingMainTypeId;
	}
	public List<Long> getPartyMeetingTypeIds() {
		return partyMeetingTypeIds;
	}
	public void setPartyMeetingTypeIds(List<Long> partyMeetingTypeIds) {
		this.partyMeetingTypeIds = partyMeetingTypeIds;
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
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public Long getDistId() {
		return distId;
	}
	public void setDistId(Long distId) {
		this.distId = distId;
	}
	public List<Long> getPartyMeetingIds() {
		return partyMeetingIds;
	}
	public void setPartyMeetingIds(List<Long> partyMeetingIds) {
		this.partyMeetingIds = partyMeetingIds;
	}
	public Long getPartyMeetingId() {
		return partyMeetingId;
	}
	public void setPartyMeetingId(Long partyMeetingId) {
		this.partyMeetingId = partyMeetingId;
	}
	public List<Long> getCategoryIdList() {
		return categoryIdList;
	}
	public void setCategoryIdList(List<Long> categoryIdList) {
		this.categoryIdList = categoryIdList;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getPartyMeetingGroupId() {
		return partyMeetingGroupId;
	}
	public void setPartyMeetingGroupId(Long partyMeetingGroupId) {
		this.partyMeetingGroupId = partyMeetingGroupId;
	}
}
