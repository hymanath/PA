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
@Table(name = "event_caste_category_day_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EventCasteCategoryDayInfo extends BaseModel implements java.io.Serializable {
	

	private Long eventCasteCategoryDayInfoId;
	private Long eventCasteCategoryInfoId;
	private Long total;
	private Long invitee;
	private Long nonInvitee;
	private String dayStr;
	
	private EventCasteCategoryInfo eventCasteCategoryInfo;
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "event_caste_category_day_info_id", unique = true, nullable = false)
	public Long getEventCasteCategoryDayInfoId() {
		return eventCasteCategoryDayInfoId;
	}

	public void setEventCasteCategoryDayInfoId(Long eventCasteCategoryDayInfoId) {
		this.eventCasteCategoryDayInfoId = eventCasteCategoryDayInfoId;
	}
	@Column(name = "event_caste_category_info_id")
	public Long getEventCasteCategoryInfoId() {
		return eventCasteCategoryInfoId;
	}

	public void setEventCasteCategoryInfoId(Long eventCasteCategoryInfoId) {
		this.eventCasteCategoryInfoId = eventCasteCategoryInfoId;
	}
	@Column(name = "total")
	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
	@Column(name = "invitee")
	public Long getInvitee() {
		return invitee;
	}

	public void setInvitee(Long invitee) {
		this.invitee = invitee;
	}
	@Column(name = "non_invitee")
	public Long getNonInvitee() {
		return nonInvitee;
	}

	public void setNonInvitee(Long nonInvitee) {
		this.nonInvitee = nonInvitee;
	}
	@Column(name = "day_str")
	public String getDayStr() {
		return dayStr;
	}

	public void setDayStr(String dayStr) {
		this.dayStr = dayStr;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "event_caste_category_info_id",insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public EventCasteCategoryInfo getEventCasteCategoryInfo() {
		return eventCasteCategoryInfo;
	}

	public void setEventCasteCategoryInfo(
			EventCasteCategoryInfo eventCasteCategoryInfo) {
		this.eventCasteCategoryInfo = eventCasteCategoryInfo;
	}
	
	
}
