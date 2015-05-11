package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserEventDetailsVO implements Serializable{
	
	private Long id;
	private String eventName;
	private Long mainEventId;
	private String userName;
	private String userPassword;
	private List<UserEventDetailsVO> subList = new ArrayList<UserEventDetailsVO>();
	private String status;
	private String IMEI;
	private String SIMCardNumber;
	private String loginTimeStamp;

	private String RFID;
	private String memberShipNo;
	private Long eventId;
	private Long userId;
	private String startTime;
	private String endTime;
	private Long tabPrimaryKey;
	private String startDate;
	private String endDate;
	private Long sectorNo;
	private Long blockNo;
	private Long entryLimit;	
	private String serverWorkMode;
	private String tabWorkMode;
	private String regText;
	private Long orderNo;
	
	
	
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public Long getMainEventId() {
		return mainEventId;
	}
	public void setMainEventId(Long mainEventId) {
		this.mainEventId = mainEventId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	public Long getSectorNo() {
		return sectorNo;
	}
	public void setSectorNo(Long sectorNo) {
		this.sectorNo = sectorNo;
	}
	public Long getBlockNo() {
		return blockNo;
	}
	public void setBlockNo(Long blockNo) {
		this.blockNo = blockNo;
	}
	public Long getEntryLimit() {
		return entryLimit;
	}
	public void setEntryLimit(Long entryLimit) {
		this.entryLimit = entryLimit;
	}
	public String getServerWorkMode() {
		return serverWorkMode;
	}
	public void setServerWorkMode(String serverWorkMode) {
		this.serverWorkMode = serverWorkMode;
	}
	public String getTabWorkMode() {
		return tabWorkMode;
	}
	public void setTabWorkMode(String tabWorkMode) {
		this.tabWorkMode = tabWorkMode;
	}
	public String getRegText() {
		return regText;
	}
	public void setRegText(String regText) {
		this.regText = regText;
	}
	public Long getTabPrimaryKey() {
		return tabPrimaryKey;
	}
	public void setTabPrimaryKey(Long tabPrimaryKey) {
		this.tabPrimaryKey = tabPrimaryKey;
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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public String getMemberShipNo() {
		return memberShipNo;
	}
	public void setMemberShipNo(String memberShipNo) {
		this.memberShipNo = memberShipNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<UserEventDetailsVO> getSubList() {
		return subList;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	
	public String getSIMCardNumber() {
		return SIMCardNumber;
	}
	public void setSIMCardNumber(String sIMCardNumber) {
		SIMCardNumber = sIMCardNumber;
	}
	
	public String getIMEI() {
		return IMEI;
	}
	public void setIMEI(String iMEI) {
		IMEI = iMEI;
	}
	public String getLoginTimeStamp() {
		return loginTimeStamp;
	}
	public void setLoginTimeStamp(String loginTimeStamp) {
		this.loginTimeStamp = loginTimeStamp;
	}
	public String getRFID() {
		return RFID;
	}
	public void setRFID(String rFID) {
		RFID = rFID;
	}
	public void setSubList(List<UserEventDetailsVO> subList) {
		this.subList = subList;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	

}
