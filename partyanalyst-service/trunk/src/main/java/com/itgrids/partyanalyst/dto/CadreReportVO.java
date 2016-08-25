package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CadreReportVO implements Serializable {
	private static final long serialVersionUID = 3609401538796873903L;
	
	private String reportType;
	List<ReportVO> reportVOList;
	private String reportPath;
	private String reportDate;
	private Integer sno;
	
	public Integer getSno() {
		return sno;
	}
	public void setSno(Integer sno) {
		this.sno = sno;
	}
	
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public List<ReportVO> getReportVOList() {
		if(reportVOList==null){
			reportVOList = new ArrayList<ReportVO>(0);  
		}
		return reportVOList;
	}
	public String getReportPath() {
		return reportPath;
	}
	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	
}
