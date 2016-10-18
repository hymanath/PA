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
@Table(name = "cadre_reg_issue_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadreRegIssueStatus extends BaseModel implements Serializable {

	
	private Long cadreRegIssueStatusId;
	private String status;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cadre_reg_issue_status_id", unique = true, nullable = false)
	public Long getCadreRegIssueStatusId() {
		return cadreRegIssueStatusId;
	}
	public void setCadreRegIssueStatusId(Long cadreRegIssueStatusId) {
		this.cadreRegIssueStatusId = cadreRegIssueStatusId;
	}
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}
