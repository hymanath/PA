package com.itgrids.partyanalyst.dto;

public class CallStatusVO {
private Long id;
private String status;
private String locationLevel;
private Long locationLevelId;
private Long locationId;
private String meetingType;
private Long meetingTypeId;
private Long locationValue;
private String location;
private String startTime;
private String endTime;
private String meetingName;
private Long partyMeetingId;
private PartyMeetingSummaryVO docTxtInfo;
private Long constLocationNum=0l;

private String isConducted;
private String conductedDate;
private String remarks;

private String thirdPartyStatus;
private String iscommentsAvailable="false";
private String name;
private String insertedtime;
private String mobileNo;
private Long tdpCadreId;
private String memberShipNo;
private String image;
private Long count = 0l;


public Long getCount() {
	return count;
}
public void setCount(Long count) {
	this.count = count;
}
public Long getTdpCadreId() {
	return tdpCadreId;
}
public void setTdpCadreId(Long tdpCadreId) {
	this.tdpCadreId = tdpCadreId;
}
public String getMemberShipNo() {
	return memberShipNo;
}
public void setMemberShipNo(String memberShipNo) {
	this.memberShipNo = memberShipNo;
}
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
public String getMobileNo() {
	return mobileNo;
}
public void setMobileNo(String mobileNo) {
	this.mobileNo = mobileNo;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getInsertedtime() {
	return insertedtime;
}
public void setInsertedtime(String insertedtime) {
	this.insertedtime = insertedtime;
}
public String getIscommentsAvailable() {
	return iscommentsAvailable;
}
public void setIscommentsAvailable(String iscommentsAvailable) {
	this.iscommentsAvailable = iscommentsAvailable;
}
public Long getConstLocationNum() {
	return constLocationNum;
}
public void setConstLocationNum(Long constLocationNum) {
	this.constLocationNum = constLocationNum;
}
public PartyMeetingSummaryVO getDocTxtInfo() {
	return docTxtInfo;
}
public void setDocTxtInfo(PartyMeetingSummaryVO docTxtInfo) {
	this.docTxtInfo = docTxtInfo;
}
public Long getPartyMeetingId() {
	return partyMeetingId;
}
public void setPartyMeetingId(Long partyMeetingId) {
	this.partyMeetingId = partyMeetingId;
}
public Long getLocationLevelId() {
	return locationLevelId;
}
public void setLocationLevelId(Long locationLevelId) {
	this.locationLevelId = locationLevelId;
}
public String getMeetingName() {
	return meetingName;
}
public void setMeetingName(String meetingName) {
	this.meetingName = meetingName;
}
public Long getMeetingTypeId() {
	return meetingTypeId;
}
public void setMeetingTypeId(Long meetingTypeId) {
	this.meetingTypeId = meetingTypeId;
}
public Long getLocationValue() {
	return locationValue;
}
public void setLocationValue(Long locationValue) {
	this.locationValue = locationValue;
}
public String getLocation() {
	return location;
}
public void setLocation(String location) {
	this.location = location;
}
public String getStartTime() {
	return startTime;
}
public void setStartTime(String startTime) {
	this.startTime = startTime;
}
public String getEndTime() {
	return endTime;
}
public void setEndTime(String endTime) {
	this.endTime = endTime;
}
public String getMeetingType() {
	return meetingType;
}
public void setMeetingType(String meetingType) {
	this.meetingType = meetingType;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getLocationLevel() {
	return locationLevel;
}
public void setLocationLevel(String locationLevel) {
	this.locationLevel = locationLevel;
}
public Long getLocationId() {
	return locationId;
}
public void setLocationId(Long locationId) {
	this.locationId = locationId;
}
public String getIsConducted() {
	return isConducted;
}
public void setIsConducted(String isConducted) {
	this.isConducted = isConducted;
}
public String getConductedDate() {
	return conductedDate;
}
public void setConductedDate(String conductedDate) {
	this.conductedDate = conductedDate;
}
public String getRemarks() {
	return remarks;
}
public void setRemarks(String remarks) {
	this.remarks = remarks;
}
public String getThirdPartyStatus() {
	return thirdPartyStatus;
}
public void setThirdPartyStatus(String thirdPartyStatus) {
	this.thirdPartyStatus = thirdPartyStatus;
}


}
