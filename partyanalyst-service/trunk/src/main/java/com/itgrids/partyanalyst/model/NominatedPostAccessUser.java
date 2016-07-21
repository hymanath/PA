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

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;
@Entity
@Table(name = "nominated_post_access_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NominatedPostAccessUser implements java.io.Serializable {

	private Long nominatedPostAccessUserId;
	private Long nominatedPostStatusId;
	private Long nominatedPostAccessTypeId;
	private Long userId;
	private String isActive;
	
	private NominatedPostStatus nominatedPostStatus;
	private NominatedPostAccessType nominatedPostAccessType;
	private User user;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "nominated_post_access_user_id", unique = true, nullable = false)
	public Long getNominatedPostAccessUserId() {
		return nominatedPostAccessUserId;
	}
	public void setNominatedPostAccessUserId(Long nominatedPostAccessUserId) {
		this.nominatedPostAccessUserId = nominatedPostAccessUserId;
	}
	@Column(name = "nominated_post_status_id")
	public Long getNominatedPostStatusId() {
		return nominatedPostStatusId;
	}
	public void setNominatedPostStatusId(Long nominatedPostStatusId) {
		this.nominatedPostStatusId = nominatedPostStatusId;
	}
	@Column(name = "access_type_id")
	public Long getNominatedPostAccessTypeId() {
		return nominatedPostAccessTypeId;
	}
	public void setNominatedPostAccessTypeId(Long nominatedPostAccessTypeId) {
		this.nominatedPostAccessTypeId = nominatedPostAccessTypeId;
	}
	@Column(name = "user_id")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Column(name = "is_active")
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="nominated_post_status_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public NominatedPostStatus getNominatedPostStatus() {
		return nominatedPostStatus;
	}
	public void setNominatedPostStatus(NominatedPostStatus nominatedPostStatus) {
		this.nominatedPostStatus = nominatedPostStatus;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="access_type_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public NominatedPostAccessType getNominatedPostAccessType() {
		return nominatedPostAccessType;
	}
	public void setNominatedPostAccessType(
			NominatedPostAccessType nominatedPostAccessType) {
		this.nominatedPostAccessType = nominatedPostAccessType;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
