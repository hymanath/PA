package com.itgrids.dto;

public class DocumentVO {
	private Long documentId;
	private String documentName;
	private String path;
	private String base64Str;
	private Long userId;
	private String userName;
	private String insertedTime;
	private Double kms=0.00;
	
	public Long getDocumentId() {
		return documentId;
	}
	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getBase64Str() {
		return base64Str;
	}
	public void setBase64Str(String base64Str) {
		this.base64Str = base64Str;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(String insertedTime) {
		this.insertedTime = insertedTime;
	}
	public Double getKms() {
		return kms;
	}
	public void setKms(Double kms) {
		this.kms = kms;
	}
	
	
}
