package com.itgrids.partyanalyst.dto;

import java.io.Serializable;






public class StateVO implements Serializable{
	

 private String stateName;
 private Long stateId;
 private Long districtId;
 private String districtName;
public String getStateName() {
	return stateName;
}
public void setStateName(String stateName) {
	this.stateName = stateName;
}
public Long getStateId() {
	return stateId;
}
public void setStateId(Long stateId) {
	this.stateId = stateId;
}
public Long getDistrictId() {
	return districtId;
}
public void setDistrictId(Long districtId) {
	this.districtId = districtId;
}
public String getDistrictName() {
	return districtName;
}
public void setDistrictName(String districtName) {
	this.districtName = districtName;
}
 

 
}