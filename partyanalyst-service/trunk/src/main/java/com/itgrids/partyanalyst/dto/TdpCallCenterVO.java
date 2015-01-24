package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class TdpCallCenterVO {
private Long id;
private String name;
private String date;
private String time;
private String purpose;
private String suggestions;
private Long executiveId;
private String executiveName;
private String image;
private Long userAddressId;
private String constituency;
private String mandal;
private String district;
private String panchayat;
private String municipality;
private String membership;
private String mobile;
private String status;
private Long total=0l;
private Long todayTotal =0l;
private List<TdpCallCenterVO> subList = new ArrayList<TdpCallCenterVO>();

public List<TdpCallCenterVO> getSubList() {
	return subList;
}
public void setSubList(List<TdpCallCenterVO> subList) {
	this.subList = subList;
}
public Long getTotal() {
	return total;
}
public void setTotal(Long total) {
	this.total = total;
}
public Long getTodayTotal() {
	return todayTotal;
}
public void setTodayTotal(Long todayTotal) {
	this.todayTotal = todayTotal;
}

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
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}
public String getPurpose() {
	return purpose;
}
public void setPurpose(String purpose) {
	this.purpose = purpose;
}
public String getSuggestions() {
	return suggestions;
}
public void setSuggestions(String suggestions) {
	this.suggestions = suggestions;
}
public Long getExecutiveId() {
	return executiveId;
}
public void setExecutiveId(Long executiveId) {
	this.executiveId = executiveId;
}
public String getExecutiveName() {
	return executiveName;
}
public void setExecutiveName(String executiveName) {
	this.executiveName = executiveName;
}
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
public Long getUserAddressId() {
	return userAddressId;
}
public void setUserAddressId(Long userAddressId) {
	this.userAddressId = userAddressId;
}
public String getConstituency() {
	return constituency;
}
public void setConstituency(String constituency) {
	this.constituency = constituency;
}
public String getMandal() {
	return mandal;
}
public void setMandal(String mandal) {
	this.mandal = mandal;
}
public String getDistrict() {
	return district;
}
public void setDistrict(String district) {
	this.district = district;
}
public String getPanchayat() {
	return panchayat;
}
public void setPanchayat(String panchayat) {
	this.panchayat = panchayat;
}
public String getMunicipality() {
	return municipality;
}
public void setMunicipality(String municipality) {
	this.municipality = municipality;
}
public String getMembership() {
	return membership;
}
public void setMembership(String membership) {
	this.membership = membership;
}
public String getMobile() {
	return mobile;
}
public void setMobile(String mobile) {
	this.mobile = mobile;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}


}
