package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pm_department")
public class PmDepartment {
	
	
	
	private Long pmDepartmentId;
	private String department;
	private String shortName;
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
	@Column(name="pm_department_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPmDepartmentId() {
		return pmDepartmentId;
	}
	public void setPmDepartmentId(Long pmDepartmentId) {
		this.pmDepartmentId = pmDepartmentId;
	}
	@Column(name="department")
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
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
	
	@Column(name="short_name")
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	

}
