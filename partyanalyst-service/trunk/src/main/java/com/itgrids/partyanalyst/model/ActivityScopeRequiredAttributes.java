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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "activity_scope_required_attributes")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActivityScopeRequiredAttributes extends BaseModel implements Serializable{
	
	private Long activityScopeRequiredAttributesId;
	private Long activityScopeId;
	private Long requiredAttributeId;
	private String isActive;
	
	private ActivityScope activityScope;
	private RequiredAttribute requiredAttribute;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "activity_scope_required_attributes_id", unique = true, nullable = false)
	public Long getActivityScopeRequiredAttributesId() {
		return activityScopeRequiredAttributesId;
	}
	public void setActivityScopeRequiredAttributesId(
			Long activityScopeRequiredAttributesId) {
		this.activityScopeRequiredAttributesId = activityScopeRequiredAttributesId;
	}
	
	@Column(name="activity_scope_id")
	public Long getActivityScopeId() {
		return activityScopeId;
	}
	public void setActivityScopeId(Long activityScopeId) {
		this.activityScopeId = activityScopeId;
	}
	
	@Column(name="required_attribute_id")
	public Long getRequiredAttributeId() {
		return requiredAttributeId;
	}
	public void setRequiredAttributeId(Long requiredAttributeId) {
		this.requiredAttributeId = requiredAttributeId;
	}
	
	@Column(name="is_active")
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="activity_scope_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ActivityScope getActivityScope() {
		return activityScope;
	}
	public void setActivityScope(ActivityScope activityScope) {
		this.activityScope = activityScope;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="required_attribute_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public RequiredAttribute getRequiredAttribute() {
		return requiredAttribute;
	}
	public void setRequiredAttribute(RequiredAttribute requiredAttribute) {
		this.requiredAttribute = requiredAttribute;
	}
}
