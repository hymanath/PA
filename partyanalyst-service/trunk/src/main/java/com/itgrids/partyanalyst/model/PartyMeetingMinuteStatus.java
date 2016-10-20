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
@Table(name="party_meeting_minute_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class PartyMeetingMinuteStatus extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = -5410225613144924859L;
	
	private Long partyMeetingMinuteStatusId;
	private String status;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="party_meeting_minute_status_id", unique=true, nullable=false)
	public Long getPartyMeetingMinuteStatusId() {
		return partyMeetingMinuteStatusId;
	}
	public void setPartyMeetingMinuteStatusId(Long partyMeetingMinuteStatusId) {
		this.partyMeetingMinuteStatusId = partyMeetingMinuteStatusId;
	}
	
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}	

}
