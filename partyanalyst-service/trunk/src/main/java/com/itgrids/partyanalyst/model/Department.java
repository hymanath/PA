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
@Table(name="department")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Department extends BaseModel {
	private static final long serialVersionUID = 8083951793368320150L;
	
	private Long departmentId;
	private String departmentName;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "department_id", unique = true, nullable = false)
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	@Column(name = "department_name")
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
}
