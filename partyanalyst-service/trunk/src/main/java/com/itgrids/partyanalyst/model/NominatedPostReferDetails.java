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
@Table(name = "nominated_post_refer_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NominatedPostReferDetails extends BaseModel implements Serializable{

	private Long nominatedPostReferDetailsId;
	private Long nominationPostCandidateId;
	private Long nominatedPostApplicationId;
	private Long referCadreId;
	private Long insertedBy;
	private Date insertedTime;
	private Long updatedBy;
	private Date updatedTime;
	private String isDeleted;
	
	private NominationPostCandidate nominationPostCandidate;
	private NominatedPostApplication nominatedPostApplication;
	private TdpCadre referCadre;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "nominated_post_refer_details_id", unique = true, nullable = false)
	public Long getNominatedPostReferDetailsId() {
		return nominatedPostReferDetailsId;
	}
	public void setNominatedPostReferDetailsId(Long nominatedPostReferDetailsId) {
		this.nominatedPostReferDetailsId = nominatedPostReferDetailsId;
	}
	
	@Column(name = "nomination_post_candidate_id")
	public Long getNominationPostCandidateId() {
		return nominationPostCandidateId;
	}
	public void setNominationPostCandidateId(Long nominationPostCandidateId) {
		this.nominationPostCandidateId = nominationPostCandidateId;
	}
	
	@Column(name="refer_cadre_id")
	public Long getReferCadreId() {
		return referCadreId;
	}
	public void setReferCadreId(Long referCadreId) {
		this.referCadreId = referCadreId;
	}
	
	@Column(name="inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name="updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
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
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="nomination_post_candidate_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public NominationPostCandidate getNominationPostCandidate() {
		return nominationPostCandidate;
	}
	public void setNominationPostCandidate(
			NominationPostCandidate nominationPostCandidate) {
		this.nominationPostCandidate = nominationPostCandidate;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="refer_cadre_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadre getReferCadre() {
		return referCadre;
	}
	public void setReferCadre(TdpCadre referCadre) {
		this.referCadre = referCadre;
	}
	
	@Column(name="nominated_post_application_id")
	public Long getNominatedPostApplicationId() {
		return nominatedPostApplicationId;
	}
	public void setNominatedPostApplicationId(Long nominatedPostApplicationId) {
		this.nominatedPostApplicationId = nominatedPostApplicationId;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="nominated_post_application_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public NominatedPostApplication getNominatedPostApplication() {
		return nominatedPostApplication;
	}
	public void setNominatedPostApplication(
			NominatedPostApplication nominatedPostApplication) {
		this.nominatedPostApplication = nominatedPostApplication;
	}
	
	
	
}
