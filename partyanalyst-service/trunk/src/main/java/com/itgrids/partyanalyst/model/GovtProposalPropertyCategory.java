package com.itgrids.partyanalyst.model;

import java.io.Serializable;
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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "govt_proposal_property_category")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GovtProposalPropertyCategory extends BaseModel implements Serializable{
	
	private Long govtProposalPropertyCategoryId;
	private Long alertId;
	private Long govtProposalCategoryId;
	private String proposalAmount;
	private Long govtProposalStatusId;
	private Date insertedTime;
	private Long insertedBy;
	private Date updatedTime;
	private Long updatedBy;
	private String isDeleted;
	
	private Alert alert;
	private GovtProposalCategory govtProposalCategory;
	private GovtProposalStatus govtProposalStatus;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "govt_proposal_property_category_id", unique = true, nullable = false)
	public Long getGovtProposalPropertyCategoryId() {
		return govtProposalPropertyCategoryId;
	}
	public void setGovtProposalPropertyCategoryId(Long govtProposalPropertyCategoryId) {
		this.govtProposalPropertyCategoryId = govtProposalPropertyCategoryId;
	}
	
	@Column(name = "alert_id")
	public Long getAlertId() {
		return alertId;
	}
	public void setAlertId(Long alertId) {
		this.alertId = alertId;
	}
	
	@Column(name = "govt_proposal_category_id")
	public Long getGovtProposalCategoryId() {
		return govtProposalCategoryId;
	}
	public void setGovtProposalCategoryId(Long govtProposalCategoryId) {
		this.govtProposalCategoryId = govtProposalCategoryId;
	}
	
	@Column(name = "proposal_amount")
	public String getProposalAmount() {
		return proposalAmount;
	}
	public void setProposalAmount(String proposalAmount) {
		this.proposalAmount = proposalAmount;
	}
	
	@Column(name = "govt_proposal_status_id")
	public Long getGovtProposalStatusId() {
		return govtProposalStatusId;
	}
	public void setGovtProposalStatusId(Long govtProposalStatusId) {
		this.govtProposalStatusId = govtProposalStatusId;
	}
	
	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name = "inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	
	@Column(name = "updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	@Column(name = "updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Alert getAlert() {
		return alert;
	}
	public void setAlert(Alert alert) {
		this.alert = alert;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "govt_proposal_category_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public GovtProposalCategory getGovtProposalCategory() {
		return govtProposalCategory;
	}
	public void setGovtProposalCategory(GovtProposalCategory govtProposalCategory) {
		this.govtProposalCategory = govtProposalCategory;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "govt_proposal_status_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public GovtProposalStatus getGovtProposalStatus() {
		return govtProposalStatus;
	}
	public void setGovtProposalStatus(GovtProposalStatus govtProposalStatus) {
		this.govtProposalStatus = govtProposalStatus;
	}
	
     
}
