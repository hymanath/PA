package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class PeshiAppAppointmentVO implements Serializable{

	private Long id;
	private String name;
	private Long todayAppoinmentsCount = 0l;
	private Long secheduledCount = 0l;
	private Long completedCount = 0l;
	private Long notCompletedCount = 0l;
	private Long generalVisitorsCount = 0l;
	private String relativeName;
	private Long memberShipId;
	private String mobileNo;
	private String image;
	private Long appoinmentId;
	private String appDescription;
	private String appTitle;
	private String appDate;
	private String appTime;
	private String presentStatus;
	
	
	private List<PeshiAppAppointmentVO> scheduledList;
	private List<PeshiAppLoginVO> completedList;
	private List<PeshiAppLoginVO> todayAppoinmentsList;
	private List<PeshiAppLoginVO> notCompletedList;
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
	public Long getTodayAppoinmentsCount() {
		return todayAppoinmentsCount;
	}
	public void setTodayAppoinmentsCount(Long todayAppoinmentsCount) {
		this.todayAppoinmentsCount = todayAppoinmentsCount;
	}
	public Long getSecheduledCount() {
		return secheduledCount;
	}
	public void setSecheduledCount(Long secheduledCount) {
		this.secheduledCount = secheduledCount;
	}
	public Long getCompletedCount() {
		return completedCount;
	}
	public void setCompletedCount(Long completedCount) {
		this.completedCount = completedCount;
	}
	public Long getNotCompletedCount() {
		return notCompletedCount;
	}
	public void setNotCompletedCount(Long notCompletedCount) {
		this.notCompletedCount = notCompletedCount;
	}
	public Long getGeneralVisitorsCount() {
		return generalVisitorsCount;
	}
	public void setGeneralVisitorsCount(Long generalVisitorsCount) {
		this.generalVisitorsCount = generalVisitorsCount;
	}
	public String getRelativeName() {
		return relativeName;
	}
	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}
	public Long getMemberShipId() {
		return memberShipId;
	}
	public void setMemberShipId(Long memberShipId) {
		this.memberShipId = memberShipId;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Long getAppoinmentId() {
		return appoinmentId;
	}
	public void setAppoinmentId(Long appoinmentId) {
		this.appoinmentId = appoinmentId;
	}
	public String getAppDescription() {
		return appDescription;
	}
	public void setAppDescription(String appDescription) {
		this.appDescription = appDescription;
	}
	public String getAppTitle() {
		return appTitle;
	}
	public void setAppTitle(String appTitle) {
		this.appTitle = appTitle;
	}
	public String getAppDate() {
		return appDate;
	}
	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}
	public String getAppTime() {
		return appTime;
	}
	public void setAppTime(String appTime) {
		this.appTime = appTime;
	}
	public String getPresentStatus() {
		return presentStatus;
	}
	public void setPresentStatus(String presentStatus) {
		this.presentStatus = presentStatus;
	}
	public List<PeshiAppAppointmentVO> getScheduledList() {
		return scheduledList;
	}
	public void setScheduledList(List<PeshiAppAppointmentVO> scheduledList) {
		this.scheduledList = scheduledList;
	}
	public List<PeshiAppLoginVO> getCompletedList() {
		return completedList;
	}
	public void setCompletedList(List<PeshiAppLoginVO> completedList) {
		this.completedList = completedList;
	}
	public List<PeshiAppLoginVO> getTodayAppoinmentsList() {
		return todayAppoinmentsList;
	}
	public void setTodayAppoinmentsList(List<PeshiAppLoginVO> todayAppoinmentsList) {
		this.todayAppoinmentsList = todayAppoinmentsList;
	}
	public List<PeshiAppLoginVO> getNotCompletedList() {
		return notCompletedList;
	}
	public void setNotCompletedList(List<PeshiAppLoginVO> notCompletedList) {
		this.notCompletedList = notCompletedList;
	}
}
