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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "activity_report_files")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActivityReportFiles extends BaseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3678856469376961901L;
	private Long activityReportFilesId;
	private ActivityReport activityReport;
	private File file;
public ActivityReportFiles()
{
	
}

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="activity_report_files_id", unique=true, nullable=false)
public Long getActivityReportFilesId() {
	return activityReportFilesId;
}

public void setActivityReportFilesId(Long activityReportFilesId) {
	this.activityReportFilesId = activityReportFilesId;
}


@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
@JoinColumn(name = "file_id")
@LazyToOne(LazyToOneOption.NO_PROXY)
@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
public File getFile() {
	return file;
}

@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
@JoinColumn(name = "activity_report_id")
@LazyToOne(LazyToOneOption.NO_PROXY)
@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
public ActivityReport getActivityReport() {
	return activityReport;
}

public void setActivityReport(ActivityReport activityReport) {
	this.activityReport = activityReport;
}

public void setFile(File file) {
	this.file = file;
}


}
