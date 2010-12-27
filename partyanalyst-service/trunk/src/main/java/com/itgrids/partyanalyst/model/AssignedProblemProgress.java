package com.itgrids.partyanalyst.model;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "assigned_problem_progress")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AssignedProblemProgress extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long assignedProblemProgressId;
	private String description;
	private String concernedPersonName;
	private String designation;
	private String contactNo;
	private String progressLevel;
	private Date performedDate;
	private ProblemHistory problemHistory; 
	private ProblemSourceScopeConcernedDepartment problemSourceScopeConcernedDepartment;
	
	public AssignedProblemProgress(){
		
	}
	
	public AssignedProblemProgress(Long assignedProblemProgressId){
		this.assignedProblemProgressId = assignedProblemProgressId;
	}

	public AssignedProblemProgress(String description,
			String progressLevel,
			Date performedDate,
			ProblemHistory problemHistory,
			ProblemSourceScopeConcernedDepartment problemSourceScopeConcernedDepartment) {
		this.description = description;
		this.progressLevel = progressLevel;
		this.performedDate = performedDate;
		this.problemHistory = problemHistory;
		this.problemSourceScopeConcernedDepartment = problemSourceScopeConcernedDepartment;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "assigned_problem_progress_id", unique = true, nullable = false)
	public Long getAssignedProblemProgressId() {
		return assignedProblemProgressId;
	}

	public void setAssignedProblemProgressId(Long assignedProblemProgressId) {
		this.assignedProblemProgressId = assignedProblemProgressId;
	}

	@Column(name = "description", length = 250)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "progress_level", length = 50)
	public String getProgressLevel() {
		return progressLevel;
	}

	public void setProgressLevel(String progressLevel) {
		this.progressLevel = progressLevel;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "performed_date", length = 50)
	public Date getPerformedDate() {
		return performedDate;
	}

	public void setPerformedDate(Date performedDate) {
		this.performedDate = performedDate;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "problem_history_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ProblemHistory getProblemHistory() {
		return problemHistory;
	}

	public void setProblemHistory(ProblemHistory problemHistory) {
		this.problemHistory = problemHistory;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "problem_source_scope_concerned_department_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ProblemSourceScopeConcernedDepartment getProblemSourceScopeConcernedDepartment() {
		return problemSourceScopeConcernedDepartment;
	}

	
	public void setProblemSourceScopeConcernedDepartment(
			ProblemSourceScopeConcernedDepartment problemSourceScopeConcernedDepartment) {
		this.problemSourceScopeConcernedDepartment = problemSourceScopeConcernedDepartment;
	}
	
	@Column(name = "concerned_person_name", length = 250)
	public String getConcernedPersonName() {
		return concernedPersonName;
	}

	public void setConcernedPersonName(String concernedPersonName) {
		this.concernedPersonName = concernedPersonName;
	}

	@Column(name = "designation", length = 25)
	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@Column(name = "contact_no", length = 25)
	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	
	

}
