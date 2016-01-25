package com.itgrids.partyanalyst.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "mobile_app_user_sms_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MobileAppUserSmsStatus {
private Long mobileAppUserSmsStatusId;
private Long mobileAppUserId;
private MobileAppUser mobileAppUser;
private Long totalSms;
private Long sentSms;
private Long pendingSms;
private Long onlineSent;
private Long simCardSent;
private Date statusDate;
private Date insertedTime;
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "mobile_app_user_sms_status_id", unique = true, nullable = false)
public Long getMobileAppUserSmsStatusId() {
	return mobileAppUserSmsStatusId;
}
public void setMobileAppUserSmsStatusId(Long mobileAppUserSmsStatusId) {
	this.mobileAppUserSmsStatusId = mobileAppUserSmsStatusId;
}
@Column(name = "mobile_app_user_id")
public Long getMobileAppUserId() {
	return mobileAppUserId;
}
public void setMobileAppUserId(Long mobileAppUserId) {
	this.mobileAppUserId = mobileAppUserId;
}
@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
@JoinColumn(name = "mobile_app_user_id",insertable=false, updatable=false)
public MobileAppUser getMobileAppUser() {
	return mobileAppUser;
}
public void setMobileAppUser(MobileAppUser mobileAppUser) {
	this.mobileAppUser = mobileAppUser;
}
@Column(name = "total_sms")
public Long getTotalSms() {
	return totalSms;
}
public void setTotalSms(Long totalSms) {
	this.totalSms = totalSms;
}
@Column(name = "sent_sms")
public Long getSentSms() {
	return sentSms;
}
public void setSentSms(Long sentSms) {
	this.sentSms = sentSms;
}
@Column(name = "pending_sms")
public Long getPendingSms() {
	return pendingSms;
}
public void setPendingSms(Long pendingSms) {
	this.pendingSms = pendingSms;
}
@Column(name = "online_sent")
public Long getOnlineSent() {
	return onlineSent;
}
public void setOnlineSent(Long onlineSent) {
	this.onlineSent = onlineSent;
}
@Column(name = "sim_card_sent")
public Long getSimCardSent() {
	return simCardSent;
}
public void setSimCardSent(Long simCardSent) {
	this.simCardSent = simCardSent;
}
@Column(name = "status_date")
public Date getStatusDate() {
	return statusDate;
}
public void setStatusDate(Date statusDate) {
	this.statusDate = statusDate;
}
@Column(name = "inserted_time")
public Date getInsertedTime() {
	return insertedTime;
}
public void setInsertedTime(Date insertedTime) {
	this.insertedTime = insertedTime;
}


}
