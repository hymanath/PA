package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
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
@Table(name = "payment_amount")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PaymentAmount extends BaseModel implements Serializable{
	
	private Long paymentAmountId;
	private Long paymentModuleId;
	private Long paymentModuleSubTypeId;
	private Long amount;
	private String isDeleted;
	private String isActive;
	
	private PaymentModule paymentModule;
	private PaymentModuleSubType paymentModuleSubType;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "payment_amount_id", unique = true, nullable = false)
	public Long getPaymentAmountId() {
		return paymentAmountId;
	}
	public void setPaymentAmountId(Long paymentAmountId) {
		this.paymentAmountId = paymentAmountId;
	}
	@Column(name = "payment_module_id")
	public Long getPaymentModuleId() {
		return paymentModuleId;
	}
	public void setPaymentModuleId(Long paymentModuleId) {
		this.paymentModuleId = paymentModuleId;
	}
	@Column(name = "payment_module_sub_type_id")
	public Long getPaymentModuleSubTypeId() {
		return paymentModuleSubTypeId;
	}
	public void setPaymentModuleSubTypeId(Long paymentModuleSubTypeId) {
		this.paymentModuleSubTypeId = paymentModuleSubTypeId;
	}
	@Column(name = "amount")
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name = "is_active")
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="payment_module_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PaymentModule getPaymentModule() {
		return paymentModule;
	}
	public void setPaymentModule(PaymentModule paymentModule) {
		this.paymentModule = paymentModule;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="payment_module_sub_type_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PaymentModuleSubType getPaymentModuleSubType() {
		return paymentModuleSubType;
	}
	public void setPaymentModuleSubType(PaymentModuleSubType paymentModuleSubType) {
		this.paymentModuleSubType = paymentModuleSubType;
	}
}
