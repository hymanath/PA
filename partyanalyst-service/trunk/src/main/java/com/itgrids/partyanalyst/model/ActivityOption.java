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
@Table(name="activity_option")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActivityOption extends BaseModel implements Serializable{
	
	private Long activityOptionId;
	private String option;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "activity_option_id", unique = true, nullable = false)
	public Long getActivityOptionId() {
		return activityOptionId;
	}
	public void setActivityOptionId(Long activityOptionId) {
		this.activityOptionId = activityOptionId;
	}
	
	@Column(name = "option")
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
}
