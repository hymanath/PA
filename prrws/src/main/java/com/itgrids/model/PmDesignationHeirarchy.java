package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pm_designation_heirarchy")
public class PmDesignationHeirarchy {
	
	private Long pmDesignationHeirarchyId;
	private Long pmDepartmentDesignationId;
	private Long subPmDepartmentDesignationId;
	private String isActive;
	private Long orderNo;
	
	@Id
	@Column(name="pm_designation_heirarchy_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPmDesignationHeirarchyId() {
		return pmDesignationHeirarchyId;
	}
	public void setPmDesignationHeirarchyId(Long pmDesignationHeirarchyId) {
		this.pmDesignationHeirarchyId = pmDesignationHeirarchyId;
	}
	@Column(name="pm_dept_designation_id")
	public Long getPmDepartmentDesignationId() {
		return pmDepartmentDesignationId;
	}
	public void setPmDepartmentDesignationId(Long pmDepartmentDesignationId) {
		this.pmDepartmentDesignationId = pmDepartmentDesignationId;
	}
	@Column(name="sub_pm_dept_designation_id")
	public Long getSubPmDepartmentDesignationId() {
		return subPmDepartmentDesignationId;
	}
	public void setSubPmDepartmentDesignationId(Long subPmDepartmentDesignationId) {
		this.subPmDepartmentDesignationId = subPmDepartmentDesignationId;
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
