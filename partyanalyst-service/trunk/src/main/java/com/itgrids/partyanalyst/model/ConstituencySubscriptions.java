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
@Table(name="constituency_subscriptions")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ConstituencySubscriptions implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long constituencySubscriptionsId;
	private User user;
	private Constituency constituency;
	private Long userId;
	private Long constituencyId;
	private Date insertedTime;
	private Date updatedTime;
	
	public ConstituencySubscriptions(){}
	
	public ConstituencySubscriptions(Long userId,Long constituencyId,Date insertedTime,Date updatedTime){
		this.userId=userId;
		this.constituencyId=constituencyId;
		this.insertedTime=insertedTime;
		this.updatedTime=updatedTime;
	}
	
	
	@Id
	@GeneratedValue( strategy=GenerationType.AUTO)
	@Column(name="constituency_subscriptions_id",unique = true,nullable =false)
	
	public Long getConstituencySubscriptionsId() {
		return constituencySubscriptionsId;
	}
	public void setConstituencySubscriptionsId(Long constituencySubscriptionsId) {
		this.constituencySubscriptionsId = constituencySubscriptionsId;
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
	@JoinColumn(name="constituency_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}
	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}
	
	@Column(name = "user_id", length = 10)
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name = "constituency_id", length = 10)
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
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
