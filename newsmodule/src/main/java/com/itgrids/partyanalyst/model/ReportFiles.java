package com.itgrids.partyanalyst.model;

import java.io.Serializable;
/*CREATE TABLE `report_files` (
		  `report_files_id` int(15) NOT NULL AUTO_INCREMENT,
		  `news_report_id` int(15) DEFAULT NULL,
		  `file_gallary_id` bigint(15) DEFAULT NULL,
		  PRIMARY KEY (`report_files_id`),
		  KEY `fk_report_files_file_gallary` (`file_gallary_id`)
		) ENGINE=InnoDB DEFAULT CHARSET=latin1$$
*/

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
@Table(name = "report_files")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ReportFiles extends BaseModel implements Serializable{
	private Long reportFilesId;
	private NewsReport newsReport;
	private FileGallary fileGallary;
public ReportFiles()
{
	
}
public ReportFiles(Long reportFilesId,NewsReport newsReport,FileGallary fileGallary)
{
this.reportFilesId = reportFilesId;
this.newsReport = newsReport;
this.fileGallary = fileGallary;

}
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="report_files_id", unique=true, nullable=false)
public Long getReportFilesId() {
	return reportFilesId;
}
public void setReportFilesId(Long reportFilesId) {
	this.reportFilesId = reportFilesId;
}
@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
@JoinColumn(name = "news_report_id")
@LazyToOne(LazyToOneOption.NO_PROXY)
@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
public NewsReport getNewsReport() {
	return newsReport;
}
public void setNewsReport(NewsReport newsReport) {
	this.newsReport = newsReport;
}
@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
@JoinColumn(name = "file_gallary_id")
@LazyToOne(LazyToOneOption.NO_PROXY)
@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
public FileGallary getFileGallary() {
	return fileGallary;
}
public void setFileGallary(FileGallary fileGallary) {
	this.fileGallary = fileGallary;
}


}
