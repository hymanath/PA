package com.itgrids.partyanalyst.model;

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
@Table(name="party_office")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyOffice extends BaseModel {
	private static final long serialVersionUID = -5612220717881986640L;
	
	private Long partyOfficeId;
	private String officeName;
	private String isDeleted;
	private Long eventId;
	
	private Event event;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "party_office_id", unique = true, nullable = false)
	public Long getPartyOfficeId() {
		return partyOfficeId;
	}
	public void setPartyOfficeId(Long partyOfficeId) {
		this.partyOfficeId = partyOfficeId;
	}
	@Column(name = "office_name")
	public String getOfficeName() {
		return officeName;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name = "event_id")
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="event_id",insertable=false, updatable = false )
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
}
