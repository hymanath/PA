/**
 * @author Sasi
 * Jan 12, 2016
 * attendanceWSVO.java
 * PA - SERV - com.itgrids.partyanalyst.dto
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Sasi
 * @date Jan 12, 2016
 */
public class AttendanceQuestionnariWSVO implements Serializable {
	
		private String			name;
		private String			mobileNo;
		private String			voterId;
		private String			membershipNo;
		private String			uniqueKey;
		private Long			tdpCadreId;
		private String			attendedTime;
		private String			imei;
		private String			langitude;
		private String			latitude;
		private Long 			activityLocationInfoId;
		
		private String 			insertedTime;
		private Long 			tabUserId;
		private String 			syncSource;
		private Long 			insertedBy;
		private Long 			tabPrimaryKey;
		private Long 			webPrimaryKey;
		private String 			status;
		
		private Long			questionnairId;
		private Long			optionId;
		private String			optionTxt;
		
		private Long			bloodGroupId;
		private String 			userType;
		private String 			activityDate;
		private String 			rfid;
		private Long 			currentTabUserId;
		
		
		
		
		public Long getCurrentTabUserId() {
			return currentTabUserId;
		}
		public void setCurrentTabUserId(Long currentTabUserId) {
			this.currentTabUserId = currentTabUserId;
		}
		public Long getQuestionnairId() {
			return questionnairId;
		}
		public void setQuestionnairId(Long questionnairId) {
			this.questionnairId = questionnairId;
		}
		public Long getOptionId() {
			return optionId;
		}
		public void setOptionId(Long optionId) {
			this.optionId = optionId;
		}
		public String getOptionTxt() {
			return optionTxt;
		}
		public void setOptionTxt(String optionTxt) {
			this.optionTxt = optionTxt;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getMobileNo() {
			return mobileNo;
		}
		public void setMobileNo(String mobileNo) {
			this.mobileNo = mobileNo;
		}
		public String getVoterId() {
			return voterId;
		}
		public void setVoterId(String voterId) {
			this.voterId = voterId;
		}
		public String getMembershipNo() {
			return membershipNo;
		}
		public void setMembershipNo(String membershipNo) {
			this.membershipNo = membershipNo;
		}
		public String getUniqueKey() {
			return uniqueKey;
		}
		public void setUniqueKey(String uniqueKey) {
			this.uniqueKey = uniqueKey;
		}
		public Long getTdpCadreId() {
			return tdpCadreId;
		}
		public void setTdpCadreId(Long tdpCadreId) {
			this.tdpCadreId = tdpCadreId;
		}
		public String getAttendedTime() {
			return attendedTime;
		}
		public void setAttendedTime(String attendedTime) {
			this.attendedTime = attendedTime;
		}
		public String getImei() {
			return imei;
		}
		public void setImei(String imei) {
			this.imei = imei;
		}
		public String getLangitude() {
			return langitude;
		}
		public void setLangitude(String langitude) {
			this.langitude = langitude;
		}
		public String getLatitude() {
			return latitude;
		}
		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}
		public Long getActivityLocationInfoId() {
			return activityLocationInfoId;
		}
		public void setActivityLocationInfoId(Long activityLocationInfoId) {
			this.activityLocationInfoId = activityLocationInfoId;
		}
		public String getInsertedTime() {
			return insertedTime;
		}
		public void setInsertedTime(String insertedTime) {
			this.insertedTime = insertedTime;
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
		public Long getWebPrimaryKey() {
			return webPrimaryKey;
		}
		public void setWebPrimaryKey(Long webPrimaryKey) {
			this.webPrimaryKey = webPrimaryKey;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public Long getBloodGroupId() {
			return bloodGroupId;
		}
		public void setBloodGroupId(Long bloodGroupId) {
			this.bloodGroupId = bloodGroupId;
		}
		public String getUserType() {
			return userType;
		}
		public void setUserType(String userType) {
			this.userType = userType;
		}
		public String getActivityDate() {
			return activityDate;
		}
		public void setActivityDate(String activityDate) {
			this.activityDate = activityDate;
		}
		public String getRfid() {
			return rfid;
		}
		public void setRfid(String rfid) {
			this.rfid = rfid;
		}
		
		
}
