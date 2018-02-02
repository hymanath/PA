package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pm_petition_type")
public class PmPetitionType {
	
private Long pmPetitionTypeId;
private String pmPetitionType;
private String isDeleted;

@Id
@Column(name="pm_petition_type_id")
@GeneratedValue(strategy= GenerationType.AUTO)
public Long getPmPetitionTypeId() {
	return pmPetitionTypeId;
}
public void setPmPetitionTypeId(Long pmPetitionTypeId) {
	this.pmPetitionTypeId = pmPetitionTypeId;
}
@Column(name="pm_petition_type")
public String getPmPetitionType() {
	return pmPetitionType;
}
public void setPmPetitionType(String pmPetitionType) {
	this.pmPetitionType = pmPetitionType;
}
@Column(name="is_deleted")
public String getIsDeleted() {
	return isDeleted;
}
public void setIsDeleted(String isDeleted) {
	this.isDeleted = isDeleted;
}
}
