package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name="custom_report_image")
public class CustomReportImage implements Serializable {
	private static final long serialVersionUID = 1L;
	public Long customReportimageId;
	public Long customReportId;
	public String imageName;
	public String path;
	public String isDeleted;
	public CustomReport customReport;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="custom_report_image_id",nullable=false,unique=true)
	public Long getCustomReportimageId() {
		return customReportimageId;
	}
	public void setCustomReportimageId(Long customReportimageId) {
		this.customReportimageId = customReportimageId;
	}
	@Column(name="custom_report_id")
	public Long getCustomReportId() {
		return customReportId;
	}
	public void setCustomReportId(Long customReportId) {
		this.customReportId = customReportId;
	}
	@Column(name="image_name")
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
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
