package com.itgrids.partyanalyst.model;

import java.io.Serializable;
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

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

import com.itgrids.partyanalyst.model.BaseModel;

@Entity
@Table(name = "problem_source_scope_concerned_department")
public class ProblemSourceScopeConcernedDepartment extends BaseModel implements Serializable{

	
	private Long problemSourceScopeConcernedDepartmentId;
	private ProblemSourceScope problemSourceScopeId;
	private String department;
	private String description;
	private Set<AssignedProblemProgress> assignedProblemProgresses = new HashSet<AssignedProblemProgress>(0);
	
	public ProblemSourceScopeConcernedDepartment(){
		
	}
	
	public ProblemSourceScopeConcernedDepartment(Long problemSourceScopeConcernedDepartmentId) {
		this.problemSourceScopeConcernedDepartmentId = problemSourceScopeConcernedDepartmentId;
	}
	
	public ProblemSourceScopeConcernedDepartment(Set<AssignedProblemProgress> assignedProblemProgresses,
			ProblemSourceScope problemSourceScopeId, String department,
			String description) {
		this.assignedProblemProgresses = assignedProblemProgresses;
		this.problemSourceScopeId = problemSourceScopeId;
		this.department = department;
		this.description = description;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_catagory_id", unique = true, nullable = false)
	public Long getProblemSourceScopeConcernedDepartmentId() {
		return problemSourceScopeConcernedDepartmentId;
	}

	public void setProblemSourceScopeConcernedDepartmentId(
			Long problemSourceScopeConcernedDepartmentId) {
		this.problemSourceScopeConcernedDepartmentId = problemSourceScopeConcernedDepartmentId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "problemSourceScopeConcernedDepartment")
	public Set<AssignedProblemProgress> getAssignedProblemProgresses() {
		return assignedProblemProgresses;
	}

	public void setAssignedProblemProgresses(
			Set<AssignedProblemProgress> assignedProblemProgresses) {
		this.assignedProblemProgresses = assignedProblemProgresses;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tehsil_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ProblemSourceScope getProblemSourceScopeId() {
		return problemSourceScopeId;
	}

	public void setProblemSourceScopeId(ProblemSourceScope problemSourceScopeId) {
		this.problemSourceScopeId = problemSourceScopeId;
	}

	@Column(name = "department", length = 50)
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Column(name = "description", length = 50)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}