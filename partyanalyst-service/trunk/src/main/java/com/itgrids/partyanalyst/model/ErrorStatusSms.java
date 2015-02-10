package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;




@Entity
@Table(name = "error_status_sms")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ErrorStatusSms {
	
	private Long errorStatusSmsId;
	private String message;
	private String mobileNo;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "error_status_sms_id", unique = true, nullable = false)
	public Long getErrorStatusSmsId() {
		return errorStatusSmsId;
	}
	public void setErrorStatusSmsId(Long errorStatusSmsId) {
		this.errorStatusSmsId = errorStatusSmsId;
	}
	
	
	@Column(name="message")
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Column(name="mobile_no")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

}
