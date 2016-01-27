/**
 * @author Sravanth
 * Jan 8, 2016
 * TabDetailsVO.java
 * PA - SERV - com.itgrids.partyanalyst.dto
 */
package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	private List<String> 	availableDates=new ArrayList<String>(0);
	private String 			surveyDate;
	private Long 			noofSmsSent;
	private String 			mobileNo;
	private Long 			mobileNosCount;
	private String 			votername;
	private String			relativename;
	private String 			votercardnum;
	private String 			voterMobileNo;
	private String			rating;
	private String			boothpartno;
	
	
	
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
	public List<String> getAvailableDates() {
		return availableDates;
	}
	public void setAvailableDates(List<String> availableDates) {
		this.availableDates = availableDates;
	}
	public String getSurveyDate() {
		return surveyDate;
	}
	public void setSurveyDate(String surveyDate) {
		this.surveyDate = surveyDate;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Long getNoofSmsSent() {
		return noofSmsSent;
	}
	public void setNoofSmsSent(Long noofSmsSent) {
		this.noofSmsSent = noofSmsSent;
	}
	public Long getMobileNosCount() {
		return mobileNosCount;
	}
	public void setMobileNosCount(Long mobileNosCount) {
		this.mobileNosCount = mobileNosCount;
	}
	public String getVotername() {
		return votername;
	}
	public void setVotername(String votername) {
		this.votername = votername;
	}
	public String getRelativename() {
		return relativename;
	}
	public void setRelativename(String relativename) {
		this.relativename = relativename;
	}
	public String getVotercardnum() {
		return votercardnum;
	}
	public void setVotercardnum(String votercardnum) {
		this.votercardnum = votercardnum;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getBoothpartno() {
		return boothpartno;
	}
	public void setBoothpartno(String boothpartno) {
		this.boothpartno = boothpartno;
	}
	public String getVoterMobileNo() {
		return voterMobileNo;
	}
	public void setVoterMobileNo(String voterMobileNo) {
		this.voterMobileNo = voterMobileNo;
	}
	
	
	
}
