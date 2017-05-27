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
@Table(name = "meekosam_annual_income")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MeekosamAnnualIncome extends BaseModel implements java.io.Serializable {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long meekosamAnnualIncomeId;
	private String meekosamAnnualIncome;
	private String isActive;
	 
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "meekosam_annual_income_id", unique = true, nullable = false)
	 public Long getMeekosamAnnualIncomeId() {
		return meekosamAnnualIncomeId;
	 }
	 public void setMeekosamAnnualIncomeId(Long meekosamAnnualIncomeId) {
		this.meekosamAnnualIncomeId = meekosamAnnualIncomeId;
	 }
	 
	 @Column(name="meekosam_annual_income")
	 public String getMeekosamAnnualIncome() {
	 	return meekosamAnnualIncome;
	 }
	 public void setMeekosamAnnualIncome(String meekosamAnnualIncome) {
		this.meekosamAnnualIncome = meekosamAnnualIncome;
	 }
	 
	 @Column(name="is_active")
	 public String getIsActive() {
		return isActive;
	 }
	 public void setIsActive(String isActive) {
		this.isActive = isActive;
	 }
	 
	 
	 
	
}
