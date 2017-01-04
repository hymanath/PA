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
@Table(name="self_appraisal_tours_month")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SelfAppraisalToursMonth {

	private Long selfAppraisalToursMonthId;
	private String toursMonth;
	private String monthName;
	private Long monthNo;
	private String year;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="self_appraisal_tours_month_id", unique=true, nullable=false)
	public Long getSelfAppraisalToursMonthId() {
		return selfAppraisalToursMonthId;
	}
	public void setSelfAppraisalToursMonthId(Long selfAppraisalToursMonthId) {
		this.selfAppraisalToursMonthId = selfAppraisalToursMonthId;
	}
	@Column(name="tours_month")
	public String getToursMonth() {
		return toursMonth;
	}
	public void setToursMonth(String toursMonth) {
		this.toursMonth = toursMonth;
	}
	@Column(name="month_name")
	public String getMonthName() {
		return monthName;
	}
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
	@Column(name="month_no")
	public Long getMonthNo() {
		return monthNo;
	}
	public void setMonthNo(Long monthNo) {
		this.monthNo = monthNo;
	}
	@Column(name="year")
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	
}
