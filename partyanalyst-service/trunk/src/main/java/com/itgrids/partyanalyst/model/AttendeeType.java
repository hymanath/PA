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
@Table(name = "attendee_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AttendeeType extends BaseModel implements Serializable{
	
	private Long attendeeTypeId;
	private String type;
	private String isDeleted;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="attendee_type_id", unique=true, nullable=false)
	public Long getAttendeeTypeId() {
		return attendeeTypeId;
	}
	public void setAttendeeTypeId(Long attendeeTypeId) {
		this.attendeeTypeId = attendeeTypeId;
	}
	
	@Column(name="type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
}
