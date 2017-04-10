package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;


public class CadrePerformanceVO implements java.io.Serializable{

	private Long id;
	private String name;
	private CadreBasicPerformaceVO cadreBasicPerformaceVO;
	private CadreEventsVO cadreEventsVO;
	private CadreStatsVO cadreStatsVO;
	private List<CadrePerformanceVO> subList = new ArrayList<CadrePerformanceVO>(0);
	private Long attendanceId;
	private Long batchId;
	private String attendedTime;
	private String traingName;
	private String traingCamp;
	private String batchNmae;
	private Long noOfBatchDays;
	private Long noOfAttendanceDays =0l;
	private String startDate;
	private String enddate;
	private Long noOfDayBetweenDates;
	private Long noOfDaysProgram;
	private String feedBackStatus;
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
	public CadreBasicPerformaceVO getCadreBasicPerformaceVO() {
		return cadreBasicPerformaceVO;
	}
	public void setCadreBasicPerformaceVO(
			CadreBasicPerformaceVO cadreBasicPerformaceVO) {
		this.cadreBasicPerformaceVO = cadreBasicPerformaceVO;
	}
	public CadreEventsVO getCadreEventsVO() {
		return cadreEventsVO;
	}
	public void setCadreEventsVO(CadreEventsVO cadreEventsVO) {
		this.cadreEventsVO = cadreEventsVO;
	}
	public CadreStatsVO getCadreStatsVO() {
		return cadreStatsVO;
	}
	public void setCadreStatsVO(CadreStatsVO cadreStatsVO) {
		this.cadreStatsVO = cadreStatsVO;
	}
	public List<CadrePerformanceVO> getSubList() {
		return subList;
	}
	public void setSubList(List<CadrePerformanceVO> subList) {
		this.subList = subList;
	}
	public Long getAttendanceId() {
		return attendanceId;
	}
	public void setAttendanceId(Long attendanceId) {
		this.attendanceId = attendanceId;
	}
	public Long getBatchId() {
		return batchId;
	}
	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}
	public String getAttendedTime() {
		return attendedTime;
	}
	public void setAttendedTime(String attendedTime) {
		this.attendedTime = attendedTime;
	}
	public String getTraingName() {
		return traingName;
	}
	public void setTraingName(String traingName) {
		this.traingName = traingName;
	}
	public String getTraingCamp() {
		return traingCamp;
	}
	public void setTraingCamp(String traingCamp) {
		this.traingCamp = traingCamp;
	}
	public String getBatchNmae() {
		return batchNmae;
	}
	public void setBatchNmae(String batchNmae) {
		this.batchNmae = batchNmae;
	}
	public Long getNoOfBatchDays() {
		return noOfBatchDays;
	}
	public void setNoOfBatchDays(Long noOfBatchDays) {
		this.noOfBatchDays = noOfBatchDays;
	}
	public Long getNoOfAttendanceDays() {
		return noOfAttendanceDays;
	}
	public void setNoOfAttendanceDays(Long noOfAttendanceDays) {
		this.noOfAttendanceDays = noOfAttendanceDays;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public Long getNoOfDayBetweenDates() {
		return noOfDayBetweenDates;
	}
	public void setNoOfDayBetweenDates(Long noOfDayBetweenDates) {
		this.noOfDayBetweenDates = noOfDayBetweenDates;
	}
	public Long getNoOfDaysProgram() {
		return noOfDaysProgram;
	}
	public void setNoOfDaysProgram(Long noOfDaysProgram) {
		this.noOfDaysProgram = noOfDaysProgram;
	}
	public String getFeedBackStatus() {
		return feedBackStatus;
	}
	public void setFeedBackStatus(String feedBackStatus) {
		this.feedBackStatus = feedBackStatus;
	}
	
}
