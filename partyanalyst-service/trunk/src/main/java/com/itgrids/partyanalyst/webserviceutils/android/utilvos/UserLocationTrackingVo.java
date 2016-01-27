package com.itgrids.partyanalyst.webserviceutils.android.utilvos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserLocationTrackingVo implements java.io.Serializable{
	
	private Long surveyUserId;
	private Date date;
	private String longitude;
	private String latitude;
	private String insertTime;
	private String imeiNo;
	private String uuid;
	private List<UserLocationTrackingVo> userLocations;
	private List<UserLocationTrackingVo> locations = new ArrayList<UserLocationTrackingVo>(0);
	private String dateTime;
	private Long userId;
	private String uniqueId;
	private String time;
	private String userName;
	private String gpsTIME;
	
	
	public String getGpsTIME() {
		return gpsTIME;
	}
	public void setGpsTIME(String gpsTIME) {
		this.gpsTIME = gpsTIME;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<UserLocationTrackingVo> getLocations() {
		return locations;
	}
	public void setLocations(List<UserLocationTrackingVo> locations) {
		this.locations = locations;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public List<UserLocationTrackingVo> getUserLocations() {
		return userLocations;
	}
	public void setUserLocations(List<UserLocationTrackingVo> userLocations) {
		this.userLocations = userLocations;
	}
	public String getImeiNo() {
		return imeiNo;
	}
	public void setImeiNo(String imeiNo) {
		this.imeiNo = imeiNo;
	}
	public Long getSurveyUserId() {
		return surveyUserId;
	}
	public void setSurveyUserId(Long surveyUserId) {
		this.surveyUserId = surveyUserId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}
	@Override
	public String toString() {
		return "UserLocationTrackingVo [surveyUserId=" + surveyUserId
				+ ", date=" + date + ", longitude=" + longitude + ", latitude="
				+ latitude + ", insertTime=" + insertTime + ", imeiNo="
				+ imeiNo + ", uuid=" + uuid + ", userLocations="
				+ userLocations + "]";
	}
	 
     
}
