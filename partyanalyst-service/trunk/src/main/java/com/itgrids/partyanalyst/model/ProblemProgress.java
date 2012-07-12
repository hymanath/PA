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

/**
 * problem_progress entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name="problem_progress")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProblemProgress extends BaseModel implements Serializable{

	private static final long serialVersionUID = -2868662680028211403L;
	
	private Long problemProgressId;
	private UserProblem userProblem;
	private ProblemActivity problemActivity;
	private Comment comment;
	private Visibility visibility;
	private Date insertedTime;
	private Date updatedTime;
	private String isDelete;
	private ProblemAssignedCadre problemAssignedCadre;
	private ProblemAssignedDepartment problemAssignedDepartment;
	private ClassifiedProblems classifiedProblems;
	
	public ProblemProgress()
	{}
	
	public ProblemProgress(UserProblem userProblem,ProblemActivity problemActivity,
			Comment comment,Visibility visibility,Date insertedTime,Date updatedTime,
			String isDelete)
	{
		this.userProblem = userProblem;
		this.problemActivity = problemActivity;
		this.comment = comment;
		this.visibility = visibility;
		this.insertedTime = insertedTime;
		this.updatedTime = updatedTime;
		this.isDelete = isDelete;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="problem_progress_id", unique=true, nullable=false)
	public Long getProblemProgressId() {
		return problemProgressId;
	}

	public void setProblemProgressId(Long problemProgressId) {
		this.problemProgressId = problemProgressId;
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
	@JoinColumn(name="problem_activity_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ProblemActivity getProblemActivity() {
		return problemActivity;
	}

	public void setProblemActivity(ProblemActivity problemActivity) {
		this.problemActivity = problemActivity;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="comment_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
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

	@Column(name = "is_delete", length = 10)
	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="problem_assigned_cadre_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ProblemAssignedCadre getProblemAssignedCadre() {
		return problemAssignedCadre;
	}

	public void setProblemAssignedCadre(ProblemAssignedCadre problemAssignedCadre) {
		this.problemAssignedCadre = problemAssignedCadre;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="problem_assigned_department_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ProblemAssignedDepartment getProblemAssignedDepartment() {
		return problemAssignedDepartment;
	}

	public void setProblemAssignedDepartment(
			ProblemAssignedDepartment problemAssignedDepartment) {
		this.problemAssignedDepartment = problemAssignedDepartment;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="classified_problems_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ClassifiedProblems getClassifiedProblems() {
		return classifiedProblems;
	}

	public void setClassifiedProblems(ClassifiedProblems classifiedProblems) {
		this.classifiedProblems = classifiedProblems;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
