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


/**
 * 
 * @author Srishailam Pittala
 *
 */


@Entity
@Table(name = "event_group_member")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EventGroupMember implements java.io.Serializable{

	private Long eventGroupMemberId;
	private EventGroup eventGroup;
	private Long eventGroupId;
	private TdpCadre tdpCadre;
	private Long tdpCadreId;
	private PublicRepresentative publicRepresentative;
	private Long publicRepresentativeId;
	private Date insertedTime;
	private User user;
	private Long createdBy;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "event_group_member_id", unique = true, nullable = false)
	public Long getEventGroupMemberId() {
		return eventGroupMemberId;
	}
	public void setEventGroupMemberId(Long eventGroupMemberId) {
		this.eventGroupMemberId = eventGroupMemberId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "event_group_id",insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public EventGroup getEventGroup() {
		return eventGroup;
	}
	public void setEventGroup(EventGroup eventGroup) {
		this.eventGroup = eventGroup;
	}
	
	@Column(name="event_group_id")
	public Long getEventGroupId() {
		return eventGroupId;
	}
	public void setEventGroupId(Long eventGroupId) {
		this.eventGroupId = eventGroupId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_cadre_id",insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}
	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}
	
	@Column(name="tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "public_representative_id",insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PublicRepresentative getPublicRepresentative() {
		return publicRepresentative;
	}
	public void setPublicRepresentative(PublicRepresentative publicRepresentative) {
		this.publicRepresentative = publicRepresentative;
	}
	
	@Column(name="public_representative_id")
	public Long getPublicRepresentativeId() {
		return publicRepresentativeId;
	}
	public void setPublicRepresentativeId(Long publicRepresentativeId) {
		this.publicRepresentativeId = publicRepresentativeId;
	}
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "created_by",insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}	
	public void setUser(User user) {
		this.user = user;
	}
	
	@Column(name="created_by")
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
}
