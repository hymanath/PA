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
@Table(name = "payment_module_gateway_merchant_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PaymentModuleGatewayMerchantDetails extends BaseModel implements Serializable{
	
	private Long paymentModuleGatewayMerchantDetailsId;
	private Long paymentModuleId;
	private Long paymentModuleSubTypeId;
	private Long paymentGatewayId;
	private String marchantNo;
	private String workingKey;
	private String isDeleted;
	private String isActive;
	private String preURL;
	private String postURL;
	private String redirectURL;
	
	private PaymentModule paymentModule;
	private PaymentModuleSubType paymentModuleSubType;
	private PaymentGateway paymentGateway;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "payment_module_gateway_merchant_details_id", unique = true, nullable = false)
	public Long getPaymentModuleGatewayMerchantDetailsId() {
		return paymentModuleGatewayMerchantDetailsId;
	}
	public void setPaymentModuleGatewayMerchantDetailsId(
			Long paymentModuleGatewayMerchantDetailsId) {
		this.paymentModuleGatewayMerchantDetailsId = paymentModuleGatewayMerchantDetailsId;
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
	@Column(name = "payment_gateway_id")
	public Long getPaymentGatewayId() {
		return paymentGatewayId;
	}
	public void setPaymentGatewayId(Long paymentGatewayId) {
		this.paymentGatewayId = paymentGatewayId;
	}
	@Column(name = "marchant_no")
	public String getMarchantNo() {
		return marchantNo;
	}
	public void setMarchantNo(String marchantNo) {
		this.marchantNo = marchantNo;
	}
	@Column(name = "working_key")
	public String getWorkingKey() {
		return workingKey;
	}
	public void setWorkingKey(String workingKey) {
		this.workingKey = workingKey;
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
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="payment_gateway_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PaymentGateway getPaymentGateway() {
		return paymentGateway;
	}
	public void setPaymentGateway(PaymentGateway paymentGateway) {
		this.paymentGateway = paymentGateway;
	}
	
	@Column(name = "pre_url")
	public String getPreURL() {
		return preURL;
	}
	public void setPreURL(String preURL) {
		this.preURL = preURL;
	}
	
	@Column(name = "post_url")
	public String getPostURL() {
		return postURL;
	}
	public void setPostURL(String postURL) {
		this.postURL = postURL;
	}
	
	@Column(name = "redirect_url")
	public String getRedirectURL() {
		return redirectURL;
	}
	public void setRedirectURL(String redirectURL) {
		this.redirectURL = redirectURL;
	}
	
	
}
