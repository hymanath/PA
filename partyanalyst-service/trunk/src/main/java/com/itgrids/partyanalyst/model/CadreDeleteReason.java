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
@Table(name = "cadre_delete_reason")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadreDeleteReason {

	private Long cadreDeleteReasonId;
	private String reason;
	private String orderNo;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cadre_delete_reason_id", unique=true, nullable=false)
	public Long getCadreDeleteReasonId() {
		return cadreDeleteReasonId;
	}
	public void setCadreDeleteReasonId(Long cadreDeleteReasonId) {
		this.cadreDeleteReasonId = cadreDeleteReasonId;
	}
	
	@Column(name="reason")
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	@Column(name="order_no")
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
}
