package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "payment_transaction")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PaymentTransaction extends BaseModel implements Serializable{
	
	private Long paymentTransactionId;
	private Long paymentModuleGatewayMerchantDetailsId;
	private Long paymentGatewayId;
	private Long paymentMethodId;
	private String transactionId;
	private Long transactionStatusId;
	private Date transactionTime;
	private String uuid;
	private String amount;
	private String ipAddress;
	private String  statusCode;
	private String preUrl;
	private String postUrl;
	private String redirectUrl;
	private String  referenceUserId;
	private Long paymentModuleId;
	
	private PaymentGateway paymentGateway;
	private PaymentModule paymentModule;
	private PaymentModuleGatewayMerchantDetails paymentModuleGatewayMerchantDetails;
	private TransactionStatus transactionStatus;
	private PaymentMethod PaymentMethod;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "payment_transaction_id", unique = true, nullable = false)
	public Long getPaymentTransactionId() {
		return paymentTransactionId;
	}
	public void setPaymentTransactionId(Long paymentTransactionId) {
		this.paymentTransactionId = paymentTransactionId;
	}
	@Column(name="payment_module_gateway_merchant_details_id")
	public Long getPaymentModuleGatewayMerchantDetailsId() {
		return paymentModuleGatewayMerchantDetailsId;
	}
	public void setPaymentModuleGatewayMerchantDetailsId(
			Long paymentModuleGatewayMerchantDetailsId) {
		this.paymentModuleGatewayMerchantDetailsId = paymentModuleGatewayMerchantDetailsId;
	}
	@Column(name="payment_gateway_id")
	public Long getPaymentGatewayId() {
		return paymentGatewayId;
	}
	public void setPaymentGatewayId(Long paymentGatewayId) {
		this.paymentGatewayId = paymentGatewayId;
	}
	@Column(name="payment_method_id")
	public Long getPaymentMethodId() {
		return paymentMethodId;
	}
	public void setPaymentMethodId(Long paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}
	@Column(name="transaction_id")
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	@Column(name="transaction_status_id")
	public Long getTransactionStatusId() {
		return transactionStatusId;
	}
	public void setTransactionStatusId(Long transactionStatusId) {
		this.transactionStatusId = transactionStatusId;
	}
	@Column(name="transaction_time")
	public Date getTransactionTime() {
		return transactionTime;
	}
	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}
	@Column(name="uuid")
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	@Column(name="amount")
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	@Column(name="ip_address")
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	@Column(name="status_code")
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	@Column(name="pre_url")
	public String getPreUrl() {
		return preUrl;
	}
	public void setPreUrl(String preUrl) {
		this.preUrl = preUrl;
	}
	@Column(name="post_url")
	public String getPostUrl() {
		return postUrl;
	}
	public void setPostUrl(String postUrl) {
		this.postUrl = postUrl;
	}
	@Column(name="redirect_url")
	public String getRedirectUrl() {
		return redirectUrl;
	}
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	@Column(name="reference_user_id")
	public String getReferenceUserId() {
		return referenceUserId;
	}
	public void setReferenceUserId(String referenceUserId) {
		this.referenceUserId = referenceUserId;
	}
	@Column(name="payment_module_id")
	public Long getPaymentModuleId() {
		return paymentModuleId;
	}
	public void setPaymentModuleId(Long paymentModuleId) {
		this.paymentModuleId = paymentModuleId;
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
	@JoinColumn(name="payment_module_gateway_merchant_details_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PaymentModuleGatewayMerchantDetails getPaymentModuleGatewayMerchantDetails() {
		return paymentModuleGatewayMerchantDetails;
	}
	public void setPaymentModuleGatewayMerchantDetails(
			PaymentModuleGatewayMerchantDetails paymentModuleGatewayMerchantDetails) {
		this.paymentModuleGatewayMerchantDetails = paymentModuleGatewayMerchantDetails;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="transaction_status_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="payment_method_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PaymentMethod getPaymentMethod() {
		return PaymentMethod;
	}
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		PaymentMethod = paymentMethod;
	}
	
}
