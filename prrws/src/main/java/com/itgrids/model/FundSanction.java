package com.itgrids.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fund_saction")
public class FundSanction{
	
	
	private static final long serialVersionUID = -2853930539938433902L;

	@Id
	@Column(name="fund_saction_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long fundSactionId;
	
	@Column(name="location_scope_id")
	private Long locationScopeId;
	
	@Column(name="location_value")
	private Long locationValue;
	
	@Column(name="address_id")
	private Long addressId;
	
	@Column(name="work_name")
	private String workName;
	
	@Column(name="saction_amount")
	private Long sactionAmount;
	
	@Column(name="go_no_date")
	private String goNoDate;
	
	@Column(name="grant_type_id")
	private Long grantTypeId;

	@Column(name="govt_scheme_id")
	private Long govtSchemeId;
	
	@Column(name="financial_year_id")
	private Long financialYearId;
	
	@Column(name="inserted_by")
	private Long insertedBy;
	
	@Column(name="updated_by")
	private Long updatedBy;
	
	@Column(name="inserted_time")
	private Date insertedTime;
	
	@Column(name="updated_time")
	private Date updatedTime;
	
	@Column(name="is_deleted")
	private String isDeleted;

	public Long getFundSactionId() {
		return fundSactionId;
	}

	public void setFundSactionId(Long fundSactionId) {
		this.fundSactionId = fundSactionId;
	}

	public Long getLocationScopeId() {
		return locationScopeId;
	}

	public void setLocationScopeId(Long locationScopeId) {
		this.locationScopeId = locationScopeId;
	}

	public Long getLocationValue() {
		return locationValue;
	}

	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public Long getSactionAmount() {
		return sactionAmount;
	}

	public void setSactionAmount(Long sactionAmount) {
		this.sactionAmount = sactionAmount;
	}

	public String getGoNoDate() {
		return goNoDate;
	}

	public void setGoNoDate(String goNoDate) {
		this.goNoDate = goNoDate;
	}

	public Long getGrantTypeId() {
		return grantTypeId;
	}

	public void setGrantTypeId(Long grantTypeId) {
		this.grantTypeId = grantTypeId;
	}

	public Long getGovtSchemeId() {
		return govtSchemeId;
	}

	public void setGovtSchemeId(Long govtSchemeId) {
		this.govtSchemeId = govtSchemeId;
	}

	public Long getFinancialYearId() {
		return financialYearId;
	}

	public void setFinancialYearId(Long financialYearId) {
		this.financialYearId = financialYearId;
	}

	public Long getInsertedBy() {
		return insertedBy;
	}

	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getInsertedTime() {
		return insertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
}
