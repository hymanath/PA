/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on August 25, 2010
 */
package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SocialCategory entity. 
 * @author <a href="mailto:raghavenderprasad@gmail.com">Raghav</a>
 */

@Entity
@Table(name = "social_category")
public class SocialCategory implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long socialCategoryId;
	private String category;
	
	public SocialCategory() {
		super();
		
	}

	public SocialCategory(Long socialCategoryId, String category) {
		super();
		this.socialCategoryId = socialCategoryId;
		this.category = category;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "social_category_id", nullable = false, unique = true)
	public Long getSocialCategoryId() {
		return socialCategoryId;
	}

	public void setSocialCategoryId(Long socialCategoryId) {
		this.socialCategoryId = socialCategoryId;
	}

	@Column(name = "category", length = 10)
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
