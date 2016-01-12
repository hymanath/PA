/**
 * @author Sravanth
 * Jan 8, 2016
 * TabDetailsVO.java
 * PA - SERV - com.itgrids.partyanalyst.dto
 */
package com.itgrids.partyanalyst.dto;

import java.util.Date;

/**
 * @author Sravanth
 * @date Jan 8, 2016
 */
/**
 * @author Client
 *
 */
public class TabDetailsVO implements java.io.Serializable{

	private Long 			id;
	private String 			name;
	private Long 			tabDetailsId;
	private Date 			attendedTime;
	private String 			imei;
	private String 			uniqueKey;
	private Date 			insertedTime;
	private String 			latitude;
	private String 			longitude;
	private Long 			tabUserId;
	private Long 			currentTabUserId;
	private String 			syncSource;
	private Long 			insertedBy;
	private Long 			tabPrimaryKey;
	
	
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
	public Long getTabDetailsId() {
		return tabDetailsId;
	}
	public void setTabDetailsId(Long tabDetailsId) {
		this.tabDetailsId = tabDetailsId;
	}
	public Date getAttendedTime() {
		return attendedTime;
	}
	public void setAttendedTime(Date attendedTime) {
		this.attendedTime = attendedTime;
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
	public Long getCurrentTabUserId() {
		return currentTabUserId;
	}
	public void setCurrentTabUserId(Long currentTabUserId) {
		this.currentTabUserId = currentTabUserId;
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
}
