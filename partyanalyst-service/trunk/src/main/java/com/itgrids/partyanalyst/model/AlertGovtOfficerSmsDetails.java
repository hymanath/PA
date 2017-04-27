package com.itgrids.partyanalyst.model;

import java.io.Serializable;
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
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="alert_govt_officer_sms_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlertGovtOfficerSmsDetails extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long alertGovtOfficerSmsDetailsId;
	private Long userId;
	private Long govtOfficerId;
	private String mobileNo;
	//private User user;
	
	private Long alertId;
	private Long alertStatusId;
	private String smsText;
	private Date insertTime;
	private Alert alert;
	private User user;
	private GovtOfficerNew govtOfficer;
	private AlertStatus alertStatus;
	private GovtAlertSubTask govtAlertSubTask;
	private GovtAlertActionType govtAlertActionType;
	private Long govtAlertSubTaskId;
	private Long govtAlertActionTypeId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alert_govt_officer_sms_details_id", unique = true, nullable = false)
	public Long getAlertGovtOfficerSmsDetailsId() {
		return alertGovtOfficerSmsDetailsId;
	}
	public void setAlertGovtOfficerSmsDetailsId(Long alertGovtOfficerSmsDetailsId) {
		this.alertGovtOfficerSmsDetailsId = alertGovtOfficerSmsDetailsId;
	}
	
	@Column(name="user_id")
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name="govt_officer_id")
	public Long getGovtOfficerId() {
		return govtOfficerId;
	}
	public void setGovtOfficerId(Long govtOfficerId) {
		this.govtOfficerId = govtOfficerId;
	}
	
	@Column(name="mobile_no")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	@Column(name="alert_id")
	public Long getAlertId() {
		return alertId;
	}
	public void setAlertId(Long alertId) {
		this.alertId = alertId;
	}
	
	@Column(name="alert_status_id")
	public Long getAlertStatusId() {
		return alertStatusId;
	}
	public void setAlertStatusId(Long alertStatusId) {
		this.alertStatusId = alertStatusId;
	}
	
	@Column(name="sms_text")
	public String getSmsText() {
		return smsText;
	}
	public void setSmsText(String smsText) {
		this.smsText = smsText;
	}
	
	@Column(name="insert_time")
	public Date getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_id" ,insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Alert getAlert() {
		return alert;
	}
	public void setAlert(Alert alert) {
		this.alert = alert;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id" ,insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	/*@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "govt_officer_id" ,insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtOfficer getGovtOfficer() {
		return govtOfficer;
	}
	public void setGovtOfficer(GovtOfficer govtOfficer) {
		this.govtOfficer = govtOfficer;
	}*/
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_status_id" ,insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AlertStatus getAlertStatus() {
		return alertStatus;
	}
	
	public void setAlertStatus(AlertStatus alertStatus) {
		this.alertStatus = alertStatus;
	}
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "govt_officer_id" ,insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtOfficerNew getGovtOfficer() {
		return govtOfficer;
	}
	public void setGovtOfficer(GovtOfficerNew govtOfficer) {
		this.govtOfficer = govtOfficer;
	}
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "govt_alert_sub_task_id" ,insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtAlertSubTask getGovtAlertSubTask() {
		return govtAlertSubTask;
	}
	public void setGovtAlertSubTask(GovtAlertSubTask govtAlertSubTask) {
		this.govtAlertSubTask = govtAlertSubTask;
	}
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "govt_alert_action_type_id" ,insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtAlertActionType getGovtAlertActionType() {
		return govtAlertActionType;
	}
	public void setGovtAlertActionType(GovtAlertActionType govtAlertActionType) {
		this.govtAlertActionType = govtAlertActionType;
	}
	@Column(name="govt_alert_sub_task_id")
	public Long getGovtAlertSubTaskId() {
		return govtAlertSubTaskId;
	}
	public void setGovtAlertSubTaskId(Long govtAlertSubTaskId) {
		this.govtAlertSubTaskId = govtAlertSubTaskId;
	}
	@Column(name="govt_alert_action_type_id")
	public Long getGovtAlertActionTypeId() {
		return govtAlertActionTypeId;
	}
	public void setGovtAlertActionTypeId(Long govtAlertActionTypeId) {
		this.govtAlertActionTypeId = govtAlertActionTypeId;
	}
	
	
}
