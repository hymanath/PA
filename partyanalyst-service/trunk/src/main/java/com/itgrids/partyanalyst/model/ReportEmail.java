package com.itgrids.partyanalyst.model;

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
@Table(name = "report_email")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)  
public class ReportEmail extends BaseModel {
	private static final long serialVersionUID = 7598048581279131963L;
	
	private Long reportEmailId;
	private Long emailReportId;
	private Long userEmailId;
	private String isEnabled;
	
	private UserEmail userEmail;
	private EmailReport emailReport;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "report_email_id", unique = true, nullable = false)
	public Long getReportEmailId() {
		return reportEmailId;
	}
	public void setReportEmailId(Long reportEmailId) {
		this.reportEmailId = reportEmailId;
	}
	@Column(name = "email_report_id")
	public Long getEmailReportId() {
		return emailReportId;
	}
	public void setEmailReportId(Long emailReportId) {
		this.emailReportId = emailReportId;
	}
	@Column(name = "user_email_id")
	public Long getUserEmailId() {
		return userEmailId;
	}
	public void setUserEmailId(Long userEmailId) {
		this.userEmailId = userEmailId;
	}
	@Column(name = "is_enabled")
	public String getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_email_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserEmail getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(UserEmail userEmail) {
		this.userEmail = userEmail;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="email_report_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public EmailReport getEmailReport() {
		return emailReport;
	}
	public void setEmailReport(EmailReport emailReport) {
		this.emailReport = emailReport;
	}
	
}
