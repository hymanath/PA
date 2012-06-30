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
 * problem_likes entity.
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name="problem_likes")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProblemLikes extends BaseModel implements Serializable{

	private static final long serialVersionUID = -2925527953113411681L;
	private Long problemLikesId;
	private Problem problem;
	private User user;
	private String isLiked;
	private Date insertedTime;
	
	public ProblemLikes()
	{}
	
	public ProblemLikes(Problem problem,User user,String isLiked,Date insertedTime)
	{
		this.problem = problem;
		this.user = user;
		this.isLiked = isLiked;
		this.insertedTime = insertedTime;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="problem_likes_id", unique=true, nullable=false)
	public Long getProblemLikesId() {
		return problemLikesId;
	}

	public void setProblemLikesId(Long problemLikesId) {
		this.problemLikesId = problemLikesId;
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

	@Column(name = "is_liked", length = 10)
	public String getIsLiked() {
		return isLiked;
	}

	public void setIsLiked(String isLiked) {
		this.isLiked = isLiked;
	}

	@Column(name = "inserted_time", length = 10)
	public Date getInsertedTime() {
		return insertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
}
