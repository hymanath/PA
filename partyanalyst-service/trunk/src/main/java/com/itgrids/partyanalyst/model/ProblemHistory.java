package com.itgrids.partyanalyst.model;


import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "problem_history")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProblemHistory extends BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long problemHistoryId;
	private ProblemLocation problemLocation;
	private ProblemStatus problemStatus;
	private String comments;
	private String updatedTableColumn;
	private String previousContent;
	private ProblemSourceScope problemSourceScope;
	private Date dateUpdated;
	private String isDelete;
	private String isApproved;
	
	private Set<AssignedProblemProgress> assignedProblemProgresses = new HashSet<AssignedProblemProgress>(0); 
	private Set<ProblemFundSource> problemFundSources = new HashSet<ProblemFundSource>(0);
	private Set<CadreProblemDetails> cadreProblemDetails =  new HashSet<CadreProblemDetails>();
	private Set<ProblemFile> ProblemFile =  new HashSet<ProblemFile>();
	
	public ProblemHistory(){
		
	}
	
	public ProblemHistory(Long problemHistoryId){
		this.problemHistoryId = problemHistoryId;
	}

	public ProblemHistory(Long problemHistoryId, ProblemLocation problemLocation,
			ProblemStatus problemStatus, String comments,
			ProblemSourceScope problemSourceScope, Date dateUpdated,
			Set<AssignedProblemProgress> assignedProblemProgresses,
			Set<ProblemFundSource> problemFundSources,String isDelete,String isApproved,Set<CadreProblemDetails> cadreProblemDetails,Set<ProblemFile> ProblemFile) {
		this.isApproved = isApproved;
		this.isDelete = isDelete;
		this.problemHistoryId = problemHistoryId;
		this.problemLocation = problemLocation;
		this.problemStatus = problemStatus;
		this.comments = comments;
		this.problemSourceScope = problemSourceScope;
		this.dateUpdated = dateUpdated;
		this.assignedProblemProgresses = assignedProblemProgresses;
		this.problemFundSources = problemFundSources;
		this.cadreProblemDetails=cadreProblemDetails;
		this.ProblemFile=ProblemFile;
	}

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "problem_history_id", unique = true, nullable = false)
	public Long getProblemHistoryId() {
		return problemHistoryId;
	}

	public void setProblemHistoryId(Long problemHistoryId) {
		this.problemHistoryId = problemHistoryId;
	}

	@Column(name = "is_delete", length = 250)
	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "problem_location_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ProblemLocation getProblemLocation() {
		return problemLocation;
	}

	public void setProblemLocation(ProblemLocation problemLocation) {
		this.problemLocation = problemLocation;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "problem_status_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ProblemStatus getProblemStatus() {
		return problemStatus;
	}

	public void setProblemStatus(ProblemStatus problemStatus) {
		this.problemStatus = problemStatus;
	}

	@Column(name = "comments", length = 250)
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "problem_source_scope_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ProblemSourceScope getProblemSourceScope() {
		return problemSourceScope;
	}

	public void setProblemSourceScope(ProblemSourceScope problemSourceScope) {
		this.problemSourceScope = problemSourceScope;
	}

	@Column(name = "updated_date", length = 10)
	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "problemHistory")
	public Set<AssignedProblemProgress> getAssignedProblemProgresses() {
		return assignedProblemProgresses;
	}

	public void setAssignedProblemProgresses(
			Set<AssignedProblemProgress> assignedProblemProgresses) {
		this.assignedProblemProgresses = assignedProblemProgresses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "problemHistory")
	public Set<ProblemFundSource> getProblemFundSources() {
		return problemFundSources;
	}

	public void setProblemFundSources(Set<ProblemFundSource> problemFundSources) {
		this.problemFundSources = problemFundSources;
	}
	
	@Column(name = "updated_table_and_column", length = 10)
	public String getUpdatedTableColumn() {
		return updatedTableColumn;
	}

	public void setUpdatedTableColumn(String updatedTableColumn) {
		this.updatedTableColumn = updatedTableColumn;
	}

	@Column(name = "previous_data", length = 10)
	public String getPreviousContent() {
		return previousContent;
	}

	public void setPreviousContent(String previousContent) {
		this.previousContent = previousContent;
	}

	@Column(name = "is_approved", length = 5)
	public String getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "problemHistory")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<CadreProblemDetails> getCadreProblemDetails() {
		return cadreProblemDetails;
	}
	public void setCadreProblemDetails(Set<CadreProblemDetails> cadreProblemDetails) {
		this.cadreProblemDetails = cadreProblemDetails;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "problemHistory")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<ProblemFile> getProblemFile() {
		return ProblemFile;
	}

	public void setProblemFile(Set<ProblemFile> problemFile) {
		ProblemFile = problemFile;
	}

	
	
	
		
}
