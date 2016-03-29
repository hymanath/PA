package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class AppointmentDetailsVO implements Serializable{
	
	private Long    appointmentId;
	private Long    candidateId;
	private String  name;
	private String  priority;
	private String  status;
	private String  subject;
	private String  dateString;
	private String  apptpreferableDates;
	
	private String  designation;
	private boolean isCadre;
	private String  constituency;
	private String  mobileNo;
	
	private Long requestCount=0l;
	private Map<Long,AppointmentDetailsVO> subMap;
	private List<AppointmentDetailsVO> subList;
	private List<IdNameVO> statusList;
	
	private boolean isLabeled;
	private String  lastVisit;
    private String  apptStatus;
    
    private String fromDateStr;
    private String toDateStr;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDateString() {
		return dateString;
	}
	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public boolean isCadre() {
		return isCadre;
	}
	public void setCadre(boolean isCadre) {
		this.isCadre = isCadre;
	}
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Map<Long, AppointmentDetailsVO> getSubMap() {
		return subMap;
	}
	public void setSubMap(Map<Long, AppointmentDetailsVO> subMap) {
		this.subMap = subMap;
	}
	public String getApptpreferableDates() {
		return apptpreferableDates;
	}
	public void setApptpreferableDates(String apptpreferableDates) {
		this.apptpreferableDates = apptpreferableDates;
	}
	public Long getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}
	public Long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
	public List<AppointmentDetailsVO> getSubList() {
		return subList;
	}
	public void setSubList(List<AppointmentDetailsVO> subList) {
		this.subList = subList;
	}
	public List<IdNameVO> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<IdNameVO> statusList) {
		this.statusList = statusList;
	}
	public Long getRequestCount() {
		return requestCount;
	}
	public void setRequestCount(Long requestCount) {
		this.requestCount = requestCount;
	}
	public boolean isLabeled() {
		return isLabeled;
	}
	public void setLabeled(boolean isLabeled) {
		this.isLabeled = isLabeled;
	}
	public String getLastVisit() {
		return lastVisit;
	}
	public void setLastVisit(String lastVisit) {
		this.lastVisit = lastVisit;
	}
	public String getApptStatus() {
		return apptStatus;
	}
	public void setApptStatus(String apptStatus) {
		this.apptStatus = apptStatus;
	}
	public String getFromDateStr() {
		return fromDateStr;
	}
	public void setFromDateStr(String fromDateStr) {
		this.fromDateStr = fromDateStr;
	}
	public String getToDateStr() {
		return toDateStr;
	}
	public void setToDateStr(String toDateStr) {
		this.toDateStr = toDateStr;
	}
    	
	
}
