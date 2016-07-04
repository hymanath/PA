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
@Table(name="call_support_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CallSupportType implements java.io.Serializable {
	private Long callSupportTypeId;
	private String supportType;
	private Long orderNo;
	private String isDeleted;
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name = "call_support_type_id",nullable = false, unique = true)
	public Long getCallSupportTypeId() {
		return callSupportTypeId;
	}
	public void setCallSupportTypeId(Long callSupportTypeId) {
		this.callSupportTypeId = callSupportTypeId;
	}
	@Column(name = "support_type")
	public String getSupportType() {
		return supportType;
	}
	public void setSupportType(String supportType) {
		this.supportType = supportType;
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
