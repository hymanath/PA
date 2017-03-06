package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "party_meeting_groups_mapping_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyMeetingGroupsMappingInfo extends BaseModel implements Serializable{
	
	private Long partyMeetingGroupsMappingInfoId;
	private Long partyMeetingGroupId;
	private Long partyMeetingId;
	private String isDeleted;
	
	private PartyMeetingGroup partyMeetingGroup;
	private PartyMeeting partyMeeting;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "party_meeting_groups_mapping_info_id", unique = true, nullable = false)
	public Long getPartyMeetingGroupsMappingInfoId() {
		return partyMeetingGroupsMappingInfoId;
	}
	public void setPartyMeetingGroupsMappingInfoId(
			Long partyMeetingGroupsMappingInfoId) {
		this.partyMeetingGroupsMappingInfoId = partyMeetingGroupsMappingInfoId;
	}
	@Column(name = "party_meeting_group_id")
	public Long getPartyMeetingGroupId() {
		return partyMeetingGroupId;
	}
	public void setPartyMeetingGroupId(Long partyMeetingGroupId) {
		this.partyMeetingGroupId = partyMeetingGroupId;
	}
	@Column(name = "party_meeting_id")
	public Long getPartyMeetingId() {
		return partyMeetingId;
	}
	public void setPartyMeetingId(Long partyMeetingId) {
		this.partyMeetingId = partyMeetingId;
	}
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="party_meeting_group_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PartyMeetingGroup getPartyMeetingGroup() {
		return partyMeetingGroup;
	}
	public void setPartyMeetingGroup(PartyMeetingGroup partyMeetingGroup) {
		this.partyMeetingGroup = partyMeetingGroup;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="party_meeting_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PartyMeeting getPartyMeeting() {
		return partyMeeting;
	}
	public void setPartyMeeting(PartyMeeting partyMeeting) {
		this.partyMeeting = partyMeeting;
	}
	
}
