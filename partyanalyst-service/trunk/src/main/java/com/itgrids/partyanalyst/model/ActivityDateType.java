package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="activity_date_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActivityDateType implements java.io.Serializable {
	private Long activityDateTypeId;
	private String dateType;
    @Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name = "activity_date_type_id",nullable = false, unique = true)
    public Long getActivityDateTypeId() {
		return activityDateTypeId;
	}
	public void setActivityDateTypeId(Long activityDateTypeId) {
		this.activityDateTypeId = activityDateTypeId;
	}
	@Column(name = "date_type")
	public String getDateType() {
		return dateType;
	}
	public void setDateType(String dateType) {
		this.dateType = dateType;
	}
	
}
