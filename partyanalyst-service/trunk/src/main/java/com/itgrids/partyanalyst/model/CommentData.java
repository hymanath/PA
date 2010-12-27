/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 14,2010
 */
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "comment_data")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CommentData extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5031662367312681535L;
	
	private Long commentDataId;
	private String commentDesc;
	private Date commentDate;
	private String commentBy;
	private CommentDataCategory commentDataCategory;
	private String isApproved;
	private Set<CommentCategoryCandidate> commentCategoryCandidate = new HashSet<CommentCategoryCandidate>(0);
	private Set<CommentCategoryParty> commentCategoryParty = new HashSet<CommentCategoryParty>(0);
	private Set<CommentCategoryConstituency> commentCategoryConstituency = new HashSet<CommentCategoryConstituency>(0);
	
	//default constructor
	public CommentData() {
		super();
	}

	//parameterized constructor
	public CommentData(String commentDesc, Date commentDate, String commentBy,
			CommentDataCategory commentDataCategory,
			Set<CommentCategoryCandidate> commentCategoryCandidate,
			Set<CommentCategoryParty> commentCategoryParty,
			Set<CommentCategoryConstituency> commentCategoryConstituency) {
		super();
		this.commentDesc = commentDesc;
		this.commentDate = commentDate;
		this.commentBy = commentBy;
		this.commentDataCategory = commentDataCategory;
		this.commentCategoryCandidate = commentCategoryCandidate;
		this.commentCategoryParty  = commentCategoryParty;
		this.commentCategoryConstituency = commentCategoryConstituency;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "comment_data_id", unique = true, nullable = false)
	public Long getCommentDataId() {
		return commentDataId;
	}

	public void setCommentDataId(Long commentDataId) {
		this.commentDataId = commentDataId;
	}

	@Column(name = "comment_desc", length = 250)
	public String getCommentDesc() {
		return commentDesc;
	}

	public void setCommentDesc(String commentDesc) {
		this.commentDesc = commentDesc;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "commented_on", length = 10)
	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "comment_data_category_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CommentDataCategory getCommentDataCategory() {
		return commentDataCategory;
	}

	public void setCommentDataCategory(CommentDataCategory commentDataCategory) {
		this.commentDataCategory = commentDataCategory;
	}

	@Column(name = "commented_by", length = 250)
	public String getCommentBy() {
		return commentBy;
	}

	public void setCommentBy(String commentBy) {
		this.commentBy = commentBy;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "commentData")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<CommentCategoryCandidate> getCommentCategoryCandidate() {
		return commentCategoryCandidate;
	}

	public void setCommentCategoryCandidate(
			Set<CommentCategoryCandidate> commentCategoryCandidate) {
		this.commentCategoryCandidate = commentCategoryCandidate;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "commentData")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<CommentCategoryParty> getCommentCategoryParty() {
		return commentCategoryParty;
	}

	public void setCommentCategoryParty(
			Set<CommentCategoryParty> commentCategoryParty) {
		this.commentCategoryParty = commentCategoryParty;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "commentData")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<CommentCategoryConstituency> getCommentCategoryConstituency() {
		return commentCategoryConstituency;
	}

	public void setCommentCategoryConstituency(
			Set<CommentCategoryConstituency> commentCategoryConstituency) {
		this.commentCategoryConstituency = commentCategoryConstituency;
	}

	@Column(name = "is_approved", length = 20)
	public String getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}

}
