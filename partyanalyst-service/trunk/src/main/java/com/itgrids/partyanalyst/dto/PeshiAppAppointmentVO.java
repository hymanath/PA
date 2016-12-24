package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class PeshiAppAppointmentVO implements Serializable{

	private Long id;
	private String name;
	private Long totalAppoinmentsCount = 0l;
	private Long secheduledCount = 0l;
	private Long completedCount = 0l;
	private Long notCompletedCount = 0l;
	private Long generalVisitorsCount = 0l;
	private String relativeName;
	private String memberShipId;
	private String mobileNo;
	private String image;
	private Long appoinmentId;
	private String appDescription;
	private String appTitle;
	private String appDate;
	private String appTime;
	private String presentStatus;
	private Long appointmentStatusId;
	
	private List<PeshiAppAppointmentVO> scheduledList;
	private List<PeshiAppAppointmentVO> completedList;
	private List<PeshiAppAppointmentVO> totalAppoinmentsList;
	private List<PeshiAppAppointmentVO> notAttendedList;
	
	
	
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
	public Long getTotalAppoinmentsCount() {
		return totalAppoinmentsCount;
	}
	public void setTotalAppoinmentsCount(Long totalAppoinmentsCount) {
		this.totalAppoinmentsCount = totalAppoinmentsCount;
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
	public String getMemberShipId() {
		return memberShipId;
	}
	public void setMemberShipId(String memberShipId) {
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
	public List<PeshiAppAppointmentVO> getCompletedList() {
		return completedList;
	}
	public void setCompletedList(List<PeshiAppAppointmentVO> completedList) {
		this.completedList = completedList;
	}
	public void setNotAttendedList(List<PeshiAppAppointmentVO> notAttendedList) {
		this.notAttendedList = notAttendedList;
	}
	public List<PeshiAppAppointmentVO> getNotAttendedList() {
		return notAttendedList;
	}
	public void setNotCompletedList(List<PeshiAppAppointmentVO> notAttendedList) {
		this.notAttendedList = notAttendedList;
	}
	public Long getAppointmentStatusId() {
		return appointmentStatusId;
	}
	public void setAppointmentStatusId(Long appointmentStatusId) {
		this.appointmentStatusId = appointmentStatusId;
	}
	public List<PeshiAppAppointmentVO> getTotalAppoinmentsList() {
		return totalAppoinmentsList;
	}
	public void setTotalAppoinmentsList(List<PeshiAppAppointmentVO> totalAppoinmentsList) {
		this.totalAppoinmentsList = totalAppoinmentsList;
	}
	
}
