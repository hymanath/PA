package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "financial_year")
public class FinancialYear{
	
	
	private static final long serialVersionUID = -2853930539938433902L;

	@Id
	@Column(name="financial_year_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long financialYearId;
	
	@Column(name="year_desc")
	private String yearDesc;
	
	@Column(name="from_year")
	private Long fromYear;
	
	@Column(name="to_year")
	private Long toTear;

	public Long getFinancialYearId() {
		return financialYearId;
	}

	public void setFinancialYearId(Long financialYearId) {
		this.financialYearId = financialYearId;
	}

	public String getYearDesc() {
		return yearDesc;
	}

	public void setYearDesc(String yearDesc) {
		this.yearDesc = yearDesc;
	}

	public Long getFromYear() {
		return fromYear;
	}

	public void setFromYear(Long fromYear) {
		this.fromYear = fromYear;
	}

	public Long getToTear() {
		return toTear;
	}

	public void setToTear(Long toTear) {
		this.toTear = toTear;
	}
	
	
	
}
