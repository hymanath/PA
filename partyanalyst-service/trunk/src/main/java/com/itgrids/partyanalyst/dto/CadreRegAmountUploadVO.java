package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;

public class CadreRegAmountUploadVO implements Serializable{

	private static final long serialVersionUID = 2837961446337439869L;
	
	private String branch;
	private String username;
	private Integer amount;
	
	private Long userId;
	private String path;
	private String fileName;
	private Date uploadedDate;
	private Date uploadedTime;
	
	public Date getUploadedDate() {
		return uploadedDate;
	}
	public void setUploadedDate(Date uploadedDate) {
		this.uploadedDate = uploadedDate;
	}
	public Date getUploadedTime() {
		return uploadedTime;
	}
	public void setUploadedTime(Date uploadedTime) {
		this.uploadedTime = uploadedTime;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}
