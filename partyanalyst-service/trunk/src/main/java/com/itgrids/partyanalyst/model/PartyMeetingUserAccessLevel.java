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
@Table(name="party_meeting_user_access_level")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyMeetingUserAccessLevel extends BaseModel implements Serializable{

	private static final long serialVersionUID = -3806379738904956901L;
	
	private Long partyMeetingUserAccessLevelId;
	private Long userId;
	private User user;
	private Long partyMeetingLevelId;
	private PartyMeetingLevel partyMeetingLevel;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="party_meeting_user_access_level_id", unique=true, nullable=false)
	public Long getPartyMeetingUserAccessLevelId() {
		return partyMeetingUserAccessLevelId;
	}
	public void setPartyMeetingUserAccessLevelId(Long partyMeetingUserAccessLevelId) {
		this.partyMeetingUserAccessLevelId = partyMeetingUserAccessLevelId;
	}
	
	@Column(name="user_id")
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Column(name="party_meeting_level_id")
	public Long getPartyMeetingLevelId() {
		return partyMeetingLevelId;
	}
	public void setPartyMeetingLevelId(Long partyMeetingLevelId) {
		this.partyMeetingLevelId = partyMeetingLevelId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="party_meeting_level_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PartyMeetingLevel getPartyMeetingLevel() {
		return partyMeetingLevel;
	}
	public void setPartyMeetingLevel(PartyMeetingLevel partyMeetingLevel) {
		this.partyMeetingLevel = partyMeetingLevel;
	}
	
	
	
}
