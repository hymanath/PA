package com.itgrids.partyanalyst.dto;

public class ComplaintScanCopyVO {
	
	private String path;
	private Long scanCopyId;
	private boolean isNewCopy;
	private String dateString;
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Long getScanCopyId() {
		return scanCopyId;
	}
	public void setScanCopyId(Long scanCopyId) {
		this.scanCopyId = scanCopyId;
	}
	public boolean isNewCopy() {
		return isNewCopy;
	}
	public void setNewCopy(boolean isNewCopy) {
		this.isNewCopy = isNewCopy;
	}
	public String getDateString() {
		return dateString;
	}
	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

}
