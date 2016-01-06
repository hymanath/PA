package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "required_attribute")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RequiredAttribute extends BaseModel implements Serializable{
	
	private Long requiredAttributeId;
	private String requiredAttribute;
	private String isActive;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "required_attribute_id", unique = true, nullable = false)
	public Long getRequiredAttributeId() {
		return requiredAttributeId;
	}
	public void setRequiredAttributeId(Long requiredAttributeId) {
		this.requiredAttributeId = requiredAttributeId;
	}
	
	@Column(name="required_attribute")
	public String getRequiredAttribute() {
		return requiredAttribute;
	}
	public void setRequiredAttribute(String requiredAttribute) {
		this.requiredAttribute = requiredAttribute;
	}
	
	@Column(name="is_active")
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
}
