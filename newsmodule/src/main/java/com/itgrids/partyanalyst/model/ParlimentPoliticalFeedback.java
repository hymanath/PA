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


@Entity
@Table(name = "parliment_political_feedback")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ParlimentPoliticalFeedback implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private Long parlimentPoliticalFeedbackId;
	private Date createdDate;
	private Constituency parlimentConstituency;
	private User user;
	private String summary;
	private Date insertedDate;
	private Date updatedDate;;
	private String isDeleted;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "parliment_political_feedback_id", unique = true, nullable = false)
	public Long getParlimentPoliticalFeedbackId() {
		return parlimentPoliticalFeedbackId;
	}
	public void setParlimentPoliticalFeedbackId(Long parlimentPoliticalFeedbackId) {
		this.parlimentPoliticalFeedbackId = parlimentPoliticalFeedbackId;
	}
	
	@Column(name="created_date")
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "parliment_constituency_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getParlimentConstituency() {
		return parlimentConstituency;
	}
	public void setParlimentConstituency(Constituency parlimentConstituency) {
		this.parlimentConstituency = parlimentConstituency;
	}
	
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Column(name="summary")
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	@Column(name="inserted_date")
	public Date getInsertedDate() {
		return insertedDate;
	}
	public void setInsertedDate(Date insertedDate) {
		this.insertedDate = insertedDate;
	}
	
	@Column(name="updated_date")
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
	
}
