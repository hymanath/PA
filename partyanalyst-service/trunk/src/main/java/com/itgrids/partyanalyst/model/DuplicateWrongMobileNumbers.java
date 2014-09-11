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
@Table(name="duplicate_wrong_mobile_numbers")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DuplicateWrongMobileNumbers implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Long duplicateWrongMobileNumbersId;
	private String mobileNo;
	private String mobileType;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="duplicate_wrong_mobile_numbers_id")
	public Long getDuplicateWrongMobileNumbersId() {
		return duplicateWrongMobileNumbersId;
	}
	public void setDuplicateWrongMobileNumbersId(Long duplicateWrongMobileNumbersId) {
		this.duplicateWrongMobileNumbersId = duplicateWrongMobileNumbersId;
	}
	
	@Column(name="mobile_number")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	@Column(name="mobile_number_type")
	public String getMobileType() {
		return mobileType;
	}
	public void setMobileType(String mobileType) {
		this.mobileType = mobileType;
	}
	
}
