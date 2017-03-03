package com.itgrids.partyanalyst.model;

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


@Entity
@Table(name = "schedulers_info")
public class SchedulersInfo {
	
	
	private Long schedulersInfoId;
	private String name;
	private String description;
	private Date schedulerStartTime; 
	private Date schedulerEndTime; 
	private String status;
	private Long userId;
	
	private User user;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "schedulers_info_id",unique = true,nullable = false)
	public Long getSchedulersInfoId() {
		return schedulersInfoId;
	}

	public void setSchedulersInfoId(Long schedulersInfoId) {
		this.schedulersInfoId = schedulersInfoId;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "scheduler_start_time")
	public Date getSchedulerStartTime() {
		return schedulerStartTime;
	}

	public void setSchedulerStartTime(Date schedulerStartTime) {
		this.schedulerStartTime = schedulerStartTime;
	}

	@Column(name = "scheduler_end_time")
	public Date getSchedulerEndTime() {
		return schedulerEndTime;
	}

	public void setSchedulerEndTime(Date schedulerEndTime) {
		this.schedulerEndTime = schedulerEndTime;
	}

	@Column(name = "status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "user_id")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
