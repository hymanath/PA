package com.itgrids.paymentgateway.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="payment_method")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PaymentMethod extends BaseModel  implements Serializable{

	private static final long serialVersionUID = 49424353562240371L;
	
	private Long paymentMethodId;
	private String method;
	private String isActive;
	private String isDeleted;
	
	//public PaymentMethod(){}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="payment_method_id", unique=true, nullable=false)
	public Long getPaymentMethodId() {
		return paymentMethodId;
	}

	public void setPaymentMethodId(Long paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}

	@Column(name = "method", length = 10)
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	@Column(name="is_active")
	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
}
