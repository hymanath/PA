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
@Table(name = "meekosam_issue_field_relation_data")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MeekosamIssueFieldRelationData extends BaseModel implements Serializable {
	private Long meekosamIssueFieldRelationDataId;
	private Long meekosamIssueFieldRelationId;
	private Long meekosamIssueFieldDataId;
	private String isDeleted;
	
	private MeekosamIssueFieldRelation meekosamIssueFieldRelation;
	private MeekosamIssueFieldData meekosamIssueFieldData;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "meekosam_issue_field_relation_data_id", unique = true, nullable = false)
	public Long getMeekosamIssueFieldRelationDataId() {
		return meekosamIssueFieldRelationDataId;
	}
	public void setMeekosamIssueFieldRelationDataId(
			Long meekosamIssueFieldRelationDataId) {
		this.meekosamIssueFieldRelationDataId = meekosamIssueFieldRelationDataId;
	}
	@Column(name = "meekosam_issue_field_relation_id")
	public Long getMeekosamIssueFieldRelationId() {
		return meekosamIssueFieldRelationId;
	}
	public void setMeekosamIssueFieldRelationId(Long meekosamIssueFieldRelationId) {
		this.meekosamIssueFieldRelationId = meekosamIssueFieldRelationId;
	}
	@Column(name = "meekosam_issue_field_data_id")
	public Long getMeekosamIssueFieldDataId() {
		return meekosamIssueFieldDataId;
	}
	public void setMeekosamIssueFieldDataId(Long meekosamIssueFieldDataId) {
		this.meekosamIssueFieldDataId = meekosamIssueFieldDataId;
	}
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="meekosam_issue_field_relation_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public MeekosamIssueFieldRelation getMeekosamIssueFieldRelation() {
		return meekosamIssueFieldRelation;
	}
	public void setMeekosamIssueFieldRelation(
			MeekosamIssueFieldRelation meekosamIssueFieldRelation) {
		this.meekosamIssueFieldRelation = meekosamIssueFieldRelation;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="meekosam_issue_field_data_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public MeekosamIssueFieldData getMeekosamIssueFieldData() {
		return meekosamIssueFieldData;
	}
	public void setMeekosamIssueFieldData(
			MeekosamIssueFieldData meekosamIssueFieldData) {
		this.meekosamIssueFieldData = meekosamIssueFieldData;
	}
	
	
}
