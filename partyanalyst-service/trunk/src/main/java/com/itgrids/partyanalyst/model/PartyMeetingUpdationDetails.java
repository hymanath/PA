package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

import java.util.Date;

@Entity
@Table(name="party_meeting_updation_details")
public class PartyMeetingUpdationDetails implements Serializable {
	
	private Long partyMeetingUpdationDetailsId;
	private Long partyMeetingId;
	//private String memberType;
	private Long tdpCadreId;
	private String name;
	private String mobileNo;
	private String comment;
	private String isDeleted;
	private String updatedBy;
	private Long userId;
	private Date insertedTime;
	
	private TdpCadre tdpCadre;
	private PartyMeeting partyMeeting;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="party_meeting_updation_details_id", unique=true, nullable=false)
	
	public Long getPartyMeetingUpdationDetailsId() {
		return partyMeetingUpdationDetailsId;
	}

	public void setPartyMeetingUpdationDetailsId(Long partyMeetingUpdationDetailsId) {
		this.partyMeetingUpdationDetailsId = partyMeetingUpdationDetailsId;
	}
	@Column(name="party_meeting_id")
	public Long getPartyMeetingId() {
		return partyMeetingId;
	}

	public void setPartyMeetingId(Long partyMeetingId) {
		this.partyMeetingId = partyMeetingId;
	}
	/*@Column(name="member_type")
	public String getMemberType() {
		return memberType;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}*/
	@Column(name="tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}

	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(name="mobile_no")
	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	@Column(name="comment")
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name="updated_by")
	public String getUpdatedBy() {
		return updatedBy;
	}
	
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	@Column(name="user_id")
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_cadre_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}

	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}

	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "party_meeting_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PartyMeeting getPartyMeeting() {
		return partyMeeting;
	}
	public void setPartyMeeting(PartyMeeting partyMeeting) {
		this.partyMeeting = partyMeeting;
	}
}