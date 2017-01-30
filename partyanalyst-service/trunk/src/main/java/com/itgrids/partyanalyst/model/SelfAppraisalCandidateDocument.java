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
@Table(name="self_appraisal_candidate_document")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SelfAppraisalCandidateDocument {
    /*
     * Author : santosh
     */
	private Long selfAppraisalCandidateDocumentId;
	private Long selfAppraisalCandidateId;
	private Date tourDate;
	private String documentPath;
	private Date insertedTime;
	private Long selfAppraisalToursMonthId;
	
	private Long insertedBy;
	private String isDeleted;
	
	private SelfAppraisalCandidate selfAppraisalCandidate;
	private User insertedUser;
	/*private User updatedUser;*/
	private SelfAppraisalToursMonth selfAppraisalToursMonth;
	
	private Long tdpCadreId;
	private TdpCadre tdpCadre;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="self_appraisal_candidate_document_id", unique=true, nullable=false)
	public Long getSelfAppraisalCandidateDocumentId() {
		return selfAppraisalCandidateDocumentId;
	}
	public void setSelfAppraisalCandidateDocumentId(
			Long selfAppraisalCandidateDocumentId) {
		this.selfAppraisalCandidateDocumentId = selfAppraisalCandidateDocumentId;
	}
	@Column(name="self_appraisal_candidate_id")
	public Long getSelfAppraisalCandidateId() {
		return selfAppraisalCandidateId;
	}
	public void setSelfAppraisalCandidateId(Long selfAppraisalCandidateId) {
		this.selfAppraisalCandidateId = selfAppraisalCandidateId;
	}
	@Column(name="tour_date")
	public Date getTourDate() {
		return tourDate;
	}
	public void setTourDate(Date tourDate) {
		this.tourDate = tourDate;
	}
	@Column(name="document_path")
	public String getDocumentPath() {
		return documentPath;
	}
	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@Column(name="inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name="self_appraisal_tours_month_id")
	public Long getSelfAppraisalToursMonthId() {
		return selfAppraisalToursMonthId;
	}
	public void setSelfAppraisalToursMonthId(Long selfAppraisalToursMonthId) {
		this.selfAppraisalToursMonthId = selfAppraisalToursMonthId;
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
	@JoinColumn(name="inserted_by",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getInsertedUser() {
		return insertedUser;
	}
	public void setInsertedUser(User insertedUser) {
		this.insertedUser = insertedUser;
	}
/*	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="updated_by",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUpdatedUser() {
		return updatedUser;
	}
	public void setUpdatedUser(User updatedUser) {
		this.updatedUser = updatedUser;
	}*/
	
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
