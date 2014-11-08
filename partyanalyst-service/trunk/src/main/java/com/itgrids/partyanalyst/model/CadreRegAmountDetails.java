package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;


@Entity
@Table(name = "cadre_reg_amount_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadreRegAmountDetails extends BaseModel implements Serializable {
	
	private Long CadreRegAmountDetailsId;
	private CadreRegAmountFile cadreRegAmountFile;
	private CadreSurveyUser cadreSurveyUser;
	private Long amount;
	private String branch;
	
	private Long cadreRegAmountFileId;
	private Long cadreSurveyUserId;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "cadre_reg_amount_details_id", unique = true, nullable = false)
	public Long getCadreRegAmountDetailsId() {
		return CadreRegAmountDetailsId;
	}
	public void setCadreRegAmountDetailsId(Long cadreRegAmountDetailsId) {
		CadreRegAmountDetailsId = cadreRegAmountDetailsId;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "cadre_reg_amount_file_id",insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreRegAmountFile getCadreRegAmountFile() {
		return cadreRegAmountFile;
	}
	public void setCadreRegAmountFile(CadreRegAmountFile cadreRegAmountFile) {
		this.cadreRegAmountFile = cadreRegAmountFile;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "cadre_survey_user_id",insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreSurveyUser getCadreSurveyUser() {
		return cadreSurveyUser;
	}
	public void setCadreSurveyUser(CadreSurveyUser cadreSurveyUser) {
		this.cadreSurveyUser = cadreSurveyUser;
	}
	
	@Column(name = "amount")
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	
	@Column(name = "branch")
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	
	@Column(name = "cadre_reg_amount_file_id")
	public Long getCadreRegAmountFileId() {
		return cadreRegAmountFileId;
	}
	public void setCadreRegAmountFileId(Long cadreRegAmountFileId) {
		this.cadreRegAmountFileId = cadreRegAmountFileId;
	}
	
	@Column(name = "cadre_survey_user_id")
	public Long getCadreSurveyUserId() {
		return cadreSurveyUserId;
	}
	public void setCadreSurveyUserId(Long cadreSurveyUserId) {
		this.cadreSurveyUserId = cadreSurveyUserId;
	}
	
	
	

}
