/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */
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

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

/**
 * SpecialPageSubscriptions entity. 
 */
@Entity
@Table(name = "special_page_subscriptions")


public class SpecialPageSubscriptions implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long specialPageSubscriptionId;
	private SpecialPage specialpage;
	private User user;
	private Long specialPageId;
	private Long userId;
	private Date insertedTime;
	private Date updatedTime;
	
	public SpecialPageSubscriptions(){
	}
	
	public SpecialPageSubscriptions(Long specialPageId,Long userId,Date insertedTime,Date updatedTime){
		this.specialPageId=specialPageId;
		this.userId=userId;
		this.insertedTime=insertedTime;
		this.updatedTime=updatedTime;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="special_page_subscriptions_id",nullable=false,unique=true)
	public Long getSpecialPageSubscriptionId() {
		return specialPageSubscriptionId;
	}

	public void setSpecialPageSubscriptionId(Long specialPageSubscriptionId) {
		this.specialPageSubscriptionId = specialPageSubscriptionId;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="special_page_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	
	public SpecialPage getSpecialpage() {
		return specialpage;
	}

	public void setSpecialpage(SpecialPage specialpage) {
		this.specialpage = specialpage;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name="special_page_id",length=10)
	public Long getSpecialPageId() {
		return specialPageId;
	}

	public void setSpecialPageId(Long specialPageId) {
		this.specialPageId = specialPageId;
	}

	@Column(name="user_id",length=10)
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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