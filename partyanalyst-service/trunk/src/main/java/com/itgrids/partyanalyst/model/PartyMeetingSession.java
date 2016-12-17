package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

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

/**
 * @author Administrator
 *
 */
@Entity
@Table(name = "party_meeting_session")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyMeetingSession extends BaseModel implements Serializable {
	
	private Long partyMeetingSessionId;
	private Long partyMeetingId;
//	private String sessionName;
//	private String description;
	private Time startTime;
	private Time endTime;
	private Time lateTime;
	private String isDeleted;
	private Long insertedBy;
	private Long updatedBy;
	private Date insertedTime;
	private Date updatedTime;
	private Long orderNo;
	
	private Long sessionTypeId;
	
	private SessionType sessionType;
	private PartyMeeting partyMeeting;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "party_meeting_session_id", unique = true, nullable = false)
	public Long getPartyMeetingSessionId() {
		return partyMeetingSessionId;
	}

	public void setPartyMeetingSessionId(Long partyMeetingSessionId) {
		this.partyMeetingSessionId = partyMeetingSessionId;
	}
 
	@Column(name = "party_meeting_id")
	public Long getPartyMeetingId() {
		return partyMeetingId;
	}

	public void setPartyMeetingId(Long partyMeetingId) {
		this.partyMeetingId = partyMeetingId;
	}

	/*@Column(name = "session_name")
	public String getSessionName() {
		return sessionName;
	}

	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}*/

	/*@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}*/

	@Column(name = "start_time")
	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	@Column(name = "end_time")
	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	@Column(name = "late_time")
	public Time getLateTime() {
		return lateTime;
	}

	public void setLateTime(Time lateTime) {
		this.lateTime = lateTime;
	}

	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Column(name = "inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}

	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}

	@Column(name = "updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}

	@Column(name = "updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "party_meeting_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public PartyMeeting getPartyMeeting() {
		return partyMeeting;
	}

	public void setPartyMeeting(PartyMeeting partyMeeting) {
		this.partyMeeting = partyMeeting;
	}

	@Column(name = "order_no")
	public Long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}

	@Column(name="session_type_id")
	public Long getSessionTypeId() {
		return sessionTypeId;
	}

	public void setSessionTypeId(Long sessionTypeId) {
		this.sessionTypeId = sessionTypeId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="session_type_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SessionType getSessionType() {
		return sessionType;
	}

	public void setSessionType(SessionType sessionType) {
		this.sessionType = sessionType;
	}
}
