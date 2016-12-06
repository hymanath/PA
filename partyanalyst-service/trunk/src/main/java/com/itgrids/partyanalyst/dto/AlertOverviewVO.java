package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class AlertOverviewVO {
	
	private Long totalAlertCnt = 0l;
	private Long partyAlertCnt = 0l;
	private Long otherAlertCnt = 0l;
	
	private Long statusTypeId;
	private String statusType;
	private Long statusCnt = 0l;
	
	private Double statusCntPer = 0.0d;
	private Double partyAlertCntPer = 0.0d;
	private Double otherAlertCntPer = 0.0d;
	
	private AlertOverviewVO overAllVO;
	private List<AlertOverviewVO> statusList;
	private List<AlertOverviewVO> categoryList;
	
	public Long getTotalAlertCnt() {
		return totalAlertCnt;
	}
	public void setTotalAlertCnt(Long totalAlertCnt) {
		this.totalAlertCnt = totalAlertCnt;
	}
	public Long getPartyAlertCnt() {
		return partyAlertCnt;
	}
	public void setPartyAlertCnt(Long partyAlertCnt) {
		this.partyAlertCnt = partyAlertCnt;
	}
	public Long getOtherAlertCnt() {
		return otherAlertCnt;
	}
	public void setOtherAlertCnt(Long otherAlertCnt) {
		this.otherAlertCnt = otherAlertCnt;
	}
	public Long getStatusTypeId() {
		return statusTypeId;
	}
	public void setStatusTypeId(Long statusTypeId) {
		this.statusTypeId = statusTypeId;
	}
	public String getStatusType() {
		return statusType;
	}
	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}
	public Long getStatusCnt() {
		return statusCnt;
	}
	public void setStatusCnt(Long statusCnt) {
		this.statusCnt = statusCnt;
	}
	public Double getStatusCntPer() {
		return statusCntPer;
	}
	public void setStatusCntPer(Double statusCntPer) {
		this.statusCntPer = statusCntPer;
	}
	public Double getPartyAlertCntPer() {
		return partyAlertCntPer;
	}
	public void setPartyAlertCntPer(Double partyAlertCntPer) {
		this.partyAlertCntPer = partyAlertCntPer;
	}
	public Double getOtherAlertCntPer() {
		return otherAlertCntPer;
	}
	public void setOtherAlertCntPer(Double otherAlertCntPer) {
		this.otherAlertCntPer = otherAlertCntPer;
	}
	public AlertOverviewVO getOverAllVO() {
		return overAllVO;
	}
	public void setOverAllVO(AlertOverviewVO overAllVO) {
		this.overAllVO = overAllVO;
	}
	public List<AlertOverviewVO> getStatusList() { 
		if(statusList == null){                               
	   	 statusList = new ArrayList<AlertOverviewVO>();   
        }                                                       
       return statusList;
     }

	public List<AlertOverviewVO> getCategoryList() {	
		if(categoryList == null){
			 categoryList = new ArrayList<AlertOverviewVO>();
		}
		return categoryList;
	}
	
}
