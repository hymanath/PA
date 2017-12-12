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
@Table(name="party_meeting_document")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyMeetingDocument extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1610878406870737696L;
	
	private Long partyMeetingDocumentId;
	private PartyMeeting partyMeeting;
	private String path;
	private String documentType;
	private String documentFormat;
	private User uploadedBy;
	private User updatedBy;
	private Date uploadedTime; 
	private Long partyMeetingId;
	private Long uploadedById;
	private Long updatedById;
	private String isDeleted;
	private String documentName;
	private Long partyMeetingSessionId;
	private Long addressId;
	private Date updatedTime;
	private Long attendanceTabUserId ;
	
	private Long itdpAppUserId;
	private Long partyMeetingMinuteId;
	
	
	private PartyMeetingMinute partyMeetingMinute;
	private PartyMeetingSession partyMeetingSession;
	private UserAddress userAddres;  

	public PartyMeetingDocument(){}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="party_meeting_document_id", unique=true, nullable=false)
	public Long getPartyMeetingDocumentId() {
		return partyMeetingDocumentId;
	}

	public void setPartyMeetingDocumentId(Long partyMeetingDocumentId) {
		this.partyMeetingDocumentId = partyMeetingDocumentId;
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

	@Column(name="path")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column(name="document_type")
	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="uploaded_by",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUploadedBy() {
		return uploadedBy;
	}

	public void setUploadedBy(User uploadedBy) {
		this.uploadedBy = uploadedBy;
	}

	@Column(name="uploaded_time")
	public Date getUploadedTime() {
		return uploadedTime;
	}

	public void setUploadedTime(Date uploadedTime) {
		this.uploadedTime = uploadedTime;
	}

	@Column(name="party_meeting_id")
	public Long getPartyMeetingId() {
		return partyMeetingId;
	}

	public void setPartyMeetingId(Long partyMeetingId) {
		this.partyMeetingId = partyMeetingId;
	}

	@Column(name="uploaded_by")
	public Long getUploadedById() {
		return uploadedById;
	}

	public void setUploadedById(Long uploadedById) {
		this.uploadedById = uploadedById;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="updated_by",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Column(name="updated_by")
	public Long getUpdatedById() {
		return updatedById;
	}

	public void setUpdatedById(Long updatedById) {
		this.updatedById = updatedById;
	}

	@Column(name="document_format")
	public String getDocumentFormat() {
		return documentFormat;
	}

	public void setDocumentFormat(String documentFormat) {
		this.documentFormat = documentFormat;
	}
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	
	@Column(name="document_name")
	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	@Column(name="party_meeting_session_id")
	public Long getPartyMeetingSessionId() {
		return partyMeetingSessionId;
	}

	public void setPartyMeetingSessionId(Long partyMeetingSessionId) {
		this.partyMeetingSessionId = partyMeetingSessionId;
	}
	@Column(name="address_id")
	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
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
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="address_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserAddress getUserAddres() {
		return userAddres;
	}

	public void setUserAddres(UserAddress userAddres) {
		this.userAddres = userAddres;
	}

	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	@Column(name="tab_user_id")
	public Long getAttendanceTabUserId() {
		return attendanceTabUserId;
	}

	public void setAttendanceTabUserId(Long attendanceTabUserId) {
		this.attendanceTabUserId = attendanceTabUserId;
	}
	
	@Column(name="itdp_app_user_id")
	public Long getItdpAppUserId() {
		return itdpAppUserId;
	}

	public void setItdpAppUserId(Long itdpAppUserId) {
		this.itdpAppUserId = itdpAppUserId;
	}
	@Column(name="party_meeting_minute_id")
	public Long getPartyMeetingMinuteId() {
		return partyMeetingMinuteId;
	}

	public void setPartyMeetingMinuteId(Long partyMeetingMinuteId) {
		this.partyMeetingMinuteId = partyMeetingMinuteId;
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
	
}
