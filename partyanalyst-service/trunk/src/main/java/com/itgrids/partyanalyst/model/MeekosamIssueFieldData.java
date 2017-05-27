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
@Table(name = "meekosam_issue_field_data")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MeekosamIssueFieldData extends BaseModel implements Serializable {
	private Long meekosamIssueFieldDataId;
	private String data;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "meekosam_issue_field_data_id", unique = true, nullable = false)
	public Long getMeekosamIssueFieldDataId() {
		return meekosamIssueFieldDataId;
	}
	public void setMeekosamIssueFieldDataId(Long meekosamIssueFieldDataId) {
		this.meekosamIssueFieldDataId = meekosamIssueFieldDataId;
	}
	@Column(name = "data")
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
}
