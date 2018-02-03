package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pm_action_type")
public class PmActionType {
	
private Long pmActionTypeId;
private String pmActionType;
private String isDeleted;

@Id
@Column(name="pm_action_type_id")
@GeneratedValue(strategy= GenerationType.AUTO)
public Long getPmActionTypeId() {
	return pmActionTypeId;
}
public void setPmActionTypeId(Long pmActionTypeId) {
	this.pmActionTypeId = pmActionTypeId;
}
@Column(name="pm_action_type")
public String getPmActionType() {
	return pmActionType;
}
public void setPmActionType(String pmActionType) {
	this.pmActionType = pmActionType;
}
@Column(name="is_deleted")
public String getIsDeleted() {
	return isDeleted;
}
public void setIsDeleted(String isDeleted) {
	this.isDeleted = isDeleted;
}

}
