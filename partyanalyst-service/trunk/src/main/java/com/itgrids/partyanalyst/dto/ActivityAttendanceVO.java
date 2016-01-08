package com.itgrids.partyanalyst.dto;

public class ActivityAttendanceVO {
	
	private Long activityId;
	private Long tdpCadreId;
	private String type;
	private String syncType;
	private String name;
	private String voterCard;
	private String mobileNumber;
	
	private String rfid;
	private String imei;
	private String unqueKey;
	private String latitude;
	private String longitude;
	private Long tabUserId;
	private Long currentTabUserId;
	private Long tabPrimaryKey;
	private Long activityLocationInfoId;
	private Long bloodGroup;
	private String activityDate;
	
	
	public String getActivityDate() {
		return activityDate;
	}
	public void setActivityDate(String activityDate) {
		this.activityDate = activityDate;
	}
	public Long getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(Long bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public Long getActivityLocationInfoId() {
		return activityLocationInfoId;
	}
	public void setActivityLocationInfoId(Long activityLocationInfoId) {
		this.activityLocationInfoId = activityLocationInfoId;
	}
	public Long getActivityId() {
		return activityId;
	}
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSyncType() {
		return syncType;
	}
	public void setSyncType(String syncType) {
		this.syncType = syncType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVoterCard() {
		return voterCard;
	}
	public void setVoterCard(String voterCard) {
		this.voterCard = voterCard;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getRfid() {
		return rfid;
	}
	public void setRfid(String rfid) {
		this.rfid = rfid;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getUnqueKey() {
		return unqueKey;
	}
	public void setUnqueKey(String unqueKey) {
		this.unqueKey = unqueKey;
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
	public Long getTabPrimaryKey() {
		return tabPrimaryKey;
	}
	public void setTabPrimaryKey(Long tabPrimaryKey) {
		this.tabPrimaryKey = tabPrimaryKey;
	}
	
	
}
