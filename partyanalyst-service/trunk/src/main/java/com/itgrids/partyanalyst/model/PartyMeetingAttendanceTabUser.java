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
@Table(name="party_meeting_attendance_tab_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyMeetingAttendanceTabUser extends BaseModel implements Serializable{

	private static final long serialVersionUID = 4217282654328376441L;
	private Long partyMeetingAttendanceTabUserId;
	private PartyMeeting partyMeeting;
	private AttendanceTabUser attendanceTabUser;
	private User insertedBy;
	private Date insertedTime;
	private String isDeleted;
	private Long partyMeetingId;
	private Long attendanceTabUserId;
	private Long insertedById;

	public PartyMeetingAttendanceTabUser(){}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="party_meeting_attendance_tab_user_id", unique=true, nullable=false)
	public Long getPartyMeetingAttendanceTabUserId() {
		return partyMeetingAttendanceTabUserId;
	}

	public void setPartyMeetingAttendanceTabUserId(
			Long partyMeetingAttendanceTabUserId) {
		this.partyMeetingAttendanceTabUserId = partyMeetingAttendanceTabUserId;
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

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="attendance_tab_user_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AttendanceTabUser getAttendanceTabUser() {
		return attendanceTabUser;
	}

	public void setAttendanceTabUser(AttendanceTabUser attendanceTabUser) {
		this.attendanceTabUser = attendanceTabUser;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="inserted_by",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getInsertedBy() {
		return insertedBy;
	}

	public void setInsertedBy(User insertedBy) {
		this.insertedBy = insertedBy;
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

	@Column(name="attendance_tab_user_id")
	public Long getAttendanceTabUserId() {
		return attendanceTabUserId;
	}

	public void setAttendanceTabUserId(Long attendanceTabUserId) {
		this.attendanceTabUserId = attendanceTabUserId;
	}

	@Column(name="inserted_by")
	public Long getInsertedById() {
		return insertedById;
	}

	public void setInsertedById(Long insertedById) {
		this.insertedById = insertedById;
	}

	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	
}
