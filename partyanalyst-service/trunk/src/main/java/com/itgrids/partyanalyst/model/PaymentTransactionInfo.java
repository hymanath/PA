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

/**
 * @author srishailam.pittala@itgrids.com
 * @Date 1st Nov, 2016
 */

@Entity
@Table(name = "payment_transaction_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PaymentTransactionInfo extends BaseModel implements java.io.Serializable{

	private Long paymentTransactionInfoId;
	private String transactionId;
	private String status;
	private Date transactionTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "payment_transaction_info_id", unique = true, nullable = false)
	public Long getPaymentTransactionInfoId() {
		return paymentTransactionInfoId;
	}
	public void setPaymentTransactionInfoId(Long paymentTransactionInfoId) {
		this.paymentTransactionInfoId = paymentTransactionInfoId;
	}
	
	@Column(name = "transaction_id")
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "transaction_time")
	public Date getTransactionTime() {
		return transactionTime;
	}
	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}
	
	
}
