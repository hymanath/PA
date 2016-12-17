package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="session_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SessionType extends BaseModel implements Serializable{
	
	private Long sessionTypeId;
	private String type;
	private Time startTime;
	private Time endTime;
	private Time lateTime;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="session_type_id", unique=true, nullable=false)
	public Long getSessionTypeId() {
		return sessionTypeId;
	}
	public void setSessionTypeId(Long sessionTypeId) {
		this.sessionTypeId = sessionTypeId;
	}
	
	@Column(name="type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name="start_time")
	public Time getStartTime() {
		return startTime;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	@Column(name="end_time")
	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	
	@Column(name="late_time")
	public Time getLateTime() {
		return lateTime;
	}
	public void setLateTime(Time lateTime) {
		this.lateTime = lateTime;
	}
	
	
	

}
