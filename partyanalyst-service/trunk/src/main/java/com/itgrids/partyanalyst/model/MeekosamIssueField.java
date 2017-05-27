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
@Table(name = "meekosam_issue_field")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MeekosamIssueField extends BaseModel implements Serializable {
	private Long meekosamIssueFieldId;
	private String field;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "meekosam_issue_field_id", unique = true, nullable = false)
	public Long getMeekosamIssueFieldId() {
		return meekosamIssueFieldId;
	}
	public void setMeekosamIssueFieldId(Long meekosamIssueFieldId) {
		this.meekosamIssueFieldId = meekosamIssueFieldId;
	}
	@Column(name = "field")
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	
	
}
