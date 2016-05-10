package com.itgrids.paymentgateway.dto;

import java.io.Serializable;

public class PaymentTransactionVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long paymentTransactionId;
	private Long paymentModuleGatewayMerchantDetailsId;
	private Long paymentGatewayId;
	private Long paymentMethodId;
	private Long transactionId;
	private Long transactionStatusId;
	private String transactionTime;
	private Long uuid;
	private String amount;
	private String ipAddress;
	private String  statusCode;
	private String preUrl;
	private String postUrl;
	private String redirectUrl;
	private Long  referenceUserId;
	private Long paymentModuleId;
	public Long getPaymentTransactionId() {
		return paymentTransactionId;
	}
	public void setPaymentTransactionId(Long paymentTransactionId) {
		this.paymentTransactionId = paymentTransactionId;
	}
	public Long getPaymentModuleGatewayMerchantDetailsId() {
		return paymentModuleGatewayMerchantDetailsId;
	}
	public void setPaymentModuleGatewayMerchantDetailsId(
			Long paymentModuleGatewayMerchantDetailsId) {
		this.paymentModuleGatewayMerchantDetailsId = paymentModuleGatewayMerchantDetailsId;
	}
	public Long getPaymentGatewayId() {
		return paymentGatewayId;
	}
	public void setPaymentGatewayId(Long paymentGatewayId) {
		this.paymentGatewayId = paymentGatewayId;
	}
	public Long getPaymentMethodId() {
		return paymentMethodId;
	}
	public void setPaymentMethodId(Long paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}
	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	public Long getTransactionStatusId() {
		return transactionStatusId;
	}
	public void setTransactionStatusId(Long transactionStatusId) {
		this.transactionStatusId = transactionStatusId;
	}
	public String getTransactionTime() {
		return transactionTime;
	}
	public void setTransactionTime(String transactionTime) {
		this.transactionTime = transactionTime;
	}
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getPreUrl() {
		return preUrl;
	}
	public void setPreUrl(String preUrl) {
		this.preUrl = preUrl;
	}
	public String getPostUrl() {
		return postUrl;
	}
	public void setPostUrl(String postUrl) {
		this.postUrl = postUrl;
	}
	public String getRedirectUrl() {
		return redirectUrl;
	}
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	public Long getReferenceUserId() {
		return referenceUserId;
	}
	public void setReferenceUserId(Long referenceUserId) {
		this.referenceUserId = referenceUserId;
	}
	public Long getPaymentModuleId() {
		return paymentModuleId;
	}
	public void setPaymentModuleId(Long paymentModuleId) {
		this.paymentModuleId = paymentModuleId;
	}
	
	

}
