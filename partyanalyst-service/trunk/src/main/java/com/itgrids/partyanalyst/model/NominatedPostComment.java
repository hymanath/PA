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
@Table(name = "nominated_post_comment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NominatedPostComment extends BaseModel implements Serializable{

	private Long nominatedPostCommentId;
	private Long nominatedPostApplicationId;
	private String remarks;
	private Long insertedBy;
	private Date insertedTime;
	private NominatedPostApplication nominatedPostApplication;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "nominated_post_comment_id", unique = true, nullable = false)
	public Long getNominatedPostCommentId() {
		return nominatedPostCommentId;
	}
	public void setNominatedPostCommentId(Long nominatedPostCommentId) {
		this.nominatedPostCommentId = nominatedPostCommentId;
	}
	
	@Column(name="nominated_post_application_id")
	public Long getNominatedPostApplicationId() {
		return nominatedPostApplicationId;
	}
	public void setNominatedPostApplicationId(Long nominatedPostApplicationId) {
		this.nominatedPostApplicationId = nominatedPostApplicationId;
	}
	
	@Column(name="remarks")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@Column(name="inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="nominated_post_application_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public NominatedPostApplication getNominatedPostApplication() {
		return nominatedPostApplication;
	}
	public void setNominatedPostApplication(
			NominatedPostApplication nominatedPostApplication) {
		this.nominatedPostApplication = nominatedPostApplication;
	}
}
