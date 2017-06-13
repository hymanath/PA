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
@Table(name = "enrollment_year")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EnrollmentYear {

	//enrollment_year_id,year,order_no
	private Long enrollmentYearId;
	private Long year;
	private String description;
	private Long orderNo;
	private String isActive;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "enrollment_year_id", unique = true, nullable = false)
	public Long getEnrollmentYearId() {
		return enrollmentYearId;
	}
	public void setEnrollmentYearId(Long enrollmentYearId) {
		this.enrollmentYearId = enrollmentYearId;
	}
	
	@Column(name="year")
	public Long getYear() {
		return year;
	}
	public void setYear(Long year) {
		this.year = year;
	}
	
	@Column(name="order_no")
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	
	@Column(name="is_active")
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
