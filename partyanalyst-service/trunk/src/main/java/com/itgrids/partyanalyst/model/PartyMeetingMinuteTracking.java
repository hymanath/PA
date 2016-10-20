package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

public class PartyMeetingMinuteTracking extends BaseModel implements Serializable {
	
	private Long partyMeetingMiniuteTrackingId;
	private Long partyMeetingMinuteId;
	private Long partyMeetingMinuteStatusId;
	
	private PartyMeetingMinute partyMeetingMinute;
	private PartyMeetingMinuteStatus partyMeetingMinuteStatus;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="party_meeting_miniute_tracking_id", unique=true, nullable=false)
	public Long getPartyMeetingMiniuteTrackingId() {
		return partyMeetingMiniuteTrackingId;
	}
	public void setPartyMeetingMiniuteTrackingId(Long partyMeetingMiniuteTrackingId) {
		this.partyMeetingMiniuteTrackingId = partyMeetingMiniuteTrackingId;
	}
	
	@Column(name="party_meeting_minute_id")
	public Long getPartyMeetingMinuteId() {
		return partyMeetingMinuteId;
	}
	public void setPartyMeetingMinuteId(Long partyMeetingMinuteId) {
		this.partyMeetingMinuteId = partyMeetingMinuteId;
	}
	
	@Column(name="party_meeting_minute_status_id")
	public Long getPartyMeetingMinuteStatusId() {
		return partyMeetingMinuteStatusId;
	}
	public void setPartyMeetingMinuteStatusId(Long partyMeetingMinuteStatusId) {
		this.partyMeetingMinuteStatusId = partyMeetingMinuteStatusId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="party_meeting_minute_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PartyMeetingMinute getPartyMeetingMinute() {
		return partyMeetingMinute;
	}
	public void setPartyMeetingMinute(PartyMeetingMinute partyMeetingMinute) {
		this.partyMeetingMinute = partyMeetingMinute;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="party_meeting_minute_status_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PartyMeetingMinuteStatus getPartyMeetingMinuteStatus() {
		return partyMeetingMinuteStatus;
	}
	public void setPartyMeetingMinuteStatus(
			PartyMeetingMinuteStatus partyMeetingMinuteStatus) {
		this.partyMeetingMinuteStatus = partyMeetingMinuteStatus;
	}
	
	
}
