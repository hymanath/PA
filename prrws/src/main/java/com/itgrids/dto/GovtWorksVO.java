package com.itgrids.dto;

import java.util.ArrayList;
import java.util.List;

public class GovtWorksVO {
	private Long workTypeId;
	private String workType;
	private Long locationScopeId;
	private String locationScope;
	private Long locationValue;
	private String location;
	
	private List<GovtWorksVO> locationsScopeList = new ArrayList<GovtWorksVO>(0);
	private List<GovtWorksVO> locationsList = new ArrayList<GovtWorksVO>(0);
	private List<GovtWorksVO> worksList = new ArrayList<GovtWorksVO>(0);
	private List<GovtWorksVO> statusList = new ArrayList<GovtWorksVO>(0);
	private Long workId,count;
	private String workName,workZoneName;
	private Double workLenght;
	private Double completedLength;
	private Double completedPercentage,completedKmsPercentage;
	private Long statusId;
	private String status;
	private Double fundAllocated;
	private String lattitude,longitude;
	private Long userId;
	private List<String> imagesList = new ArrayList<String>(0);
	private Long govtMainWorkId;
	private String govtMainWork;
	private Long totalWorks=0l,statusWorks=0l;
	private Double totalKms=0.00,statusKms=0.00,totalAvgKms=0.00,totalAvgPerc=0.00;
	private Long sanctionAmt=0l,target=0l;
	private String date;
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public Long getTarget() {
		return target;
	}
	public void setTarget(Long target) {
		this.target = target;
	}
	public Long getSanctionAmt() {
		return sanctionAmt;
	}
	public void setSanctionAmt(Long sanctionAmt) {
		this.sanctionAmt = sanctionAmt;
	}
	public Long getLocationScopeId() {
		return locationScopeId;
	}
	public void setLocationScopeId(Long locationScopeId) {
		this.locationScopeId = locationScopeId;
	}
	public String getLocationScope() {
		return locationScope;
	}
	public void setLocationScope(String locationScope) {
		this.locationScope = locationScope;
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
	public Long getWorkTypeId() {
		return workTypeId;
	}
	public void setWorkTypeId(Long workTypeId) {
		this.workTypeId = workTypeId;
	}
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}
	public List<GovtWorksVO> getLocationsScopeList() {
		return locationsScopeList;
	}
	public void setLocationsScopeList(List<GovtWorksVO> locationsScopeList) {
		this.locationsScopeList = locationsScopeList;
	}
	public List<GovtWorksVO> getLocationsList() {
		return locationsList;
	}
	public void setLocationsList(List<GovtWorksVO> locationsList) {
		this.locationsList = locationsList;
	}
	public List<GovtWorksVO> getWorksList() {
		return worksList;
	}
	public void setWorksList(List<GovtWorksVO> worksList) {
		this.worksList = worksList;
	}
	public Long getWorkId() {
		return workId;
	}
	public void setWorkId(Long workId) {
		this.workId = workId;
	}
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	public Double getWorkLenght() {
		return workLenght;
	}
	public void setWorkLenght(Double workLenght) {
		this.workLenght = workLenght;
	}
	public Double getCompletedPercentage() {
		return completedPercentage;
	}
	public void setCompletedPercentage(Double completedPercentage) {
		this.completedPercentage = completedPercentage;
	}
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getWorkZoneName() {
		return workZoneName;
	}
	public void setWorkZoneName(String workZoneName) {
		this.workZoneName = workZoneName;
	}
	public Double getFundAllocated() {
		return fundAllocated;
	}
	public void setFundAllocated(Double fundAllocated) {
		this.fundAllocated = fundAllocated;
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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public List<String> getImagesList() {
		return imagesList;
	}
	public void setImagesList(List<String> imagesList) {
		this.imagesList = imagesList;
	}
	public Long getGovtMainWorkId() {
		return govtMainWorkId;
	}
	public void setGovtMainWorkId(Long govtMainWorkId) {
		this.govtMainWorkId = govtMainWorkId;
	}
	public Double getCompletedLength() {
		return completedLength;
	}
	public void setCompletedLength(Double completedLength) {
		this.completedLength = completedLength;
	}
	public String getGovtMainWork() {
		return govtMainWork;
	}
	public void setGovtMainWork(String govtMainWork) {
		this.govtMainWork = govtMainWork;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getTotalWorks() {
		return totalWorks;
	}
	public void setTotalWorks(Long totalWorks) {
		this.totalWorks = totalWorks;
	}
	public Long getStatusWorks() {
		return statusWorks;
	}
	public void setStatusWorks(Long statusWorks) {
		this.statusWorks = statusWorks;
	}
	public Double getTotalKms() {
		return totalKms;
	}
	public void setTotalKms(Double totalKms) {
		this.totalKms = totalKms;
	}
	public Double getStatusKms() {
		return statusKms;
	}
	public void setStatusKms(Double statusKms) {
		this.statusKms = statusKms;
	}
	public Double getCompletedKmsPercentage() {
		return completedKmsPercentage;
	}
	public void setCompletedKmsPercentage(Double completedKmsPercentage) {
		this.completedKmsPercentage = completedKmsPercentage;
	}
	public List<GovtWorksVO> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<GovtWorksVO> statusList) {
		this.statusList = statusList;
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
	
	
}
