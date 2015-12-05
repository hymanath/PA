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
@Table(name = "activity_attribute")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActivityAttribute extends BaseModel implements Serializable{
	
	private Long activityAttributeId;
	private String attributeName;
	private String description;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "activity_attribute_id", unique = true, nullable = false)
	public Long getActivityAttributeId() {
		return activityAttributeId;
	}
	public void setActivityAttributeId(Long activityAttributeId) {
		this.activityAttributeId = activityAttributeId;
	}
	
	@Column(name="attribute_name")
	public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
