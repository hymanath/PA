package com.itgrids.partyanalyst.dto;

import java.util.Date;

public class ImageVO implements java.io.Serializable{

	private Long 			id;
	private String 			name;
	private Date 			uploadTime;
	private String 			imei;
	private String 			uniqueKey;
	private Date 			insertedTime;
	private String 			latitude;
	private String 			longitude;
	private Long 			tabUserId;
	private String 			syncSource;
	private Long 			insertedBy;
	private Long 			tabPrimaryKey;
	private String 			base64ImageStr;	
	private Long 			webPrimaryKey;
	private String 			status;
	
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getWebPrimaryKey() {
		return webPrimaryKey;
	}
	public void setWebPrimaryKey(Long webPrimaryKey) {
		this.webPrimaryKey = webPrimaryKey;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getUniqueKey() {
		return uniqueKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public Long getTabUserId() {
		return tabUserId;
	}
	public void setTabUserId(Long tabUserId) {
		this.tabUserId = tabUserId;
	}
	public String getSyncSource() {
		return syncSource;
	}
	public void setSyncSource(String syncSource) {
		this.syncSource = syncSource;
	}
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	public Long getTabPrimaryKey() {
		return tabPrimaryKey;
	}
	public void setTabPrimaryKey(Long tabPrimaryKey) {
		this.tabPrimaryKey = tabPrimaryKey;
	}
	public String getBase64ImageStr() {
		return base64ImageStr;
	}
	public void setBase64ImageStr(String base64ImageStr) {
		this.base64ImageStr = base64ImageStr;
	}
	
	
}
