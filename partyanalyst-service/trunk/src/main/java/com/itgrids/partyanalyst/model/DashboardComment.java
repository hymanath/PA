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

@Entity
@Table(name = "dashboard_comment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DashboardComment  extends BaseModel implements Serializable{
	
	
	private Long dashboardCommentId;
	private  DashboardComponent dashboardComponent;
	private Long dashboradComponentId;
	private String comment;
	private User user;
	private Long userId;
	private Date insertedTime;
	private String isDeleted;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "dashboard_comment_id", unique = true, nullable = false)
	public Long getDashboardCommentId() {
		return dashboardCommentId;
	}
	
	public void setDashboardCommentId(Long dashboardCommentId) {
		this.dashboardCommentId = dashboardCommentId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "dashboard_component_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public DashboardComponent getDashboardComponent() {
		return dashboardComponent;
	}
	
	public void setDashboardComponent(DashboardComponent dashboardComponent) {
		this.dashboardComponent = dashboardComponent;
	}
	
	@Column(name = "dashboard_component_id")
	public Long getDashboradComponentId() {
		return dashboradComponentId;
	}
	
	public void setDashboradComponentId(Long dashboradComponentId) {
		this.dashboradComponentId = dashboradComponentId;
	}
	
	@Column(name ="comment")
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	@Column(name ="user_id")
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Column(name ="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name ="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	
}
