package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class TdpCadreVolunteerVO  implements java.io.Serializable{
private Long id;
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


private String tablet3G;
private String smartPhone3G;
private String ipad3G;

private List<TdpCadreVolunteerVO> tdpCadreVolunteerVOList = new ArrayList<TdpCadreVolunteerVO>(0);
private List<String> datesList = new ArrayList<String>(0);


public String getTablet3G() {
	return tablet3G;
}
public void setTablet3G(String tablet3g) {
	tablet3G = tablet3g;
}
public String getSmartPhone3G() {
	return smartPhone3G;
}
public void setSmartPhone3G(String smartPhone3G) {
	this.smartPhone3G = smartPhone3G;
}
public String getIpad3G() {
	return ipad3G;
}
public void setIpad3G(String ipad3g) {
	ipad3G = ipad3g;
}
public List<String> getDatesList() {
	return datesList;
}
public void setDatesList(List<String> datesList) {
	this.datesList = datesList;
}
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


public List<TdpCadreVolunteerVO> getTdpCadreVolunteerVOList() {
	return tdpCadreVolunteerVOList;
}
public void setTdpCadreVolunteerVOList(
		List<TdpCadreVolunteerVO> tdpCadreVolunteerVOList) {
	this.tdpCadreVolunteerVOList = tdpCadreVolunteerVOList;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}

}
