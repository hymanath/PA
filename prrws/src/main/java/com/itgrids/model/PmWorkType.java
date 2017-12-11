package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pm_work_type")
public class PmWorkType {
	private Long pmWorkTypeId;
	private String workType;
	private String isDeleted;
	private Long orderNo;
	
	@Id
	@Column(name="pm_work_type_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPmWorkTypeId() {
		return pmWorkTypeId;
	}
	public void setPmWorkTypeId(Long pmWorkTypeId) {
		this.pmWorkTypeId = pmWorkTypeId;
	}
	@Column(name="work_type")
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
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
