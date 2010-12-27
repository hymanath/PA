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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "comment_category_party")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CommentCategoryParty extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4956164041799222810L;
	
	private Long commentCategoryPartyId;
	private CommentData commentData;
	private Party party;
	private Election election;
	
	
	//default constructor
	public CommentCategoryParty() {
		super();
	}

	//parameterized constructor
	public CommentCategoryParty(CommentData commentData, Party party,
			Election election) {
		super();
		this.commentData = commentData;
		this.party = party;
		this.election = election;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "comment_category_party_id", unique = true, nullable = false)
	public Long getCommentCategoryPartyId() {
		return commentCategoryPartyId;
	}

	public void setCommentCategoryPartyId(Long commentCategoryPartyId) {
		this.commentCategoryPartyId = commentCategoryPartyId;
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
	@JoinColumn(name = "party_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "election_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Election getElection() {
		return election;
	}

	public void setElection(Election election) {
		this.election = election;
	}

}
