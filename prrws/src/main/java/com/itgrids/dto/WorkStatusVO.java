package com.itgrids.dto;

import java.util.ArrayList;
import java.util.List;

public class WorkStatusVO {
	public Long workId;
	public Long userId;
	public Long statusTypeId;
	public String statusType;
	public Long govtWorkTypeId;
	public String govtWorkType;
	public Long govtWorkStatusId;
	public String govtWorkStatus;
	public List<String> imagesList = new ArrayList<String>(0);
	public Double totalLenght;
	public Double workLenght;
	public Double workCompletedPercentage;
	public String isCompleted;
	public String userName;
	public String date;
	public List<DocumentVO> documentList = new ArrayList<DocumentVO>(0);
	public List<WorkStatusVO> workStatusVOList = new ArrayList<WorkStatusVO>(0);
	
	public Long getWorkId() {
		return workId;
	}
	public void setWorkId(Long workId) {
		this.workId = workId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Long getStatusTypeId() {
		return statusTypeId;
	}
	public void setStatusTypeId(Long statusTypeId) {
		this.statusTypeId = statusTypeId;
	}
	public String getStatusType() {
		return statusType;
	}
	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}
	public Long getGovtWorkStatusId() {
		return govtWorkStatusId;
	}
	public void setGovtWorkStatusId(Long govtWorkStatusId) {
		this.govtWorkStatusId = govtWorkStatusId;
	}
	public List<String> getImagesList() {
		return imagesList;
	}
	public void setImagesList(List<String> imagesList) {
		this.imagesList = imagesList;
	}
	public Double getWorkLenght() {
		return workLenght;
	}
	public void setWorkLenght(Double workLenght) {
		this.workLenght = workLenght;
	}
	public Double getWorkCompletedPercentage() {
		return workCompletedPercentage;
	}
	public void setWorkCompletedPercentage(Double workCompletedPercentage) {
		this.workCompletedPercentage = workCompletedPercentage;
	}
	public String getIsCompleted() {
		return isCompleted;
	}
	public void setIsCompleted(String isCompleted) {
		this.isCompleted = isCompleted;
	}
	
	public Long getGovtWorkTypeId() {
		return govtWorkTypeId;
	}
	public void setGovtWorkTypeId(Long govtWorkTypeId) {
		this.govtWorkTypeId = govtWorkTypeId;
	}
	public String getGovtWorkType() {
		return govtWorkType;
	}
	public void setGovtWorkType(String govtWorkType) {
		this.govtWorkType = govtWorkType;
	}
	public String getGovtWorkStatus() {
		return govtWorkStatus;
	}
	public void setGovtWorkStatus(String govtWorkStatus) {
		this.govtWorkStatus = govtWorkStatus;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<DocumentVO> getDocumentList() {
		return documentList;
	}
	public void setDocumentList(List<DocumentVO> documentList) {
		this.documentList = documentList;
	}
	public List<WorkStatusVO> getWorkStatusVOList() {
		return workStatusVOList;
	}
	public void setWorkStatusVOList(List<WorkStatusVO> workStatusVOList) {
		this.workStatusVOList = workStatusVOList;
	}
	public Double getTotalLenght() {
		return totalLenght;
	}
	public void setTotalLenght(Double totalLenght) {
		this.totalLenght = totalLenght;
	}

	
}
