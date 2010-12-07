/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 14,2010
 */
package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "comment_category_candidate")
public class CommentCategoryCandidate extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4326918622341965388L;
	
	private Long commentCategoryCandidateId;
	private CommentData commentData;
	private Nomination nomination;
	private Registration paidUser;
	private AnanymousUser freeUser;
	private Float severity = 0f;
	
	//default constructor
	public CommentCategoryCandidate() {
		super();
	}
	
	//parameterized constructor
	public CommentCategoryCandidate(CommentData commentData,
			Nomination nomination) {
		super();
		this.commentData = commentData;
		this.nomination = nomination;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "comment_category_candidate_id", unique = true, nullable = false)
	public Long getCommentCategoryCandidateId() {
		return commentCategoryCandidateId;
	}

	public void setCommentCategoryCandidateId(Long commentCategoryCandidateId) {
		this.commentCategoryCandidateId = commentCategoryCandidateId;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "comment_data_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CommentData getCommentData() {
		return commentData;
	}

	public void setCommentData(CommentData commentData) {
		this.commentData = commentData;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "nomination_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Nomination getNomination() {
		return nomination;
	}

	public void setNomination(Nomination nomination) {
		this.nomination = nomination;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "paid_user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Registration getPaidUser() {
		return paidUser;
	}

	public void setPaidUser(Registration paidUser) {
		this.paidUser = paidUser;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "free_user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AnanymousUser getFreeUser() {
		return freeUser;
	}

	public void setFreeUser(AnanymousUser freeUser) {
		this.freeUser = freeUser;
	}

	@Column(name="severity")
	public Float getSeverity() {
		return severity;
	}

	public void setSeverity(Float severity) {
		this.severity = severity;
	}

}
