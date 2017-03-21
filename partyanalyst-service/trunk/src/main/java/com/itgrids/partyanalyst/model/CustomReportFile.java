package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;
@Entity
@Table(name="custom_report_file")
public class CustomReportFile implements Serializable {
	private static final long serialVersionUID = 1L;
	public Long customReportFileId;
	public Long customReportId ;
	public String fileName;
	public String path;
	public String isDeleted;
	public Long orderNo;
	public CustomReport customReport;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="custom_report_file_id",nullable=false,unique=true)
	public Long getCustomReportFileId() {
		return customReportFileId;
	}
	public void setCustomReportFileId(Long customReportFileId) {
		this.customReportFileId = customReportFileId;
	}
	@Column(name="custom_report_id")
	public Long getCustomReportId() {
		return customReportId;
	}
	public void setCustomReportId(Long customReportId) {
		this.customReportId = customReportId;
	}
	@Column(name="file_name")
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Column(name="path")
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name="order_no")
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
 	@JoinColumn(name = "custom_report_id", insertable = false, updatable = false)
 	@LazyToOne(LazyToOneOption.NO_PROXY)
 	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public CustomReport getCustomReport() {
		return customReport;
	}
	public void setCustomReport(CustomReport customReport) {
		this.customReport = customReport;
	}
}
