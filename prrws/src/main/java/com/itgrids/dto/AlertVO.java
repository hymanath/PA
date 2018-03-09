package com.itgrids.dto;

import java.util.ArrayList;

import java.util.List;

public class AlertVO implements java.io.Serializable{

	private Long alertTypeId;
	private String alertTypeName;
	private Long locationLevelId;
	private Long locationValue;
	private String candidateId;
	private String color;
	
	private Long id,monthId;
	private String name,monthName;
	private Long statusId;
	private String status;
	private Long count = 0l;
	private String category;
	private Long categoryId;
	private Long categoryCount = 0l;
	private Long locationId;
	private String locationName;
	
	
	private Long alertId;
	private String clarificationRequired;
	private String fileIdList;
	private Double statusPercent = 0.0d;
	private String assignedDate;
	private Double percentage = 0.0d,posPerc=0.0,negPerc=0.0;
	private Long alertCnt = 0l;
	private String createdTime;
    private String year;
    private String fromDate;
    private String toDate;
    private int startIndex;
	private int endIndex;
	private String type;
	private Long posCount=0l,negCount=0l,totalNewsCnt=0l;
	private Long locationCnt=0l;
	private Long statusCount=0l;
	private Long count1 = 0l,printCount=0l,electCount=0l,feedbackCount=0l;
	private String comment;
	
	private List<AlertVO> subList1 = new ArrayList<AlertVO>(0);
	private List<AlertVO> subList2 = new ArrayList<AlertVO>(0);
	private List<AlertVO> list = new ArrayList<AlertVO>(0);
	
	public Long getFeedbackCount() {
		return feedbackCount;
	}
	public void setFeedbackCount(Long feedbackCount) {
		this.feedbackCount = feedbackCount;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Long getTotalNewsCnt() {
		return totalNewsCnt;
	}
	public void setTotalNewsCnt(Long totalNewsCnt) {
		this.totalNewsCnt = totalNewsCnt;
	}
	public Long getPrintCount() {
		return printCount;
	}
	public void setPrintCount(Long printCount) {
		this.printCount = printCount;
	}
	public Long getElectCount() {
		return electCount;
	}
	public void setElectCount(Long electCount) {
		this.electCount = electCount;
	}
	public Long getMonthId() {
		return monthId;
	}
	public void setMonthId(Long monthId) {
		this.monthId = monthId;
	}
	public String getMonthName() {
		return monthName;
	}
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
	public Long getAlertTypeId() {
		return alertTypeId;
	}
	public void setAlertTypeId(Long alertTypeId) {
		this.alertTypeId = alertTypeId;
	}
	public String getAlertTypeName() {
		return alertTypeName;
	}
	public void setAlertTypeName(String alertTypeName) {
		this.alertTypeName = alertTypeName;
	}
	public Long getLocationLevelId() {
		return locationLevelId;
	}
	public void setLocationLevelId(Long locationLevelId) {
		this.locationLevelId = locationLevelId;
	}
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	public String getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
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
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public Long getCategoryCount() {
		return categoryCount;
	}
	public void setCategoryCount(Long categoryCount) {
		this.categoryCount = categoryCount;
	}
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public Long getAlertId() {
		return alertId;
	}
	public void setAlertId(Long alertId) {
		this.alertId = alertId;
	}
	public String getClarificationRequired() {
		return clarificationRequired;
	}
	public void setClarificationRequired(String clarificationRequired) {
		this.clarificationRequired = clarificationRequired;
	}
	public String getFileIdList() {
		return fileIdList;
	}
	public void setFileIdList(String fileIdList) {
		this.fileIdList = fileIdList;
	}
	public Double getStatusPercent() {
		return statusPercent;
	}
	public void setStatusPercent(Double statusPercent) {
		this.statusPercent = statusPercent;
	}
	public String getAssignedDate() {
		return assignedDate;
	}
	public void setAssignedDate(String assignedDate) {
		this.assignedDate = assignedDate;
	}
	public Double getPercentage() {
		return percentage;
	}
	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
	public Double getPosPerc() {
		return posPerc;
	}
	public void setPosPerc(Double posPerc) {
		this.posPerc = posPerc;
	}
	public Double getNegPerc() {
		return negPerc;
	}
	public void setNegPerc(Double negPerc) {
		this.negPerc = negPerc;
	}
	public Long getAlertCnt() {
		return alertCnt;
	}
	public void setAlertCnt(Long alertCnt) {
		this.alertCnt = alertCnt;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getPosCount() {
		return posCount;
	}
	public void setPosCount(Long posCount) {
		this.posCount = posCount;
	}
	public Long getNegCount() {
		return negCount;
	}
	public void setNegCount(Long negCount) {
		this.negCount = negCount;
	}
	public Long getLocationCnt() {
		return locationCnt;
	}
	public void setLocationCnt(Long locationCnt) {
		this.locationCnt = locationCnt;
	}
	public Long getStatusCount() {
		return statusCount;
	}
	public void setStatusCount(Long statusCount) {
		this.statusCount = statusCount;
	}
	public Long getCount1() {
		return count1;
	}
	public void setCount1(Long count1) {
		this.count1 = count1;
	}
	public List<AlertVO> getSubList1() {
		return subList1;
	}
	public void setSubList1(List<AlertVO> subList1) {
		this.subList1 = subList1;
	}
	public List<AlertVO> getSubList2() {
		return subList2;
	}
	public void setSubList2(List<AlertVO> subList2) {
		this.subList2 = subList2;
	}
	public List<AlertVO> getList() {
		return list;
	}
	public void setList(List<AlertVO> list) {
		this.list = list;
	}
}
