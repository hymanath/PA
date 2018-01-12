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
@Table(name ="user_group_entitlement")
public class UserGroupEntitlement extends BaseModel {
	
	private Long userGroupEntitlementId;
	private Long entitlementGroupId;
	private Long userId;
	private String isDeleted;
	private Date insertedTime;
	private Date updatedTime;
	private Long insertedUserId;
	private Long updatedUserId;
	
	private EntitlementGroup entitlementGroup;
	private User user;
	private User insertedUser;
	private User updatedUser;
	
	@Id
	@Column(name="user_group_entitlement_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getUserGroupEntitlementId() {
		return userGroupEntitlementId;
	}
	public void setUserGroupEntitlementId(Long userGroupEntitlementId) {
		this.userGroupEntitlementId = userGroupEntitlementId;
	}
	@Column(name="entitlements_group_id")
	public Long getEntitlementGroupId() {
		return entitlementGroupId;
	}
	public void setEntitlementGroupId(Long entitlementGroupId) {
		this.entitlementGroupId = entitlementGroupId;
	}
	@Column(name="user_id")
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	@Column(name="inserted_user_id")
	public Long getInsertedUserId() {
		return insertedUserId;
	}
	public void setInsertedUserId(Long insertedUserId) {
		this.insertedUserId = insertedUserId;
	}
	@Column(name="updated_user_id")
	public Long getUpdatedUserId() {
		return updatedUserId;
	}
	public void setUpdatedUserId(Long updatedUserId) {
		this.updatedUserId = updatedUserId;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "entitlements_group_id", insertable = false, updatable = false)
	public EntitlementGroup getEntitlementGroup() {
		return entitlementGroup;
	}
	public void setEntitlementGroup(EntitlementGroup entitlementGroup) {
		this.entitlementGroup = entitlementGroup;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "inserted_user_id", insertable = false, updatable = false)
	public User getInsertedUser() {
		return insertedUser;
	}
	public void setInsertedUser(User insertedUser) {
		this.insertedUser = insertedUser;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_user_id", insertable = false, updatable = false)
	public User getUpdatedUser() {
		return updatedUser;
	}
	public void setUpdatedUser(User updatedUser) {
		this.updatedUser = updatedUser;
	}
}
