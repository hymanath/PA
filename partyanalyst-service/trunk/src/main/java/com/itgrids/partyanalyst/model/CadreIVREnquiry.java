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
@Table(name = "cadre_ivr_enquiry")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class CadreIVREnquiry {
  private Long cadreIVREnquiryId;
  private Long userId;
  private Long locationTypeId;
  private Long locationValue;
  private String details;
  private String mobile;
  private Long received;
  private Long delivered;
  private Date insertedDate;
  private String callStatus;
  private String isDeleted;
  private Long constituencyId;
  
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cadre_ivr_enquiry_id", unique = true, nullable = false)
	public Long getCadreIVREnquiryId() {
		return cadreIVREnquiryId;
	}
	
	public void setCadreIVREnquiryId(Long cadreIVREnquiryId) {
		this.cadreIVREnquiryId = cadreIVREnquiryId;
	}
	
	@Column(name = "user_id")
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name = "location_type_id")
	public Long getLocationTypeId() {
		return locationTypeId;
	}
	
	public void setLocationTypeId(Long locationTypeId) {
		this.locationTypeId = locationTypeId;
	}
	
	@Column(name = "location_value")
	public Long getLocationValue() {
		return locationValue;
	}
	
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	
	@Column(name = "details")
	public String getDetails() {
		return details;
	}
	
	public void setDetails(String details) {
		this.details = details;
	}
	
	@Column(name = "mobile")
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Column(name = "received")
	public Long getReceived() {
		return received;
	}
	
	public void setReceived(Long received) {
		this.received = received;
	}
	
	@Column(name = "delivered")
	public Long getDelivered() {
		return delivered;
	}
	
	public void setDelivered(Long delivered) {
		this.delivered = delivered;
	}
	
	@Column(name = "inserted_date")
	public Date getInsertedDate() {
		return insertedDate;
	}
	
	public void setInsertedDate(Date insertedDate) {
		this.insertedDate = insertedDate;
	}
	
	@Column(name = "call_status")
	public String getCallStatus() {
		return callStatus;
	}
	
	public void setCallStatus(String callStatus) {
		this.callStatus = callStatus;
	}
	
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Column(name = "constituency_id")
	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
  
  
}
