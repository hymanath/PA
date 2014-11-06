package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "sms_job_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SmsJobStatus implements Serializable{

	
	private static final long serialVersionUID = -4898155853845716990L;
	private Long 						smsJobStatusId;
	private String 						jobCode;
	private String						mobileNumber;
	private String						smsStatus;
	private String						fromTask;
	private Long						tdpCadreId;
	private Date						smsSentTime;
	
	private TdpCadre 					tdpCadre;
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sms_job_status_id", unique = true, nullable = false)
	public Long getSmsJobStatusId() {
		return smsJobStatusId;
	}
	public void setSmsJobStatusId(Long smsJobStatusId) {
		this.smsJobStatusId = smsJobStatusId;
	}
	
	@Column(name="tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	
	@Column(name="job_code")
	public String getJobCode() {
		return jobCode;
	}
	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}
	
	@Column(name="mobile_number")
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	@Column(name="sms_status")
	public String getSmsStatus() {
		return smsStatus;
	}
	public void setSmsStatus(String smsStatus) {
		this.smsStatus = smsStatus;
	}
	
	@Column(name="from_task")
	public String getFromTask() {
		return fromTask;
	}
	public void setFromTask(String fromTask) {
		this.fromTask = fromTask;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_cadre_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}
	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}
	
	
	@Column(name="sms_sent_time")
	public Date getSmsSentTime() {
		return smsSentTime;
	}
	public void setSmsSentTime(Date smsSentTime) {
		this.smsSentTime = smsSentTime;
	}
	
	
	
	
	
}
