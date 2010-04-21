/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 20,2010
 */
package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "comment_data_category")
public class CommentDataCategory extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2470581083133094909L;
	
	private Long commentDataCategoryId;
	private String commentDataCategoryType;
	private Date updatedDate;
	private Set<CommentData> commentData = new HashSet<CommentData>();
	
	//default constructor
	public CommentDataCategory(){
		super();
	}
	
	//parameterized constructor
	public CommentDataCategory(String commentDataCategoryType,
			Date updatedDate, Set<CommentData> commentData) {
		super();
		this.commentDataCategoryType = commentDataCategoryType;
		this.updatedDate = updatedDate;
		this.commentData = commentData;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "comment_data_category_id", unique = true, nullable = false)
	public Long getCommentDataCategoryId() {
		return commentDataCategoryId;
	}

	public void setCommentDataCategoryId(Long commentDataCategoryId) {
		this.commentDataCategoryId = commentDataCategoryId;
	}

	@Column(name = "comment_data_category_type", length = 100)
	public String getCommentDataCategoryType() {
		return commentDataCategoryType;
	}

	public void setCommentDataCategoryType(String commentDataCategoryType) {
		this.commentDataCategoryType = commentDataCategoryType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "updated_date", length = 10)
	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "commentDataCategory")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<CommentData> getCommentData() {
		return commentData;
	}

	public void setCommentData(Set<CommentData> commentData) {
		this.commentData = commentData;
	}
	
	
	
	

}
