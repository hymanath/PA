package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AppointmentReportVO implements Serializable{
	
	private Long id;
	private String name;
	private Long requestedTotalCount;
	private Long requestedUniqueCount;
	private Long scheduleTotalCount;
	private Long scheduleUniqueCount;
	
	private Long requestedStateCount;
	private Long requestedSecondStateCount;	
	private Long uniqueStateCount;
	private Long uniqueSecondStateCount;
	
	private Long totalCount;
	private Long uniqueCount;
	
	private List<AppointmentReportVO> appointmentReportVOList = new ArrayList<AppointmentReportVO>();
	
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
	public Long getRequestedTotalCount() {
		return requestedTotalCount;
	}
	public void setRequestedTotalCount(Long requestedTotalCount) {
		this.requestedTotalCount = requestedTotalCount;
	}
	public Long getRequestedUniqueCount() {
		return requestedUniqueCount;
	}
	public void setRequestedUniqueCount(Long requestedUniqueCount) {
		this.requestedUniqueCount = requestedUniqueCount;
	}
	public Long getScheduleTotalCount() {
		return scheduleTotalCount;
	}
	public void setScheduleTotalCount(Long scheduleTotalCount) {
		this.scheduleTotalCount = scheduleTotalCount;
	}
	public Long getScheduleUniqueCount() {
		return scheduleUniqueCount;
	}
	public void setScheduleUniqueCount(Long scheduleUniqueCount) {
		this.scheduleUniqueCount = scheduleUniqueCount;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getUniqueCount() {
		return uniqueCount;
	}
	public void setUniqueCount(Long uniqueCount) {
		this.uniqueCount = uniqueCount;
	}
	public List<AppointmentReportVO> getAppointmentReportVOList() {
		return appointmentReportVOList;
	}
	public void setAppointmentReportVOList(
			List<AppointmentReportVO> appointmentReportVOList) {
		this.appointmentReportVOList = appointmentReportVOList;
	}
	public Long getRequestedStateCount() {
		return requestedStateCount;
	}
	public void setRequestedStateCount(Long requestedStateCount) {
		this.requestedStateCount = requestedStateCount;
	}
	public Long getRequestedSecondStateCount() {
		return requestedSecondStateCount;
	}
	public void setRequestedSecondStateCount(Long requestedSecondStateCount) {
		this.requestedSecondStateCount = requestedSecondStateCount;
	}
	public Long getUniqueStateCount() {
		return uniqueStateCount;
	}
	public void setUniqueStateCount(Long uniqueStateCount) {
		this.uniqueStateCount = uniqueStateCount;
	}
	public Long getUniqueSecondStateCount() {
		return uniqueSecondStateCount;
	}
	public void setUniqueSecondStateCount(Long uniqueSecondStateCount) {
		this.uniqueSecondStateCount = uniqueSecondStateCount;
	}
	
	
	
	

}
