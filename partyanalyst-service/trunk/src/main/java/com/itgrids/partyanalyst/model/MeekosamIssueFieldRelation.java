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
@Table(name = "meekosam_issue_field_relation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MeekosamIssueFieldRelation extends BaseModel implements Serializable {
	private Long meekosamIssueFieldRelationId;
	private Long meekosamIssueFieldId;
	private Long meekosamIssueFieldTypeId;
	private String isDeleted;
	private Long meekosamIssueTypeId;
	
	private MeekosamIssueField meekosamIssueField;
	private MeekosamIssueFieldType meekosamIssueFieldType;
	private MeekosamIssueType meekosamIssueType;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "meekosam_issue_field_relation_id", unique = true, nullable = false)
	public Long getMeekosamIssueFieldRelationId() {
		return meekosamIssueFieldRelationId;
	}
	public void setMeekosamIssueFieldRelationId(Long meekosamIssueFieldRelationId) {
		this.meekosamIssueFieldRelationId = meekosamIssueFieldRelationId;
	}
	@Column(name = "meekosam_issue_field_id")
	public Long getMeekosamIssueFieldId() {
		return meekosamIssueFieldId;
	}
	public void setMeekosamIssueFieldId(Long meekosamIssueFieldId) {
		this.meekosamIssueFieldId = meekosamIssueFieldId;
	}
	@Column(name = "meekosam_issue_field_type_id")
	public Long getMeekosamIssueFieldTypeId() {
		return meekosamIssueFieldTypeId;
	}
	public void setMeekosamIssueFieldTypeId(Long meekosamIssueFieldTypeId) {
		this.meekosamIssueFieldTypeId = meekosamIssueFieldTypeId;
	}
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="meekosam_issue_field_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public MeekosamIssueField getMeekosamIssueField() {
		return meekosamIssueField;
	}
	public void setMeekosamIssueField(MeekosamIssueField meekosamIssueField) {
		this.meekosamIssueField = meekosamIssueField;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="meekosam_issue_field_type_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public MeekosamIssueFieldType getMeekosamIssueFieldType() {
		return meekosamIssueFieldType;
	}
	public void setMeekosamIssueFieldType(
			MeekosamIssueFieldType meekosamIssueFieldType) {
		this.meekosamIssueFieldType = meekosamIssueFieldType;
	}
	
	@Column(name = "meekosam_issue_type_id")
	public Long getMeekosamIssueTypeId() {
		return meekosamIssueTypeId;
	}
	public void setMeekosamIssueTypeId(Long meekosamIssueTypeId) {
		this.meekosamIssueTypeId = meekosamIssueTypeId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="meekosam_issue_type_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public MeekosamIssueType getMeekosamIssueType() {
		return meekosamIssueType;
	}
	public void setMeekosamIssueType(MeekosamIssueType meekosamIssueType) {
		this.meekosamIssueType = meekosamIssueType;
	}
}
