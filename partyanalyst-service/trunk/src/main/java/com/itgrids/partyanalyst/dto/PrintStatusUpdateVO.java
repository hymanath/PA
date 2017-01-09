package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;

public class PrintStatusUpdateVO implements Serializable{
	
	private  Long constituencyId;
	private  Long printVendorId;
	private  Long printStatusId;
	private  String remarks;
	private  Long  userId;
	private  Date  currentDate;
	
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public Long getPrintVendorId() {
		return printVendorId;
	}
	public void setPrintVendorId(Long printVendorId) {
		this.printVendorId = printVendorId;
	}
	public Long getPrintStatusId() {
		return printStatusId;
	}
	public void setPrintStatusId(Long printStatusId) {
		this.printStatusId = printStatusId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Date getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}
	
}
