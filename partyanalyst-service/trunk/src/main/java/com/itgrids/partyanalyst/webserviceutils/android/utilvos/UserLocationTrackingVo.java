package com.itgrids.partyanalyst.webserviceutils.android.utilvos;

import java.util.Date;
import java.util.List;

public class UserLocationTrackingVo {
	
	private Long surveyUserId;
	private Date date;
	private String longitude;
	private String latitude;
	private String insertTime;
	private String imeiNo;
	private String uuid;
	private List<UserLocationTrackingVo> userLocations;
	
	
	
	
	
	
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
