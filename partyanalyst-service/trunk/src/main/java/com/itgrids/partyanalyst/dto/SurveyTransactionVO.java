package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class SurveyTransactionVO implements java.io.Serializable{

	private Long id;
	private String name;
	private Long yesterDayCount;
	private Long weekCount;
	private Long totalCount;
	private Long pendingCount;
	
	private Long teamSize ;
	private Long idleTeamSize;
	
	private Long submittedCount;
	private Long notSubmittedCount;
	
	private Long otpRequestCount;
	private Long otpConfirmCount;
	private Long remainingOTPCount;
	
	private Long otpNonRequestCount;
	private Long nonOTPConfirmCount;
	private Long remainingNonOTPCount;
	
	private Long actualAmount;
	private Long recordsCount;
	private Long depositedAmount;
	private Long remainingAmount;
	private String surveyDate;
	
	private Long locationId;
	private String LocationName;
	private String parliamentName;
	
	private List<SurveyTransactionVO> surveyTransactionVOList = new ArrayList<SurveyTransactionVO>(0);
	
	
	public String getParliamentName() {
		return parliamentName;
	}
	public void setParliamentName(String parliamentName) {
		this.parliamentName = parliamentName;
	}
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public String getLocationName() {
		return LocationName;
	}
	public void setLocationName(String locationName) {
		LocationName = locationName;
	}
	public String getSurveyDate() {
		return surveyDate;
	}
	public void setSurveyDate(String surveyDate) {
		this.surveyDate = surveyDate;
	}
	public Long getPendingCount() {
		return pendingCount;
	}
	public void setPendingCount(Long pendingCount) {
		this.pendingCount = pendingCount;
	}
	public Long getTeamSize() {
		return teamSize;
	}
	public void setTeamSize(Long teamSize) {
		this.teamSize = teamSize;
	}
	public Long getIdleTeamSize() {
		return idleTeamSize;
	}
	public void setIdleTeamSize(Long idleTeamSize) {
		this.idleTeamSize = idleTeamSize;
	}
	public List<SurveyTransactionVO> getSurveyTransactionVOList() {
		return surveyTransactionVOList;
	}
	public void setSurveyTransactionVOList(
			List<SurveyTransactionVO> surveyTransactionVOList) {
		this.surveyTransactionVOList = surveyTransactionVOList;
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
	public Long getYesterDayCount() {
		return yesterDayCount;
	}
	public void setYesterDayCount(Long yesterDayCount) {
		this.yesterDayCount = yesterDayCount;
	}
	public Long getWeekCount() {
		return weekCount;
	}
	public void setWeekCount(Long weekCount) {
		this.weekCount = weekCount;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getSubmittedCount() {
		return submittedCount;
	}
	public void setSubmittedCount(Long submittedCount) {
		this.submittedCount = submittedCount;
	}
	public Long getNotSubmittedCount() {
		return notSubmittedCount;
	}
	public void setNotSubmittedCount(Long notSubmittedCount) {
		this.notSubmittedCount = notSubmittedCount;
	}
	public Long getOtpRequestCount() {
		return otpRequestCount;
	}
	public void setOtpRequestCount(Long otpRequestCount) {
		this.otpRequestCount = otpRequestCount;
	}
	public Long getOtpConfirmCount() {
		return otpConfirmCount;
	}
	public void setOtpConfirmCount(Long otpConfirmCount) {
		this.otpConfirmCount = otpConfirmCount;
	}
	public Long getRemainingOTPCount() {
		return remainingOTPCount;
	}
	public void setRemainingOTPCount(Long remainingOTPCount) {
		this.remainingOTPCount = remainingOTPCount;
	}
	public Long getOtpNonRequestCount() {
		return otpNonRequestCount;
	}
	public void setOtpNonRequestCount(Long otpNonRequestCount) {
		this.otpNonRequestCount = otpNonRequestCount;
	}
	public Long getNonOTPConfirmCount() {
		return nonOTPConfirmCount;
	}
	public void setNonOTPConfirmCount(Long nonOTPConfirmCount) {
		this.nonOTPConfirmCount = nonOTPConfirmCount;
	}
	public Long getRemainingNonOTPCount() {
		return remainingNonOTPCount;
	}
	public void setRemainingNonOTPCount(Long remainingNonOTPCount) {
		this.remainingNonOTPCount = remainingNonOTPCount;
	}
	public Long getActualAmount() {
		return actualAmount;
	}
	public void setActualAmount(Long actualAmount) {
		this.actualAmount = actualAmount;
	}
	public Long getRecordsCount() {
		return recordsCount;
	}
	public void setRecordsCount(Long recordsCount) {
		this.recordsCount = recordsCount;
	}
	public Long getDepositedAmount() {
		return depositedAmount;
	}
	public void setDepositedAmount(Long depositedAmount) {
		this.depositedAmount = depositedAmount;
	}
	public Long getRemainingAmount() {
		return remainingAmount;
	}
	public void setRemainingAmount(Long remainingAmount) {
		this.remainingAmount = remainingAmount;
	}
	
}
