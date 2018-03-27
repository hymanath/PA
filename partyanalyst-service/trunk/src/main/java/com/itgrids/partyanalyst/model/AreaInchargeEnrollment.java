package com.itgrids.partyanalyst.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "area_incharge_enrollment")
public class AreaInchargeEnrollment {
    /*
     * Author:srujana
     */
	private Long areaInchargeEnrollmentId;
	private Date year;
	private Long publicationDateId;
	private String isDeleted;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "area_incharge_enrollment_id",unique = true,nullable = false)
	public Long getAreaInchargeEnrollmentId() {
		return areaInchargeEnrollmentId;
	}
	public void setAreaInchargeEnrollmentId(Long areaInchargeEnrollmentId) {
		this.areaInchargeEnrollmentId = areaInchargeEnrollmentId;
	}
	@Column(name = "year")
	public Date getYear() {
		return year;
	}
	public void setYear(Date year) {
		this.year = year;
	}
	@Column(name = "publication_date_id")
	public Long getPublicationDateId() {
		return publicationDateId;
	}
	public void setPublicationDateId(Long publicationDateId) {
		this.publicationDateId = publicationDateId;
	}
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	

}
