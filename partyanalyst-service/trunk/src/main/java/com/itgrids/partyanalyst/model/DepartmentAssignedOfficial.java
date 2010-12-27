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
@Table(name = "department_assigned_official")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DepartmentAssignedOfficial extends BaseModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8687509418615398499L;
	private Long departmentAssignedOfficialId;
	private String designation;
	private String name;
	private String contactNo;
	
	public DepartmentAssignedOfficial(){

	}
	
	public DepartmentAssignedOfficial(Long departmentAssignedOfficialId){
		this.departmentAssignedOfficialId = departmentAssignedOfficialId;
	}
	
	public DepartmentAssignedOfficial(
			Long departmentAssignedOfficialId,
			String designation,
			String name,
			String contactNo) {
		this.departmentAssignedOfficialId = departmentAssignedOfficialId;
		this.designation = designation;
		this.name = name;
		this.contactNo = contactNo;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "department_assigned_official_id", unique = true, nullable = false)
	public Long getDepartmentAssignedOfficialId() {
		return departmentAssignedOfficialId;
	}
	
	public void setDepartmentAssignedOfficialId(Long departmentAssignedOfficialId) {
		this.departmentAssignedOfficialId = departmentAssignedOfficialId;
	}
	
	@Column(name = "designation", length = 50)
	public String getDesignation() {
		return designation;
	}
	
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	@Column(name = "name", length = 50)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "contact_no", length = 50)
	public String getContactNo() {
		return contactNo;
	}
	
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	
}
