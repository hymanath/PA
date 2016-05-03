package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

import com.itgrids.partyanalyst.model.BaseModel;

public class PaymentGatewayVO extends BaseModel implements java.io.Serializable{

	private Long id;
	private String name;
	private String workingKey;
	private String preURL;
	private String postURL;
	private String redirectURL;
	private String orderNo;
	private String checkSum;
	private String merchantId;
	private String amount;
	private List<PaymentGatewayVO> responsePropertyFiledsVOLIst = new ArrayList<PaymentGatewayVO>(0);
	
	
	public PaymentGatewayVO(){}
	public PaymentGatewayVO(Long id,String name){
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWorkingKey() {
		return workingKey;
	}

	public void setWorkingKey(String workingKey) {
		this.workingKey = workingKey;
	}

	public String getPreURL() {
		return preURL;
	}

	public void setPreURL(String preURL) {
		this.preURL = preURL;
	}

	public String getPostURL() {
		return postURL;
	}

	public void setPostURL(String postURL) {
		this.postURL = postURL;
	}

	public String getRedirectURL() {
		return redirectURL;
	}

	public void setRedirectURL(String redirectURL) {
		this.redirectURL = redirectURL;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getCheckSum() {
		return checkSum;
	}

	public void setCheckSum(String checkSum) {
		this.checkSum = checkSum;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	
	
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
}
