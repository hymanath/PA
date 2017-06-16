package com.itgrids.model;

import java.util.Date;

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
@Table(name = "fund_sanction_matrix_details")
public class FundSanctionMatrixDetails {
	
	private static final long serialVersionUID = -2853930539938433902L;

	

	private Long fundSanctionMatrixDetailsId;
	private Long scopeId;
	private String scopeValue;
	private Long previousYearId;
	private Long presentYearId;
	private Long fundSanctionMatrixRangeId;
	private String isDeleted;
	private FundSanctionMatrixRange fundSanctionMatrixRange;
	private Date insertedTime;
	
	@Id
	@Column(name="fund_sanction_matrix_details_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getFundSanctionMatrixDetailsId() {
		return fundSanctionMatrixDetailsId;
	}

	public void setFundSanctionMatrixDetailsId(Long fundSanctionMatrixDetailsId) {
		this.fundSanctionMatrixDetailsId = fundSanctionMatrixDetailsId;
	}

	@Column(name="scope_id")
	public Long getScopeId() {
		return scopeId;
	}

	public void setScopeId(Long scopeId) {
		this.scopeId = scopeId;
	}

	@Column(name="scope_value")
	public String getScopeValue() {
		return scopeValue;
	}

	public void setScopeValue(String scopeValue) {
		this.scopeValue = scopeValue;
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

	@Column(name="fund_sanction_matrix_range_id")
	public Long getFundSanctionMatrixRangeId() {
		return fundSanctionMatrixRangeId;
	}

	public void setFundSanctionMatrixRangeId(Long fundSanctionMatrixRangeId) {
		this.fundSanctionMatrixRangeId = fundSanctionMatrixRangeId;
	}

	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "fund_sanction_matrix_range_id", insertable = false, updatable = false)
	public FundSanctionMatrixRange getFundSanctionMatrixRange() {
		return fundSanctionMatrixRange;
	}

	public void setFundSanctionMatrixRange(
			FundSanctionMatrixRange fundSanctionMatrixRange) {
		this.fundSanctionMatrixRange = fundSanctionMatrixRange;
	}

	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}	
	
}
