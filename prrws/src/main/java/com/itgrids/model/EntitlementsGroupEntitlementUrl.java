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
@Table(name = "entitlements_group_entitlement_url")
public class EntitlementsGroupEntitlementUrl extends BaseModel {
	
	private Long entitlementsGroupEntitlementUrlId;
	private Long entitlementGroupId;
	private Long entitlementUrlId;
	private Long parentEntitlementsGroupId;
	private Long orderNo;
	private String isDeleted;
	private Date insertedTime;
	private Date updatedTime;
	private Long insertedUserId;
	private Long updatedUserId;
	
	private EntitlementGroup entitlementGroup;
	private EntitlementUrl entitlementUrl;
	private EntitlementGroup parentEntitlementsGroup;
	private User insertedUser;
	private User updatedUser;
	
	@Id
	@Column(name="entitlements_group_entitlement_url_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getEntitlementsGroupEntitlementUrlId() {
		return entitlementsGroupEntitlementUrlId;
	}
	public void setEntitlementsGroupEntitlementUrlId(Long entitlementsGroupEntitlementUrlId) {
		this.entitlementsGroupEntitlementUrlId = entitlementsGroupEntitlementUrlId;
	}
	@Column(name="entitlements_group_id")
	public Long getEntitlementGroupId() {
		return entitlementGroupId;
	}
	public void setEntitlementGroupId(Long entitlementGroupId) {
		this.entitlementGroupId = entitlementGroupId;
	}
	@Column(name="entitlement_url_id")
	public Long getEntitlementUrlId() {
		return entitlementUrlId;
	}
	public void setEntitlementUrlId(Long entitlementUrlId) {
		this.entitlementUrlId = entitlementUrlId;
	}
	@Column(name="parent_entitlements_group_id")
	public Long getParentEntitlementsGroupId() {
		return parentEntitlementsGroupId;
	}
	public void setParentEntitlementsGroupId(Long parentEntitlementsGroupId) {
		this.parentEntitlementsGroupId = parentEntitlementsGroupId;
	}
	@Column(name="order_no")
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
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
	@JoinColumn(name = "entitlement_url_id", insertable = false, updatable = false)
	public EntitlementUrl getEntitlementUrl() {
		return entitlementUrl;
	}
	
	public void setEntitlementUrl(EntitlementUrl entitlementUrl) {
		this.entitlementUrl = entitlementUrl;
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
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_entitlements_group_id", insertable = false, updatable = false)
	public EntitlementGroup getParentEntitlementsGroup() {
		return parentEntitlementsGroup;
	}
	public void setParentEntitlementsGroup(EntitlementGroup parentEntitlementsGroup) {
		this.parentEntitlementsGroup = parentEntitlementsGroup;
	}
	
	
}
