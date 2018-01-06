package com.itgrids.partyanalyst.model;

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
@Table(name = "otp_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OtpDetails {
	
	private Long otpDetailsId;
	private Long tdpCadreId;
	private String mobileNo;
	private String otp;
	private Date generatedTime;
	private Date updatedTime;
	private Character isValid;
	private String membershipId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "otp_details_id", unique = true, nullable = false)
	public Long getOtpDetailsId() {
		return otpDetailsId;
	}
	public void setOtpDetailsId(Long otpDetailsId) {
		this.otpDetailsId = otpDetailsId;
	}
	@Column(name = "tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	@Column(name = "mobile_no")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	@Column(name = "otp")
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
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
	public Character getIsValid() {
		return isValid;
	}
	public void setIsValid(Character isValid) {
		this.isValid = isValid;
	}
	@Column(name = "membership_id")
	public String getMembershipId() {
		return membershipId;
	}
	public void setMembershipId(String membershipId) {
		this.membershipId = membershipId;
	}
	
	
}
