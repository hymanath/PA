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

/**
 * problem_assigned_department entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */


@Entity
@Table(name="problem_assigned_department")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProblemAssignedDepartment extends BaseModel implements Serializable{

	private static final long serialVersionUID = 801857693855302320L;
	
	private Long problemAssignedDepartmentId;
	private UserProblem userProblem;
	private DepartmentOrganisation departmentOrganisation;
	private ProblemCompleteLocation departmentLocation;
	private String status;
	private Date insertedTime;
	private Date updatedTime;
	private Set<ProblemProgress> problemProgressSet = new HashSet<ProblemProgress>(0);
	public ProblemAssignedDepartment()
	{}
	
	public ProblemAssignedDepartment(UserProblem userProblem,DepartmentOrganisation departmentOrganisation,
			ProblemCompleteLocation departmentLocation,String status,Date insertedTime,Date updatedTime)
	{
		this.userProblem = userProblem;
		this.departmentOrganisation = departmentOrganisation;
		this.departmentLocation = departmentLocation;
		this.status = status;
		this.insertedTime = insertedTime;
		this.updatedTime = updatedTime;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="problem_assigned_department_id", unique=true, nullable=false)
	public Long getProblemAssignedDepartmentId() {
		return problemAssignedDepartmentId;
	}

	public void setProblemAssignedDepartmentId(Long problemAssignedDepartmentId) {
		this.problemAssignedDepartmentId = problemAssignedDepartmentId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_problem_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserProblem getUserProblem() {
		return userProblem;
	}

	public void setUserProblem(UserProblem userProblem) {
		this.userProblem = userProblem;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="department_organisation_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public DepartmentOrganisation getDepartmentOrganisation() {
		return departmentOrganisation;
	}

	public void setDepartmentOrganisation(
			DepartmentOrganisation departmentOrganisation) {
		this.departmentOrganisation = departmentOrganisation;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="department_location_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ProblemCompleteLocation getDepartmentLocation() {
		return departmentLocation;
	}

	public void setDepartmentLocation(ProblemCompleteLocation departmentLocation) {
		this.departmentLocation = departmentLocation;
	}

	@Column(name = "status", length = 10)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "inserted_time", length = 10)
	public Date getInsertedTime() {
		return insertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}

	@Column(name = "updated_time", length = 10)
	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "problemAssignedDepartment")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<ProblemProgress> getProblemProgressSet() {
		return problemProgressSet;
	}

	public void setProblemProgressSet(Set<ProblemProgress> problemProgressSet) {
		this.problemProgressSet = problemProgressSet;
	}
	
}
