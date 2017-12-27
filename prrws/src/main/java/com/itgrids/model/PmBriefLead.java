package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pm_brief_lead")
public class PmBriefLead {
	
	private Long pmBriefLeadId;
	private String briefLead;
	private String isDeleted;
	private Long orderNo;
	private Long preferrableOrderNO;
	
	@Column(name="preferable_order_no")
	public Long getPreferrableOrderNO() {
		return preferrableOrderNO;
	}
	public void setPreferrableOrderNO(Long preferrableOrderNO) {
		this.preferrableOrderNO = preferrableOrderNO;
	}
	@Id
	@Column(name="pm_brief_lead_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPmBriefLeadId() {
		return pmBriefLeadId;
	}
	public void setPmBriefLeadId(Long pmBriefLeadId) {
		this.pmBriefLeadId = pmBriefLeadId;
	}
	@Column(name="brief_lead")
	public String getBriefLead() {
		return briefLead;
	}
	public void setBriefLead(String briefLead) {
		this.briefLead = briefLead;
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
