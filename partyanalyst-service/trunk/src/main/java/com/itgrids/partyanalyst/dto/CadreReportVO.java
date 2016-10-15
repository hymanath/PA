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
	
	private Long locationId;
	private String locationName;
	private Long total2014CadreCnt=0l;
	private Long total2014CadreTargetCnt=0l;
	private Long total2016CadreCnt=0l;
	private Long total2016CadreTargetCnt=0l;
	private Long total2016RenewalCadreCount=0l;
	private Long total2016NewCadreCount=0l;
	private Double total2014CadrePer=0.0d;
	private Double total2016CadrePer=0.0d;
	private Double total2016RenewalCadrePer=0.0d;
	private Double total2016NewCadrePer=0.0d;
	private List<CadreReportVO> subList1;
	private List<CadreReportVO> subList2;
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
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public Long getTotal2014CadreCnt() {
		return total2014CadreCnt;
	}
	public void setTotal2014CadreCnt(Long total2014CadreCnt) {
		this.total2014CadreCnt = total2014CadreCnt;
	}
	public Long getTotal2014CadreTargetCnt() {
		return total2014CadreTargetCnt;
	}
	public void setTotal2014CadreTargetCnt(Long total2014CadreTargetCnt) {
		this.total2014CadreTargetCnt = total2014CadreTargetCnt;
	}
	public Long getTotal2016CadreCnt() {
		return total2016CadreCnt;
	}
	public void setTotal2016CadreCnt(Long total2016CadreCnt) {
		this.total2016CadreCnt = total2016CadreCnt;
	}
	public Long getTotal2016CadreTargetCnt() {
		return total2016CadreTargetCnt;
	}
	public void setTotal2016CadreTargetCnt(Long total2016CadreTargetCnt) {
		this.total2016CadreTargetCnt = total2016CadreTargetCnt;
	}
	public Long getTotal2016RenewalCadreCount() {
		return total2016RenewalCadreCount;
	}
	public void setTotal2016RenewalCadreCount(Long total2016RenewalCadreCount) {
		this.total2016RenewalCadreCount = total2016RenewalCadreCount;
	}
	public Long getTotal2016NewCadreCount() {
		return total2016NewCadreCount;
	}
	public void setTotal2016NewCadreCount(Long total2016NewCadreCount) {
		this.total2016NewCadreCount = total2016NewCadreCount;
	}
	public Double getTotal2014CadrePer() {
		return total2014CadrePer;
	}
	public void setTotal2014CadrePer(Double total2014CadrePer) {
		this.total2014CadrePer = total2014CadrePer;
	}
	public Double getTotal2016RenewalCadrePer() {
		return total2016RenewalCadrePer;
	}
	public void setTotal2016RenewalCadrePer(Double total2016RenewalCadrePer) {
		this.total2016RenewalCadrePer = total2016RenewalCadrePer;
	}
	public Double getTotal2016NewCadrePer() {
		return total2016NewCadrePer;
	}
	public void setTotal2016NewCadrePer(Double total2016NewCadrePer) {
		this.total2016NewCadrePer = total2016NewCadrePer;
	}
	public Double getTotal2016CadrePer() {
		return total2016CadrePer;
	}
	public void setTotal2016CadrePer(Double total2016CadrePer) {
		this.total2016CadrePer = total2016CadrePer;
	}
	public List<CadreReportVO> getSubList1() {
		return subList1;
	}
	public void setSubList1(List<CadreReportVO> subList1) {
		this.subList1 = subList1;
	}
	public List<CadreReportVO> getSubList2() {
		return subList2;
	}
	public void setSubList2(List<CadreReportVO> subList2) {
		this.subList2 = subList2;
	}
}
