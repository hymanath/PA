package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pm_subject")
public class PmSubject {
	
	private Long pmSubjectId;
	private String subject;
	private Long parentPmSubjectId;
	private String isDeleted;
	private Long orderNo;
	
	@Id
	@Column(name="pm_subject_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPmSubjectId() {
		return pmSubjectId;
	}
	public void setPmSubjectId(Long pmSubjectId) {
		this.pmSubjectId = pmSubjectId;
	}
	@Column(name="subject")
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	@Column(name="parent_pm_subject_d")
	public Long getParentPmSubjectId() {
		return parentPmSubjectId;
	}
	public void setParentPmSubjectId(Long parentPmSubjectId) {
		this.parentPmSubjectId = parentPmSubjectId;
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
