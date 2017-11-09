package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "jb_committee_enrollment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class JbCommitteeEnrollment extends BaseModel implements java.io.Serializable {
	
	private Long jbCommitteeEnrollmentId;
	private String desc;
	private Date fromYear;
	private Date toYear;
	private String isActive;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "jb_committee_enrollment_id", unique = true, nullable = false)
	public Long getJbCommitteeEnrollmentId() {
		return jbCommitteeEnrollmentId;
	}
	public void setJbCommitteeEnrollmentId(Long jbCommitteeEnrollmentId) {
		this.jbCommitteeEnrollmentId = jbCommitteeEnrollmentId;
	}
	@Column(name="desc")
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Column(name="form_year")
	public Date getFromYear() {
		return fromYear;
	}
	public void setFromYear(Date fromYear) {
		this.fromYear = fromYear;
	}
	@Column(name="to_year")
	public Date getToYear() {
		return toYear;
	}
	public void setToYear(Date toYear) {
		this.toYear = toYear;
	}
	@Column(name="is_active")
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
}
