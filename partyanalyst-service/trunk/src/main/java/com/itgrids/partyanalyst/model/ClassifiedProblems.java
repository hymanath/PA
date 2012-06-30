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
 * classified_problems entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name="classified_problems")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ClassifiedProblems extends BaseModel implements Serializable{

	private static final long serialVersionUID = 901837510307079217L;
	
	private Long classifiedProblemsId;
	private UserProblem userProblem;
	private ProblemClassification problemClassification;
	private Date insertedTime;
	private Date updatedTime;
	
	public ClassifiedProblems()
	{}
	
	public ClassifiedProblems(UserProblem userProblem,ProblemClassification problemClassification,
			Date insertedTime,Date updatedTime)
	{
		this.userProblem = userProblem;
		this.problemClassification = problemClassification;
		this.insertedTime = insertedTime;
		this.updatedTime = updatedTime;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="classified_problems_id", unique=true, nullable=false)
	public Long getClassifiedProblemsId() {
		return classifiedProblemsId;
	}

	public void setClassifiedProblemsId(Long classifiedProblemsId) {
		this.classifiedProblemsId = classifiedProblemsId;
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
	@JoinColumn(name="problem_classification_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ProblemClassification getProblemClassification() {
		return problemClassification;
	}

	public void setProblemClassification(ProblemClassification problemClassification) {
		this.problemClassification = problemClassification;
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
	
	
}
