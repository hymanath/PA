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

@Entity
@Table(name = "nrega_work_expenditure")
public class NregaWorkExpenditure {

	private Long nregaWorkExpenditureId;
	private Long nregaFinancialYearId;
	private Long nregaWorkTypeId;
	private Long wageExpenditure;
	private Long materialExpenditure;
	private Long totalxpenditure;
	private Long groundedWorks;
	private Long inprogressWorks;
	private Long completedWorks;
	private String isDeleted;
	
	private NregaFinancialYear nregaFinancialYear;
	private NregaWorkType nregaWorkType;
	
	@Id
	@Column(name="nrega_work_expenditure_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getNregaWorkExpenditureId() {
		return nregaWorkExpenditureId;
	}
	public void setNregaWorkExpenditureId(Long nregaWorkExpenditureId) {
		this.nregaWorkExpenditureId = nregaWorkExpenditureId;
	}
	
	@Column(name="nrega_financial_year_id")
	public Long getNregaFinancialYearId() {
		return nregaFinancialYearId;
	}
	public void setNregaFinancialYearId(Long nregaFinancialYearId) {
		this.nregaFinancialYearId = nregaFinancialYearId;
	}
	
	@Column(name="nrega_work_type_id")
	public Long getNregaWorkTypeId() {
		return nregaWorkTypeId;
	}
	public void setNregaWorkTypeId(Long nregaWorkTypeId) {
		this.nregaWorkTypeId = nregaWorkTypeId;
	}
	
	@Column(name="wage_expenditure")
	public Long getWageExpenditure() {
		return wageExpenditure;
	}
	public void setWageExpenditure(Long wageExpenditure) {
		this.wageExpenditure = wageExpenditure;
	}
	
	@Column(name="material_expenditure")
	public Long getMaterialExpenditure() {
		return materialExpenditure;
	}
	public void setMaterialExpenditure(Long materialExpenditure) {
		this.materialExpenditure = materialExpenditure;
	}
	
	@Column(name="total_expenditure")
	public Long getTotalxpenditure() {
		return totalxpenditure;
	}
	public void setTotalxpenditure(Long totalxpenditure) {
		this.totalxpenditure = totalxpenditure;
	}
	
	@Column(name="grounded_works")
	public Long getGroundedWorks() {
		return groundedWorks;
	}
	public void setGroundedWorks(Long groundedWorks) {
		this.groundedWorks = groundedWorks;
	}
	
	@Column(name="inprogress_works")
	public Long getInprogressWorks() {
		return inprogressWorks;
	}
	public void setInprogressWorks(Long inprogressWorks) {
		this.inprogressWorks = inprogressWorks;
	}
	
	@Column(name="completed_works")
	public Long getCompletedWorks() {
		return completedWorks;
	}
	public void setCompletedWorks(Long completedWorks) {
		this.completedWorks = completedWorks;
	}
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "nrega_financial_year_id", insertable = false, updatable = false)
	public NregaFinancialYear getNregaFinancialYear() {
		return nregaFinancialYear;
	}
	public void setNregaFinancialYear(NregaFinancialYear nregaFinancialYear) {
		this.nregaFinancialYear = nregaFinancialYear;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "nrega_work_type_id", insertable = false, updatable = false)
	public NregaWorkType getNregaWorkType() {
		return nregaWorkType;
	}
	public void setNregaWorkType(NregaWorkType nregaWorkType) {
		this.nregaWorkType = nregaWorkType;
	}
}
