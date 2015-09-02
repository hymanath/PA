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
@Table(name = "academic_year")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AcademicYear extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long acadamicYearId;
	private String startYear;
	private String startMonth;
	private String endYear;
	private String endMonth;
	private Long code;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "acadamic_year_id", unique = true, nullable = false)
	public Long getAcadamicYearId() {
		return acadamicYearId;
	}
	public void setAcadamicYearId(Long acadamicYearId) {
		this.acadamicYearId = acadamicYearId;
	}
	
	@Column(name = "start_year")
	public String getStartYear() {
		return startYear;
	}
	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}
	
	@Column(name = "start_month")
	public String getStartMonth() {
		return startMonth;
	}
	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}
	
	@Column(name = "end_year")
	public String getEndYear() {
		return endYear;
	}
	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}
	
	@Column(name = "end_month")
	public String getEndMonth() {
		return endMonth;
	}
	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}
	
	@Column(name = "code")
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
}
