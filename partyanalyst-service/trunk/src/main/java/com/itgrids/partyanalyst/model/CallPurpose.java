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
@Table(name="call_purpose")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CallPurpose implements java.io.Serializable {
	private Long callPurposeId;
	private String purpose;
	private Long orderNo;
	private String isDeleted;
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name = "call_purpose_id",nullable = false, unique = true)
	public Long getCallPurposeId() {
		return callPurposeId;
	}
	public void setCallPurposeId(Long callPurposeId) {
		this.callPurposeId = callPurposeId;
	}
	@Column(name = "purpose")
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	@Column(name = "order_no")
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
}
