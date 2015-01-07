package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "tdp_cadre_image_sink_data")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCadreImageSinkData {
private Long tdpCadreImageSinkDataId;
private String uuid;
private String imageStr;
private Long tdpCadreId;
private String status;
private Long userId;
private String imeiNo;
@Id
@GeneratedValue (strategy = GenerationType.AUTO)
@Column(name = "tdp_cadre_image_sink_data_id", nullable = false, unique = true)
public Long getTdpCadreImageSinkDataId() {
	return tdpCadreImageSinkDataId;
}
public void setTdpCadreImageSinkDataId(Long tdpCadreImageSinkDataId) {
	this.tdpCadreImageSinkDataId = tdpCadreImageSinkDataId;
}
@Column(name = "uuid")
public String getUuid() {
	return uuid;
}
public void setUuid(String uuid) {
	this.uuid = uuid;
}
@Column(name = "imageStr")
public String getImageStr() {
	return imageStr;
}
public void setImageStr(String imageStr) {
	this.imageStr = imageStr;
}
@Column(name = "tdp_cadre_id")
public Long getTdpCadreId() {
	return tdpCadreId;
}
public void setTdpCadreId(Long tdpCadreId) {
	this.tdpCadreId = tdpCadreId;
}
@Column(name = "status")
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
@Column(name = "user_id")
public Long getUserId() {
	return userId;
}
public void setUserId(Long userId) {
	this.userId = userId;
}
@Column(name = "imeiNo")
public String getImeiNo() {
	return imeiNo;
}
public void setImeiNo(String imeiNo) {
	this.imeiNo = imeiNo;
}




}
