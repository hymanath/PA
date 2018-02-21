package com.itgrids.model;

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

@Entity
@Table(name = "govt_main_work_user")
public class GovtMainWorkUser {
	
	private Long govtMainWorkUserId;
	private Long govtMainWorkId;
	private Long userId;
	
	private GovtMainWork govtMainWork;
	private MobileAppUser user;
	
	
	@Id
	@Column(name="govt_main_work_user_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getGovtMainWorkUserId() {
		return govtMainWorkUserId;
	}
	public void setGovtMainWorkUserId(Long govtMainWorkUserId) {
		this.govtMainWorkUserId = govtMainWorkUserId;
	}
	
	@Column(name="govt_main_work_id")
	public Long getGovtMainWorkId() {
		return govtMainWorkId;
	}
	public void setGovtMainWorkId(Long govtMainWorkId) {
		this.govtMainWorkId = govtMainWorkId;
	}
	
	@Column(name="user_id")
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "govt_main_work_id", insertable = false, updatable = false)
	public GovtMainWork getGovtMainWork() {
		return govtMainWork;
	}
	public void setGovtMainWork(GovtMainWork govtMainWork) {
		this.govtMainWork = govtMainWork;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	public MobileAppUser getUser() {
		return user;
	}
	public void setUser(MobileAppUser user) {
		this.user = user;
	}
}
