package com.itgrids.model;

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

/**
 * Description
 * @author <a href="mailto:shrinu.pittala@itgrids.com">Shrinu Pittala</a> 
 * @version 1.0/
 */

@Entity
@Table(name = "fund_sanction_matrix_range")
public class FundSanctionMatrixRange {
	
	private static final long serialVersionUID = -2853930539938433902L;
	

	private Long fundSanctionMatrixRangeId;	
	private Long previousYearId;	
	private Long presentYearId;
	private Long scopeId;
	private String rangeValue;
	private String min;
	private String max;
	private String orderNo;
	private FinancialYear previousYear;
	private FinancialYear presentYear;
	private LocationScope locationScope;
	
	@Id
	@Column(name="fund_sanction_matrix_range_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getFundSanctionMatrixRangeId() {
		return fundSanctionMatrixRangeId;
	}

	public void setFundSanctionMatrixRangeId(Long fundSanctionMatrixRangeId) {
		this.fundSanctionMatrixRangeId = fundSanctionMatrixRangeId;
	}

	@Column(name="previous_year_id")
	public Long getPreviousYearId() {
		return previousYearId;
	}

	public void setPreviousYearId(Long previousYearId) {
		this.previousYearId = previousYearId;
	}

	@Column(name="present_year_id")
	public Long getPresentYearId() {
		return presentYearId;
	}

	
	public void setPresentYearId(Long presentYearId) {
		this.presentYearId = presentYearId;
	}

	@Column(name="range_value")
	public String getRangeValue() {
		return rangeValue;
	}

	public void setRangeValue(String rangeValue) {
		this.rangeValue = rangeValue;
	}

	@Column(name="min")
	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	@Column(name="max")
	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	@Column(name="order_no")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "fund_sanction_matrix_range_id", insertable = false, updatable = false)
	public FinancialYear getPreviousYear() {
		return previousYear;
	}

	public void setPreviousYear(FinancialYear previousYear) {
		this.previousYear = previousYear;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "fund_sanction_matrix_range_id", insertable = false, updatable = false)
	public FinancialYear getPresentYear() {
		return presentYear;
	}

	public void setPresentYear(FinancialYear presentYear) {
		this.presentYear = presentYear;
	}
	@Column(name="scope_id")
	public Long getScopeId() {
		return scopeId;
	}

	public void setScopeId(Long scopeId) {
		this.scopeId = scopeId;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "scope_id", insertable = false, updatable = false)
	public LocationScope getLocationScope() {
		return locationScope;
	}
	public void setLocationScope(LocationScope locationScope) {
		this.locationScope = locationScope;
	}	
	
	
	
}
