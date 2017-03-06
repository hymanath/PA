package com.itgrids.partyanalyst.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;



@Entity
@Table(name = "tdp_cadre_registraion_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCadreRegistraionInfo  implements java.io.Serializable{

	private Long tdpCadreRegistraionInfoId;
	private Long locationLevelId;
	private Long locationLevelValue;
	private Long enrollmentYearId;
	private Long regCount;
	private String regPerc;

	private EnrollmentYear enrollmentYear;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_cadre_registraion_info_id", unique = true, nullable = false)
	public Long getTdpCadreRegistraionInfoId() {
		return tdpCadreRegistraionInfoId;
	}

	public void setTdpCadreRegistraionInfoId(Long tdpCadreRegistraionInfoId) {
		this.tdpCadreRegistraionInfoId = tdpCadreRegistraionInfoId;
	}

	@Column(name="location_level_id")
	public Long getLocationLevelId() {
		return locationLevelId;
	}

	public void setLocationLevelId(Long locationLevelId) {
		this.locationLevelId = locationLevelId;
	}

	@Column(name="location_level_value")
	public Long getLocationLevelValue() {
		return locationLevelValue;
	}

	public void setLocationLevelValue(Long locationLevelValue) {
		this.locationLevelValue = locationLevelValue;
	}

	@Column(name="enrollment_year_id")
	public Long getEnrollmentYearId() {
		return enrollmentYearId;
	}

	public void setEnrollmentYearId(Long enrollmentYearId) {
		this.enrollmentYearId = enrollmentYearId;
	}

	@Column(name="reg_count")
	public Long getRegCount() {
		return regCount;
	}

	public void setRegCount(Long regCount) {
		this.regCount = regCount;
	}
	
	@Column(name="reg_perc")
	public String getRegPerc() {
		return regPerc;
	}

	public void setRegPerc(String regPerc) {
		this.regPerc = regPerc;
	}	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="enrollment_year_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public EnrollmentYear getEnrollmentYear() {
		return enrollmentYear;
	}

	public void setEnrollmentYear(EnrollmentYear enrollmentYear) {
		this.enrollmentYear = enrollmentYear;
	}
	
	
}
