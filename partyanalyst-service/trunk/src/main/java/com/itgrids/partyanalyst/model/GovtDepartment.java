package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "govt_department")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GovtDepartment extends BaseModel implements Serializable{

	private Long govtDepartmentId;
	private String departmentName;
	private String color;
	
	private Long cnpGovtDepartmentId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "govt_department_id", unique = true, nullable = false)
	public Long getGovtDepartmentId() {
		return govtDepartmentId;
	}
	public void setGovtDepartmentId(Long govtDepartmentId) {
		this.govtDepartmentId = govtDepartmentId;
	}
	
	@Column(name = "department_name")
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	@Column(name = "cnp_govt_department_id")
	public Long getCnpGovtDepartmentId() {
		return cnpGovtDepartmentId;
	}
	public void setCnpGovtDepartmentId(Long cnpGovtDepartmentId) {
		this.cnpGovtDepartmentId = cnpGovtDepartmentId;
	}
	@Column(name = "color")
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	
}
