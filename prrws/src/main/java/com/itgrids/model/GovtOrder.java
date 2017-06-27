package com.itgrids.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "govt_order")
public class GovtOrder implements Serializable {
	private Long govtOrderId;
	private String goNumber;
	private Date issueDate;
	private String filePath;
	private Long sno;
	@Id
	@Column(name="govt_order_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getGovtOrderId() {
		return govtOrderId;
	}
	public void setGovtOrderId(Long govtOrderId) {
		this.govtOrderId = govtOrderId;
	}
	@Column(name="go_number")
	public String getGoNumber() {
		return goNumber;
	}
	public void setGoNumber(String goNumber) {
		this.goNumber = goNumber;
	}
	@Column(name="issue_date")
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	@Column(name="file_path")
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	@Column(name="sno")
	public Long getSno() {
		return sno;
	}
	public void setSno(Long sno) {
		this.sno = sno;
	}
}
