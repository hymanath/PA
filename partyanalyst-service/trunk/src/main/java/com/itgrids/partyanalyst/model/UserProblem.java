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
 * user_problem entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */


@Entity
@Table(name="user_problem")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserProblem extends BaseModel implements Serializable{

	private static final long serialVersionUID = -3233176421405683819L;
	
	private Long userProblemId;
	private Problem problem;
	private User user;
	private Visibility visibility;
	private String isOwner;
	private Date insertedTime;
	private Date updatedTime;
	
	private Set<ProblemProgress> problemProgresses = new HashSet<ProblemProgress>(0);
	private Set<ProblemAssignedDepartment> problemAssignedDepartments = new HashSet<ProblemAssignedDepartment>(0);
	private Set<ProblemAssignedCadre> problemAssignedCadres = new HashSet<ProblemAssignedCadre>(0);
	private Set<ClassifiedProblems> classifiedProblems = new HashSet<ClassifiedProblems>(0);
	
	public UserProblem()
	{}
	
	public UserProblem(Problem problem,User user,Visibility visibility,String isOwner,
			Date insertedTime,Date updatedTime)
	{
		this.problem = problem;
		this.user = user;
		this.visibility = visibility;
		this.isOwner = isOwner;
		this.insertedTime = insertedTime;
		this.updatedTime = updatedTime;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_problem_id", unique=true, nullable=false)
	public Long getUserProblemId() {
		return userProblemId;
	}

	public void setUserProblemId(Long userProblemId) {
		this.userProblemId = userProblemId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="problem_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="visibility_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Visibility getVisibility() {
		return visibility;
	}

	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}

	@Column(name = "is_owner", length = 10)
	public String getIsOwner() {
		return isOwner;
	}

	public void setIsOwner(String isOwner) {
		this.isOwner = isOwner;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userProblem")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<ProblemProgress> getProblemProgresses() {
		return problemProgresses;
	}

	public void setProblemProgresses(Set<ProblemProgress> problemProgresses) {
		this.problemProgresses = problemProgresses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userProblem")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<ProblemAssignedDepartment> getProblemAssignedDepartments() {
		return problemAssignedDepartments;
	}

	public void setProblemAssignedDepartments(
			Set<ProblemAssignedDepartment> problemAssignedDepartments) {
		this.problemAssignedDepartments = problemAssignedDepartments;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userProblem")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<ProblemAssignedCadre> getProblemAssignedCadres() {
		return problemAssignedCadres;
	}

	public void setProblemAssignedCadres(
			Set<ProblemAssignedCadre> problemAssignedCadres) {
		this.problemAssignedCadres = problemAssignedCadres;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userProblem")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<ClassifiedProblems> getClassifiedProblems() {
		return classifiedProblems;
	}

	public void setClassifiedProblems(Set<ClassifiedProblems> classifiedProblems) {
		this.classifiedProblems = classifiedProblems;
	}

}
