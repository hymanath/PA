package com.itgrids.partyanalyst.model;

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

@Entity
@Table(name = "feedback")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FeedBack extends BaseModel implements java.io.Serializable {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	// Fields
	private Long feedbackId;
	private String comment;
	private Registration registration;
	private String responseCategory;
	private FeedBackComment feedBackComment;
	private FeedBackTask feedBackTask;

	/** default constructor */
	public FeedBack() {

	}

	/** full constructor */
	public FeedBack(Long feedbackId, String comment, Registration registration,
			String responseCategory, FeedBackComment feedBackComment,
			FeedBackTask feedBackTask) {
		this.feedbackId = feedbackId;
		this.comment = comment;
		this.registration = registration;
		this.feedBackComment = feedBackComment;
		this.feedBackTask = feedBackTask;
		this.responseCategory = responseCategory;

	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "feedback_id", unique = true, nullable = false)
	public Long getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(Long feedbackId) {
		this.feedbackId = feedbackId;
	}

	@Column(name = "comment", length = 500)
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
    @Column(name="response_category" ,length=10)
	public String getResponseCategory() {
		return responseCategory;
	}

	public void setResponseCategory(String responseCategory) {
		this.responseCategory = responseCategory;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Registration getRegistration() {
		return registration;
	}

	public void setRegistration(Registration registration) {
		this.registration = registration;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "feedback_comment_id")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public FeedBackComment getFeedBackComment() {
		return feedBackComment;
	}

	public void setFeedBackComment(FeedBackComment feedBackComment) {
		this.feedBackComment = feedBackComment;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "feedback_task_id")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public FeedBackTask getFeedBackTask() {
		return feedBackTask;
	}

	public void setFeedBackTask(FeedBackTask feedBackTask) {
		this.feedBackTask = feedBackTask;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}