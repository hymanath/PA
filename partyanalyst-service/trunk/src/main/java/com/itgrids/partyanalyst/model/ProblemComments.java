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
 * problem_comments entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name="problem_comments")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProblemComments extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1878678190747959789L;
	
	private Long problemCommentsId;
	private Problem problem;
	private Comment comment;
	private User user;
	private String isApproved;
	private String isDelete;
	private Date insertedTime;
	
	public ProblemComments()
	{}
	
	public ProblemComments(Problem problem,Comment comment,User user,String isApproved,
			String isDelete,Date insertedTime)
	{
		this.problem = problem;
		this.comment = comment;
		this.user = user;
		this.isApproved = isApproved;
		this.isDelete = isDelete;
		this.insertedTime = insertedTime;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="problem_comments_id", unique=true, nullable=false)
	public Long getProblemCommentsId() {
		return problemCommentsId;
	}

	public void setProblemCommentsId(Long problemCommentsId) {
		this.problemCommentsId = problemCommentsId;
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
	@JoinColumn(name="user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "is_approved", length = 10)
	public String getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}

	@Column(name = "is_delete", length = 10)
	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	@Column(name = "inserted_time", length = 10)
	public Date getInsertedTime() {
		return insertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	

}
