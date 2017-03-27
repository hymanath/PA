package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="custom_report_program")
public class CustomReportProgram implements Serializable {
	private static final long serialVersionUID = 1L;
	public Long customReportProgramId;
	public String programName;
	public Date lastDate;
	public String isDeleted;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="custom_report_program_id",nullable=false,unique=true)
	public Long getCustomReportProgramId() {
		return customReportProgramId;
	}
	public void setCustomReportProgramId(Long customReportProgramId) {
		this.customReportProgramId = customReportProgramId;
	}
	@Column(name="program_name")
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	@Column(name="last_date")
	public Date getLastDate() {
		return lastDate;
	}
	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
}
