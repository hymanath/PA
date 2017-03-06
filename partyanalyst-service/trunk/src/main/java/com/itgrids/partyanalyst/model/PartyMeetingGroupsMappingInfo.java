package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "party_meeting_groups_mapping_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyMeetingGroupsMappingInfo extends BaseModel implements Serializable{
	
	private Long partyMeetingGroupsMappingInfoId;
	private String partyMeetingGroupId;
	private String partyMeetingId;
	private String isDeleted;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "activity_attribute_id", unique = true, nullable = false)
	public Long getPartyMeetingGroupsMappingInfoId() {
		return partyMeetingGroupsMappingInfoId;
	}
	public void setPartyMeetingGroupsMappingInfoId(
			Long partyMeetingGroupsMappingInfoId) {
		this.partyMeetingGroupsMappingInfoId = partyMeetingGroupsMappingInfoId;
	}
	public String getPartyMeetingGroupId() {
		return partyMeetingGroupId;
	}
	public void setPartyMeetingGroupId(String partyMeetingGroupId) {
		this.partyMeetingGroupId = partyMeetingGroupId;
	}
	public String getPartyMeetingId() {
		return partyMeetingId;
	}
	public void setPartyMeetingId(String partyMeetingId) {
		this.partyMeetingId = partyMeetingId;
	}
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
}
