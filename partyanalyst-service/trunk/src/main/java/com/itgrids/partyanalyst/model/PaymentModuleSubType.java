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
@Table(name = "payment_module_sub_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PaymentModuleSubType extends BaseModel implements Serializable{
	
	private Long paymentModuleSubTypeId;
	private String subType;
	private Long isDeleted;
	private Date isActive;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "payment_module_sub_type_id", unique = true, nullable = false)
	public Long getPaymentModuleSubTypeId() {
		return paymentModuleSubTypeId;
	}
	public void setPaymentModuleSubTypeId(Long paymentModuleSubTypeId) {
		this.paymentModuleSubTypeId = paymentModuleSubTypeId;
	}
	@Column(name = "sub_type")
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
	@Column(name = "is_deleted")
	public Long getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Long isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name = "is_active")
	public Date getIsActive() {
		return isActive;
	}
	public void setIsActive(Date isActive) {
		this.isActive = isActive;
	}
	
}
