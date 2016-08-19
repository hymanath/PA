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
@Table(name="application_document")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ApplicationDocument extends BaseModel implements Serializable{
	
	private Long applicationDocumentId;
	private Long nominationPostCandidateId;
	private String filePath;
	private String isDeleted;
	private Long nominatedPostApplicationId;
	private Date insertedDate;
	
	private NominationPostCandidate nominationPostCandidate;
	private NominatedPostApplication nominatedPostApplication;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "application_document_id", unique = true, nullable = false)
	public Long getApplicationDocumentId() {
		return applicationDocumentId;
	}

	public void setApplicationDocumentId(Long applicationDocumentId) {
		this.applicationDocumentId = applicationDocumentId;
	}
	@Column(name = "nomination_post_candidate_id")
	public Long getNominationPostCandidateId() {
		return nominationPostCandidateId;
	}
	public void setNominationPostCandidateId(Long nominationPostCandidateId) {
		this.nominationPostCandidateId = nominationPostCandidateId;
	}
	@Column(name = "file_path")
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	@Column(name = "inserted_date")
	public Date getInsertedDate() {
		return insertedDate;
	}
	public void setInsertedDate(Date insertedDate) {
		this.insertedDate = insertedDate;
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

	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@Column(name = "nominated_post_application_id")
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
