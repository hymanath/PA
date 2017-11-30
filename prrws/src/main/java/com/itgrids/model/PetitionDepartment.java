package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "petition_department")
public class PetitionDepartment {

	private Long petitionDepartmentId;
	private String departmentName ;
	private Long orderNo;
	private String isDeleted;
	
	@Id
	@Column(name="petition_department_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPetitionDepartmentId() {
		return petitionDepartmentId;
	}
	public void setPetitionDepartmentId(Long petitionDepartmentId) {
		this.petitionDepartmentId = petitionDepartmentId;
	}
	@Column(name="department_name")
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	@Column(name="order_no")	
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
}
