package com.itgrids.partyanalyst.dto;

import java.util.List;

public class TdpCadreVolunteerVO {
private String name;
private String mobileNo;
private String email;
private String address;
private String lapTop;
private String internet;
private String tablet;
private String smartPhone;
private String 	constituencyId;
private String date;
private List<Long> constituencyIds;
private String ipad;
private String noTab;



public String getTablet() {
	return tablet;
}
public void setTablet(String tablet) {
	this.tablet = tablet;
}
public String getSmartPhone() {
	return smartPhone;
}
public void setSmartPhone(String smartPhone) {
	this.smartPhone = smartPhone;
}

public String getNoTab() {
	return noTab;
}
public void setNoTab(String noTab) {
	this.noTab = noTab;
}
public List<Long> getConstituencyIds() {
	return constituencyIds;
}
public void setConstituencyIds(List<Long> constituencyIds) {
	this.constituencyIds = constituencyIds;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getConstituencyId() {
	return constituencyId;
}
public void setConstituencyId(String constituencyId) {
	this.constituencyId = constituencyId;
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
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getLapTop() {
	return lapTop;
}
public void setLapTop(String lapTop) {
	this.lapTop = lapTop;
}
public String getInternet() {
	return internet;
}
public void setInternet(String internet) {
	this.internet = internet;
}
public String getIpad() {
	return ipad;
}
public void setIpad(String ipad) {
	this.ipad = ipad;
}



}
