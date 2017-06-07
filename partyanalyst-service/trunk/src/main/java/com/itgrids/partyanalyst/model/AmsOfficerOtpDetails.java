package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name = "ams_officer_otp_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AmsOfficerOtpDetails  extends BaseModel implements Serializable {

	
	private Long amsOfficerOtpDetailsId;
	private Long govtOfficerId;
	private String mobileNo;
	private String otpNo;
	private String referenceId;
	private Date generatedTime;
	private Date updatedTime;
	private String isValid;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ams_officer_otp_details_id", unique = true, nullable = false)
	
	public Long getAmsOfficerOtpDetailsId() {
		return amsOfficerOtpDetailsId;
	}
	public void setAmsOfficerOtpDetailsId(Long amsOfficerOtpDetailsId) {
		this.amsOfficerOtpDetailsId = amsOfficerOtpDetailsId;
	}
	
	@Column(name = "govt_officer_id")
	public Long getGovtOfficerId() {
		return govtOfficerId;
	}
	public void setGovtOfficerId(Long govtOfficerId) {
		this.govtOfficerId = govtOfficerId;
	}
	
	@Column(name = "mobile_no")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	@Column(name = "otp_no")
	public String getOtpNo() {
		return otpNo;
	}
	public void setOtpNo(String otpNo) {
		this.otpNo = otpNo;
	}
	@Column(name = "reference_id")
	public String getReferenceId() {
		return referenceId;
	}
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}
	@Column(name = "generated_time")
	public Date getGeneratedTime() {
		return generatedTime;
	}
	public void setGeneratedTime(Date generatedTime) {
		this.generatedTime = generatedTime;
	}
	@Column(name = "updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	@Column(name = "is_valid")
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	
	
	
}
