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
@Table(name="party_meeting_invitee")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyMeetingInvitee extends BaseModel implements Serializable{

	private static final long serialVersionUID = -6376793908350998375L;
	
	private Long partyMeetingInviteeId;
	private PartyMeeting partyMeeting;
	private TdpCadre tdpCadre;
	private PublicRepresentative publicRepresentative;
	private User insertedBy;
	private User updatedBy;
	private Date insertedTime;
	private Date updatedTime;
	private Long partyMeetingId;
	private Long tdpCadreId;
	private Long publicRepresentativeId;
	private Long insertedById;
	private Long updatedById;
	private String absenteeRemark;
	private Long tabUserId;
	private String isAttended;
	public PartyMeetingInvitee(){}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="party_meeting_invitee_id", unique=true, nullable=false)
	public Long getPartyMeetingInviteeId() {
		return partyMeetingInviteeId;
	}

	public void setPartyMeetingInviteeId(Long partyMeetingInviteeId) {
		this.partyMeetingInviteeId = partyMeetingInviteeId;
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
	@JoinColumn(name="tdp_cadre_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}

	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="public_representative_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PublicRepresentative getPublicRepresentative() {
		return publicRepresentative;
	}

	public void setPublicRepresentative(PublicRepresentative publicRepresentative) {
		this.publicRepresentative = publicRepresentative;
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

	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}

	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	@Column(name="party_meeting_id")
	public Long getPartyMeetingId() {
		return partyMeetingId;
	}

	public void setPartyMeetingId(Long partyMeetingId) {
		this.partyMeetingId = partyMeetingId;
	}

	@Column(name="tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}

	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}

	@Column(name="public_representative_id")
	public Long getPublicRepresentativeId() {
		return publicRepresentativeId;
	}

	public void setPublicRepresentativeId(Long publicRepresentativeId) {
		this.publicRepresentativeId = publicRepresentativeId;
	}

	@Column(name="inserted_by")
	public Long getInsertedById() {
		return insertedById;
	}

	public void setInsertedById(Long insertedById) {
		this.insertedById = insertedById;
	}

	@Column(name="updated_by")
	public Long getUpdatedById() {
		return updatedById;
	}

	public void setUpdatedById(Long updatedById) {
		this.updatedById = updatedById;
	}

	@Column(name="absentee_remark")
	public String getAbsenteeRemark() {
		return absenteeRemark;
	}

	public void setAbsenteeRemark(String absenteeRemark) {
		this.absenteeRemark = absenteeRemark;
	}

	@Column(name="tab_user_id")
	public Long getTabUserId() {
		return tabUserId;
	}

	public void setTabUserId(Long tabUserId) {
		this.tabUserId = tabUserId;
	}
	@Column(name="is_attended")
	public String getIsAttended() {
		return isAttended;
	}

	public void setIsAttended(String isAttended) {
		this.isAttended = isAttended;
	}
	
	
	
}
