package com.itgrids.partyanalyst.dto;

public class SurveyTransactionReportVO {

	private Long totalRecords ;
	private Long todayRecords ;
	private Long totalUsers;
	private Long todayUsers;
	private Long yesterDayUsers;
	private Long otpReqCount;
	private Long todayMoneyCollected;
	private Long yesterDayMoneyCollected;
	private Long todayPaidAmount;
	private Long yesterDayPaidAmount;
	private Long todayPendingAmount;
	private Long yesterDayPendingAmount;
	private Long todayotpTransactionCompleted;
	private Long todayotpTransactionPending;
	
	public Long getTodayotpTransactionCompleted() {
		return todayotpTransactionCompleted;
	}
	public void setTodayotpTransactionCompleted(Long todayotpTransactionCompleted) {
		this.todayotpTransactionCompleted = todayotpTransactionCompleted;
	}
	public Long getTodayotpTransactionPending() {
		return todayotpTransactionPending;
	}
	public void setTodayotpTransactionPending(Long todayotpTransactionPending) {
		this.todayotpTransactionPending = todayotpTransactionPending;
	}
	public Long getTodayPendingAmount() {
		return todayPendingAmount;
	}
	public void setTodayPendingAmount(Long todayPendingAmount) {
		this.todayPendingAmount = todayPendingAmount;
	}
	public Long getYesterDayPendingAmount() {
		return yesterDayPendingAmount;
	}
	public void setYesterDayPendingAmount(Long yesterDayPendingAmount) {
		this.yesterDayPendingAmount = yesterDayPendingAmount;
	}
	public Long getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(Long totalRecords) {
		this.totalRecords = totalRecords;
	}
	public Long getTodayRecords() {
		return todayRecords;
	}
	public void setTodayRecords(Long todayRecords) {
		this.todayRecords = todayRecords;
	}
	public Long getTotalUsers() {
		return totalUsers;
	}
	public void setTotalUsers(Long totalUsers) {
		this.totalUsers = totalUsers;
	}
	public Long getTodayUsers() {
		return todayUsers;
	}
	public void setTodayUsers(Long todayUsers) {
		this.todayUsers = todayUsers;
	}
	public Long getYesterDayUsers() {
		return yesterDayUsers;
	}
	public void setYesterDayUsers(Long yesterDayUsers) {
		this.yesterDayUsers = yesterDayUsers;
	}
	public Long getOtpReqCount() {
		return otpReqCount;
	}
	public void setOtpReqCount(Long otpReqCount) {
		this.otpReqCount = otpReqCount;
	}
	public Long getTodayMoneyCollected() {
		return todayMoneyCollected;
	}
	public void setTodayMoneyCollected(Long todayMoneyCollected) {
		this.todayMoneyCollected = todayMoneyCollected;
	}
	public Long getYesterDayMoneyCollected() {
		return yesterDayMoneyCollected;
	}
	public void setYesterDayMoneyCollected(Long yesterDayMoneyCollected) {
		this.yesterDayMoneyCollected = yesterDayMoneyCollected;
	}
	public Long getTodayPaidAmount() {
		return todayPaidAmount;
	}
	public void setTodayPaidAmount(Long todayPaidAmount) {
		this.todayPaidAmount = todayPaidAmount;
	}
	public Long getYesterDayPaidAmount() {
		return yesterDayPaidAmount;
	}
	public void setYesterDayPaidAmount(Long yesterDayPaidAmount) {
		this.yesterDayPaidAmount = yesterDayPaidAmount;
	}
	
	
	
}
