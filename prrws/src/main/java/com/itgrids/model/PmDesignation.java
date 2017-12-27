package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pm_designation")
public class PmDesignation {
	
	private Long pmDesignationId;
	private String designation;
	private String isDeleted;
	private Long orderNo;
	private Long preferrableOrderNO;
	
	@Id
	@Column(name="pm_designation_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPmDesignationId() {
		return pmDesignationId;
	}
	public void setPmDesignationId(Long pmDesignationId) {
		this.pmDesignationId = pmDesignationId;
	}
	@Column(name="designation")
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name="order_no")
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	
	@Column(name="preferable_order_no")
	public Long getPreferrableOrderNO() {
		return preferrableOrderNO;
	}
	public void setPreferrableOrderNO(Long preferrableOrderNO) {
		this.preferrableOrderNO = preferrableOrderNO;
	}
	
	
}
