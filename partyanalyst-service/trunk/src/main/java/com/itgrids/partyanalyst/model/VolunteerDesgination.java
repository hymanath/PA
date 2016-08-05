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
@Table(name = "volunteer_designation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VolunteerDesgination  extends BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long volunteerDesignationId;
	private String volunteerDesignation;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "volunteer_designation_id", unique = true, nullable = false)
	public Long getVolunteerDesignationId() {
		return volunteerDesignationId;
	}
	public void setVolunteerDesignationId(Long volunteerDesignationId) {
		this.volunteerDesignationId = volunteerDesignationId;
	}
	
	@Column(name = "volunteer_designation")
	public String getVolunteerDesignation() {
		return volunteerDesignation;
	}
	public void setVolunteerDesignation(String volunteerDesignation) {
		this.volunteerDesignation = volunteerDesignation;
	}
	
	
}
