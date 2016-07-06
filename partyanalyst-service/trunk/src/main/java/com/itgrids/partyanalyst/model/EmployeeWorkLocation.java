package com.itgrids.partyanalyst.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="employee_work_location")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EmployeeWorkLocation extends BaseModel {
	private static final long serialVersionUID = -4534799780105723169L;
	
	private Long employeeWorkLocationId;
	private Long partyOfficeId;
	private Long employeeId;
	private String isDeleted;
	
	private PartyOffice partyOffice;
	private Employee employee;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "employee_work_location_id", unique = true, nullable = false)
	public Long getEmployeeWorkLocationId() {
		return employeeWorkLocationId;
	}
	public void setEmployeeWorkLocationId(Long employeeWorkLocationId) {
		this.employeeWorkLocationId = employeeWorkLocationId;
	}
	@Column(name = "party_office_id")
	public Long getPartyOfficeId() {
		return partyOfficeId;
	}
	public void setPartyOfficeId(Long partyOfficeId) {
		this.partyOfficeId = partyOfficeId;
	}
	@Column(name = "employee_id")
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="party_office_id", insertable=false, updatable = false )
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PartyOffice getPartyOffice() {
		return partyOffice;
	}  
	public void setPartyOffice(PartyOffice partyOffice) {
		this.partyOffice = partyOffice;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="employee_id", insertable=false, updatable = false )
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
