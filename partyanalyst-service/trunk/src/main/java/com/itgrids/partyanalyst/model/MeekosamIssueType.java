package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
@Entity
@Table(name = "meekosam_issue_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MeekosamIssueType extends BaseModel implements Serializable {
	private Long meekosamIssueTypeId;
	private String issueType;
	private Long parentMeekosamIssueTypeId;
	private String isActive;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "meekosam_issue_type_id", unique = true, nullable = false)
	public Long getMeekosamIssueTypeId() {
		return meekosamIssueTypeId;
	}
	public void setMeekosamIssueTypeId(Long meekosamIssueTypeId) {
		this.meekosamIssueTypeId = meekosamIssueTypeId;
	}
	@Column(name = "issue_type")
	public String getIssueType() {
		return issueType;
	}
	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}
	@Column(name = "parent_meekosam_issue_type_id")
	public Long getParentMeekosamIssueTypeId() {
		return parentMeekosamIssueTypeId;
	}
	public void setParentMeekosamIssueTypeId(Long parentMeekosamIssueTypeId) {
		this.parentMeekosamIssueTypeId = parentMeekosamIssueTypeId;
	}
	@Column(name = "is_active")
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
}
