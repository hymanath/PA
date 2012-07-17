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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "problem_rating")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class ProblemRating extends BaseModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2514253494120632401L;
	private Long problemRatingId;
	private Problem problem;
	private User user;
	private Integer rating;
	private Date insertedTime;
	private Date updatedTime;
	
	public ProblemRating()
	{}
		
	public ProblemRating(Problem problem,User user,Integer rating,Date insertedTime,Date updatedTime)
	{
		this.problem = problem;
		this.user = user;
		this.rating = rating;
		this.insertedTime = insertedTime;
		this.updatedTime = updatedTime;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="problem_rating_id", unique=true, nullable=false)
	public Long getProblemRatingId() {
		return problemRatingId;
	}
	public void setProblemRatingId(Long problemRatingId) {
		this.problemRatingId = problemRatingId;
	}
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="problem_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Problem getProblem() {
		return problem;
	}
	public void setProblem(Problem problem) {
		this.problem = problem;
	}
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Column(name="rating")
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="inserted_time", length = 10)
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="updated_time", length = 10)
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	
}
