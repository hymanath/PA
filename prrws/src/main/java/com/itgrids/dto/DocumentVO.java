package com.itgrids.dto;

import java.util.ArrayList;
import java.util.List;

public class DocumentVO {
	private Long documentId;
	private String documentName;
	private String path;
	private String base64Str;
	private Long userId;
	private String userName;
	private String insertedTime;
	private Double kms=0.00;
	private List<DocumentVO> list = new ArrayList<DocumentVO>(0);
	private Double completedPercentage=0.00,completedKms = 0.00;
	private Long districtId,divisionId,subDivisionId,panchayatId,mandalId;
	private String districtName,divisionName,subDivisionName,panchayatName,mandalName;
	private List<String> datesList = new ArrayList<String>(0);
	private String lattitude,longitude;
	private Double totalAvgKms = 0.00,totalAvgPerc = 0.00;
	
	public Long getDocumentId() {
		return documentId;
	}
	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getBase64Str() {
		return base64Str;
	}
	public void setBase64Str(String base64Str) {
		this.base64Str = base64Str;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(String insertedTime) {
		this.insertedTime = insertedTime;
	}
	public Double getKms() {
		return kms;
	}
	public void setKms(Double kms) {
		this.kms = kms;
	}
	public List<DocumentVO> getList() {
		return list;
	}
	public void setList(List<DocumentVO> list) {
		this.list = list;
	}
	public Double getCompletedPercentage() {
		return completedPercentage;
	}
	public void setCompletedPercentage(Double completedPercentage) {
		this.completedPercentage = completedPercentage;
	}
	public Long getMandalId() {
		return mandalId;
	}
	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	public String getPanchayatName() {
		return panchayatName;
	}
	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	public List<String> getDatesList() {
		return datesList;
	}
	public void setDatesList(List<String> datesList) {
		this.datesList = datesList;
	}
	public String getLattitude() {
		return lattitude;
	}
	public void setLattitude(String lattitude) {
		this.lattitude = lattitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public Double getTotalAvgKms() {
		return totalAvgKms;
	}
	public void setTotalAvgKms(Double totalAvgKms) {
		this.totalAvgKms = totalAvgKms;
	}
	public Double getTotalAvgPerc() {
		return totalAvgPerc;
	}
	public void setTotalAvgPerc(Double totalAvgPerc) {
		this.totalAvgPerc = totalAvgPerc;
	}
	public Double getCompletedKms() {
		return completedKms;
	}
	public void setCompletedKms(Double completedKms) {
		this.completedKms = completedKms;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public Long getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(Long divisionId) {
		this.divisionId = divisionId;
	}
	public Long getSubDivisionId() {
		return subDivisionId;
	}
	public void setSubDivisionId(Long subDivisionId) {
		this.subDivisionId = subDivisionId;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public String getSubDivisionName() {
		return subDivisionName;
	}
	public void setSubDivisionName(String subDivisionName) {
		this.subDivisionName = subDivisionName;
	}
	
	
}
