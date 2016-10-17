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
@Table(name = "cadre_reg_issue_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadreRegIssueType extends BaseModel implements Serializable {

	/**
	 * @param args
	 */
	
	private Long cadreRegIssueTypeId;
	private String issueType;//issue_type
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cadre_reg_issue_type_id", unique = true, nullable = false)
	public Long getCadreRegIssueTypeId() {
		return cadreRegIssueTypeId;
	}
	public void setCadreRegIssueTypeId(Long cadreRegIssueTypeId) {
		this.cadreRegIssueTypeId = cadreRegIssueTypeId;
	}
	@Column(name="issue_type")
	public String getIssueType() {
		return issueType;
	}
	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}
	
	
}
