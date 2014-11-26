package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class TabRecordsStatusVO implements java.io.Serializable{
	
	private Long userId;
	private String name;
	private Long totalRecords;
	private Long pendingRecords;
	private String tabIMEINo;	
	private String mobileNo;
	private Long duplicateRecords;
	private Long currPendingRecords;
	private Long  currTotalRecords;
	private Long currDuplicateRecords;
	private Long actualUsrId;
	
	private List<TabRecordsStatusVO> tabRecordsStatusVOList = new ArrayList<TabRecordsStatusVO>(0);
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(Long totalRecords) {
		this.totalRecords = totalRecords;
	}
	public Long getPendingRecords() {
		return pendingRecords;
	}
	public void setPendingRecords(Long pendingRecords) {
		this.pendingRecords = pendingRecords;
	}
	public String getTabIMEINo() {
		return tabIMEINo;
	}
	public void setTabIMEINo(String tabIMEINo) {
		this.tabIMEINo = tabIMEINo;
	}
	public List<TabRecordsStatusVO> getTabRecordsStatusVOList() {
		return tabRecordsStatusVOList;
	}
	public void setTabRecordsStatusVOList(
			List<TabRecordsStatusVO> tabRecordsStatusVOList) {
		this.tabRecordsStatusVOList = tabRecordsStatusVOList;
	}
	public Long getDuplicateRecords() {
		return duplicateRecords;
	}
	public void setDuplicateRecords(Long duplicateRecords) {
		this.duplicateRecords = duplicateRecords;
	}
	public Long getCurrPendingRecords() {
		return currPendingRecords;
	}
	public void setCurrPendingRecords(Long currPendingRecords) {
		this.currPendingRecords = currPendingRecords;
	}
	public Long getCurrTotalRecords() {
		return currTotalRecords;
	}
	public void setCurrTotalRecords(Long currTotalRecords) {
		this.currTotalRecords = currTotalRecords;
	}
	public Long getCurrDuplicateRecords() {
		return currDuplicateRecords;
	}
	public void setCurrDuplicateRecords(Long currDuplicateRecords) {
		this.currDuplicateRecords = currDuplicateRecords;
	}
	public Long getActualUsrId() {
		return actualUsrId;
	}
	public void setActualUsrId(Long actualUsrId) {
		this.actualUsrId = actualUsrId;
	}
	
	
}
