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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;


@Entity
@Table(name="opinion_poll_comments")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class OpinionPollComments extends BaseModel implements Serializable{
	
	private Long opinionPollCommentsId;
	private OpinionPoll opinionPoll;
	private Comment comment;
	private User user;
	private String firstName;
	private String lastName;
	private Date insertedTime;
	private String isDelete;
	private String isApproved;
	
	private Long opinionPollId;
	private Long commentId;
	private Long userId;
	
	
	
	public OpinionPollComments(OpinionPoll opinionPoll, Comment comment,
			User user, String firstName, String lastName, Date insertedTime,
			String isDelete, String isApproved, Long opinionPollId,
			Long commentId, Long userId) {
		super();
		this.opinionPoll = opinionPoll;
		this.comment = comment;
		this.user = user;
		this.firstName = firstName;
		this.lastName = lastName;
		this.insertedTime = insertedTime;
		this.isDelete = isDelete;
		this.isApproved = isApproved;
		this.opinionPollId = opinionPollId;
		this.commentId = commentId;
		this.userId = userId;
	}

	public OpinionPollComments(){}
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "opinion_poll_comments_id", unique = true, nullable = false)
	public Long getOpinionPollCommentsId() {
		return opinionPollCommentsId;
	}

	public void setOpinionPollCommentsId(Long opinionPollCommentsId) {
		this.opinionPollCommentsId = opinionPollCommentsId;
	}
	
	
	@Column(name="first_name" , length =30)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	
	@Column(name="last_name" , length =30)

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name="is_delete" , length = 10)
	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	
	
	@Column(name="is_approved" ,length = 10)
	public String getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}
	
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id",insertable = false,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}	
		
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "opinion_poll_id",insertable = false,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public OpinionPoll getOpinionPoll() {
		return opinionPoll;
	}

	public void setOpinionPoll(OpinionPoll opinionPoll) {
		this.opinionPoll = opinionPoll;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "comment_id",insertable = false,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	
	@Column(name="time" ,length = 10)
	public Date getInsertedTime() {
		return insertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}

	@Column(name="opinion_poll_id")
	public Long getOpinionPollId() {
		return opinionPollId;
	}

	public void setOpinionPollId(Long opinionPollId) {
		this.opinionPollId = opinionPollId;
	}

	@Column(name="comment_id")
	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	@Column(name="user_id")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
		
	
}
