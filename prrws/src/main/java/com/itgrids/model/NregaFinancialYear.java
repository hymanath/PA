package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nrega_financial_year")
public class NregaFinancialYear {

	private Long nregaFinancialYearId;
	private String yearDesc;
	private String fromYear;
	private String toYear;
	
	@Id
	@Column(name="nrega_financial_year_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getNregaFinancialYearId() {
		return nregaFinancialYearId;
	}
	public void setNregaFinancialYearId(Long nregaFinancialYearId) {
		this.nregaFinancialYearId = nregaFinancialYearId;
	}
	
	@Column(name="year_desc")
	public String getYearDesc() {
		return yearDesc;
	}
	public void setYearDesc(String yearDesc) {
		this.yearDesc = yearDesc;
	}
	
	@Column(name="from_year")
	public String getFromYear() {
		return fromYear;
	}
	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}
	
	@Column(name="to_year")
	public String getToYear() {
		return toYear;
	}
	public void setToYear(String toYear) {
		this.toYear = toYear;
	}
}
