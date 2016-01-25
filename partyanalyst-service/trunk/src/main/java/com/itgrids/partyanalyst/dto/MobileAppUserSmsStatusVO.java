package com.itgrids.partyanalyst.dto;

public class MobileAppUserSmsStatusVO {
	private Long mobileAppUserId;
	private Long totalSms;
	private Long sentSms;
	private Long pendingSms;
	private Long onlineSent;
	private Long simCardSent;
	public Long getMobileAppUserId() {
		return mobileAppUserId;
	}
	public void setMobileAppUserId(Long mobileAppUserId) {
		this.mobileAppUserId = mobileAppUserId;
	}
	public Long getTotalSms() {
		return totalSms;
	}
	public void setTotalSms(Long totalSms) {
		this.totalSms = totalSms;
	}
	public Long getSentSms() {
		return sentSms;
	}
	public void setSentSms(Long sentSms) {
		this.sentSms = sentSms;
	}
	public Long getPendingSms() {
		return pendingSms;
	}
	public void setPendingSms(Long pendingSms) {
		this.pendingSms = pendingSms;
	}
	public Long getOnlineSent() {
		return onlineSent;
	}
	public void setOnlineSent(Long onlineSent) {
		this.onlineSent = onlineSent;
	}
	public Long getSimCardSent() {
		return simCardSent;
	}
	public void setSimCardSent(Long simCardSent) {
		this.simCardSent = simCardSent;
	}
	

}
