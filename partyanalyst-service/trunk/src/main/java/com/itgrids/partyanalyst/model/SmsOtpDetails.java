
package com.itgrids.partyanalyst.model;

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
@Table(name = "sms_otp_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SmsOtpDetails  extends BaseModel{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2002236293800949300L;
	private Long smsOtpDetailsId;
	private String otpReferenceId;
	private String otpNo;
	private String isDeleted;
	private Date insertedTime;
	private Date updatedTime;
	private String mobileNo;
	private Long userId;
	private User user;
	
	public SmsOtpDetails() {
		
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sms_otp_details_id", nullable = false, unique = true)
	public Long getSmsOtpDetailsId() {
		return smsOtpDetailsId;
	}

	public void setSmsOtpDetailsId(Long smsOtpDetailsId) {
		this.smsOtpDetailsId = smsOtpDetailsId;
	}
	@Column(name = "otp_reference_id")
	public String getOtpReferenceId() {
		return otpReferenceId;
	}

	public void setOtpReferenceId(String otpReferenceId) {
		this.otpReferenceId = otpReferenceId;
	}
	@Column(name = "otp_no")
	public String getOtpNo() {
		return otpNo;
	}

	public void setOtpNo(String otpNo) {
		this.otpNo = otpNo;
	}
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@Column(name = "updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	@Column(name = "mobile_no")
	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	@Column(name = "user_id")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}
