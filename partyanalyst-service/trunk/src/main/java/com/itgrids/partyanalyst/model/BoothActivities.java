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
@Table(name="booth_activities")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BoothActivities extends BaseModel implements Serializable{

	private static final long serialVersionUID = 6455050842550570647L;
	
	private Long boothActivitiesId;
	private String activity;
	
	public BoothActivities(){}
	
	public BoothActivities(String activity)
	{
		this.activity = activity;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="booth_activities_id", unique=true, nullable=false)
	public Long getBoothActivitiesId() {
		return boothActivitiesId;
	}

	public void setBoothActivitiesId(Long boothActivitiesId) {
		this.boothActivitiesId = boothActivitiesId;
	}

	@Column(name="activity",length=200)
	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}
	
}
