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
@Table(name="activity_option_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActivityOptionType extends BaseModel implements Serializable{
	
	private Long activityOptionTypeId;
	private String type;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "activity_option_type_id", unique = true, nullable = false)
	public Long getActivityOptionTypeId() {
		return activityOptionTypeId;
	}
	public void setActivityOptionTypeId(Long activityOptionTypeId) {
		this.activityOptionTypeId = activityOptionTypeId;
	}
	
	@Column(name = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
