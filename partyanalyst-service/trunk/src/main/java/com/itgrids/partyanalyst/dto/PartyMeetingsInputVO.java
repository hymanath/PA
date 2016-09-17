package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PartyMeetingsInputVO implements Serializable{
	
	private Long        partyMeetingMainTypeId;
	private List<Long>  partyMeetingTypeIds;
	private Long        stateId;
  	private Date        startDate;
	private Date        endDate;
	private Long 		distId;
	
	
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
}
