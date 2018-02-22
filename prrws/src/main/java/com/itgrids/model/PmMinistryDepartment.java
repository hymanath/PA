package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pm_ministry_department")
public class PmMinistryDepartment  {
	
	private Long pmMinistryDepartmentId;
	private String ministryName;
	private String isActive;
	private String isDeleted;
	
	@Id
	@Column(name="pm_ministry_department_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPmMinistryDepartmentId() {
		return pmMinistryDepartmentId;
	}
	public void setPmMinistryDepartmentId(Long pmMinistryDepartmentId) {
		this.pmMinistryDepartmentId = pmMinistryDepartmentId;
	}
	@Column(name="ministry_name")
	public String getMinistryName() {
		return ministryName;
	}
	public void setMinistryName(String ministryName) {
		this.ministryName = ministryName;
	}
	@Column(name="is_active")
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

}
