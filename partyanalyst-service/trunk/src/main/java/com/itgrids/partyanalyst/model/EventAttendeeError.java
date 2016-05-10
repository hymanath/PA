package com.itgrids.partyanalyst.model;

import java.util.Date;

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
@Table(name = "event_attendee_error")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EventAttendeeError {

	
	private Long eventAttendeeErrorId;
	private Event event;
	private Long eventId;
	private User user;
	private Long insertedBy;
	private String rfid;
	private String imei;
	private Date insertedTime;
	private Date attendedTime;
	private String uniqueKey;
	private String latitude;
	private String longitude;
	private Long tabUserId;
	private Long currentTabUserId;
	private String syncSource;
	private String memberShipId;
	private String errorDescription;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "event_attendee_error_id", unique = true, nullable = false)
	public Long getEventAttendeeErrorId() {
		return eventAttendeeErrorId;
	}
	public void setEventAttendeeErrorId(Long eventAttendeeErrorId) {
		this.eventAttendeeErrorId = eventAttendeeErrorId;
	}
	@Column(name = "member_ship_id")
	public String getMemberShipId() {
		return memberShipId;
	}
	public void setMemberShipId(String memberShipId) {
		this.memberShipId = memberShipId;
	}
	@Column(name = "error_description")
	public String getErrorDescription() {
		return errorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "event_id",insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	
	@Column(name="event_id")
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "inserted_by",insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Column(name="inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	
	@Column(name="rfid")
	public String getRfid() {
		return rfid;
	}
	public void setRfid(String rfid) {
		this.rfid = rfid;
	}
	@Column(name="imei")
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@Column(name="attended_time")
	public Date getAttendedTime() {
		return attendedTime;
	} 
	public void setAttendedTime(Date attendedTime) {
		this.attendedTime = attendedTime;
	}
   @Column(name="unique_key")
	public String getUniqueKey() {
		return uniqueKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	
	@Column(name="latitude")
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	@Column(name="longitude")
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	@Column(name="tab_user_id")
	public Long getTabUserId() {
		return tabUserId;
	}
	public void setTabUserId(Long tabUserId) {
		this.tabUserId = tabUserId;
	}
	@Column(name="current_tab_user_id")
	public Long getCurrentTabUserId() {
		return currentTabUserId;
	}
	public void setCurrentTabUserId(Long currentTabUserId) {
		this.currentTabUserId = currentTabUserId;
	}
	
	@Column(name="sync_source")
	public String getSyncSource() {
		return syncSource;
	}
	public void setSyncSource(String syncSource) {
		this.syncSource = syncSource;
	}
	
	
	
	
}
