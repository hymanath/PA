package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CadreReportVO implements Serializable {
	private static final long serialVersionUID = 3609401538796873903L;
	
	private String reportType;
	List<ReportVO> reportVOList;
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

}
