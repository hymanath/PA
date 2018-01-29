package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pm_officer_designation")
public class PmOfficerDesignation {

	private Long pmOfficerDesignationId;
	private String designation;
	private String shortName;
	
	@Id
	@Column(name="pm_officer_designation_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPmOfficerDesignationId() {
		return pmOfficerDesignationId;
	}
	public void setPmOfficerDesignationId(Long pmOfficerDesignationId) {
		this.pmOfficerDesignationId = pmOfficerDesignationId;
	}
	@Column(name="designation")
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	@Column(name="short_name")
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	
}
