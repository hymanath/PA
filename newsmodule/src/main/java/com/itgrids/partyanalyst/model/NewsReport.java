package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "news_report")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
				
public class NewsReport extends BaseModel implements Serializable{
	private Long newsReportId;
	private String description;
	private User user;
	private Date createdDate;
	private Set<ReportFiles> reportFiles = new HashSet<ReportFiles>(0);
	public NewsReport()
	{
		
	}
	public NewsReport(Long newsReportId,String description,User user,Date createdDate)
	{
	this.newsReportId = newsReportId;
	this.description = description;
	this.user = user;
	this.createdDate = createdDate;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="news_report_id", unique=true, nullable=false)
	public Long getNewsReportId() {
		return newsReportId;
	}
	public void setNewsReportId(Long newsReportId) {
		this.newsReportId = newsReportId;
	}
	@Column(name = "description", length = 45)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Column(name = "created_date")
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "newsReport")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<ReportFiles> getReportFiles() {
		return reportFiles;
	}

	public void setReportFiles(Set<ReportFiles> reportFiles) {
		this.reportFiles = reportFiles;
	}

}
