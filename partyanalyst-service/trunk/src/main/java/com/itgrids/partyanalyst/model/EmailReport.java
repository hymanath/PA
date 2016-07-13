package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "email_report")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)  
public class EmailReport extends BaseModel {
	private static final long serialVersionUID = 761184693215111133L;
	
	private Long emailReportId;
	private String reportName;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "email_report_id", unique = true, nullable = false)
	public Long getEmailReportId() {
		return emailReportId;
	}
	public void setEmailReportId(Long emailReportId) {
		this.emailReportId = emailReportId;
	}
	@Column(name = "report_name")
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
}
