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
/**
 *  PartySubscriptions entity
 */
@Entity
@Table(name="party_subscriptions")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)


public class PartySubscriptions implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long partySubscriptionsId;
	private User user;
	private Party party;
	private Long userId;
	private Long partyId;
	private Date insertedTime;
	private Date updatedTime;
	
	public PartySubscriptions(){
	}
	
	public PartySubscriptions(Long userId,Long partyId,Date insertedTime,Date updatedTime){
		this.partyId=partyId;
		this.userId=userId;
		this.insertedTime=insertedTime;
		this.updatedTime=updatedTime;
	}

	@Id
	@GeneratedValue( strategy=GenerationType.AUTO)
	@Column(name="party_subscriptions_id",unique = true,nullable =false)
	public Long getPartySubscriptionsId() {
		return partySubscriptionsId;
	}

	public void setPartySubscriptionsId(Long partySubscriptionsId) {
		this.partySubscriptionsId = partySubscriptionsId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="party_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)


	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	@Column(name = "user_id", length = 10)
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "party_id", length = 10)
	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	@Column(name="inserted_time",length=10)
	public Date getInsertedTime() {
		return insertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name="updated_time",length=10)
	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	
	
	

}
