package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class SurveyTransactionVO implements java.io.Serializable{

	private Long id;
	private String name;
	private Long yesterDayCount;
	private Long weekCount;
	private Long totalCount;
	
	private Long submittedCount;
	private Long notSubmittedCount;
	private Long teamCount;
	
	private Long otpRequestCount;
	private Long otpConfirmCount;
	private Long remainingOTPCount;
	
	private Long otpNonRequestCount;
	private Long nonOTPConfirmCount;
	private Long remainingNonOTPCount;
	
	private Long actualAmount;
	private Long depositAmount;
	private Long recordsCount;
	private Long depositedAmount;
	private Long remainingAmount;
	
	private List<SurveyTransactionVO> surveyTransactionVOList = new ArrayList<SurveyTransactionVO>(0);
	
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
	public Long getTeamCount() {
		return teamCount;
	}
	public void setTeamCount(Long teamCount) {
		this.teamCount = teamCount;
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
	public Long getDepositAmount() {
		return depositAmount;
	}
	public void setDepositAmount(Long depositAmount) {
		this.depositAmount = depositAmount;
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
