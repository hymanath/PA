package com.itgrids.model;

import java.sql.Date;

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

@Entity
@Table(name = "fund_sanction")
public class FundSanction{
		
	private static final long serialVersionUID = -2853930539938433902L;

	private Long fundSactionId;
	private String workName;
	private Long sactionAmount;
	private String goNoDate;
	private Long grantTypeId;
	private Long govtSchemeId;
	private Long financialYearId;
	private Long insertedBy;
	private Long updatedBy;
	private Date insertedTime;
	private Date updatedTime;
	private String isDeleted;
	private Long departmentId;
	private Long govtOrderId;
	private Long subProgramId;
	
	private FinancialYear financialYear;
	private GrantType grantType;
	private GovtScheme govtScheme;
	private Department department;
	private GovtOrder govtOrder;
	private SubProgram subProgram;

	@Id
	@Column(name="fund_sanction_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getFundSactionId() {
		return fundSactionId;
	}
	public void setFundSactionId(Long fundSactionId) {
		this.fundSactionId = fundSactionId;
	}
	@Column(name="work_name")
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}

	@Column(name="saction_amount")
	public Long getSactionAmount() {
		return sactionAmount;
	}
	public void setSactionAmount(Long sactionAmount) {
		this.sactionAmount = sactionAmount;
	}

	@Column(name="go_no_date")
	public String getGoNoDate() {
		return goNoDate;
	}
	public void setGoNoDate(String goNoDate) {
		this.goNoDate = goNoDate;
	}

	@Column(name="grant_type_id")
	public Long getGrantTypeId() {
		return grantTypeId;
	}
	public void setGrantTypeId(Long grantTypeId) {
		this.grantTypeId = grantTypeId;
	}

	@Column(name="govt_scheme_id")
	public Long getGovtSchemeId() {
		return govtSchemeId;
	}
	public void setGovtSchemeId(Long govtSchemeId) {
		this.govtSchemeId = govtSchemeId;
	}

	@Column(name="financial_year_id")
	public Long getFinancialYearId() {
		return financialYearId;
	}
	public void setFinancialYearId(Long financialYearId) {
		this.financialYearId = financialYearId;
	}

	@Column(name="inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}

	@Column(name="updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}

	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Column(name="department_id")
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "financial_year_id", insertable = false, updatable = false)
	public FinancialYear getFinancialYear() {
		return financialYear;
	}
	public void setFinancialYear(FinancialYear financialYear) {
		this.financialYear = financialYear;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "grant_type_id", insertable = false, updatable = false)
	public GrantType getGrantType() {
		return grantType;
	}
	public void setGrantType(GrantType grantType) {
		this.grantType = grantType;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "govt_scheme_id", insertable = false, updatable = false)
	public GovtScheme getGovtScheme() {
		return govtScheme;
	}
	public void setGovtScheme(GovtScheme govtScheme) {
		this.govtScheme = govtScheme;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id", insertable = false, updatable = false)
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	@Column(name="govt_order_id")
	public Long getGovtOrderId() {
		return govtOrderId;
	}
	public void setGovtOrderId(Long govtOrderId) {
		this.govtOrderId = govtOrderId;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "govt_order_id", insertable = false, updatable = false)
	public GovtOrder getGovtOrder() {
		return govtOrder;
	}
	public void setGovtOrder(GovtOrder govtOrder) {
		this.govtOrder = govtOrder;
	}
	@Column(name="sub_program_id")
	public Long getSubProgramId() {
		return subProgramId;
	}
	public void setSubProgramId(Long subProgramId) {
		this.subProgramId = subProgramId;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "sub_program_id", insertable = false, updatable = false)
	public SubProgram getSubProgram() {
		return subProgram;
	}
	public void setSubProgram(SubProgram subProgram) {
		this.subProgram = subProgram;
	}
	
	
	
}
