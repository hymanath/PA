package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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
@Table(name = "cadre_reg_issue_person")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadreRegIssuePerson extends BaseModel implements Serializable{

	private Long cadreRegIssuePersonId;
	private Long cadreRegIssueId;
	private String mandal;
	private String name;
	private String mobileNo;
	private CadreRegIssue cadreRegIssue;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cadre_reg_issue_person_id", unique = true, nullable = false)
	public Long getCadreRegIssuePersonId() {
		return cadreRegIssuePersonId;
	}
	public void setCadreRegIssuePersonId(Long cadreRegIssuePersonId) {
		this.cadreRegIssuePersonId = cadreRegIssuePersonId;
	}
	
	@Column(name="cadre_reg_issue_id")
	public Long getCadreRegIssueId() {
		return cadreRegIssueId;
	}
	public void setCadreRegIssueId(Long cadreRegIssueId) {
		this.cadreRegIssueId = cadreRegIssueId;
	}
	
	@Column(name="mandal")
	public String getMandal() {
		return mandal;
	}
	public void setMandal(String mandal) {
		this.mandal = mandal;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="mobile_no")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "cadre_reg_issue_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreRegIssue getCadreRegIssue() {
		return cadreRegIssue;
	}
	public void setCadreRegIssue(CadreRegIssue cadreRegIssue) {
		this.cadreRegIssue = cadreRegIssue;
	}
}
