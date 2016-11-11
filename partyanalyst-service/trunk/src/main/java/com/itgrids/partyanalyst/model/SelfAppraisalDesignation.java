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
@Table(name="self_appraisal_designation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SelfAppraisalDesignation {

	private Long selfAppraisalDesignationId;
	private String designation;
	private String isActive;
	private Long orderNo;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="self_appraisal_designation_id", unique=true, nullable=false)
	public Long getSelfAppraisalDesignationId() {
		return selfAppraisalDesignationId;
	}
	public void setSelfAppraisalDesignationId(Long selfAppraisalDesignationId) {
		this.selfAppraisalDesignationId = selfAppraisalDesignationId;
	}
	@Column(name="designation")
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	@Column(name="is_active")
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	@Column(name="order_no")  
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	
	
}
