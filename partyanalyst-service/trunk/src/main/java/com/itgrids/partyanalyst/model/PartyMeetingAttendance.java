package com.itgrids.partyanalyst.model;

import java.io.Serializable;
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

@Entity
@Table(name="party_meeting_attendance")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyMeetingAttendance extends BaseModel implements Serializable{

	private static final long serialVersionUID = -4847352204126095105L;
	
	private Long partyMeetingAttendanceId;
	private Attendance attendance;
	private PartyMeeting partyMeeting;
	private Date insertedTime;
	private Long partyMeetingId;
	private Long partyMeetingSessionId;
	//private Long attendanceId;
	
	private PartyMeetingSession partyMeetingSession;
	public PartyMeetingAttendance(){}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="party_meeting_attendance_id", unique=true, nullable=false)
	public Long getPartyMeetingAttendanceId() {
		return partyMeetingAttendanceId;
	}

	public void setPartyMeetingAttendanceId(Long partyMeetingAttendanceId) {
		this.partyMeetingAttendanceId = partyMeetingAttendanceId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="attendance_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Attendance getAttendance() {
		return attendance;
	}

	public void setAttendance(Attendance attendance) {
		this.attendance = attendance;
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

	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}

	@Column(name="party_meeting_id")
	public Long getPartyMeetingId() {
		return partyMeetingId;
	}

	public void setPartyMeetingId(Long partyMeetingId) {
		this.partyMeetingId = partyMeetingId;
	}

	
	@Column(name="party_meeting_session_id")
	public Long getPartyMeetingSessionId() {
		return partyMeetingSessionId;
	}

	public void setPartyMeetingSessionId(Long partyMeetingSessionId) {
		this.partyMeetingSessionId = partyMeetingSessionId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="party_meeting_session_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PartyMeetingSession getPartyMeetingSession() {
		return partyMeetingSession;
	}

	public void setPartyMeetingSession(PartyMeetingSession partyMeetingSession) {
		this.partyMeetingSession = partyMeetingSession;
	}

	/*@Column(name="attendance_id")
	public Long getAttendanceId() {
		return attendanceId;
	}
	public void setAttendanceId(Long attendanceId) {
		this.attendanceId = attendanceId;
	}*/
}
