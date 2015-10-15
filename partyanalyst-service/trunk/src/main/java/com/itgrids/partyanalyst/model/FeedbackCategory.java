package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "feedback_category")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FeedbackCategory {
	private Long feedbackCategoryId;
	private String categoryName;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "feedback_category_id", unique = true, nullable = false)
	public Long getFeedbackCategoryId() {
		return feedbackCategoryId;
	}
	public void setFeedbackCategoryId(Long feedbackCategoryId) {
		this.feedbackCategoryId = feedbackCategoryId;
	}
	@Column(name = "category_name")
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	

}
