package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pm_lead")
public class PmLead {

	
	private Long pmLeadId;
	private String leadName;
	private String isDeleted;
	private Long orderNo;
	

	@Id
	@Column(name="pm_lead_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPmLeadId() {
		return pmLeadId;
	}
	public void setPmLeadId(Long pmLeadId) {
		this.pmLeadId = pmLeadId;
	}
	@Column(name="lead_name")
	public String getLeadName() {
		return leadName;
	}
	public void setLeadName(String leadName) {
		this.leadName = leadName;
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
}
