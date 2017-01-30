package com.itgrids.partyanalyst.model;

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
@Table(name="self_appraisal_candidate_details_new")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SelfAppraisalCandidateDetailsNew {

	private Long selfAppraisalCandidateDetailsNewId;
	private Long selfAppraisalCandidateId;
	private Long selfAppraisalDesignationId;
	private Long selfAppraisalTourCategoryId;
	private Long tourTypeId;
	private Long tourDays;
	private Long selfAppraisalToursMonthId;
	private String remarks;
	private Long insertedBy;
	private Long updatedBy;
	private Date insertedTime;
	private Date updatedTime;
	
	private SelfAppraisalCandidate selfAppraisalCandidate;
	private SelfAppraisalDesignation selfAppraisalDesignation;
	private SelfAppraisalTourCategory selfAppraisalTourCategory;
	private TourType tourType;
	private SelfAppraisalToursMonth selfAppraisalToursMonth;
	private User insertedUser;
	private User updatedUser;
	
	private String isDeleted;
	private Long tdpCadreId;
	private TdpCadre tdpCadre;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="self_appraisal_candidate_details_new_id", unique=true, nullable=false)
	public Long getSelfAppraisalCandidateDetailsNewId() {
		return selfAppraisalCandidateDetailsNewId;
	}
	public void setSelfAppraisalCandidateDetailsNewId(
			Long selfAppraisalCandidateDetailsNewId) {
		this.selfAppraisalCandidateDetailsNewId = selfAppraisalCandidateDetailsNewId;
	}
	@Column(name="self_appraisal_candidate_id")
	public Long getSelfAppraisalCandidateId() {
		return selfAppraisalCandidateId;
	}
	public void setSelfAppraisalCandidateId(Long selfAppraisalCandidateId) {
		this.selfAppraisalCandidateId = selfAppraisalCandidateId;
	}
	@Column(name="self_appraisal_designation_id")
	public Long getSelfAppraisalDesignationId() {
		return selfAppraisalDesignationId;
	}
	public void setSelfAppraisalDesignationId(Long selfAppraisalDesignationId) {
		this.selfAppraisalDesignationId = selfAppraisalDesignationId;
	}
	@Column(name="self_appraisal_tour_category_id")
	public Long getSelfAppraisalTourCategoryId() {
		return selfAppraisalTourCategoryId;
	}
	public void setSelfAppraisalTourCategoryId(Long selfAppraisalTourCategoryId) {
		this.selfAppraisalTourCategoryId = selfAppraisalTourCategoryId;
	}
	@Column(name="tour_type_id")
	public Long getTourTypeId() {
		return tourTypeId;
	}
	public void setTourTypeId(Long tourTypeId) {
		this.tourTypeId = tourTypeId;
	}
	@Column(name="tour_days")
	public Long getTourDays() {
		return tourDays;
	}
	public void setTourDays(Long tourDays) {
		this.tourDays = tourDays;
	}
	@Column(name="self_appraisal_tours_month_id")
	public Long getSelfAppraisalToursMonthId() {
		return selfAppraisalToursMonthId;
	}
	public void setSelfAppraisalToursMonthId(Long selfAppraisalToursMonthId) {
		this.selfAppraisalToursMonthId = selfAppraisalToursMonthId;
	}
	@Column(name="remarks")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="self_appraisal_candidate_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SelfAppraisalCandidate getSelfAppraisalCandidate() {
		return selfAppraisalCandidate;
	}
	public void setSelfAppraisalCandidate(
			SelfAppraisalCandidate selfAppraisalCandidate) {
		this.selfAppraisalCandidate = selfAppraisalCandidate;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="self_appraisal_designation_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SelfAppraisalDesignation getSelfAppraisalDesignation() {
		return selfAppraisalDesignation;
	}
	public void setSelfAppraisalDesignation(
			SelfAppraisalDesignation selfAppraisalDesignation) {
		this.selfAppraisalDesignation = selfAppraisalDesignation;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="self_appraisal_tour_category_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SelfAppraisalTourCategory getSelfAppraisalTourCategory() {
		return selfAppraisalTourCategory;
	}
	public void setSelfAppraisalTourCategory(
			SelfAppraisalTourCategory selfAppraisalTourCategory) {
		this.selfAppraisalTourCategory = selfAppraisalTourCategory;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="tour_type_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TourType getTourType() {
		return tourType;
	}
	public void setTourType(TourType tourType) {
		this.tourType = tourType;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="self_appraisal_tours_month_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SelfAppraisalToursMonth getSelfAppraisalToursMonth() {
		return selfAppraisalToursMonth;
	}
	public void setSelfAppraisalToursMonth(
			SelfAppraisalToursMonth selfAppraisalToursMonth) {
		this.selfAppraisalToursMonth = selfAppraisalToursMonth;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="inserted_by",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getInsertedUser() {
		return insertedUser;
	}
	public void setInsertedUser(User insertedUser) {
		this.insertedUser = insertedUser;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="updated_by",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUpdatedUser() {
		return updatedUser;
	}
	public void setUpdatedUser(User updatedUser) {
		this.updatedUser = updatedUser;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name="tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="tdp_cadre_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}
	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}
	
	
	
	
}
