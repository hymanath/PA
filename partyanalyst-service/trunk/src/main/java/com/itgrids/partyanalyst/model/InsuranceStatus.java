package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 
 * @author Balu
 *
 */
@Entity
@Table(name = "insurance_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InsuranceStatus extends BaseModel implements Serializable{

	private Long insuranceStatusId;
	private String status;
	private Long orderNo;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "insurance_status_id", unique = true, nullable = false)
	public Long getInsuranceStatusId() {
		return insuranceStatusId;
	}
	public void setInsuranceStatusId(Long insuranceStatusId) {
		this.insuranceStatusId = insuranceStatusId;
	}
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name="order_no")
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	
}
